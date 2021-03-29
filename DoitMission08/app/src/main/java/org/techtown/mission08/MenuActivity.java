package org.techtown.mission08;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button customerBtn = (Button)findViewById(R.id.customerButton);
        customerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passToOtherActivities(CustomerActivity.class, 201);
            }
        });

        Button salesBtn = (Button)findViewById(R.id.salesButton);
        salesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passToOtherActivities(SalesActivity.class, 202);
            }
        });

        Button goodsBtn = (Button)findViewById(R.id.goodsButton);
        goodsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passToOtherActivities(GoodsActivity.class, 203);
            }
        });
    }

    private void passToOtherActivities(Class<?> cls, int request) {
        Intent intent = new Intent(getApplicationContext(), cls);

        startActivityForResult(intent, request);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if(intent != null) {
            if(requestCode == 201) {    // from 고객 관리
                String message = intent.getStringExtra("customerMessage");
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
            }
            else if(requestCode == 202) {   // from 매출 관리
                String message = intent.getStringExtra("salesMessage");
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
            }
            else if(requestCode == 203) {   // from 상품 관리
                String mesage = intent.getStringExtra("goodsMessage");
                Toast.makeText(getApplicationContext(), mesage, Toast.LENGTH_SHORT).show();
            }
        }
    }
}