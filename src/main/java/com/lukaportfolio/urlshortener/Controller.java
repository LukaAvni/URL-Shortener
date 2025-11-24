package com.lukaportfolio.urlshortener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class Controller {
    @Autowired
    private UrlService urlService;

    @GetMapping("/")
    public String index() {
        return "This is default";
    }

    @GetMapping("/retrieve")
    public String shorten(@RequestParam String longUrl) {
        return urlService.encode(longUrl);
    }

}