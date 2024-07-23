# Versicherung API

Dies ist eine Spring Boot-Anwendung zur Berechnung der Versicherungsprämien basierend auf Kilometerleistung, Fahrzeugtyp und Region der Fahrzeugzulassung. Die Anwendung bietet verschiedene Endpunkte zur Berechnung der Prämie und zum Verwalten der Benutzerdaten und Fahrzeugtypen.

## Voraussetzungen

- Java 17 oder höher
- Maven

## Projekt ausführen

Um die Anwendung mit einem lokalen Profil auszuführen, verwenden Sie den folgenden Befehl:

```bash
./mvnw spring-boot:run -Dspring-boot.run.profiles=local
```

### 1. InsuranceController

Dieser Controller bietet Endpunkte zur Berechnung der Versicherungsprämie.

#### Endpunkte

- **POST /api/insurance/calculate**

  Berechnet die Versicherungsprämie basierend auf den bereitgestellten Eingabewerten.

  **Anfrage:**

  ```json
  {
    "kilometer": 12000,
    "zipcode": "12345",
    "vehicleType": "SUV"
  }
  ```
  **Antwort:**
    ```json
  {
  "id": 1,
  "kilometer": 12000,
  "vehicleType": "SUV",
  "zipCode": "12345",
  "calculatedInsurance": 1.5
  }  
  ```

### 2. UserDataController

Dieser Controller bietet Endpunkte zum Verwalten der Benutzerdaten.

#### Endpunkte

- **GET /api/userdata/**

  Gibt eine Liste aller gespeicherten Benutzerdaten zurück.

  **Antwort:**

  ```json
  [
    {
      "id": 1,
      "kilometer": 12000,
      "vehicleType": "SUV",
      "zipCode": "12345",
      "calculatedFactor": 1.5
    },
    ...
  ]
  ```

- **GET /api/userdata/{id}**

    Gibt die Benutzerdaten für die angegebene ID zurück.

    **Antwort:**

    ```json
    {
      "id": 1,
      "kilometer": 12000,
      "vehicleType": "SUV",
      "zipCode": "12345",
      "calculatedFactor": 1.5
    }
  ```

### 3. VehicleTypeController

Dieser Controller bietet Endpunkte zum Verwalten der Fahrzeugtypen.

#### Endpunkte

- **GET /api/vehicles**

  Gibt eine Liste aller verfügbaren Fahrzeugtypen zurück.

  **Antwort:**

    ```json
      [
        {
          "id": 1,
          "name": "SUV",
          "factor": 1.0
        },
        ...
      ]
    ```

- **GET /api/vehicles/{id}**

    Gibt den Fahrzeugtyp für die angegebene ID zurück.

    **Antwort:**
    ```json
    {
      "id": 1,
      "name": "SUV",
      "factor": 1.0
    }
    ```