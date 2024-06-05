package rca.ac.supermarket.controllers;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rca.ac.supermarket.DTO.QuantityDTO;
import rca.ac.supermarket.models.Quantity;
import rca.ac.supermarket.services.QuantityService;


@RestController
@RequestMapping("/quantities")
@Tag(name = "Quantity Management System", description = "Operations pertaining to product quantities in Online Store")
public class QuantityController {
    @Autowired
    private QuantityService quantityService;

    @PostMapping("/add")
    @Operation(summary = "Add quantity to a product")
    public ResponseEntity<Quantity> saveQuantity(@RequestBody QuantityDTO quantityDTO) {
        Quantity quantity = new Quantity();
        quantity.setProductCode(quantityDTO.getProductCode());
        quantity.setQuantity(quantityDTO.getQuantity());
        quantity.setOperation(quantityDTO.getOperation());
        quantity.setDate(quantityDTO.getDate());
        return ResponseEntity.ok(quantityService.saveQuantity(quantity));
    }
}
