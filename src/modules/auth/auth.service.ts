import { getDb } from "../../database/mongo";
import { User } from "./auth.types";
import { ObjectId } from "mongodb";
import bcrypt from "bcryptjs";

export class AuthService {

    async createUser(data: User) {
        const db = getDb();
        const usersCollection = db.collection<User>("users");

        // Verifica se o email já existe
        const existing = await usersCollection.findOne({ email: data.email });
        if (existing) {
            throw new Error("E-mail já cadastrado.");
        }

        const hashedPassword = await bcrypt.hash(data.password, 10);

        const newUser = {
            ...data,
            password: hashedPassword,
            createdAt: new Date()
        };

        const result = await usersCollection.insertOne(newUser);

        return {
            _id: result.insertedId,
            name: data.name,
            email: data.email
        };
    }
    
    async login(email: string, password: string) {
        const db = getDb();
        const usersCollection = db.collection<User>("users");

        const user = await usersCollection.findOne({ email });

        if (!user) {
            throw new Error("E-mail não encontrado.");
        }

        const passwordMatch = await bcrypt.compare(password, user.password);

        if (!passwordMatch) {
            throw new Error("Senha incorreta.");
        }

        // remover a senha antes de retornar
        const { password: _, ...userNoPass } = user;

        return userNoPass;
    }

}
