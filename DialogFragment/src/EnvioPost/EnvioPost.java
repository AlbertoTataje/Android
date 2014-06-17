package EnvioPost;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public class EnvioPost extends AsyncTask<String, Void, String>{

	Context context;
	String usuario,password;
	
	public EnvioPost(Context context, String usuario,String password){
	    this.password=password;
	    this.context=context;
	    this.usuario=usuario;
	}
	@Override
	protected String doInBackground(String... arg0) {
		try {
			HttpClient httpclient  = new DefaultHttpClient();
			
			
			//C:\wamp\www\Login\principal.php
		   HttpPost httpost =new HttpPost("http://10.0.2.2/Login/principal.php");
		
		List <NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("usuario",usuario));
		nvps.add(new BasicNameValuePair("password",password));
		
		//Z2C26C5224FE4
		try {
			
			httpost.setEntity(new UrlEncodedFormEntity(nvps,HTTP.UTF_8));
			
			HttpResponse response =  httpclient.execute(httpost);
			Log.i("postData", response.getStatusLine().toString());
		} catch (Exception e) {
			 Log.e("TAG1", "Error:  "+e.toString()+e.getMessage());
		}
	   
		//progressDialog.dismiss(); 
	
		//Directory "c:/wamp/www/"
		//# Deny from all
		
		//8464946767311854701430227
		
		
		
		
		} catch (Exception e) {
			// TODO: handle exception
			 Log.e("TAG", "Error:  "+e.toString());
		}
		
		return null;
	}

	
	
}
