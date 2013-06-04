package com.demo.lucene;

import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.LongField;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;

public class Demo {

    public static void main(String[] args) throws IOException {
        Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_43);
        TokenStream ts = analyzer.tokenStream("content", new StringReader("the fox jump over the lazy dog"));
        OffsetAttribute attribute = ts.addAttribute(OffsetAttribute.class);

        ts.reset();
        while (ts.incrementToken()) {
            System.out.println(ts.reflectAsString(true));
            System.out.println(attribute.startOffset() + "-" + attribute.endOffset());

        }
        ts.end();
        ts.close();
    }

    public static void indexAndSearch() throws IOException, ParseException {

        String intput = "/home/tom/Desktop/lucene-4.3.0/docs";
        String output = "data/output";

        String keyword = "lucene";

        // store
        Directory directory = new RAMDirectory();

        Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_43);

        IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_43, analyzer);
        config.setOpenMode(OpenMode.CREATE_OR_APPEND);

        // index
        IndexWriter writer = new IndexWriter(directory, config);
        for (File file : new File(intput).listFiles(new FileFilter() {

            @Override
            public boolean accept(File pathname) {
                return pathname.getName().endsWith(".html");
            }
        })) {
            Document doc = new Document();
            doc.add(new StringField("path", file.getAbsolutePath(), Store.YES));
            doc.add(new LongField("modified", file.lastModified(), Store.YES));
            doc.add(new TextField("content", new FileReader(file)));

            writer.addDocument(doc);
        }
        writer.close();

        // search
        IndexReader reader = DirectoryReader.open(directory);
        IndexSearcher searcher = new IndexSearcher(reader);

        QueryParser parser = new QueryParser(Version.LUCENE_43, "content", analyzer);
        Query query = parser.parse(keyword);
        System.out.println(query.toString("content"));

        for (ScoreDoc doc : searcher.search(query, 10).scoreDocs) {
            Document document = searcher.doc(doc.doc);
            System.out.println(document.get("path"));
            System.out.println(document.get("modified"));
            System.out.println(document.get("content"));
        }
        reader.close();
    }
}
