package br.uece.eesdevops.introducaospringboot;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

import static java.util.Objects.isNull;

public class BankSlip {

    private Long id;
    private LocalDate dueDate;
    private LocalDate paymentDate;
    private BigDecimal totalInCents;
    private String customer;
    private Status status;

    public enum Status { PENDING, PAID, CANCELED }

    private static final int DECIMAL_PRECISION = 2;

    private static int LIMIT_OF_DAYS_TO_APPLY_HALF_INTEREST = 10;

    private final BigDecimal HALF_PERCENT_INTEREST_PER_DAY = new BigDecimal("0.05");

    private final BigDecimal ONE_PERCENT_INTEREST_PER_DAY = new BigDecimal("0.10");

    public BigDecimal calculateInterest() {
        LocalDate now = LocalDate.now();

        if(isNull(dueDate) || now.isBefore(dueDate)) {
            return new BigDecimal("0.00");
        }

        int days = Period.between(dueDate, now).getDays();

        if(days <= LIMIT_OF_DAYS_TO_APPLY_HALF_INTEREST) {
            return applyPercentInterestPerDay(days, HALF_PERCENT_INTEREST_PER_DAY);
        } else {
            return applyPercentInterestPerDay(days, ONE_PERCENT_INTEREST_PER_DAY);
        }
    }

    private BigDecimal applyPercentInterestPerDay(int days, BigDecimal interest) {
        return totalInCents
                .multiply(interest.multiply(new BigDecimal(days)))
                .setScale(DECIMAL_PRECISION, BigDecimal.ROUND_HALF_EVEN);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public BigDecimal getTotalInCents() {
        return totalInCents;
    }

    public void setTotalInCents(BigDecimal totalInCents) {
        this.totalInCents = totalInCents;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
