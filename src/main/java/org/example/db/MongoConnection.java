package com.agroview.db;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoException;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class MongoConnection {

    private static MongoClient mongoClient;
    private static MongoDatabase database;

    public static MongoDatabase getDatabase() {
        if (database == null) {
            try {
                String connectionString = "mongodb+srv://pedrokachan:100nha@cluster0.ylxthep.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0";

                ServerApi serverApi = ServerApi.builder()
                        .version(ServerApiVersion.V1)
                        .build();

                MongoClientSettings settings = MongoClientSettings.builder()
                        .applyConnectionString(new ConnectionString(connectionString))
                        .serverApi(serverApi)
                        .build();

                mongoClient = MongoClients.create(settings);

                database = mongoClient.getDatabase("Data");

                database.runCommand(new Document("ping", 1));
                System.out.println("🌿 Conectado com sucesso ao MongoDB Atlas!");
            } catch (MongoException e) {
                System.err.println("❌ Erro ao conectar ao MongoDB: " + e.getMessage());
                throw new RuntimeException("Falha ao conectar ao MongoDB", e);
            }
        }
        return database;
    }

    public static void closeConnection() {
        if (mongoClient != null) {
            mongoClient.close();
            System.out.println("🔒 Conexão com MongoDB fechada.");
        }
    }
}
