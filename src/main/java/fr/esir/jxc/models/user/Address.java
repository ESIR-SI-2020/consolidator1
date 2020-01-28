package fr.esir.jxc.models.user;

import lombok.Value;

@Value
public class Address {

    private String postalCode;
    private String street;
    private Integer streetNumber;
    private String complement;

}