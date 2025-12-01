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

    async listarPlantios(req: Request, res: Response) {
        
        try {
            const { oid } = req.params;

            if (!oid) {
                return res.status(400).json({ error: "OID do usuário não informado." });
            }

            const plantios = await plantioService.listarPlantios(oid);

            return res.status(200).json(plantios);
            

        } catch (error: any) {
            console.error("Erro ao listar plantios:", error);
            return res.status(500).json({ error: "Erro interno ao listar plantios." });
        }
    }
}
