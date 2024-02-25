package edu.java.bot;

import com.pengrad.telegrambot.model.Chat;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserMessageProcessorTest {

    static Update update = Mockito.mock(Update.class);
    static Message message = Mockito.mock(Message.class);
    static Chat chat = Mockito.mock(Chat.class);

    @BeforeAll
    static void setup() {
        Mockito.when(update.message()).thenReturn(message);
        Mockito.when(message.chat()).thenReturn(chat);
    }

    @Order(1)
    @Test
    void startTest() {
        Mockito.when(message.chat().id()).thenReturn((long) 1);
        Mockito.when(message.text()).thenReturn("/start");

        SendMessage sendMessage = UserMessageProcessor.process(update);

        SendMessage expected = new SendMessage((long) 1, "Hello, you are registered for the TrackSiteUpdates_bot\n " +
            "For a list of possible commands, enter /help");
        assertEquals(expected.getParameters(), sendMessage.getParameters());
    }

    @Order(2)
    @Test
    void TrackTest1() {
        Mockito.when(message.chat().id()).thenReturn((long) 1);
        Mockito.when(message.text()).thenReturn("/track test/mksstp");

        SendMessage sendMessage = UserMessageProcessor.process(update);

        SendMessage expected = new SendMessage((long) 1, "Link successfully added");
        assertEquals(expected.getParameters(), sendMessage.getParameters());
    }

    @Order(3)
    @Test
    void TrackTest2() {
        Mockito.when(message.chat().id()).thenReturn((long) 1);
        Mockito.when(message.text()).thenReturn("/track test2/mksstp");

        SendMessage sendMessage = UserMessageProcessor.process(update);

        SendMessage expected = new SendMessage((long) 1, "Link successfully added");
        assertEquals(expected.getParameters(), sendMessage.getParameters());
    }

    @Order(4)
    @Test
    void ListTest1() {
        Mockito.when(message.chat().id()).thenReturn((long) 1);
        Mockito.when(message.text()).thenReturn("/list");

        SendMessage sendMessage = UserMessageProcessor.process(update);

        SendMessage expected1 = new SendMessage((long) 1, """
                test/mksstp
                test2/mksstp
                """);
        SendMessage expected2 = new SendMessage((long) 1, """
                test2/mksstp
                test/mksstp
                """);
        Assertions.assertTrue(expected1.getParameters().equals(sendMessage.getParameters()) ||
            expected2.getParameters().equals(sendMessage.getParameters()));
    }

    @Order(5)
    @Test
    void UntrackTest1() {
        Mockito.when(message.chat().id()).thenReturn((long) 1);
        Mockito.when(message.text()).thenReturn("/untrack test/mksstp");

        SendMessage sendMessage = UserMessageProcessor.process(update);

        SendMessage expected = new SendMessage((long) 1, "Link successfully deleted");
        assertEquals(expected.getParameters(), sendMessage.getParameters());
    }

    @Order(6)
    @Test
    void UntrackTest2() {
        Mockito.when(message.chat().id()).thenReturn((long) 1);
        Mockito.when(message.text()).thenReturn("/untrack test3/mksstp");

        SendMessage sendMessage = UserMessageProcessor.process(update);

        SendMessage expected = new SendMessage((long) 1, "No such link");
        assertEquals(expected.getParameters(), sendMessage.getParameters());
    }

    @Order(7)
    @Test
    void ListTest2() {
        Mockito.when(message.chat().id()).thenReturn((long) 1);
        Mockito.when(message.text()).thenReturn("/list");

        SendMessage sendMessage = UserMessageProcessor.process(update);

        SendMessage expected = new SendMessage((long) 1, "test2/mksstp\n");
        assertEquals(expected.getParameters(), sendMessage.getParameters());
    }

    @Order(8)
    @Test
    void TrackTestAnotherUser() {
        Mockito.when(message.chat().id()).thenReturn((long) 2);
        Mockito.when(message.text()).thenReturn("/track test3/mksstp");

        SendMessage sendMessage = UserMessageProcessor.process(update);

        SendMessage expected = new SendMessage((long) 2, "Link successfully added");
        assertEquals(expected.getParameters(), sendMessage.getParameters());
    }

    @Order(9)
    @Test
    void ListTestAnotherUser() {
        Mockito.when(message.chat().id()).thenReturn((long) 2);
        Mockito.when(message.text()).thenReturn("/list");

        SendMessage sendMessage = UserMessageProcessor.process(update);

        SendMessage expected = new SendMessage((long) 2, "test3/mksstp\n");
        assertEquals(expected.getParameters(), sendMessage.getParameters());
    }

    @Order(10)
    @Test
    void UntrackTest3() {
        Mockito.when(message.chat().id()).thenReturn((long) 1);
        Mockito.when(message.text()).thenReturn("/untrack test2/mksstp");

        SendMessage sendMessage = UserMessageProcessor.process(update);

        SendMessage expected = new SendMessage((long) 1, "Link successfully deleted");
        assertEquals(expected.getParameters(), sendMessage.getParameters());
    }

    @Order(11)
    @Test
    void ListTest3() {
        Mockito.when(message.chat().id()).thenReturn((long) 1);
        Mockito.when(message.text()).thenReturn("/list");

        SendMessage sendMessage = UserMessageProcessor.process(update);

        SendMessage expected = new SendMessage((long) 1, "No tracking links");
        assertEquals(expected.getParameters(), sendMessage.getParameters());
    }

    @Order(12)
    @Test
    void HelpTest() {
        Mockito.when(message.chat().id()).thenReturn((long) 1);
        Mockito.when(message.text()).thenReturn("/help");

        SendMessage sendMessage = UserMessageProcessor.process(update);

        assertEquals((long) 1, sendMessage.getParameters().get("chat_id"));
    }

}
