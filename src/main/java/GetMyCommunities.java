import com.ibm.commons.xml.XMLException;
import com.ibm.sbt.services.client.ClientServicesException;
import com.ibm.sbt.services.client.Response;
import com.ibm.sbt.services.rest.RestClient;
import com.ibm.sbt.services.rest.atom.AtomFeed;

public class GetMyCommunities {
	public static void main(String[] args) throws ClientServicesException, XMLException{
		  String LOGIN = "LOGIN";
			String PASSWORD = "PASSWORD";	
	RestClient restClient = new RestClient("https://apps.ce.collabserv.com",LOGIN,PASSWORD);
	Response<AtomFeed> responseFeed = restClient.doGet("profiles/atom/search.do?name=administrateur").asAtomFeed();
	System.out.println(responseFeed.getData().toXmlString());
	

//	Response<AtomFeed> responseFeed2 = restClient.doGet("https://apps.ce.collabserv.com/profiles/photo.do?key=cc70cadc-abd0-4c64-aa5b-c8a45e527974&amp;lastMod=1517854026253").asAtomFeed();
//	System.out.println(responseFeed2.getData().toString());
	}
}
