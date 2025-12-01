export interface Plantio {
    plantio: string,
    area: number,
    quantidadePlantio: number,
    gastoEstimado: number,
    producaoEsperada: number,
    observacoes?: string,
    status: string,
    dataPlantio: Date,
    previsaoColheita: Date,
    userId: string;
}
