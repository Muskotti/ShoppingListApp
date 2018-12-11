package fi.tamk.tiko.gui;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.users.FullAccount;

public class DropboxSaver {
    private String ACCESS_TOKEN = "oY9YcCe30dAAAAAAAAAAaRjZBNEBUfzyZJ5xLhbwDaBehAW_cPkZ-fl5VsjbH1JC";

    public void save() throws DbxException {
        // Create Dropbox client
        DbxRequestConfig config = DbxRequestConfig.newBuilder("dropbox/java-tutorial").build();
        DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);

        // Get current account info
        FullAccount account = client.users().getCurrentAccount();
        System.out.println(account.getName().getDisplayName());
    }
}