package com.example.expensemanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.expensemanagement.db.MyExpense;
import com.example.expensemanagement.db.MyExpenseDao;
import com.example.expensemanagement.db.MyExpenseDb;

import org.w3c.dom.Text;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    MyAdapter adapter;
    TextView txt_getTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_getTotal = findViewById(R.id.txt_getTotal);

        final MyExpenseDb myExpenseDb = initDb();
        final MyExpenseDao myExpenseDao = myExpenseDb.myExpenseDao();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        new Thread(new Runnable() {
            @Override
            public void run() {
                final List<MyExpense> myExpenseList = myExpenseDao.getAllExpense();
                adapter = new MyAdapter(myExpenseList);
                recyclerView.setAdapter(adapter);

            }
        }).start();

        findViewById(R.id.btn_add).setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), AddExpenseActivity.class));
            finish();
        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                final int myExpense = myExpenseDao.getAllPrice();
                txt_getTotal.setText("Total Expense: " + String.valueOf(myExpense));
            }
        }).start();
    }

    public MyExpenseDb initDb() {
        return Room
                .databaseBuilder(getApplicationContext(), MyExpenseDb.class, "expenseDb")
                .build();
    }
}