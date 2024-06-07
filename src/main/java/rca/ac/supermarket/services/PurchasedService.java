package rca.ac.supermarket.services;

import rca.ac.supermarket.DTO.PurchasedDTO;
import rca.ac.supermarket.models.Purchased;

import java.util.List;

public interface PurchasedService {
    Purchased savePurchased(PurchasedDTO purchasedDTO);
    List<Purchased> getAllPurchased();
}
