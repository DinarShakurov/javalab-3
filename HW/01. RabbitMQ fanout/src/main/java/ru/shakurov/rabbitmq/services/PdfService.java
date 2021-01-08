package ru.shakurov.rabbitmq.services;

import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.TextAlignment;
import org.apache.commons.lang3.SerializationUtils;
import ru.shakurov.rabbitmq.domain.RequestData;
import ru.shakurov.rabbitmq.domain.RequestType;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PdfService {

    private static final String DEST = "/pdf/";

    public void createPdf(byte[] data, RequestType type) throws IOException {
        switch (type) {
            case DISMISSAL:
                createDismissalPdf(data);
                break;
            case EXPULSION:
                createExpulsionPdf(data);
                break;
        }
    }

    private String createDir(RequestData request) throws IOException {
        String destDirectory = DEST + request.getId().toString();
        Files.createDirectories(Paths.get(destDirectory));
        return destDirectory;
    }

    private void createExpulsionPdf(byte[] data) throws IOException {
        RequestData request = SerializationUtils.deserialize(data);

        String destDirectory = createDir(request);

        String fileDest = destDirectory + "/" + RequestType.EXPULSION.name() + ".pdf";
        PdfDocument pdf = new PdfDocument(new PdfWriter(fileDest));

        Document document = new Document(pdf, PageSize.A4, true);
        document.setFont(getRussianFont());

        Paragraph paragraph1 = new Paragraph("EXPULSION - Заявление (типа отчисление)")
                .setTextAlignment(TextAlignment.CENTER)
                .setFontSize(20)
                .setBold();

        document.add(paragraph1);

        String result = "Я, " + request.getName() + " " + request.getLastName() + ", хочу отчислиться из вашей шаражкиной" +
                " конторы. Я хз зачем, но вот мои паспортные данные: " + request.getPassportNumber() + ", выдали мне его " +
                request.getPassportDate() + ". А ещё мне " + request.getAge() + " лет, если вдруг вы забыли.";
        Paragraph paragraph2 = new Paragraph(result);
        document.add(paragraph2);

        document.add(new Paragraph("Дата: " + new SimpleDateFormat("dd.MM.yyyy").format(new Date())));

        document.close();
    }

    private void createDismissalPdf(byte[] data) throws IOException {
        RequestData request = SerializationUtils.deserialize(data);

        String destDirectory = createDir(request);

        String fileDest = destDirectory + "/" + RequestType.DISMISSAL.name() + ".pdf";
        PdfDocument pdf = new PdfDocument(new PdfWriter(fileDest));

        Document document = new Document(pdf, PageSize.A4, true);
        document.setFont(getRussianFont());

        Paragraph paragraph1 = new Paragraph("DISMISSAL - Заявление (типа увольнение)")
                .setTextAlignment(TextAlignment.CENTER)
                .setFontSize(20)
                .setBold();

        document.add(paragraph1);

        String result = "Я, " + request.getName() + " " + request.getLastName() + ", хочу свалить из вашей шаражкиной" +
                " конторы. Я хз зачем, но вот мои паспортные данные: " + request.getPassportNumber() + ", выдали мне его " +
                request.getPassportDate() + ". А ещё мне " + request.getAge() + " лет, если вдруг вы забыли.";
        Paragraph paragraph2 = new Paragraph(result);
        document.add(paragraph2);

        document.add(new Paragraph("Дата: " + new SimpleDateFormat("dd.MM.yyyy").format(new Date())));

        document.setFont(getRussianFont());
        document.close();

    }

    private PdfFont getRussianFont() {
        try {
            return PdfFontFactory.createFont(
                    "src/main/resources/FreeSans.ttf",
                    "CP1251", true);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
