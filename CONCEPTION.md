# SimpleCashSI – Document de conception

## 1. Contexte et objectifs

La banque **SimpleCash** souhaite disposer d’un SI permettant à ses conseillers de :
- Gérer leurs **clients**
- Gérer les **comptes bancaires** (au minimum comptes courants)
- Réaliser des **opérations de débit / crédit**
- Effectuer des **virements compte à compte**
- Préparer des **audits** sur les comptes (soldes débiteurs / créditeurs)

Ce projet est réalisé en **Spring Boot**, avec une architecture en couches :
- **Controller** (exposition REST)
- **Service** (règles métier)
- **Repository** (accès aux données JPA)
- **Entity** (modèle métier JPA)

La base de données utilisée est **H2 en mémoire** pour simplifier les tests.

---

## 2. Architecture applicative

L’application suit un **modèle en couches** :

- **Controller layer**  
  Expose des endpoints REST pour les clients, conseillers, comptes et virements.

- **Service layer**  
  Porte la logique métier (limite de 10 clients par conseiller, gestion du découvert, suppression des comptes lors de la suppression d’un client, etc.).

- **Persistence layer**  
  Basée sur **Spring Data JPA** (`JpaRepository`) pour simplifier l’accès aux données.

- **Domain layer (entities)**  
  Contient les entités JPA : `Advisor`, `Client`, `Account`, `CurrentAccount`.

---

## 3. Modèle de données (entités principales)

### 3.1 Advisor

Représente un **conseiller bancaire**.

Attributs principaux :
- `id : Long`
- `name : String`
- `clients : List<Client>`

Contraintes :
- Un conseiller peut gérer **au plus 10 clients**.

### 3.2 Client

Représente un **client** de la banque.

Attributs principaux :
- `id : Long`
- `name : String`
- `surname : String`
- `adress : String`
- `city : String`
- `phoneNumber : String`
- `advisor : Advisor`
- `accounts : List<Account>`

Règles métier :
- Un client est rattaché à **un conseiller**.
- Lors de la suppression d’un client, **tous ses comptes sont supprimés**.

### 3.3 Account (abstraite)

Classe abstraite représentant un **compte bancaire**.

Attributs :
- `id : Long`
- `accountNumber : String`
- `balance : BigDecimal`
- `creationDate : LocalDateTime`
- `client : Client`

Deux sous-types :
- `CurrentAccount`
- `SavingAccount` (optionnel dans cette version, pas forcément implémenté côté services/controllers)

### 3.4 CurrentAccount

Compte courant.

Attributs :
- `overdraft : BigDecimal` (découvert autorisé, par défaut 1000€)

Règles :
- Le solde ne peut pas descendre sous `-overdraft`.

---

## 4. User stories minimales

1. **US1 – Créer un conseiller**
    - En tant qu’administrateur, je souhaite créer un conseiller afin qu’il puisse gérer des clients.

2. **US2 – Créer un client**
    - En tant que conseiller, je peux créer un client rattaché à moi, dans la limite de 10 clients.

3. **US3 – Créer un compte courant pour un client**
    - En tant que conseiller, je peux créer un compte courant pour un client existant.

4. **US4 – Créditer / débiter un compte**
    - En tant que conseiller, je peux ajouter ou retirer de l’argent d’un compte courant, en respectant la limite de découvert.

5. **US5 – Virement compte à compte**
    - En tant que conseiller, je peux transférer un montant d’un compte source vers un compte destination de la banque.

6. **US6 – Supprimer un client**
    - En tant que conseiller, je peux supprimer un client, ce qui supprime également ses comptes.

---

## 5. API REST – principaux endpoints

### 5.1 Advisors

- `POST /api/advisors`  
  Crée un nouveau conseiller.

- `GET /api/advisors/{id}`  
  Récupère un conseiller par son id.

- `DELETE /api/advisors/{id}`  
  Supprime un conseiller (seulement s’il n’a plus de clients).

### 5.2 Clients

- `POST /api/advisors/{advisorId}/clients`  
  Crée un nouveau client rattaché à un conseiller.

- `GET /api/clients/{id}`  
  Récupère un client.

- `PUT /api/clients/{id}`  
  Met à jour les informations d’un client.

- `DELETE /api/clients/{id}`  
  Supprime un client et ses comptes.

- `GET /api/advisors/{advisorId}/clients`  
  Liste les clients d’un conseiller.

### 5.3 Comptes courants

- `POST /api/current-accounts/create/{clientId}`  
  Crée un compte courant pour un client.

- `GET /api/current-accounts/{number}`  
  Récupère un compte par numéro.

- `POST /api/current-accounts/{number}/add?amount=XXX`  
  Créditer un compte.

- `POST /api/current-accounts/{number}/remove?amount=XXX`  
  Débiter un compte (en vérifiant le découvert).

### 5.4 Virements

- `POST /api/transfers`  
  Corps attendu :
  ```json
  {
    "source": "ACCOUNT_NUMBER_SOURCE",
    "destination": "ACCOUNT_NUMBER_DEST",
    "amount": 100.0
  }

### 6. Règles métier importantes

Un conseiller ne peut pas gérer plus de 10 clients.

Lors d’un virement :

le compte source est débité

le compte destination est crédité

le virement est refusé si le découvert autorisé serait dépassé.

Lors de la suppression d’un client :

tous les comptes associés sont supprimés.

Les cartes bancaires ne sont pas implémentées dans cette version (choix de simplification).

### 7. Choix techniques

Langage : Java 21

Framework : Spring Boot 3.5.x

Persistance : Spring Data JPA + Hibernate

Base de données : H2 en mémoire (profil dev)

Tests : JUnit 5 + Mockito (tests sur les services AccountService, TransferService, etc.)

### 8. Reste à faire / améliorations possibles

Implémenter la gestion des comptes épargne et du taux d’intérêt.

Ajouter un vrai AuditService listant :

tous les comptes débiteurs / créditeurs

total des soldes débiteurs / créditeurs.

Ajouter des validations sur les DTO (Bean Validation).

Gérer des exceptions globales avec @ControllerAdvice pour renvoyer des erreurs JSON propres.

Sécuriser l’API (Spring Security) avec authentification par rôle (conseiller / admin).

Ajouter une interface front (Angular/React) pour consommer l’API.

Persistance dans une vraie base (PostgreSQL / MySQL) pour un environnement de prod.

