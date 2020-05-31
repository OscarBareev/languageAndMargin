package com.example.language;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.io.UTFDataFormatException;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    int chooseLanguagePos;
    int chooseMarginPos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.onActivityCreateSetTheme(this);
        setContentView(R.layout.activity_main);

        init();
    }


    private void init() {

        final Spinner languageSpin = findViewById(R.id.languageSpin);
        final Spinner marginSpin = findViewById(R.id.marginSpin);


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


        ArrayAdapter<?> marginAdapter =
                ArrayAdapter.createFromResource(this, R.array.margin, android.R.layout.simple_spinner_item);
        marginAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        marginSpin.setAdapter(marginAdapter);

        marginSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent,
                                       View itemSelected, int selectedItemPosition, long selectedId) {

                chooseMarginPos = selectedItemPosition;
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

                switch (chooseMarginPos){
                    case 0:
                        Utils.changeToTheme(MainActivity.this, Utils.THEME_LITTLE);
                        break;
                    case 1:
                        Utils.changeToTheme(MainActivity.this, Utils.THEME_MIDDLE);
                        break;
                    case 2:
                        Utils.changeToTheme(MainActivity.this, Utils.THEME_BIG);
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
