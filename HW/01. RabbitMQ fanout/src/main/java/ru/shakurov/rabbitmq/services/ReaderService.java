package ru.shakurov.rabbitmq.services;

import org.apache.commons.lang3.SerializationUtils;
import ru.shakurov.rabbitmq.domain.RequestData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReaderService {

    public byte[] readClient() {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            RequestData data = new RequestData();
            System.out.println("=================================CLIENT=======================================");

            System.out.print("Имя: ");
            data.setName(reader.readLine());

            System.out.print("Фамилия: ");
            data.setLastName(reader.readLine());

            System.out.print("Номер паспорта: ");
            data.setPassportNumber(reader.readLine());

            System.out.print("Дата выдадчи: ");
            data.setPassportDate(reader.readLine());

            System.out.print("Возраст: ");
            data.setAge(reader.readLine());
            System.out.println("=================================CLIENT=======================================\n");

            return SerializationUtils.serialize(data);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }


}
