import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.auth.Credentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.entity.FileEntity;

import com.ibm.sbt.services.client.connections.forums.ForumService;
import com.ibm.sbt.services.endpoints.ConnectionsBasicEndpoint;

import java.util.Base64;
import java.net.HttpURLConnection;
import java.net.URL;

public class PostPhoto {
	public static void main() {
		  String LOGIN = "LOGIN";
			String PASSWORD = "PASSWORD";
	   CredentialsProvider credsProvider  = new BasicCredentialsProvider();
		UsernamePasswordCredentials creds = new UsernamePasswordCredentials(LOGIN, PASSWORD);
		credsProvider.setCredentials(AuthScope.ANY, creds);

		// Add AuthCache to the execution context
		HttpClientContext context = HttpClientContext.create();  
		HttpClient httpClient = HttpClientBuilder.create()
				  .setDefaultCredentialsProvider(credsProvider)
				  .build();
		 File file = new File("C:\\Temp\\bla.gif"); 
		 HttpPut request = new HttpPut("https://apps.ce.collabserv.com/profiles/photo.do?uid=203205163");
	        request.addHeader("Content-type", "image/jpeg");
	        request.setEntity(new FileEntity(file));
	        HttpResponse response;
			try {
				response = httpClient.execute(request);
				int statusCode = response.getStatusLine().getStatusCode();
		    	System.out.println("Response status : "+statusCode);
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		
}
  
}

