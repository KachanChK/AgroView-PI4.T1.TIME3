import { Request, Response } from "express";
import { AuthService } from "./auth.service";

const authService = new AuthService();

export class AuthController {
    async register(req: Request, res: Response) {
        try {
            const { name, email, telefone, documento, tipoDoc, password } = req.body;

            if (!name || !email || !telefone || !documento || !tipoDoc || !password) {
                return res.status(400).json({ error: "Preencha todos os campos!" });
            }

            if (tipoDoc !== "cpf" && tipoDoc !== "cnpj") {
                return res.status(400).json({ error: "Tipo de documento inválido!" });
            }

            const userData: any = {
                name,
                email,
                telefone,
                password
            };

            if (tipoDoc === "cpf") {
                userData.cpf = documento;
            } else {
                userData.cnpj = documento;
            }

            const user = await authService.createUser(userData);

            return res.status(201).json({
                message: "Usuário cadastrado com sucesso!",
                user
            });

        } catch (error: any) {
            return res.status(400).json({ error: error.message });
        }
    }
    
    async login(req: Request, res: Response) {
        try {
            const { email, password } = req.body;

            if (!email || !password) {
                return res.status(400).json({ error: "Preencha todos os campos!" });
            }

            const user = await authService.login(email, password);
            return res.status(200).json({
                message: "Login realizado com sucesso!",
                user
            });

        } catch (error: any) {
            return res.status(400).json({ error: error.message });
        }
    }


}
