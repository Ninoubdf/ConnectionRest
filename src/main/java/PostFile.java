import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import com.ibm.sbt.services.client.connections.forums.ForumService;
import com.ibm.sbt.services.endpoints.ConnectionsBasicEndpoint;

import java.util.Base64;
import java.net.HttpURLConnection;
import java.net.URL;

public class PostFile {
  public static void main(String[] args) throws Exception {
	  

	  String LOGIN = "LOGIN";
	String PASSWORD = "PASSWORD";		 
	  DefaultHttpClient httpclient = new DefaultHttpClient();

	  httpclient.getCredentialsProvider().setCredentials(
	          new AuthScope(AuthScope.ANY_HOST, AuthScope.ANY_PORT), 
	          new UsernamePasswordCredentials(LOGIN, PASSWORD));
	//  String encoding = Base64.getEncoder().encodeToString((LOGIN+":"+PASSWORD).getBytes());
	  String encoding = LOGIN+":"+PASSWORD;
	//  URL url = new URL ("https://apps.ce.collabserv.com/manage/account/dashboardHandler/input");
	  URL url = new URL ("http://httpbin.org/post");
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
      connection.setRequestMethod("POST");
      connection.setDoOutput(true);
      connection.setRequestProperty  ("Authorization", "Basic " + encoding);
      connection.addRequestProperty("username", LOGIN);
      connection.addRequestProperty("password", PASSWORD);
      System.out.println("Basic " + encoding);
      InputStream content = (InputStream)connection.getInputStream();
      BufferedReader in   = 
          new BufferedReader (new InputStreamReader (content));
      String line;
      while ((line = in.readLine()) != null) {
          System.out.println(line);
      }
      
      
      
      /*
      
	  HttpPost httppost = new HttpPost("https://apps.ce.collabserv.com");

	  System.out.println("executing request " + httppost.getRequestLine());
	  HttpResponse response;
	  try {
		response = httpclient.execute(httppost);
	
	  HttpEntity entity = response.getEntity();

	  System.out.println("----------------------------------------");
	  System.out.println(response.getStatusLine());
	  if (entity != null) {
	      System.out.println("Response content length: " + entity.getContentLength());
	  }
	  if (entity != null) {
	      entity.consumeContent();
	  }

	  httpclient.getConnectionManager().shutdown();  
	  } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	  */
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	 	ConnectionsBasicEndpoint basicEndpoint = new ConnectionsBasicEndpoint();
		basicEndpoint.setUrl("https://apps.ce.collabserv.com");
		basicEndpoint.setUser(LOGIN);
		basicEndpoint.setPassword(PASSWORD);
		basicEndpoint.setForceTrustSSLCertificate(true);
    
		ForumService svc = new ForumService(basicEndpoint);

      String strFileContents = ""; 

	
	
  }
}
	/*
	System.out.println(file.length());
	 byte[] result = new byte[(int)file.length()];
	    try {
	      InputStream input = null;

	      try {

	        int totalBytesRead = 0;
	        input = new BufferedInputStream(new FileInputStream(file));
	        while(totalBytesRead < result.length){
	        
	          int bytesRemaining = result.length - totalBytesRead;
	          //input.read() returns -1, 0, or more :
	          int bytesRead = input.read(result, totalBytesRead, bytesRemaining); 
	          strFileContents += new String(result, 0, bytesRead);
	          if (bytesRead > 0){
	            totalBytesRead = totalBytesRead + bytesRead;
	          }
	        }
	        /*
	         the above style is a bit tricky: it places bytes into the 'result' array; 
	         'result' is an output parameter;
	         the while loop usually has a single iteration only.

	        System.out.println("Num bytes read: " + totalBytesRead);
	  //      System.out.println("bytes read: " +strFileContents);
	      }
	      finally {
	    	 System.out.println("Closing input stream.");
	        input.close();
	      }
	    }
	    catch (FileNotFoundException ex) {
	    	System.out.println("File not found.");
	      }
		System.out.println(result.toString());
	
	FileInputStream fis = null;
	*/
	
	
	
	/*
	try {
		
		CredentialsProvider provider = new BasicCredentialsProvider();
		UsernamePasswordCredentials credentials
		 = new UsernamePasswordCredentials(LOGIN, PASSWORD);
		provider.setCredentials(AuthScope.ANY, credentials);
		  

		context set credential
		
		HttpResponse response = client.execute(
		  new HttpGet("https://apps.ce.collabserv.com"));
		int statusCode = response.getStatusLine()
		  .getStatusCode();
		  
	//	assertThat(statusCode, equalTo(HttpStatus.SC_OK));
		System.out.println("Response status : "+statusCode);
	//	fis = new FileInputStream(file);
		DefaultHttpClient httpclient = new DefaultHttpClient(new BasicHttpParams());
		
		// server back-end URL
		HttpPost httppost = new HttpPost("https://apps.ce.collabserv.com/profiles/photo.do?key=cc70cadc-abd0-4c64-aa5b-c8a45e527974");
		httppost.setEntity(new FileEntity(file));
		
		
		/*MultipartEntity entity = new MultipartEntity();
		// set the file input stream and file name as arguments
		entity.addPart("file", new InputStreamBody(fis, file.getName()));
		
		httppost.setEntity(entity);
		httppost.setHeader("Content-type", "image/jpeg");
		httppost.setHeader("key", "cc70cadc-abd0-4c64-aa5b-c8a45e527974");
		
//		HttpResponse response = httpclient.execute(httppost);
//		System.out.println("Response status : "+response.getStatusLine().toString());
	

	 //   httpclient.getConnectionManager().shutdown();
		
//		 System.out.println("mpEntity " + entity.toString());
		// execute the request
//		Request requete = restClient.doPost("https://apps.ce.collabserv.com/profiles/photo.do?key=cc70cadc-abd0-4c64-aa5b-c8a45e527974&amp;lastMod=1517854026253").header("Content-type", "image/jpeg").header("Content-length", Integer.toString(strFileContents.length())).header("key", "cc70cadc-abd0-4c64-aa5b-c8a45e527974")
//				.body(strFileContents, "multipart/form-data");
//		System.out.println(requete.toString());
	//	HttpResponse response = httpclient.execute(httppost);
		
//		int statusCode = response.getStatusLine().getStatusCode();
//		HttpEntity responseEntity = response.getEntity();
//		String responseString = EntityUtils.toString(responseEntity, "UTF-8");
		
//		System.out.println("[" + statusCode + "] " + responseString);
		
	} catch (IOException e) {
		System.err.println("Unable to read file");
		e.printStackTrace();
	}
}
  
}

*/
    
    
    
    /*
     * HttpClient httpclient = new DefaultHttpClient();
    httpclient.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);

    HttpPost httppost = new HttpPost("https://apps.ce.collabserv.com/profiles/photo.do?key=cc70cadc-abd0-4c64-aa5b-c8a45e527974");
    File file = new File("C:\\Temp\\bla.jpg");

    MultipartEntity mpEntity = new MultipartEntity();
    ContentBody cbFile = new FileBody(file, "image/jpeg");
    mpEntity.addPart("userfile", cbFile);


    httppost.setEntity(mpEntity);
    System.out.println("mpEntity " + mpEntity.toString());
 
    System.out.println("executing request " + httppost.getRequestLine());
    
    Request response = restClient.doPost(httppost.toString());
    
 //   HttpResponse response = httpclient.execute(httppost);
 //   HttpEntity resEntity = response.getEntity();

    System.out.println(response.toString());
     * 
     * 
    		response.getStatusLine());
    if (resEntity != null) {
      System.out.println(EntityUtils.toString(resEntity));
    }
    if (resEntity != null) {
      resEntity.consumeContent();
    }
*/

 //   httpclient.getConnectionManager().shutdown();
  
