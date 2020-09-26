abstract class ReportGenerator {

    final public void generate() {
        createDatabaseConnection();
        executeQuery();
        convert();
    }

    public void createDatabaseConnection() {
        System.out.println("Creating Database Connection...");
    }

    abstract public void executeQuery();

    abstract public void convert();
}