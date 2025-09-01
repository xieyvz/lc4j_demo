package com.xieyv.lc4j.store;

import com.xieyv.lc4j.entity.DChatMessage;
import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.data.message.ChatMessageDeserializer;
import dev.langchain4j.data.message.ChatMessageSerializer;
import dev.langchain4j.store.memory.chat.ChatMemoryStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class MongoChatMemoryStore implements ChatMemoryStore {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<ChatMessage> getMessages(Object memoryId) {
        DChatMessage dChatMessage = mongoTemplate.findOne(new Query(Criteria.where("memoryId").is(memoryId)), DChatMessage.class);
        if (dChatMessage == null) {
            return new LinkedList<>();
        }
        String content = dChatMessage.getContent();
        return ChatMessageDeserializer.messagesFromJson(content);
    }

    @Override
    public void updateMessages(Object memoryId, List<ChatMessage> list) {
        mongoTemplate.upsert(new Query(Criteria.where("memoryId").is(memoryId)),
                new Update().set("content", ChatMessageSerializer.messagesToJson(list)),
                DChatMessage.class);
    }


    @Override
    public void deleteMessages(Object memoryId) {
        mongoTemplate.remove(new Query(Criteria.where("memoryId").is(memoryId)), DChatMessage.class);
    }
}
