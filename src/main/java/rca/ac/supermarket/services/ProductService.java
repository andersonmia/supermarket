package rca.ac.supermarket.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rca.ac.supermarket.DTO.ProductDTO;
import rca.ac.supermarket.exceptions.ResourceNotFoundException;
import rca.ac.supermarket.models.Product;
import rca.ac.supermarket.repositories.ProductRepository;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Product saveProduct(ProductDTO productDTO) {
        Product product = productDTO.toEntity();
        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product findByCode(String code) {
        return productRepository.findByCode(code).orElseThrow(()->new ResourceNotFoundException("Product", "code", code));
    }
}
