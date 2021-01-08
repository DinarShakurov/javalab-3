package ru.shakurov.rabbitmq.consumer;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.styledxmlparser.css.media.MediaDeviceDescription;
import com.itextpdf.styledxmlparser.css.media.MediaType;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import com.rabbitmq.client.Delivery;
import ru.shakurov.rabbitmq.Props;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

public abstract class AbstractConsumer implements Runnable {

    public static final String SRC = "templates/";
    public static final String DEST = "generated/";

    public void run() {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(Props.getHost());

        try {
            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();
            channel.basicQos(1);

            channel.exchangeDeclare(exchangeName(), exchangeType());
            channel.queueDeclare(queueName(), true, false, false, null);
            AMQP.Queue.BindOk bindOk = channel.queueBind(queueName(), exchangeName(), exchangeRoutingKey());
            Objects.requireNonNull(bindOk);

            DeliverCallback deliverCallback = (consumerTag, message) -> {
                try {
                    handle(message);
                    channel.basicAck(message.getEnvelope().getDeliveryTag(), false);
                } catch (IOException | URISyntaxException e) {
                    System.err.println("FAILED");
                    channel.basicReject(message.getEnvelope().getDeliveryTag(), false);
                }
            };

            channel.basicConsume(queueName(), false, deliverCallback, consumerTag -> {
            });
        } catch (IOException | TimeoutException e) {
            throw new IllegalArgumentException(e);
        }
    }

    protected abstract String getFilename();

    protected abstract String exchangeName();

    protected abstract String exchangeType();

    protected abstract String exchangeRoutingKey();

    protected abstract String queueName();

    protected abstract String templateName();

    protected String data(String message) {
        return message;
    }

    private void handle(Delivery message) throws IOException, URISyntaxException {
        File file = new File(DEST);
        file.mkdirs();

        URI htmlSource = getClass().getClassLoader().getResource(SRC + templateName()).toURI();

        String dest = DEST + getFilename();

        PdfWriter writer = new PdfWriter(dest);
        PdfDocument pdfDoc = new PdfDocument(writer);

        pdfDoc.setTagged()
                .setDefaultPageSize(PageSize.A4);

        MediaDeviceDescription mediaDescription = new MediaDeviceDescription(MediaType.SCREEN);
        mediaDescription.setWidth(PageSize.A4.getWidth());

        ConverterProperties converterProperties = new ConverterProperties()
                .setMediaDeviceDescription(mediaDescription)
                .setBaseUri(SRC)
                .setCreateAcroForm(true);

        String html = Files.lines(Path.of(htmlSource)).collect(Collectors.joining())
                .replace("${data}", data(new String(message.getBody())));

        HtmlConverter.convertToPdf(new ByteArrayInputStream(html.getBytes()), pdfDoc, converterProperties);

        pdfDoc.close();
    }
}
