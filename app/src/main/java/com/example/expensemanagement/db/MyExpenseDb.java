package com.example.expensemanagement.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {MyExpense.class}, version = 1)
public abstract class MyExpenseDb extends RoomDatabase {
    public abstract MyExpenseDao myExpenseDao();
}
