package app;

import java.util.UUID;

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
