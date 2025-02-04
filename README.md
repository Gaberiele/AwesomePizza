# 🍕 AwesomePizza API

AwesomePizza è un'API REST per la gestione degli ordini di pizze. Consente di creare, visualizzare e aggiornare lo stato degli ordini.

## 📌 Tecnologie Utilizzate  
- **Spring Boot** (Web, JPA, Data)  
- **MariaDB** (Database)  
- **JUnit & Mockito** (Testing)  
- **Maven** (Gestione dipendenze)  

## 🚀 Avvio del Progetto  

### ** Prerequisiti**  
- Java 17+  
- Maven  
- MariaDB  
- Postman o cURL per testare le API  

### ** Configurazione del Database**  
Nel file `application.properties` assicurati che i dati di connessione siano corretti:  

```properties
spring.datasource.url=jdbc:mariadb://localhost:3306/testpizzadb
spring.datasource.username=root
spring.datasource.password=
spring.datasource.driver-class-name=org.mariadb.jdbc.Driver
spring.jpa.database-platform=org.hibernate.dialect.MariaDBDialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### ** Avvio dell'Applicazione** 
Esegui il seguente comando:

```sh
mvn spring-boot:run
```
L'API sarà disponibile su http://localhost:8080.


## 📚 API Endpoints
### ** Creare un Ordine **
✅ Endpoint: POST /orders/create

🎯 Descrizione: Crea un nuovo ordine di pizza.

🧰 Request Body (JSON):
```json
{
  "ordine": "Pizza Margherita"
}
```
📦 Response (JSON):
```json
{
  "id": 1,
  "ordine": "Pizza Margherita",
  "stato": "DA_FARE",
  "dataOra": "2024-02-04T18:00:00"
}
```
### **  Recuperare tutti gli Ordini **
✅ Endpoint: GET /orders

🎯 Descrizione: Ottiene la lista di tutti gli ordini.

📦 Response (JSON):
```json
[
  {
    "id": 1,
    "ordine": "Pizza Margherita",
    "stato": "DA_FARE",
    "dataOra": "2024-02-04T18:00:00"
  },
  {
    "id": 2,
    "ordine": "Pizza Diavola",
    "stato": "IN_LAVORAZIONE",
    "dataOra": "2024-02-04T18:05:00"
  }
]
```

### **  Recuperare un Ordine per ID **
✅ Endpoint: GET /orders/{id}

🎯 Descrizione: Ottiene un ordine specifico tramite ID.

🌟 Esempio: GET /orders/1

📦 Response (JSON):
```json
{
  "id": 1,
  "ordine": "Pizza Margherita",
  "stato": "DA_FARE",
  "dataOra": "2024-02-04T18:00:00"
}
```

### **  Aggiornare lo Stato di un Ordine **
✅ Endpoint: PUT /orders/{id}/update?status={nuovo_stato}

🎯 Descrizione: Modifica lo stato di un ordine esistente.

🌟 Esempio: PUT /orders/1/update?status=IN_CONSEGNA

📦 Response: "Stato aggiornato" oppure "Ordine non trovato"


🧪 Testing dell'API
Esegui i test unitari con:
```sh
mvn test
```

I test coprono:

Creazione ordine (OrderServiceTest)
Recupero ordini (OrderRepositoryTest)
Controller API (OrderControllerTest)
