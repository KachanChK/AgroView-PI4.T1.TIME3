import { Router } from "express";
import { PlantioController } from "./plantio.controller";

const router = Router();
const controller = new PlantioController();

router.post("/registerPlantio", controller.registerPlantio);

export default router;
