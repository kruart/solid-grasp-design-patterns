package app;

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
