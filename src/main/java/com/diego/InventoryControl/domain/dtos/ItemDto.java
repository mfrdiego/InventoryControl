package com.diego.InventoryControl.domain.dtos;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class ItemDto {

    @NotBlank
    @Size(max = 10)
    private String serialNumber;

    @NotBlank
    private String local;

    @NotBlank
    private String quality;

    @NotBlank
    private String note;
}
