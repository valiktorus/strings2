import java.util.regex.Pattern;

public class Constants {
    public static final String PROPERTY_FILE = "in1";
    public static final String VALUE = "value";
    public static final String OUTPUT_LINE = "sum = %s%nerror-lines = %d";
    public static final int VALUE_GROUP = 1;
    public static final int NOT_VALID_PART_GROUP = 2;
    public static final String REGEX = "^%s([1-9]\\d*)|(.*?)$";
    public static final String INDEX = "index";
    public static final Pattern INDEX_PATTERN = Pattern.compile(String.format(REGEX, INDEX));
    public static final Pattern VALUE_PATTERN = Pattern.compile(String.format(REGEX, ""));
}
