package org.example.mapper;
import org.example.dto.PayerLookupResponse;
import java.util.HashMap;
import java.util.Map;

public class PayerLookupMapper {

    public static Map<String, Object> toOutputVariables(PayerLookupResponse response) {
        Map<String, Object> output = new HashMap<>();
        output.put("orderId", response.getOrderId());
        output.put("processPaymentId", response.getProcessPaymentId());
        output.put("paymentId", response.getPaymentId());
        output.put("processId", "someProcessId"); // Это нужно будет установить
        output.put("paymentType", response.getPaymentType());
        output.put("startedAt", response.getStartedAt());
        output.put("completedAt", response.getCompletedAt());
        output.put("responseSent", response.isResponseSent());
        output.put("requestReceived", response.isRequestReceived());
        output.put("payerAccountId", response.getPayerAccountId());
        output.put("payeeAccountId", response.getPayeeAccountId());
        output.put("currencyCode", response.getCurrencyCode());
        output.put("amount", response.getAmount());
        output.put("remittanceDetails", response.getRemittanceDetails());
        output.put("channel", response.getChannel());
        output.put("payerAccountName", response.getPayerAccountName());
        output.put("payerLookupStatus", response.getPayerLookupStatus());
        output.put("payerLookupRetryNumber", response.getPayerLookupRetryNumber());
        output.put("transactionStatus", response.getTransactionStatus());
        output.put("errorInformation", response.getErrorInformation());

        return output;
    }
}