package Programa;

import javax.swing.*;
import java.util.ArrayList;

public class Market {
    // Array para lista de usuarios registrados *JA INCLUIDO O USER ADMIN/ADMIN*
    static ArrayList<User> userAccounts;

    static ArrayList<RegisterProduct> productList;

    static ArrayList<RegisterProduct> cart = new ArrayList<>();

    public static void main(String[] args) {
        userAccounts = new ArrayList<User>();

        productList = new ArrayList<RegisterProduct>();

        // Sempre mantendo o login do user Admin
        User adminUser = new User("Admin", "admin", "admin");
        userAccounts.add(adminUser);

        userOptions();
    }
    // Opcoes de login *Primeira interface*
    public static void userOptions() {

        int userOption = Integer.parseInt(JOptionPane.showInputDialog(
                "----------------Market----------------\n" +
                        "1 - Login\n" +
                        "2 - Register\n" +
                        "3 - Exit"
        ));

        switch (userOption) {
            case 1:
                String email = JOptionPane.showInputDialog("Email:");
                String password = JOptionPane.showInputDialog("Password:");
                login(email, password);
                break;
            case 2:
                register();
                break;
            case 3:
                JOptionPane.showMessageDialog(null, "Thanks for using our market!");
                System.exit(0);
            default:
                JOptionPane.showMessageDialog(null, "We can't find that option. Please try again.");
                userOptions();
        }
    }
    // Opcao de login para o Admin
    public static void loggedOptionsA() {
        int adminOption = Integer.parseInt(JOptionPane.showInputDialog(
                """
                        ------Your welcome Admin!------
                        1 - Register a Product
                        2 - Remove a Product
                        3 - List Products
                        4 - Users List
                        5 - Remove an User Account
                        6 - Logout
                        7 - Exit"""
        ));
        switch (adminOption) {
            case 1: registerProduct();
            case 2: removeProduct();
            case 3: listProducts();
            case 4: listUsers();
            case 5: removeUser();
            case 6:
                JOptionPane.showMessageDialog(null, "Good bye Admin!");
                userOptions();
            case 7: System.exit(0);
        }
    }
    //Opcao de login para Clientes
    public static void loggedOptionsM() {
        int memberOption = Integer.parseInt(JOptionPane.showInputDialog(
                """
                        ------Your Welcome!------
                        1 - List Products
                        2 - Buy Products
                        3 - View Cart
                        4 - Logout
                        5 - Exit
                        """
        ));
        switch (memberOption) {
            case 1: listProductsM();
            case 2: buyProduct();
            case 3: viewCart();
            case 4: userOptions();
            case 5: System.exit(0);
        }
    }
    // Registrando contas
    public static void register() {
        User newUser = new User();
        newUser.setName(JOptionPane.showInputDialog("Name: "));
        newUser.setEmail(JOptionPane.showInputDialog("Email:"));
        newUser.setPassword(JOptionPane.showInputDialog("Password:"));
        // Verificando se o email registrado ja existe no Array
        boolean eAccount = false;
        for (User existingUser : userAccounts) {
            if (existingUser.getEmail().equals(newUser.getEmail())) {
                eAccount = true;
                break;
            }
        }

        if (eAccount) {
            JOptionPane.showMessageDialog(null, "Account already exists!");
            userOptions();
        } else {
            userAccounts.add(newUser);
            loggedOptionsM();
        }
    }
    // Logando em contas ja registradas no Array
    public static void login(String email, String password) {
        // Verificação para saber se o user que esta logando é admin
        boolean isAdmin = email.equals("admin") && password.equals("admin");
        if (isAdmin) {
            JOptionPane.showMessageDialog(null, "Login successful!");
            loggedOptionsA();
            return;
        }
        // Verificacao para saber se o user existe no array de users
        boolean found = false;
        for (User user : userAccounts) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                found = true;
                break;
            }
        }

        if (found) {
            JOptionPane.showMessageDialog(null, "Login successful!");
            loggedOptionsM();
        } else {
            JOptionPane.showMessageDialog(null, "Invalid email or password. Please try again.");
            userOptions();
        }
    }
    // Lista os produtos ja registrados pelo ADMIN
    public static void listProducts() {
        if (productList.size() > 0) {
            StringBuilder message = new StringBuilder();
            for (RegisterProduct product : productList) {
                message.append("Id: ").append(product.getId()).append("\n");
                message.append("Product: ").append(product.getProduct()).append("\n");
                message.append("Price: ").append(product.getPrice()).append("\n\n");
            }
            JOptionPane.showMessageDialog(null, message.toString());
        } else {
            JOptionPane.showMessageDialog(null, "There are no products registered");
        }
        loggedOptionsA();
    }
    public static void listProductsM() {
        if (productList.size() > 0) {
            StringBuilder message = new StringBuilder();
            for (RegisterProduct product : productList) {
                message.append("Id: ").append(product.getId()).append("\n");
                message.append("Product: ").append(product.getProduct()).append("\n");
                message.append("Price: ").append(product.getPrice()).append("\n\n");
            }
            JOptionPane.showMessageDialog(null, message.toString());
        } else {
            JOptionPane.showMessageDialog(null, "There are no products registered");
        }
        loggedOptionsM();
    }
    // Lista todos os Usuarios registrados
    public static void listUsers() {
        if (userAccounts.size() > 0) {
            StringBuilder message = new StringBuilder();
            for (User user : userAccounts) {
                message.append("Name: ").append(user.getName()).append("\n");
                message.append("Email: ").append(user.getEmail()).append("\n");
                message.append("Password: ").append(user.getPassword()).append("\n\n");
            }
            JOptionPane.showMessageDialog(null, message.toString());
        } else {
            JOptionPane.showMessageDialog(null, "No accounts found!");
        }
        loggedOptionsA();
    }
    // Registra um produto novo
    public static void registerProduct() {
        String productName = JOptionPane.showInputDialog("Product name: ");
        double price = Double.parseDouble(JOptionPane.showInputDialog("Price: "));

        RegisterProduct product = new RegisterProduct(productName, price);

        productList.add(product);
        JOptionPane.showMessageDialog(null, "Product successfully added");
        loggedOptionsA();
    }
    // Remover um produto da lista de produtos apartir do ID
    public static void removeProduct() {
        int productId = Integer.parseInt(JOptionPane.showInputDialog("Enter the ID of the product to remove:"));

        boolean productFound = false;
        for (RegisterProduct product : productList) {
            if (product.getId() == productId) {
                productList.remove(product);
                productFound = true;
                break;
            }
        }

        if (productFound) {
            JOptionPane.showMessageDialog(null, "Product removed!");
        } else {
            JOptionPane.showMessageDialog(null, "Product not found!");
        }

        loggedOptionsA();
    }
    // Remover um usuario apartir do Email registrado
    public static void removeUser() {
        String userEmail = JOptionPane.showInputDialog("Enter the email of the user to remove:");

        boolean userFound = false;
        for (User user : userAccounts) {
            if (user.getEmail().equals(userEmail)) {
                userAccounts.remove(user);
                userFound = true;
                break;
            }
        }

        if (userFound) {
            JOptionPane.showMessageDialog(null, "User account removed!");
        } else {
            JOptionPane.showMessageDialog(null, "User account not found!");
        }
        loggedOptionsA();
    }
    // Comprando um produto
    public static void buyProduct() {
        int productId = Integer.parseInt(JOptionPane.showInputDialog("Product ID:"));

        boolean productFound = false;
        for (RegisterProduct product : productList) {
            if (product.getId() == productId) {
                cart.add(product);
                productFound = true;
                break;
            }
        }

        if (productFound) {
            JOptionPane.showMessageDialog(null, "Product added to cart!");
        } else {
            JOptionPane.showMessageDialog(null, "Product not found!");
        }

        loggedOptionsM();
    }
    // Conferindo o carrinho de compras
    public static void viewCart() {
        if (cart.size() > 0) {
            StringBuilder message = new StringBuilder("Cart:\n\n");
            for (RegisterProduct product : cart) {
                message.append("Product: ").append(product.getProduct()).append("\n");
                message.append("Price: ").append(product.getPrice()).append("\n\n");
            }
            JOptionPane.showMessageDialog(null, message.toString());
        } else {
            JOptionPane.showMessageDialog(null, "The cart is empty!");
        }

        loggedOptionsM();
    }
}
/*  CODE WRITTEN BY SAMUEL BOZZA FROM https://github.com/SamuelBozza */