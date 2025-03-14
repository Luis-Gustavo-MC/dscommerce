package com.LSG.dscommerce.dto;

import com.LSG.dscommerce.entities.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @AllArgsConstructor @NoArgsConstructor
public class ProductDTO {
    private Long id;
    @NotBlank(message = "Campo em Branco") @Size(min = 3, max = 80 , message = "Nome precisa ter de 3 a 80 caracteres")
    private String name;
    @NotBlank(message = "Campo em Branco") @Size(min = 10, message = "Descrição precisa ter no minimo 10 caracteres")
    private String description;
    @Positive
    private Double price;
    private String imgURL;

    public ProductDTO(Product entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.description = entity.getDescripton();
        this.price = entity.getPrice();
        this.imgURL = entity.getImgURL();
    }

}
