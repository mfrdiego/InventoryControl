package com.diego.InventoryControl.domain.model;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter @Setter
@Table(name = "product")
public class ProductModel {

    @Id
    @Column(nullable = false, length = 50)
    private String serialNumber;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 100)
    private String description;

    @Column(nullable = false, length = 100)
    private String status;

    @Column(nullable = false, length = 100)
    private int quantity;

    @Column(nullable = false, length = 100)
    private String category;
}
