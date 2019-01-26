package lt.karpovic.fileservice.common;

public class AppConfig {
    public static final String TXT_FILE_EXTENSION = "txt";

    public static final String ALL_SUPPORTED_EXTENSIONS = TXT_FILE_EXTENSION;

    private AppConfig() {
        throw new IllegalStateException(MsgConst.UTILITY_CLASS);
    }

}
