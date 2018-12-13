package org.vedas.av.services;

import org.springframework.stereotype.Service;
import ws.schild.jave.*;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

@Service
public class AudioService {

    private Encoder encoder = new Encoder();

    public void mp3toFlac(File source, File target) throws Exception {
        if (target.exists()) {
            target.delete();
        }

        Encoder encoder = new Encoder();
        PListener listener = new PListener();
        AudioAttributes audio = new AudioAttributes();
        EncodingAttributes attrs = new EncodingAttributes();
        attrs.setFormat("flac"); //mp3
        attrs.setAudioAttributes(audio);
        attrs.setMapMetaData(true);
        encoder.encode(new MultimediaObject(source), target, attrs, listener);
    }

    public void mp3toOgg(File source, File target) throws Exception {
        if (target.exists()) {
            target.delete();
        }

        Encoder encoder = new Encoder();
        PListener listener = new PListener();
        AudioAttributes audio = new AudioAttributes();
        audio.setCodec("libopus");
        audio.setSamplingRate(16000);
        EncodingAttributes attrs = new EncodingAttributes();
        attrs.setAudioAttributes(audio);
        attrs.setMapMetaData(true);
        encoder.encode(new MultimediaObject(source), target, attrs, listener);
    }

    public void trim(File source, Long start, Long end) throws EncoderException {
        AudioAttributes audio = new AudioAttributes();
        audio.setCodec("libvorbis");

        EncodingAttributes attrs = new EncodingAttributes();
        attrs.setFormat("mpegvideo");
        attrs.setAudioAttributes(audio);
        Encoder encoder = new Encoder();
        encoder.encode(new MultimediaObject(source), new File("/tmp/test.mp3"), attrs);
    }

    protected class PListener implements EncoderProgressListener
    {
        private MultimediaInfo _info= null;
        private final List<String> _messages= new LinkedList<>();
        private final List<Integer> _progress= new LinkedList<>();

        @Override
        public void sourceInfo(MultimediaInfo info) {
            _info= info;
        }

        @Override
        public void progress(int permil) {
            _progress.add(permil);
        }

        @Override
        public void message(String message) {
            _messages.add(message);
        }

        /**
         * @return the _info
         */
        public MultimediaInfo getInfo() {
            return _info;
        }

        /**
         * @return the _messages
         */
        public List<String> getMessages() {
            return _messages;
        }

        /**
         * @return the _progress
         */
        public List<Integer> getProgress() {
            return _progress;
        }

    }
}
