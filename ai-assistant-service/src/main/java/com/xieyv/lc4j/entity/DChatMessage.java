package com.xieyv.lc4j.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
/**
 *  基于mongodb持久化保存记忆
 *  @author xieyv
 *  @apiNote DeepseekChatMessage
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Document("chat_memory_demo")
public class DChatMessage {
    @Id //映射到_id
    private ObjectId messageId; //int64

    private Long memoryId;

    private String content;
}
