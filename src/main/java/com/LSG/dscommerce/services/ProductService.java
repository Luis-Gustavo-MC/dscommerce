package com.LSG.dscommerce.services;

import com.LSG.dscommerce.dto.ProductDTO;
import com.LSG.dscommerce.entities.Product;
import com.LSG.dscommerce.repositories.ProductRepository;
import jakarta.persistence.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id){
        Optional<Product> result = repository.findById(id);
        Product product = result.get();
        ProductDTO productDTO = new ProductDTO(product);
        return productDTO;
    }
    @Transactional(readOnly = true)
    public Page<ProductDTO> findAll(Pageable pageable){
        Page<Product> result = repository.findAll(pageable);
        return result.map(ProductDTO::new);
    }
    @Transactional
    public ProductDTO insert(ProductDTO dto){

        Product entity = new Product();

        copyDtoToEntity(dto,entity);

        entity = repository.save(entity);

        return new ProductDTO(entity);
    }
    @Transactional
    public ProductDTO update(ProductDTO dto, Long id){
        Product entity = repository.getReferenceById(id);

        copyDtoToEntity(dto,entity);

        entity = repository.save(entity);

        return new ProductDTO(entity);
    }

    private void copyDtoToEntity(ProductDTO dto, Product entity) {
        entity.setName(dto.getName());
        entity.setDescripton(entity.getDescripton());
        entity.setImgURL(entity.getImgURL());
        entity.setPrice(entity.getPrice());
    }
}
