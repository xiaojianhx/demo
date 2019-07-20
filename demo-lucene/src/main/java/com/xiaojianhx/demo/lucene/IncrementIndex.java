package com.xiaojianhx.demo.lucene;

import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

public class IncrementIndex {

    public static void main(String[] args) throws Exception {

        String path = "/usr/lucene/index";

        IncrementIndex index = new IncrementIndex();

        ResultSet rs = index.getResult();
        System.out.println("开始建立索引。。。。");
        index.indexBuilding(path, rs);
        index.indexBuilding(path, rs);
    }

    private void indexBuilding(String path, ResultSet rs) throws Exception {

        StandardAnalyzer analyzer = new StandardAnalyzer();

        IndexWriterConfig config = new IndexWriterConfig(analyzer);

        Directory directory = FSDirectory.open(Paths.get(path));

        IndexWriter writer = new IndexWriter(directory, config);

        Document doc = new Document();

        while (rs.next()) {

            doc.add(new Field("fieldname", rs.getString("adsname"), TextField.TYPE_STORED));
        }
        writer.addDocument(doc);
        writer.close();

        ////////////////////////////////////////////////
        System.out.println("建立索引完成，开始搜索。。。。");

        DirectoryReader ireader = DirectoryReader.open(directory);
        IndexSearcher isearcher = new IndexSearcher(ireader);
        QueryParser parser = new QueryParser("fieldname", analyzer);
        Query query = parser.parse("ios");

        ScoreDoc[] hits = isearcher.search(query, 20).scoreDocs;

        for (int i = 0; i < hits.length; i++) {
            Document hitDoc = isearcher.doc(hits[i].doc);
            System.out.println(hitDoc.get("fieldname"));
        }
        ireader.close();
        directory.close();
    }

    private ResultSet getResult() throws Exception {

        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://192.168.1.80:3306/app";
        String userName = "root";
        String password = "123456";
        Connection conn = DriverManager.getConnection(url, userName, password);
        Statement stmt = conn.createStatement();
        return stmt.executeQuery("SELECT * FROM management_adx_ads");
    }
}
