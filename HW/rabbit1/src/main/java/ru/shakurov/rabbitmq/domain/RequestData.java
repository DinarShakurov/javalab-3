package ru.shakurov.rabbitmq.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
@AllArgsConstructor
@Builder
public class RequestData implements Serializable {

    public RequestData(){
        this.id = UUID.randomUUID();
    }

    private UUID id;
    private String name;
    private String lastName;
    private String passportNumber;
    private String passportDate;
    private String age;
}
