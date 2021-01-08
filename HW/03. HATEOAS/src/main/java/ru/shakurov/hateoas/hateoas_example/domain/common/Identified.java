package ru.shakurov.hateoas.hateoas_example.domain.common;

import java.io.Serializable;

public interface Identified extends Serializable {
    Serializable getId();
}
