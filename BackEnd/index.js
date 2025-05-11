import express from "express";
import cors from "cors";
import dotenv from "dotenv";

// Načtení proměnných z .env souboru
dotenv.config();

const app = express();
const PORT = process.env.PORT || 8080;

// Middleware
app.use(cors({
    origin: "*", // V produkci můžeš omezit např. jen na frontend URL
    credentials: true
}));
app.use(express.json());

// Testovací endpoint
app.get("/", (req, res) => {
    res.send("Backend běží! 🚀");
});

// Start serveru
app.listen(PORT, () => {
    console.log(`Server běží na portu ${PORT}`);
});
