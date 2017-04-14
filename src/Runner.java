import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Runner {
    public static void main(String[] args) {
        ResourceBundle rb = ResourceBundle.getBundle("in1", Locale.ENGLISH);
        Enumeration<String> keys = rb.getKeys();
        double sum = 0;
        int errorLines = 0;
        String key;
        String keyValue;
        String indexNumber = null;
        String requiredValue;
        while (keys.hasMoreElements()){
            key = keys.nextElement();
            keyValue = rb.getString(key);
            Pattern pattern = Pattern.compile("^index(\\d+)$");
            Matcher matcher = pattern.matcher(key);
            if (matcher.find()){
                indexNumber = matcher.group(1);
                try {
                    requiredValue = rb.getString("value" + indexNumber + keyValue).trim();
                    sum += Double.parseDouble(requiredValue);
                }catch (Exception e){
                    errorLines++;
                }
            }

        }
        System.out.println(sum);
        System.out.println(errorLines);

    }
}
