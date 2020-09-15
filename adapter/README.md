# Adapter
`Mainly adapts one object to another one. Adapters allow objects to work together 
that couldn’t otherwise because of incompatible interfaces.`

Example:  
A computer has a USB connector, but you cannot insert a memory card into it.

The memory card cannot be inserted into a computer, 
which makes it impossible for us to save our photos, videos and other data.

The card reader is an adapter that solves this problem. 
After all, it has a USB cable! 
Unlike the memory card itself, the card reader can be inserted into a computer. 
They have a common interface with a computer - USB.

This is our `Usb` interface with the only method to insert the USB cable:
```java
public interface Usb {
    void connectWithUsbCable();
}
```

Here's our class that represents a memory card. 
It has 2 methods we need, but the trouble is: it does not implement the `Usb` interface. 
The card cannot be inserted into the `Usb` connector.
```java
public class MemoryCard {

    public void insert() {
        System.out.println("Memory card inserted successfully!");
    }

    public void copyData() {
        System.out.println("Data has been copied to your computer!");
    }
}
```

This is our Adapter.
The adaptable class (`MemoryCard`) becomes one of the adapter fields. 
This is logical, because in real life we also insert the card inside the card reader, and it also becomes part of it.
```java
public class CardReader implements Usb {

    private MemoryCard memoryCard;

    public CardReader(MemoryCard memoryCard) {
        this.memoryCard = memoryCard;
    }

    @Override
    public void connectWithUsbCable() {
        this.memoryCard.insert();
        this.memoryCard.copyData();
    }
}
```


With such a solution we can use `MemoryCard` through `CardReader` object 
that implements `Usb` interface

Main
```java
public class Main {
    public static void main(String[] args) {
        Usb cardReader = new CardReader(new MemoryCard());
        cardReader.connectWithUsbCable();
    }
}
```