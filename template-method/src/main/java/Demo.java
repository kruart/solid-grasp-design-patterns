public class Demo {
    public static void main(String[] args) {
        ReportGenerator taxReport = new TaxReport();
        taxReport.generate();

        ReportGenerator expenseReport = new ExpenseReport();
        expenseReport.generate();
    }
}
