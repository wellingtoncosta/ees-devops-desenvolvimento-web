package br.uece.ees.spring.database.application.model;

import br.uece.ees.spring.database.domain.BillEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class BillDto {

    private Long id;
    private String title;
    private BigDecimal amountDue;
    private LocalDate dueDate;
    private LocalDateTime createdAt = LocalDateTime.now();

    public static BillDto from(BillEntity entity) {
        BillDto dto = new BillDto();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setAmountDue(entity.getAmountDue());
        dto.setDueDate(entity.getDueDate());
        dto.setCreatedAt(entity.getCreatedAt());
        return dto;
    }

    public BillEntity toEntity() {
        BillEntity entity = new BillEntity();
        entity.setId(id);
        entity.setTitle(title);
        entity.setAmountDue(amountDue);
        entity.setDueDate(dueDate);
        entity.setCreatedAt(createdAt);
        return entity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
