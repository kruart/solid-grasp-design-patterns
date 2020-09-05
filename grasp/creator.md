### Creator
`Instances of a class must be created by the class that needs them`

######Example with violation:
Consider the same problem with orders and goods. 
Let's assume that the code we write follows the [Information Expert](information-expert.md) principle:
```java
@Setter
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

@Setter
@Getter
@AllArgsConstructor
public class OrderItem {
    private Good good;
    private int amount;

    public int getPrice() {
        return amount * good.getPrice();
    }
}

@Setter
@Getter
@AllArgsConstructor
public class Good {
    private String name;
    private int price;
}
```

Despite the triviality of the principle we are studying, in some client code you can find the following:
```java
public class Client {
    public void doSmth() {
        Good good = new Good("name", 2);
        OrderItem orderItem = new OrderItem(good, amount);
        List<OrderItem> orderItems = new ArrayList<>();
        orderItems.add(orderItem);
        Order order = new Order(orderItems, "abc");
        // client code 
    }
}
```
The `Client` class now depends on the `Order` class and all of its internals: `OrderItem` and `Good`. Thus, we cannot reuse the `Client` class without the aforementioned classes, which the `Client` does not need.
We effectively negated all the efforts to comply with the `Information Expert`, because the `Client` class created all the objects.

Let's fix it:
```java
@Setter
@Getter
public class Order {
    private List<OrderItem> orderItems = new ArrayList<>();
    private String destinationAddress;
    
    public Order(String destinationAddress) {
        this.destinationAddress = destinationAddress;
    }
    
    public int getPrice() {
        int result = 0;
        
        for(OrderItem orderItem : orderItems) {
            result += orderItem.getPrice();
        }
        
        return result;
    }

    public void addOrderItem(int amount, String name, int price) {
       orderItems.add(new OrderItem(amount, name, price));
   }
}

@Setter
@Getter
public class OrderItem {
    private Good good;
    private int amount;

    public OrderItem(int amount, String name, int price) {
        this.amount = amount;
        this.good = new Good(name, price);
    }

    public int getPrice() {
        return amount * good.getPrice();
    }
}

@Setter
@Getter
@AllArgsConstructor
public class Good {
    private String name;
    private int price;
}
```

Now the number of dependencies between classes will be minimal. The client code is somewhat simplified and may look like this:
```java
public class Client {
    public void doSmth() {
        Order order = new Order("address");
        order.addOrderItem(amount, name, price); // moved creation logic to Order class
        // client code 
    }
}
```
So, here we have moved the creation of `OrderItem` to the `Order` class, since it uses the `OrderItem` instances.

`Creator` can be considered a special case of `Information Expert`, because calling a constructor is the same as calling a method. 
Adhering to it together with `Information Expert` allows you to achieve a minimum number of connections between classes and greater reusability.
 
