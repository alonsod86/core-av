package org.vedas.av.services;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Acl;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

@Service
public class StorageService {

    private static Storage storage = null;

    static {
        StorageOptions storageOptions = null;
        try {
            storageOptions = StorageOptions.newBuilder()
                .setProjectId("vedas-223316")
                .setCredentials(GoogleCredentials.fromStream(new
                    FileInputStream("/stratio/l/core-av/credentials.json"))).build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        storage = storageOptions.getService();
//        storage = StorageOptions.getDefaultInstance().getService();
    }

    /**
     * Uploads a file to Google Cloud Storage to the bucket specified in the BUCKET_NAME
     * environment variable, appending a timestamp to end of the uploaded filename.
     */
    @SuppressWarnings("deprecation")
    public String uploadFile(File file, final String bucketName) throws IOException {
        final String fileName = file.getName();

        // the inputstream is closed by default, so we don't need to close it here
        BlobInfo blobInfo =
            storage.create(
                BlobInfo
                    .newBuilder(bucketName, fileName)
                    // Modify access list to allow all users with link to read file
                    .setAcl(new ArrayList<>(Arrays.asList(Acl.of(Acl.User.ofAllUsers(), Acl.Role.READER))))
                    .build(),
                new FileInputStream(file));
        // return the public download link
        return blobInfo.getMediaLink();
    }
}
