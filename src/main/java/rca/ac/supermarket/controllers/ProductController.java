package rca.ac.supermarket.controllers;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rca.ac.supermarket.DTO.FindByCodeDTO;
import rca.ac.supermarket.DTO.FindByEmailDTO;
import rca.ac.supermarket.DTO.ProductDTO;
import rca.ac.supermarket.DTO.Response;
import rca.ac.supermarket.enums.ResponseType;
import rca.ac.supermarket.models.Product;
import rca.ac.supermarket.services.ProductService;
import rca.ac.supermarket.utils.ExceptionHandlerUtil;


import java.util.List;

@RestController
@RequestMapping("/products")
@Tag(name = "Product Management System", description = "Operations pertaining to products in Online Store")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("/add")
    @Operation(summary = "Add a new product")
    public ResponseEntity<Response> saveProduct(@RequestBody ProductDTO productDTO) {
        try {
            return ResponseEntity.status(201).body(new Response().setResponseType(ResponseType.SUCCESS).setPayload(productService.saveProduct(productDTO)));
        } catch (Exception e) {
            return ExceptionHandlerUtil.handleException(e);
        }
    }

    @GetMapping("/")
    @Operation(summary = "Get all products")
    public ResponseEntity<Response> getAllProducts() {
        try {
            List<Product> products = productService.getAllProducts();
            return ResponseEntity.ok(new Response().setResponseType(ResponseType.SUCCESS).setPayload(products));
        } catch (Exception e) {
            return ExceptionHandlerUtil.handleException(e);
        }
    }

    @GetMapping("/code")
    @Operation(summary = "Get product details by code")
    public ResponseEntity<Response> getProductByCode(@RequestBody FindByCodeDTO byCodeDTO) {
        try {
            return ResponseEntity.status(200).body(new Response().setResponseType(ResponseType.SUCCESS).setPayload(productService.findByCode(byCodeDTO.getCode())));
        } catch (Exception e) {
            return ExceptionHandlerUtil.handleException(e);
        }
    }

}
