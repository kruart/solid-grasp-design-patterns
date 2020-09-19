package app;

import java.util.HashMap;
import java.util.Map;

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
