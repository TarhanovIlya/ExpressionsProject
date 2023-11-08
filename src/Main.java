import com.fathzer.soft.javaluator.DoubleEvaluator;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        String str="expression 1-3+5 and (8*5)/4+2 (9-5)*2";
        DoubleEvaluator evaluator = new DoubleEvaluator();
        ExpressionExtractor extractor = new ExpressionExtractor(str);
        List<String> strings = new ArrayList<>();

        do {
           strings.add(str.substring(extractor.GetStart(),extractor.GetEnd()).trim());
        }while(extractor.GoToNext());

        for (String s:strings) {
            Double res = evaluator.evaluate(s);
            System.out.println(res);
        }

    }
}