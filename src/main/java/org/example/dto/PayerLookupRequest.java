package org.example.dto;

import lombok.Data;

@Data
public class PayerLookupRequest{
    private String orderId;
    private String processPaymentId;
    private String paymentId;
    private String paymentType;
    private String payerAccountId;
    private String payeeAccountId;
    private String currencyCode;
    private double amount;
    private String remittanceDetails;
    private String channel;
}
