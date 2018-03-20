import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.FileEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import com.ibm.sbt.services.client.connections.profiles.Profile;
import com.ibm.sbt.services.client.connections.profiles.ProfileService;
import com.ibm.sbt.services.endpoints.ConnectionsBasicEndpoint;

public class connectCNX {

	public static void main(String[] args) throws Exception{

		System.out.println("la");
		  String LOGIN = "LOGIN";
			String PASSWORD = "PASSWORD";
		String url = "http://www.google.com/search?q=httpClient";

		HttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet(url);

		// add request header
	//	request.addHeader("User-Agent", USER_AGENT);
		HttpResponse response = client.execute(request);

		System.out.println("Response Code : "
		                + response.getStatusLine().getStatusCode());

		BufferedReader rd = new BufferedReader(
			new InputStreamReader(response.getEntity().getContent()));

		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
		System.out.println(result);
		
		String url2 = "http://httpbin.org/post";

		HttpClient client2 = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost(url2);

		// add header
	//	post.setHeader("User-Agent", USER_AGENT);

		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		urlParameters.add(new BasicNameValuePair("sn", "C02G8416DRJM"));
		urlParameters.add(new BasicNameValuePair("cn", ""));
		urlParameters.add(new BasicNameValuePair("locale", ""));
		urlParameters.add(new BasicNameValuePair("caller", ""));
		urlParameters.add(new BasicNameValuePair("num", "12345"));

		post.setEntity(new UrlEncodedFormEntity(urlParameters));
		// the file we want to upload
		File file = new File("C:\\Temp\\bla.gif"); 
		System.out.println(file.length());
		   FileEntity fe=new FileEntity(file);
		   post.setEntity(fe);
		post.setEntity(new UrlEncodedFormEntity(urlParameters));
		
		HttpResponse response2 = client2.execute(post);
		System.out.println("Response Code : "
		                + response2.getStatusLine().getStatusCode());

		BufferedReader rd2 = new BufferedReader(
		        new InputStreamReader(response2.getEntity().getContent()));

		StringBuffer result2 = new StringBuffer();
		String line2 = "";
		while ((line2 = rd2.readLine()) != null) {
			result2.append(line2);
		}
		System.out.println(result2);
	
		ConnectionsBasicEndpoint basicEndpoint = new ConnectionsBasicEndpoint();
		basicEndpoint.setUrl("https://apps.ce.collabserv.com");
		basicEndpoint.setUser(LOGIN);
		basicEndpoint.setPassword(PASSWORD);
		basicEndpoint.setForceTrustSSLCertificate(true);
		ProfileService profileService = new ProfileService(basicEndpoint);
		System.out.println("1");
		System.out.println(profileService.getAuthType());
		System.out.println("2");
		Profile profile = profileService.getProfile("203205163");
		//		.getProfile(LOGIN);
		File file2 = new File("C:/Temp/bla.gif"); 
		System.out.println(file2.length());
		profile.setJobTitle("Et hop");
		profileService.updateProfile(profile);
		profileService.updateProfilePhoto(file2, "203205163");
		System.out.println(profile.getJobTitle());
		System.out.println(profile.getUpdated());
		//	updateProfilePhoto()
		
}
	
	
}
