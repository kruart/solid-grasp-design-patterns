# Flyweight
`Lets you fit more objects into the available amount of RAM by sharing common parts 
of state between multiple objects instead of keeping all of the data in each object`

 The Flyweight pattern has a single purpose: minimizing memory intake. 
 If your program doesn't struggle with a shortage of RAM, then you might just ignore this pattern for a while.
 
 
 Example: 
 
 Phone
 ```java
public class Phone {
    private String IMEI;
    private PhoneConf conf;

    public Phone(String IMEI, PhoneConf conf) {
        this.IMEI = IMEI;
        this.conf = conf;
    }

    public PhoneConf getConf() {
        return conf;
    }
}
```

PhoneConf
```java
public class PhoneConf {
    private final String model;
    private final int RAM;
    private final String processor;

    public PhoneConf(String model, int RAM, String processor) {
        this.model = model;
        this.RAM = RAM;
        this.processor = processor;
    }
}
```

PhoneConfFactory
```java
public class PhoneConfFactory {
    private static Map<String, PhoneConf> confMap = new HashMap<>();

    public static PhoneConf createInstance(String model) {
        // return object from cache to reduces memory consumption 
        PhoneConf phoneConf = confMap.get(model);

        if (phoneConf == null) {
            System.out.println("Creating a new phone configuration ...");
            // load conf from database
            switch (model) {
                case "MODEL_X" -> phoneConf = new PhoneConf(model, 6, "processor-256");
                case "MODEL_X_MAX" -> phoneConf = new PhoneConf(model, 8, "processor-512");
                case "MODEL_X_MAX_MEGA" -> phoneConf = new PhoneConf(model, 10, "processor-1024");
            }
            confMap.put(model, phoneConf);
        }
        return phoneConf;
    }
}
```

DemoPhoneProducer
```java
public class DemoPhoneProducer {
    public static void main(String[] args) {
        Phone max1 = createPhone("MODEL_X_MAX");
        Phone max2 = createPhone("MODEL_X_MAX");

        System.out.println(max1 == max2); // false
        System.out.println(max1.getConf() == max2.getConf()); // true
    }

    private static Phone createPhone(String model) {
        PhoneConf phoneConf = PhoneConfFactory.createInstance(model);
        return new Phone(UUID.randomUUID().toString(), phoneConf);
    }
}
```