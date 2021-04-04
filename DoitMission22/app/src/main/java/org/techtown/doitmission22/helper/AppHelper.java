package org.techtown.doitmission22.helper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import org.techtown.doitmission22.item.BookItem;

import java.util.ArrayList;

public class AppHelper {
    private static final String LOG = "AppHelper";
    public static final String DB_NAME = "book.db";
    public static final String BOOK_TABLE = "book";
    public static final String BOOK_ID = "_id";
    public static final String BOOK_TITLE = "title";
    public static final String BOOK_AUTHOR = "author";
    public static final String BOOK_CONTENT = "content";

    private static final String createBookSQL = "CREATE TABLE IF NOT EXISTS " + BOOK_TABLE + " ("
            + BOOK_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + BOOK_TITLE + " TEXT, "
            + BOOK_AUTHOR + " TEXT, "
            + BOOK_CONTENT + " TEXT);";

    private static final String insertBookSQL = "INSERT INTO " + BOOK_TABLE + " ("
            + BOOK_TITLE + ", " + BOOK_AUTHOR + ", " + BOOK_CONTENT + ") "
            + "VALUES (?,?,?);";

    private static final String selectBook = "SELECT " + BOOK_TITLE + ", " + BOOK_AUTHOR + ", " + BOOK_CONTENT
            + " FROM " + BOOK_TABLE + ";";

    private Context context;
    private DatabaseHelper helper;
    private SQLiteDatabase db;

    public AppHelper(Context context) {
        this.context = context;
    }

    public void initDB() {
        helper = new DatabaseHelper(context, DB_NAME, null, 1);
        db = helper.getWritableDatabase();
    }

    public void create(String tableName) {
        if(db != null) {
            if(tableName.equals(AppHelper.BOOK_TABLE)) {
                db.execSQL(createBookSQL);
            }
        } else {
            Log.d(LOG, "데이터베이스를 먼저 오픈해주세요.");
        }
    }

    public void insert(String tableName, Object[] objects) {
        if(db != null) {
            if(tableName.equals(AppHelper.BOOK_TABLE)) {
                db.execSQL(insertBookSQL, objects);
            }

            Toast.makeText(context, "DB에 성공적으로 저장되었습니다!", Toast.LENGTH_SHORT).show();
        } else {
            Log.d(LOG, "데이터베이스를 먼저 오픈해주세요.");
        }
    }

    public ArrayList<BookItem> select() {
        ArrayList<BookItem> items = new ArrayList<>();

        if(db != null) {
            Cursor cursor = db.rawQuery(selectBook, null);

            while(cursor.moveToNext()) {
                String title = cursor.getString(0);
                String author = cursor.getString(1);
                String content = cursor.getString(2);

                BookItem item = new BookItem(title, author, content);
                items.add(item);
            }
        } else {
            Log.d(LOG, "데이터베이스를 먼저 오픈해주세요.");
        }

        return items;
    }

    class DatabaseHelper extends SQLiteOpenHelper {

        public DatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}
