package com.example.expensemanagement;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.expensemanagement.db.MyExpense;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    List<MyExpense> myExpenseList;

    public MyAdapter(List<MyExpense> myExpenseList) {
        this.myExpenseList = myExpenseList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.my_expense_list,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final MyExpense myExpense = myExpenseList.get(position);
        holder.txt_expense_name.setText(myExpense.getName());
        holder.txt_expense_price.setText("Rs: " + String.valueOf(myExpense.getPrice()));
        holder.txt_expense_date.setText(myExpense.getDate());
        holder.expense_card.setOnClickListener(view -> {
            Intent intent = new Intent(holder.expense_card.getContext(), ExpenseDetailsActivity.class);
            intent.putExtra("name", myExpense.getName());
            intent.putExtra("price", myExpense.getPrice());
            intent.putExtra("date", myExpense.getDate());
            intent.putExtra("description", myExpense.getDescription());
            holder.expense_card.getContext().startActivity(intent);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        });
    }

    @Override
    public int getItemCount() {
        return myExpenseList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txt_expense_name, txt_expense_price, txt_expense_date;
        CardView expense_card;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_expense_name = itemView.findViewById(R.id.txt_expense_name);
            txt_expense_price = itemView.findViewById(R.id.txt_expense_price);
            txt_expense_date = itemView.findViewById(R.id.txt_expense_date);
            expense_card = itemView.findViewById(R.id.expense_card);
        }
    }
}
