package fi.tamk.tiko.jsonparser;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Dropbox class
 * Used to save files to dropbox
 *
 * @author Toni Vänttinen (toni.vanttinen@cs.tamk.fi)
 * @version V1
 * @since 2018-12-17
 */
public class DropboxSaver {
    /**
     * Access token where the file is saved
     */
    private static final String ACCESS_TOKEN = "oY9YcCe30dAAAAAAAAAAaRjZBNEBUfzyZJ5xLhbwDaBehAW_cPkZ-fl5VsjbH1JC";

    /**
     * Saves the file to dropbox
     *
     * First the dropbox client is accessed
     * and then the selected file is saved there
     *
     * @param fileName file that is to be saved
     * @throws DbxException dropbox error
     * @throws IOException run error
     */
    public static void save(String fileName) throws DbxException, IOException {

        DbxRequestConfig config = new DbxRequestConfig("dropbox/java-tutorial", "en_US");
        DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);

        try (InputStream in = new FileInputStream(fileName)) {
            FileMetadata metadata = client.files().uploadBuilder("/" + fileName)
                    .uploadAndFinish(in);
        }
    }
}