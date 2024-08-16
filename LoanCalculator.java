import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class LoanCalculator extends JFrame {
    private JTextField loanAmountField;
    private JTextField annualInterestRateField;
    private JTextField loanTermField;
    private JTextField monthlyPaymentField;
    private JTextArea loanListArea;

    private ArrayList<Loan> loans;

    public LoanCalculator() {
        loans = new ArrayList<>();
        setTitle("Loan Calculator");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 2));

        add(new JLabel("Loan Amount:"));
        loanAmountField = new JTextField();
        add(loanAmountField);

        add(new JLabel("Annual Interest Rate:"));
        annualInterestRateField = new JTextField();
        add(annualInterestRateField);

        add(new JLabel("Loan Term (years):"));
        loanTermField = new JTextField();
        add(loanTermField);

        add(new JLabel("Monthly Payment:"));
        monthlyPaymentField = new JTextField();
        monthlyPaymentField.setEditable(false);
        add(monthlyPaymentField);

        JButton calculateButton = new JButton("Calculate");
        add(calculateButton);

        JButton addButton = new JButton("Add Loan");
        add(addButton);

        loanListArea = new JTextArea();
        loanListArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(loanListArea);
        add(scrollPane);

        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateMonthlyPayment();
            }
        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addLoan();
            }
        });
    }

    private void calculateMonthlyPayment() {
        try {
            double loanAmount = Double.parseDouble(loanAmountField.getText());
            double annualInterestRate = Double.parseDouble(annualInterestRateField.getText());
            int loanTerm = Integer.parseInt(loanTermField.getText());

            Loan loan = new Loan(loanAmount, annualInterestRate, loanTerm);
            double monthlyPayment = loan.calculateMonthlyPayment();
            monthlyPaymentField.setText(String.format("%.2f", monthlyPayment));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addLoan() {
        try {
            double loanAmount = Double.parseDouble(loanAmountField.getText());
            double annualInterestRate = Double.parseDouble(annualInterestRateField.getText());
            int loanTerm = Integer.parseInt(loanTermField.getText());

            Loan loan = new Loan(loanAmount, annualInterestRate, loanTerm);
            loans.add(loan);
            updateLoanList();
            clearInputFields();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateLoanList() {
        StringBuilder sb = new StringBuilder();
        for (Loan loan : loans) {
            sb.append(String.format("Loan Amount: %.2f, Annual Interest Rate: %.2f%%, Loan Term: %d years, Monthly Payment: %.2f\n",
                    loan.getLoanAmount(), loan.getAnnualInterestRate(), loan.getLoanTerm(), loan.calculateMonthlyPayment()));
        }
        loanListArea.setText(sb.toString());
    }

    private void clearInputFields() {
        loanAmountField.setText("");
        annualInterestRateField.setText("");
        loanTermField.setText("");
        monthlyPaymentField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoanCalculator().setVisible(true);
            }
        });
    }
}
