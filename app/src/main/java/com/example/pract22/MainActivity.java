package com.example.pract22;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    SharedPreferences themeSettings;
    SharedPreferences.Editor settingsEditor;
    ImageButton imageTheme;

    private int[][] board = new int[3][3];
    private int currentPlayer = 1; // 1 - крестик, 2 - нолик
    private boolean gameOver = false;
    SharedPreferences sharedPreferences;

    private Button[] btns;
    private TextView table1;
    private Button btnbot, reset;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Получаем SharedPreferences
        themeSettings = getSharedPreferences("SETTINGS", MODE_PRIVATE);
        // Проверяем, есть ли уже сохраненные настройки
        if (!themeSettings.contains("MODE_NIGHT_ON")) {
            settingsEditor = themeSettings.edit();
            settingsEditor.putBoolean("MODE_NIGHT_ON", false);
            settingsEditor.apply();
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        } else {
            setCurrentTheme();
        }

        setContentView(R.layout.activity_main);

        // Находим кнопка для изменения темы
        imageTheme = findViewById(R.id.imgbtn);
        updateImageButton();

        // Устанавливаем слушатель клика
        imageTheme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Проверяем текущее состояние и переключаем тему
                if (themeSettings.getBoolean("MODE_NIGHT_ON", false)) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    settingsEditor = themeSettings.edit();
                    settingsEditor.putBoolean("MODE_NIGHT_ON", false);
                    settingsEditor.apply();

                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    settingsEditor = themeSettings.edit();
                    settingsEditor.putBoolean("MODE_NIGHT_ON", true);
                    settingsEditor.apply();
                }
                // Обновляем изображение кнопки
                updateImageButton();
            }
        });

        //кусок говна ебаный я ебал всю семью андроида
        //а если без рофлов
        //инициализация кнопок и массива, крч полный фарш


        //кнопка перехода на другую активити
        btnbot = findViewById(R.id.bot);
        btnbot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Bot.class);
                startActivity(intent);
            }
        });

        //массив
        btns = new Button[]{
                findViewById(R.id.game1),
                findViewById(R.id.game2),
                findViewById(R.id.game3),
                findViewById(R.id.game4),
                findViewById(R.id.game5),
                findViewById(R.id.game6),
                findViewById(R.id.game7),
                findViewById(R.id.game8),
                findViewById(R.id.game9),
        };
        //инициализация текста сверху
        table1 = findViewById(R.id.table);



    }

    // Метод для обновления изображения в зависимости от темы
    private void updateImageButton() {
        if (themeSettings.getBoolean("MODE_NIGHT_ON", false)) {
            imageTheme.setImageResource(R.drawable.sun); // здесь укажим иконку для светлой темы
        } else {
            imageTheme.setImageResource(R.drawable.moon); // здесь укажим иконку для темной темы
        }
    }

    private void setCurrentTheme() {
        if (themeSettings.getBoolean("MODE_NIGHT_ON", false)) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }



}