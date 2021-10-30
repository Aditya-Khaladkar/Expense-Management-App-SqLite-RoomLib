package com.example.expensemanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import com.example.expensemanagement.db.MyExpense;
import com.example.expensemanagement.db.MyExpenseDao;
import com.example.expensemanagement.db.MyExpenseDb;

public class AddExpenseActivity extends AppCompatActivity {
    EditText ed_expenseName, ed_expensePrice, ed_expenseDescription;
    CalendarView calendarView;
    String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);

        final MyExpenseDb myExpenseDb = initDb();
        final MyExpenseDao myExpenseDao = myExpenseDb.myExpenseDao();

        ed_expenseName = findViewById(R.id.ed_expenseName);
        ed_expensePrice = findViewById(R.id.ed_expensePrice);
        ed_expenseDescription = findViewById(R.id.ed_expenseDescription);
        calendarView = findViewById(R.id.calendarView);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {
                date = day + "/" + month + "/"+ year;
            }
        });

        findViewById(R.id.btn_addExpense).setOnClickListener(view -> {
            String expenseName = ed_expenseName.getText().toString();
            int expensePrice = Integer.parseInt(ed_expensePrice.getText().toString());
            String expenseDescription = ed_expenseDescription.getText().toString();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    final MyExpense myExpense = new MyExpense();
                    myExpense.setName(expenseName);
                    myExpense.setPrice(expensePrice);
                    myExpense.setDescription(expenseDescription);
                    myExpense.setDate(date);
                    myExpenseDao.insertExpense(myExpense);
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                }
            }).start();

        });
    }

    public MyExpenseDb initDb() {
        return Room
                .databaseBuilder(getApplicationContext(), MyExpenseDb.class, "expenseDb")
                .build();
    }
}