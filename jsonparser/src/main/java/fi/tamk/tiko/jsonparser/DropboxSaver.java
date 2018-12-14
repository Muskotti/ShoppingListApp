package fi.tamk.tiko.jsonparser;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class DropboxSaver {
    private static final String ACCESS_TOKEN = "oY9YcCe30dAAAAAAAAAAaRjZBNEBUfzyZJ5xLhbwDaBehAW_cPkZ-fl5VsjbH1JC";

    public static void save(String fileName) throws DbxException, IOException {

        DbxRequestConfig config = new DbxRequestConfig("dropbox/java-tutorial", "en_US");
        DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);

        try (InputStream in = new FileInputStream(fileName)) {
            FileMetadata metadata = client.files().uploadBuilder("/" + fileName)
                    .uploadAndFinish(in);
        }
    }
}