package org.techtown.doitmission21;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    AppHelper helper;

    EditText title;
    EditText author;
    EditText content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // DB 초기화
        helper = new AppHelper(this);
        helper.createTable(AppHelper.BOOK_TABLE);

        title = (EditText)findViewById(R.id.titleEditText);
        author = (EditText)findViewById(R.id.authorEditText);
        content = (EditText)findViewById(R.id.contentEditText);

        Button saveButton = (Button)findViewById(R.id.button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(title.getText().toString().equals("") || author.getText().toString().equals("") || content.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "정보를 모두 입력해주세요!", Toast.LENGTH_SHORT).show();
                } else {
                    Object[] objs = {title.getText().toString(), author.getText().toString(), content.getText().toString()};
                    insertBookInfo(objs);
                }
            }
        });
    }

    public void insertBookInfo(Object[] objs) {
        helper.insert(AppHelper.BOOK_TABLE, objs);
        clearText();
    }

    public void clearText() {
        title.setText("");
        author.setText("");
        content.setText("");

    }
}