import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

class Atm {
    private String name;
    private String accountNumber;
    private String pin;
    private float balance;

    public Atm(String name, String accountNumber, String pin, float balance) {
        this.name = name;
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.balance = balance;
    }

    public void deposit(float amount) {
        this.balance += amount;
        showMessage("Deposit Successful!");
        printReceipt();
    }

    public void withdraw(float amount) {
        if (this.balance >= amount) {
            this.balance -= amount;
            showMessage("Withdrawal Successful!");
            printReceipt();
        } else {
            showMessage("Insufficient Funds!");
        }
    }

    public void checkBalance() {
        showMessage("Current Account Balance: Rs." + String.format("%.2f", this.balance));
        printReceipt();
    }

    public void changePin(String existingPin, String newPin) {
        if (existingPin.equals(this.pin)) {
            this.pin = newPin;
            showMessage("Your PIN has been changed successfully!");
            printReceipt();
        } else {
            showMessage("Incorrect existing PIN. Please try again.");
        }
    }

    public void printReceipt() {
        System.out.println("\nPrinting receipt..............");
        System.out.println("******************************************");
        System.out.println("Transaction is now complete.");
        System.out.println("Account Holder: " + this.name.toUpperCase());
        System.out.println("Account Number: " + this.accountNumber);
        System.out.println("Available Balance: Rs." + String.format("%.2f", this.balance));
        System.out.println("\nThanks Visit Again!");
        System.out.println("******************************************");
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    public void showGui() {
        JFrame frame = new JFrame("ATM Application");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);

        frame.setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel welcomeLabel = new JLabel("Welcome to the Bank of India");
        welcomeLabel.setBounds(10, 20, 300, 25);
        panel.add(welcomeLabel);

        JButton transactionButton = new JButton("Do Transaction");
        transactionButton.setBounds(10, 50, 150, 25);
        panel.add(transactionButton);

        JButton exitButton = new JButton("Exit");
        exitButton.setBounds(170, 50, 80, 25);
        panel.add(exitButton);

        transactionButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showTransactionDialog();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    private void showTransactionDialog() {
        JFrame transactionFrame = new JFrame("Transaction Options");
        transactionFrame.setSize(400, 300);

        JPanel transactionPanel = new JPanel();
        transactionFrame.add(transactionPanel);

        JButton depositButton = new JButton("Deposit");
        JButton withdrawButton = new JButton("Withdraw");
        JButton checkBalanceButton = new JButton("Check Balance");
        JButton changePinButton = new JButton("Change PIN");
        JButton printReceiptButton = new JButton("Print Receipt");

        depositButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog("Enter the deposit amount:");
                float amount = Float.parseFloat(input);
                deposit(amount);
            }
        });

        withdrawButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog("Enter the withdrawal amount:");
                float amount = Float.parseFloat(input);
                withdraw(amount);
            }
        });

        checkBalanceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkBalance();
            }
        });

        changePinButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String existingPin = JOptionPane.showInputDialog("Enter your existing 4-digit PIN:");
                String newPin = JOptionPane.showInputDialog("Enter your new 4-digit PIN:");
                changePin(existingPin, newPin);
            }
        });

        printReceiptButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                printReceipt();
            }
        });

        transactionPanel.add(depositButton);
        transactionPanel.add(withdrawButton);
        transactionPanel.add(checkBalanceButton);
        transactionPanel.add(changePinButton);
        transactionPanel.add(printReceiptButton);
        transactionFrame.setVisible(true);
    }
}

public class Project2 {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                System.out.println("___________________________________________________________");
                System.out.println("****** WELCOME TO THE BANK OF INDIA ******");
                System.out.println("___________________________________________________________");

                System.out.println("----------ACCOUNT CREATION----------");
                Scanner scanner = new Scanner(System.in);
                System.out.print("Enter Your Name: ");
                String name = scanner.nextLine();
                System.out.print("Enter Your Account Number: ");
                String accountNumber = scanner.nextLine();
                System.out.print("Enter your 4 Digit Pin: ");
                String pin = scanner.nextLine();

                System.out.println("Congratulations! Account created successfully......");

                Atm atm = new Atm(name, accountNumber, pin, 0);
                atm.showGui();
            }
        });
    }
}