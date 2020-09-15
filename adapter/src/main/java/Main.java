public class Main {
    public static void main(String[] args) {
        Usb cardReader = new CardReader(new MemoryCard());
        cardReader.connectWithUsbCable();
    }
}