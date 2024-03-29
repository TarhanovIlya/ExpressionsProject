import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExpressionExtractor {


    // expression must not contain spaces between numbers and operators and have space before and after : " 1+2*(3+4/5) "
    private Pattern pattern = Pattern.compile("( ||^)\\(*(\\d+(\\.\\d+)?([+\\-*()\\/\\d])*)+\\)*( )(\\s||$)");
    private Matcher matcher;
    private String string;
    private boolean isValid = false;


    public boolean isValid() {
        return isValid;
    }

    ExpressionExtractor(String str){
         string = str;
         matcher = pattern.matcher(str);

        if(!matcher.find(0)) {
            isValid = false;
        }
        else{
            isValid = true;
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
