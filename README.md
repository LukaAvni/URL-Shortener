# URL Shortener REST API

## Overview
This is a simple REST API for shortening URLs. It converts a long URL into a short code and allows redirection to the original URL.

---

## API Usage

### Endpoint
`GET /retrieve`

### Parameter
- `longUrl` (string): The URL you want to shorten.

### Example Usage in Python

```python
import requests

resp = requests.get(
    "https://url-shortener-0j28.onrender.com/retrieve", 
    params={"longUrl": "https://youtube.com"}
)

print(resp)
```

This will return a JSON object with a link in the format:

```JSON
{
    "shortUrl": "https://url-shortener-0j28.onrender.com/r/33594"
}
```

Visiting this URL will redirect you to the original link, which in this case is chess.com.

## Additional Notes

Implemented in Java, using Spring Boot as a framework, and using a database provided by the free tier of Neon.

## Error Handling

In the case of a code that does not exist in the database, the user is redirected to a webpage explaining the error.