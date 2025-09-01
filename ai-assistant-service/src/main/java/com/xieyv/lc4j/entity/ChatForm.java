package com.xieyv.lc4j.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ChatForm {
    @Schema(description = "聊天记忆id")
    private Long memoryId;

    @Schema(description = "用户发送内容")
    private String message;
}
