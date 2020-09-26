# Template Method
`Defines the skeleton of an algorithm in the superclass 
but lets subclasses override specific steps of the algorithm without changing its structure`

Pros:
- You can let clients override only certain parts of a large algorithm, making them less affected by changes that happen to other parts of the algorithm.
- You can pull the duplicate code into a superclass.

Cons:
- Some clients may be limited by the provided skeleton of an algorithm.
- You might violate the Liskov Substitution Principle by suppressing a default step implementation via a subclass.
- Template methods tend to be harder to maintain the more steps they have.

Example:

Create a `ReportGenerator` class that will generate tax and expense reports. 
Note that the `generate()` method is `final` and it executes different methods step by step.  
```java
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
```

Each child must implement all abstract methods and can override `createDatabaseConnection()`.  

Tax Report
```java
class TaxReport extends ReportGenerator {

    public void executeQuery() {
        System.out.println("Executing MySQL Query...");
    }

    public void convert() {
        System.out.println("Converting To PDF...");
    }
}
```

Expense Report
```java
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
```

Demo
```java
public class Demo {
    public static void main(String[] args) {
        ReportGenerator taxReport = new TaxReport();
        taxReport.generate();

        ReportGenerator expenseReport = new ExpenseReport();
        expenseReport.generate();
    }
}
```

Output:
```
Creating Database Connection...
Executing MySQL Query...
Converting To PDF...
Creating Database Connection...
Executing Postgres Query...
Converting To XLS...
```