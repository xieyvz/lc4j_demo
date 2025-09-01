package com.xieyv.lc4j;

import com.xieyv.lc4j.assistant.ChatAssistant;
import dev.langchain4j.model.openai.OpenAiChatModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Scanner;

@SpringBootTest
public class LLMTest {
    @Autowired
    private OpenAiChatModel deepseekModel;
    @Autowired
    private ChatAssistant assistant;

    @Test
    public void testGPTDemo() {
        OpenAiChatModel model = OpenAiChatModel.builder()
                .baseUrl("http://langchain4j.dev/demo/openai/v1")
                .apiKey("demo")
                .modelName("gpt-4o-mini")
                .build();

        String chat = model.chat("你是谁");
        System.out.println(chat);

    }


    @Test
    public void testDeepSeekDemo() {
        String chat = deepseekModel.chat("今天是哪天");
        System.out.println(chat);
    }

    @Test
    public void testAssistant() {
        /*
        String chat1 = assistant.chat(1L, "我在测试聊天记忆功能，告诉你个暗号'@azr'");
        System.out.println(chat1);
        String chat2 = assistant.chat(1L, "还记得我们之间的暗号是什么吗");
        System.out.println(chat2);
        String chat3 = assistant.chat(2L, "还记得我们之间的暗号是什么吗？如果不记得的话，请记住'@xyz'，我们的新暗号");
        System.out.println(chat3);
        String chat4 = assistant.chat(2L, "还记得我们之间的暗号是什么吗");
        System.out.println(chat4);
         */
    }

    @Test
    public void testChat() {
        /*
        String chat = assistant.chat(3L, "今天是哪天");
        System.out.println(chat);
         */
    }
}
