### Information Expert
`Information should be processed where it is contained`

######Example with violation:
`Order`, containing a list of `OrderItems`, the elements of which, in turn, contain `Good` and its quantity, and `Good` may contain, for example, price, name, etc.:
```java
@Getter
@AllArgsConstructor
public class Order {
    private List<OrderItem> orderItems;
    private String destinationAddress;
}

@Getter
@AllArgsConstructor
public class OrderItem {
    private Good good;
    private int amount;
}

@Getter
@AllArgsConstructor
public class Good {
    private String name;
    private int price;
}
```

We are faced with a simple task: to calculate the amount of an order. If you are not very thoughtful about solving this problem, 
you can immediately write something like this in the client code that works with objects of the `Order` class:
```java
public class Client {
    public void doSmth() {
        /* do some hard work here */
    }
    
    private int getOrderPrice(Order order) {
        List<OrderItem> orderItems = order.getOrderItems();
        int result = 0;
        
        for (OrderItem orderItem : orderItems) {
            int amount = orderItem.getAmount();
            Good good = orderItem.getGood();
            int price = good.getPrice();   

            result += price * amount;
        }
        return result;
    }
}
```

Let's analyze the code.

First, if we start adding business logic related to price calculation, the code of the `Client::getOrderPrice` method will not only inevitably grow, but also acquire all sorts of ifs (discount for pensioners, discount on holidays, discount due to wholesale purchases), which in the end will lead to the fact that the code will become hard to read and maintain, and every change will come with pain.

Secondly, the `Client` class depends on 3 classes: `Order`, `OrderItem` and `Good`. 
All business logic for working with these classes is pulled into it. 
This means that if we want to reuse `OrderItem` or `Good` separately from `Order` 
(for example, to calculate the price of goods left in warehouses), we simply cannot do this, 
because the business logic is in the client code, which will lead to inevitable duplication of code.

In this example, as well as anywhere where there is a chain of getter methods,
the principle of `Information Expert` is violated, because the client code processes information, and `Order` contains it.
But as that principle says, `information should be processed where it is contained`.

Let's try to fix it:
```java
@Getter
@AllArgsConstructor
public class Order {
    private List<OrderItem> orderItems;
    private String destinationAddress;
    
    public int getPrice() {
        int result = 0;
        
        for(OrderItem orderItem : orderItems) {
            result += orderItem.getPrice();
        }
        
        return result;
    }
}

@Getter
@AllArgsConstructor
public class OrderItem {
    private Good good;
    private int amount;

    public int getPrice() {
        return good.getPrice();
    }
}

@Getter
@AllArgsConstructor
public class Good {
    private String name;
    private int price;
}

public class Client {
    public void doSmth() {
        Order order = new Order(new ArrayList<>(), "");
        order.getPrice();
    }
}
``` 
Now the information is processed in the class that contains it, the client code depends only on `Order`, 
unaware of its internal structure, and the `Order`, `OrderItem` and `Good`, or `OrderItem` and `Good` classes 
can be collected into a separate library that can be used in various parts of the project.