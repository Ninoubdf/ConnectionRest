import java.io.File;
import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.FileEntity;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class test {
	public static void main(String[] args) throws Exception{
		  String LOGIN = "LOGIN";
			String PASSWORD = "PASSWORD";
		String blogid = "7184995e-facd-4379-84a3-992cad39dcdc";
		String filepath = "C:\\Temp\\logoConnelink.gif";
		HttpClientContext context = HttpClientContext.create(); 
		UsernamePasswordCredentials creds = new UsernamePasswordCredentials(LOGIN, PASSWORD);
		CredentialsProvider credsProvider  = new BasicCredentialsProvider();
		credsProvider.setCredentials(AuthScope.ANY, creds);
		context.setCredentialsProvider(credsProvider);
		// Add AuthCache to the execution context

		
		   AuthCache authCache = new BasicAuthCache();
	       context.setAuthCache(authCache);
	       System.out.println("test");
		
		HttpClient httpClient = HttpClients.custom()
				  .setDefaultCredentialsProvider(credsProvider)
				  .build();

		 
		File file = new File(filepath); 
		 HttpPost request = new HttpPost("https://apps.ce.collabserv.com/blogs/"+blogid+"/api/media");
		 request.addHeader("Content-type", "image/gif");
		 request.setEntity(new FileEntity(file));
			try {
				HttpResponse response = httpClient.execute(request,context);
				 HttpEntity respEntity = response.getEntity();

				    if (respEntity != null) {
				        // EntityUtils to get the response content
				        String content =  EntityUtils.toString(respEntity);
				        System.out.println("Response content : "+content);
				    }
				int statusCode = response.getStatusLine().getStatusCode();
		    	System.out.println("Response status : "+statusCode);
		    	// renvoie 201 si OK
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		
}
}

