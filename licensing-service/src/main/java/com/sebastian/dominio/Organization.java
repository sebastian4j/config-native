package com.sebastian.dominio;

import lombok.Data;

@Data
public class Organization {
    private String id;
    private String name;
    private String contactName;
    private String contactEmail;
    private String contactPhone;

}
