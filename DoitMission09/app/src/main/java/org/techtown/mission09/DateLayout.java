package org.techtown.mission09;

import android.app.DatePickerDialog;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.DatePicker;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

public class DateLayout extends LinearLayout {
    public DatePicker datePicker;

    private Calendar calendar;

    private int year;
    private int month;
    private int day;

    public DateLayout(Context context, Calendar calendar) {
        super(context);

        this.calendar = calendar;

        init(context);
    }

    public DateLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.dialog, this, true);

        datePicker = (DatePicker)findViewById(R.id.datePicker);
        setDatePickerDialog();
    }

    private void setDatePickerDialog() {
        datePicker.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int years, int monthOfYears, int dayOfMonths) {
                year = years;
                month = monthOfYears;
                day = dayOfMonths;
            }
        });
    }

    public int getYear(){
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }
}
