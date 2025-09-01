package com.xieyv.lc4j.controller;

import com.xieyv.lc4j.assistant.ChatAssistant;
import com.xieyv.lc4j.entity.ChatForm;
import com.xieyv.lc4j.entity.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@Tag(name = "ai助手")
@RestController
@RequestMapping("/ai")
public class ChatController {
    @Autowired
    ChatAssistant aiChat;

    @Operation(summary = "聊天功能")
    @PostMapping(value = "/chat", produces = "text/stream;charset=utf-8")
    @ResponseBody
    public Flux<String> chat(@RequestBody ChatForm chatForm) {
        return aiChat.chat(chatForm.getMemoryId(), chatForm.getMessage());
    }
    /*
    @Operation(summary = "聊天功能（返回json）")
    @PostMapping("/chatJson")
    @ResponseBody
    public Result<String> chatJson(@RequestBody ChatForm chatForm) {
        String chat = aiChat.chat(chatForm.getMemoryId(), chatForm.getMessage());
        return Result.ok(chat);
    }
    */

}
