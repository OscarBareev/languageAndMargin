package com.example.language;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    int chooseLanguagePos;
    int chooseColorPos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.onActivityCreateSetTheme(this);
        setContentView(R.layout.activity_main);

        init();
    }


    private void init() {

        final Spinner languageSpin = findViewById(R.id.languageSpin);
        final Spinner colorSpin = findViewById(R.id.colorSpin);


        ArrayAdapter<?> langAdapter =
                ArrayAdapter.createFromResource(this, R.array.languages, android.R.layout.simple_spinner_item);
        langAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        languageSpin.setAdapter(langAdapter);

        languageSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent,
                                       View itemSelected, int selectedItemPosition, long selectedId) {

                chooseLanguagePos = selectedItemPosition;
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        ArrayAdapter<?> colorAdapter =
                ArrayAdapter.createFromResource(this, R.array.colors, android.R.layout.simple_spinner_item);
        langAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        colorSpin.setAdapter(colorAdapter);

        colorSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent,
                                       View itemSelected, int selectedItemPosition, long selectedId) {

                chooseColorPos = selectedItemPosition;
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });




        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                switch (chooseLanguagePos) {
                    case 1:
                        switchLocale("ru");
                        break;
                    default:
                        switchLocale("en");

                }

                switch (chooseColorPos){
                    case 0:
                        Utils.changeToTheme(MainActivity.this, Utils.THEME_GREEN);
                        break;
                    case 1:
                        Utils.changeToTheme(MainActivity.this, Utils.THEME_BLUE);
                        break;
                    case 2:
                        Utils.changeToTheme(MainActivity.this, Utils.THEME_BLACK);
                        break;

                }





            }
        });
    }





    private void switchLocale(String language) {
        Locale locale = new Locale(language);
        Configuration config = new Configuration();
        config.setLocale(locale);
        getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());

    }
}
