package com.linkly.demo.Controller;


import com.linkly.demo.Collection.UrlClicksEntity;
import com.linkly.demo.Collection.UrlMapping;
import com.linkly.demo.Repository.UrlClicksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Date;

@CrossOrigin
@RestController
public class MainController {

    @Autowired
    private UrlClicksRepository urlClicksRepository;

    @Value("${app.getLongUrlService}")
    private String getLongUrlUri;

    // Mapped to route all alphaNumerics of size 10
    @CrossOrigin
    @GetMapping(value = "/{path:[a-zA-Z0-9]{10}}")
    public ResponseEntity<Object> getLongURL(@PathVariable String path , HttpServletRequest httpServletRequest) {

        // create an instance of RestTemplate
        RestTemplate restTemplate = new RestTemplate();
        UrlMapping result;

        // create headers
        HttpHeaders headers = new HttpHeaders();

        // set `Content-Type` and `Accept` headers
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        // example of custom header
        headers.set("Authorization", "Basic bGlua2x5YWRtaW46S3VLLCFxM2EtOVhxKEFCXw==");

        // build the request
        HttpEntity request = new HttpEntity(headers);

        try {
            // make an HTTP GET request with headers
            ResponseEntity<UrlMapping> response = restTemplate.exchange(
                    getLongUrlUri + "/url/short/get/" + path,
                    HttpMethod.GET,
                    request,
                    UrlMapping.class
            );
            result = response.getBody();
            System.out.println(response.getBody().getLongUrl());

        }
        catch (HttpClientErrorException e) {
            System.out.println(e.getMessage());
            throw new ResponseStatusException(
                    e.getStatusCode(), "A problem occured With our Server"
            );
        }

        catch (Exception e) {
            System.out.println("Error Occured");
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "entity not found"
            );
        }

        updateAnalytics(path, result.getTerminated_at() , httpServletRequest.getRemoteAddr());
        return ResponseEntity.status(302).header("Location" , result.getLongUrl()).build();
    }


    // Update the Analytics Database by 1
    public void updateAnalytics(String path, LocalDateTime terminate_at , String ipAddress) {
        LocalDateTime timeStamp = LocalDateTime.now();
        UrlClicksEntity obj = new UrlClicksEntity(path , timeStamp, terminate_at , ipAddress);
        urlClicksRepository.save(obj);
    }



}
