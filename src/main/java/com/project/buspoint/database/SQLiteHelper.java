package com.project.buspoint.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.io.IOException;
import java.io.FileOutputStream;
import android.database.sqlite.SQLiteException;
import java.io.InputStream;
import java.io.OutputStream;
import android.database.SQLException;

public class SQLiteHelper extends SQLiteOpenHelper{

	//The Android's default system path of your application database.

    private static String ABS_DB_PATH;
 
    private static String DB_NAME = "rutas";
    private static int DB_VERSION = 1;
    private SQLiteDatabase myDataBase;
 
    private final Context myContext;

    public SQLiteHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
        this.myContext = context;
        ABS_DB_PATH = this.myContext.getDatabasePath(DB_NAME).getAbsolutePath();
    }

    public void createDatabase() throws IOException{
        boolean dbExist = checkDataBase();

        if(dbExist){
            //No hacer nada porque ya existe
        }else{
             //By calling this method and empty database will be created into the default system path
            //of your application so we are gonna be able to overwrite that database with our database.
            this.getReadableDatabase();
 
            try{
             
                copyDataBase();
             
            }catch (IOException e){
             
                throw new Error("Error copying database");
            }
        }
    }

//METHOD 1
     /**
  * Check if the database already exist to avoid re-copying the file each time you open the application.
  * @return true if it exists, false if it doesn't
  */
    private boolean checkDataBase(){
     
        SQLiteDatabase checkDB = null;
         
        try{
            
            //checkDB = SQLiteDatabase.openDatabase(ABS_DB_PATH, null, SQLiteDatabase.OPEN_READONLY);
            checkDB = SQLiteDatabase.openDatabase(ABS_DB_PATH, null, SQLiteDatabase.NO_LOCALIZED_COLLATORS | SQLiteDatabase.CREATE_IF_NECESSARY);
         
         
         }catch(SQLiteException e){
         
            //database does't exist yet.
         }
         
        if(checkDB != null)
            checkDB.close();
         
        return checkDB != null ? true : false;
    }
 //METHOD 2
     /**
      * Copies your database from your local assets-folder to the just created empty database in the
      * system folder, from where it can be accessed and handled.
      * This is done by transfering bytestream.
      * */
    private void copyDataBase() throws IOException{
     
        //Open your local db as the input stream
        InputStream myInput = myContext.getAssets().open(DB_NAME);
         
       
        //Open the empty db as the output stream
        OutputStream myOutput = new FileOutputStream(ABS_DB_PATH);
         
        //transfer bytes from the inputfile to the outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer))>0){
        myOutput.write(buffer, 0, length);
        }
         
        //Close the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();
         
    }

//METHOD 3
    public void openDataBase() throws SQLException{
 
       
    //myDataBase = SQLiteDatabase.openDatabase(ABS_DB_PATH, null, SQLiteDatabase.OPEN_READONLY);
     myDataBase = SQLiteDatabase.openDatabase(ABS_DB_PATH, null, SQLiteDatabase.NO_LOCALIZED_COLLATORS | SQLiteDatabase.CREATE_IF_NECESSARY);
         
    }
 
//METHOD 4
    @Override   
    public synchronized void close() {
 
        if(myDataBase != null)
            myDataBase.close();
 
        super.close();
    }

//METHOD 5
    @Override
    public void onCreate(SQLiteDatabase db) {
 
    }
//METHOD 6
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
 
    }

 
}