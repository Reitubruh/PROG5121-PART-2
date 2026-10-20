package com.mycompany.chatapp;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
/**
 *
 * @author Student
 */
public final class Message {
    
    private static final Random RANDOM = new Random();
    private static final String STORE_FILE = "messages.json";
 
   
    private static int totalSent = 0;
    private static final List<Message> sentMessages = new ArrayList<>();
 
  
    static class StoredMessage {
        String messageId;
        String messageHash;
        String recipient;
        String message;
 
        StoredMessage(String messageId, String messageHash, String recipient, String message) {
            this.messageId = messageId;
            this.messageHash = messageHash;
            this.recipient = recipient;
            this.message = message;
        }
    }
 
    private final String messageId;
    private final int messageNumber;
    private final String recipient;
    private final String messageText;
    private final String messageHash;
 
    
    public Message(int messageNumber, String recipient, String messageText) {
        this(generateMessageId(), messageNumber, recipient, messageText);
    }
 
   
    public Message(String messageId, int messageNumber, String recipient, String messageText) {
        this.messageId = messageId;
        this.messageNumber = messageNumber;
        this.recipient = recipient;
        this.messageText = messageText;
        this.messageHash = createMessageHash();
    }
 
    public static String generateMessageId() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) sb.append(RANDOM.nextInt(10));
        return sb.toString();
    }
 
    
    public String messageIdGenerated() {
        return "Message ID generated: " + messageId;
    }
 
   
    public boolean checkMessageID() {
        return messageId.length() <= 10;
    }
 
    
    public String checkRecipientCell() {
        return checkRecipientCell(recipient);
    }
 
    public boolean isRecipientValid() {
        return isRecipientValid(recipient);
    }
 
    
    public static String checkRecipientCell(String enteredCell) {
        return isRecipientValid(enteredCell)
                ? "Cell phone number successfully captured."
                : "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
    }
 
    public static boolean isRecipientValid(String enteredCellPhoneNumber) {
        return new Login().checkCellPhoneNumber(enteredCellPhoneNumber);
    }
 
   
    public String checkMessageLength() {
        return checkMessageLength(messageText);
    }
 
    public boolean isMessageLengthValid() {
        return isMessageLengthValid(messageText);
    }
 
    
    public static String checkMessageLength(String enteredText) {
        if (enteredText.length() <= 250) return "Message ready to send.";
        int over = enteredText.length() - 250;
        return "Message exceeds 250 characters by " + over + "; please reduce the size.";
    }
 
    public static boolean isMessageLengthValid(String enteredText) {
        return enteredText.length() <= 250;
    }
 
    
    public String createMessageHash() {
        String[] words = messageText.trim().split("\\s+");
        String first = words[0].replaceAll("[^A-Za-z0-9]", "");
        String last = words[words.length - 1].replaceAll("[^A-Za-z0-9]", "");
        String prefix = messageId.length() >= 2 ? messageId.substring(0, 2) : messageId;
        return (prefix + ":" + messageNumber + ":" + first + last).toUpperCase();
    }
 
   
    public boolean isMessageValid() {
        return isMessageLengthValid();
    }
 
   
    public String SentMessage(int choice) {
        switch (choice) {
            case 1 -> {
                totalSent++;
                sentMessages.add(this);
                return "Message successfully sent.";
            }
            case 2 -> {
                return "Press 0 to delete the message.";
            }
            case 3 -> {
                storeMessage();
                return "Message successfully stored.";
            }
            default -> {
                return "Invalid option.";
            }
        }
    }
 
    
    public String sentMessage(int option) {
        return SentMessage(option);
    }
 
    
    public String SentMessage(String choice) {
        String c = choice.trim().toLowerCase();
        if (c.equals("3") || c.contains("store")) return SentMessage(3);
        if (c.equals("2") || c.contains("disregard") || c.contains("discard")) return SentMessage(2);
        if (c.equals("1") || c.contains("send")) return SentMessage(1);
        return "Invalid option.";
    }
 
    
    public static String printMessages() {
        if (sentMessages.isEmpty()) return "No messages sent.";
        StringBuilder sb = new StringBuilder();
        for (Message m : sentMessages) sb.append(m.details()).append("\n");
        return sb.toString();
    }
 
    
    public static int returnTotalMessages() {
        return totalSent;
    }
 
    
    public static int returnTotalMessagess() {
        return returnTotalMessages();
    }
 
    public String printMessagesDetails() {
        return details();
    }
 
    public String details() {
        return "Message ID: " + messageId
                + "\nMessage Hash: " + messageHash
                + "\nRecipient: " + recipient
                + "\nMessage: " + messageText;
    }
 
    
    public void storeMessage() {
        List<StoredMessage> all = readStoredMessages();
        all.add(new StoredMessage(messageId, messageHash, recipient, messageText));
        try (FileWriter writer = new FileWriter(STORE_FILE)) {
            writer.write(toJson(all));
        } catch (IOException e) {
            System.out.println("Could not write messages.json: " + e.getMessage());
        }
    }
 
    
    public static List<StoredMessage> readStoredMessages() {
        File file = new File(STORE_FILE);
        if (!file.exists()) return new ArrayList<>();
        try (Scanner reader = new Scanner(file)) {
            reader.useDelimiter("\\A");
            String json = reader.hasNext() ? reader.next() : "";
            return fromJson(json);
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }
 
   
    private static String toJson(List<StoredMessage> messages) {
        StringBuilder sb = new StringBuilder("[\n");
        for (int i = 0; i < messages.size(); i++) {
            StoredMessage m = messages.get(i);
            sb.append("  {\n")
              .append("    \"messageId\": \"").append(escape(m.messageId)).append("\",\n")
              .append("    \"messageHash\": \"").append(escape(m.messageHash)).append("\",\n")
              .append("    \"recipient\": \"").append(escape(m.recipient)).append("\",\n")
              .append("    \"message\": \"").append(escape(m.message)).append("\"\n")
              .append("  }").append(i < messages.size() - 1 ? ",\n" : "\n");
        }
        return sb.append("]").toString();
    }
 
    
    private static List<StoredMessage> fromJson(String json) {
        List<String> strings = new ArrayList<>();
        try {
         int i = 0;
        while (i < json.length()) {
         if (json.charAt(i) == '"') {
             StringBuilder sb = new StringBuilder(); i++;
                    while (json.charAt(i) != '"') {
                        char c = json.charAt(i);
                        if (c == '\\') {
                            i++;
                            char n = json.charAt(i);
                            switch (n) {
                                case 'n' -> sb.append('\n');
                                case 'r' -> sb.append('\r');
                                case 't' -> sb.append('\t');
                                case 'u' -> {
                                    sb.append((char) Integer.parseInt(json.substring(i + 1, i + 5), 16));
                                    i += 4;
                                }
                                default -> sb.append(n);
                            }
                        } else {
                            sb.append(c);
                        }
                        i++;
                    }
                    strings.add(sb.toString());
                }
                i++;
            }
        } catch (StringIndexOutOfBoundsException | NumberFormatException e) {
            return new ArrayList<>();
        }
 
        List<StoredMessage> result = new ArrayList<>();
        for (int j = 0; j + 8 <= strings.size(); j += 8) {
            result.add(new StoredMessage(strings.get(j + 1), strings.get(j + 3),
                    strings.get(j + 5), strings.get(j + 7)));
        }
        return result;
    }
 
    private static String escape(String text) {
        return text.replace("\\", "\\\\").replace("\"", "\\\"")
                   .replace("\n", "\\n").replace("\r", "\\r").replace("\t", "\\t");
    }
 
    
    public static void resetState() {
        totalSent = 0;
        sentMessages.clear();
        File file = new File(STORE_FILE);
        if (file.exists()) {
            file.delete();
        }
    }
}
 
    

