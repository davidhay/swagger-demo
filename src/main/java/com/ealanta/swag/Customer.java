package com.ealanta.swag;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Customer {

    private String id;
    private String first;
    private String last;
    private LocalDate dob;

}
