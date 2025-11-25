package com.lukaportfolio.urlshortener;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
public class Controller {
    @Autowired
    private UrlService urlService;

    @GetMapping("/")
    public String index() {
        return "This is default";
    }

    @GetMapping("/retrieve")
    public ShortUrlResponse shorten(@RequestParam String longUrl) {
        String code = urlService.encode(longUrl);
        String fullUrl = "https://url-shortener-0j28.onrender.com/r/" + code;
        return new ShortUrlResponse(fullUrl);
    }

    @GetMapping("/r/{code}")
    public void redirect(@PathVariable String code, HttpServletResponse response) throws IOException {
        try {
            String longUrl = urlService.decode(code); // Lookup happens in service
            response.sendRedirect(longUrl);            // Redirect if found
        } catch (ResponseStatusException e) {
            response.sendRedirect("/e404.html");      // Redirect to custom error page if not found
        }
    }

    @GetMapping("/e404")
    public String errorPage() {
        return "e404";
    }
}