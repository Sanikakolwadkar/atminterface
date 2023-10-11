import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ATMInterface extends JFrame {
    private double balance = 10000.0;

    private JLabel balanceLabel;
    private JTextField amountField;

    public ATMInterface() {
        setTitle("ATM Interface");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initializeComponents();

        setLayout(new GridLayout(3, 1));
        add(balanceLabel);
        add(amountField);
        add(createButtonPanel());

        updateBalanceLabel();
    }

    private void initializeComponents() {
        balanceLabel = new JLabel("Balance: Rs." + balance);
        amountField = new JTextField();
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel(new GridLayout(1, 3));

        JButton checkBalanceButton = new JButton("Check Balance");
        JButton depositButton = new JButton("Deposit");
        JButton withdrawButton = new JButton("Withdraw");

        checkBalanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateBalanceLabel();
            }
        });

        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performTransaction(true);
            }
        });

        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performTransaction(false);
            }
        });

        buttonPanel.add(checkBalanceButton);
        buttonPanel.add(depositButton);
        buttonPanel.add(withdrawButton);

        return buttonPanel;
    }

    private void updateBalanceLabel() {
        balanceLabel.setText("Balance: Rs." + balance);
    }

    private void performTransaction(boolean isDeposit) {
        try {
            double amount = Double.parseDouble(amountField.getText());
            if (isDeposit) {
                balance += amount;
            } else {
                if (amount <= balance) {
                    balance -= amount;
                } else {
                    JOptionPane.showMessageDialog(this, "Insufficient funds!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            updateBalanceLabel();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid amount entered!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ATMInterface().setVisible(true);
            }
        });
    }
}
