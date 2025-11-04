package db;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoConnection {
    private static MongoClient client;
    private static MongoDatabase database;

    public static MongoDatabase getDatabase() {
        if (database == null) {
            client = MongoClients.create("");
            database = client.getDatabase("AgroViewDB");
        }
        return database;
    }
}
