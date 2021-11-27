package com.example.mybooks;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.text.Html;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.example.mybooks.databinding.ActivityMainBinding;
import com.example.mybooks.models.BookModel;
import com.example.mybooks.utils.FileUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    public List<BookModel> array;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String booksJson = null;
        try {
            booksJson = readFromAssets("books.json");
        } catch (IOException e) {
            e.printStackTrace();
        }

        Gson gson = new Gson();
        array = gson.fromJson(booksJson, new TypeToken<List<BookModel>>() {}.getType());
        BookAdapter bookAdapter = new BookAdapter(array);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        binding.recyclerView.setAdapter(bookAdapter);

    }

    public String readFromAssets(String name) throws IOException {
        InputStream InputStream = getAssets().open(name);
        return FileUtils.readStream(InputStream);
    }



}






