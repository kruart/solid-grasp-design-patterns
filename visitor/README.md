# Visitor
`Define a new operation without introducing the modifications to an existing object structure.`

Visitor pattern is used when we have to perform an operation on a group of 
similar kind of objects. With the help of visitor pattern, we can move the operational logic 
from the objects to another class.

Example:   

For example, think of a shopping cart where we can add different type of items (Elements). 
When we click on checkout button, it calculates the total amount to be paid. 
Now we can have the calculation logic in item classes or 
we can move out this logic to another class using visitor pattern. 
Let’s implement this in our example of visitor pattern.

To implement visitor pattern, first of all we will create 
different type of items (Elements) to be used in shopping cart.

```java
public interface ItemElement {
    int accept(ShoppingCartVisitor visitor);
}
```
Notice that accept method takes Visitor argument. 
We can have some other methods also specific for items but for simplicity 
I am not going into that much detail and focusing on visitor pattern only.

Let’s create some concrete classes for different types of items.  

Book
```java
public class Book implements ItemElement {

	private int price;
	private String isbnNumber;
	
	public Book(int cost, String isbn){
		this.price=cost;
		this.isbnNumber=isbn;
	}
	
	public int getPrice() {
		return price;
	}

	public String getIsbnNumber() {
		return isbnNumber;
	}

	@Override
	public int accept(ShoppingCartVisitor visitor) {
		return visitor.visit(this);
	}

}
```

Fruit
```java
public class Fruit implements ItemElement {
	
	private int pricePerKg;
	private int weight;
	private String name;
	
	public Fruit(int priceKg, int wt, String nm){
		this.pricePerKg=priceKg;
		this.weight=wt;
		this.name = nm;
	}
	
	public int getPricePerKg() {
		return pricePerKg;
	}


	public int getWeight() {
		return weight;
	}

	public String getName(){
		return this.name;
	}
	
	@Override
	public int accept(ShoppingCartVisitor visitor) {
		return visitor.visit(this);
	}

}
```


Notice the implementation of accept() method in concrete classes, 
its calling visit() method of Visitor and passing itself as argument.

We have visit() method for different type of items in Visitor interface 
that will be implemented by concrete visitor class.

ShoppingCartVisitor
```java
public interface ShoppingCartVisitor {
    int visit(Book book);
    int visit(Fruit fruit);
}
```

Now we will implement visitor interface
 and every item will have it’s own logic to calculate the cost.

ShoppingCartVisitorImpl
```java
public class ShoppingCartVisitorImpl implements ShoppingCartVisitor {
    @Override
    public int visit(Book book) {
        // apply 5$ discount if book price is greater than 50
        int cost = book.getPrice() > 50 ? book.getPrice() - 5 : book.getPrice();
        System.out.println("Book ISBN::" + book.getIsbnNumber() + " cost =" + cost);
        return cost;
    }

    @Override
    public int visit(Fruit fruit) {
        int cost = fruit.getPricePerKg() * fruit.getWeight();
        System.out.println(fruit.getName() + " cost = " + cost);
        return cost;
    }
}
```

ShoppingCartClient
```java
public class ShoppingCartClient {
    public static void main(String[] args) {
        ItemElement[] items = new ItemElement[]{
                new Book(20, "1234"), new Book(100, "5678"),
                new Fruit(10, 2, "Banana"), new Fruit(5, 5, "Apple")
        };

        int total = calculatePrice(items);
        System.out.println("Total Cost = " + total);
    }

    private static int calculatePrice(ItemElement[] items) {
        ShoppingCartVisitor visitor = new ShoppingCartVisitorImpl();
        int sum = 0;
        for (ItemElement item : items) {
            sum = sum + item.accept(visitor);
        }
        return sum;
    }
}
```

Output
```
Book ISBN::1234 cost = 20
Book ISBN::5678 cost = 95
Banana cost = 20
Apple cost = 25
Total Cost = 160
```

Notice that implementation if accept() method in all the items are same 
but it can be different, for example there can be logic to check 
if item is free then don’t call the visit() method at all.