Diagramme de classe:
@startuml

class Advisor {
- id : Long
- name : String
  --
+ getId()
+ getName()
+ getClients()
  }

class Client {
- id : Long
- name : String
- surname : String
- adress : String
- city : String
- phoneNumber : String
  --
+ getId()
+ getName()
+ getSurname()
+ getAdress()
+ getCity()
+ getPhoneNumber()
  }

abstract class Account {
- id : Long
- accountNumber : String
- balance : BigDecimal
- creationDate : LocalDateTime
  --
+ getId()
+ getAccountNumber()
+ getBalance()
+ getCreationDate()
  }

class CurrentAccount {
- overdraft : BigDecimal
  --
+ getOverdraft()
  }

class SavingAccount {
- rate : BigDecimal
  --
+ getRate()
  }

Advisor "1" o-- "0..10" Client
Client "1" o-- "0..*" Account
Account <|-- CurrentAccount
Account <|-- SavingAccount


Vue architechture:

package "Controller layer" {
class AdvisorController
class ClientController
class CurrentAccountController
class TransferController
}

package "Service layer" {
class AdvisorService
class ClientService
class AccountService
class CurrentAccountService
class TransferService
}

package "Persistence layer" {
class AdvisorRepository
class ClientRepository
class AccountRepository
}

AdvisorController --> AdvisorService
ClientController --> ClientService
CurrentAccountController --> CurrentAccountService
CurrentAccountController --> AccountService
TransferController --> TransferService
TransferService --> AccountService

AdvisorService --> AdvisorRepository
ClientService --> ClientRepository
ClientService --> AccountRepository
AccountService --> AccountRepository
CurrentAccountService --> AccountRepository

@enduml


+---------------------+         1        *     +----------------------+
|       Advisor       |------------------------|        Client        |
+---------------------+                       +----------------------+
| id : Long           |                       | id : Long            |
| name : String       |                       | name : String        |
| clients : List<>    |                       | surname : String     |
+---------------------+                       | adress : String      |
| city : String        |
| phoneNumber : String |
| advisor : Advisor    |
| accounts : List<>    |
+----------------------+
|
| 1
|
| *
+----------------------+
|       Account        |
+----------------------+
| id : Long            |
| accountNumber:String |
| balance: BigDecimal  |
| creationDate : Date  |
| client : Client      |
+----------------------+
^
|
|
+---------------------------+--------------------------+
|                                                          |
+----------------------------+                            +-----------------------------+
|       CurrentAccount       |                            |      SavingAccount (opt)    |
+----------------------------+                            +-----------------------------+
| overdraft : BigDecimal     |                            | interestRate : BigDecimal   |
+----------------------------+                            +-----------------------------+
