package io.github.Brettyuchoa.controller;
public class TransferRequest {
    private Long sourceAccountNumber;
    private Long targetAccountNumber;
    private Double amount;

    // Construtores, getters e setters

    public TransferRequest() {
    }

    public TransferRequest(Long sourceAccountNumber, Long targetAccountNumber, Double amount) {
        this.sourceAccountNumber = sourceAccountNumber;
        this.targetAccountNumber = targetAccountNumber;
        this.amount = amount;
    }

    public Long getSourceAccountNumber() {
        return sourceAccountNumber;
    }

    public void setSourceAccountNumber(Long sourceAccountNumber) {
        this.sourceAccountNumber = sourceAccountNumber;
    }

    public Long getTargetAccountNumber() {
        return targetAccountNumber;
    }

    public void setTargetAccountNumber(Long targetAccountNumber) {
        this.targetAccountNumber = targetAccountNumber;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
