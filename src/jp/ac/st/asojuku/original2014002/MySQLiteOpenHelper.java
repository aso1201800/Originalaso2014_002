package jp.ac.st.asojuku.original2014002;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteOpenHelper extends SQLiteOpenHelper {

	public MySQLiteOpenHelper(Context context) {
		super(context, "20140021201800.sqlite3", null, 1);
		// TODO 自動生成されたコンストラクター・スタブ

	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO 自動生成されたメソッド・スタブ
		db.execSQL("CREATE TABLE IF NOT EXISTS " +
				"Hitokoto (_id INTEFER PRIMARY KEY AUTOINCREMENT NOT NULL , phrase TEXT)");

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("drop table Hitokoto ");
		onCreate(db);
		// TODO 自動生成されたメソッド・スタブ

	}

	public void insertHitokoto(SQLiteDatabase db, String inputMsg){

		String sqlstr = " insert into Hitokoto (phease) values('" + inputMsg + " ');";
			try {
				db.beginTransaction();
				db.execSQL(sqlstr);
				db.setTransactionSuccessful();
			} catch (SQLException e) {
				Log.e("ERROR",e.toString());
			}finally {
				db.endTransaction();
			}
			return;
	}

	public String selectRandomHitokoto(SQLiteDatabase db){

		String rtString = null;

		String sqlstr = "SELECT _id, phrase FROM Hitokoto ORDER BY RANDOM(); ";
			try {
				SQLiteCursor cursor = (SQLiteCursor)db.rawQuery(sqlstr,  null);
				if(cursor.getCount()!=0){
					cursor.moveToFirst();
					rtString = cursor.getString(1);
				}
				cursor.close();

			} catch (SQLException e) {
				Log.e("ERROR", e.toString());
			}finally {
			}
			return rtString;
				}
	}




