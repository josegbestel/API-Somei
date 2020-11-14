package com.somei.apisomei.service;

import com.somei.apisomei.config.ApplicationConfig;
import com.somei.apisomei.exception.DomainException;
import com.somei.apisomei.model.Profissional;
import com.somei.apisomei.model.Servico;
import com.somei.apisomei.service.juno.request.ChargesRequest;
import com.somei.apisomei.service.juno.request.DigitalAccountRequest;
import com.somei.apisomei.service.juno.request.PaymentRequest;
import com.somei.apisomei.service.juno.request.TransferRequest;
import com.somei.apisomei.service.juno.response.*;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.Base64;

public class JunoService {

    private static final String API_VERSION = "2";
    private String ACCESS_TOKEN = "";
    private static final String ENDPOINT_API = ApplicationConfig.ENDPOINT_GATEWAY_PAGAMENTO;
    private static final String RESOURCE_TOKEN = ApplicationConfig.TOKEN_JUNO_PRIVATE;

    public JunoService() {
    }

    private HttpHeaders createHeaders(){
        return new HttpHeaders(){{
            add( "Authorization", "Bearer " + ACCESS_TOKEN );
            add("Content-Type", "application/json");
            add("Accept", "application/json");
            add("Keep-Alive", "true");
        }};
    }

    public TransferResponse efetuarTransferencia(Profissional profissional, float valor){

        //String request = ENDPOINT_API + "/api-integration/transfers";
        String request = "https://run.mocky.io/v3/a6bd907e-5a69-489c-be3a-d09908d11324";
        RestTemplate template = new RestTemplate();
        HttpMethod method = HttpMethod.POST;
        ResponseEntity<TransferResponse> response;
        HttpHeaders headers = this.createHeaders();
        headers.add("X-Api-Version", API_VERSION);
        headers.add("X-Resource-Token", profissional.getResourceTokenJuno());

        TransferRequest transferRequest = new TransferRequest(valor);

        try {
            HttpEntity<TransferRequest> entity = new HttpEntity<TransferRequest>(transferRequest, headers);
            response = template.exchange(request, method, entity, TransferResponse.class);

            return response.getBody();
        }catch (Exception e){
            throw new DomainException("erro ao realizar transferencia: " + e.getMessage());
        }
    }

    public BalanceResponse consultarSaldo(Profissional profissional){
        String request = ENDPOINT_API + "/api-integration/digital-accounts/" + profissional.getIdAccountJuno();
        RestTemplate template = new RestTemplate();
        HttpMethod method = HttpMethod.GET;
        ResponseEntity<BalanceResponse> response;
        HttpHeaders headers = this.createHeaders();
        headers.add("X-Api-Version", API_VERSION);
        headers.add("X-Resource-Token", profissional.getResourceTokenJuno());

        try {
            HttpEntity<Object> entity = new HttpEntity(headers);
            response = template.exchange(request, method, entity, BalanceResponse.class);

            return response.getBody();
        }catch (Exception e){
            throw new DomainException("erro ao consultar saldo: " + e.getMessage());
        }
    }

    public PaymentsResponse efetuarPagamento(Servico servico, String cartaoHash){
        String request = ENDPOINT_API + "/api-integration/payments";
        RestTemplate template = new RestTemplate();
        HttpMethod method = HttpMethod.POST;
        ResponseEntity<PaymentsResponse> response;
        HttpHeaders headers = this.createHeaders();
        headers.add("X-Api-Version", API_VERSION);
        headers.add("X-Resource-Token", RESOURCE_TOKEN);

        PaymentRequest paymentRequest = new PaymentRequest(servico, cartaoHash);

        try {
            HttpEntity<PaymentRequest> entity = new HttpEntity<PaymentRequest>(paymentRequest, headers);
            response = template.exchange(request, method, entity, PaymentsResponse.class);
            System.out.println("Transaction ID: " + response.getBody().getTransactionId());
            System.out.println("Payment ID: " + response.getBody().getPayments().get(0).getId());
            return response.getBody();
        }catch (Exception e){
            throw new DomainException("erro ao efetuar pagamento: " + e.getMessage());
        }

    }

