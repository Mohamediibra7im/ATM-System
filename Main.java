import java.io.*;
import java.util.*;

public class Main {

    static final String DATA_FILE = "accounts.json";

    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        HashMap<String, Account> accounts = loadAccounts();

        System.out.println("*******WELCOME TO BANK OF HSBC*******");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.print("Choose option (1 or 2): ");
        String option = input.nextLine();

        Account acc = null;

        if (option.equals("1")) {
            System.out.print("Enter your name: ");
            String name = input.nextLine();

            String accountNumber;
            while (true) {
                System.out.print("Enter your account number: ");
                accountNumber = input.nextLine();
                if (!accountNumber.isEmpty() && accountNumber.matches("\\d+")) {
                    if (!accounts.containsKey(accountNumber)) {
                        break; 
                    }else {
                        System.out.println("Account number already exists.");
                    }
                } else {
                    System.out.println("Invalid account number. Use digits only.");
                }
            }

            System.out.print("Set your PIN: ");
            String pin = input.nextLine();

            acc = new Account(name, accountNumber, pin, 0.0);
            accounts.put(accountNumber, acc);
            saveAccounts(accounts);
            System.out.println("Account registered successfully. Please login below.");
        }

        if (acc == null) {
            System.out.println("----------LOGIN----------");
            System.out.print("Enter your account number: ");
            String accNum = input.nextLine();
            System.out.print("Enter your PIN: ");
            String pin = input.nextLine();
            acc = accounts.get(accNum);
            if (acc == null || !acc.checkPin(pin)) {
                System.out.println("Invalid credentials. Exiting.");
                return;
            }
        }

        System.out.println("Login successful. Welcome, " + acc.getName() + ".");

        while (true) {
            System.out.println("TRANSACTION");
            System.out.println("*********************");
            System.out.println("Menu:");
            System.out.println("1. Account Detail");
            System.out.println("2. Check Balance");
            System.out.println("3. Deposit");
            System.out.println("4. Withdraw");
            System.out.println("5. Exit");
            System.out.println("*********************");

            System.out.print("Enter 1, 2, 3, 4 or 5: ");
            String choice = input.nextLine();

            switch (choice) {
                case "1":
                    acc.printDetails();
                    break;
                case "2":
                    System.out.println("Available balance: LE. " + acc.getBalance());
                    break;
                case "3":
                    System.out.print("How much you want to deposit(LE.): ");
                    double deposit = getValidAmount(input);
                    acc.deposit(deposit);
                    break;
                case "4":
                    System.out.print("How much you want to withdraw(LE.): ");
                    double withdraw = getValidAmount(input);
                    acc.withdraw(withdraw);
                    break;
                case "5":
                    System.out.println("printing receipt..............");
                    acc.printReceipt();
                    saveAccounts(accounts);
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private static double getValidAmount(Scanner input) {
        while (true) {
            try {
                double value = Double.parseDouble(input.nextLine());
                return value;
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Enter a number: ");
            }
        }
    }

    private static HashMap<String, Account> loadAccounts() {
        HashMap<String, Account> accounts = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(DATA_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Account acc = Account.fromJSON(line);
                accounts.put(acc.getAccountNumber(), acc);
            }
        } catch (IOException ignored) {
        }
        return accounts;
    }

    private static void saveAccounts(HashMap<String, Account> accounts) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DATA_FILE))) {
            for (Account acc : accounts.values()) {
                writer.write(acc.toJSON());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving account data.");
        }
    }
}
