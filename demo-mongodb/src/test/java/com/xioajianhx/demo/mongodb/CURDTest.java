package com.xioajianhx.demo.mongodb;

import org.bson.Document;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class CURDTest {

    private static final String DATABASE_NAME = "emmber";
    private MongoClient client;
    private MongoDatabase database;

    @Before
    public void init() {
        System.out.println("init");
        client = new MongoClient("192.168.1.141", 27017);
        database = client.getDatabase(DATABASE_NAME);
    }

    @Test
    public void testCrud() {

        System.out.println("===================test insert================");
        MongoCollection<Document> collection = database.getCollection("users");

        for (int i = 0; i < 1000000; i++) {
            Document document = new Document();
            document.append("username", "admin" + i);
            document.append("password", "123456" + i);
            collection.insertOne(document);
        }

        show(collection);

        Document document = new Document();
        document.append("username", "admin");
        document.append("password", "123456");

        // 删除
        FindIterable<Document> tmp = collection.find();
        BasicDBObject baseBson = new BasicDBObject();

        baseBson.put("_id", tmp.first().get("_id"));
        collection.deleteOne(baseBson);
        show(collection);

        // 更新
        tmp = collection.find();
        baseBson = new BasicDBObject();
        baseBson.put("_id", tmp.first().get("_id"));

        collection.updateOne(tmp.first(), new Document("$set", new Document("name", "haha")));
        show(collection);
    }

    private void show(MongoCollection<Document> collection) {

        FindIterable<Document> data = collection.find();

        MongoCursor<Document> cursor = data.iterator();

        while (cursor.hasNext()) {
            System.out.println(cursor.next());
        }
    }

    @After
    public void close() {
        // client.dropDatabase(DATABASE_NAME);
        client.close();
        System.out.println("close");
    }
}
