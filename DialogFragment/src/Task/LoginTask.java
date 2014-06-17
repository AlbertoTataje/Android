/**
 * 
 */
/**
 * @author Alberto
 *
 */
package Task;

import org.json.JSONException;
import org.json.JSONObject;

import Parser.JSONParser;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public class LoginTask extends AsyncTask<String, Void, String>{

	 Context context;
	 private final static  String url="http://10.0.2.2/Login/principal.php" ;
	 private String usuario;
	 private String password;
	 
	public LoginTask(Context context,String usuario,String password){
        		
		this.context=context;
		this.usuario=usuario;
		this.password=password;
	}
	
	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
	}	
	
	@Override
	protected String doInBackground(String... arg0) {
		JSONParser jParser = new JSONParser();

		
		JSONObject objct = new JSONObject();
		try {
			objct.put("user",usuario);
			objct.put("password", password);
			
			Log.d("post", objct.toString());

			jParser.postData(url, objct);

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "Exito";

	}
	@Override
	protected void onPostExecute(String result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
	}

	
}