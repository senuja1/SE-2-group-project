package org.example.controller;

import org.example.service.ReportService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReportController {

    private final ReportService service;

    public ReportController(ReportService service) {
        this.service = service;
    }

    @GetMapping("/api/report/pdf")
    public ResponseEntity<byte[]> downloadPdf() {

        byte[] pdf = service.generatePdfReport();

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=inventory-report.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }
}