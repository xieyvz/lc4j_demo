package com.xieyv.lc4j;

import com.xieyv.lc4j.entity.DChatMessage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

@SpringBootTest
public class MongodbTest {
    @Autowired
    MongoTemplate mongoTemplate;

    @Test
    public void linkedTest() {
        DChatMessage dChatMessage = new DChatMessage();
        dChatMessage.setContent("{\"type\": \"测试1\", \"msg\": \"测试msg\"}");
        mongoTemplate.insert(dChatMessage);
    }

    @Test
    public void findTest() {
        DChatMessage dChatMessage = mongoTemplate.findById("689ffabcbcb2ab4fe6e71c7f", DChatMessage.class);
        System.out.println(dChatMessage);
    }

    @Test
    public void updateTest() {
        Criteria criteria = Criteria.where("_id").is("fsaf21abcbcb2ab445211c7f");
        Query query = new Query(criteria);
        Update update = new Update().set("content", "{\"type\": \"测试2\", \"msg\": \"测试msg\"}");
        mongoTemplate.upsert(query, update, DChatMessage.class);
    }

    @Test
    public void deleteTest() {
        mongoTemplate.remove(new Query(Criteria.where("_id").is("fsaf21abcbcb2ab445211c7f")), DChatMessage.class);
    }
}
