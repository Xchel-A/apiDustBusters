

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
    // Clase TarjetaRequest creada directamente en el controlador
    public static class TarjetaRequest {
        private String card_number;
        private String holder_name;
        private String expiration_year;
        private String expiration_month;
        private String cvv2;
        private String device_session_id;

        public String getCard_number() {
            return card_number;
        }

        public void setCard_number(String card_number) {
            this.card_number = card_number;
        }

        public String getHolder_name() {
            return holder_name;
        }

        public void setHolder_name(String holder_name) {
            this.holder_name = holder_name;
        }

        public String getExpiration_year() {
            return expiration_year;
        }

        public void setExpiration_year(String expiration_year) {
            this.expiration_year = expiration_year;
        }

        public String getExpiration_month() {
            return expiration_month;
        }

        public void setExpiration_month(String expiration_month) {
            this.expiration_month = expiration_month;
        }

        public String getCvv2() {
            return cvv2;
        }

        public void setCvv2(String cvv2) {
            this.cvv2 = cvv2;
        }

        public String getDevice_session_id() {
            return device_session_id;
        }

        public void setDevice_session_id(String device_session_id) {
            this.device_session_id = device_session_id;
        }
    }
    // Clase TarjetaData creada directamente en el controlador
    public static class TarjetaData {
        private String card_number;
        private String holder_name;
        private String expiration_year;
        private String expiration_month;
        private String cvv2;
        private String device_session_id;

        public String getCard_number() {
            return card_number;
        }

        public void setCard_number(String card_number) {
            this.card_number = card_number;
        }

        public String getHolder_name() {
            return holder_name;
        }

        public void setHolder_name(String holder_name) {
            this.holder_name = holder_name;
        }

        public String getExpiration_year() {
            return expiration_year;
        }

        public void setExpiration_year(String expiration_year) {
            this.expiration_year = expiration_year;
        }

        public String getExpiration_month() {
            return expiration_month;
        }

        public void setExpiration_month(String expiration_month) {
            this.expiration_month = expiration_month;
        }

        public String getCvv2() {
            return cvv2;
        }

        public void setCvv2(String cvv2) {
            this.cvv2 = cvv2;
        }

        public String getDevice_session_id() {
            return device_session_id;
        }

        public void setDevice_session_id(String device_session_id) {
            this.device_session_id = device_session_id;
        }
    }
    // Clase ChargeRequest creada directamente en el controlador
    public static class ChargeRequest {
        private String source_id;
        private String method;
        private Double amount;
        private String currency;
        private String description;
        private String order_id;
        private String device_session_id;

        public String getSource_id() {
            return source_id;
        }

        public void setSource_id(String source_id) {
            this.source_id = source_id;
        }

        public String getMethod() {
            return method;
        }

        public void setMethod(String method) {
            this.method = method;
        }

        public Double getAmount() {
            return amount;
        }

        public void setAmount(Double amount) {
            this.amount = amount;
        }

        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }

        public String getDevice_session_id() {
            return device_session_id;
        }

        public void setDevice_session_id(String device_session_id) {
            this.device_session_id = device_session_id;
        }
    }




    // Clase ChargeData creada directamente en el controlador
    public static class ChargeData {
        private String id;
        private String authorization;
        private String operation_type;
        private String transaction_type;
        private String status;
        private Boolean conciliated;
        private String creation_date;
        private String operation_date;
        private String description;
        private String error_message;
        private String order_id;
        private Card card;
        private String customer_id;
        private Double amount;
        private Fee fee;
        private String currency;
        private String method;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAuthorization() {
            return authorization;
        }

        public void setAuthorization(String authorization) {
            this.authorization = authorization;
        }

        public String getOperation_type() {
            return operation_type;
        }

        public void setOperation_type(String operation_type) {
            this.operation_type = operation_type;
        }

        public String getTransaction_type() {
            return transaction_type;
        }

        public void setTransaction_type(String transaction_type) {
            this.transaction_type = transaction_type;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Boolean getConciliated() {
            return conciliated;
        }

        public void setConciliated(Boolean conciliated) {
            this.conciliated = conciliated;
        }

        public String getCreation_date() {
            return creation_date;
        }

        public void setCreation_date(String creation_date) {
            this.creation_date = creation_date;
        }

        public String getOperation_date() {
            return operation_date;
        }

        public void setOperation_date(String operation_date) {
            this.operation_date = operation_date;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getError_message() {
            return error_message;
        }

        public void setError_message(String error_message) {
            this.error_message = error_message;
        }

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }

        public Card getCard() {
            return card;
        }

        public void setCard(Card card) {
            this.card = card;
        }

        public String getCustomer_id() {
            return customer_id;
        }

        public void setCustomer_id(String customer_id) {
            this.customer_id = customer_id;
        }

        public Double getAmount() {
            return amount;
        }

        public void setAmount(Double amount) {
            this.amount = amount;
        }

        public Fee getFee() {
            return fee;
        }

        public void setFee(Fee fee) {
            this.fee = fee;
        }

        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }

        public String getMethod() {
            return method;
        }

        public void setMethod(String method) {
            this.method = method;
        }
    }

    public static class Card {
        private String id;
        private String type;
        private String brand;
        private String address;
        private String card_number;
        private String holder_name;
        private String expiration_year;
        private String expiration_month;
        private Boolean allows_charges;
        private Boolean allows_payouts;
        private String creation_date;
        private String bank_name;
        private String bank_code;
        private String customer_id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getBrand() {
            return brand;
        }

        public void setBrand(String brand) {
            this.brand = brand;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getCard_number() {
            return card_number;
        }

        public void setCard_number(String card_number) {
            this.card_number = card_number;
        }

        public String getHolder_name() {
            return holder_name;
        }

        public void setHolder_name(String holder_name) {
            this.holder_name = holder_name;
        }

        public String getExpiration_year() {
            return expiration_year;
        }

        public void setExpiration_year(String expiration_year) {
            this.expiration_year = expiration_year;
        }

        public String getExpiration_month() {
            return expiration_month;
        }

        public void setExpiration_month(String expiration_month) {
            this.expiration_month = expiration_month;
        }

        public Boolean getAllows_charges() {
            return allows_charges;
        }

        public void setAllows_charges(Boolean allows_charges) {
            this.allows_charges = allows_charges;
        }

        public Boolean getAllows_payouts() {
            return allows_payouts;
        }

        public void setAllows_payouts(Boolean allows_payouts) {
            this.allows_payouts = allows_payouts;
        }

        public String getCreation_date() {
            return creation_date;
        }

        public void setCreation_date(String creation_date) {
            this.creation_date = creation_date;
        }

        public String getBank_name() {
            return bank_name;
        }

        public void setBank_name(String bank_name) {
            this.bank_name = bank_name;
        }

        public String getBank_code() {
            return bank_code;
        }

        public void setBank_code(String bank_code) {
            this.bank_code = bank_code;
        }

        public String getCustomer_id() {
            return customer_id;
        }

        public void setCustomer_id(String customer_id) {
            this.customer_id = customer_id;
        }
    }

    public static class Fee {
        private Double amount;
        private Double tax;
        private String currency;

        public Double getAmount() {
            return amount;
        }

        public void setAmount(Double amount) {
            this.amount = amount;
        }

        public Double getTax() {
            return tax;
        }

        public void setTax(Double tax) {
            this.tax = tax;
        }

        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }

    }

}



