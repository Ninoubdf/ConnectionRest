
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;




import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.apache.http.client.AuthCache;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.StringEntity;

public class ChangeBlog {

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

// get list of all post of this blog (description du blog puis liste de toutes les entrées et commentaires : permet de récupérer les ID des posts)
	// l'auteur et contributeur sont aussi présent dans les informations	
	//	HttpGet request1 = new HttpGet("https://mail.notes.ce.collabserv.com/livemail/0/4BB75BEC7BDB3D6185613DCB47CB95CB?OpenDocument");
		HttpGet request1 = new HttpGet("https://apps.ce.collabserv.com/blogs/"+blogid+"/api/entries");
		
		 try {
			 System.out.println("get blog");
	            HttpResponse response = httpClient.execute(request1);
	            System.out.println("Response 1 Code get : "
	                    + response.getStatusLine().getStatusCode());
	            HttpEntity entity = response.getEntity();
	           
	            BufferedReader rd = new BufferedReader(
	            		new InputStreamReader(response.getEntity().getContent()));

	            	StringBuffer result = new StringBuffer();
	            	String line = "";
	            	while ((line = rd.readLine()) != null) {
	            		result.append(line);
	            	}
	            	System.out.println(result);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }finally{
	        	request1.releaseConnection();
	        }
		 
		 // Code permettant de récupérer les noms des entrées, les id pour réaliser l'appel suivant : 
		 String handleBlogpost = "7acbba3f-b59a-4261-98bd-892d9d150341";
		 // Valeurs trouvée dans le lien atom de l'entrée : 
		// <link href="https://apps.ce.collabserv.com:443/blogs/7184995e-facd-4379-84a3-992cad39dcdc/api/entries/7acbba3f-b59a-4261-98bd-892d9d150341" rel="edit" type="application/atom+xml"/>
		 String contenu="";
		 HttpGet request3 = new HttpGet("https://apps.ce.collabserv.com/blogs/"+blogid+"/api/entries/"+handleBlogpost);
			
		 try {
			 System.out.println("get entry");
	            HttpResponse response = httpClient.execute(request3);
	            System.out.println("Response 3 Code get : "
	                    + response.getStatusLine().getStatusCode());
	            BufferedReader rd = new BufferedReader(
	            		new InputStreamReader(response.getEntity().getContent()));
	            System.out.println("Response 3 : response "+response.toString());
	            	StringBuffer result = new StringBuffer();
	            	String line = "";
	            	while ((line = rd.readLine()) != null) {
	            		result.append(line);
	            	}
	            	System.out.println(result);
	            	contenu = result.toString();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally{
	        	request3.releaseConnection();
	        }
			System.out.println("Contenu"+contenu);
			String recherche = "&lt;span style=\"color:gray;\"&gt;image retir&amp;eacute;e&lt;/span&gt;";
		 int indexcontenu = contenu.indexOf(recherche);
		 if (indexcontenu>0){
			 System.out.println("Response4 remplacement");
			 String contenunew = contenu;
			 while (indexcontenu>0){
				 contenunew = contenunew.substring(0, indexcontenu)+"&lt;a href=\"https://apps.ce.collabserv.com/blogs/7184995e-facd-4379-84a3-992cad39dcdc/resource/BLOGS_UPLOADED_IMAGES/logoConnelink.gif\" target=\"_blank\"&gt;&lt;img alt=\"image\" src=\"https://apps.ce.collabserv.com/blogs/7184995e-facd-4379-84a3-992cad39dcdc/resource/BLOGS_UPLOADED_IMAGES/logoConnelink.gif\" style=\"  display:block; margin: 1em 1em 0pt 0pt; float: left;\"&gt;&lt;/img&gt;&lt;/a&gt;"+contenu.substring(indexcontenu+recherche.length(),contenu.length());
				 indexcontenu = contenunew.indexOf("&lt;span style=\"color:gray;\"&gt;image retir&amp;eacute;e&lt;/span&gt;");
			 }
			 HttpPut request4 = new HttpPut("https://apps.ce.collabserv.com/blogs/"+blogid+"/api/entries/"+handleBlogpost);
				try {
					StringEntity entity = new StringEntity(contenunew, "utf-8");
				    entity.setContentType(new BasicHeader("Content-Type",
				        "application/atom+xml"));
				    request4.setEntity(entity);
					HttpResponse response = httpClient.execute(request4,context);

					int statusCode = response.getStatusLine().getStatusCode();
			    	System.out.println("Response4 status : "+statusCode);
				} catch (ClientProtocolException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}  finally{
		        	request4.releaseConnection();
		        }
			 
			 /* POSTER UNE NOUVELLE ENTREE DE BLOG
				 HttpPost request4 = new HttpPost("https://apps.ce.collabserv.com/blogs/"+blogid+"/api/entries");
					try {
						StringEntity entity = new StringEntity(contenunew, "utf-8");
					    entity.setContentType(new BasicHeader("Content-Type",
					        "application/atom+xml"));
					    request4.setEntity(entity);
					    request4.addHeader("Content-Type",  "application/atom+xml");
					    BufferedReader rd = new BufferedReader(
			            		new InputStreamReader(request4.getEntity().getContent()));
			       
			            	StringBuffer result = new StringBuffer();
			            	String line = "";
			            	while ((line = rd.readLine()) != null) {
			            		result.append(line);
			            	}
			            	System.out.println("Request4 content : "+result);
					    System.out.println("Response4 post request : "+request4.getAllHeaders().toString());
						HttpResponse response = httpClient.execute(request4,context);

						int statusCode = response.getStatusLine().getStatusCode();
				    	System.out.println("Response4 post status : "+statusCode);
				    	
				    	System.out.println("Response4 post status to string : "+response.getEntity().getContent().toString());
					} catch (ClientProtocolException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}  finally{
			        	request4.releaseConnection();
			        }
			        
			        
			      */  
		 }
		 
		 // Champ intéressant : champ content
		 // Image attachée code : 
		/*
		 <a href="https://apps.ce.collabserv.com/blogs/7184995e-facd-4379-84a3-992cad39dcdc/resource/BLOGS_UPLOADED_IMAGES/logoConnelink.gif" target="_blank">
			<img alt="image" src="https://apps.ce.collabserv.com/blogs/7184995e-facd-4379-84a3-992cad39dcdc/resource/BLOGS_UPLOADED_IMAGES/logoConnelink.gif" style="  display:block; margin: 1em 1em 0pt 0pt; float: left;"/>
		</a>
		 */
		 
		 HttpGet request2 = new HttpGet("https://apps.ce.collabserv.com/blogs/"+blogid+"/api/media");
			
		 try {
			 System.out.println("get media");
	            HttpResponse response = httpClient.execute(request2);
	            System.out.println("Response 2 Code get : "
	                    + response.getStatusLine().getStatusCode());
	            BufferedReader rd = new BufferedReader(
	            		new InputStreamReader(response.getEntity().getContent()));
	            
	            	StringBuffer result2 = new StringBuffer();
	            	String line = "";
	            	while ((line = rd.readLine()) != null) {
	            		result2.append(line);
	            	}
	            	System.out.println(result2);
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally{
	        	request2.releaseConnection();
	        }
		 
	/*	 
	// Permet d'envoyer une image dans les types de fichiers du blog
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
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
	*/	
}
	  
}

