package downloadReport;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.DataOutputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.net.ssl.HttpsURLConnection;
import javax.xml.namespace.NamespaceContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.soap.Node;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

/**
 * This example shows how to connect to Salesforce, download report and SOQL results
 * 
 * Example output:
 * 
    Enter hostname (or accept https://login.salesforce.com): 
	Enter username: ...
	Enter password: ...
	Enter reportId (or accept 00Ob0000003uviH):
	Enter output csv file name (or accept C:/Temp/csv.csv):
	Enter SOQL (or accept select+Id,Name+From+Account+Limit+10):
	================= getSecurityToken ======================= 
	Sending 'POST' request to URL : https://login.salesforce.com/services/Soap/u/36.0
	Response Code : 200
	sessionId=00Db0000000Jy9e!AR4AQMAm....
	================= getSOQLData ======================= 
	
	Sending 'GET' request to URL : https://jjds-sunrise.my.salesforce.com/services/data/v37.0/query/?q=select+Id,Name+From+Account+Limit+10
	Response Code : 200
	{"totalSize":10,"done":true,"records":[{"attributes":{"type":"Account","url":"/services/data/v37.0/sobjects/Account/001b000000msKs2AAE"},"Id":"001b000000msKs2AAE","Name":"QUEITIANE REIS DO SACRAMENTO"},{"attributes":{"type":"Account","url":"/services/data/v37.0/sobjects/Account/001b000000msKs3AAE"},"Id":"001b000000msKs3AAE","Name":"MARIA APARECIDA"},{"attributes":{"type":"Account","url":"/services/data/v37.0/sobjects/Account/001b000000msKs4AAE"},"Id":"001b000000msKs4AAE","Name":"jaciara cunhada"},{"attributes":{"type":"Account","url":"/services/data/v37.0/sobjects/Account/001b000000msKs5AAE"},"Id":"001b000000msKs5AAE","Name":"ANTONIO CARLOS"},{"attributes":{"type":"Account","url":"/services/data/v37.0/sobjects/Account/001b000000msKs6AAE"},"Id":"001b000000msKs6AAE","Name":"tatianesecretaria ."},{"attributes":{"type":"Account","url":"/services/data/v37.0/sobjects/Account/001b000000msKs7AAE"},"Id":"001b000000msKs7AAE","Name":"SONIAESPOSA ."},{"attributes":{"type":"Account","url":"/services/data/v37.0/sobjects/Account/001b000000msKs8AAE"},"Id":"001b000000msKs8AAE","Name":"GISELE MASCARENHAS  UBS"},{"attributes":{"type":"Account","url":"/services/data/v37.0/sobjects/Account/001b000000msKs9AAE"},"Id":"001b000000msKs9AAE","Name":"ANDREIAFILHA ."},{"attributes":{"type":"Account","url":"/services/data/v37.0/sobjects/Account/001b000000msKsAAAU"},"Id":"001b000000msKsAAAU","Name":"RAIMUNDO FRANCISCO"},{"attributes":{"type":"Account","url":"/services/data/v37.0/sobjects/Account/001b000000msKsBAAU"},"Id":"001b000000msKsBAAU","Name":"ANA PAULA"}]}
	Accounts
	QUEITIANE REIS DO SACRAMENTO
	MARIA APARECIDA
	jaciara cunhada
	ANTONIO CARLOS
	tatianesecretaria .
	SONIAESPOSA .
	GISELE MASCARENHAS  UBS
	ANDREIAFILHA .
	RAIMUNDO FRANCISCO
	ANA PAULA
	================= getReportData ======================= 
	
	Sending 'GET' request to URL : https://jjds-sunrise.my.salesforce.com/services/data/v30.0/analytics/reports/00Ob0000003uviH?includeDetails=true
	Response Code : 200
	Response:
			{  
			   "attributes":{  
			      "describeUrl":"/services/data/v30.0/analytics/reports/00Ob0000003uviHEAQ/describe",
			      "instancesUrl":"/services/data/v30.0/analytics/reports/00Ob0000003uviHEAQ/instances",
			      "reportId":"00Ob0000003uviHEAQ",
			      "reportName":"TEMP",
			      "type":"Report"
			   },
			   "allData":true,
			   "factMap":{  
			      "T!T":{  
			         "aggregates":[  
			            {  
			               "label":"314",
			               "value":314
			            }
			         ],
			         "rows":[  
			            {  
			               "dataCells":[  
			                  {  
			                     "label":"001b000003QSlaT",
			                     "value":"001b000003QSlaTAAT"
			                  },
			                  {  
			                     "label":"21/06/2016",
			                     "value":"2016-06-21"
			                  },
			                  {  
			                     "label":"Custom",
			                     "value":"Custom"
			                  }
			               ]
			            },
			            {  
			               "dataCells":[  
			                  {  
			                     "label":"001b000003JkZc0",
			                     "value":"001b000003JkZc0AAF"
			                  },
			                  {  
			                     "label":"01/03/2016",
			                     "value":"2016-03-01"
			                  },
			                  {  
			                     "label":"Custom",
			                     "value":"Custom"
			                  }
			               ]
			            },
			            {  
			               "dataCells":[  
			                  {  
			                     "label":"001b000003Jm3Be",
			                     "value":"001b000003Jm3BeAAJ"
			                  },
			                  {  
			                     "label":"11/03/2016",
			                     "value":"2016-03-11"
			                  },
			                  {  
			                     "label":"Custom",
			                     "value":"Custom"
			                  }
			               ]
			            },
			            {  
			               "dataCells":[  
			                  {  
			                     "label":"001b000003LSBTF",
			                     "value":"001b000003LSBTFAA5"
			                  },
			                  {  
			                     "label":"04/04/2016",
			                     "value":"2016-04-04"
			                  },
			                  {  
			                     "label":"Manual",
			                     "value":"Manual"
			                  }
			               ]
			            },
			            {  
			               "dataCells":[  
			                  {  
			                     "label":"001b000003LSQfb",
			                     "value":"001b000003LSQfbAAH"
			                  },
			                  {  
			                     "label":"05/04/2016",
			                     "value":"2016-04-05"
			                  },
			                  {  
			                     "label":"Custom",
			                     "value":"Custom"
			                  }
			               ]
			            },
			            {  
			               "dataCells":[  
			                  {  
			                     "label":"001b000000rp5ZP",
			                     "value":"001b000000rp5ZPAAY"
			                  },
			                  {  
			                     "label":"01/07/2015",
			                     "value":"2015-07-01"
			                  },
			                  {  
			                     "label":"Manual",
			                     "value":"Manual"
			                  }
			               ]
			            },
			            {  
			               "dataCells":[  
			                  {  
			                     "label":"001b000003QUDA9",
			                     "value":"001b000003QUDA9AAP"
			                  },
			                  {  
			                     "label":"22/06/2016",
			                     "value":"2016-06-22"
			                  },
			                  {  
			                     "label":"Custom",
			                     "value":"Custom"
			                  }
			               ]
			            }
			         ]
			      }
			   },
			   "groupingsAcross":{  
			      "groupings":[  
			
			      ]
			   },
			   "groupingsDown":{  
			      "groupings":[  
			
			      ]
			   },
			   "hasDetailRows":true,
			   "reportExtendedMetadata":{  
			      "aggregateColumnInfo":{  
			         "RowCount":{  
			            "acrossGroupingContext":null,
			            "dataType":"int",
			            "downGroupingContext":null,
			            "label":"Record Count"
			         }
			      },
			      "detailColumnInfo":{  
			         "ACCOUNT_ID":{  
			            "dataType":"id",
			            "label":"Account ID"
			         },
			         "CREATED_DATE":{  
			            "dataType":"datetime",
			            "label":"Created Date"
			         },
			         "Account.Brick_type__c":{  
			            "dataType":"picklist",
			            "label":"Brick Type"
			         }
			      },
			      "groupingColumnInfo":{  
			
			      }
			   },
			   "reportMetadata":{  
			      "aggregates":[  
			         "RowCount"
			      ],
			      "currency":"USD",
			      "detailColumns":[  
			         "ACCOUNT_ID",
			         "CREATED_DATE",
			         "Account.Brick_type__c"
			      ],
			      "developerName":"TEMP",
			      "groupingsAcross":[  
			
			      ],
			      "groupingsDown":[  
			
			      ],
			      "historicalSnapshotDates":[  
			
			      ],
			      "id":"00Ob0000003uviHEAQ",
			      "name":"TEMP",
			      "reportBooleanFilter":null,
			      "reportFilters":[  
			         {  
			            "column":"RECORDTYPE",
			            "operator":"equals",
			            "value":"Business Account"
			         },
			         {  
			            "column":"Account.x__c",
			            "operator":"equals",
			            "value":""
			         },
			         {  
			            "column":"ADDRESS2_STREET",
			            "operator":"notEqual",
			            "value":"Chain"
			         }
			      ],
			      "reportFormat":"TABULAR",
			      "reportType":{  
			         "label":"Accounts",
			         "type":"AccountList@Account.x2__c"
			      }
			   }
			}	

 * @author Maciej Szymczak
 * @version 2017.12.04
 */

