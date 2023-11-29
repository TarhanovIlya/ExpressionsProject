import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExpressionExtractor {

    // returns index of " " before first digit and index of " " after last. May need trim
    //  (\s||^)\(*\d+(\.\d+)?([+\-*()/\d])*(\s||$) -backup regex.

    //TODO create regex that can handle double type equations
    private Pattern pattern = Pattern.compile("(\\s||^)\\(*(\\d+(\\.\\d+)?([+\\-*()\\/\\d])*)+\\)*(\\s||$)");
    private Matcher matcher;
    String string;


     ExpressionExtractor(String str){
         string = str;
         matcher = pattern.matcher(str);


         if(!matcher.find(0)){
            throw new RuntimeException("String parsed to ExpressionExtractor contains no expressions");
         }
    }

    //will throw exception if no next math equation is found
    public int GetStart(){
        return matcher.start();
    }

    //will throw exception if no next math equation is found
    public int GetEnd(){
        return matcher.end();
    }

    public boolean GoToNext(){
        return matcher.find(matcher.end());
    }

    public String GetEquation(){
         return string.substring(this.GetStart(),this.GetEnd()).trim();

    }


}
