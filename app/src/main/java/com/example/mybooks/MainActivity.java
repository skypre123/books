package com.example.mybooks;

import androidx.appcompat.app.AppCompatActivity;

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
    private final String saveFilename = "memo.txt";
    public List<BookModel> array;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.button1.setOnClickListener(v -> save());
        binding.button2.setOnClickListener(v -> load());

        try {
            String booksJson = readFromAssets("books.json");
            Gson gson = new Gson();
            array = gson.fromJson(booksJson, new TypeToken<List<BookModel>>() {}.getType());


            String text = "제목: " + bookItem.getString("title") + ", 가격: " + bookItem.getInt("price") + "\n" + "책 이미지 URL: " + bookItem.getString("image");
            binding.text.setText(text);

            Glide.with(this)
                    .load(item.getImage())
                    .into(binding.imageBook);

            String description = bookItem.getString("description");
            binding.textDescription.setText(Html.fromHtml(description));
        } catch (IOException e) {
            Log.i("DEBUG", "파일읽기실패");
        } catch (JSONException e) {
            Log.i("DEBUG", "JSON파일에러");
        }

    }

    public void save() {
        String contents = binding.editText.getText().toString();
        FileUtils.writeFile(this, saveFilename, contents);
    }

    public void load() {
        try {
            String loadedContents = FileUtils.readFile(this, saveFilename);
            binding.editText.setText(loadedContents);
        } catch (FileNotFoundException e) {
        }
    }

    public String readFromAssets(String name) throws IOException {
        InputStream InputStream = getAssets().open(name);
        return FileUtils.readStream(InputStream);
    }



}






