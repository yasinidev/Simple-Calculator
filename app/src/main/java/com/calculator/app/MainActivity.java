package com.calculator.app;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.calculator.tutorial.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;


public class MainActivity<val> extends AppCompatActivity {
    boolean isNewOperator = true;
    EditText edt1;
    String operator = "+";
    String initialNumber = "";

    private AdView mAdView;

    @SuppressLint("MissingSuperCall")
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_main);

        edt1 = findViewById(R.id.editText);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }

            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
            }

            public void onAdFailedToLoad(LoadAdError adError) {
                // Code to be executed when an ad request fails.
            }

            public void onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
            }

            public void onAdClicked() {
                // Code to be executed when the user clicks on an ad.
            }
            public void onAdClosed() {
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
            }

        });

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


    }


    public void numberEvent(View view) {

        if(isNewOperator)
            edt1.setText("");
        isNewOperator = false;

        String number = edt1.getText().toString();

        if(view.getId() == R.id.btnOne){
            number += "1";
        }
        else if(view.getId() == R.id.btnTwo){
            number += "2";
        }
        else if(view.getId() == R.id.btnThree){
            number += "3";
        }
        else if(view.getId() == R.id.btnFour){
            number += "4";
        }
        else if(view.getId() == R.id.btnFive){
            number += "5";
        }
        else if(view.getId() == R.id.btnSix){
            number += "6";
        }
        else if(view.getId() == R.id.btnSeven){
            number += "7";
        }
        else if(view.getId() == R.id.btnEight){
            number += "8";
        }
        else if(view.getId() == R.id.btnNine){
            number += "9";
        }
        else if(view.getId() == R.id.btnZero){
            number += "0";
        }
        else if(view.getId() == R.id.btnZeroo){
            number += "00";
        }

        else if(view.getId() == R.id.btnDot){
            number += ".";
        }

        edt1.setText(number);
    
    }

    public void operatorEvent(View view) {

        isNewOperator =true;
        initialNumber = edt1.getText().toString();

        if(view.getId() == R.id.btnMultiply){
            operator = "*";}

        else if(view.getId() == R.id.btnMinus){
            operator = "-";}

        else if(view.getId() == R.id.btnDivide){
            operator = "/";}

        else if(view.getId() == R.id.btnPlus){
            operator = "+";
        }


    }

    public void equalEvent(View view) {
        String newNumber = edt1.getText().toString();
        double output = 0;

        if(operator == "+")
            output = Double.parseDouble(initialNumber) + Double.parseDouble(newNumber);

        if(operator == "/")
            output = Double.parseDouble(initialNumber) / Double.parseDouble(newNumber);

        if(operator == "*")
            output = Double.parseDouble(initialNumber) * Double.parseDouble(newNumber);

        if(operator == "-")
            output = Double.parseDouble(initialNumber) - Double.parseDouble(newNumber);

        edt1.setText(output+"");

    }


    public void clearEvent(View view) {
        edt1.setText("");
        isNewOperator = true;
    }

    public void deleteEvent(View view) {
        String BackSpace= null;
        if(edt1.getText().length()>0){
            StringBuilder stringBuilder= new StringBuilder(edt1.getText());
            stringBuilder.deleteCharAt(edt1.getText().length()-1);
            BackSpace=stringBuilder.toString();
            edt1.setText(BackSpace);
    }}



    public void percentEvent(View view) {
        double num = Double.parseDouble(edt1.getText().toString())/100;
        edt1.setText(num+"");
        isNewOperator = true;
        
    }
}