package com.xieyv.lc4j.config;

import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.openai.OpenAiEmbeddingModel;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class AssistantContentRetriever {

    //@Autowired
    //EmbeddingModel embeddingModel;

    @Bean
    public ContentRetriever contentRetrieverLc() {
        //加载->默认解析
        Document document_d = FileSystemDocumentLoader.loadDocument("F:/zone/knowledge/科室信息.md");
        Document document_n = FileSystemDocumentLoader.loadDocument("F:/zone/knowledge/神经内科.md");
        Document document_h = FileSystemDocumentLoader.loadDocument("F:/zone/knowledge/医院信息.md");
        List<Document> list = Arrays.asList(document_d, document_n, document_h);
        //内存向量存储
        InMemoryEmbeddingStore<TextSegment> embeddingStore = new InMemoryEmbeddingStore<>();
        //默认文档分割器
        EmbeddingStoreIngestor.ingest(list, embeddingStore);
        /*
        EmbeddingStoreIngestor ingestor = EmbeddingStoreIngestor.builder()
                .embeddingModel(embeddingModel)
                .embeddingStore(embeddingStore)
                .build();
        ingestor.ingest(list);
        */
        //从嵌入存储检索和查询内容信息
        return EmbeddingStoreContentRetriever.from(embeddingStore);
    }
}
