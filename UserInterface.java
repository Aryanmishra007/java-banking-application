import java.util.Scanner;

// Account class
class Account {
    private int accountNumber;
    private String accountHolderName;
    private double balance;
    private String email;
    private String phoneNumber;

    // Constructor
    public Account(int accountNumber, String accountHolderName, double balance, String email, String phoneNumber) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = balance;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    // Deposit method
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: " + amount + " | New Balance: " + balance);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    // Withdraw method
    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid withdrawal amount.");
        } else if (amount > balance) {
            System.out.println("Insufficient balance.");
        } else {
            balance -= amount;
            System.out.println("Withdrawn: " + amount + " | New Balance: " + balance);
        }
    }

    // Display account details
    public void displayAccountDetails() {
        System.out.println("Account Number : " + accountNumber);
        System.out.println("Account Holder : " + accountHolderName);
        System.out.println("Balance        : " + balance);
        System.out.println("Email          : " + email);
        System.out.println("Phone Number   : " + phoneNumber);
    }

    // Update contact details
    public void updateContactDetails(String email, String phoneNumber) {
        this.email = email;
        this.phoneNumber = phoneNumber;
        System.out.println("Contact details updated.");
    }

    // Getter
    public int getAccountNumber() {
        return accountNumber;
    }
}

// UserInterface class with main menu
public class UserInterface {
    private static final int MAX_ACCOUNTS = 100;
    private Account[] accounts = new Account[MAX_ACCOUNTS];
    private int accountCount = 0;
    private int nextAccountNumber = 1001;
    private Scanner scanner = new Scanner(System.in);

    // Create new account
    public void createAccount() {
        System.out.print("Enter account holder name: ");
        String name = scanner.nextLine();
        System.out.print("Enter initial deposit amount: ");
        double deposit = scanner.nextDouble();
        scanner.nextLine(); // consume newline
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter phone number: ");
        String phone = scanner.nextLine();

        if (accountCount < MAX_ACCOUNTS) {
            Account acc = new Account(nextAccountNumber++, name, deposit, email, phone);
            accounts[accountCount++] = acc;
            System.out.println("Account created successfully. Account Number: " + acc.getAccountNumber());
        } else {
            System.out.println("Account limit reached.");
        }
    }

    // Find account by number
    private Account findAccount(int accNo) {
        for (int i = 0; i < accountCount; i++) {
            if (accounts[i].getAccountNumber() == accNo) {
                return accounts[i];
            }
        }
        return null;
    }

    // Deposit
    public void performDeposit() {
        System.out.print("Enter account number: ");
        int accNo = scanner.nextInt();
        System.out.print("Enter amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();
        Account acc = findAccount(accNo);
        if (acc != null) {
            acc.deposit(amount);
        } else {
            System.out.println("Account not found.");
        }
    }

    // Withdraw
    public void performWithdrawal() {
        System.out.print("Enter account number: ");
        int accNo = scanner.nextInt();
        System.out.print("Enter amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();
        Account acc = findAccount(accNo);
        if (acc != null) {
            acc.withdraw(amount);
        } else {
            System.out.println("Account not found.");
        }
    }

    // Show details
    public void showAccountDetails() {
        System.out.print("Enter account number: ");
        int accNo = scanner.nextInt();
        scanner.nextLine();
        Account acc = findAccount(accNo);
        if (acc != null) {
            acc.displayAccountDetails();
        } else {
            System.out.println("Account not found.");
        }
    }

    // Update contact
    public void updateContact() {
        System.out.print("Enter account number: ");
        int accNo = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter new email: ");
        String email = scanner.nextLine();
        System.out.print("Enter new phone number: ");
        String phone = scanner.nextLine();

        Account acc = findAccount(accNo);
        if (acc != null) {
            acc.updateContactDetails(email, phone);
        } else {
            System.out.println("Account not found.");
        }
    }

    // Main menu
    public void mainMenu() {
        int choice;
        do {
            System.out.println("\n--- Banking Application ---");
            System.out.println("1. Create a new account");
            System.out.println("2. Deposit money");
            System.out.println("3. Withdraw money");
            System.out.println("4. View account details");
            System.out.println("5. Update contact details");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> createAccount();
                case 2 -> performDeposit();
                case 3 -> performWithdrawal();
                case 4 -> showAccountDetails();
                case 5 -> updateContact();
                case 6 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid choice.");
            }
        } while (choice != 6);
    }

    // Main method
    public static void main(String[] args) {
        UserInterface ui = new UserInterface();
        ui.mainMenu();
    }
}
