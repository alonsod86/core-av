package org.vedas.av.services;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.vedas.av.services.creds.GoogleSpecs;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles({"no-nlp"})
public class TranscriptionServiceTestIT extends GoogleSpecs {

    @Autowired
    private TranscriptionService transcriptionService;

    @Autowired
    private AudioService audioService;

    @Before
    public void init() throws Exception {
        String credentials = new File("credentials.json").getAbsolutePath();
        injectEnvironmentVariable("GOOGLE_APPLICATION_CREDENTIALS", credentials);
    }

    @Test
    public void testLongTranscription() throws InterruptedException, ExecutionException, IOException {
        transcriptionService.longTranscription("gs://test-vedas/audio.ogg", "es-ES");
    }

    @Test
    public void testShortTranscription() {
        transcriptionService.shortTranscription("/home/dgutierrez/Descargas/test_1m.flac", "es-ES");
    }

    @Test
    public void testTranscription() throws Exception {
        transcriptionService
            .transcribeModelSelection("/stratio/l/core-av/src/test/resources/long_audio.mp3", "es-ES");
    }
}
