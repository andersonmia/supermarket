package rca.ac.supermarket.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rca.ac.supermarket.DTO.ProductDTO;
import rca.ac.supermarket.models.Product;
import rca.ac.supermarket.repositories.ProductRepository;
import rca.ac.supermarket.exceptions.ResourceNotFoundException;
import rca.ac.supermarket.services.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product addProduct(ProductDTO productDTO) {
        Product product = productDTO.toEntity();
        return productRepository.save(product);
    }

    @Override
    public Iterable<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long id) throws ResourceNotFoundException {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", String.valueOf(id)));
    }

    @Override
    public Product updateProduct(Long id, ProductDTO productDetails) throws ResourceNotFoundException {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", String.valueOf(id)));
        product.setCode(productDetails.getCode());
        product.setName(productDetails.getName());
        product.setPrice(productDetails.getPrice());
        product.setInDate(productDetails.getInDate());
        product.setImage(productDetails.getImage());
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long id) throws ResourceNotFoundException {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", String.valueOf(id)));
        productRepository.delete(product);
    }
}
