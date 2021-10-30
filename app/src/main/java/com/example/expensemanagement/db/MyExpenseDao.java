package com.example.expensemanagement.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MyExpenseDao {

    @Query("select * from my_expense")
    List<MyExpense> getAllExpense();

    @Insert
    void insertExpense(MyExpense myExpense);

    @Query("select sum(price) from my_expense")
    int getAllPrice();
}
