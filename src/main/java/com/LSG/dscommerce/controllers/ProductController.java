package com.LSG.dscommerce.controllers;

import com.LSG.dscommerce.dto.ProductDTO;
import com.LSG.dscommerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/products")
public class ProductController {
    @Autowired
    private ProductService service;

    @GetMapping(value = "/{id}")
    public ProductDTO findById(@PathVariable Long id) {
        ProductDTO dto = service.findById(id);
        return dto;
    }
    @GetMapping
    public Page<ProductDTO> findAll(Pageable pageable){
        Page<ProductDTO> dto =  service.findAll(pageable);
        return dto;
    }
}
