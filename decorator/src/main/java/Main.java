import decorator.AllCaps;
import decorator.Brackets;
import decorator.ReplaceThisWithThat;
import decorator.StringConcat;
import model.PlainText;
import model.Text;

public class Main {
    public static void main(String[] args){
        Text baseText = new PlainText();
        Text t = new ReplaceThisWithThat(new StringConcat(new AllCaps(new Brackets(baseText))) );

        System.out.println(t.format("this is some random text"));
    }
}
