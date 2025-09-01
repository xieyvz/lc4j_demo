package com.xieyv.lc4j.assistant;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.spring.AiService;
import dev.langchain4j.service.spring.AiServiceWiringMode;
import reactor.core.publisher.Flux;

import java.util.Date;

@AiService(wiringMode = AiServiceWiringMode.EXPLICIT,
        //chatModel = "openAiChatModel",
        streamingChatModel = "openAiStreamingChatModel",
        chatMemory = "chatMemory",
        chatMemoryProvider = "chatMemoryProvider",
        tools = "nativeTools",
        contentRetriever = "contentRetrieverLc")
public interface ChatAssistant {
    @SystemMessage(fromResource = "prompt-word-template.txt")    //修改系统消息会导致失忆
    Flux<String> chat(@MemoryId Long memoryId, @UserMessage String msg);
}