package rca.ac.supermarket.services;

import rca.ac.supermarket.DTO.ProductDTO;
import rca.ac.supermarket.models.Product;
import rca.ac.supermarket.exceptions.ResourceNotFoundException;

public interface ProductService {
    Product addProduct(ProductDTO productDTO);
    Iterable<Product> getAllProducts();
    Product getProductById(Long id) throws ResourceNotFoundException;
    Product updateProduct(Long id, ProductDTO productDetails) throws ResourceNotFoundException;
    void deleteProduct(Long id) throws ResourceNotFoundException;
}
