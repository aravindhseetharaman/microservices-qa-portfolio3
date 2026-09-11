    package com.lseg.paymentservice;

    import au.com.dius.pact.provider.junit5.HttpTestTarget;
    import au.com.dius.pact.provider.junit5.PactVerificationContext;
    import au.com.dius.pact.provider.junit5.PactVerificationInvocationContextProvider;
    import au.com.dius.pact.provider.junitsupport.Provider;
    import au.com.dius.pact.provider.junitsupport.State;
    import au.com.dius.pact.provider.junitsupport.VerificationReports;
    import au.com.dius.pact.provider.junitsupport.loader.PactBroker;
    import org.junit.jupiter.api.BeforeEach;
    import org.junit.jupiter.api.TestTemplate;
    import org.junit.jupiter.api.extension.ExtendWith;

    @Provider("PaymentService")
    @PactBroker(url = "http://localhost:9292")
    @VerificationReports(value = {"console"}, reportDir = "target/pact-verification")
    class PaymentServiceProviderTest {


        @BeforeEach

        void before(PactVerificationContext context) {
            System.setProperty("pact.verifier.publishResults", "true");
            System.setProperty("pact.provider.version", "1.0.0");
            context.setTarget(new HttpTestTarget("localhost", 8585));
           // context.setTarget(new HttpTestTarget("host.docker.internal", 8585));
        }

        @State("payment 1 exists")
        void paymentOneExists() {
            System.out.println("Ensuring payment 1 exists...");
        }

        @TestTemplate
        @ExtendWith(PactVerificationInvocationContextProvider.class)
        void pactVerificationTestTemplate(PactVerificationContext context) {
            context.verifyInteraction();
        }
    }