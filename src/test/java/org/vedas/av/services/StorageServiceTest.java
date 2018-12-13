package org.vedas.av.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.vedas.av.services.creds.GoogleSpecs;

import java.io.File;
import java.io.IOException;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class StorageServiceTest extends GoogleSpecs {

    @Spy
    private StorageService storageService;

    @Before
    public void init() throws Exception {
        String credentials = new File("credentials.json").getAbsolutePath();
        injectEnvironmentVariable("GOOGLE_APPLICATION_CREDENTIALS", credentials);
    }

    @Test
    public void testUploadFile() throws IOException {
        storageService.uploadFile(new File("/stratio/l/core-av/src/test/resources/long_audio.mp3"),
            "test-vedas");
    }

    @Test
    public void testUploadOgg() throws IOException {
        storageService.uploadFile(new File("/tmp/audio.ogg"), "test-vedas");
    }

}
