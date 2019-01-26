package lt.karpovic.fileservice.service;

import lt.karpovic.fileservice.common.AppConfig;
import lt.karpovic.fileservice.common.MsgConst;
import lt.karpovic.fileservice.common.ResponseStatusEnum;
import lt.karpovic.fileservice.model.ServiceResponse;
import lt.karpovic.fileservice.service.impl.FileServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.io.InputStream;

@RunWith(MockitoJUnitRunner.class)
public class FileServiceTest {

    private static final String FILE_NAME_TXT = "uploadFile.txt";
    private static final String FILE_NAME_CSV = "uploadFile.csv";

    @InjectMocks
    FileServiceImpl fileServiceImpl;

    @Test
    public void initializeTest() {
        Assert.assertNotNull(fileServiceImpl);
    }

    @Test
    public void uploadFileTest() throws IOException {
        ServiceResponse response = fileServiceImpl.uploadFile(null);
        Assert.assertEquals(MsgConst.VALIDATION_FILE_IS_EMPTY, response.getResponseMsg());
        Assert.assertEquals(ResponseStatusEnum.CLIENT_ERROR, response.getStatus());
        Assert.assertEquals(ResponseStatusEnum.CLIENT_ERROR.getStatusCode(), response.getStatusCode());


        InputStream inputstream = getClass().getClassLoader().getResourceAsStream(FILE_NAME_TXT);
        Assert.assertNotNull(inputstream);
        MockMultipartFile file = new MockMultipartFile(FILE_NAME_TXT, FILE_NAME_TXT, MediaType.MULTIPART_FORM_DATA_VALUE, inputstream);
        Assert.assertNotNull(file);
        response = fileServiceImpl.uploadFile(file);
        Assert.assertEquals("", response.getResponseMsg());
        Assert.assertEquals(ResponseStatusEnum.COMPLETED_SUCCESS, response.getStatus());
        Assert.assertEquals(ResponseStatusEnum.COMPLETED_SUCCESS.getStatusCode(), response.getStatusCode());

        inputstream = getClass().getClassLoader().getResourceAsStream(FILE_NAME_CSV);
        Assert.assertNotNull(inputstream);
        file = new MockMultipartFile(FILE_NAME_CSV, FILE_NAME_CSV, MediaType.MULTIPART_FORM_DATA_VALUE, inputstream);
        Assert.assertNotNull(file);
        response = fileServiceImpl.uploadFile(file);
        Assert.assertEquals(String.format(MsgConst.VALIDATION_NOT_SUPPORTED_FILE, AppConfig.ALL_SUPPORTED_EXTENSIONS), response.getResponseMsg());
        Assert.assertEquals(ResponseStatusEnum.CLIENT_ERROR, response.getStatus());
        Assert.assertEquals(ResponseStatusEnum.CLIENT_ERROR.getStatusCode(), response.getStatusCode());
    }

    @Test
    public void getNumberOfFilesTest() {
        ServiceResponse response = fileServiceImpl.getNumberOfFiles();
        Assert.assertNotNull(response);
        Assert.assertEquals(ResponseStatusEnum.COMPLETED_SUCCESS, response.getStatus());
        Assert.assertEquals(ResponseStatusEnum.COMPLETED_SUCCESS.getStatusCode(), response.getStatusCode());
    }

}