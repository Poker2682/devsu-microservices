package com.devsu.microservice.account.api.controller;

import com.devsu.microservice.account.api.dto.ReportItemDTO;
import com.devsu.microservice.account.api.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/reportes")
public class ReportController {

    @Autowired
    private ReportService reportService;


    @GetMapping
    public ResponseEntity<List<ReportItemDTO>> generateReport(
            @RequestParam("cliente") Long clientId,
            @RequestParam(value = "fechaInicio", required = false) String startDateStr,
            @RequestParam(value = "fechaFin", required = false) String endDateStr) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = null;
        Date endDate = null;

        try {
            if (startDateStr != null) {
                startDate = formatter.parse(startDateStr);
            }
            if (endDateStr != null) {
                endDate = formatter.parse(endDateStr);
            }
        } catch (ParseException e) {
            return ResponseEntity.badRequest().build();
        }

        List<ReportItemDTO> report = reportService.getReportByClientIdAndDateRange(clientId, startDate, endDate);
        return ResponseEntity.ok(report);
    }

}
