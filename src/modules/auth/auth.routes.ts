import { Router } from "express";
import { AuthController } from "./auth.controller";

const router = Router();
const controller = new AuthController();

router.post("/register", controller.register.bind(AuthController));
router.post("/login", controller.login.bind(AuthController));

export default router;
