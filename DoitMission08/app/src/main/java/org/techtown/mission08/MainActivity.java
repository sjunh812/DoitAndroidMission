package org.techtown.mission08;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText id;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        id = (EditText)findViewById(R.id.idEditText);
        password = (EditText)findViewById(R.id.pwEditText);

        Button button = (Button)findViewById(R.id.loginButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(id.getText().toString().equals("") || password.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "아이디, 비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show();
                }
                else{
                    loginToMenuActivity();
                }
            }
        });
    }

    private void loginToMenuActivity() {
        Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
        //intent.putExtra("id", id.getText());

        startActivityForResult(intent, 101);
    }
}