package rca.ac.supermarket.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rca.ac.supermarket.models.Purchased;
import rca.ac.supermarket.repositories.PurchasedRepository;

import java.util.List;

@Service
public class PurchasedService {
    @Autowired
    private PurchasedRepository purchasedRepository;

    public Purchased savePurchased(Purchased purchased) {
        return purchasedRepository.save(purchased);
    }

    public List<Purchased> getAllPurchased() {
        return purchasedRepository.findAll();
    }
}

