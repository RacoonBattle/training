package com.demo.lucene;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.LongField;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

public class Demo {

    static Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_43);
    static IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_43, analyzer);

    public static void main(String[] args) throws IOException, ParseException {

        //doIndex();
        doSearch("lisp");
    }

    static void doSearch(String keyword) throws IOException, ParseException {
        IndexReader reader = DirectoryReader.open(FSDirectory.open(new File("data/output")));
        IndexSearcher searcher = new IndexSearcher(reader);

        QueryParser parser = new QueryParser(Version.LUCENE_43, "content", analyzer);
        Query query = parser.parse(keyword);
        System.out.println("query:" + query.toString("content"));

        TopDocs docs = searcher.search(query, 10);

        for (ScoreDoc doc : docs.scoreDocs) {

            Document document = searcher.doc(doc.doc);

            System.out.println("path:" + document.getField("path").stringValue());
            //System.out.println("modified:" + document.getField("modified").stringValue());
            //System.out.println("content:" + document.getField("content").stringValue());
        }

        reader.close();
    }

    static void doIndex() throws IOException {

        config.setOpenMode(OpenMode.CREATE);

        IndexWriter writer = new IndexWriter(FSDirectory.open(new File("data/output")), config);

        File[] files = new File("data/input").listFiles();
        for (File file : files) {
            indexFile(writer, file);
        }

        writer.close();
    }

    static void indexFile(IndexWriter writer, File file) throws IOException {

        Document doc = new Document();

        Field pathField = new StringField("path", file.getPath(), Store.YES);
        doc.add(pathField);
        doc.add(new LongField("modified", file.lastModified(), Store.NO));
        doc.add(new TextField("content", new BufferedReader(new FileReader(file))));

        writer.updateDocument(new Term("path", file.getPath()), doc);
    }
}
