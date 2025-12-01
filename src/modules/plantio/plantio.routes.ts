import { Router } from "express";
import { PlantioController } from "./plantio.controller";

const router = Router();
const controller = new PlantioController();

router.post("/registerPlantio", (req, res) => controller.registerPlantio(req, res));

router.get("/listarPlantios/:oid", (req, res) => controller.listarPlantios(req, res));

export default router;