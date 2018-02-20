package downloadReport;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.DataOutputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;

import javax.net.ssl.HttpsURLConnection;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

/*
 * @Author Maciej Szymczak
 * @version 2018.02.13
 *
 */

public class ExecuteAnonymous {

	static String gserverUrl;
	static String gapiversion;
	static String gaccessToken;		

	public static void main(String[] args) throws Exception {
		String hostName; //https://login.salesforce.com
		String username;
		String password;
		String anonymousBody="";
		
		hostName = args[0]; 
		username = args[1]; 
		password = args[2];
		gapiversion = args[3];  			
			
		FileReader fr;
		fr = new FileReader(args[4]); 
		BufferedReader br = new BufferedReader(fr);
		String phisicalLine;
		while( (phisicalLine = br.readLine()) != null) {
			anonymousBody = anonymousBody + phisicalLine + "\n";
		}
		fr.close();
	    //System.out.println("anonymousBody=" + anonymousBody);
		anonymousBody = URLEncoder.encode(anonymousBody, "UTF-8");
	    //System.out.println("anonymousBody ENC=" + anonymousBody);
		
		ExecuteAnonymous http = new ExecuteAnonymous();
		http.getSecurityTokenMethod1(hostName,  username, password);
	    String res = http.executeAnonymous(gserverUrl, gaccessToken, anonymousBody);		
	    System.out.println("res=" + res);
	}

	//======================================================================================
	private Document loadXMLFromString(String xml) throws Exception
	{
	    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	    factory.setNamespaceAware(true);
	    DocumentBuilder builder = factory.newDocumentBuilder();
	    return builder.parse(new ByteArrayInputStream(xml.getBytes()));
	}		
	
