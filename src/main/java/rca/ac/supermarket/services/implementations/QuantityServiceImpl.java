package rca.ac.supermarket.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rca.ac.supermarket.DTO.QuantityDTO;
import rca.ac.supermarket.models.Quantity;
import rca.ac.supermarket.repositories.QuantityRepository;
import rca.ac.supermarket.exceptions.ResourceNotFoundException;
import rca.ac.supermarket.services.QuantityService;

import java.util.List;

@Service
public class QuantityServiceImpl implements QuantityService {

    private final QuantityRepository quantityRepository;

    @Autowired
    public QuantityServiceImpl(QuantityRepository quantityRepository) {
        this.quantityRepository = quantityRepository;
    }

    @Override
    public Quantity addQuantity(QuantityDTO quantityDTO) {
        Quantity quantity = quantityDTO.toEntity();
        return quantityRepository.save(quantity);
    }

    @Override
    public List<Quantity> getAllQuantities() {
        return quantityRepository.findAll();
    }

    @Override
    public Quantity getQuantityById(Long id) throws ResourceNotFoundException {
        return quantityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Quantity", "id", String.valueOf(id)));
    }

    @Override
    public Quantity updateQuantity(Long id, QuantityDTO quantityDetails) throws ResourceNotFoundException {
        Quantity quantity = quantityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Quantity", "id", String.valueOf(id)));
        quantity.setProductCode(quantityDetails.getProductCode());
        quantity.setQuantity(quantityDetails.getQuantity());
        quantity.setOperation(quantityDetails.getOperation());
        quantity.setDate(quantityDetails.getDate());
        return quantityRepository.save(quantity);
    }

    @Override
    public void deleteQuantity(Long id) throws ResourceNotFoundException {
        Quantity quantity = quantityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Quantity", "id", String.valueOf(id)));
        quantityRepository.delete(quantity);
    }
}
