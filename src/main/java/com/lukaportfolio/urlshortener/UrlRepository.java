package com.lukaportfolio.urlshortener;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UrlRepository extends JpaRepository<Url, String> {
    Optional<Url> findByLongUrl(String longUrl);
    Optional<Url> findByShortCode(String longUrl);
}
