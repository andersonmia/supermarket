package rca.ac.supermarket.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rca.ac.supermarket.models.Quantity;
import rca.ac.supermarket.repositories.QuantityRepository;


@Service
public class QuantityService {
    @Autowired
    private QuantityRepository quantityRepository;

    public Quantity saveQuantity(Quantity quantity) {
        return quantityRepository.save(quantity);
    }
}

