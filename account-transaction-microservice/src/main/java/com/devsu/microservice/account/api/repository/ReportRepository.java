package com.devsu.microservice.account.api.repository;

import com.devsu.microservice.account.api.dto.ReportItemDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class ReportRepository {

    @PersistenceContext
    EntityManager entityManager;

    public List<ReportItemDTO> findReportByClientIdAndDateRange(Long clientId, Date startDate, Date endDate) {
        String sql = "SELECT " +
                "t.date as date, " +
                "p.name as name, " +
                "a.account_number as accountNumber, " +
                "a.account_type as accountType, " +
                "(t.balance_after_transaction - t.amount) as initialBalance, " +
                "a.status as status, " +
                "t.amount as amount, " +
                "t.balance_after_transaction as balanceAfterTransaction " +
                "FROM transactions t " +
                "JOIN accounts a ON t.account_id = a.id " +
                "JOIN clients c ON a.client_id = c.client_id " +
                "JOIN person p ON c.client_id = p.id " +
                "WHERE c.client_id = :clientId " +
                "AND (:startDate IS NULL OR t.date >= DATE(:startDate))" +
                "AND (:endDate IS NULL OR t.date <= DATE(:endDate) + INTERVAL 1 DAY - INTERVAL 1 SECOND)";

        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("clientId", clientId);
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);

        List<Object[]> results = query.getResultList();

        return results.stream().map(this::convertToDTO).toList();
    }

    private ReportItemDTO convertToDTO(Object[] result) {
        ReportItemDTO dto = new ReportItemDTO();
        dto.setDate((Date) result[0]);
        dto.setClientName((String) result[1]);
        dto.setAccountNumber((String) result[2]);
        dto.setAccountType((String) result[3]);
        dto.setInitialBalance((Double) result[4]);
        dto.setStatus((Boolean) result[5]);
        dto.setAmount((Double) result[6]);
        dto.setBalanceAfterTransaction((Double) result[7]);
        return dto;
    }
}
