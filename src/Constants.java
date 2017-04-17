public class Constants {
    public static final String PROPERTY_FILE = "in1";
    public static final String LINES_STARTS_WITH_INDEX = "index.*";
    public static final String NUMBER_PATTERN = "(.*?)([1-9]\\d*)(.*)";
    public static final String CORRECT_INDEX = "^(index)" + NUMBER_PATTERN;

    public static final String CORRECT_INDEX_VALUE = "^[1-9]\\d*$";
    public static final String VALUE = "value";
    public static final String OUTPUT_LINE = "sum = %s%nerror-lines = %d";
}
