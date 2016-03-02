/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebamongo;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 *
 * @author oracle
 */
public class Metodos {

    MongoClient cliente;
    MongoDatabase db;
    MongoCollection col;

    public void conexion(String database, String coleccion) {
        cliente = new MongoClient("localhost", 27017);
        System.out.println("Conexion a mongo realizada.");

        db = cliente.getDatabase(database);
        System.out.println("Conectado a BD ");

        col = db.getCollection(coleccion);
        System.out.println("Conectado a colleccion scores.");
    }

    public void desconexion() {
        cliente.close();
        System.out.println("Conexion finalizada");

    }

    public void consultar() {
        BasicDBObject query1 = new BasicDBObject("kind", "essay").append("student", new BasicDBObject("$gt", 0).append("$lt", 3));
        BasicDBObject campos = new BasicDBObject();
        campos.put("_id", 0);
        campos.put("kind", 1);
        campos.put("student", 1);

        FindIterable cursor = col.find(query1).projection(campos);
        MongoCursor<Document> it = cursor.iterator();
        System.out.println("Consulta en proceso");

        int i = 1;
        while (it.hasNext()) {
            System.out.println("Documento " + i);
            Document docu = it.next();
            System.out.println(docu);
            //   System.out.println("student:" + docu.get("student") + "\t" + "score:" + docu.get("score") + "\t" + "kind:" + docu.get("kind"));
            i++;
        }
        System.out.println("Consulta finalizada");

        it.close();
    }

    public void insertar() {
        col.insertOne(
                new Document("_id", 1)
                .append("kind", "taller")
                .append("scores", 8000)
                .append("enderezo",
                        new Document()
                        .append("rua", "urzaiz")
                        .append("numero", 7)));

        System.out.println("Inserccion realizada");
    }

    public void actualizar() {
        col.updateOne(
                new Document("_id", 1),
                new Document("$inc",
                        new Document("scores", 2)
                ));
        System.out.println("Actualizacion realizada");
    }

    public void borrar() {
        col.deleteOne(new Document("_id", 1));
        col.deleteOne(new Document("_id",new ObjectId("54f9d465a46jf")));
    }
}
