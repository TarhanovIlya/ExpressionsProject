import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExpressionExtractor {

    // returns index of " " before first digit and index of " " after last. May need trim
    private Pattern pattern = Pattern.compile("(\\s||^)\\({0,1}\\d+(\\.\\d+)?([+\\-\\*\\(\\)\\/\\d])*(\\s||$)");
    private Matcher matcher;



    ExpressionExtractor(String str){
        matcher = pattern.matcher(str);
        matcher.find(0);
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


}
