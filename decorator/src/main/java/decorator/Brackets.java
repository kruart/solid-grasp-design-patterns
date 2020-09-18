package decorator;

import model.Text;

public class Brackets extends TextDecorator{
    public Brackets(Text text){
        super(text);
    }

    public String format(String s){
        return '[' + text.format(s) + ']';
    }
}