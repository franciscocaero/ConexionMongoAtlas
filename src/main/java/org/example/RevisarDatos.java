package org.example;

import com.mongodb.client.MongoClient;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

import com.mongodb.client.MongoCollection;
import org.bson.Document;
import com.mongodb.client.FindIterable;


import javax.swing.*;

public class RevisarDatos {
    public JPanel verPasatiempos;
    private JTextArea DatosBase;
    public void cargarDatos() {
        String connectionString = "tu_connection_string";
        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            MongoDatabase database = mongoClient.getDatabase("nombre_base_de_datos");
            MongoCollection<Document> collection = database.getCollection("nombre_coleccion");
            FindIterable<Document> documents = collection.find();
            for (Document doc : documents) {
                DatosBase.append(doc.toJson() + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
