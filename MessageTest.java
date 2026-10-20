/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import com.mycompany.chatapp.Message;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Student
 */
public class MessageTest {
    
    public MessageTest() {
    }
    
    @BeforeAll
    public void setUpClass() {
        Message.resetState();
    }
    
    @AfterAll
    public static void tearDownClass() {
        Message.resetState();
    }
    
    private static final String RECIPIENT1 = "+27718693002";
    private static final String MSG1 = "Hi Mike, can you join us for dinner tonight?";
    private static final String RECIPIENT2 = "08575975889";
    private static final String MSG2 = "Hi Keegan, did you receive the payment?";

    // ---------- assertEquals ----------
    @Test
    public void messageLength_success() {
        assertEquals("Message ready to send.", Message.checkMessageLength(MSG1));
    }

    @Test
    public void messageLength_failure() {
        assertEquals("Message exceeds 250 characters by 10; please reduce the size.",
                Message.checkMessageLength("a".repeat(260)));
    }

    @Test
    public void recipient_success() {
        assertEquals("Cell phone number successfully captured.", Message.checkRecipientCell(RECIPIENT1));
    }

    @Test
    public void recipient_failure() {
        assertEquals("Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.",
                Message.checkRecipientCell(RECIPIENT2));
    }

    @Test
    public void recipient_storedValue() {
        assertEquals("Cell phone number successfully captured.",
                new Message(0, RECIPIENT1, MSG1).checkRecipientCell());
    }

    @Test
    public void hash_testCase1() {
        Message m = new Message("0012345678", 0, RECIPIENT1, MSG1);
        assertEquals("00:0:HITONIGHT", m.createMessageHash());
    }

    @Test
    public void hash_remainderInLoop() {
        String[] recipients = { RECIPIENT1, RECIPIENT2 };
        String[] messages = { MSG1, MSG2 };
        String[] expected = { "00:0:HITONIGHT", "00:1:HIPAYMENT" };

        for (int i = 0; i < messages.length; i++) {
            Message m = new Message("0098765432", i, recipients[i], messages[i]);
            assertEquals(expected[i], m.createMessageHash());
        }
    }

    @Test
    public void messageIdCreated() {
        Message m = new Message("1234567890", 0, RECIPIENT1, MSG1);
        assertEquals("Message ID generated: 1234567890", m.messageIdGenerated());
    }

    @Test
    public void randomMessageIdIsTenCharacters() {
        String text = new Message(0, RECIPIENT1, MSG1).messageIdGenerated();
        String id = text.substring("Message ID generated: ".length());
        assertEquals(10, id.length());
    }

    @Test
    public void sentMessage_send() {
        assertEquals("Message successfully sent.", new Message(0, RECIPIENT1, MSG1).SentMessage(1));
    }

    @Test
    public void sentMessage_disregard() {
        assertEquals("Press 0 to delete the message.", new Message(1, RECIPIENT2, MSG2).SentMessage(2));
    }

    @Test
    public void sentMessage_store() {
        assertEquals("Message successfully stored.", new Message(0, RECIPIENT1, MSG1).SentMessage(3));
    }

    @Test
    public void sentMessage_withText() {
        assertEquals("Message successfully sent.", new Message(0, RECIPIENT1, MSG1).SentMessage("Select Send"));
        assertEquals("Press 0 to delete the message.", new Message(1, RECIPIENT2, MSG2).SentMessage("Select Discard"));
        assertEquals("Message successfully stored.", new Message(0, RECIPIENT1, MSG1).SentMessage("Store Message to send later"));
    }

    // ---------- assertTrue / assertFalse ----------
    @Test
    public void messageIdValid() {
        assertTrue(new Message(0, RECIPIENT1, MSG1).checkMessageID());
    }

    @Test
    public void messageIdTooLong() {
        assertFalse(new Message("12345678901", 0, RECIPIENT1, MSG1).checkMessageID());
    }

    @Test
    public void recipientValidTrue() {
        assertTrue(Message.isRecipientValid(RECIPIENT1));
    }

    @Test
    public void recipientValidFalse() {
        assertFalse(Message.isRecipientValid(RECIPIENT2));
    }

    @Test
    public void messageLengthValid() {
        assertTrue(Message.isMessageLengthValid(MSG1));
        assertFalse(Message.isMessageLengthValid("a".repeat(251)));
    }

    // ---------- Totals (message 1 sent, message 2 discarded) ----------
    @Test
    public void totalMessagesSent_briefSpelling() {
        new Message(0, RECIPIENT1, MSG1).SentMessage(1);
        assertEquals(1, Message.returnTotalMessagess());
    }

    @Test
    public void totalMessagesSent() {
        new Message(0, RECIPIENT1, MSG1).SentMessage(1);
        new Message(1, RECIPIENT2, MSG2).SentMessage(2);
        assertEquals(1, Message.returnTotalMessages());
    }

    @Test
    public void printMessages_listsSentMessage() {
        new Message("1234567890", 0, RECIPIENT1, MSG1).SentMessage(1);
        assertTrue(Message.printMessages().contains("1234567890"));
    }


    @Test
    public void storedMessagesAreKept() {
        new Message(0, RECIPIENT1, MSG1).SentMessage(3);
        new Message(1, RECIPIENT1, MSG2).SentMessage(3);
        assertEquals(2, Message.readStoredMessages().size());
    }

    
    
}