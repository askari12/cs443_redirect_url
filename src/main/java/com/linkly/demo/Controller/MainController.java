package com.linkly.demo.Controller;


import com.linkly.demo.Collection.UrlClicksEntity;
import com.linkly.demo.Collection.UrlMapping;
import com.linkly.demo.Repository.UrlClicksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
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
    public ResponseEntity<Object> getLongURL(@PathVariable String path /*, HttpServletResponse httpServletResponse*/) {

//        System.out.println(path);

        // Just For testing
        updateAnalytics(path , LocalDateTime.now().plusDays(10));

        RestTemplate restTemplate = new RestTemplate();
        UrlMapping result = null;
        try {
            result = restTemplate.getForObject(getLongUrlUri + "/url/short/get/" + path, UrlMapping.class);
            System.out.println(result.getLongUrl());

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

        updateAnalytics(path, result.getTerminated_at());
        return ResponseEntity.status(302).header("Location" , result.getLongUrl()).build();
    }


    // Update the Analytics Database by 1
    public void updateAnalytics(String path, LocalDateTime terminate_at) {
        LocalDateTime timeStamp = LocalDateTime.now();
        UrlClicksEntity obj = new UrlClicksEntity(path , timeStamp, terminate_at);
        urlClicksRepository.save(obj);
    }



}
