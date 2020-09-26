# Strategy
`Lets you define a family of algorithms, put each of them into a separate class, 
and make their objects interchangeable`

Example:

Let's imagine that we are writing a program that actively works with the `Auto` object.
```java
public class Auto {
   public void gas() {
       System.out.println("Go ahead!");
   }

   public void stop() {
       System.out.println("Slow down!");
   }
}

public class Sedan extends Auto {
}

public class HybridAuto extends Auto {
}

public class F1Car extends Auto {
}
```

All three child classes inherit from the parent two standard methods - gas() and stop()

Our program is quite simple: cars can only drive forward and brake.

Continuing our work, we decided to add a new method for the machines - fill() (fill petrol).
```java
public class Auto {
   public void gas() {
       System.out.println("Go ahead!");
   }

   public void stop() {
       System.out.println("Slow down!");
   }

   public void fill() {
       System.out.println("Fill petrol");
   }
}
```

Let's imagine that here's the problem. Different types of cars can refuel in different ways.  

What should we do in such a situation? Well, for example, you can override the fill() method.
But this solution can hardly be called successful, at least because of the duplication code. 
For example, most of the classes will use a method from the parent class, 
but other classes will be forced to override it.

And that's where strategies come into play
We will move the fill() method into a separate interface - `FillStrategy`:
```java
public interface FillStrategy {
   void fill();
}
```

Now we can create several classes that will implement this interface:
```java
public class HybridFillStrategy implements FillStrategy {

   @Override
   public void fill() {
       System.out.println("Refuel with petrol or electricity of your choice!");
   }
}

public class F1PitstopStrategy implements FillStrategy {

   @Override
   public void fill() {
       System.out.println("Refuel only after all other pit stop procedures!");
   }
}

public class StandardFillStrategy implements FillStrategy {
   @Override
   public void fill() {
       System.out.println("Just refueling petrol");
   }
}
```

We have created three behavior strategies - for conventional cars, for hybrids and for Formula 1 cars.
Each strategy implements a separate refueling algorithm. 
In our case, this is just output to the console, 
but there may be some complex logic inside the method.

Now our cars may look like this:
```java
public class Auto {
   private FillStrategy fillStrategy;

   public Auto(FillStrategy fillStrategy) {
       this.fillStrategy = fillStrategy;
   }

   public void fill() {
       this.fillStrategy.fill();
   }

   public void gas() {
       System.out.println("Go ahead!");
   }

   public void stop() {
       System.out.println("Slow down!");
   }
}

public class Sedan extends Auto {
   public Sedan() {
       super(new StandardFillStrategy());
   }
}



public class HybridAuto extends Auto {
   public HybridAuto() {
       super(new HybridFillStrategy());
   }
}

public class F1Car extends Auto {
   public F1Car() {
       super(new F1PitstopStrategy());
   }
}
```


Main
```java
public class Main {
    public static void main(String[] args) {
        Auto sedan = new Sedan();
        Auto hybrid = new HybridAuto();
        Auto f1car = new F1Car();

        sedan.fill();
        hybrid.fill();
        f1car.fill();
    }
}
```

Output:
```
Just refueling petrol!
Refuel with petrol or electricity of your choice!
Refuel only after all other pit stop procedures!
```
---

`State` pattern can be considered as an extension of `Strategy`. 
Both patterns are based on composition: 
they change the behavior of the context by delegating some work to helper objects. 
`Strategy` makes these objects completely independent and unaware of each other. 
However, `State` doesn't restrict dependencies between concrete states, 
letting them alter the state of the context at will.

It's not always easy to understand the difference between the Strategy and State patterns.
I guess google maps is a good example, strategy is a satellite, terrain, scheme, but panorama (street view) 
is already another state.   
Another explanation [State vs Strategy](https://stackoverflow.com/a/12868304/10701730).