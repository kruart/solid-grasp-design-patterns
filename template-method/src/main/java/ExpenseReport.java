class ExpenseReport extends ReportGenerator {
    public void createDatabaseConnection() {
        System.out.println("Creating Database Connection...");
    }

    public void executeQuery() {
        System.out.println("Executing Postgres Query...");
    }

    public void convert() {
        System.out.println("Converting To XLS...");
    }
}