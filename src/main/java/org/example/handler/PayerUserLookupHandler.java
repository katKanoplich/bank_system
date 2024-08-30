package org.example.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.client.api.worker.JobWorker;
import lombok.extern.slf4j.Slf4j;
import org.example.ams.AMSService;
import org.example.dto.PayerLookupRequest;
import org.example.dto.PayerLookupResponse;
import org.example.mapper.PayerLookupMapper;

@Slf4j
public class PayerUserLookupHandler {
    public static void main(String[] args) {
        ZeebeClient client = ZeebeClient.newClientBuilder()
                .usePlaintext()
                .gatewayAddress("localhost:26500")
                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        AMSService mockAMSService = new AMSService();

        JobWorker worker = client.newWorker()
                .jobType("intra-bank-payer-user-lookup")
                .handler((jobClient, job) -> {
                    try {
                        // Десериализация входных данных
                        PayerLookupRequest request = objectMapper.readValue(job.getVariables(), PayerLookupRequest.class);
                        log.info("Received request: {}", request);

                        // Использование заглушки для отправки запроса к AMS
                        PayerLookupResponse response = mockAMSService.mockLookup(request);
                        log.info("Response from AMS: {}", response);

                        // Обработка ответа
                        processResponse(jobClient, job, response);
                    } catch (Exception e) {
                        log.error("Error processing job: {}, exception: {}", job, e);
                        jobClient.newFailCommand(job.getKey())
                                .retries(0)
                                .send()
                                .join();
                    }
                })
                .open();

        log.info("PayerUserLookupHandler is ready to process jobs.");
    }

    private static void processResponse(JobClient jobClient, ActivatedJob job, PayerLookupResponse response) {
        long currentTime = System.currentTimeMillis();
        response.setStartedAt(currentTime);
        response.setCompletedAt(currentTime);
        response.setResponseSent(true);
        response.setRequestReceived(true);

        // Установка выходных переменных с использованием маппера
        jobClient.newCompleteCommand(job.getKey())
                .variables(PayerLookupMapper.toOutputVariables(response))
                .send()
                .join();
        log.info("Job {} completed with response: {}", job.getKey(), response);
    }
}