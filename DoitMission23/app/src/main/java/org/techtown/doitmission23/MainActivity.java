package org.techtown.doitmission23;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {
    private EditText thickText;
    private PaintBoard paintBoard;

    private int color = Color.BLACK;
    private float thick = 3f;
    private Paint.Cap cap = Paint.Cap.ROUND;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button red = (Button)findViewById(R.id.redButton);
        red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                color = Color.RED;
                paintBoard.setPaint(color, thick, cap);
            }
        });

        Button blue = (Button)findViewById(R.id.blueButton);
        blue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                color = Color.BLUE;
                paintBoard.setPaint(color, thick, cap);
            }
        });

        Button black = (Button)findViewById(R.id.blackButton);
        black.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                color = Color.BLACK;
                paintBoard.setPaint(color, thick, cap);
            }
        });

        thickText = (EditText)findViewById(R.id.thickEdit);
        Button thickButton = (Button)findViewById(R.id.thickButton);
        thickButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    thick = Float.valueOf(thickText.getText().toString());
                } catch(Exception e) {
                    e.printStackTrace();
                }

                paintBoard.setPaint(color, thick, cap);
            }
        });

        RadioGroup group = (RadioGroup)findViewById(R.id.radioGroup);
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId) {
                    case R.id.radioButton:
                        cap = Paint.Cap.BUTT;
                        break;
                    case R.id.radioButton2:
                        cap = Paint.Cap.ROUND;
                        break;
                    case R.id.radioButton3:
                        cap = Paint.Cap.SQUARE;
                        break;
                }

                paintBoard.setPaint(color, thick, cap);
            }
        });

        Button resetButton = (Button)findViewById(R.id.resetButton);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paintBoard.reset();
            }
        });

        paintBoard = new PaintBoard(this);
        FrameLayout container = (FrameLayout)findViewById(R.id.container);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT
        );

        container.addView(paintBoard, params);
    }
}