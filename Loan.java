public class Loan {
    private double loanAmount;
    private double annualInterestRate;
    private int loanTerm;

    public Loan(double loanAmount, double annualInterestRate, int loanTerm) {
        this.loanAmount = loanAmount;
        this.annualInterestRate = annualInterestRate;
        this.loanTerm = loanTerm;
    }

    public double getLoanAmount() {
        return loanAmount;
    }

    public double getAnnualInterestRate() {
        return annualInterestRate;
    }

    public int getLoanTerm() {
        return loanTerm;
    }

    public double calculateMonthlyPayment() {
        double monthlyInterestRate = annualInterestRate / 1200;
        int numberOfPayments = loanTerm * 12;
        return (loanAmount * monthlyInterestRate) /
               (1 - Math.pow(1 + monthlyInterestRate, -numberOfPayments));
    }
}
