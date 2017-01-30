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
	Response:{"attributes":{"describeUrl":"/services/data/v30.0/analytics/reports/00Ob0000003uviHEAQ/describe","instancesUrl":"/services/data/v30.0/analytics/reports/00Ob0000003uviHEAQ/instances","reportId":"00Ob0000003uviHEAQ","reportName":"TEMP","type":"Report"},"allData":true,"factMap":{"T!T":{"aggregates":[{"label":"314","value":314}],"rows":[{"dataCells":[{"label":"001b000003QSlaT","value":"001b000003QSlaTAAT"},{"label":"21/06/2016","value":"2016-06-21"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003JkZc0","value":"001b000003JkZc0AAF"},{"label":"01/03/2016","value":"2016-03-01"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003Jm3Be","value":"001b000003Jm3BeAAJ"},{"label":"11/03/2016","value":"2016-03-11"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003LSBTF","value":"001b000003LSBTFAA5"},{"label":"04/04/2016","value":"2016-04-04"},{"label":"Manual","value":"Manual"}]},{"dataCells":[{"label":"001b000003LSQfb","value":"001b000003LSQfbAAH"},{"label":"05/04/2016","value":"2016-04-05"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003Jkh4k","value":"001b000003Jkh4kAAB"},{"label":"02/03/2016","value":"2016-03-02"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003JkU9h","value":"001b000003JkU9hAAF"},{"label":"01/03/2016","value":"2016-03-01"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003JkKNY","value":"001b000003JkKNYAA3"},{"label":"29/02/2016","value":"2016-02-29"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003LSAkJ","value":"001b000003LSAkJAAX"},{"label":"04/04/2016","value":"2016-04-04"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003JnoOS","value":"001b000003JnoOSAAZ"},{"label":"22/03/2016","value":"2016-03-22"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003Jn4MD","value":"001b000003Jn4MDAAZ"},{"label":"17/03/2016","value":"2016-03-17"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003ZXVIL","value":"001b000003ZXVILAA5"},{"label":"18/11/2016","value":"2016-11-18"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003ZY03f","value":"001b000003ZY03fAAD"},{"label":"21/11/2016","value":"2016-11-21"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003ZXPgO","value":"001b000003ZXPgOAAX"},{"label":"18/11/2016","value":"2016-11-18"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003ZXPbY","value":"001b000003ZXPbYAAX"},{"label":"18/11/2016","value":"2016-11-18"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003ZYXQS","value":"001b000003ZYXQSAA5"},{"label":"22/11/2016","value":"2016-11-22"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003aJ08a","value":"001b000003aJ08aAAC"},{"label":"05/12/2016","value":"2016-12-05"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003aJNOt","value":"001b000003aJNOtAAO"},{"label":"06/12/2016","value":"2016-12-06"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003cQoWx","value":"001b000003cQoWxAAK"},{"label":"23/12/2016","value":"2016-12-23"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003df49f","value":"001b000003df49fAAA"},{"label":"10/01/2017","value":"2017-01-10"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003bCp98","value":"001b000003bCp98AAC"},{"label":"09/12/2016","value":"2016-12-09"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003aJGd2","value":"001b000003aJGd2AAG"},{"label":"06/12/2016","value":"2016-12-06"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003aGIZ6","value":"001b000003aGIZ6AAO"},{"label":"25/11/2016","value":"2016-11-25"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003f4crA","value":"001b000003f4crAAAQ"},{"label":"24/01/2017","value":"2017-01-24"},{"label":"Auto","value":"Auto"}]},{"dataCells":[{"label":"001b000003f4nJv","value":"001b000003f4nJvAAI"},{"label":"25/01/2017","value":"2017-01-25"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003dfYPP","value":"001b000003dfYPPAA2"},{"label":"12/01/2017","value":"2017-01-12"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003f5aEk","value":"001b000003f5aEkAAI"},{"label":"29/01/2017","value":"2017-01-29"},{"label":"Auto","value":"Auto"}]},{"dataCells":[{"label":"001b000003f5108","value":"001b000003f5108AAA"},{"label":"26/01/2017","value":"2017-01-26"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003f5eix","value":"001b000003f5eixAAA"},{"label":"30/01/2017","value":"2017-01-30"},{"label":"Auto","value":"Auto"}]},{"dataCells":[{"label":"001b000003f512J","value":"001b000003f512JAAQ"},{"label":"26/01/2017","value":"2017-01-26"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003f4ZGb","value":"001b000003f4ZGbAAM"},{"label":"24/01/2017","value":"2017-01-24"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003f500X","value":"001b000003f500XAAQ"},{"label":"26/01/2017","value":"2017-01-26"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003f4geL","value":"001b000003f4geLAAQ"},{"label":"25/01/2017","value":"2017-01-25"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003dfVsC","value":"001b000003dfVsCAAU"},{"label":"12/01/2017","value":"2017-01-12"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003f4uom","value":"001b000003f4uomAAA"},{"label":"26/01/2017","value":"2017-01-26"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003f5avW","value":"001b000003f5avWAAQ"},{"label":"29/01/2017","value":"2017-01-29"},{"label":"Auto","value":"Auto"}]},{"dataCells":[{"label":"001b000003f5eii","value":"001b000003f5eiiAAA"},{"label":"30/01/2017","value":"2017-01-30"},{"label":"Auto","value":"Auto"}]},{"dataCells":[{"label":"001b000003f4UT1","value":"001b000003f4UT1AAM"},{"label":"24/01/2017","value":"2017-01-24"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003f58jO","value":"001b000003f58jOAAQ"},{"label":"27/01/2017","value":"2017-01-27"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003dgtQz","value":"001b000003dgtQzAAI"},{"label":"17/01/2017","value":"2017-01-17"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003cRdDD","value":"001b000003cRdDDAA0"},{"label":"26/12/2016","value":"2016-12-26"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003f4vte","value":"001b000003f4vteAAA"},{"label":"26/01/2017","value":"2017-01-26"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003dgdg2","value":"001b000003dgdg2AAA"},{"label":"16/01/2017","value":"2017-01-16"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003aHVbQ","value":"001b000003aHVbQAAW"},{"label":"29/11/2016","value":"2016-11-29"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003bGEVw","value":"001b000003bGEVwAAO"},{"label":"19/12/2016","value":"2016-12-19"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000001MwRw0","value":"001b000001MwRw0AAF"},{"label":"16/09/2015","value":"2015-09-16"},{"label":"Manual","value":"Manual"}]},{"dataCells":[{"label":"001b000003MR95F","value":"001b000003MR95FAAT"},{"label":"05/05/2016","value":"2016-05-05"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003MRNXN","value":"001b000003MRNXNAA5"},{"label":"06/05/2016","value":"2016-05-06"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003MYhPu","value":"001b000003MYhPuAAL"},{"label":"19/04/2016","value":"2016-04-19"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003MRN7C","value":"001b000003MRN7CAAX"},{"label":"06/05/2016","value":"2016-05-06"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003NXIPs","value":"001b000003NXIPsAAP"},{"label":"10/05/2016","value":"2016-05-10"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003MPoXo","value":"001b000003MPoXoAAL"},{"label":"28/04/2016","value":"2016-04-28"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003MPMQi","value":"001b000003MPMQiAAP"},{"label":"27/04/2016","value":"2016-04-27"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003MPAp8","value":"001b000003MPAp8AAH"},{"label":"26/04/2016","value":"2016-04-26"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003MZ1BL","value":"001b000003MZ1BLAA1"},{"label":"21/04/2016","value":"2016-04-21"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003YYJZE","value":"001b000003YYJZEAA5"},{"label":"11/11/2016","value":"2016-11-11"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000001v7kMK","value":"001b000001v7kMKAAY"},{"label":"26/10/2015","value":"2015-10-26"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000001v9dbB","value":"001b000001v9dbBAAQ"},{"label":"28/10/2015","value":"2015-10-28"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000001v8zY2","value":"001b000001v8zY2AAI"},{"label":"27/10/2015","value":"2015-10-27"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000001v9qg2","value":"001b000001v9qg2AAA"},{"label":"29/10/2015","value":"2015-10-29"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000001v9r2w","value":"001b000001v9r2wAAA"},{"label":"29/10/2015","value":"2015-10-29"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000001v93xf","value":"001b000001v93xfAAA"},{"label":"27/10/2015","value":"2015-10-27"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003P7wdB","value":"001b000003P7wdBAAR"},{"label":"07/06/2016","value":"2016-06-07"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003NZIdS","value":"001b000003NZIdSAAX"},{"label":"19/05/2016","value":"2016-05-19"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003NaUkJ","value":"001b000003NaUkJAAV"},{"label":"25/05/2016","value":"2016-05-25"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003NY8zZ","value":"001b000003NY8zZAAT"},{"label":"13/05/2016","value":"2016-05-13"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003NaRxA","value":"001b000003NaRxAAAV"},{"label":"25/05/2016","value":"2016-05-25"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003NYcXk","value":"001b000003NYcXkAAL"},{"label":"16/05/2016","value":"2016-05-16"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003P8A5h","value":"001b000003P8A5hAAF"},{"label":"08/06/2016","value":"2016-06-08"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003NZxKk","value":"001b000003NZxKkAAL"},{"label":"23/05/2016","value":"2016-05-23"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003NYnVL","value":"001b000003NYnVLAA1"},{"label":"17/05/2016","value":"2016-05-17"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003NbBC2","value":"001b000003NbBC2AAN"},{"label":"31/05/2016","value":"2016-05-31"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003NXj2E","value":"001b000003NXj2EAAT"},{"label":"11/05/2016","value":"2016-05-11"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003NZ88B","value":"001b000003NZ88BAAT"},{"label":"18/05/2016","value":"2016-05-18"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003NZJKk","value":"001b000003NZJKkAAP"},{"label":"19/05/2016","value":"2016-05-19"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003NZIs1","value":"001b000003NZIs1AAH"},{"label":"19/05/2016","value":"2016-05-19"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003NaluW","value":"001b000003NaluWAAR"},{"label":"27/05/2016","value":"2016-05-27"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003NZyLP","value":"001b000003NZyLPAA1"},{"label":"23/05/2016","value":"2016-05-23"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003NaFsm","value":"001b000003NaFsmAAF"},{"label":"24/05/2016","value":"2016-05-24"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003NZFSp","value":"001b000003NZFSpAAP"},{"label":"19/05/2016","value":"2016-05-19"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003NZ2nx","value":"001b000003NZ2nxAAD"},{"label":"18/05/2016","value":"2016-05-18"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003Nb10i","value":"001b000003Nb10iAAB"},{"label":"30/05/2016","value":"2016-05-30"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b0000025kdel","value":"001b0000025kdelAAA"},{"label":"05/11/2015","value":"2015-11-05"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000002IM3xW","value":"001b000002IM3xWAAT"},{"label":"20/11/2015","value":"2015-11-20"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000002IODtf","value":"001b000002IODtfAAH"},{"label":"23/11/2015","value":"2015-11-23"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000002IKeq8","value":"001b000002IKeq8AAD"},{"label":"16/11/2015","value":"2015-11-16"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000001vAVed","value":"001b000001vAVedAAG"},{"label":"30/10/2015","value":"2015-10-30"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b0000025mcjt","value":"001b0000025mcjtAAA"},{"label":"11/11/2015","value":"2015-11-11"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000001vAVfq","value":"001b000001vAVfqAAG"},{"label":"30/10/2015","value":"2015-10-30"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000002IM3aH","value":"001b000002IM3aHAAT"},{"label":"20/11/2015","value":"2015-11-20"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b0000025nODd","value":"001b0000025nODdAAM"},{"label":"13/11/2015","value":"2015-11-13"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000002ILI5p","value":"001b000002ILI5pAAH"},{"label":"18/11/2015","value":"2015-11-18"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000001vAUkH","value":"001b000001vAUkHAAW"},{"label":"30/10/2015","value":"2015-10-30"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b0000025l7w3","value":"001b0000025l7w3AAA"},{"label":"06/11/2015","value":"2015-11-06"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000001vAS56","value":"001b000001vAS56AAG"},{"label":"30/10/2015","value":"2015-10-30"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b0000025mLDx","value":"001b0000025mLDxAAM"},{"label":"10/11/2015","value":"2015-11-10"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b0000025l1DZ","value":"001b0000025l1DZAAY"},{"label":"06/11/2015","value":"2015-11-06"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b0000025mIxi","value":"001b0000025mIxiAAE"},{"label":"10/11/2015","value":"2015-11-10"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b0000025mf1c","value":"001b0000025mf1cAAA"},{"label":"11/11/2015","value":"2015-11-11"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000001vB5oD","value":"001b000001vB5oDAAS"},{"label":"02/11/2015","value":"2015-11-02"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b0000025m1FS","value":"001b0000025m1FSAAY"},{"label":"09/11/2015","value":"2015-11-09"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003QDCVv","value":"001b000003QDCVvAAP"},{"label":"15/06/2016","value":"2016-06-15"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000002SqanE","value":"001b000002SqanEAAR"},{"label":"26/11/2015","value":"2015-11-26"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000002XBPcN","value":"001b000002XBPcNAAX"},{"label":"04/12/2015","value":"2015-12-04"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000002XDJSe","value":"001b000002XDJSeAAP"},{"label":"09/12/2015","value":"2015-12-09"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000002hHWm4","value":"001b000002hHWm4AAG"},{"label":"25/12/2015","value":"2015-12-25"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000002XCNlN","value":"001b000002XCNlNAAX"},{"label":"07/12/2015","value":"2015-12-07"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003HmYF9","value":"001b000003HmYF9AAN"},{"label":"26/01/2016","value":"2016-01-26"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003LRJz0","value":"001b000003LRJz0AAH"},{"label":"29/03/2016","value":"2016-03-29"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000002XDEtb","value":"001b000002XDEtbAAH"},{"label":"09/12/2015","value":"2015-12-09"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003LSCcL","value":"001b000003LSCcLAAX"},{"label":"04/04/2016","value":"2016-04-04"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000002XCJhZ","value":"001b000002XCJhZAAX"},{"label":"07/12/2015","value":"2015-12-07"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000002XDDTo","value":"001b000002XDDToAAP"},{"label":"09/12/2015","value":"2015-12-09"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003JnFI1","value":"001b000003JnFI1AAN"},{"label":"18/03/2016","value":"2016-03-18"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000002QMeDt","value":"001b000002QMeDtAAL"},{"label":"24/11/2015","value":"2015-11-24"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000002hH84k","value":"001b000002hH84kAAC"},{"label":"23/12/2015","value":"2015-12-23"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000002XDZrY","value":"001b000002XDZrYAAX"},{"label":"10/12/2015","value":"2015-12-10"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003F80d4","value":"001b000003F80d4AAB"},{"label":"27/01/2016","value":"2016-01-27"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000002hFYdA","value":"001b000002hFYdAAAW"},{"label":"14/12/2015","value":"2015-12-14"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003Jnll3","value":"001b000003Jnll3AAB"},{"label":"22/03/2016","value":"2016-03-22"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b0000038Gwa6","value":"001b0000038Gwa6AAC"},{"label":"19/01/2016","value":"2016-01-19"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000002XAVx8","value":"001b000002XAVx8AAH"},{"label":"02/12/2015","value":"2015-12-02"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000002XAvgB","value":"001b000002XAvgBAAT"},{"label":"03/12/2015","value":"2015-12-03"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b0000038G9xO","value":"001b0000038G9xOAAS"},{"label":"14/01/2016","value":"2016-01-14"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b0000038FfF0","value":"001b0000038FfF0AAK"},{"label":"11/01/2016","value":"2016-01-11"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b0000038G936","value":"001b0000038G936AAC"},{"label":"14/01/2016","value":"2016-01-14"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000002hHuUt","value":"001b000002hHuUtAAK"},{"label":"29/12/2015","value":"2015-12-29"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b0000038FxvU","value":"001b0000038FxvUAAS"},{"label":"13/01/2016","value":"2016-01-13"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003F88Ke","value":"001b000003F88KeAAJ"},{"label":"28/01/2016","value":"2016-01-28"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003F8BMV","value":"001b000003F8BMVAA3"},{"label":"28/01/2016","value":"2016-01-28"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003F8Mpr","value":"001b000003F8MprAAF"},{"label":"29/01/2016","value":"2016-01-29"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003F8O8F","value":"001b000003F8O8FAAV"},{"label":"29/01/2016","value":"2016-01-29"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003ICDOj","value":"001b000003ICDOjAAP"},{"label":"05/02/2016","value":"2016-02-05"},{"label":"Manual","value":"Manual"}]},{"dataCells":[{"label":"001b000003ICDOk","value":"001b000003ICDOkAAP"},{"label":"05/02/2016","value":"2016-02-05"},{"label":"Manual","value":"Manual"}]},{"dataCells":[{"label":"001b000003ICDOl","value":"001b000003ICDOlAAP"},{"label":"05/02/2016","value":"2016-02-05"},{"label":"Manual","value":"Manual"}]},{"dataCells":[{"label":"001b000003ICDOm","value":"001b000003ICDOmAAP"},{"label":"05/02/2016","value":"2016-02-05"},{"label":"Manual","value":"Manual"}]},{"dataCells":[{"label":"001b000003ICDOn","value":"001b000003ICDOnAAP"},{"label":"05/02/2016","value":"2016-02-05"},{"label":"Manual","value":"Manual"}]},{"dataCells":[{"label":"001b000003ICDOo","value":"001b000003ICDOoAAP"},{"label":"05/02/2016","value":"2016-02-05"},{"label":"Manual","value":"Manual"}]},{"dataCells":[{"label":"001b000003ICDOp","value":"001b000003ICDOpAAP"},{"label":"05/02/2016","value":"2016-02-05"},{"label":"Manual","value":"Manual"}]},{"dataCells":[{"label":"001b000003ICDOq","value":"001b000003ICDOqAAP"},{"label":"05/02/2016","value":"2016-02-05"},{"label":"Manual","value":"Manual"}]},{"dataCells":[{"label":"001b000003ICDOr","value":"001b000003ICDOrAAP"},{"label":"05/02/2016","value":"2016-02-05"},{"label":"Manual","value":"Manual"}]},{"dataCells":[{"label":"001b000003ICDOs","value":"001b000003ICDOsAAP"},{"label":"05/02/2016","value":"2016-02-05"},{"label":"Manual","value":"Manual"}]},{"dataCells":[{"label":"001b000003ICDOt","value":"001b000003ICDOtAAP"},{"label":"05/02/2016","value":"2016-02-05"},{"label":"Manual","value":"Manual"}]},{"dataCells":[{"label":"001b000003ICDOu","value":"001b000003ICDOuAAP"},{"label":"05/02/2016","value":"2016-02-05"},{"label":"Manual","value":"Manual"}]},{"dataCells":[{"label":"001b000003ICDOv","value":"001b000003ICDOvAAP"},{"label":"05/02/2016","value":"2016-02-05"},{"label":"Manual","value":"Manual"}]},{"dataCells":[{"label":"001b000003ICDOw","value":"001b000003ICDOwAAP"},{"label":"05/02/2016","value":"2016-02-05"},{"label":"Manual","value":"Manual"}]},{"dataCells":[{"label":"001b000003ICDOx","value":"001b000003ICDOxAAP"},{"label":"05/02/2016","value":"2016-02-05"},{"label":"Manual","value":"Manual"}]},{"dataCells":[{"label":"001b000003ICDOy","value":"001b000003ICDOyAAP"},{"label":"05/02/2016","value":"2016-02-05"},{"label":"Manual","value":"Manual"}]},{"dataCells":[{"label":"001b000003ICDOz","value":"001b000003ICDOzAAP"},{"label":"05/02/2016","value":"2016-02-05"},{"label":"Manual","value":"Manual"}]},{"dataCells":[{"label":"001b000003ICDP0","value":"001b000003ICDP0AAP"},{"label":"05/02/2016","value":"2016-02-05"},{"label":"Manual","value":"Manual"}]},{"dataCells":[{"label":"001b000003ICDjU","value":"001b000003ICDjUAAX"},{"label":"05/02/2016","value":"2016-02-05"},{"label":"Manual","value":"Manual"}]},{"dataCells":[{"label":"001b000003ICDjV","value":"001b000003ICDjVAAX"},{"label":"05/02/2016","value":"2016-02-05"},{"label":"Manual","value":"Manual"}]},{"dataCells":[{"label":"001b000003ICDjW","value":"001b000003ICDjWAAX"},{"label":"05/02/2016","value":"2016-02-05"},{"label":"Manual","value":"Manual"}]},{"dataCells":[{"label":"001b000003ICDjX","value":"001b000003ICDjXAAX"},{"label":"05/02/2016","value":"2016-02-05"},{"label":"Manual","value":"Manual"}]},{"dataCells":[{"label":"001b000003ICDGa","value":"001b000003ICDGaAAP"},{"label":"05/02/2016","value":"2016-02-05"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003ICDOG","value":"001b000003ICDOGAA5"},{"label":"05/02/2016","value":"2016-02-05"},{"label":"Manual","value":"Manual"}]},{"dataCells":[{"label":"001b000003ICDOH","value":"001b000003ICDOHAA5"},{"label":"05/02/2016","value":"2016-02-05"},{"label":"Manual","value":"Manual"}]},{"dataCells":[{"label":"001b000003ICDOI","value":"001b000003ICDOIAA5"},{"label":"05/02/2016","value":"2016-02-05"},{"label":"Manual","value":"Manual"}]},{"dataCells":[{"label":"001b000003ICDOJ","value":"001b000003ICDOJAA5"},{"label":"05/02/2016","value":"2016-02-05"},{"label":"Manual","value":"Manual"}]},{"dataCells":[{"label":"001b000003IE9sW","value":"001b000003IE9sWAAT"},{"label":"17/02/2016","value":"2016-02-17"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b0000038GxCh","value":"001b0000038GxChAAK"},{"label":"19/01/2016","value":"2016-01-19"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003IEhgj","value":"001b000003IEhgjAAD"},{"label":"20/02/2016","value":"2016-02-20"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003ICyaQ","value":"001b000003ICyaQAAT"},{"label":"10/02/2016","value":"2016-02-10"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b0000038FqYP","value":"001b0000038FqYPAA0"},{"label":"12/01/2016","value":"2016-01-12"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003ICble","value":"001b000003ICbleAAD"},{"label":"08/02/2016","value":"2016-02-08"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003ID0Zw","value":"001b000003ID0ZwAAL"},{"label":"10/02/2016","value":"2016-02-10"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003ICOQh","value":"001b000003ICOQhAAP"},{"label":"06/02/2016","value":"2016-02-06"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003IFOIY","value":"001b000003IFOIYAA5"},{"label":"25/02/2016","value":"2016-02-25"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003IFczC","value":"001b000003IFczCAAT"},{"label":"26/02/2016","value":"2016-02-26"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003UUpsG","value":"001b000003UUpsGAAT"},{"label":"02/09/2016","value":"2016-09-02"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003UUnEK","value":"001b000003UUnEKAA1"},{"label":"02/09/2016","value":"2016-09-02"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003UUu1V","value":"001b000003UUu1VAAT"},{"label":"02/09/2016","value":"2016-09-02"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003TvuVn","value":"001b000003TvuVnAAJ"},{"label":"17/08/2016","value":"2016-08-17"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003VBxZJ","value":"001b000003VBxZJAA1"},{"label":"07/09/2016","value":"2016-09-07"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003Tvoan","value":"001b000003TvoanAAB"},{"label":"17/08/2016","value":"2016-08-17"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003VDDXR","value":"001b000003VDDXRAA5"},{"label":"12/09/2016","value":"2016-09-12"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003VDCL6","value":"001b000003VDCL6AAP"},{"label":"12/09/2016","value":"2016-09-12"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003UT2CF","value":"001b000003UT2CFAA1"},{"label":"26/08/2016","value":"2016-08-26"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003UU1El","value":"001b000003UU1ElAAL"},{"label":"30/08/2016","value":"2016-08-30"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003UUFoM","value":"001b000003UUFoMAAX"},{"label":"31/08/2016","value":"2016-08-31"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003WBDLY","value":"001b000003WBDLYAA5"},{"label":"29/09/2016","value":"2016-09-29"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003WtZE0","value":"001b000003WtZE0AAN"},{"label":"07/10/2016","value":"2016-10-07"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003VDZQk","value":"001b000003VDZQkAAP"},{"label":"13/09/2016","value":"2016-09-13"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003VERnU","value":"001b000003VERnUAAX"},{"label":"16/09/2016","value":"2016-09-16"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003VDsFb","value":"001b000003VDsFbAAL"},{"label":"14/09/2016","value":"2016-09-14"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003VDvOE","value":"001b000003VDvOEAA1"},{"label":"14/09/2016","value":"2016-09-14"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003VE6FU","value":"001b000003VE6FUAA1"},{"label":"15/09/2016","value":"2016-09-15"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003VZVC5","value":"001b000003VZVC5AAP"},{"label":"19/09/2016","value":"2016-09-19"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003VZe1A","value":"001b000003VZe1AAAT"},{"label":"20/09/2016","value":"2016-09-20"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003VDvxH","value":"001b000003VDvxHAAT"},{"label":"14/09/2016","value":"2016-09-14"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003VZq5O","value":"001b000003VZq5OAAT"},{"label":"20/09/2016","value":"2016-09-20"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003Va8AH","value":"001b000003Va8AHAAZ"},{"label":"21/09/2016","value":"2016-09-21"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003P5Gl1","value":"001b000003P5Gl1AAF"},{"label":"21/07/2016","value":"2016-07-21"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003P4Uii","value":"001b000003P4UiiAAF"},{"label":"18/07/2016","value":"2016-07-18"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003P35rg","value":"001b000003P35rgAAB"},{"label":"12/07/2016","value":"2016-07-12"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003P336L","value":"001b000003P336LAAR"},{"label":"12/07/2016","value":"2016-07-12"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003P5FQ7","value":"001b000003P5FQ7AAN"},{"label":"21/07/2016","value":"2016-07-21"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003P4V8b","value":"001b000003P4V8bAAF"},{"label":"18/07/2016","value":"2016-07-18"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003P54Qc","value":"001b000003P54QcAAJ"},{"label":"20/07/2016","value":"2016-07-20"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003P541a","value":"001b000003P541aAAB"},{"label":"20/07/2016","value":"2016-07-20"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003P4pbg","value":"001b000003P4pbgAAB"},{"label":"19/07/2016","value":"2016-07-19"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003P5E5I","value":"001b000003P5E5IAAV"},{"label":"21/07/2016","value":"2016-07-21"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003TBCyl","value":"001b000003TBCylAAH"},{"label":"29/07/2016","value":"2016-07-29"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003P3Auo","value":"001b000003P3AuoAAF"},{"label":"12/07/2016","value":"2016-07-12"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003P3Iqd","value":"001b000003P3IqdAAF"},{"label":"13/07/2016","value":"2016-07-13"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003TuISa","value":"001b000003TuISaAAN"},{"label":"11/08/2016","value":"2016-08-11"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003W9v8g","value":"001b000003W9v8gAAB"},{"label":"26/09/2016","value":"2016-09-26"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003RhcRh","value":"001b000003RhcRhAAJ"},{"label":"05/07/2016","value":"2016-07-05"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003RhdsI","value":"001b000003RhdsIAAR"},{"label":"05/07/2016","value":"2016-07-05"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003RLGBN","value":"001b000003RLGBNAA5"},{"label":"27/06/2016","value":"2016-06-27"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003RMNhZ","value":"001b000003RMNhZAAX"},{"label":"01/07/2016","value":"2016-07-01"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003QVWSh","value":"001b000003QVWShAAP"},{"label":"25/06/2016","value":"2016-06-25"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003RM28C","value":"001b000003RM28CAAT"},{"label":"30/06/2016","value":"2016-06-30"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003RLaPl","value":"001b000003RLaPlAAL"},{"label":"28/06/2016","value":"2016-06-28"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003RLHec","value":"001b000003RLHecAAH"},{"label":"27/06/2016","value":"2016-06-27"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003RLimT","value":"001b000003RLimTAAT"},{"label":"29/06/2016","value":"2016-06-29"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003RLUG5","value":"001b000003RLUG5AAP"},{"label":"28/06/2016","value":"2016-06-28"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003XFYt5","value":"001b000003XFYt5AAH"},{"label":"10/10/2016","value":"2016-10-10"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003Xd9Aj","value":"001b000003Xd9AjAAJ"},{"label":"18/10/2016","value":"2016-10-18"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003Xfsss","value":"001b000003XfsssAAB"},{"label":"28/10/2016","value":"2016-10-28"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003XfODN","value":"001b000003XfODNAA3"},{"label":"26/10/2016","value":"2016-10-26"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003YWre2","value":"001b000003YWre2AAD"},{"label":"07/11/2016","value":"2016-11-07"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003Xf4NB","value":"001b000003Xf4NBAAZ"},{"label":"25/10/2016","value":"2016-10-25"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003YVs4h","value":"001b000003YVs4hAAD"},{"label":"03/11/2016","value":"2016-11-03"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003XdqvL","value":"001b000003XdqvLAAR"},{"label":"21/10/2016","value":"2016-10-21"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003YVPwd","value":"001b000003YVPwdAAH"},{"label":"01/11/2016","value":"2016-11-01"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003XFcNL","value":"001b000003XFcNLAA1"},{"label":"10/10/2016","value":"2016-10-10"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003Xcfi7","value":"001b000003Xcfi7AAB"},{"label":"17/10/2016","value":"2016-10-17"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003XGcC1","value":"001b000003XGcC1AAL"},{"label":"14/10/2016","value":"2016-10-14"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000000pOXbw","value":"001b000000pOXbwAAG"},{"label":"20/05/2015","value":"2015-05-20"},{"label":"Manual","value":"Manual"}]},{"dataCells":[{"label":"001b000000pOZGL","value":"001b000000pOZGLAA4"},{"label":"20/05/2015","value":"2015-05-20"},{"label":"Manual","value":"Manual"}]},{"dataCells":[{"label":"001b000000pOfO1","value":"001b000000pOfO1AAK"},{"label":"20/05/2015","value":"2015-05-20"},{"label":"Manual","value":"Manual"}]},{"dataCells":[{"label":"001b000000pOfVx","value":"001b000000pOfVxAAK"},{"label":"20/05/2015","value":"2015-05-20"},{"label":"Manual","value":"Manual"}]},{"dataCells":[{"label":"001b000000pOi4j","value":"001b000000pOi4jAAC"},{"label":"20/05/2015","value":"2015-05-20"},{"label":"Manual","value":"Manual"}]},{"dataCells":[{"label":"001b000000pOfxe","value":"001b000000pOfxeAAC"},{"label":"20/05/2015","value":"2015-05-20"},{"label":"Auto","value":"Auto"}]},{"dataCells":[{"label":"001b000000pOaPc","value":"001b000000pOaPcAAK"},{"label":"20/05/2015","value":"2015-05-20"},{"label":"Manual","value":"Manual"}]},{"dataCells":[{"label":"001b000000pObsW","value":"001b000000pObsWAAS"},{"label":"20/05/2015","value":"2015-05-20"},{"label":"Manual","value":"Manual"}]},{"dataCells":[{"label":"001b000000pOzb1","value":"001b000000pOzb1AAC"},{"label":"20/05/2015","value":"2015-05-20"},{"label":"Manual","value":"Manual"}]},{"dataCells":[{"label":"001b000000pOiii","value":"001b000000pOiiiAAC"},{"label":"20/05/2015","value":"2015-05-20"},{"label":"Manual","value":"Manual"}]},{"dataCells":[{"label":"001b000000pOasS","value":"001b000000pOasSAAS"},{"label":"20/05/2015","value":"2015-05-20"},{"label":"Auto","value":"Auto"}]},{"dataCells":[{"label":"001b000000pOgZp","value":"001b000000pOgZpAAK"},{"label":"20/05/2015","value":"2015-05-20"},{"label":"Manual","value":"Manual"}]},{"dataCells":[{"label":"001b000000pOb1Q","value":"001b000000pOb1QAAS"},{"label":"20/05/2015","value":"2015-05-20"},{"label":"Manual","value":"Manual"}]},{"dataCells":[{"label":"001b000000pOaGR","value":"001b000000pOaGRAA0"},{"label":"20/05/2015","value":"2015-05-20"},{"label":"Manual","value":"Manual"}]},{"dataCells":[{"label":"001b000000pOdlM","value":"001b000000pOdlMAAS"},{"label":"20/05/2015","value":"2015-05-20"},{"label":"Manual","value":"Manual"}]},{"dataCells":[{"label":"001b000000pQqSe","value":"001b000000pQqSeAAK"},{"label":"27/05/2015","value":"2015-05-27"},{"label":"Manual","value":"Manual"}]},{"dataCells":[{"label":"001b000000pOdyN","value":"001b000000pOdyNAAS"},{"label":"20/05/2015","value":"2015-05-20"},{"label":"Manual","value":"Manual"}]},{"dataCells":[{"label":"001b000000pOcKV","value":"001b000000pOcKVAA0"},{"label":"20/05/2015","value":"2015-05-20"},{"label":"Manual","value":"Manual"}]},{"dataCells":[{"label":"001b000000pOdjj","value":"001b000000pOdjjAAC"},{"label":"20/05/2015","value":"2015-05-20"},{"label":"Manual","value":"Manual"}]},{"dataCells":[{"label":"001b000000pObzR","value":"001b000000pObzRAAS"},{"label":"20/05/2015","value":"2015-05-20"},{"label":"Manual","value":"Manual"}]},{"dataCells":[{"label":"001b000000pOgDG","value":"001b000000pOgDGAA0"},{"label":"20/05/2015","value":"2015-05-20"},{"label":"Manual","value":"Manual"}]},{"dataCells":[{"label":"001b000000pOgPC","value":"001b000000pOgPCAA0"},{"label":"20/05/2015","value":"2015-05-20"},{"label":"Manual","value":"Manual"}]},{"dataCells":[{"label":"001b000000pOd1L","value":"001b000000pOd1LAAS"},{"label":"20/05/2015","value":"2015-05-20"},{"label":"Manual","value":"Manual"}]},{"dataCells":[{"label":"001b000000pOgnV","value":"001b000000pOgnVAAS"},{"label":"20/05/2015","value":"2015-05-20"},{"label":"Manual","value":"Manual"}]},{"dataCells":[{"label":"001b000000pOgEw","value":"001b000000pOgEwAAK"},{"label":"20/05/2015","value":"2015-05-20"},{"label":"Manual","value":"Manual"}]},{"dataCells":[{"label":"001b000000pOf2d","value":"001b000000pOf2dAAC"},{"label":"20/05/2015","value":"2015-05-20"},{"label":"Manual","value":"Manual"}]},{"dataCells":[{"label":"001b000000pOf0l","value":"001b000000pOf0lAAC"},{"label":"20/05/2015","value":"2015-05-20"},{"label":"Auto","value":"Auto"}]},{"dataCells":[{"label":"001b000000pOZwG","value":"001b000000pOZwGAAW"},{"label":"20/05/2015","value":"2015-05-20"},{"label":"Manual","value":"Manual"}]},{"dataCells":[{"label":"001b000000pOf10","value":"001b000000pOf10AAC"},{"label":"20/05/2015","value":"2015-05-20"},{"label":"Manual","value":"Manual"}]},{"dataCells":[{"label":"001b000000pOawo","value":"001b000000pOawoAAC"},{"label":"20/05/2015","value":"2015-05-20"},{"label":"Manual","value":"Manual"}]},{"dataCells":[{"label":"001b000000pOax0","value":"001b000000pOax0AAC"},{"label":"20/05/2015","value":"2015-05-20"},{"label":"Manual","value":"Manual"}]},{"dataCells":[{"label":"001b000000pOZnF","value":"001b000000pOZnFAAW"},{"label":"20/05/2015","value":"2015-05-20"},{"label":"Manual","value":"Manual"}]},{"dataCells":[{"label":"001b000000pOgNp","value":"001b000000pOgNpAAK"},{"label":"20/05/2015","value":"2015-05-20"},{"label":"Manual","value":"Manual"}]},{"dataCells":[{"label":"001b000000pOgpr","value":"001b000000pOgprAAC"},{"label":"20/05/2015","value":"2015-05-20"},{"label":"Manual","value":"Manual"}]},{"dataCells":[{"label":"001b000000pOZo8","value":"001b000000pOZo8AAG"},{"label":"20/05/2015","value":"2015-05-20"},{"label":"Manual","value":"Manual"}]},{"dataCells":[{"label":"001b000000pOaZR","value":"001b000000pOaZRAA0"},{"label":"20/05/2015","value":"2015-05-20"},{"label":"Manual","value":"Manual"}]},{"dataCells":[{"label":"001b000000pOZuA","value":"001b000000pOZuAAAW"},{"label":"20/05/2015","value":"2015-05-20"},{"label":"Manual","value":"Manual"}]},{"dataCells":[{"label":"001b000000pObvM","value":"001b000000pObvMAAS"},{"label":"20/05/2015","value":"2015-05-20"},{"label":"Manual","value":"Manual"}]},{"dataCells":[{"label":"001b000000pOfGm","value":"001b000000pOfGmAAK"},{"label":"20/05/2015","value":"2015-05-20"},{"label":"Manual","value":"Manual"}]},{"dataCells":[{"label":"001b000000pOcmx","value":"001b000000pOcmxAAC"},{"label":"20/05/2015","value":"2015-05-20"},{"label":"Manual","value":"Manual"}]},{"dataCells":[{"label":"001b000000pOf8r","value":"001b000000pOf8rAAC"},{"label":"20/05/2015","value":"2015-05-20"},{"label":"Auto","value":"Auto"}]},{"dataCells":[{"label":"001b000000pOddo","value":"001b000000pOddoAAC"},{"label":"20/05/2015","value":"2015-05-20"},{"label":"Manual","value":"Manual"}]},{"dataCells":[{"label":"001b000000pOg99","value":"001b000000pOg99AAC"},{"label":"20/05/2015","value":"2015-05-20"},{"label":"Manual","value":"Manual"}]},{"dataCells":[{"label":"001b000000pOXk5","value":"001b000000pOXk5AAG"},{"label":"20/05/2015","value":"2015-05-20"},{"label":"Manual","value":"Manual"}]},{"dataCells":[{"label":"001b000000pOhLZ","value":"001b000000pOhLZAA0"},{"label":"20/05/2015","value":"2015-05-20"},{"label":"Manual","value":"Manual"}]},{"dataCells":[{"label":"001b000000pOeiO","value":"001b000000pOeiOAAS"},{"label":"20/05/2015","value":"2015-05-20"},{"label":"Manual","value":"Manual"}]},{"dataCells":[{"label":"001b000000pOXkp","value":"001b000000pOXkpAAG"},{"label":"20/05/2015","value":"2015-05-20"},{"label":"Manual","value":"Manual"}]},{"dataCells":[{"label":"001b000000pOePA","value":"001b000000pOePAAA0"},{"label":"20/05/2015","value":"2015-05-20"},{"label":"Manual","value":"Manual"}]},{"dataCells":[{"label":"001b000000pOetj","value":"001b000000pOetjAAC"},{"label":"20/05/2015","value":"2015-05-20"},{"label":"Manual","value":"Manual"}]},{"dataCells":[{"label":"001b000000pOaD4","value":"001b000000pOaD4AAK"},{"label":"20/05/2015","value":"2015-05-20"},{"label":"Manual","value":"Manual"}]},{"dataCells":[{"label":"001b000000pOeoP","value":"001b000000pOeoPAAS"},{"label":"20/05/2015","value":"2015-05-20"},{"label":"Manual","value":"Manual"}]},{"dataCells":[{"label":"001b000000pOdGm","value":"001b000000pOdGmAAK"},{"label":"20/05/2015","value":"2015-05-20"},{"label":"Manual","value":"Manual"}]},{"dataCells":[{"label":"001b000000pOYrw","value":"001b000000pOYrwAAG"},{"label":"20/05/2015","value":"2015-05-20"},{"label":"Manual","value":"Manual"}]},{"dataCells":[{"label":"001b000000pOfBk","value":"001b000000pOfBkAAK"},{"label":"20/05/2015","value":"2015-05-20"},{"label":"Manual","value":"Manual"}]},{"dataCells":[{"label":"001b000000pOfBz","value":"001b000000pOfBzAAK"},{"label":"20/05/2015","value":"2015-05-20"},{"label":"Auto","value":"Auto"}]},{"dataCells":[{"label":"001b000000pOfhn","value":"001b000000pOfhnAAC"},{"label":"20/05/2015","value":"2015-05-20"},{"label":"Manual","value":"Manual"}]},{"dataCells":[{"label":"001b000000pOepI","value":"001b000000pOepIAAS"},{"label":"20/05/2015","value":"2015-05-20"},{"label":"Manual","value":"Manual"}]},{"dataCells":[{"label":"001b000000pOhZN","value":"001b000000pOhZNAA0"},{"label":"20/05/2015","value":"2015-05-20"},{"label":"Manual","value":"Manual"}]},{"dataCells":[{"label":"001b000000pOY4S","value":"001b000000pOY4SAAW"},{"label":"20/05/2015","value":"2015-05-20"},{"label":"Manual","value":"Manual"}]},{"dataCells":[{"label":"001b000000pOhmu","value":"001b000000pOhmuAAC"},{"label":"20/05/2015","value":"2015-05-20"},{"label":"Manual","value":"Manual"}]},{"dataCells":[{"label":"001b000000pOcMu","value":"001b000000pOcMuAAK"},{"label":"20/05/2015","value":"2015-05-20"},{"label":"Manual","value":"Manual"}]},{"dataCells":[{"label":"001b000000pOdAY","value":"001b000000pOdAYAA0"},{"label":"20/05/2015","value":"2015-05-20"},{"label":"Manual","value":"Manual"}]},{"dataCells":[{"label":"001b000000pOXhZ","value":"001b000000pOXhZAAW"},{"label":"20/05/2015","value":"2015-05-20"},{"label":"Manual","value":"Manual"}]},{"dataCells":[{"label":"001b000000pObY7","value":"001b000000pObY7AAK"},{"label":"20/05/2015","value":"2015-05-20"},{"label":"Auto","value":"Auto"}]},{"dataCells":[{"label":"001b000000pOYLd","value":"001b000000pOYLdAAO"},{"label":"20/05/2015","value":"2015-05-20"},{"label":"Manual","value":"Manual"}]},{"dataCells":[{"label":"001b000000pOc6R","value":"001b000000pOc6RAAS"},{"label":"20/05/2015","value":"2015-05-20"},{"label":"Manual","value":"Manual"}]},{"dataCells":[{"label":"001b000000pOc6c","value":"001b000000pOc6cAAC"},{"label":"20/05/2015","value":"2015-05-20"},{"label":"Manual","value":"Manual"}]},{"dataCells":[{"label":"001b000000pOa26","value":"001b000000pOa26AAC"},{"label":"20/05/2015","value":"2015-05-20"},{"label":"Manual","value":"Manual"}]},{"dataCells":[{"label":"001b000000pOhGt","value":"001b000000pOhGtAAK"},{"label":"20/05/2015","value":"2015-05-20"},{"label":"Manual","value":"Manual"}]},{"dataCells":[{"label":"001b000000pObL5","value":"001b000000pObL5AAK"},{"label":"20/05/2015","value":"2015-05-20"},{"label":"Manual","value":"Manual"}]},{"dataCells":[{"label":"001b000000pOfbk","value":"001b000000pOfbkAAC"},{"label":"20/05/2015","value":"2015-05-20"},{"label":"Manual","value":"Manual"}]},{"dataCells":[{"label":"001b000000pOZAV","value":"001b000000pOZAVAA4"},{"label":"20/05/2015","value":"2015-05-20"},{"label":"Manual","value":"Manual"}]},{"dataCells":[{"label":"001b000000pOcWT","value":"001b000000pOcWTAA0"},{"label":"20/05/2015","value":"2015-05-20"},{"label":"Manual","value":"Manual"}]},{"dataCells":[{"label":"001b000000pOZkW","value":"001b000000pOZkWAAW"},{"label":"20/05/2015","value":"2015-05-20"},{"label":"Manual","value":"Manual"}]},{"dataCells":[{"label":"001b000000pOgfJ","value":"001b000000pOgfJAAS"},{"label":"20/05/2015","value":"2015-05-20"},{"label":"Manual","value":"Manual"}]},{"dataCells":[{"label":"001b000000pOfZS","value":"001b000000pOfZSAA0"},{"label":"20/05/2015","value":"2015-05-20"},{"label":"Manual","value":"Manual"}]},{"dataCells":[{"label":"001b000000pOiiS","value":"001b000000pOiiSAAS"},{"label":"20/05/2015","value":"2015-05-20"},{"label":"Manual","value":"Manual"}]},{"dataCells":[{"label":"001b000000pOdKI","value":"001b000000pOdKIAA0"},{"label":"20/05/2015","value":"2015-05-20"},{"label":"Manual","value":"Manual"}]},{"dataCells":[{"label":"001b000000pOaoO","value":"001b000000pOaoOAAS"},{"label":"20/05/2015","value":"2015-05-20"},{"label":"Manual","value":"Manual"}]},{"dataCells":[{"label":"001b000000pOe1N","value":"001b000000pOe1NAAS"},{"label":"20/05/2015","value":"2015-05-20"},{"label":"Manual","value":"Manual"}]},{"dataCells":[{"label":"001b000003QUeNy","value":"001b000003QUeNyAAL"},{"label":"24/06/2016","value":"2016-06-24"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000003YV3xb","value":"001b000003YV3xbAAD"},{"label":"31/10/2016","value":"2016-10-31"},{"label":"Custom","value":"Custom"}]},{"dataCells":[{"label":"001b000000puwVG","value":"001b000000puwVGAAY"},{"label":"29/05/2015","value":"2015-05-29"},{"label":"Manual","value":"Manual"}]},{"dataCells":[{"label":"001b000000rp5ZP","value":"001b000000rp5ZPAAY"},{"label":"01/07/2015","value":"2015-07-01"},{"label":"Manual","value":"Manual"}]},{"dataCells":[{"label":"001b000003QUDA9","value":"001b000003QUDA9AAP"},{"label":"22/06/2016","value":"2016-06-22"},{"label":"Custom","value":"Custom"}]}]}},"groupingsAcross":{"groupings":[]},"groupingsDown":{"groupings":[]},"hasDetailRows":true,"reportExtendedMetadata":{"aggregateColumnInfo":{"RowCount":{"acrossGroupingContext":null,"dataType":"int","downGroupingContext":null,"label":"Record Count"}},"detailColumnInfo":{"ACCOUNT_ID":{"dataType":"id","label":"Account ID"},"CREATED_DATE":{"dataType":"datetime","label":"Created Date"},"Account.Brick_type__c":{"dataType":"picklist","label":"Brick Type"}},"groupingColumnInfo":{}},"reportMetadata":{"aggregates":["RowCount"],"currency":"USD","detailColumns":["ACCOUNT_ID","CREATED_DATE","Account.Brick_type__c"],"developerName":"TEMP","groupingsAcross":[],"groupingsDown":[],"historicalSnapshotDates":[],"id":"00Ob0000003uviHEAQ","name":"TEMP","reportBooleanFilter":null,"reportFilters":[{"column":"RECORDTYPE","operator":"equals","value":"Business Account - RU"},{"column":"Account.Terr_Rule_Applied__c","operator":"equals","value":""},{"column":"ADDRESS2_STREET","operator":"notEqual","value":"Chain"}],"reportFormat":"TABULAR","reportType":{"label":"Accounts","type":"AccountList@Account.Brick__c"}}}

 * @author Maciej Szymczak
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

		if (args.length>0) {
			hostName      = args[0];
			username      = args[1];
			password      = args[2];
			reportId      = args[3];
			csvFile       = args[4];
			SOQL          = args[5];
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

			System.out.print("Enter reportId (or accept 00Ob0000003uviH):");
			terminalInput = new Scanner(System.in);
			reportId = terminalInput.nextLine();				
			if ((reportId+"").length() == 0)  reportId = "00Ob0000003uviH";
			
			System.out.print("Enter output csv file name (or accept C:/Temp/csv.csv):");
			terminalInput = new Scanner(System.in);
			csvFile = terminalInput.nextLine();				
			if ((csvFile+"").length() == 0)  csvFile = "C:/Temp/x.csv";

			System.out.print("Enter SOQL (or accept select+Id,Name+From+Account+Limit+10):");
			terminalInput = new Scanner(System.in);
			SOQL = terminalInput.nextLine();				
			if ((SOQL+"").length() == 0)  SOQL = "select+Id,Name+From+Account+Limit+10";
		};		
		
		downloadReport http = new downloadReport();
		try {
			//both authorization methods do work
			http.getSecurityTokenMethod1(hostName,  username, password);
			//Method2 works too, it is additionally required to delived client_id and client_secret
			//String accessToken = http.getSecurityTokenMethod2(hostName, client_id, client_secret, username, password);
		    String data = http.getSOQLData(gserverUrl, gaccessToken, SOQL);
			 data = http.getReportData(gserverUrl, gaccessToken, reportId, csvFile);	
				
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
