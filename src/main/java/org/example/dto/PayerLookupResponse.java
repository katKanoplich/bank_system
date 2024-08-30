package org.example.dto;

import lombok.Data;

@Data
public class PayerLookupResponse {
    private String orderId;
    private String paymentId;
    private String processId;
    private String processPaymentId;
    private String paymentType;
    private long startedAt;
    private long completedAt;
    private boolean responseSent;
    private boolean requestReceived;
    private String payerAccountId;
    private String payeeAccountId;
    private String currencyCode;
    private double amount;
    private String remittanceDetails;
    private String channel;
    private String payerAccountName;
    private String payerLookupStatus;
    private int payerLookupRetryNumber;
    private String transactionStatus;
    private String errorInformation;
}
