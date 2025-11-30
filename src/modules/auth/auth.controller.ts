import { Request, Response } from "express";
import { AuthService } from "./auth.service";
import net from "net";

const authService = new AuthService();

export class AuthController {

    validarSenhaComJava = (senha: string): Promise<boolean | "Servidor não operando"> => {
        return new Promise((resolve, reject) => {
            const client = new net.Socket();

            client.connect(1010, "localhost", () => {
                client.write(senha + "\n");
            });

            client.on("data", data => {
                const resposta = data.toString().trim();
                client.end();
                resolve(resposta === "true");
            });

            client.on("error", () => {
            resolve("Servidor não operando");
        });
        });
    };

    register = async (req: Request, res: Response) => {
        try {
            const { name, email, telefone, documento, tipoDoc, password } = req.body;

            if (!name || !email || !telefone || !documento || !tipoDoc || !password) {
                return res.status(400).json({ error: "Preencha todos os campos!" });
            }

            if (tipoDoc !== "cpf" && tipoDoc !== "cnpj") {
                return res.status(400).json({ error: "Tipo de documento inválido!" });
            }

            const senhaAprovada = await this.validarSenhaComJava(password);

            if (senhaAprovada === "Servidor não operando") {
                return res.status(503).json({

                    error: "Servidor não operando"

                });
            }
            
            if (!senhaAprovada) {
                return res.status(400).json({
                    error: "Senha reprovada pelo validador de segurança."
                });
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
    };

    login = async (req: Request, res: Response) => {
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
    };
}