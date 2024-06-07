package rca.ac.supermarket.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import rca.ac.supermarket.DTO.CartDTO;
import rca.ac.supermarket.DTO.CartItemDTO;
import rca.ac.supermarket.services.implementations.CartServiceImpl;
import rca.ac.supermarket.utils.ExceptionHandlerUtil;
import rca.ac.supermarket.DTO.Response;
import rca.ac.supermarket.enums.ResponseType;

@RestController
@RequestMapping("/carts")
@Tag(name = "Cart Management System", description = "Operations pertaining to carts in Online Store")
public class CartController {

    @Autowired
    private CartServiceImpl cartService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Add a new Cart")
    public ResponseEntity<Response> addCart(@RequestBody CartDTO cartDTO) {
        try {
            return ResponseEntity.status(201).body(new Response().setResponseType(ResponseType.SUCCESS).setPayload(cartService.addCart(cartDTO)));
        } catch (Exception e) {
            return ExceptionHandlerUtil.handleException(e);
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a Cart by ID")
    public ResponseEntity<Response> getCartById(@PathVariable long id) {
        try {
            return ResponseEntity.status(200).body(new Response().setResponseType(ResponseType.SUCCESS).setPayload(cartService.getCartById(id)));
        } catch (Exception e) {
            return ExceptionHandlerUtil.handleException(e);
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Update a Cart by ID")
    public ResponseEntity<Response> updateCart(@PathVariable long id, @RequestBody CartDTO cartDTO) {
        try {
            return ResponseEntity.status(200).body(new Response().setResponseType(ResponseType.SUCCESS).setPayload(cartService.updateCart(id, cartDTO)));
        } catch (Exception e) {
            return ExceptionHandlerUtil.handleException(e);
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Delete a Cart by ID")
    public ResponseEntity<Response> deleteCart(@PathVariable long id) {
        try {
            cartService.deleteCart(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ExceptionHandlerUtil.handleException(e);
        }
    }

    @PostMapping("/{cartId}/items")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Add an Item to a Cart")
    public ResponseEntity<Response> addItemToCart(@PathVariable long cartId, @RequestBody CartItemDTO cartItemDTO) {
        try {
            return ResponseEntity.status(201).body(new Response().setResponseType(ResponseType.SUCCESS).setPayload(cartService.addItemToCart(cartId, cartItemDTO)));
        } catch (Exception e) {
            return ExceptionHandlerUtil.handleException(e);
        }
    }

    @DeleteMapping("/{cartId}/items/{itemId}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Remove an Item from a Cart")
    public ResponseEntity<Response> removeItemFromCart(@PathVariable long cartId, @PathVariable long itemId) {
        try {
            cartService.removeItemFromCart(cartId, itemId);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ExceptionHandlerUtil.handleException(e);
        }
    }
}
