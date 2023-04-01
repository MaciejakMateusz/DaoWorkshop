package pl.coderslab.entity;

import pl.coderslab.dao.UserDao;
import java.sql.SQLException;
import java.util.Scanner;

public class UserMain {
    static final UserDao DAO = new UserDao();
    public static void main(String[] args) throws SQLException {
        mainMenu();
    }

    private static void mainMenu() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Main menu");
        System.out.println("Choose one of the following commands:"); 
        System.out.println("create");
        System.out.println("read");
        System.out.println("update");
        System.out.println("delete");
        System.out.println("exit" + "\n");

        boolean correctChoice = false;
        while(!correctChoice) {
            String choice = scanner.nextLine();
            switch (choice) {
                case "create":
                    createUser();
                    correctChoice = true;
                    break;
                case "read":
                    readDatabase();
                    correctChoice = true;
                    break;
                case "update":
                    updateUser();
                    correctChoice = true;
                    break;
                case "delete":
                    deleteUser();
                    correctChoice = true;
                    break;
                case "exit":
                    System.out.println("Program ended");
                    System.exit(0);
                default:
                    System.out.println("Wrong command, try again:");
                    break;
            }
        }
    }

    private static void createUser() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        User user = new User();

        System.out.print("Enter email: ");
        boolean correctEmail = false;
        while (!correctEmail) {
            String email = scanner.nextLine();
            if (email.contains("@")) {
                user.setEmail(email);
                correctEmail = true;
            } else {
                System.out.print("Wrong email, try again: ");
            }
        }

        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        user.setUserName(username);

        boolean correctPasswords = false;
        while (!correctPasswords) {
            System.out.print("Enter password: ");
            String password = scanner.nextLine();
            System.out.print("Confirm password: ");
            String confirmPassword = scanner.nextLine();

            if (password.equals(confirmPassword)) {
                user.setPassword(confirmPassword);
                correctPasswords = true;
            } else {
                System.out.println("Passwords are not the same, try again.");
            }
        }
        DAO.create(user);
        System.out.println("User created successfully.");
        mainMenu();
    }

    private static void readDatabase() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Reading the database function.");
        System.out.println("Enter one of the following commands:");
        System.out.println("read-all");
        System.out.println("id");
        System.out.println("email");
        System.out.println("username");
        System.out.println("back" + "\n");

        boolean correctChoice = false;
        while (!correctChoice) {
            String choice = scanner.nextLine();
            switch (choice) {
                case "read-all":
                    DAO.printAll();
                    correctChoice = true;
                    break;
                case "id":
                    readFromId();
                    correctChoice = true;
                    break;
                case "email":
                    System.out.println(readFromEmail());
                    correctChoice = true;
                    break;
                case "username":
                    System.out.println(readFromUsername());
                    correctChoice = true;
                    break;
                case "back":
                    mainMenu();
                    break;
                default:
                    System.out.println("Wrong command, try again:");
                    break;
            }
        }
        System.out.println();
        mainMenu();
    }

    private static void updateUser() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter id of the user you wish to update.");
        User user = readFromId();
        System.out.println("What data should be updated? "
                + " Enter one of the following:");
        System.out.println("email");
        System.out.println("username");
        System.out.println("password" + "\n");

        boolean correctChoice = false;
        while(!correctChoice) {
            String choice = scanner.nextLine();
            switch (choice) {
                case "email":
                    updateEmail(user);
                    correctChoice = true;
                    break;
                case "username":
                    updateUsername(user);
                    correctChoice = true;
                    break;
                case "password":
                    updatePassword(user);
                    correctChoice = true;
                    break;
                default:
                    System.out.print("Wrong command, try again: ");
                    break;
            }
        }


    }

    private static void updateEmail(User user) throws SQLException {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter new email address: ");
        boolean correctEmail = false;
        while (!correctEmail) {
            String email = scanner.nextLine();
            if (email.contains("@")) {
                user.setEmail(email);
                DAO.update(user);
                System.out.println("Email successfully updated.");
                DAO.readEmail(email);
                correctEmail = true;
            } else {
                System.out.print("Wrong email, try again: ");
            }
        }
        System.out.println();
        mainMenu();
    }

    private static void updateUsername(User user) throws SQLException {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter new username: ");
        String username = scanner.nextLine();
        user.setUserName(username);
        DAO.update(user);
        System.out.println("Username successfully updated." + "\n");
        DAO.readUsername(username);
        mainMenu();
    }

    private static void updatePassword(User user) throws SQLException {

        Scanner scanner = new Scanner(System.in);
        boolean correctPasswords = false;
        while (!correctPasswords) {
            System.out.print("Enter new password: ");
            String password = scanner.nextLine();
            System.out.print("Enter password again for confirmation: ");
            String confirmPassword = scanner.nextLine();

            if (password.equals(confirmPassword)) {
                user.setPassword(confirmPassword);
                DAO.update(user);
                System.out.println("Password successfully updated.");
                correctPasswords = true;
            } else {
                System.out.println("Passwords are not the same, try again.");
            }
        }
        System.out.println();
        mainMenu();
    }

    private static void deleteUser() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Delete user from database function");
        System.out.print("Enter id of the user to delete: ");
        boolean correctId = false;
        while (!correctId) {
            while (!scanner.hasNextInt()) {
                System.out.print("Wrong id, try again: ");
                scanner.next();
            }
            int idInput = scanner.nextInt();
            if (idInput > 0) {
                DAO.delete(idInput);
                System.out.println("User deleted successfully.");
                correctId = true;
            } else {
                System.out.print("Wrong id, try again: ");
            }
        }
        System.out.println();
        mainMenu();
    }

    private static User readFromId() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter id: ");


            while (!scanner.hasNextInt()) {
                System.out.print("Wrong id, try again: ");
                scanner.next();
            }

            int idInput = scanner.nextInt();
            if (idInput > 0) {
                System.out.println(DAO.read(idInput));
                return DAO.read(idInput);
            } else {
                System.out.print("Wrong id, try again: ");
                return readFromId();
            }
    }

    private static User readFromUsername() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter username: ");
        String usernameInput = scanner.nextLine();
            return DAO.readUsername(usernameInput);
        }
    private static User readFromEmail() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter email: ");
        String emailInput = scanner.nextLine();
        return DAO.readEmail(emailInput);
    }
}