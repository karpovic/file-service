package lt.karpovic.fileservice.common;

public class AppConfig {
    public static final String TXT_FILE_EXTENSION = "txt";

    public static final String ALL_SUPPORTED_EXTENSIONS = TXT_FILE_EXTENSION;

    public static final String FILE_ENCODING = "UTF-8";

    public static final String ATTACHMENT_HEADER = "attachment;filename=";

    public static final String REG_EX_FOR_SPLITTING_WORDS = "\\s+";

    public static final String REG_EX_FOR_LEAVE_ONLY_LETTERS = "[^a-zA-Z]+";

    public static final String SYMBOL_FOR_REPLACING_NOT_LETTERS = "";

    public static final String SYMBOL_FOR_SEPARATE_WORD_AND_NUMBER = " ";

    public static final String SPLIT_WORDS = "; ";

    public static final String SPLIT_WORDS_NEW_LINE = "; " + System.lineSeparator();

    public static final String SPLIT_SYMBOL = "-";

    public static final String FILE_NAME_PREFIX = "file_";

    public static final String FILE_EXTENSION_SPLIT = ".";

    public static final char START_1 = 'a';
    public static final char END_1 = 'g';
    public static final String WORDS_1 = START_1 + SPLIT_SYMBOL + END_1;

    public static final char START_2 = 'h';
    public static final char END_2 = 'n';
    public static final String WORDS_2 = START_2 + SPLIT_SYMBOL + END_2;

    public static final char START_3 = 'o';
    public static final char END_3 = 'u';
    public static final String WORDS_3 = START_3 + SPLIT_SYMBOL + END_3;

    public static final char START_4 = 'v';
    public static final char END_4 = 'z';
    public static final String WORDS_4 = START_4 + SPLIT_SYMBOL + END_4;

    public static final int MIN_PART_NUMBER = 1;
    public static final int MAX_PART_NUMBER = 4;

    private AppConfig() {
        throw new IllegalStateException(MsgConst.UTILITY_CLASS);
    }

}