    public ChargeResponse gerarCobranca(Servico servico){

        String request = ENDPOINT_API + "/api-integration/charges";
        RestTemplate template = new RestTemplate();
        HttpMethod method = HttpMethod.POST;
        ResponseEntity<ChargesResponse> response;
        HttpHeaders headers = this.createHeaders();
        headers.add("X-Api-Version", API_VERSION);
        headers.add("X-Resource-Token", RESOURCE_TOKEN);

        ChargesRequest chargesRequest = new ChargesRequest(servico);

        try {
            HttpEntity<ChargesRequest> entity = new HttpEntity<>(chargesRequest, headers);
            response = template.exchange(request, method, entity, ChargesResponse.class);
            System.out.println("Charge ID: " + response.getBody()
                    .getEmbedded()
                    .getCharges()
                    .get(0)
                    .getId());
            return response.getBody().getEmbedded().getCharges().get(0);
        }catch (Exception e){
            throw new DomainException("erro ao gerar cobranca: " + e.getMessage());
        }

    }

    public DigitalAccountResponse criarContaDigital(Profissional profissional){

        String request = ENDPOINT_API + "/api-integration/digital-accounts";
        RestTemplate template = new RestTemplate();
        HttpMethod method = HttpMethod.POST;
        ResponseEntity<DigitalAccountResponse> response;
        HttpHeaders headers = this.createHeaders();
        headers.add("X-Api-Version", API_VERSION);
        headers.add("X-Resource-Token", RESOURCE_TOKEN);

        DigitalAccountRequest digitalAccountRequest = new DigitalAccountRequest(profissional);

        try {
            HttpEntity<DigitalAccountRequest> entity = new HttpEntity<>(digitalAccountRequest, headers);
            response = template.exchange(request, method, entity, DigitalAccountResponse.class);
            System.out.println("DigitalAccount ID: " + response.getBody().getId());
            System.out.println("DigitalAccount Token: " + response.getBody().getResourceToken());

            return response.getBody();
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw new DomainException("Erro ao criar a conta: " + e.getMessage());
        }

    }

    public void gerarTokenAcesso(){

        String request = ENDPOINT_API + "/authorization-server/oauth/token";
        RestTemplate template = new RestTemplate();
        HttpMethod method = HttpMethod.POST;
        ResponseEntity<TokenResponse> response;

        String auth = "6d4tkcld1YrihK7H:Q>eXJ8N#CkT-Tw)Ib17+PxHQv%Ud0n+O";
        byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes(Charset.defaultCharset()));
        String authHeader = "Basic " + new String(encodedAuth);

        HttpHeaders headers = new HttpHeaders(){{
            add( "Authorization", authHeader );
            add("Content-Type", "multipart/form-data");
            add("Accept", "application/json");
            add("Keep-Alive", "true");
        }};

        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
        map.add("grant_type", "client_credentials");

        try {
            HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<MultiValueMap<String, String>>(map, headers);
            response = template.exchange(request, method, entity, TokenResponse.class);
            this.ACCESS_TOKEN = response.getBody().getAccessToken();
            System.out.println("Token acesso: " + this.ACCESS_TOKEN);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
//        this.ACCESS_TOKEN = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJqb3NlZ2Jlc3RlbEBnbWFpbC5jb20iLCJzY29wZSI6WyJhbGwiXSwiZXhwIjoxNjA1MDU3MTQ5LCJqdGkiOiJmMzU5MTQxYy1kYjI0LTQzMWItYmFkNi0zMzk2MDk2YjlmODEiLCJjbGllbnRfaWQiOiI2ZDR0a2NsZDFZcmloSzdIIn0.nZ_ub30MueupgLGzvvuybEUzBH5cGp7gLppj0O38eAld6q_Fhuu_qE4mklmgOa695ma5sVsAEpI7rOyxOlva4kX0ZS6BPzWV7QhV9DjSywEQlh0oclmn_Ud99tQ_UpmBUfEAJSlEspEAcddsIYtOrElg52wuRFch5gDQJtSawzu4eGk20l2YhVpnokrBJukvOyC6V_XbSwtxHceUnSQUG1bB3e8QpQ6P89QrV03WZYYklOnuZO-G_wY3TxPCzTa3-ztwGq5r4AzOdZtgXNYPMoO2EvTc12WwDT5Cq-PhYcaR2cP_KtogjvBANf6Iw-CsOMlMSfGKvsKD3SjDJd9aPA";

        System.out.println(this.ACCESS_TOKEN);
    }


}
