package fr.esir.jxc.models.user;

import lombok.Data;

@Data
public class Address {

    String postalCode;
    String street;
    Integer streetNumber;
    String complement;

}