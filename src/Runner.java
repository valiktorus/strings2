import java.util.Enumeration;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;

public class Runner {
    public static void main(String[] args) {
        ResourceBundle rb = ResourceBundle.getBundle(Constants.PROPERTY_FILE, Locale.ENGLISH);
        Enumeration<String> keys = rb.getKeys();
        double sum = 0;
        int errorLines = 0;
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            if (!key.startsWith(Constants.INDEX)) {
                continue;
            }
            Matcher matcher = Constants.INDEX_PATTERN.matcher(key);
            if (isInvalidValue(matcher)) {
                errorLines++;
                continue;
            }
            String indexValue = matcher.group(Constants.VALUE_GROUP);
            try {
                String value = rb.getString(key).trim();
                matcher = Constants.VALUE_PATTERN.matcher(value);
                if (isInvalidValue(matcher)) {
                    errorLines++;
                    continue;
                }
                String requiredValue = rb.getString(Constants.VALUE + indexValue + matcher.group(Constants.VALUE_GROUP)).trim();
                sum += Double.parseDouble(requiredValue);
            } catch (MissingResourceException | NumberFormatException e){
                errorLines++;
            }
        }
        System.out.printf(Constants.OUTPUT_LINE, sum , errorLines);
    }

    private static boolean isInvalidValue(Matcher matcher) {
        return !matcher.find() || matcher.group(Constants.NOT_VALID_PART_GROUP) != null;
    }
}