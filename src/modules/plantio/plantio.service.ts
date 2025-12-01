import { getDb } from "../../database/mongo";
import { Plantio } from "./plantio.types";
import { ObjectId } from "mongodb";

export class PlantioService {
    async createPlantio(data: Plantio) {
        const db = getDb();
        const plantioCollection = db.collection<Plantio>("plantios");

        const newPlantio = {
            ...data,
            createdAt: new Date()
        };

        const result = await plantioCollection.insertOne(newPlantio);

        return {
            _id: result.insertedId
        };
    }

    async listarPlantios(oid: string) {
        const db = getDb();
        const plantioCollection = db.collection<Plantio>("plantios");

        const plantios = await plantioCollection
            .find({ oid })
            .sort({ createdAt: -1 })
            .toArray();

        return plantios;
    }
}
