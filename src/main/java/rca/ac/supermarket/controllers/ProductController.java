package rca.ac.supermarket.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import rca.ac.supermarket.DTO.Response;
import rca.ac.supermarket.DTO.ProductDTO;
import rca.ac.supermarket.services.ProductService;
import rca.ac.supermarket.utils.ExceptionHandlerUtil;
import rca.ac.supermarket.DTO.Response;
import rca.ac.supermarket.enums.ResponseType;

@RestController
@RequestMapping("/products")
@Tag(name = "Product Management System", description = "Operations pertaining to products in Online Store")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Admin: Add a new Product")
    public ResponseEntity<Response> addProduct(@RequestBody ProductDTO productDTO) {
        try {
            return ResponseEntity.status(201).body(new Response().setResponseType(ResponseType.SUCCESS).setPayload(productService.addProduct(productDTO)));
        } catch (Exception e) {
            return ExceptionHandlerUtil.handleException(e);
        }
    }

    @GetMapping("/")
    @Operation(summary = "Get all Products")
    public ResponseEntity<Response> getAllProducts() {
        try {
            return ResponseEntity.status(200).body(new Response().setResponseType(ResponseType.SUCCESS).setPayload(productService.getAllProducts()));
        } catch (Exception e) {
            return ExceptionHandlerUtil.handleException(e);
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a Product by ID")
    public ResponseEntity<Response> getProductById(@PathVariable Long id) {
        try {
            return ResponseEntity.status(200).body(new Response().setResponseType(ResponseType.SUCCESS).setPayload(productService.getProductById(id)));
        } catch (Exception e) {
            return ExceptionHandlerUtil.handleException(e);
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Admin: Update a Product by ID")
    public ResponseEntity<Response> updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDetails) {
        try {
            return ResponseEntity.status(200).body(new Response().setResponseType(ResponseType.SUCCESS).setPayload(productService.updateProduct(id, productDetails)));
        } catch (Exception e) {
            return ExceptionHandlerUtil.handleException(e);
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Admin: Delete a Product by ID")
    public ResponseEntity<Response> deleteProduct(@PathVariable Long id) {
        try {
            productService.deleteProduct(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ExceptionHandlerUtil.handleException(e);
        }
    }
}
