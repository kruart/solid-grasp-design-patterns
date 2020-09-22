# Command
`Wraps requests or simple operations into objects`

Example:  

Let's use a remote control as the example. 
Our remote is the center of home automation and can control everything. 
We'll just use a light as an example, that we can switch on or off, 
but we could add many more commands.

Our command interface:  
```java
public interface Command {
  void execute();
}
```

Now let's create two concrete commands. 
One will turn on the lights, another turns off lights:
```java
public class LightOnCommand implements Command {
    private Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    public void execute() {
        light.switchOn();
    }
}
```

```java
public class LightOffCommand implements Command {
    private final Light light;

    public LightOffCommand(Light light) {
        this.light = light;
    }

    public void execute() {
        light.switchOff();
    }
}
```

`Light` is our receiver class, so let's set that up now:
```java
public class Light {
    private boolean on;

    public void switchOn() {
        System.out.println("Turning on the light");
        on = true;
    }

    public void switchOff() {
        System.out.println("Turning off the light");
        on = false;
    }
}
```

Our invoker in this case is the remote control:  
```java
public class RemoteControl {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void pressButton() {
        command.execute();
    }
}
```

Finally we'll set up a client to use the invoker
```java

public class Client{
  public static void main(String[] args)    {
    RemoteControl control = new RemoteControl();
    Light light = new Light();
    Command lightsOn = new LightsOnCommand(light);
    Command lightsOff = new LightsOffCommand(light);

    //switch on
    control.setCommand(lightsOn);
    control.pressButton();

    //switch off
    control.setCommand(lightsOff);
    control.pressButton();
  }
}
```

So, in fact, we convert all actions into separate objects (commands). 
We have a collection of small classes that implement the Command interface. 
The logic for each action is small, and self-contained in each execute() method.
 
In fact, it saves us from spaghetti code. 
Our `RemoteControl` class could look like this without the Command pattern:
```java
public class RemoteControl {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void pressButton() {
        if (command instanceof LightOnCommand) {
            ((LightOnCommand) command).turnOn();
        } else if (command instanceof LightOnCommand) {
           ((LightOnCommand) command).turnOff();
        } else if ...
        ...
    }
}
```

But with the Command pattern each command hides all logic inside `execute()` method: 
```java
public class RemoteControl {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void pressButton() {
        command.execute();
    }
}
```

