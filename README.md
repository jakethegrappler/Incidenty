# 🛡️ Mapa bezpecnostnich incidentu – ČVUT FEL

Interaktivní webová aplikace pro evidenci, vizualizaci a správu bezpečnostních incidentů v kampusu ČVUT FEL. Projekt vznikl jako bakalářská práce s důrazem na přehlednost, moderní design a funkční bezpečnostní nástroje pro univerzitní prostředí.

---

## 🚀 Funkce

* 🗘️ **Interaktivní mapa kampusu** s tečkami a heatmapou incidentů
* 📝 **Formulář pro nahlášení incidentu** – s možností přiložit fotografii
* 📊 **Statistiky incidentů podle sektorů** v podobě grafů
* 🔍 **Filtrování incidentů** podle typu a časového období
* 🎨 **Barevně rozlišené typy incidentů** pro snadnou orientaci
* 🔐 **JWT autentizace** pro různé role uživatelů (Admin, Zaměstnanec, Student)
* 🗃️ **Persistovaný stav a přístupová práva**
* 📩 **Notifikace pro správce a zaměstnance**

---

## 🪰 Použité technologie

### 📦 Backend – Java (Spring Boot)

* Spring Boot 3
* Spring Security + JWT
* REST API
* PostgreSQL
* JPA (Hibernate)
* Docker (pro databázi i vývoj)

### 🌐 Frontend – React

* React 18 (Vite)
* React Router
* Chart.js (statistiky)
* Vlastní SVG mapa + canvas heatmapa
* Responsivní CSS s proměnnými
* Kontextová správa autentizace (`AuthContext`, `useAuth`)

---



## 🛠️ Lokální spuštění

### 1. Backend

```bash
cd backend
./mvnw spring-boot:run
```

> Backend běží na `http://localhost:8080`

### 2. Frontend

```bash
cd frontend
npm install
npm run dev
```

> Frontend běží na `http://localhost:5173`

### 3. Databáze (Docker)

```bash
docker-compose up -d
```

> PostgreSQL databáze bude dostupná na `localhost:5432`

---

## 🔑 Údaje pro přihlášení (výchozí)

| Role        | Uživatelské jméno | Heslo   |
| ----------- | ----------------- | ------- |
| Student     | `student@cvut.cz`  | `heslo`  |
| Zaměstnanec | `employee@cvut.cz`      | `heslo`  |

---

## 📁 Struktura projektu

```
📆 incidenty
👤 backend
│   └── src/main/java/cz/cvut/fel/incidenty/...
👤 frontend
│   └── src/
│       ├── components/
│       ├── pages/
│       ├── auth/
│       └── css/
└── docker-compose.yml
    README.md
```

---

## 👨‍💻 Autor

**Jakub Nedbal**
Student softwarového inženýrství, ČVUT FEL
🔗 [linkedin.com/in/jakub-nedbal](https://linkedin.com)


```
```
