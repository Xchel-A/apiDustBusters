

package com.dustbuster.dustbusterApi.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;

@RestController
@RequestMapping("/api/openpay")
public class OpenpayController {

    private final RestTemplate restTemplate;

    private final String merchantId = "mqmsrg8kqp8emsh76dgj";
    private final String authorizationHeader = "Basic c2tfOTE0ZGUzODljM2E3NDA1ZDkxNGViM2ExMGFiNzE3M2U6";

    @Autowired
    public OpenpayController(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @GetMapping("/cards/{userIdOpenpay}")
    public ResponseEntity<?> getCards(@PathVariable String userIdOpenpay) {
        String url = String.format("https://sandbox-api.openpay.mx/v1/%s/customers/%s/cards", merchantId, userIdOpenpay);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authorizationHeader);

        HttpEntity<?> requestEntity = new HttpEntity<>(headers);

        return restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
    }

    @PostMapping("/cards/{userIdOpenpay}")
    public ResponseEntity<?> addCard(@PathVariable String userIdOpenpay, @RequestBody TarjetaRequest tarjetaRequest) {
        String url = String.format("https://sandbox-api.openpay.mx/v1/%s/customers/%s/cards", merchantId, userIdOpenpay);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", authorizationHeader);

        TarjetaData tarjetaData = new TarjetaData();
        tarjetaData.setCard_number(tarjetaRequest.getCard_number());
        tarjetaData.setHolder_name(tarjetaRequest.getHolder_name());
        tarjetaData.setExpiration_year(tarjetaRequest.getExpiration_year());
        tarjetaData.setExpiration_month(tarjetaRequest.getExpiration_month());
        tarjetaData.setCvv2(tarjetaRequest.getCvv2());
        tarjetaData.setDevice_session_id(tarjetaRequest.getDevice_session_id());

        HttpEntity<TarjetaData> requestEntity = new HttpEntity<>(tarjetaData, headers);

        return restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
    }

    @DeleteMapping("/cards/{userIdOpenpay}/{cardId}")
    public ResponseEntity<?> deleteCard(@PathVariable String userIdOpenpay, @PathVariable String cardId) {
        String url = String.format("https://sandbox-api.openpay.mx/v1/%s/customers/%s/cards/%s", merchantId, userIdOpenpay, cardId);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authorizationHeader);

        HttpEntity<?> requestEntity = new HttpEntity<>(headers);

        restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, Void.class);

        return ResponseEntity.ok().build();
    }


    @PostMapping("/charges/{userIdOpenpay}")
    public ResponseEntity<?> createCharge(@PathVariable String userIdOpenpay, @RequestBody ChargeRequest chargeRequest) {
        String url = String.format("https://sandbox-api.openpay.mx/v1/%s/customers/%s/charges", merchantId, userIdOpenpay);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", authorizationHeader);

        HttpEntity<ChargeRequest> requestEntity = new HttpEntity<>(chargeRequest, headers);

        ResponseEntity<ChargeData> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, ChargeData.class);

        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }
}