public class downloadReport {

	static String gserverUrl;
	static String gaccessToken;

	public static void main(String[] args) {		
		String hostName;
		String username;
		String password;
		String reportId;
		String csvFile;
		String SOQL;
		String anonymousBody;

		if (args.length>0) {
			hostName      = args[0];
			username      = args[1];
			password      = args[2];
			reportId      = args[3];
			csvFile       = args[4];
			SOQL          = args[5];
			anonymousBody = args[6];
		} else {
			System.out.print("Enter hostname (or accept https://login.salesforce.com): ");
			Scanner terminalInput = new Scanner(System.in);
			hostName = terminalInput.nextLine();
			if ((hostName+"").length() == 0)  hostName = "https://login.salesforce.com";

			System.out.print("Enter username: ");
			terminalInput = new Scanner(System.in);
			username = terminalInput.nextLine();	

			System.out.print("Enter password: ");
			terminalInput = new Scanner(System.in);
			password = terminalInput.nextLine();				

			System.out.print("Enter anonymousBody (or accept System.debug(1/0);");
			terminalInput = new Scanner(System.in);
			anonymousBody = terminalInput.nextLine();				
			//System.debug(1/0)
			//Contact+contact+%3D+%5Bselect+id%2C+Brand__c+from+contact+where+id+%3D+%27003w0000018P6Ez%27%5D%3B+contact.Brand__c+%3D+%27AAA%27%3B+update+contact%3B
			if ((anonymousBody+"").length() == 0)  anonymousBody = "Contact contact = [select id, Brand__c from contact where id = '003w0000018P6Ez']; contact.Brand__c = 'AAA'; update contact;";

			System.out.print("Enter SOQL (or accept select+Id,Name+From+Account+Limit+10):");
			terminalInput = new Scanner(System.in);
			SOQL = terminalInput.nextLine();				
			if ((SOQL+"").length() == 0)  SOQL = "select+Id,Name+From+Account+Limit+10";

			System.out.print("Enter reportId (or accept 00Ob0000003uviH):");
			terminalInput = new Scanner(System.in);
			reportId = terminalInput.nextLine();				
			if ((reportId+"").length() == 0)  reportId = "00Ob0000003uviH";
			
			System.out.print("Enter output csv file name (or accept C:/Temp/csv.csv):");
			terminalInput = new Scanner(System.in);
			csvFile = terminalInput.nextLine();				
			if ((csvFile+"").length() == 0)  csvFile = "C:/Temp/x.csv";

			
		};		
		
		downloadReport http = new downloadReport();
		try {
			//both authorization methods do work
			http.getSecurityTokenMethod1(hostName,  username, password);
			//Method2 works too, it is additionally required to delived client_id and client_secret
			//String accessToken = http.getSecurityTokenMethod2(hostName, client_id, client_secret, username, password);
		     String data1 = http.executeAnonymous(gserverUrl, gaccessToken, anonymousBody);
		     String data2 = http.getSOQLData(gserverUrl, gaccessToken, SOQL);
			 String Data3=  http.getReportData(gserverUrl, gaccessToken, reportId, csvFile);	
				
		} catch (Exception e) {
			e.printStackTrace();
		}
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
		
		System.out.println("================= getSecurityToken ======================= ");
		String url = hostName + "/services/Soap/u/36.0";
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
		
		System.out.println("Sending 'POST' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);
 
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
		System.out.println("Response:" + xmlStr);
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
		System.out.println("serverUrl=" + serverUrl);
		System.out.println("sessionId=" + sessionId);

		gserverUrl = serverUrl;
		gaccessToken = sessionId;
	}

	//======================================================================================
	private String getSecurityTokenMethod2(String hostName, String client_id, String client_secret, String username, String password) throws Exception {		 
		String url = hostName+"/services/oauth2/token";
		URL obj = new URL(url);
		HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
 
		//add reuqest header
		con.setRequestMethod("POST");
		//eqeuivalent curl -H
		con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
 
		String urlParameters = "grant_type=password"+
		                       "&client_id="+client_id+
				               "&client_secret="+client_secret+
		                       "&username="+username+
				               "&password="+password;

		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();
 
		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + url);
		//System.out.println("Post parameters : " + urlParameters);
		System.out.println("Response Code : " + responseCode);
 
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
		JSONParser parser = new JSONParser();
		Object obj2 = parser.parse(response.toString());
		JSONObject jsonObject = (JSONObject) obj2;
		String accessToken = (String) jsonObject.get("access_token");
		System.out.println(accessToken);
		return accessToken; 
	}
	
