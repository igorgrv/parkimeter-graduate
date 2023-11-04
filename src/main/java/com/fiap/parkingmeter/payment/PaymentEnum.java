package com.fiap.parkingmeter.payment;

public enum PaymentEnum {
    PIX("PIX"), CREDIT("CREDIT"), DEBIT("DEBIT");

    private String paymentType;

    PaymentEnum(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getPaymentType() {
        return paymentType;
    }
}
