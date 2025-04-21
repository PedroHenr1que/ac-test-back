package com.api.test_accenture.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class VerifyCEPService {

    private final RestTemplate restTemplate;

    public VerifyCEPService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void verifyCEP(String cep) {
        Map<String, String> data = findCEPData(cep);
        if (data == null || data.isEmpty()) {
            throw new RuntimeException("invalid CEP or not found.");
        }
    }

    public String getState(String cep) {
        Map<String, String> data = findCEPData(cep);
        assert data != null;
        return data.get("uf");
    }

    private Map<String, String> findCEPData(String cep) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Accept", "application/json");

            HttpEntity<Void> request = new HttpEntity<>(headers);

            ResponseEntity<Map> response = restTemplate.exchange(
                    String.format("http://viacep.com.br/ws/%s/json/",cep),
                    HttpMethod.GET,
                    request,
                    Map.class
            );

            if (!response.getStatusCode().is2xxSuccessful() || response.getBody() == null) {
                return null;
            }

            return response.getBody();

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
