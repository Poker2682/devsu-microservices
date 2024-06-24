package com.devsu.microservice.account.api.service;

import com.devsu.microservice.account.api.dto.ReportItemDTO;

import java.util.Date;
import java.util.List;

public interface ReportService {

    List<ReportItemDTO> getReportByClientIdAndDateRange(Long clientId, Date startDate, Date endDate);
}
