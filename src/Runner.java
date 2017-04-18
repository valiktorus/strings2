import java.util.Enumeration;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Runner {
    private static final int VALUE_GROUP = 1;
    private static final int NOT_VALID_PART_GROUP = 2;
    private static final String REGEX = "^%s([1-9]\\d*)|(.*)";
    private static final String INDEX = "index";
    private static final Pattern INDEX_PATTERN = Pattern.compile(String.format(REGEX, INDEX));
    private static final Pattern VALUE_PATTERN = Pattern.compile(String.format(REGEX, ""));

    public static void main(String[] args) {
        ResourceBundle rb = ResourceBundle.getBundle(Constants.PROPERTY_FILE, Locale.ENGLISH);
        Enumeration<String> keys = rb.getKeys();
        double sum = 0;
        int errorLines = 0;
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            if (!key.startsWith("index")) {
                continue;
            }
            Matcher matcher = INDEX_PATTERN.matcher( key );
            if (isInvalidValue(matcher)) {
                errorLines++;
                continue;
            }
            String indexValue = matcher.group(VALUE_GROUP);
            try {
                String value = rb.getString( key ).trim();
                matcher = VALUE_PATTERN.matcher(value);
                if (isInvalidValue(matcher)) {
                    errorLines++;
                    continue;
                }
                String requiredValue = rb.getString(Constants.VALUE + indexValue + matcher.group(VALUE_GROUP)).trim();
                sum += Double.parseDouble(requiredValue);
            } catch (MissingResourceException | NumberFormatException e){
                errorLines++;
            }
        }
        System.out.printf(Constants.OUTPUT_LINE, sum , errorLines);
    }

    private static boolean isInvalidValue(Matcher matcher) {
        return !matcher.find() || matcher.group(NOT_VALID_PART_GROUP) != null;
    }
   /* public static void main(String[] args) {
        ResourceBundle rb = ResourceBundle.getBundle(Constants.PROPERTY_FILE, Locale.ENGLISH);
        Enumeration<String> keys = rb.getKeys();
        double sum = 0;
        int errorLines = 0;
        String indexValue;
        String key;
        String requiredValue;
        Pattern pattern = Pattern.compile(Constants.CORRECT_INDEX);
        while (keys.hasMoreElements()){
            key = keys.nextElement();
            Matcher matcher = pattern.matcher(key);
            if (matcher.find()){
                if (!matcher.group(3).isEmpty() && matcher.group(2).isEmpty() && matcher.group(4).isEmpty()){
                    try{
                        indexValue = rb.getString(key).trim();
                        if (checkNumber(indexValue)){
                            requiredValue = rb.getString(Constants.VALUE + matcher.group(3) + indexValue).trim();
                            sum += Double.parseDouble(requiredValue);
                        }else {
                            errorLines++;
                        }
                    }catch (MissingResourceException |NumberFormatException e){
                        errorLines++;
                    }
                }else {
                    errorLines++;
                }
            }
        }
        System.out.printf(Constants.OUTPUT_LINE, sum , errorLines);
    }
    private static boolean checkNumber(String number){
        Pattern pattern = Pattern.compile(Constants.NUMBER_PATTERN);
        Matcher matcher = pattern.matcher(number);
        return matcher.find() && matcher.group(1).isEmpty() && matcher.group(3).isEmpty() && !matcher.group(2).isEmpty();
    }*/
}
