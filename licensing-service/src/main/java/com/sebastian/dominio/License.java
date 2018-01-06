package com.sebastian.dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "licenses")
public class License {
    @Id
    @Column(name = "license_id", nullable = false)
    private String id;
    @Column(name = "product_name", nullable = false)
    private String productName;
    @Column(name = "licence_type", nullable = false)
    private String licenceType;
    @Column(name = "organization_id", nullable = false)
    private String organizationId;
}
