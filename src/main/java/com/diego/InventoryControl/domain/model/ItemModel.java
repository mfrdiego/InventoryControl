package com.diego.InventoryControl.domain.model;


import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "item")
public class ItemModel {

    @Id
    @Column(nullable = false, length = 10)
    private String serialNumber;

    @Column(nullable = false, length = 100)
    private String local;

    @Column (nullable = false, length = 100)
    private String quality;

    @Column (nullable = false, length = 100)
    private String note;

    @ManyToOne
    @JoinColumn(name = "id_product")
    private ProductModel productModel;
}