import express from "express";
import path from "path";
import { connectMongo } from "./database/mongo";
import authRoutes from "./modules/auth/auth.routes";

const app = express();
const PORT = 5000;

app.use(express.json());
app.use(express.urlencoded({ extended: true }));
app.use(express.static(path.join(__dirname, "..", "public")));

// Rotas da API
app.use("/api/auth", authRoutes);

// Rotas de página
app.get("/auth", (req, res) => {
  res.sendFile(path.join(__dirname, "..", "public", "auth.html"));
});

app.get("/painel", (req, res) => {
  res.sendFile(path.join(__dirname, "..", "public", "painel.html"));
});

app.listen(PORT, async () => {
  console.log(`Servidor rodando em: http://localhost:${PORT}`);
  await connectMongo();
});
