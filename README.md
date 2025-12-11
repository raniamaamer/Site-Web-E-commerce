# 🛒 Tests Fonctionnels E-commerce

Framework d'automatisation de tests pour applications e-commerce utilisant Selenium WebDriver et TestNG.

[![Java](https://img.shields.io/badge/Java-21-orange.svg)](https://www.oracle.com/java/)
[![Selenium](https://img.shields.io/badge/Selenium-4.11.0-brightgreen.svg)](https://www.selenium.dev/)
[![TestNG](https://img.shields.io/badge/TestNG-7.8.0-red.svg)](https://testng.org/)
[![Maven](https://img.shields.io/badge/Maven-3.9-blue.svg)](https://maven.apache.org/)

## 📋 Table des Matières

- [Présentation](#présentation)
- [Architecture du Projet](#architecture-du-projet)
- [Technologies](#technologies)
- [Fonctionnalités](#fonctionnalités)
- [Installation](#installation)
- [Configuration](#configuration)
- [Exécution des Tests](#exécution-des-tests)
- [Résultats des Tests](#résultats-des-tests)
- [Points Forts](#points-forts)
- [Améliorations Futures](#améliorations-futures)
- [Auteur](#auteur)

## 🎯 Présentation

Ce projet automatise les tests fonctionnels de l'application e-commerce **SauceDemo**, garantissant le bon fonctionnement des fonctionnalités critiques et réduisant le temps de test manuel.

**Objectifs Principaux :**
- Vérifier le bon fonctionnement des fonctionnalités critiques
- Assurer la régression lors des mises à jour
- Réduire le temps de test manuel
- Fournir un framework maintenable et réutilisable

**Périmètre des Tests :**
- Authentification et navigation
- Gestion du catalogue et recherche de produits
- Fonctionnalités du panier et checkout
- Workflow complet E2E (End-to-End)

## 🏗️ Architecture du Projet

Le projet utilise le **Page Object Model (POM)** pour séparer les pages et la logique des tests.

```
ecommerce-functional-tests/
├── src/
│   ├── main/
│   │   ├── java/
│   │   └── resources/
│   └── test/
│       ├── java/
│       │   └── com.rania.ecommerce/
│       │       ├── base/
│       │       │   └── BaseTest.java
│       │       ├── pages/
│       │       │   ├── CartPage.java
│       │       │   ├── CheckoutPage.java
│       │       │   ├── HomePage.java
│       │       │   ├── LoginPage.java
│       │       │   ├── ProductPage.java
│       │       │   └── SearchResultsPage.java
│       │       ├── tests/
│       │       │   └── E2ETest.java
│       │       └── utils/
│       │           └── ConfigReader.java
│       └── resources/
│           ├── config.properties
│           └── testng.xml
├── pom.xml
└── README.md
```

### Avantages du POM :
- Séparation claire des responsabilités
- Réutilisabilité des méthodes
- Maintenance facilitée
- Tests plus lisibles et explicites

## 🛠️ Technologies

| Technologie | Version | Rôle |
|------------|---------|------|
| Java | 21 | Langage principal |
| Selenium WebDriver | 4.11.0 | Automatisation UI |
| TestNG | 7.8.0 | Framework de test |
| WebDriverManager | 5.3.2 | Gestion automatique des drivers |
| Maven | 3.x | Gestion des dépendances |
| SLF4J | 2.0.9 | Logger |

## ✨ Fonctionnalités

### Composants de Base

**BaseTest.java**
- Initialisation du driver Chrome avec WebDriverManager
- Configuration automatique des options Chrome (maximisation de la fenêtre)
- Gestion des timeouts implicites (10 secondes)
- Option `keepBrowserOpen` pour garder le navigateur ouvert après les tests
- Chargement automatique du fichier `config.properties`

### Page Objects

- **LoginPage** : Connexion avec gestion des messages d'erreur
- **HomePage** : Connexion utilisateur et vérification de l'état connecté
- **ProductPage** : Sélection et ajout de produits au panier
- **SearchResultsPage** : Affichage et sélection des résultats de recherche
- **CartPage** : Vérification du contenu du panier et passage à la commande
- **CheckoutPage** : Workflow complet en 3 étapes
  - Informations de livraison (prénom, nom, email, adresse, ville, code postal)
  - Informations de paiement (numéro de carte, expiration, CVV)
  - Confirmation de commande

### Utilitaires

**ConfigReader.java**
- Lecture centralisée des propriétés depuis `config.properties`
- Méthodes utilitaires pour récupérer des valeurs (String, Int, Boolean)
- Gestion d'erreurs si le fichier est introuvable

## 📥 Installation

1. **Prérequis**
   - Java 11 ou supérieur installé
   - Maven 3.x installé
   - Navigateur Chrome installé

2. **Cloner le dépôt**
   ```bash
   git clone <url-du-depot>
   cd ecommerce-functional-tests
   ```

3. **Installer les dépendances**
   ```bash
   mvn clean install
   ```

## ⚙️ Configuration

### Fichier config.properties

Créer un fichier `config.properties` dans `src/test/resources/` :

```properties
# URL du site de démo
base.url=https://www.saucedemo.com/

# Identifiants de connexion
username=standard_user
password=secret_sauce

# Produit à rechercher / ajouter au panier
search.keyword=Sauce Labs Backpack

# Informations fictives pour le checkout (si nécessaire)
first.name=Rania
last.name=Maamer
email=test@example.com
address=123 Rue Exemple
city=Tunis
postal=1009
card.number=4111111111111111
card.expiry=12/30
card.cvv=123
```

### Configuration testng.xml

```xml
<!DOCTYPE suite SYSTEM "https://testng.org/testng-1.0.dtd" >
<suite name="EcommerceSuite">
  <test name="E2E">
    <classes>
      <class name="com.rania.ecommerce.tests.E2ETest"/>
    </classes>
  </test>
</suite>
```

### Configuration Maven (pom.xml)

Le fichier `pom.xml` est déjà configuré avec toutes les dépendances nécessaires :
- Selenium Java 4.11.0
- WebDriverManager 5.3.2
- TestNG 7.8.0
- SLF4J Simple Logger 2.0.9

## 🚀 Exécution des Tests

### Via Maven

```bash
# Exécuter tous les tests
mvn clean test

# Exécuter avec un fichier testng.xml spécifique
mvn test -DsuiteXmlFile=testng.xml
```

### Via IDE (Eclipse/IntelliJ)

1. Clic droit sur `testng.xml` → **Run as TestNG Suite**
2. Ou clic droit sur `E2ETest.java` → **Run as TestNG Test**

### Options de Configuration

**Garder le navigateur ouvert :**
Dans `BaseTest.java`, modifier la variable :
```java
protected boolean keepBrowserOpen = true;  // Le navigateur reste ouvert
protected boolean keepBrowserOpen = false; // Le navigateur se ferme automatiquement
```

## 📊 Résultats des Tests

### Test E2E Complet

Le test `E2ETest.java` couvre le parcours utilisateur complet :

| Étape | Test | Priorité | Description |
|-------|------|----------|-------------|
| 1 | `testLogin` | 0 | Connexion avec identifiants valides |
| 2 | `testAddProductToCart` | 1 | Recherche et ajout d'un produit au panier |
| 3 | `testCheckoutProcess` | 2 | Processus complet de commande |

**Assertions Principales :**
- ✅ Vérification de la connexion réussie
- ✅ Vérification de la présence du produit dans le panier
- ✅ Vérification de la confirmation de commande

**Durée Moyenne :** 15-20 secondes pour le test E2E complet

## 💪 Points Forts

- ✅ Architecture claire et modulaire (POM)
- ✅ Code réutilisable et maintenable
- ✅ Gestion automatique des drivers avec WebDriverManager
- ✅ Configuration externalisée dans `config.properties`
- ✅ Gestion robuste des timeouts implicites
- ✅ Test E2E couvrant le parcours utilisateur complet
- ✅ Option pour garder le navigateur ouvert pendant le développement
- ✅ Utilitaire ConfigReader pour une gestion centralisée des propriétés

## 🔮 Améliorations Futures

- [ ] **Intégration CI/CD** : Pipeline Jenkins, GitLab CI, GitHub Actions
- [ ] **Tests Négatifs** : Ajouter des tests avec identifiants invalides

## 📝 Notes Additionnelles

### Locateurs Selenium Utilisés

| Type | Exemple | Usage |
|------|---------|-------|
| ID | `By.id("user-name")` | Éléments avec attribut id |
| ClassName | `By.className("inventory_item")` | Classes CSS |
| CSS Selector | `By.cssSelector(".cart-item")` | Sélecteurs CSS |
| XPath | *(non utilisé)* | - |

### Glossaire
- **POM** : Page Object Model - Pattern de conception pour les tests
- **WebDriver** : Interface d'automatisation de navigateur
- **TestNG** : Framework de test pour Java avec annotations
- **WebDriverManager** : Gestion automatique des drivers navigateurs
- **Implicit Wait** : Attente globale appliquée à tous les éléments
- **E2E** : End-to-End - Test du parcours utilisateur complet

## 👤 Auteur

**Rania Maamer**

📅 Date du Projet : 11 Décembre 2025  
📌 Version : 1.0  
🎓 Projet d'automatisation de tests Selenium WebDriver