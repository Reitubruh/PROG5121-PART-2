/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.chatapp;
import java.util.Scanner;
/**
 *
 * @author Student
 */
public class ChatApp {
    

    public static void main(String[] args) {
        Scanner input;
        input = new Scanner(System.in);
        
        Login userLogin = register(input);
        
        if(!Login(input, userLogin)){
            System.out.println("You must log in or register successfully to send messages.");
            input.close();
            return;
        }
        
        QuickChat(input);
        input.close();
    
        
        
        
        
    }
    private static Login register(Scanner input){
        
        System.out.println("==============================");
        System.out.println("      CHATAPP REGISTRATION    ");
        System.out.println("==============================");
        
        
        System.out.println("Enter your First Name: ");
        String FirstName = input.nextLine();
        
        System.out.println("Enter your Last Name: ");
        String LastName = input.nextLine();
        
        
        
        Login userLogin = new Login(FirstName, LastName);
        
        String UserName;
        do{
            System.out.println("Enter a Username: ");
            UserName = input.nextLine();
            System.out.println(">>>" + userLogin.getUserNameMessage(UserName));
        }while (!userLogin.CheckUserName(UserName));
        
        
        String Password;
        do{
            System.out.println("Enter a Password: ");
            Password = input.nextLine();
            System.out.println(">>>" + userLogin.getPasswordMessage(Password));
        }while(!userLogin.checkPasswordComplexity(Password));
        
        
        String CellPhoneNumber;
        do{
            System.out.println("Enter South African Cell phone number: ");
            CellPhoneNumber = input.nextLine();
            System.out.println(">>>" + userLogin.getCellPhoneNumberMessage(CellPhoneNumber));
        }while(!userLogin.checkCellPhoneNumber(CellPhoneNumber));
        
        System.out.println("\n>>> Registration Result: " + userLogin.registerUser(UserName, Password, CellPhoneNumber));
        System.out.println("\n>>> Registered Details <<<");
        System.out.println(userLogin.UserDetails());
        return userLogin;
    }
    
    private static boolean Login(Scanner input, Login userLogin){
        
        System.out.println("==============");
        System.out.println("     LOGIN    ");
        System.out.println("==============");
        
        System.out.println("Enter your Username: ");
        String loginUser = input.nextLine();
        
        System.out.println("Enter your Password: ");
        String loginPass = input.nextLine();
        
        boolean authStatus = userLogin.LogInUser(loginUser, loginPass);
        
        System.out.println("==============");
        System.out.println("  LOGIN STATUS  " + userLogin.ReturnLogInStatus(authStatus));
        System.out.println("===============");
        
        if(authStatus){
            System.out.println("\n>>> Account Details <<<");
            System.out.println(userLogin.UserDetails());
        }
        return authStatus;
    }    
                
                
    
    
    private static void QuickChat(Scanner input){
        
        
        System.out.println("\n   WELCOME TO CHATAPP.");
        int Limit = readInt(input, "How many messages do you wish to enter?");
        int entered = 0;
        boolean running = true;
        
        while(running){
            System.out.println("Option 1) Send Messages.");
            System.out.println("Option 2) Show recently sent messages.");
            System.out.println("Option 3) Quit.");
            
            int option = readInt(input, "Choose an option: ");
            
            switch (option) {
                case 1 -> {
                    if(entered<Limit){
                        sendMessage(input, entered);
                        if (entered == Limit) {
                            System.out.println("\nAll " + Limit + " messages have been entered. Total number of messages sent: " + Message.returnTotalMessages());
                            
                        }
                    }else{
                        System.out.println("You have already entered the " + Limit + " messages you set at the start.");
                    }
                }
                case 2 -> System.out.println("Coming Soon.");
                case 3 -> running = false;
                default -> System.out.println("Invalid option, please enter 1, 2 or 3.");
            }
        }
 
        System.out.println("\nTotal number of messages sent: " + Message.returnTotalMessages());
    }
 
    
    private static void sendMessage(Scanner input, int messageNumber) {
        String recipient;
        do{
            System.out.print("Recipient cell number: ");
            recipient = input.nextLine();
            System.out.println(Message.checkRecipientCell(recipient));
        }while (!Message.isRecipientValid(recipient));
 
        String text;
        do{
            System.out.print("Enter message: ");
            text = input.nextLine();
            if(!Message.isMessageLengthValid(text)) {
                System.out.println("Please enter a message of less than 250 characters.");
                System.out.println(Message.checkMessageLength(text));
            }
        }while (!Message.isMessageLengthValid(text));
        System.out.println(Message.checkMessageLength(text));
 
        Message message = new Message(messageNumber, recipient, text);
        System.out.println(message.messageIdGenerated());
 
        System.out.println("\n1) Send Message");
        System.out.println("2) Disregard Message");
        System.out.println("3) Store Message to send later");
        int choice = readInt(input, "Choose: ");
        System.out.println(message.SentMessage(choice));
 
        if(choice == 2) {
            while(readInt(input, "") != 0) {
                System.out.println("Press 0 to delete the message.");
            }
            System.out.println("Message deleted.");
        }else if (choice == 1) {
            System.out.println("\n" + message.details());
        }
    }
 
    private static int readInt(Scanner input, String prompt) {
        while(true) {
            if(!prompt.isEmpty()) System.out.print(prompt);
            try{
                return Integer.parseInt(input.nextLine().trim());
            } catch(NumberFormatException e) {
                System.out.println("Please enter a number.");
            }
                    
                }
            }
        }
    
                
        
        
          
            
        
          
         
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
               
            
            
            
            
            
            
            
            
        
   
    
    
    























        