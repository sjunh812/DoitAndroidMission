package org.techtown.mission09;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CustomerFragment extends Fragment {
    // Activity Interface
    private FragmentCallBack activity;

    private EditText nameInput;
    private EditText ageInput;
    private EditText birthInput;

    private Button birthButton;

    public static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy년 MM월 dd일");

    private DateLayout dateLayout;
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if(context instanceof FragmentCallBack) {
            activity = (FragmentCallBack)context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();

        activity = null;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.customer_fragment, container, false);

        nameInput = rootView.findViewById(R.id.editTextName);
        ageInput = rootView.findViewById(R.id.editTextAge);
        birthInput = rootView.findViewById(R.id.editTextBirth);

        // 현재 날짜 EdittText에 표시
        setSelectedDate(new Date());

        birthButton = rootView.findViewById(R.id.birthButton);
        birthButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });

        Button okButton = (Button)rootView.findViewById(R.id.okButton);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText((MainActivity)activity, "이름 : " + nameInput.getText() + "\n"
                + "나이 : " + ageInput.getText() + "\n"
                + "생년월일 : " + birthInput.getText(), Toast.LENGTH_LONG).show();
            }
        });
        return rootView;
    }

    private void showDialog() {
        String birth = birthInput.getText().toString();
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();

        try {
            date = dateFormat.parse(birth);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        calendar.setTime(date);
        dateLayout = new DateLayout(getContext(), calendar);

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("날짜 입력");
        builder.setMessage("생년월일을 입력해주세요");
        builder.setView(dateLayout);

        builder.setPositiveButton("아니오", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.setNegativeButton("예", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                int year = dateLayout.getYear();
                int month = dateLayout.getMonth();
                int day = dateLayout.getDay();

                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, day);

                setSelectedDate(calendar.getTime());
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showDateDialog() {
        String birth = birthInput.getText().toString();
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();

        try {
            date = dateFormat.parse(birth);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        calendar.setTime(date);
        int curYear = calendar.get(Calendar.YEAR);
        int curMonth = calendar.get(Calendar.MONTH);
        int curDay = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dateDialog = new DatePickerDialog(getContext(), onDateSetListener, curYear, curMonth, curDay);
        dateDialog.show();
    }

    private DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            Calendar selectedCalendar = Calendar.getInstance();
            selectedCalendar.set(year, month, dayOfMonth);

            Date selectedDate = selectedCalendar.getTime();
            setSelectedDate(selectedDate);
        }
    };

    private void setSelectedDate(Date date) {
        String birth = dateFormat.format(date);

        birthInput.setText(birth);
    }
}
