package rca.ac.supermarket.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rca.ac.supermarket.DTO.ReportDTO;
import rca.ac.supermarket.models.Purchased;
import rca.ac.supermarket.repositories.PurchasedRepository;
import rca.ac.supermarket.services.ReportService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReportServiceImpl implements ReportService {

    private final PurchasedRepository purchasedRepository;

    @Autowired
    public ReportServiceImpl(PurchasedRepository purchasedRepository) {
        this.purchasedRepository = purchasedRepository;
    }

    @Override
    public List<ReportDTO> generateReport() {
        List<Purchased> purchasedItems = purchasedRepository.findAll();
        return purchasedItems.stream().map(this::convertToReportDTO).collect(Collectors.toList());
    }

    private ReportDTO convertToReportDTO(Purchased purchased) {
        ReportDTO reportDTO = new ReportDTO();
        reportDTO.setDate(purchased.getDate());
        reportDTO.setProductCode(purchased.getProductCode());
        reportDTO.setQuantity(purchased.getQuantity());
        reportDTO.setTotalPrice(purchased.getTotal());
        return reportDTO;
    }
}
