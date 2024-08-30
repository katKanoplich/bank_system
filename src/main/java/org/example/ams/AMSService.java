package org.example.ams;

import org.example.dto.PayerLookupRequest;
import org.example.dto.PayerLookupResponse;

public class AMSService {
    public PayerLookupResponse mockLookup(PayerLookupRequest request) {
        PayerLookupResponse response = new PayerLookupResponse();
        response.setOrderId(request.getOrderId());
        response.setPayerAccountId(request.getPayerAccountId());

        // Имитация логики
        if ("validAccount".equals(request.getPayerAccountId())) {
            response.setPayerAccountName("John Doe");
            response.setPayerLookupStatus("found");
            response.setTransactionStatus("success");
        } else {
            response.setPayerLookupStatus("not found");
            response.setTransactionStatus("failure");
        }

        return response;
    }
}
