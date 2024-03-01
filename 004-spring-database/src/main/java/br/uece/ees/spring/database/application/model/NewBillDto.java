package br.uece.ees.spring.database.application.model;

import br.uece.ees.spring.database.domain.BillEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class NewBillDto {

    private String title;
    private BigDecimal amountDue;
    private LocalDate dueDate;
    private LocalDateTime createdAt = LocalDateTime.now();

    public static NewBillDto from(BillEntity entity) {
        NewBillDto dto = new NewBillDto();
        dto.setTitle(entity.getTitle());
        dto.setAmountDue(entity.getAmountDue());
        dto.setDueDate(entity.getDueDate());
        dto.setCreatedAt(entity.getCreatedAt());
        return dto;
    }

    public BillEntity toEntity() {
        BillEntity entity = new BillEntity();
        entity.setTitle(title);
        entity.setAmountDue(amountDue);
        entity.setDueDate(dueDate);
        entity.setCreatedAt(createdAt);
        return entity;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getAmountDue() {
        return amountDue;
    }

    public void setAmountDue(BigDecimal amountDue) {
        this.amountDue = amountDue;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
