### High Cohesion
`classes must contain related business logic`  
  
In fact, `High Cohesion` is very closely related to the `Single Responsibility Principle (SRP)` from `SOLID` principles, which states that `A class should have only one reason to change`.  

If you adhere to `Low Coupling` to the absolute, then you can quickly come to the point where all the functionality in one single class. 
In this case, there will be no coupling at all, but at the same time, everyone understands that something is clearly wrong here, because completely unrelated business logic will fall into this class.  


######Example with violation:
```java
@AllArgsConstructor
public class Data {
    private int temperature;
    private int time;
   
   private int calculateTimeDifference(int time) {
      return this.time - time; 
  }

  private int calculateTemperatureDifference(int temperature) {
      return this.temperature - temperature; 
  }
}
```
The presented class contains business logic for working with both temperature and time. 
They have nothing in common, except that they are collected from one sensor. 
If we want to reuse the business logic associated with working only with temperature, then it will not be easy to do this. 
If we draw parallels with the principles of SOLID, then the class violates the SRP: two axes of change pass through it.

By the way, the presence of prefixes(**calculateTime**Difference and **calculateTemperature**Difference) 
in the names often means that the High Cohesion principle is violated.

######Solution
It makes sense to create 2 classes: one for temperature, other for time:
```java
@AllArgsConstructor
public class Data {
    private TemperatureData temperatureData;
    private TimeData timeData;
   
   public Data(int time, int temperature) {
       this.temperatureData = new TemperatureData(temperature);
       this.timeData = new TimeData(time);
   }

   // here is the logic for working with both time and temperature

}

@AllArgsConstructor
public class TimeData {
    private int time;

    private int calculateTimeDifference(int time) {
      return this.time - time; 
  }
}

@AllArgsConstructor
public class TemperatureData {
    private int temperature;

    private int calculateTemperatureDifference(int temperature) {
      return this.temperature - temperature; 
  }
}
```

Conclusion

`Low Coupling` and `High Cohesion` are two interconnected patterns that can only be considered together. 
Their essence can be combined as follows: the system should consist of loosely coupled classes that contain related business logic. 
Compliance with these principles allows you to conveniently reuse the created classes without losing understanding of their area of responsibility.