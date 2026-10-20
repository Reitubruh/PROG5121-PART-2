/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapp;

import java.util.Scanner;
import java.util.regex.Pattern;

/**
 *
 * @author Student
 */
public class Login {

    Scanner input = new Scanner(System.in);
    private static final Pattern CELL_PATTERN = Pattern.compile("^^\\+\\d{1,3}\\d{1,10}$");
    private String UserName;
    private String Password;
    private String CellPhoneNumber;
    private String FirstName;
    private String LastName;

    public Login() {
        this("", "");
    }

    public Login(String FirstName, String LastName) {
        this(FirstName, LastName, "", "", "");
    }

    public Login(String FirstName, String LastName, String UserName, String Password, String CellPhoneNumber) {
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.UserName = UserName;
        this.Password = Password;
        this.CellPhoneNumber = CellPhoneNumber;
    }

    public boolean CheckUserName() {
        return CheckUserName(UserName);
    }

    public boolean checkPasswordComplexity() {
        return checkPasswordComplexity(Password);
    }

    public boolean checkCellPhoneNumber() {
        return checkCellPhoneNumber(CellPhoneNumber);
    }

    public boolean CheckUserName(String enteredUserName) {
        return enteredUserName.contains("") && enteredUserName.length() <= 5;

    }

    public boolean checkPasswordComplexity(String enteredPassword) {
        boolean hasCapital = false;
        boolean hasNumber = false;
        boolean hasSpecialCharacter = false;

        if (enteredPassword.length() < 8) {
            return false;
        }
        for (int i = 0; i < enteredPassword.length(); i++) {
            char currentCharacter = enteredPassword.charAt(i);

            if (Character.isUpperCase(currentCharacter)) {
                hasCapital = true;
            } else if (Character.isDigit(currentCharacter)) {
                hasNumber = true;
            } else if (Character.isLetterOrDigit(currentCharacter)) {
                hasSpecialCharacter = true;
            }

        }
        return hasCapital && hasNumber && hasSpecialCharacter;

    }

    public boolean checkCellPhoneNumber(String enteredCellPhoneNumber) {
        String cellPhoneRegex = "\\+27\\d{9}$";

        return enteredCellPhoneNumber.matches(cellPhoneRegex);
    }

    public String getUserNameMessage(String enteredUserName) {
        if (CheckUserName(enteredUserName)) {

            return "Username succesfully captured.";
        } else {
            return "Username is not correctly formatted;please ensure that your username contains an underscore and is no more than five characters in length";
        }
    }

    public String getPasswordMessage(String enteredPassword) {
        if (checkPasswordComplexity(enteredPassword)) {
            return "Password successfully captured.";
        } else {
            return "Password is not correctly formatted;please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }
    }

    public String getCellPhoneNumberMessage(String enteredCellPhoneNumber) {
        if (checkCellPhoneNumber(enteredCellPhoneNumber)) {
            return "Cell phone number successfully captured.";
        } else {
            return "Cell phone number incorrectly formatted or does not contain international code.";
        }
    }

    public String registerUser() {
        if (!CheckUserName()) {
            return getUserNameMessage(UserName);
        }

        if (!checkPasswordComplexity()) {
            return getPasswordMessage(Password);
        }

        if (!checkCellPhoneNumber()) {
            return getCellPhoneNumberMessage(CellPhoneNumber);
        }

        return "You have been successfully registered";
    }

    public String registerUser(String enteredUserName, String enteredPassword, String enteredCellPhoneNumber) {
        if (!CheckUserName(enteredUserName)) {
            return getUserNameMessage(enteredUserName);
        }

        if (!checkPasswordComplexity()) {
            return getPasswordMessage(enteredPassword);
        }

        if (!checkCellPhoneNumber()) {
            return getCellPhoneNumberMessage(enteredCellPhoneNumber);
        }

        UserName = enteredUserName;
        Password = enteredPassword;
        CellPhoneNumber = enteredCellPhoneNumber;

        return registerUser();
    }

    public boolean LogInUser(String enteredUserName, String enteredPassword) {
        return !UserName.isEmpty()
                && UserName.equals(enteredUserName)
                && Password.equals(enteredPassword);
    }

    public String ReturnLogInStatus(boolean LoggedIn) {
        if (LoggedIn) {

            return "Welcome" + FirstName + "" + LastName + ", it is great to see you again";

        } else {
            return "Username or password is incorrect.";
        }
    }

    public String ReturnLogInStatus(String enteredUserName, String enteredPassword) {
        return ReturnLogInStatus(LogInUser(enteredUserName, enteredPassword));
    }

    public String UserDetails() {
        return "First name: " + FirstName 
                + "\nLast name: " + LastName
                + "\nUsername: " + UserName
                + "\nPassword: " + "*".repeat(Password.length())
                + "\nCellPhoneNumber: " + CellPhoneNumber;
    }
}
