package lt.karpovic.fileservice.common;

public class MsgConst {

    public static final String UTILITY_CLASS = "Utility class; ";

    public static final String VALIDATION_FILE_IS_EMPTY = "Got empty file; ";

    public static final String VALIDATION_NOT_SUPPORTED_FILE = "Not supported file extension. Supported: %s; ";

    public static final String APP_STATUS_NUMBER_OF_UPLOAD_FILES = "Number of upload files: %s";

    private MsgConst() {
        throw new IllegalStateException(UTILITY_CLASS);
    }
}
