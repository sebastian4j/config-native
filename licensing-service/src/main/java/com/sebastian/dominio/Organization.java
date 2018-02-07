package com.sebastian.dominio;

import java.io.Serializable;
import lombok.Data;

@Data
public class Organization implements Serializable {
    private String id;
    private String name;
    private String contactName;
    private String contactEmail;
    private String contactPhone;

}
