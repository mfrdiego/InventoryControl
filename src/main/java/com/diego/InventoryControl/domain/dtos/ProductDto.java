package com.diego.InventoryControl.domain.dtos;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Getter @Setter
public class ProductDto {

    @NotBlank
    @Size(max = 50)
    private String partNumber;

    @NotBlank
    private String name;

    @NotBlank
    private String category;

    @NotBlank
    private String description;

    @NotBlank
    private String status;

    @NotNull
    private int quantity;
}