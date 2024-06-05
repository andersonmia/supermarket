package rca.ac.supermarket.controllers;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rca.ac.supermarket.DTO.PurchasedDTO;
import rca.ac.supermarket.models.Purchased;
import rca.ac.supermarket.services.PurchasedService;


import java.util.List;

@RestController
@RequestMapping("/purchased")
@Tag(name = "Purchased Management System", description = "Operations pertaining to purchased products in Online Store")
public class PurchasedController {
    @Autowired
    private PurchasedService purchasedService;

    @PostMapping("/add")
    @Operation(summary = "Add purchased product")
    public ResponseEntity<Purchased> savePurchased(@RequestBody PurchasedDTO purchasedDTO) {
        Purchased purchased = new Purchased();
        purchased.setProductCode(purchasedDTO.getProductCode());
        purchased.setQuantity(purchasedDTO.getQuantity());
        purchased.setTotal(purchasedDTO.getTotal());
        purchased.setDate(purchasedDTO.getDate());
        return ResponseEntity.ok(purchasedService.savePurchased(purchased));
    }

    @GetMapping("/")
    @Operation(summary = "Get all purchased products")
    public ResponseEntity<List<Purchased>> getAllPurchased() {
        return ResponseEntity.ok(purchasedService.getAllPurchased());
    }
}
