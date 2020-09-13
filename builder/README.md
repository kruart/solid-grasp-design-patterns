# Builder
`Provides a build object which is used to construct a complex object. 
It encapsulates the logic of constructing the different pieces of the complex object.`

Builder is very helpful when you have a class with a large number of fields
and it has many constructors with different combinations of parameters. For example:
```java
public class Person {
    private String lastName;
    private String firstName;
    private String middleName;
    private String streetAddress;
    private String city;
    private String state;
    private boolean isFemale;
    private boolean isEmployed;

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
    
    public Person(String firstName, String middleName, String lastName) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
    }
     
    private Person(
            final String newLastName,
            final String newFirstName,
            final String newMiddleName,
            final String newStreetAddress,
            final String newCity,
            final String newState) {
        this.lastName = newLastName;
        this.firstName = newFirstName;
        this.middleName = newMiddleName;
        this.streetAddress = newStreetAddress;
        this.city = newCity;
        this.state = newState;
    }  

    // other constructors ...
}
``` 

To add `Builder` to class you need  to create static inner class (typically  named Builder), 
add same fields to it (it allows us to achieve immutability and make `Person` fields `final`). 
Also we need to add setter for each field with return type of `Builder` class:

```java
package app;

public class Person {
    private final String lastName;
    private final String firstName;
    private final String middleName;
    private final String streetAddress;
    private final String city;
    private final String state;
    private final boolean isFemale;
    private final boolean isEmployed;

    // constructor could be public
    private Person(
            final String newLastName,
            final String newFirstName,
            final String newMiddleName,
            final String newStreetAddress,
            final String newCity,
            final String newState,
            final boolean newIsFemale,
            final boolean newIsEmployed) {
        this.lastName = newLastName;
        this.firstName = newFirstName;
        this.middleName = newMiddleName;
        this.streetAddress = newStreetAddress;
        this.city = newCity;
        this.state = newState;
        this.isFemale = newIsFemale;
        this.isEmployed = newIsEmployed;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public boolean isFemale() {
        return isFemale;
    }

    public boolean isEmployed() {
        return isEmployed;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static class Builder {
        private String lastName;
        private String firstName;
        private String middleName;
        private String streetAddress;
        private String city;
        private String state;
        private boolean isFemale;
        private boolean isEmployed;

        public Builder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder setMiddleName(String middleName) {
            this.middleName = middleName;
            return this;
        }

        public Builder setStreetAddress(String streetAddress) {
            this.streetAddress = streetAddress;
            return this;
        }

        public Builder setCity(String city) {
            this.city = city;
            return this;
        }

        public Builder setState(String state) {
            this.state = state;
            return this;
        }

        public Builder setIsFemale(boolean isFemale) {
            this.isFemale = isFemale;
            return this;
        }

        public Builder setIsEmployed(boolean isEmployed) {
            this.isEmployed = isEmployed;
            return this;
        }

        public Person build() {
            return new Person(
                    lastName, firstName, middleName,
                    streetAddress, city, state,
                    isFemale, isEmployed);
        }
    }
}

```

After that, we can dynamically create objects initializing only necessary fields. 
A new instance of `Person` object is returned on each `build` method call:
```java
public class Main {
    public static void main(String[] args) {
        Person.Builder builder = Person.newBuilder()
                .setFirstName("John")
                .setLastName("Doe")
                .setIsFemale(false)
                .setIsEmployed(true);

        Person person = builder.build();
        System.out.println(person);
    }
}
```

