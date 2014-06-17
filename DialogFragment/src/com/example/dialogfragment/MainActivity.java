package com.example.dialogfragment;
import EnvioPost.EnvioPost;
import Task.LoginTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends FragmentActivity implements OnClickListener,Comunicacion{
   
	 Button boton;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);
		boton = (Button) findViewById(R.id.button1);
		boton.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View arg0) {
		 FragmentManager manager = getSupportFragmentManager();
		 Dialogo d = new Dialogo();
		 d.show(manager, "hol");
		
	}

	@Override
	public void Comunicacion(String usuario,String password) {
	      	Log.d("Hola", usuario+"  "+ password);
	        //Envia un JSon
	       //new LoginTask(MainActivity.this,usuario,password).execute();  	
	        
	      	//Envia mediante Post
	       new EnvioPost(MainActivity.this, usuario, password).execute();
	  
	      		
	}

    

	
}
