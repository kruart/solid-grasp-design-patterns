public class Main {
    public static void main(String[] args) {
        Developer dev1 = new Developer("Dev 1");
        Developer dev2 = new Developer("Dev 2");
        Developer dev3 = new Developer("Dev 3");
        Developer dev4 = new Developer("Dev 4");

        Manager manager1 = new Manager("Manager 1");
        Manager manager2 = new Manager("Manager 2");

        GeneralManager generalManager = new GeneralManager("General Manager");

        generalManager.addReportee(manager1);
        generalManager.addReportee(manager2);
        generalManager.addReportee(dev3);

        manager1.addReportee(dev1);
        manager1.addReportee(dev4);

        manager2.addReportee(dev2);

        generalManager.print();
    }
}
