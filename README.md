# bankapi
Demo project for Spring Cloud

Business-domen microservices: 
* **API** not public high-level API for Front-clients, responsibility for business-logic, persistence services with SQL BD.
* **Front** - simple public API for frontend (js-app) + caсhed persistence with NoSQL BD.

Domen model:
* **Customer** - have some accounts.
* **Account** - have balance, some transactions and concrete currency. 
* **Transactions** - have status: DRAFT, REFUSE or COMPLETE.