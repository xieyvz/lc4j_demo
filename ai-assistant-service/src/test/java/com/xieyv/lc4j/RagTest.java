package com.xieyv.lc4j;

import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RagTest {
    @Test
    public void loadDocumentTest() {
        Document document = FileSystemDocumentLoader.loadDocument("F:/zone/knowledge/测试.txt");
        System.out.println(document.text());
    }
}