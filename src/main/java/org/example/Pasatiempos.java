package org.example;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.bson.Document;
public class Pasatiempos {
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JLabel nombApe;
    private JLabel pasatiempos;
    private JLabel descPas;
    public JPanel aplicacion;
    private JButton InsertarDatos;
    private JButton RevisarDatos;
    public Pasatiempos() {
        InsertarDatos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombreApellido = textField1.getText();
                String pasatiempos = textField2.getText();
                String descPasatiempos = textField3.getText();
                insertarDatos(nombreApellido, pasatiempos, descPasatiempos);
                JOptionPane.showMessageDialog(null, "Datos insertados exitosamente");
                textField1.setText("");
                textField2.setText("");
                textField3.setText("");
            }
        });
        RevisarDatos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame=new JFrame("Ver los datos de pasatiempos");
                frame.setContentPane(new RevisarDatos().verPasatiempos);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(500,500);
                frame.setLocationRelativeTo(null);
                frame.pack();
                frame.setVisible(true);
            }
        });
    }

    public static void insertarDatos(String nombreApellido, String pasatiempos, String descPasatiempos){
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017"); //Cambiar link al de Atlas
        MongoDatabase database = mongoClient.getDatabase("mydb2"); //Base
        MongoCollection<Document> collection = database.getCollection("pasatiempos"); //Coleccion

        Document newDocumento = new Document()
                .append("Nombre y Apellido", nombreApellido)
                .append("Pasatiempos", pasatiempos)
                .append("Descripción Pasatiempos", descPasatiempos);

        collection.insertOne(newDocumento);
    }
}
