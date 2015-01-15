package com.example.buspoint;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class ObtenerRuta extends Activity {

	private TextView tv2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.obtenerruta);
		
		tv2 = (TextView)findViewById(R.id.tv2);
		
		  Bundle reicieveParams = getIntent().getExtras();
	      tv2.setText(reicieveParams.getString("param1"));	
	}
	
}
