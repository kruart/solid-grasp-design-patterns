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

    @Override
    public String toString() {
        return "Person{" +
                "lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", streetAddress='" + streetAddress + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", isFemale=" + isFemale +
                ", isEmployed=" + isEmployed +
                '}';
    }
}
