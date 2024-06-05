package rca.ac.supermarket.controllers;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rca.ac.supermarket.DTO.ProductDTO;
import rca.ac.supermarket.models.Product;
import rca.ac.supermarket.services.ProductService;


import java.util.List;

@RestController
@RequestMapping("/products")
@Tag(name = "Product Management System", description = "Operations pertaining to products in Online Store")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("/add")
    @Operation(summary = "Add a new product")
    public ResponseEntity<Product> saveProduct(@RequestBody ProductDTO productDTO) {
        Product product = new Product();
        product.setCode(productDTO.getCode());
        product.setName(productDTO.getName());
        product.setProductType(productDTO.getProductType());
        product.setPrice(productDTO.getPrice());
        product.setInDate(productDTO.getInDate());
        product.setImage(productDTO.getImage());
        return ResponseEntity.ok(productService.saveProduct(product));
    }

    @GetMapping("/")
    @Operation(summary = "Get all products")
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{code}")
    @Operation(summary = "Get product details by code")
    public ResponseEntity<Product> getProductByCode(@PathVariable String code) {
        return ResponseEntity.ok(productService.findByCode(code));
    }
}
