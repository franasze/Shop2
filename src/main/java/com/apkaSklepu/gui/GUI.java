package com.apkaSklepu.gui;

import com.apkaSklepu.core.Authenticator;
import com.apkaSklepu.database.ProductDB;
import com.apkaSklepu.database.UserDB;
import com.apkaSklepu.model.Product;
import com.apkaSklepu.model.User;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.Scanner;

public class GUI {

    private static final Scanner scanner = new Scanner(System.in);
    private static final Authenticator authenticator = Authenticator.getInstance();

    private GUI() {
    }
    public static String showLogMenu(){
        System.out.println("1.Registration");
        System.out.println("2.Login");
        System.out.println("3. Exit");
        return scanner.nextLine();
    }

    public static String showMenu(){
        System.out.println("1. Buy product");
        System.out.println("2. Logout");

        if (authenticator.loggedUser != null &&
                authenticator.loggedUser.getRole().equals(User.Role.ADMIN)) {
            System.out.println("3. Add quantity of product");
            System.out.println("4. Give admin status");
        }
        return scanner.nextLine();
    }

    public static User readLoginAndPassword(){
        User user = new User();
        System.out.println("Login:");
        user.setLogin(scanner.nextLine());
        System.out.println("Password:");
        user.setPassword(DigestUtils.md5Hex(scanner.nextLine() + authenticator.seed));
        return user;
    }
    public static String readName() {
        System.out.println("Product name:");
        return scanner.nextLine();
    }
    public static int readQuantity() {
        System.out.println("Product quantity:");
        return scanner.nextInt();
    }
    public static String readUser(){
        System.out.println("User name:");
        return scanner.nextLine();
    }

    public static void showEffectRegistration(boolean effect){
        if(effect)
            System.out.println("Registered successful");
        else
            System.out.println("login is taken, please try again");
    }
    public static void showProductsList() {
        ProductDB productDB = ProductDB.getInstance();
        System.out.println("Name\t\tPrice\t\t\t Quantity");
        for (Product product : productDB.getProducts())
            System.out.println(product);
        //System.out.println("\n");
    }
    public static void showUsersList(){
        UserDB userDB = UserDB.getInstance();
        System.out.println("Login   Role");
        for (User user : userDB.getUsers()) {
            System.out.println(user);
        }
    }
    public static void showBuyEffect(boolean effect) {
        if (effect) {
            System.out.println("The Products successful bought\n");
        } else {
            System.out.println("The Product doesn't exist \n");
        }
    }
    public static void showChangeQuantityEffect(boolean effect) {
        if (effect) {
            System.out.println("Quantity successful changed\n");
        } else {
            System.out.println("The Product doesn't exist\n");
        }
    }public static void showAdminStatus(boolean effect) {
        if (effect) {
            System.out.println("User is admin now\n");
        } else {
            System.out.println("User doesn't exist or is already admin\n");
        }
    }

}