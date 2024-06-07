package rca.ac.supermarket.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rca.ac.supermarket.DTO.Response;
import rca.ac.supermarket.enums.ResponseType;
import rca.ac.supermarket.services.ReportService;
import rca.ac.supermarket.utils.ExceptionHandlerUtil;

@RestController
@RequestMapping("/reports")
@Tag(name = "Report Management System", description = "Operations pertaining to reports in Online Store")
public class ReportController {

    private final ReportService reportService;

    @Autowired
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Admin: Generate a Purchase Report")
    public ResponseEntity<Response> generateReport() {
        try {
            return ResponseEntity.status(200).body(new Response().setResponseType(ResponseType.SUCCESS).setPayload(reportService.generateReport()));
        } catch (Exception e) {
            return ExceptionHandlerUtil.handleException(e);
        }
    }
}
