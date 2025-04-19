public class Account {

    private String name;
    private String accountNumber;
    private String pin;
    private double balance;

    public Account(String name, String accountNumber, String pin, double balance) {
        this.name = name;
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public boolean checkPin(String enteredPin) {
        return this.pin.equals(enteredPin);
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid deposit amount.");
            return;
        }
        balance += amount;
        System.out.println("Current account balance: LE. " + balance);
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid withdrawal amount.");
        } else if (amount > balance) {
            System.out.println("Insufficient fund!");
            System.out.println("Your balance is LE. " + balance + " only.");
        } else {
            balance -= amount;
            System.out.println("LE. " + amount + " withdrawal successful!");
            System.out.println("Current account balance: LE. " + balance);
        }
    }

    public void printDetails() {
        System.out.println("----------ACCOUNT DETAIL----------");
        System.out.println("Account Holder: " + name);
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Available balance: LE. " + balance);
    }

    public void printReceipt() {
        int transactionId = 100000 + (int) (Math.random() * 900000);
        System.out.println("******************************************");
        System.out.println("Transaction is now complete.");
        System.out.println("Transaction number: " + transactionId);
        System.out.println("Account holder: " + name);
        System.out.println("Account number: " + accountNumber);
        System.out.println("Available balance: LE. " + balance);
        System.out.println("Thanks for choosing us as your bank");
        System.out.println("******************************************");
    }

    public String toJSON() {
        return String.format("{\"name\":\"%s\",\"accountNumber\":\"%s\",\"pin\":\"%s\",\"balance\":%.2f}",
                name, accountNumber, pin, balance);
    }

    public static Account fromJSON(String jsonLine) {
        String[] parts = jsonLine.replace("{", "").replace("}", "").replace("\"", "").split(",");
        String name = parts[0].split(":")[1];
        String accountNumber = parts[1].split(":")[1];
        String pin = parts[2].split(":")[1];
        double balance = Double.parseDouble(parts[3].split(":")[1]);
        return new Account(name, accountNumber, pin, balance);
    }
}
