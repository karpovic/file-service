package lt.karpovic.fileservice.common;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.io.InputStream;

@RunWith(MockitoJUnitRunner.class)
public class ValidatorTest {

    private static final String FILE_NAME_TXT = "uploadFile.txt";
    private static final String FILE_NAME_CSV = "uploadFile.csv";

    @Test
    public void validateInputFileTest() throws IOException {
        String result = Validator.validateInputFile(null);
        Assert.assertEquals(MsgConst.VALIDATION_FILE_IS_EMPTY, result);

        InputStream inputstream = getClass().getClassLoader().getResourceAsStream(FILE_NAME_TXT);
        Assert.assertNotNull(inputstream);
        MockMultipartFile file = new MockMultipartFile(FILE_NAME_TXT, FILE_NAME_TXT, MediaType.MULTIPART_FORM_DATA_VALUE, inputstream);
        Assert.assertNotNull(file);
        result = Validator.validateInputFile(file);
        Assert.assertEquals("", result);

        inputstream = getClass().getClassLoader().getResourceAsStream(FILE_NAME_CSV);
        Assert.assertNotNull(inputstream);
        file = new MockMultipartFile(FILE_NAME_CSV, FILE_NAME_CSV, MediaType.MULTIPART_FORM_DATA_VALUE, inputstream);
        Assert.assertNotNull(file);
        result = Validator.validateInputFile(file);
        Assert.assertEquals(String.format(MsgConst.VALIDATION_NOT_SUPPORTED_FILE, AppConfig.ALL_SUPPORTED_EXTENSIONS), result);

    }

}