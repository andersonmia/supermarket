package rca.ac.supermarket.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rca.ac.supermarket.DTO.PurchasedDTO;
import rca.ac.supermarket.models.Purchased;
import rca.ac.supermarket.models.User;
import rca.ac.supermarket.repositories.PurchasedRepository;
import rca.ac.supermarket.services.PurchasedService;

import java.util.List;

@Service
public class PurchasedServiceImpl implements PurchasedService {

    private final PurchasedRepository purchasedRepository;

    @Autowired
    public PurchasedServiceImpl(PurchasedRepository purchasedRepository) {
        this.purchasedRepository = purchasedRepository;
    }

    @Override
    public Purchased savePurchased(PurchasedDTO purchasedDTO) {
        Purchased purchased = purchasedDTO.toEntity();
        return purchasedRepository.save(purchased);
    }

    @Override
    public List<Purchased> getAllPurchased() {
        return purchasedRepository.findAll();
    }

    @Override
    public List<Purchased> getPurchasedByUser(User user) {
        return purchasedRepository.findByUser(user);
    }
}
