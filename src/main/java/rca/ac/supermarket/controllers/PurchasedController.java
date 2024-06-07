package rca.ac.supermarket.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rca.ac.supermarket.models.Purchased;
import rca.ac.supermarket.models.User;
import rca.ac.supermarket.services.PurchasedService;
import rca.ac.supermarket.services.UserService;
import rca.ac.supermarket.utils.ExceptionHandlerUtil;
import rca.ac.supermarket.DTO.Response;
import rca.ac.supermarket.enums.ResponseType;

import java.util.List;

@RestController
@RequestMapping("/purchased")
@Tag(name = "Purchased Management System", description = "Operations pertaining to purchased items in Online Store")
public class PurchasedController {

    private final PurchasedService purchasedService;
    private final UserService userService;

    @Autowired
    public PurchasedController(PurchasedService purchasedService, UserService userService) {
        this.purchasedService = purchasedService;
        this.userService = userService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Get all Purchased Items")
    public ResponseEntity<Response> getAllPurchased() {
        try {
            List<Purchased> purchasedItems = purchasedService.getAllPurchased();
            return ResponseEntity.status(200).body(new Response().setResponseType(ResponseType.SUCCESS).setPayload(purchasedItems));
        } catch (Exception e) {
            return ExceptionHandlerUtil.handleException(e);
        }
    }

    @GetMapping("/my-purchases")
    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Get all Purchased Items for the logged-in User")
    public ResponseEntity<Response> getUserPurchased() {
        try {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User user = userService.findByEmail(userDetails.getUsername());
            List<Purchased> purchasedItems = purchasedService.getPurchasedByUser(user);
            return ResponseEntity.status(200).body(new Response().setResponseType(ResponseType.SUCCESS).setPayload(purchasedItems));
        } catch (Exception e) {
            return ExceptionHandlerUtil.handleException(e);
        }
    }
}
