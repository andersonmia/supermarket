package rca.ac.supermarket.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rca.ac.supermarket.DTO.PurchasedDTO;
import rca.ac.supermarket.models.Purchased;
import rca.ac.supermarket.services.PurchasedService;
import rca.ac.supermarket.utils.ExceptionHandlerUtil;
import rca.ac.supermarket.DTO.Response;
import rca.ac.supermarket.enums.ResponseType;

import java.util.List;

@RestController
@RequestMapping("/purchased")
@Tag(name = "Purchased Management System", description = "Operations pertaining to purchased items in Online Store")
public class PurchasedController {

    @Autowired
    private PurchasedService purchasedService;

    @PostMapping
    @Operation(summary = "Add a new Purchased Item")
    public ResponseEntity<Response> addPurchased(@RequestBody PurchasedDTO purchasedDTO) {
        try {
            return ResponseEntity.status(201).body(new Response().setResponseType(ResponseType.SUCCESS).setPayload(purchasedService.savePurchased(purchasedDTO)));
        } catch (Exception e) {
            return ExceptionHandlerUtil.handleException(e);
        }
    }

    @GetMapping("/")
    @Operation(summary = "Get all Purchased Items")
    public ResponseEntity<Response> getAllPurchased() {
        try {
            return ResponseEntity.status(200).body(new Response().setResponseType(ResponseType.SUCCESS).setPayload(purchasedService.getAllPurchased()));
        } catch (Exception e) {
            return ExceptionHandlerUtil.handleException(e);
        }
    }
}
