# bankapi
Demo project for Spring Cloud

##Business-domen microservices 
* **API** not public high-level API for Front-clients, responsibility for business-logic, persistence services with SQL BD.
* **Front** - simple public API for frontend (js-app) + caсhed persistence with NoSQL BD.

####Domen model API module
* **Customer** - have some accounts.
* **Account** - have balance, some transactions and concrete currency. 
* **Transactions** - have status: DRAFT, REFUSE or COMPLETE.

Fat JAR: https://yadi.sk/d/pDFHTbiMEvLk_A

Run: `java -jar api-0.0.1-SNAPSHOT.jar`

####Develop Services
* **Swagger UI**: http://localhost:8080/swagger-ui.html
* **H2 console**: http://localhost:8080/console (JDBC URL: jdbc:h2:mem:testdb,  User Name: sa)
* **POSTMAN requests collection**: 
https://www.getpostman.com/collections/742c9f7339d9d0594253

###TODO
#####CODE
* Abstract class BaseEntity with Audit (create and change timestep)
* Add validation annotations for fields of entities classes
* Comments for public methods
* Comments for swagger (@Api and @ApiOperation)
* Loging
* Add TEST, DEV, PROD profiles

#####Tests:
* Fix test method **postAndRunTransactSuccess** in class **BankApiRestControllerTest**
* Add Failed tests in **BankApiRestControllerTest** (and include validation fields)
* Unit tests for services
* Integrations tests 
* E2E api-tests
