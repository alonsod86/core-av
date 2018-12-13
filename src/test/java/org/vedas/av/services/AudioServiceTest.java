package org.vedas.av.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import ws.schild.jave.EncoderException;

import java.io.File;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class AudioServiceTest {

    @Spy
    private AudioService audioService;

    @Test
    public void testMp3ToFlac() throws Exception {
        audioService.mp3toFlac(new File("/stratio/l/core-av/src/test/resources/long_audio.mp3"),
            new File("/tmp/audio.flac"));
    }

    @Test
    public void testMp3ToOgg() throws Exception {
        audioService.mp3toOgg(new File("/stratio/l/core-av/src/test/resources/long_audio.mp3"),
            new File("/tmp/audio_16000.ogg"));
    }

    @Test
    public void testAudioService() throws EncoderException {
        audioService.trim(new File("/home/dgutierrez/Descargas/test_audio.mp3"), null, null);
    }
}
