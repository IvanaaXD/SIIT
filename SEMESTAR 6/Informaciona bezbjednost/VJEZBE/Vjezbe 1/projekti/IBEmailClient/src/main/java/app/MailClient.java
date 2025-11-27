package app;


import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;

import com.google.api.services.gmail.GmailScopes;

import com.google.api.services.gmail.Gmail;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;


public class MailClient {
    /** Application name. */
    private static final String APPLICATION_NAME =
        "IBEmailClient";

    /** Directory to store user credentials for this application. */
    //private static final java.io.File DATA_STORE_DIR = new java.io.File(
    //    System.getProperty("user.home"), ".credentials/ib-email-client");

    /** Global instance of the {@link FileDataStoreFactory}. */
    @SuppressWarnings("unused")
	private static FileDataStoreFactory DATA_STORE_FACTORY;

    /** Global instance of the JSON factory. */
    private static final JsonFactory JSON_FACTORY =
    		GsonFactory.getDefaultInstance();;

    /** Global instance of the HTTP transport. */
    private static HttpTransport HTTP_TRANSPORT;

    /** Global instance of the scopes required by this client.
     *
     * If modifying these scopes, delete your previously saved credentials
     * at ~/.credentials/ib-email-client
     */
    private static final List<String> SCOPES =
    	Arrays.asList(GmailScopes.MAIL_GOOGLE_COM);
    static {
        try {
        	//System.setProperty("https.proxyHost", "192.168.77.100");
            //System.setProperty("https.proxyPort", "8080");
            HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
            //DATA_STORE_FACTORY = new FileDataStoreFactory(DATA_STORE_DIR);
        } catch (Throwable t) {
            t.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Creates an authorized Credential object.
     * @return an authorized Credential object.
     * @throws IOException
     * @throws URISyntaxException 
     */
    public static Credential authorize() throws IOException, URISyntaxException {
        // Load client secrets.
    	
    	URI uri = MailClient.class.getClassLoader().getResource("client_secret.json").toURI();
		Path path = Paths.get(uri);
        File file = path.toFile();
        InputStream is = new FileInputStream(file);
    	
        GoogleClientSecrets clientSecrets =
            GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(is));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow =
                new GoogleAuthorizationCodeFlow.Builder(
                        HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                //.setDataStoreFactory(DATA_STORE_FACTORY)
                //.setAccessType("offline")
                .build();
        Credential credential = new AuthorizationCodeInstalledApp(
            flow, new LocalServerReceiver()).authorize("user");
        //System.out.println(
         //       "Credentials saved to " + DATA_STORE_DIR.getAbsolutePath());
        return credential;
    }

    /**
     * Build and return an authorized Gmail client service.
     * @return an authorized Gmail client service
     * @throws IOException
     * @throws URISyntaxException 
     */
    public static Gmail getGmailService() throws IOException, URISyntaxException {
        Credential credential = authorize();
        return new Gmail.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

}
