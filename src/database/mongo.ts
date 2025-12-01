import { MongoClient } from "mongodb";
import dotenv from "dotenv";

dotenv.config();

const uri = process.env.MONGO_URI;

if (!uri) {
  throw new Error("A variável MONGO_URI não está definida no .env");
}

export const client = new MongoClient(uri);

export async function connectMongo() {
  try {
    await client.connect();
    console.log("📦 MongoDB conectado com o Driver Oficial!");
    return client.db("AgroViewDB");
  } catch (error) {
    console.error("Erro ao conectar no MongoDB:", error);
    process.exit(1);
  }
}

export function getDb() {
  return client.db("AgroViewDB");
}
