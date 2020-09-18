# Decorator
`Lets you attach new behaviors to objects by placing these objects
 inside special wrapper objects that contain the behaviors`
 
 Example:  
 We have the interface `Text` and a basic implementation of it which returns the text.   
 There are 4 classes `AllCaps`, `StringConcat`, `ReplaceThisWithThat`  and `Brackets`
 which are decorators taking the input text and adding specific behavior to it. 
 
 So let’s define an interface `Text` with a method format as shown below:  
 

 ```java
public interface Text {
    String format(String s);
}
```

`PlainText` - simply return the text
```java
public class PlainText implements Text {
    public String format(String s){
        return s;
    }
}
```

The `TextDecorator` class serves as base decorator class which other classes extend. 
This decorator class is like a core class which helps in combining different functionalities.

```java
public abstract class TextDecorator implements Text {
    private Text text;
 
    public TextDecorator(Text text) {
        this.text = text;
    }
 
    public abstract String format(String s);
}
```

The `AllCaps` class takes the input and formats it to uppercase
```java
public class AllCaps extends TextDecorator{
    public AllCaps(Text text){
        super(text);
    }

    public String format(String s){
        return text.format(s).toUpperCase();
    }
}
```

The `StringConcat` class calls format and then concatenates the input string
```java
public class StringConcat extends TextDecorator{
    public StringConcat(Text text){
        super(text);
    }

    public String format(String s){
        return text.format(s).concat(s);
    }
}
```
`ReplaceThisWithThat` - the class which replaces text “this” with “that”
```java
public class ReplaceThisWithThat extends TextDecorator{
    public ReplaceThisWithThat(Text text){
        super(text);
    }

    public String format(String s){
        return text.format(s).replaceAll("this","that");
    }
}
```

`Brackets` just wraps string with "[", "]" chars
```java
public class Brackets extends TextDecorator{
    public Brackets(Text text){
        super(text);
    }

    public String format(String s){
        return '[' + text.format(s) + ']';
    }
}
```

`Main` class to run the decorators
```java
public class Main {
    public static void main(String[] args){
        Text baseText = new PlainText();
        Text t = new ReplaceThisWithThat(new StringConcat(new AllCaps(new Brackets(baseText))) );

        System.out.println(t.format("this is some random text"));
    }
}
```

Output:
`[THIS IS SOME RANDOM TEXT]that is some random text`

So execution is started from the end from `Bracket -> AllCaps -> StringConcat -> ReplaceThisWithThat`
1. Firstly we wrap initial string with brackets and get string like this: `[this is some random text]`
2. Then we format string to upper case: `[THIS IS SOME RANDOM TEXT]`
3. Concat initial string to the result of two previous steps: `[THIS IS SOME RANDOM TEXT]this is some random text`
4. Replace all `this` with `that`:  `[THIS IS SOME RANDOM TEXT]that is some random text`
