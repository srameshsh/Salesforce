

import java.io.InputStreamReader;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class RESTJAVATEST {
	private static final String CLIENT_ID = "CLIENT_ID";
	private static final String CLIENT_SECRET = "CLIENT_SECRET";
	private static final String REDIRECT_URL = "http:/localhost:9090/lead";
	private static final String ENV_LOGIN_URL = "https://login.salesforce.com";
	private static final String USERNAME = "USERNAME";
	private static final String PASSWORD = "PASSWORDANDSECURITYTOKEN";
	
	
	private static  String tokenUrl = "";
	private static  String access_token = "";
	private static  String instance_url = "";
	
	public RESTJAVATEST() {
		// TODO Auto-generated constructor stub
	}
	public static void main(String[] args) {
		try {
			tokenUrl = ENV_LOGIN_URL+"/services/oauth2/token";
			HttpClient client = new HttpClient();
			PostMethod post = new PostMethod(tokenUrl);
			post.addParameter("grant_type","password");
			post.addParameter("client_id",CLIENT_ID);
			post.addParameter("client_secret",CLIENT_SECRET);			
			post.addParameter("redirect_url",REDIRECT_URL);
			post.addParameter("username",USERNAME);
			post.addParameter("password",PASSWORD);
			client.executeMethod(post);
			//System.out.println(post.getStatusCode());
			if(post.getStatusCode() == 200){
				JSONObject resp = new JSONObject(new JSONTokener(new InputStreamReader(post.getResponseBodyAsStream())));
				//	System.out.println(resp.toString());
					access_token = resp.getString("access_token");
					instance_url = resp.getString("instance_url");
					System.out.println("access_token :: "+access_token);
					System.out.println("instance_url :: "+instance_url);
					createAccount(access_token, instance_url);
			}
			
			
			
		} catch (Exception e) {
			System.out.println("ERROR :: main :: "+e);
		}

	}

	private static void createAccount(String access_token,String instance_url){
		try {
			JSONObject account = new JSONObject();
			account.put("Name", "Java REST TEST v40");
			account.put("AccountNumber", "654321");
			
			HttpClient client = new HttpClient();
			PostMethod post = new PostMethod(instance_url+"/services/data/v40.0/sobjects/Account");
			
			post.setRequestHeader("Authorization","OAuth "+access_token);
			post.setRequestEntity(new StringRequestEntity(account.toString(),"application/json",null));
			client.executeMethod(post);
			JSONObject resp = new JSONObject(new JSONTokener(new InputStreamReader(post.getResponseBodyAsStream())));
			System.out.println(resp.toString());
		} catch (Exception e) {
			System.out.println("ERROR :: createAccount :: "+e);
		}
	}
}
