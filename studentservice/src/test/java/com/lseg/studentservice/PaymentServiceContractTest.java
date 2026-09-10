package com.lseg.studentservice;

import au.com.dius.pact.consumer.MockServer;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.core.model.V4Pact;
import au.com.dius.pact.core.model.annotations.Pact;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(PactConsumerTestExt.class)
@PactTestFor(providerName = "PaymentService")
class PaymentServiceContractTest {

    @Pact(consumer = "StudentService")
    public V4Pact createPact(PactDslWithProvider builder) {
        return builder
                .given("payment 1 exists")
                .uponReceiving("a request for payment with id 1")
                .path("/payments/1")
                .method("GET")
                .willRespondWith()
                .status(200)
                .body("{\"id\":1,\"currency\":\"GBP\",\"status\":\"PAID\",\"amount\":90.0}")
                .toPact(V4Pact.class);
    }

    @Test
    @PactTestFor(pactMethod = "createPact")
    void testGetPayment(MockServer mockServer) {
        RestTemplate restTemplate = new RestTemplate();
        String url = mockServer.getUrl() + "/payments/1";

        Map<String, Object> response = restTemplate.getForObject(url, Map.class);

        assertNotNull(response);
        assertEquals(1, response.get("id"));
        assertEquals("GBP", response.get("currency"));
        assertEquals("PAID", response.get("status"));

        System.out.println("✅ Pact test passed!");
        System.out.println("Response: " + response);
    }
}