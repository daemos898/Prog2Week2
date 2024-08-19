import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener; //Imports all necessary java utilities

public class BankApp extends JFrame {
	private static final long serialVersionUID = 1L;

	private double balance = 0.0; //Creates the base balance which is set at 0

    private JLabel balanceLabel; //Create various variables such as the balance, the boxes for user to enter numbers, and buttons user uses to interact with the program
    private JTextField depositField;
    private JTextField withdrawField;
    private JButton depositButton;
    private JButton withdrawButton;

    public BankApp() {
        setTitle("Banking Application"); //Create the title and size of the panel the user will see
        setSize(600, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(); //Create layout that the user panel will use
        panel.setLayout(new GridLayout(4, 2));

        balanceLabel = new JLabel("Balance: " + balance); //Insert the different panels such as balance, withdraw and deposit buttons, and areas for user to type the number they want to enter
        depositField = new JTextField(10);
        withdrawField = new JTextField(10);
        depositButton = new JButton("Deposit");
        withdrawButton = new JButton("Withdraw");

        depositButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    double depositAmount = Double.parseDouble(depositField.getText()); //Method for user to deposit money into account
                    balance += depositAmount;
                    updateBalanceLabel();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Error, please enter a number"); //Catch so if the user enters something that is not an integer then it will alert them
                }
            }
        });

        withdrawButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Handle withdraw action
                try {
                    double withdrawAmount = Double.parseDouble(withdrawField.getText()); //Method for user to withdraw money from account
                    if (withdrawAmount <= balance) {
                        balance -= withdrawAmount;
                        updateBalanceLabel();
                    } else {
                        JOptionPane.showMessageDialog(null, "Error, insufficient funds"); //Else statement which will not allow user to go negative due to insufficient funds
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Error, please enter a number"); //Catch exactly like the deposit one but for withdrawling
                }
            }
        });

        panel.add(depositField); //This creates the order in which the panels are added into the user panel
        panel.add(withdrawField);
        panel.add(depositButton);
        panel.add(withdrawButton);
        panel.add(balanceLabel);

        add(panel);
    }

    private void updateBalanceLabel() { //Creates the bank balance that updates as the user deposits and withdrawls
        balanceLabel.setText("Account Balance: " + balance);
    }

    public static void main(String[] args) { //Ensures that components are created
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new BankApp().setVisible(true);
            }
        });
    }
}