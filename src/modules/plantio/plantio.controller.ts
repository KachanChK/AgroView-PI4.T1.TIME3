import { Request, Response } from "express";
import { PlantioService } from "./plantio.service";
import { Plantio } from "./plantio.types";

const plantioService = new PlantioService();


export class PlantioController {
    async registerPlantio(req: Request, res: Response) {
        try {
            const { plantio, area, quantidadePlantio, gastoEstimado, producaoEsperada, observacoes, status, dataPlantio, previsaoColheita, oid } = req.body;

            if (!plantio || !area || !quantidadePlantio || !gastoEstimado || !producaoEsperada || !status || !dataPlantio || !previsaoColheita || !oid) {
                return res.status(400).json({ error: "Preencha todos os campos!" });
            }

            const plantioData: any = {
                plantio, 
                area, 
                quantidadePlantio, 
                gastoEstimado, 
                producaoEsperada,
                status, 
                dataPlantio, 
                previsaoColheita,
                oid
            };

            if (observacoes) { plantioData.observacoes = observacoes; } 

            const plantio_obj = await plantioService.createPlantio(plantioData);

            return res.status(201).json({
                message: "Plantio cadastrado com sucesso!",
                plantio_obj
            });

        } catch (error: any) {
            return res.status(400).json({ error: error.message });
        }
    }
}
