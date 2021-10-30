package com.example.expensemanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ExpenseDetailsActivity extends AppCompatActivity {
    TextView detail_name, detail_price, detail_date, detail_description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_details);

        detail_name = findViewById(R.id.detail_name);
        detail_price = findViewById(R.id.detail_price);
        detail_date = findViewById(R.id.detail_date);
        detail_description = findViewById(R.id.detail_description);

        String name = getIntent().getStringExtra("name");
        int price = getIntent().getIntExtra("price", 0);
        String date = getIntent().getStringExtra("date");
        String description = getIntent().getStringExtra("description");

        detail_name.setText(name);
        detail_price.setText("Rs: " + String.valueOf(price));
        detail_date.setText(date);
        detail_description.setText(description);
    }
}