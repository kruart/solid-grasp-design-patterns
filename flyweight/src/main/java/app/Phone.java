package app;

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
