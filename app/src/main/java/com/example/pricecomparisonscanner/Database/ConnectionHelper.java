package com.example.pricecomparisonscanner.Database;

import com.example.pricecomparisonscanner.information.AllProductInformation;
import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.util.JSON;

import org.bson.Document;

import java.util.ArrayList;

public class ConnectionHelper {

    public static void sendPrices(AllProductInformation finalInfo) {

        MongoClientURI uri = new MongoClientURI(Login.getMongo());
        MongoClient client = new MongoClient(uri);
        MongoDatabase db = client.getDatabase(uri.getDatabase());
        MongoCollection collection = db.getCollection("newDB");

        Gson gson = new Gson();
        BasicDBObject dbObject = (BasicDBObject) JSON.parse(gson.toJson(finalInfo));
        collection.insertOne(new Document(dbObject.toMap()));
//
        MongoCursor cursor = collection.find(new BasicDBObject("upc", "043000204313")).iterator();

        try {
            while (cursor.hasNext()) {
                Document doc = (Document) cursor.next();
            }
        } finally {
            cursor.close();
        }
//
        client.close();
    }

    public static ArrayList retrievePrices(String upc) {

        ArrayList priceList = new ArrayList<String>();

        MongoClientURI uri = new MongoClientURI(Login.getMongo());
        MongoClient client = new MongoClient(uri);
        MongoDatabase db = client.getDatabase(uri.getDatabase());
        MongoCollection collection = db.getCollection("newDB");
        MongoCursor cursor = collection.find(new BasicDBObject("upc", upc)).iterator();

        try {
            while (cursor.hasNext()) {
                Document doc = (Document) cursor.next();
                priceList.add(doc.toJson());
            }
        } finally {
            cursor.close();
        }
        client.close();
        return priceList;
    }
}
