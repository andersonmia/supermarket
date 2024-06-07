package rca.ac.supermarket.services;


import rca.ac.supermarket.DTO.ReportDTO;

import java.util.List;

public interface ReportService {
    List<ReportDTO> generateReport();
}

