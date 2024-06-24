package com.devsu.microservice.account.api.service;

import com.devsu.microservice.account.api.dto.ReportItemDTO;
import com.devsu.microservice.account.api.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private ReportRepository reportRepository;


    public List<ReportItemDTO> getReportByClientIdAndDateRange(Long clientId, Date startDate, Date endDate) {
        return reportRepository.findReportByClientIdAndDateRange(clientId, startDate, endDate);
    }
}
