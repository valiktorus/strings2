import java.util.Enumeration;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Runner {
    public static void main(String[] args) {
        ResourceBundle rb = ResourceBundle.getBundle(Constants.PROPERTY_FILE, Locale.ENGLISH);
        Enumeration<String> keys = rb.getKeys();
        double sum = 0;
        int errorLines = 0;
        String indexValue;
        String key;
        String requiredValue;
        while (keys.hasMoreElements()){
            try{
                key = keys.nextElement();
                if (key.matches(Constants.LINES_STARTS_WITH_INDEX)){
                    Pattern pattern = Pattern.compile(Constants.CORRECT_INDEX_LINE);
                    Matcher matcher = pattern.matcher(key);
                    if (matcher.find()){
                        indexValue = rb.getString(key);
                        if (indexValue.matches(Constants.CORRECT_INDEX_VALUE)){
                            requiredValue = rb.getString(Constants.VALUE + matcher.group(1) + indexValue);
                                sum += Double.parseDouble(requiredValue);
                        }else {
                            errorLines++;
                        }
                    }else {
                        errorLines++;
                    }
                }
            }catch (MissingResourceException|NumberFormatException e){
                errorLines++;
            }
        }
        System.out.printf(Constants.OUTPUT_LINE, sum , errorLines);

    }
}
