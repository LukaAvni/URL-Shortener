package com.lukaportfolio.urlshortener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Random;
import org.springframework.stereotype.Service;

@Service
public class UrlService {
    @Autowired
    private UrlRepository urlRepository;

    private String simpleHash(String longUrl) { //Hash to find in database
        int sum = 0;
        for (int i = 0; i < longUrl.length(); i++) {
            sum += longUrl.charAt(i) * (i + 1);
        }
        //Convert to 5-digit code (padded if needed)
        return String.format("%05d", Math.abs(sum % 100_000));
    }

    public String encode(String longUrl) {
        // Check if URL already exists
        return urlRepository.findByLongUrl(longUrl)
                .map(Url::getShortCode)  // If exists, return code
                .orElseGet(() -> {
                    // Create new short code
                    String code = simpleHash(longUrl);
                    Url newUrl = new Url(code, longUrl);
                    urlRepository.save(newUrl); // Save to DB
                    return code;
                });
    }

    public String decode(String code) {
        return urlRepository.findByShortCode(code)
                .map(Url::getLongUrl)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Error 404: Not Found"
                ));
    }
}