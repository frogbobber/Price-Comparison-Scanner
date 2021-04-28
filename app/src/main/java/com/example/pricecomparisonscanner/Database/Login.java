package com.example.pricecomparisonscanner.Database;

public class Login {

    private static String mongo = "mongodb://jj:Tong4L0ng@cluster0-shard-00-00.4zztb.mongodb.net:27017,cluster0-shard-00-01.4zztb.mongodb.net:27017,cluster0-shard-00-02.4zztb.mongodb.net:27017/prices?ssl=true&replicaSet=atlas-1x8opw-shard-0&authSource=admin&retryWrites=true&w=majority";
    private static String username = "jj";
    private static String password = "Tong4L0ng";
    private static String database = "prices";

    public static String getMongo() {
        return mongo;
    }

    public static String getUsername() {
        return username;
    }

    public static String getPassword() {
        return password;
    }

    public static String getDatabase() {
        return database;
    }
}
