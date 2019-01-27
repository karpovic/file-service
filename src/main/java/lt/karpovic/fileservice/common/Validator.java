package lt.karpovic.fileservice.common;

import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

public class Validator {

    private Validator() {
        throw new IllegalStateException(MsgConst.UTILITY_CLASS);
    }

    public static String validateInputFile(MultipartFile file) {
        String result = "";
        if (file != null) {
            String extension = FilenameUtils.getExtension(file.getOriginalFilename()).toLowerCase();
            if (!extension.equals(AppConfig.TXT_FILE_EXTENSION)) {
                result = String.format(MsgConst.VALIDATION_NOT_SUPPORTED_FILE, AppConfig.ALL_SUPPORTED_EXTENSIONS);
            }
        } else {
            result = MsgConst.VALIDATION_FILE_IS_EMPTY;
        }

        return result;
    }


    public static String validateIntervalNumber(int intervalNumber) {
        String result = "";
        if (!(AppConfig.MIN_PART_NUMBER <= intervalNumber && intervalNumber <= AppConfig.MAX_PART_NUMBER)) {
            result = String.format(MsgConst.VALIDATION_NOT_SUPPORTED_INTERVAL_NUMBER, AppConfig.MIN_PART_NUMBER, AppConfig.MAX_PART_NUMBER);
        }
        return result;
    }

}
