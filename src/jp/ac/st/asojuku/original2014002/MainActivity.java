package jp.ac.st.asojuku.original2014002;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import com.example.originalaso_2014_002.R;

public class MainActivity extends Activity implements View.OnClickListener {

	SQLiteDatabase sdb = null;
	MySQLiteOpenHelper helper = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	protected void onResume() {
		// TODO 自動生成されたメソッド・スタブ
		super.onResume();

		//登録ボタン変数にリスナー登録する
		Button btnENTRY = (Button)findViewById(R.id.button1);
		btnENTRY.setOnClickListener(this);

		//メンテボタン変数にリスナー登録する
		Button btnMAINTE = (Button)findViewById(R.id.button2);
		btnMAINTE.setOnClickListener(this);

		//一言チェックボタン変数にリスナーを登録する
		Button btnCHECK = (Button)findViewById(R.id.button3);
		btnCHECK.setOnClickListener(this);

		if(sdb == null) {
			helper = new MySQLiteOpenHelper(getApplicationContext());
		}
		try{
			sdb = helper.getWritableDatabase();
		} catch(SQLiteException e){
			return;
		}
	}




	@Override
	public void onClick(View v) {
		// TODO 自動生成されたメソッド・スタブ

	//生成して代入用のInntentインスタンス変数を用意
	Intent intent = null;
	switch(v.getId()) {
		case R.id.button1:

			EditText etv =(EditText)findViewById(R.id.button3);
			String inputMsg = etv.getText().toString();

			if(inputMsg!=null && inputMsg.isEmpty()){
				helper.insertHitokoto(sdb,inputMsg);
			}

			etv.setText("");
			break;
	case R.id.button2:

		intent =new Intent(MainActivity.this,MaintenanceActivity.class);
		startActivity(intent);
		break;

	case R.id.button3:

		String strHitokoto = helper.selectRandomHitokoto(sdb);
		intent = new Intent(MainActivity.this, HitokotoActivity.class);
		intent.putExtra("hitokoto",strHitokoto);

		startActivity(intent);
	break;

	}





