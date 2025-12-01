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
}
