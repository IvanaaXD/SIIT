package support;

import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.ListMessagesResponse;
import com.google.api.services.gmail.model.Message;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;

// ...

public class MailReader {

  // ...


  /**
   * List all Messages of the user's mailbox matching the query.
   *
   * @param service Authorized Gmail API instance.
   * @param userId User's email address. The special value "me"
   * can be used to indicate the authenticated user.
   * @param query String used to filter the Messages listed.
   * @throws IOException
   */
  public static List<Message> listMessagesMatchingQuery(Gmail service, String userId,
      String query, long pageSize, boolean onlyFirstPage) throws IOException {
    ListMessagesResponse response = service.users().messages().list(userId).setQ(query).setMaxResults(pageSize).execute();

    List<Message> messages = new ArrayList<Message>();
    while (response.getMessages() != null) {
      messages.addAll(response.getMessages());
      
	  if (response.getNextPageToken() != null && !onlyFirstPage) {
		  String pageToken = response.getNextPageToken();
		  response = service.users().messages().list(userId).setQ(query)
				  .setPageToken(pageToken).execute();
	  } else {
		  break;
	  }
    }

    //for (Message message : messages) {
    //   System.out.println(message.toPrettyString());
    //}

    return messages;
  }

  /**
   * List all Messages of the user's mailbox with labelIds applied.
   *
   * @param service Authorized Gmail API instance.
   * @param userId User's email address. The special value "me"
   * can be used to indicate the authenticated user.
   * @param labelIds Only return Messages with these labelIds applied.
   * @throws IOException
   */
  public static List<Message> listMessagesWithLabels(Gmail service, String userId,
      List<String> labelIds, long maxResults, boolean onlyFirstPage) throws IOException {
    ListMessagesResponse response = service.users().messages().list(userId)
        .setLabelIds(labelIds).setMaxResults(maxResults).execute();

    List<Message> messages = new ArrayList<Message>();
    while (response.getMessages() != null) {
      messages.addAll(response.getMessages());

      if (response.getNextPageToken() != null && !onlyFirstPage) {
        String pageToken = response.getNextPageToken();
        response = service.users().messages().list(userId).setLabelIds(labelIds)
            .setPageToken(pageToken).execute();
      } else {
        break;
      }
    }

    /*for (Message message : messages) {
      System.out.println(message.toPrettyString());
    }*/

    return messages;
  }
  
  public static Message getMessage(Gmail service, String userId, String messageId)
  {
      try
      {
          return service.users().messages().get(userId, messageId).execute();
      }
      catch (Exception e)
      {
           System.out.println("An error occurred: " + e.getStackTrace());
      }

      return null;
  }

  public static MimeMessage getMimeMessage(Gmail service, String userId, String messageId)
	      throws IOException, MessagingException {
	    Message message = service.users().messages().get(userId, messageId).setFormat("raw").execute();

	    byte[] emailBytes = Base64.getUrlDecoder().decode(message.getRaw().getBytes());

	    Properties props = new Properties();
	    Session session = Session.getInstance(props);

	    MimeMessage email = new MimeMessage(session, new ByteArrayInputStream(emailBytes));

	    return email;
	  }

}
