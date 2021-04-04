package org.techtown.doitmission21;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

public class AppHelper {
    private static final String LOG = "AppHelper";

    public static final String DB_NAME = "book.db";
    public static final String BOOK_TABLE = "book";

    private static final String BOOK_ID = "_id";
    private static final String BOOK_TITLE = "title";
    private static final String BOOK_AUTHOR = "author";
    private static final String BOOK_CONTENT = "content";

    Context context;

    private static final String createBookTableSQL = "CREATE TABLE IF NOT EXISTS " + BOOK_TABLE + " ("
            + BOOK_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + BOOK_TITLE + " TEXT, "
            + BOOK_AUTHOR + " TEXT, "
            + BOOK_CONTENT + " TEXT);";
    private static final String insertBookSQL = "INSERT INTO " + BOOK_TABLE + " ("
            + BOOK_TITLE + ", " + BOOK_AUTHOR + ", " + BOOK_CONTENT + ") "
            + "VALUES (?, ?, ?);";

    private SQLiteDatabase db;

    public AppHelper(Context context) {
        this.context = context;
        db = context.openOrCreateDatabase(AppHelper.DB_NAME, context.MODE_PRIVATE, null);
    }

    public void createTable(String tableName) {
        if(db != null) {
            if(tableName.equals(BOOK_TABLE)) {
                db.execSQL(createBookTableSQL);
                Log.d(LOG, "성공적으로 책 테이블을 생성했습니다!");
            }
        } else {
            Log.d(LOG, "데이터베이스를 먼저 오픈해주십시오..");
        }
    }

    public void insert(String tableName, Object[] objs) {
        if(db != null) {
            if(tableName.equals(BOOK_TABLE)) {
                db.execSQL(insertBookSQL, objs);
                Log.d(LOG, "성공적으로 레이블을 추가했습니다!");

                Toast.makeText(context, "DB에 성공적으로 저장되었습니다!", Toast.LENGTH_SHORT).show();
            }
        } else {
            Log.d(LOG, "데이터베이스를 먼저 오픈해주십시오..");
        }
    }
}
