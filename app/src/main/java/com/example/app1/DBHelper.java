package com.example.app1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "App3Usuarios.db";
    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE " + FeedReaderContract.FeedUsers.TABLE_NAME +
            " (" + FeedReaderContract.FeedUsers._ID + " INTEGER PRIMARY KEY, " +
            FeedReaderContract.FeedUsers.COLUMN_NAME_NOMBRE + " TEXT," +
            FeedReaderContract.FeedUsers.COLUMN_NAME_NUMERO_CUENTA + " TEXT," +
            FeedReaderContract.FeedUsers.COLUMN_NAME_GENERO + " TEXT," +
            FeedReaderContract.FeedUsers.COLUMN_NAME_EMAIL + " TEXT UNIQUE," +
            FeedReaderContract.FeedUsers.COLUMN_NAME_PASSWORD + " TEXT)";


    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + FeedReaderContract.FeedUsers.TABLE_NAME;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public boolean insertUser(Alumno u) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(FeedReaderContract.FeedUsers.COLUMN_NAME_NOMBRE, u.getNombre());
        contentValues.put(FeedReaderContract.FeedUsers.COLUMN_NAME_NUMERO_CUENTA, u.getNumeroCuenta());
        contentValues.put(FeedReaderContract.FeedUsers.COLUMN_NAME_GENERO, u.getGenero());
        contentValues.put(FeedReaderContract.FeedUsers.COLUMN_NAME_EMAIL , u.getEmail());
        contentValues.put(FeedReaderContract.FeedUsers.COLUMN_NAME_PASSWORD, u.getPassword());
        long id = db.insert(FeedReaderContract.FeedUsers.TABLE_NAME, null, contentValues);
        if (id == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor getUsuario(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * from " + FeedReaderContract.FeedUsers.TABLE_NAME +
                " WHERE ID=" + id, null);
        return res;
    }

    public Integer deleteUsuario(Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(FeedReaderContract.FeedUsers.TABLE_NAME, " id = ?",
                new String[] {Integer.toString(id)});
    }

    public Integer eliminarUsuario(String correo) {
        long id = getID(correo);
        return deleteUsuario((int) id);
    }

    public String getPassword(String email) {
        SQLiteDatabase db = this.getReadableDatabase();

        String projeccion[] = {
                BaseColumns._ID,
                FeedReaderContract.FeedUsers.COLUMN_NAME_PASSWORD
        };
        String seleccion = FeedReaderContract.FeedUsers.COLUMN_NAME_EMAIL + " = ?";
        String[] seleccionArgs = {email};

        Cursor res = db.query(FeedReaderContract.FeedUsers.TABLE_NAME,
                projeccion,
                seleccion,
                seleccionArgs,
                null,
                null,
                null);
        if (res.getCount() <= 0) {
            res.close();
            return null;
        } else {
            if (res.moveToFirst()) {
                String password = res.getString(res.getColumnIndex(FeedReaderContract.FeedUsers.COLUMN_NAME_PASSWORD));
                res.close();
                return password;
            } else {
                return null;
            }
        }
    }

    public long getID(String email) {
        SQLiteDatabase db = this.getReadableDatabase();

        String projeccion[] = {
                BaseColumns._ID,
                FeedReaderContract.FeedUsers.COLUMN_NAME_PASSWORD
        };
        String seleccion = FeedReaderContract.FeedUsers.COLUMN_NAME_EMAIL + " = ?";
        String[] seleccionArgs = {email};

        Cursor res = db.query(FeedReaderContract.FeedUsers.TABLE_NAME,
                projeccion,
                seleccion,
                seleccionArgs,
                null,
                null,
                null);
        if (res.getCount() <= 0) {
            res.close();
            return -1;
        } else {
            if (res.moveToFirst()) {
                long id = res.getLong(res.getColumnIndex(FeedReaderContract.FeedUsers._ID));
                res.close();
                return id;
            } else {
                return -1;
            }
        }
    }

    public ArrayList<Alumno> getAllUsuarios() {
        ArrayList<Alumno> alumnos = new ArrayList<Alumno>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT  * FROM " + FeedReaderContract.FeedUsers.TABLE_NAME, null);
        if (res.getCount() <= 0) {
            res.close();
            return null;
        } else {
            if (res.moveToFirst()) {
                do {
                    long id = res.getLong(res.getColumnIndexOrThrow(FeedReaderContract.FeedUsers._ID));
                    String nombre = res.getString(res.getColumnIndex(FeedReaderContract.FeedUsers.COLUMN_NAME_NOMBRE));
                    String numeroCuenta = res.getString(res.getColumnIndex(FeedReaderContract.FeedUsers.COLUMN_NAME_NUMERO_CUENTA));
                    String genero = res.getString(res.getColumnIndex(FeedReaderContract.FeedUsers.COLUMN_NAME_GENERO));
                    String email = res.getString(res.getColumnIndex(FeedReaderContract.FeedUsers.COLUMN_NAME_EMAIL));
                    alumnos.add(new Alumno(id, nombre, numeroCuenta, email, genero));
                } while (res.moveToNext());
            }
            res.close();
            return alumnos;
        }
    }
}
