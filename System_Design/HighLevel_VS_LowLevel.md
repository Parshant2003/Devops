# URL Shortener System Design (HLD + LLD)

A clean, interview-ready breakdown of **High-Level Design (HLD)** and **Low-Level Design (LLD)** using a scalable **URL Shortener** as the reference system.

---

## 📌 Overview

* **HLD (High-Level Design)** focuses on system architecture, scalability, and component interactions.
* **LLD (Low-Level Design)** dives into implementation details like classes, APIs, and database schema.
* Structured for **quick revision + GitHub README usage**

---

## ⚖️ HLD vs LLD Comparison

| Aspect      | HLD (High Level)                      | LLD (Low Level)                   |
| ----------- | ------------------------------------- | --------------------------------- |
| Scope       | Entire system architecture            | Individual modules/classes        |
| Abstraction | High (macro level)                    | Low (code-level detail)           |
| Focus       | Components, scalability, interactions | Algorithms, data structures, APIs |
| Audience    | Architects, stakeholders              | Developers                        |
| Output      | Diagrams, system flows                | Class diagrams, pseudocode        |

---

## 🏗️ HLD (High-Level Design)

### 1. Functional Requirements

* **URL Shortening**

  * Input: Long URL
  * Output: Unique short URL (Base62 encoding)
  * Store mapping in DB

* **Redirection**

  * Short URL → Redirect (HTTP 301) to original URL

* **Click Tracking**

  * Increment click count on every redirect

---

### 2. Non-Functional Requirements

* **Scalability**

  * Handle ~1B URLs/month
  * Use sharding + replication

* **Low Latency**

  * Target <50ms redirect
  * Cache-first strategy

* **High Availability**

  * 99.99% uptime
  * Multi-AZ + load balancers

---

### 3. Core Components

* **Load Balancer**

  * Distributes traffic (Nginx / AWS ALB)

* **API Gateway**

  * Auth, rate limiting, routing

* **Microservices**

  * Shorten service
  * Redirect service
  * Analytics service

* **Cache**

  * Redis / Memcached
  * Stores hot URLs (clicks ≥ 300)

* **Database**

  * MySQL / PostgreSQL
  * Optional: DynamoDB

* **Queue**

  * Kafka / SQS for async tasks

* **CDN**

  * Static content delivery

* **Storage**

  * S3 for logs

---

### 4. System Flow

```text
[User] --> Load Balancer --> API Gateway --> Web Servers
           |                                      |
           v                                      v
        CDN (static)                        Cache (Redis) <--> DB
                                                    |
                                                    v
                                             Queue --> Analytics

Storage (S3)
```

---

## ⚙️ LLD (Low-Level Design)

### 1. Class Diagram

```text
+---------------+       +----------------+       +-----------------+
|   URLService  |<>---->|   URLMapper    |<>---->|   CacheService  |
+---------------+       +----------------+       +-----------------+
| - db          |       | - hashGen      |       | - redis         |
| - cache       |       +----------------+       +-----------------+
+---------------+       | + shorten()    |       | + get()         |
| + shorten()   |       | + redirect()   |       | + set()         |
| + redirect()  |       +----------------+       +-----------------+
| + trackClick()|       
+---------------+

                      +-----------------+
                      | HashGenerator   |
                      +-----------------+
                      | + generate(7)   |
                      +-----------------+
```

---

### 2. Database Schema

```sql
CREATE TABLE url_mappings (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    short_link VARCHAR(10) UNIQUE NOT NULL,
    actual_link TEXT NOT NULL,
    clicks BIGINT DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    user_id VARCHAR(50),
    expires_at TIMESTAMP
);

CREATE INDEX idx_short ON url_mappings(short_link);
CREATE INDEX idx_clicks ON url_mappings(clicks DESC);
```

* **Sharding**: Based on `short_link` hash

---

### 3. REST APIs

#### ➤ Create Short URL

```http
POST /api/shorten
```

```json
{
  "longUrl": "https://example.com/very/long",
  "userId": "rocky"
}
```

```json
{
  "shortUrl": "abc123"
}
```

---

#### ➤ Redirect

```http
GET /:shortCode
```

* Redirects to original URL
* Increments click count

---

#### ➤ Get Stats

```http
GET /api/stats/:shortCode
```

```json
{
  "clicks": 350,
  "created": "2026-04-24"
}
```

---

### 4. Core Object

```java
class URL {
    String longUrl;
    String shortCode;
    long clicks;
    Timestamp createdAt;
    String userId;
}
```

* One-to-one: short ↔ long URL
* One-to-many: User → URLs

---

### 5. Hashing Strategy

* Counter-based ID generation
* Convert to Base62
* Capacity: `62^7 ≈ 3.5 trillion`

---

### 6. Caching Strategy

* Cache hot URLs (clicks ≥ 300)
* Reduce DB load
* Lazy loading / write-through

---

### 7. Error Handling & Security

#### Errors

* 400 → Invalid URL
* 409 → Duplicate short code
* 410 → Expired link
* 429 → Rate limit exceeded

#### Security

* URL validation (regex)
* HTTPS only
* CAPTCHA (abuse prevention)
* Prepared statements (SQL injection protection)

---

### 8. Edge Cases

* **Collision** → Retry generation
* **High Traffic** → Queue click updates
* **Analytics** → Async processing

---

## ✅ Summary

* **HLD** → System architecture, scalability
* **LLD** → Code-level implementation

### Key Takeaways

* Cache-first approach for low latency
* Async processing for scalability
* Base62 for compact unique IDs
* Sharding for large-scale systems

---
