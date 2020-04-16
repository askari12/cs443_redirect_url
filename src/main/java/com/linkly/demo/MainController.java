package com.linkly.demo;

import com.linkly.demo.primary.repository.URLRepository;
import com.linkly.demo.primary.UrlEntity;
import com.linkly.demo.secondary.UrlClicksEntity;
import com.linkly.demo.secondary.UrlClicksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class MainController {

    @Autowired
    private URLRepository urlRepository;
    @Autowired
    private UrlClicksRepository urlClicksRepository;

    // Mapped to route all alphaNumerics of size 10
    @GetMapping(value = "/{path:[a-zA-Z0-9]{10}}")
    public String getLongURL(@PathVariable String path , HttpServletResponse httpServletResponse) {

        // the path value is the short URL used
        // This will be used to fetch from repository
        UrlEntity getEntity = getLongURLHelper(path);
        if ( getEntity != null)  {
            updateAnalytics(path);
            System.out.println(getEntity.getLongURL());

            httpServletResponse.setHeader("Location", "" + getEntity.getLongURL());
            httpServletResponse.setStatus(302);

            return "";
        } else {
            System.out.println("Does not exist");
        }

        httpServletResponse.setStatus(404);
        return "404" ;
    }



    // Get the Long URL and return
    public UrlEntity getLongURLHelper(String path) {
        return urlRepository.findById(path).orElse(null);
    }

    // Update the Analytics Database by 1
    public void updateAnalytics(String path) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        String timeStamp = formatter.format(date);
        UrlClicksEntity obj = new UrlClicksEntity(path , timeStamp);
        urlClicksRepository.save(obj);
    }


}
