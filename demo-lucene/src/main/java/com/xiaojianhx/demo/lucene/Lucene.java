package com.xiaojianhx.demo.lucene;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
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

import com.xiaojianhx.utils.IOUtils;

public final class Lucene {

    public static String PATH = "/usr/lucene/index";

    private static Analyzer analyzer = new SmartChineseAnalyzer();

    public static void buildIndex(List<String> data) {

        IndexWriter writer = null;
        Directory directory = null;

        try {

            directory = FSDirectory.open(Paths.get(PATH));

            IndexWriterConfig config = new IndexWriterConfig(analyzer);

            writer = new IndexWriter(directory, config);
            writer.deleteAll();

            Document doc = new Document();

            data.forEach(item -> doc.add(new TextField("fieldname", item, Field.Store.YES)));
            writer.addDocument(doc);
            doc.clear();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtils.close(writer);
            IOUtils.close(directory);
        }
    }

    public static List<String> search(String string) {

        List<String> result = new ArrayList<>();
        DirectoryReader reader = null;
        Directory directory = null;
        try {

            directory = FSDirectory.open(Paths.get(PATH));

            reader = DirectoryReader.open(directory);

            IndexSearcher searcher = new IndexSearcher(reader);

            QueryParser parser = new QueryParser("fieldname", analyzer);
            Query query = parser.parse(string);

            ScoreDoc[] hits = searcher.search(query, 100000000).scoreDocs;

            for (int i = 0; i < hits.length; i++) {
                Document hitDoc = searcher.doc(hits[i].doc);
                result.add(hitDoc.get("fieldname"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            IOUtils.close(directory);
            IOUtils.close(reader);
        }

        return result;
    }
}