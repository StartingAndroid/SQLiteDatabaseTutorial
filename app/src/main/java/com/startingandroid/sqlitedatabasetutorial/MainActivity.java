package com.startingandroid.sqlitedatabasetutorial;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private UsersAdapter adapter;
    private DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.usersList);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        dbHandler = new DBHandler(MainActivity.this);
        /**
         * CRUD Operations
         * */
        // Inserting Contacts
        Log.e("Insert: ", "Inserting...");
        dbHandler.addUser(new User("Starting Android", "contact@startingandroid.com"));
        dbHandler.addUser(new User("Zeeshan Ahmed", "imzeeshan4u@gmail.com"));
        dbHandler.addUser(new User("Luanna Factora", "luanna@startingandroid.com"));
        dbHandler.addUser(new User("Aliza Ben", "aliza@hotmail.com"));
        dbHandler.addUser(new User("Martin", "marting@startingandroid.com"));
        dbHandler.addUser(new User("Bruce", "bruce@android.com"));
        dbHandler.addUser(new User("Micheal", "michel@gmail.com"));
        dbHandler.addUser(new User("Angelina", "angelina@outlook.com"));
        dbHandler.addUser(new User("Sarah", "sarah@ary.tv"));
        // Reading all contacts
        Log.d("Reading: ", "reading contacts..");
        ArrayList<User> users = dbHandler.getAllUsers();
        adapter = new UsersAdapter(users);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
