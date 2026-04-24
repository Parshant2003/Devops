


| Aspect      | HLD (High Level)                      | LLD (Low Level)                                 |
| ----------- | ------------------------------------- | ----------------------------------------------- |
| Scope       | Pura system architecture              | Individual modules/classes                      |
| Abstraction | High (macro level)                    | Low (micro level, code-ready) geeksforgeeks     |
| Focus       | Components, interactions, scalability | Algorithms, data structures, APIs               |
| Audience    | Architects/stakeholders               | Developers                                      |
| Output      | Diagrams, flowcharts                  | Class diagrams, pseudocode systemdesignhandbook |


HLD Components Overview
Ye common components hain scalable systems mein, jaise URL shortener ke liye:

Load Balancing: Traffic ko multiple servers pe distribute karta hai (Nginx/ALB on AWS).

Cache: Redis/Memcached se hot data (jaise popular short URLs) fast access (clicks >=300 wale).

CDN: Static content (images) global edges pe serve karta hai low latency ke liye.

Microservices: Independent services (shorten, redirect, analytics).

API Gateway: Single entry point, auth/rate limiting (Kong/AWS API Gateway).

DB: Relational (MySQL/PostgreSQL) ya NoSQL (DynamoDB) mappings store karne ke liye.

Queue: Async tasks jaise analytics (SQS/Kafka).

Storage (AWS S3): Logs, user data long-term store.

HLD of URL Shortener

1. Functional Requirements
Long URL → Short URL: User long URL bhejta hai, unique short code generate hokar mapping store karo (e.g., base62 encoding).

Redirect: Short URL pe GET request aaye to original long URL pe 301 redirect.

Clicks Tracker: Har redirect pe clicks++ karo analytics ke liye.

2. Non-Functional Requirements (DevOps Focus)
Scalable: 1B URLs/month handle (sharding, replication).

Low Latency: <50ms redirect (cache-first), high performance.

Available: 99.99% uptime (multi-AZ, load balancers).

3. High Level Components
text
[User] --> Load Balancer (Nginx/ALB) --> API Gateway --> Web Servers (Microservices)
          |                                           |
          v                                           v
       CDN (static assets)                    Cache (Redis) <--> DB (MySQL sharded)
                                                    |
                                                    v
                                               Queue (SQS) --> Analytics Service
Storage (S3 for logs)

Web Server: Shorten/redirect handle.

Hash Generator: Unique short code (base62 on counter).

DB: id | short_link | clicks | actual_link | created_at.

Caching: High-click URLs (300+) cache mein for no DB hits.

LLD of URL Shortener (Detailed Implementation)
Class Diagram (UML Style)
text
+---------------+       +----------------+       +-----------------+
|   URLService  |<>---->|   URLMapper    |<>---->|   CacheService  |
+---------------+       +----------------+       +-----------------+
| - db: DBConn  |       | - hashGen: Hash|       | - redis: Redis  |
| - cache: Cache|       +----------------+       +-----------------+
+---------------+       | + shorten() +  |       | + get()         |
| + shorten(url)|       |  redirect(code)|       | + set(key,val)  |
| + redirect(code|      +----------------+       +-----------------+
| + trackClick()|       
+---------------+       +-----------------+
                       |   HashGenerator  |
                       +-----------------+
                       | + generate(len=7)|
                       +-----------------+
Key relations: URLService orchestrates, URLMapper DB ops, Cache for reads.

DB Schema
sql
CREATE TABLE url_mappings (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    short_link VARCHAR(10) UNIQUE NOT NULL,
    actual_link TEXT NOT NULL,
    clicks BIGINT DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    user_id VARCHAR(50),
    expires_at TIMESTAMP
);

INDEX idx_short (short_link);
INDEX idx_clicks (clicks DESC);  -- For analytics
Sharding: short_link hash pe.

APIs (REST)
POST /api/shorten: { "longUrl": "https://example.com/very/long", "userId": "rocky" } → { "shortUrl": "abc123" }

GET /:shortCode: Redirect to actual_link, increment clicks.

GET /api/stats/:shortCode: { "clicks": 350, "created": "2026-04-24" }

Objects & Relations
URL Object: class URL { longUrl, shortCode, clicks, createdAt, userId }

Relations: One-to-one (short→long), User has many URLs.

Hashing: Counter++ → base62 encode (62^7 = 3.5T unique).

Error Handling & Security
Errors: Invalid URL (400), Duplicate short (409), Expired (410), Rate limit (429).

Security: Input validation (URL regex), HTTPS only, CAPTCHA for abuse, custom short unique check, SQL injection prevent (prepared stmts).

Edge Cases: Collision (retry generate), High traffic (queue clicks), Analytics async via queue.