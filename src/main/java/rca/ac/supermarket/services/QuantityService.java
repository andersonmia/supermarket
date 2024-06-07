package rca.ac.supermarket.services;

import rca.ac.supermarket.DTO.QuantityDTO;
import rca.ac.supermarket.models.Quantity;
import rca.ac.supermarket.exceptions.ResourceNotFoundException;

import java.util.List;

public interface QuantityService {
    Quantity addQuantity(QuantityDTO quantityDTO);
    List<Quantity> getAllQuantities();
    Quantity getQuantityById(Long id) throws ResourceNotFoundException;
    Quantity updateQuantity(Long id, QuantityDTO quantityDetails) throws ResourceNotFoundException;
    void deleteQuantity(Long id) throws ResourceNotFoundException;
}
