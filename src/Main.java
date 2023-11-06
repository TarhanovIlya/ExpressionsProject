import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        String str ="one equals (3-2)*1 or (6-3)/3+1-1  or 4-3";

        ExpressionExtractor extractor = new ExpressionExtractor(str);

        System.out.println(str.substring(extractor.GetStart(),extractor.GetEnd()));
        if(!extractor.GoToNext()){
            System.out.println("No next");
        }

        System.out.println(str.substring(extractor.GetStart(),extractor.GetEnd()));
        if(!extractor.GoToNext()){
            System.out.println("No next");
        }

        System.out.println(str.substring(extractor.GetStart(),extractor.GetEnd()));
        if(!extractor.GoToNext()){
            System.out.println("No next");
        }




    }
}