	//======================================================================================
	private void getSecurityTokenMethod1(String hostName, String username, String password) throws Exception {
		
		//System.out.println("================= getSecurityToken ======================= ");
		String url = hostName + "/services/Soap/u/{VERSION}".replace("{VERSION}", gapiversion);
		URL obj = new URL(url);
		HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
 
		//add reuqest header
		con.setRequestMethod("POST");
		//eqeuivalent curl -H
		con.setRequestProperty("Content-Type", "text/xml; charset=UTF-8");
		con.setRequestProperty("SOAPAction", "login");
 
		//eqeuivalent curl -D
		String urlParameters = 
		"<?xml version=\"1.0\" encoding=\"utf-8\" ?>"+
		"<env:Envelope xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\""+
		    " xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\""+
		    " xmlns:env=\"http://schemas.xmlsoap.org/soap/envelope/\">"+
		  "<env:Body>"+
		    "<n1:login xmlns:n1=\"urn:partner.soap.sforce.com\">"+
		      "<n1:username>"+username+"</n1:username>"+
		      "<n1:password>"+password+"</n1:password>"+
		    "</n1:login>"+
		  "</env:Body>"+
		"</env:Envelope>";
		
		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();
 
		int responseCode = con.getResponseCode();
		
		//System.out.println("Sending 'POST' request to URL : " + url);
		//System.out.println("Response Code : " + responseCode);
 
		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
 
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		//I do prefer to delete schemas rather than play with complex java code required to support schemas
		String xmlStr = response.toString();
		xmlStr = xmlStr.replace("soapenv:", "");
		xmlStr = xmlStr.replace("xsi:", "");
		xmlStr = xmlStr.replace("<Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns=\"urn:partner.soap.sforce.com\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">", "<Envelope>");
		//System.out.println("Response:" + xmlStr);
		//sample response
		/*
		   <?xml version="1.0" encoding="UTF-8"?>
			<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns="urn:partner.soap.sforce.com" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
			    <soapenv:Body>
			        <loginResponse>
			            <result>
			                <metadataServerUrl>https://jjds-sunrise.my.salesforce.com/services/Soap/m/36.0/00Db0000000Jy9e</metadataServerUrl>
			                <passwordExpired>false</passwordExpired>
			                <sandbox>false</sandbox>
			                <serverUrl>https://jjds-sunrise.my.salesforce.com/services/Soap/u/36.0/00Db0000000Jy9e</serverUrl>
			                <sessionId>00Db0000000Jy9e!AR4AQNBFU62TCNFV6TkN0mnq2Z1Cu5MhRp1wbC3sPVBPkk8vFNR1gtsFvQAKj1KjETaXOmukV1011dAb8vpUhVzKl8DUug2r</sessionId>
			                <userId>005b00000013QuVAAU</userId>
			            </result>
			        </loginResponse>
			    </soapenv:Body>
			</soapenv:Envelope>
		 */
		
        Document doc = loadXMLFromString(xmlStr);	
		
		XPathFactory xpf = XPathFactory.newInstance();
		XPath xpath = xpf.newXPath();

		//XPathExpression expression = xpath.compile("//soapenv:Envelope/soapenv:Body/loginResponse/result/serverUrl/text()" );
		XPathExpression expression = xpath.compile("//serverUrl/text()" );		
		Object result = expression.evaluate(doc, XPathConstants.NODESET);		
		NodeList nodes = (NodeList) result;
		String serverUrl = nodes.item(0).getNodeValue(); 

		expression = xpath.compile("//sessionId/text()" );		
		result = expression.evaluate(doc, XPathConstants.NODESET);		
		nodes = (NodeList) result;
		String sessionId = nodes.item(0).getNodeValue(); 
		
		serverUrl = serverUrl.substring(0, serverUrl.indexOf("/services"));  
		//System.out.println("serverUrl=" + serverUrl);
		//System.out.println("sessionId=" + sessionId);

		gserverUrl = serverUrl;
		gaccessToken = sessionId;
	}
	
	
	//======================================================================================
	private String executeAnonymous(String hostName, String accessToken, String anonymousBody) throws Exception {
		//System.out.println("================= executeAnonymous ======================= ");
		String url = hostName + "/services/data/v{VERSION}/tooling/executeAnonymous/?anonymousBody=".replace("{VERSION}", gapiversion)+anonymousBody;
		URL obj = new URL(url);
		HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
 
		//add reuqest header
		con.setRequestMethod("GET");
		//equivalent curl -H
		con.setRequestProperty("Authorization", "Bearer "+accessToken);
 
		// Send post request
		con.setDoOutput(true);
 
		int responseCode = con.getResponseCode();
		//System.out.println("\nSending 'GET' request to URL : " + url);
		//System.out.println("Post parameters : " + urlParameters);
		//System.out.println("Response Code : " + responseCode);
 
		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
 
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		
		//print result
		System.out.println(response.toString());
		//sample response
		//{"id":"https://login.salesforce.com/id/00D200000005AU3EAM/00520000002nmehAAA","issued_at":"1385833580880","instance_url":"https://eu3.salesforce.com","signature":"H+1vAiLoDhXe4IdVI6VdsPNq9Q7oOXKjYEen8rCAc+M=","access_token":"00D200000005AU3!AQYAQIY7RhkjVRbpHB37ynAA.fnO1Sv1pdooAEJMZKfHOfmeGaCaNYNW.EwAGKlR.1I.OGwrZnRE8ocy1vWeAbALBPC5psz3"}
		
		// source: http://www.mkyong.com/java/json-simple-example-read-and-write-json/
		//JSONParser parser = new JSONParser();
		//Object obj2 = parser.parse(response.toString());
		//JSONObject jsonObject = (JSONObject) obj2;
		//JSONArray a = (JSONArray)jsonObject.get("records");
		//System.out.println("Accounts");
		//for (int i=0;i<a.size();i++) {
		//	System.out.println( ((JSONObject)a.get(i)).get("Name").toString() );			
		//}
		return response.toString(); 
	} 	
		
}
