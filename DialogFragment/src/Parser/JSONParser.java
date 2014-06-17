package Parser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import android.util.Log;

public class JSONParser {

static JSONArray jarray=null;
	
	public JSONParser(){
	}
	
	public static HttpUriRequest createPostForJSONObject(
	        JSONObject params, String url) {
		
	    HttpPost post = new HttpPost(url);
	    post.setEntity(createStringEntity(params));
	    return post;
	}

	private static HttpEntity createStringEntity(JSONObject params) {
	    StringEntity se = null;
	    try {
	        se = new StringEntity(params.toString(), "UTF-8");
	        se.setContentType("application/json; charset=UTF-8");
	    } catch (UnsupportedEncodingException e) {
	        Log.e("TAG", "Failed to create StringEntity");
	     
	    }
	    return se;
	}
	
	public void postData(String url,JSONObject json) {
	    HttpClient httpclient = new DefaultHttpClient();
	    try {
	          HttpPost post = (HttpPost) createPostForJSONObject(json, url);
	          HttpResponse response = httpclient.execute(post);
	          Log.i("postData1", response.getStatusLine().toString());
	          /* 
	          BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
	          String line = "";
	          while ((line = rd.readLine()) != null) {
	            Log.d("Resp",line);
	          }*/
	          
	    } catch (Exception e) {
	         // HANDLE EXCEPTION
	    }
	}
}
