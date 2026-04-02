# 🎲 TableTopHub - Board Game Rental System

TableTopHub to kompleksowa aplikacja backendowa typu REST API stworzona do zarządzania wypożyczalnią gier planszowych. System pozwala na zarządzanie katalogiem gier, użytkownikami, procesem wypożyczeń oraz systemem ocen i recenzji.

## 🚀 Kluczowe Funkcjonalności

### 📦 Zarządzanie Katalogiem (CRUD)
* Pełna obsługa **Gier**, **Wydawców** oraz **Kategorii**.
* Zaawansowane mapowanie DTO (Data Transfer Object) dla zapewnienia bezpieczeństwa i wydajności API.
* Liczniki gier dla każdego wydawcy i kategorii widoczne bezpośrednio w widoku zestawienia.

### 💳 System Wypożyczeń (Business Logic)
* **Zasada 3 gier:** Użytkownik może posiadać maksymalnie 3 aktywne wypożyczenia jednocześnie.
* **Kontrola dostępności:** System uniemożliwia wypożyczenie gry, która jest aktualnie u innego klienta.
* **Integracja transakcyjna:** Użycie `@Transactional` zapewnia spójność danych (zmiana statusu gry + utworzenie rekordu wypożyczenia).

### ⭐️ System Ocen i Opinii
* Możliwość oceniania gier w skali 1-10.
* **Średnia ocen:** System dynamicznie oblicza i wyświetla średnią ocenę dla każdej gry.
* **Ochrona spamu:** Każdy użytkownik może wystawić tylko jedną ocenę danej grze (z możliwością jej późniejszej edycji).

## 🛠 Stos Technologiczny
* **Backend:** Java 17, Spring Boot 3.x
* **Data Access:** Spring Data JPA, Hibernate
* **Baza Danych:** MySQL 9.x
* **Dokumentacja API:** Swagger UI (OpenAPI 3.0)
* **Narzędzia:** Maven, Lombok, Git

## 📋 Dokumentacja API
Aplikacja posiada wbudowaną dokumentację Swagger, która pozwala na testowanie wszystkich endpointów w czasie rzeczywistym.
* **URL:** `http://localhost:8080/swagger-ui/index.html`

## 🏗 Architektura Projektu
Projekt został zrealizowany w architekturze wielowarstwowej:
1. **Controller Layer:** Obsługa żądań HTTP i zwracanie JSON.
2. **Service Layer:** Całość logiki biznesowej i walidacji.
3. **Repository Layer:** Komunikacja z bazą danych (Spring Data JPA).
4. **Entity Layer:** Mapowanie obiektowo-relacyjne (ORM).
5. **DTO Layer:** Separacja danych wejściowych i wyjściowych od bazy danych.

## 🚦 Uruchomienie Projektu
1. Sklonuj repozytorium.
2. Skonfiguruj bazę danych MySQL (nazwa bazy: `TableTopHub`).
3. Zaktualizuj hasło do bazy w `src/main/resources/application.properties`.
4. Uruchom aplikację – system automatycznie utworzy tabele i załaduje dane testowe z pliku `data.sql`.

---
*Projekt realizowany w ramach kursu programowania Java - Coders Lab.*