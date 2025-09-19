# Microservices: Shop Demo (Spring Boot backend)

Een kleine set microservices op basis van een monoliet (zie main branch) met:
- **Auth**: register/login (dummy, geen echte security)
- **Productcatalogus**: CRUD
- **Orders**: order plaatsen (controleert productvoorraad), simple status
- **Payments**: gesimuleerde betaling

## Quickstart
Installeer Java 23

```bash
./mvnw spring-boot:run
```

H2 Console: `http://localhost:8080/h2-console` (JDBC URL: `jdbc:h2:mem:testdb`)

### Endpoints (selectie)
POST http://localhost:8081/api/auth/register { "username": "alice", "password": "pw" }

POST http://localhost:8081/api/auth/login { "username": "alice", "password": "pw" }

GET http://localhost:8084/api/products

POST http://localhost:8084/api/products { "name": "Book", "description": "SWA", "price": 19.99, "stock": 5 }

POST http://localhost:8082/api/orders { "userId": 1, "items": [{ "productId": 1, "quantity": 2 }]}

GET http://localhost:8082/api/orders/{id}

De folder src/main/resources bevat een Bruno collection (bruno.json) die je in Bruno kunt importeren om endpoints te testen.
