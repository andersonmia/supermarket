package rca.ac.supermarket.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import rca.ac.supermarket.DTO.QuantityDTO;
import rca.ac.supermarket.services.QuantityService;
import rca.ac.supermarket.utils.ExceptionHandlerUtil;
import rca.ac.supermarket.DTO.Response;
import rca.ac.supermarket.enums.ResponseType;

@RestController
@RequestMapping("/quantities")
@Tag(name = "Quantity Management System", description = "Operations pertaining to quantities in Online Store")
public class QuantityController {

    private final QuantityService quantityService;

    public QuantityController(QuantityService quantityService) {
        this.quantityService = quantityService;
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Admin: Add a new Quantity")
    public ResponseEntity<Response> addQuantity(@RequestBody QuantityDTO quantityDTO) {
        try {
            return ResponseEntity.status(201).body(new Response().setResponseType(ResponseType.SUCCESS).setPayload(quantityService.addQuantity(quantityDTO)));
        } catch (Exception e) {
            return ExceptionHandlerUtil.handleException(e);
        }
    }

    @GetMapping("/")
    @Operation(summary = "Get all Quantities")
    public ResponseEntity<Response> getAllQuantities() {
        try {
            return ResponseEntity.status(200).body(new Response().setResponseType(ResponseType.SUCCESS).setPayload(quantityService.getAllQuantities()));
        } catch (Exception e) {
            return ExceptionHandlerUtil.handleException(e);
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a Quantity by ID")
    public ResponseEntity<Response> getQuantityById(@PathVariable Long id) {
        try {
            return ResponseEntity.status(200).body(new Response().setResponseType(ResponseType.SUCCESS).setPayload(quantityService.getQuantityById(id)));
        } catch (Exception e) {
            return ExceptionHandlerUtil.handleException(e);
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Admin: Update a Quantity by ID")
    public ResponseEntity<Response> updateQuantity(@PathVariable Long id, @RequestBody QuantityDTO quantityDetails) {
        try {
            return ResponseEntity.status(200).body(new Response().setResponseType(ResponseType.SUCCESS).setPayload(quantityService.updateQuantity(id, quantityDetails)));
        } catch (Exception e) {
            return ExceptionHandlerUtil.handleException(e);
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Admin: Delete a Quantity by ID")
    public ResponseEntity<Response> deleteQuantity(@PathVariable Long id) {
        try {
            quantityService.deleteQuantity(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ExceptionHandlerUtil.handleException(e);
        }
    }
}
