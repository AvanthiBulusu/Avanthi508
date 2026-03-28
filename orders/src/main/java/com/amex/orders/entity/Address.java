package com.amex.orders.entity;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    private String street;
    private String city;
    private String zip;
}