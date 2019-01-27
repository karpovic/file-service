package lt.karpovic.fileservice.common;

public class AppConfig {
    public static final String TXT_FILE_EXTENSION = "txt";

    public static final String ALL_SUPPORTED_EXTENSIONS = TXT_FILE_EXTENSION;

    public static final String FILE_ENCODING = "UTF-8";

    public static final String REG_EX_FOR_SPLITTING_WORDS = "\\s+";

    public static final String REG_EX_FOR_LEAVE_ONLY_LETTERS = "[^a-zA-Z]+";

    public static final String SYMBOL_FOR_REPLACING_NOT_LETTERS = "";

    private AppConfig() {
        throw new IllegalStateException(MsgConst.UTILITY_CLASS);
    }

}
