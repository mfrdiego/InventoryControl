package com.diego.InventoryControl.domain.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter @Setter
@Table(name = "product")
public class ProductModel {

    @Id
    @Column(nullable = false, length = 50)
    private String partNumber;

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
