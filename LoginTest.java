/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.chatapp;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Student
 */
public class LoginTest {
    
    private Login Login;
    
    public LoginTest() {
    }
    
    @BeforeAll
    public void setUpClass() {
        Login = new Login("Kyle", "Smith", "ky1_1", "CH&&sec@ke99!", "+27718693002");
    }
    
    @AfterAll
    public static void tearDownClass() {
    }

    /**
     * Test of main method, of class ChatApp.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        ChatApp.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
        @Test
        public void testLogInCorrect(){
            Login user = new Login("Kyle", "Smith", "ky1_1", "CH&&sec@ke99!", "+27718693002");
            assertEquals("Welcome" + "Kyle" + "" + "Smith" + ", it is great to see you again", user.ReturnLogInStatus("ky1_11","CH&&sec@ke99!"));
        }
        
        @Test
        public void testLogInIncorrect(){
             Login user = new Login("Kyle", "Smith", "ky1_1", "CH&&sec@ke99!", "+27718693002");
             assertEquals ("Username or password is incorrect.", user.ReturnLogInStatus("ky1_11","wrong password"));
           
        }
    
        @Test
        public void testUserNameCorrect(){
        assertEquals("UserName successfully captured.", Login.getUserNameMessage("ky1_1"));
        }
        
        @Test
        public void testUserNameIncorrect(){
            Login user = new Login("Kyle", "Smith", "ky1_1", "CH&&sec@ke99!", "+27718693002");
            assertEquals("Username is not correctly formatted;please ensure that your username contains an underscore and is no more than five characters in length." ,user.getUserNameMessage("kyle!!!!!"));
        }
        
        @Test
        public void testPasswordCorrect(){
            Login user = new Login("Kyle", "Smith", "ky1_1", "CH&&sec@ke99!", "+27718693002");
            assertEquals("Password successfully captured.", user.getPasswordMessage("CH&&sec@ke99"));
        
          }
        
        @Test
        public void testPasswordIncorrect(){
            Login user = new Login("Kyle", "Smith", "ky1_1", "CH&&sec@ke99!", "+27718693002");
            assertEquals("Password is not correctly formatted;please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.", user.getPasswordMessage("password"));
           }
        
        @Test
        public void testCellPhoneNumberCorrect(){
            Login user = new Login("Kyle", "Smith", "ky1_1", "CH&&sec@ke99!", "+27718693002");
            assertEquals("Cell phone number is successfully captured.", user.getCellPhoneNumberMessage("+27718693002"));
       }
        
        @Test
        public void testCellPhoneNumberIncorrect(){
            Login user = new Login("Kyle", "Smith", "ky1_1", "CH&&sec@ke99!", "+27718693002" );
            assertEquals("Cell phone number incorrectly formatted or does not contain international code.", user.getCellPhoneNumberMessage("08966553"));
        }
        
        @Test
        public void testUserNameReturnsTrue(){
            Login user = new Login("Kyle", "Smith", "ky1_1", "CH&&sec@ke99!", "+27718693002");
            assertTrue(user.CheckUserName("ky1_11"));
         }
        
        @Test
        public void testUserNameReturnsFalse(){
            Login user = new Login("Kyle", "Smith", "ky1_1", "CH&&sec@ke99!", "+27718693002");
            assertFalse(user.CheckUserName("Kyle!!!!!!"));
           
        }
        
        @Test
        public void testPasswordReturnsTrue(){
        Login user = new Login("Kyle", "Smith", "ky1_1", "CH&&sec@ke99!", "+27718693002" );
        assertTrue(user.checkPasswordComplexity());
        
        }
        
        @Test
        public void testPasswordReturnsFalse(){
            Login user = new Login("Kyle", "Smith", "ky1_1", "CH&&sec@ke99!", "+27718693002" );
            assertFalse(user.checkPasswordComplexity("password"));
          }
        
        @Test
        public void testCellPhoneNumberReturnsTrue(){
        Login user = new Login("Kyle", "Smith", "ky1_1", "CH&&sec@ke99!", "+27718693002" );
        assertTrue(user.checkCellPhoneNumber("+27718693002"));
       }
        
        @Test
        public void testCellPhoneNumberReturnsFalse(){
            Login user = new Login("Kyle", "Smith", "ky1_1", "CH&&sec@ke99!", "+27718693002" );
            assertFalse(user.checkCellPhoneNumber("08966533"));
         }
        
    
}
