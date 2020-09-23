# Interpreter
`Provides a way to evaluate language grammar or expression.  
Defines the grammar of a particular language in an object-oriented way 
which can be evaluated by the interpreter itself.`

This pattern is used in SQL parsing, symbol processing engine etc.


Example: 

Expression interface
```java
public interface Expression {
    boolean interpret(String context);
}
```


Create concrete classes implementing the above interface.  
OrExpression
```java
public class OrExpression implements Expression {
    private final Expression expr1;
    private final Expression expr2;

    public OrExpression(Expression expr1, Expression expr2) {
        this.expr1 = expr1;
        this.expr2 = expr2;
    }

    @Override
    public boolean interpret(String context) {
        return expr1.interpret(context) || expr2.interpret(context);
    }
}
```

AndExpression
```java
public class AndExpression implements Expression {
    private final Expression expr1;
    private final Expression expr2;

    public AndExpression(Expression expr1, Expression expr2) {
        this.expr1 = expr1;
        this.expr2 = expr2;
    }

    @Override
    public boolean interpret(String context) {
        return expr1.interpret(context) && expr2.interpret(context);
    }
}
```

TerminalExpression
```java
public class TerminalExpression implements Expression {
    private final String data;

    public TerminalExpression(String data) {
        this.data = data;
    }

    @Override
    public boolean interpret(String context) {
        return context.contains(data);
    }
}
```


Main
```java
public class Main {
    //Rule: Robert and John are male
    public static Expression getMaleExpression(){
        Expression robert = new TerminalExpression("Robert");
        Expression john = new TerminalExpression("John");
        return new OrExpression(robert, john);
    }

    //Rule: Julie is a married women
    public static Expression getMarriedWomanExpression(){
        Expression julie = new TerminalExpression("Julie");
        Expression married = new TerminalExpression("Married");
        return new AndExpression(julie, married);
    }

    public static void main(String[] args) {
        Expression isMale = getMaleExpression();
        Expression isMarriedWoman = getMarriedWomanExpression();

        System.out.println("John is male? " + isMale.interpret("John"));
        System.out.println("Julie is a married women? " + isMarriedWoman.interpret("Married Julie"));
    }
}
```

Output
```
John is male? true
Julie is a married women? true
```

`Interpreter` traverses the whole hierarchy recursively 
until the terminal operation.

This pattern is very similar to `Composite` pattern. 
Except that `Composite` uses leaf/composite terminology 
and `Interpreter` patters uses terminal/non-terminal expressions.