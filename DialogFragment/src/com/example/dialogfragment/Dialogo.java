package com.example.dialogfragment;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.widget.TextView;

public class Dialogo  extends DialogFragment{
  
	
	Comunicacion comunicar;
	
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		comunicar = (Comunicacion) activity;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
	    final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
	    // Get the layout inflater
	    LayoutInflater inflater = getActivity().getLayoutInflater();

	    // Inflate and set the layout for the dialog
	    // Pass null as the parent view because its going in the dialog layout
	    builder.setView(inflater.inflate(R.layout.dialog_signin, null))
	    // Add action buttons
	           .setPositiveButton(R.string.sign_in, new DialogInterface.OnClickListener() {
	               @Override
	               public void onClick(DialogInterface dialog, int id) {
	                   // sign in the user ...
	            	   final AlertDialog alertDialog = (AlertDialog) dialog;
	            	   TextView text = (TextView)alertDialog.findViewById(R.id.username);
	            	   TextView text2 = (TextView)alertDialog.findViewById(R.id.password);
	            	   comunicar.Comunicacion(text.getText().toString(),text2.getText().toString());
	               }
	           })
	           .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
	               public void onClick(DialogInterface dialog, int id) {
	            	   Dialogo.this.getDialog().cancel();
	               }
	           });      
	    return builder.create();
	}
}