	//======================================================================================
	private String getSOQLData(String hostName, String accessToken, String soql) throws Exception {
		System.out.println("================= getSOQLData ======================= ");
		String url = hostName + "/services/data/v37.0/query/?q="+soql;
		URL obj = new URL(url);
		HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
 
		//add reuqest header
		con.setRequestMethod("GET");
		//equivalent curl -H
		con.setRequestProperty("Authorization", "Bearer "+accessToken);
 
		// Send post request
		con.setDoOutput(true);
 
		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		//System.out.println("Post parameters : " + urlParameters);
		System.out.println("Response Code : " + responseCode);
 
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
		JSONParser parser = new JSONParser();
		Object obj2 = parser.parse(response.toString());
		JSONObject jsonObject = (JSONObject) obj2;
		JSONArray a = (JSONArray)jsonObject.get("records");
		System.out.println("Accounts");
		for (int i=0;i<a.size();i++) {
			System.out.println( ((JSONObject)a.get(i)).get("Name").toString() );			
		}
		return response.toString(); 
	} 	

	//======================================================================================
	private String executeAnonymous(String hostName, String accessToken, String anonymousBody) throws Exception {
		System.out.println("================= executeAnonymous ======================= ");
		String url = hostName + "/services/data/v37.0/tooling/executeAnonymous/?anonymousBody="+anonymousBody;
		URL obj = new URL(url);
		HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
 
		//add reuqest header
		con.setRequestMethod("GET");
		//equivalent curl -H
		con.setRequestProperty("Authorization", "Bearer "+accessToken);
 
		// Send post request
		con.setDoOutput(true);
 
		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		//System.out.println("Post parameters : " + urlParameters);
		System.out.println("Response Code : " + responseCode);
 
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

	//======================================================================================
	private String getReportData(String hostName, String accessToken, String reportId, String csvFile) throws Exception {
		System.out.println("================= getReportData ======================= ");
		String url = hostName+"/services/data/v30.0/analytics/reports/"+reportId+"?includeDetails=true";

		URL obj = new URL(url);
		HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
 
		//add reuqest header
		con.setRequestMethod("GET");
		//equivalent curl -H
		con.setRequestProperty("Authorization", "Bearer "+accessToken);
		
		// Send post request
		con.setDoOutput(true);
 
		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		//System.out.println("Post parameters : " + urlParameters);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
 
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		
		//print result
		System.out.println("Response:" + response.toString());
		//sample response
		//{"attributes":{"describeUrl":"/services/data/v29.0/analytics/reports/00O11000000SoKzEAK/describe","instancesUrl":"/services/data/v29.0/analytics/reports/00O11000000SoKzEAK/instances","reportId":"00O11000000SoKzEAK","reportName":"TEST","type":"Report"},"allData":true,"factMap":{"T!T":{"aggregates":[{"label":"20","value":20}],"rows":[{"dataCells":[{"label":"one more test call egle","value":"00U11000002RLO5EAO"},{"label":".","value":"0031100000TAcjjAAD"},{"label":"Alexandra","value":"0031100000TAcjjAAD"},{"label":"-","value":null},{"label":"-","value":null}]},{"dataCells":[{"label":"Meeting","value":"00U11000002MyYtEAK"},{"label":"Maria Elisabeth","value":"0031100000TAoNHAA1"},{"label":"Körkemeyer","value":"0031100000TAoNHAA1"},{"label":"WDEM04150411","value":"WDEM04150411"},{"label":"WDEE00015991","value":"WDEE00015991"}]},{"dataCells":[{"label":"arztazzarzaerezareza","value":"00U11000002PUvpEAG"},{"label":".","value":"0031100000TAxN6AAL"},{"label":"Adamczak","value":"0031100000TAxN6AAL"},{"label":"-","value":null},{"label":"-","value":null}]},{"dataCells":[{"label":"Meeting","value":"00U11000002OYDsEAO"},{"label":"Елена","value":"0031100000TBTOaAAP"},{"label":"Брусник","value":"0031100000TBTOaAAP"},{"label":"WRUM00222113","value":"WRUM00222113"},{"label":"WRUE00003822","value":"WRUE00003822"}]},{"dataCells":[{"label":"Meeting","value":"00U11000002OYYCEA4"},{"label":"Валентина","value":"0031100000TC0XFAA1"},{"label":"Зайцева","value":"0031100000TC0XFAA1"},{"label":"WRUM00056512","value":"WRUM00056512"},{"label":"WRUE00050049","value":"WRUE00050049"}]},{"dataCells":[{"label":"Meeting","value":"00U11000002N0f9EAC"},{"label":".","value":"0031100000TB2FvAAL"},{"label":"Schulte","value":"0031100000TB2FvAAL"},{"label":"WDEM03458428","value":"WDEM03458428"},{"label":"WDEE00321750","value":"WDEE00321750"}]},{"dataCells":[{"label":"Meeting","value":"00U11000002OYYGEA4"},{"label":"Оксана","value":"0031100000TCDJgAAP"},{"label":"Гирич","value":"0031100000TCDJgAAP"},{"label":"-","value":null},{"label":"-","value":null}]},{"dataCells":[{"label":"Meeting","value":"00U11000002NJZqEAO"},{"label":"Volker","value":"0031100000TAugNAAT"},{"label":"Brauer","value":"0031100000TAugNAAT"},{"label":"WDEM00008633","value":"WDEM00008633"},{"label":"WDEE00009459","value":"WDEE00009459"}]},{"dataCells":[{"label":"Meeting","value":"00U11000002OYYNEA4"},{"label":"Ирина","value":"0031100000TBVFAAA5"},{"label":"Абушахманова","value":"0031100000TBVFAAA5"},{"label":"WRUM00321849","value":"WRUM00321849"},{"label":"WRUE00016970","value":"WRUE00016970"}]},{"dataCells":[{"label":"Meeting","value":"00U11000002MymHEAS"},{"label":"Andreas","value":"0031100000TAtngAAD"},{"label":"Mancke","value":"0031100000TAtngAAD"},{"label":"WDEM05509086","value":"WDEM05509086"},{"label":"WDEE00276750","value":"WDEE00276750"}]},{"dataCells":[{"label":"Meeting","value":"00U11000002OWtxEAG"},{"label":"Ирина","value":"0031100000TBVFAAA5"},{"label":"Абушахманова","value":"0031100000TBVFAAA5"},{"label":"WRUM00321849","value":"WRUM00321849"},{"label":"WRUE00016970","value":"WRUE00016970"}]},{"dataCells":[{"label":"Meeting","value":"00U11000002OXwUEAW"},{"label":"Валентина","value":"0031100000TC0XFAA1"},{"label":"Зайцева","value":"0031100000TC0XFAA1"},{"label":"WRUM00056512","value":"WRUM00056512"},{"label":"WRUE00050049","value":"WRUE00050049"}]},{"dataCells":[{"label":"Meeting","value":"00U11000002OXwVEAW"},{"label":"Татьяна","value":"0031100000TCCICAA5"},{"label":"Трифонова","value":"0031100000TCCICAA5"},{"label":"WRUM00350916","value":"WRUM00350916"},{"label":"WRUE00053241","value":"WRUE00053241"}]},{"dataCells":[{"label":"Meeting","value":"00U11000002N7RSEA0"},{"label":"Peggy","value":"0031100000TAvOkAAL"},{"label":"Neumann","value":"0031100000TAvOkAAL"},{"label":"-","value":null},{"label":"WDEE00843371","value":"WDEE00843371"}]},{"dataCells":[{"label":"Meeting","value":"00U11000002OXwWEAW"},{"label":"Валентина","value":"0031100000TC0XFAA1"},{"label":"Зайцева","value":"0031100000TC0XFAA1"},{"label":"WRUM00056512","value":"WRUM00056512"},{"label":"WRUE00050049","value":"WRUE00050049"}]},{"dataCells":[{"label":"Meeting","value":"00U11000002NwVSEA0"},{"label":".","value":"0031100000TAn4bAAD"},{"label":"Halfmann","value":"0031100000TAn4bAAD"},{"label":"WDEM00800361","value":"WDEM00800361"},{"label":"WDEE04997040","value":"WDEE04997040"}]},{"dataCells":[{"label":"Meeting","value":"00U11000002N8IREA0"},{"label":"Kathrin","value":"0031100000TAe6OAAT"},{"label":"Nowock","value":"0031100000TAe6OAAT"},{"label":"-","value":null},{"label":"WDEE00012355","value":"WDEE00012355"}]},{"dataCells":[{"label":"Meeting","value":"00U11000002OXwXEAW"},{"label":"Валентина","value":"0031100000TC0XFAA1"},{"label":"Зайцева","value":"0031100000TC0XFAA1"},{"label":"WRUM00056512","value":"WRUM00056512"},{"label":"WRUE00050049","value":"WRUE00050049"}]},{"dataCells":[{"label":"Meeting","value":"00U11000002NjNREA0"},{"label":"Peter","value":"0031100000TAerAAAT"},{"label":"Mittenzwei","value":"0031100000TAerAAAT"},{"label":"WDEM05679044","value":"WDEM05679044"},{"label":"WDEE08506161","value":"WDEE08506161"}]},{"dataCells":[{"label":"Meeting","value":"00U11000002NktdEAC"},{"label":"Kerstin","value":"0031100000TAmPIAA1"},{"label":"Hempel","value":"0031100000TAmPIAA1"},{"label":"-","value":null},{"label":"WDEE08704050","value":"WDEE08704050"}]}]}},"groupingsAcross":{"groupings":[]},"groupingsDown":{"groupings":[]},"hasDetailRows":true,"reportExtendedMetadata":{"aggregateColumnInfo":{"RowCount":{"acrossGroupingContext":null,"dataType":"int","downGroupingContext":null,"label":"Record Count"}},"detailColumnInfo":{"SUBJECT":{"dataType":"string","label":"Subject"},"FIRST_NAME":{"dataType":"string","label":"First Name"},"LAST_NAME":{"dataType":"string","label":"Last Name"},"Contact.Cegedim_number__c":{"dataType":"string","label":"Cegedim #"},"Account.Cegedim_number__c":{"dataType":"string","label":"Cegedim #"}},"groupingColumnInfo":{}},"reportMetadata":{"aggregates":["RowCount"],"currency":"EUR","detailColumns":["SUBJECT","FIRST_NAME","LAST_NAME","Contact.Cegedim_number__c","Account.Cegedim_number__c"],"developerName":"TEST","groupingsAcross":[],"groupingsDown":[],"id":"00O11000000SoKzEAK","name":"TEST","reportBooleanFilter":null,"reportFilters":[],"reportFormat":"TABULAR","reportType":{"label":"Activities with Contacts","type":"ContactActivity"}}}

		// source: http://www.mkyong.com/java/json-simple-example-read-and-write-json/
		JSONParser parser = new JSONParser();
		JSONObject jsonObject = (JSONObject) parser.parse(response.toString());
		String  factMap = (String) jsonObject.get("factMap").toString();

		JSONObject jsonObject3 = (JSONObject) parser.parse(factMap);
		String  TT = (String) jsonObject3.get("T!T").toString();

		JSONObject jsonObject4 = (JSONObject) parser.parse(TT);
		String  rows = (String) jsonObject4.get("rows").toString();
		
		Object obj5 = parser.parse(rows);
		JSONArray a = (JSONArray) obj5;

		FileWriter fw = new FileWriter(csvFile);
		
		for (int i=0;i<a.size();i++) {
			//System.out.println( ((JSONObject)a.get(i)).toString() );
			String dataCells=((JSONObject)a.get(i)).toString();
			
			JSONObject jsonObject5 = (JSONObject) parser.parse(dataCells);
			String  dataCellsArray = (String) jsonObject5.get("dataCells").toString();

			Object obj6 = parser.parse( dataCellsArray );
			JSONArray subarray = (JSONArray) obj6;
			String rowString="";
			for (int j=0;j<subarray.size();j++) {
				String outputcell= ((JSONObject)subarray.get(j)).get("label").toString();
				if (outputcell.equals("-") ) {
					outputcell="\"\"";
				}
				else
				{
				    outputcell = "\"" + outputcell.replace("\"", "\"\"") + "\"";
				}
				rowString = rowString + outputcell;
				if (j<subarray.size()-1) {
					rowString = rowString + ",";
				}				
			}
			fw.append(rowString+"\n");	
		}
 		fw.flush();
    	fw.close();		
		return response.toString(); 
	} 	
}
