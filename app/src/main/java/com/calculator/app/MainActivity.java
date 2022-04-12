package com.calculator.app;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.calculator.tutorial.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;


public class MainActivity extends AppCompatActivity {
    boolean isNewOperator = true;
    EditText edt1;
    String operator = "+";
    String initialNumber = "";
    private TextView screen;
    private String display="";
    private EditText inputtext;
    private TextView displaytext;
    private String currentOperator="";
    private String result="";

    private AdView mAdView;

    @SuppressLint("MissingSuperCall")
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_main);

        edt1 = findViewById(R.id.input_box);
        screen = (TextView)findViewById(R.id.input_box);
        screen.setText(display);
        inputtext = findViewById(R.id.input_box);
        displaytext = findViewById(R.id.result_box);

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


    private void appendToLast(String str) {
        this.inputtext.getText().append(str);
    }

    public void onClickNumber(View v) {
        Button b = (Button) v;
        display += b.getText();
        appendToLast(display);
        display="";
    }

    public void onClickOperator(View v) {
        Button b = (Button) v;
        display += b.getText();
        if(endsWithOperatore())
        {
            replace(display);
            currentOperator = b.getText().toString();
            display = "";
        }
        else {
            appendToLast(display);
            currentOperator = b.getText().toString();

            display = "";
        }

    }

    private String getinput() {
        return this.inputtext.getText().toString();
    }

    private boolean endsWithOperatore() {
        return getinput().endsWith("+") || getinput().endsWith("-") || getinput().endsWith("/") || getinput().endsWith("x");
    }

    private void replace(String str) {
        inputtext.getText().replace(getinput().length() - 1, getinput().length(), str);
    }

    private double operate(String a,String b,String cp)
    {
        switch(cp) {
            case "+": return Double.valueOf(a) + Double.valueOf(b);
            case "-": return Double.valueOf(a) - Double.valueOf(b);
            case "x": return Double.valueOf(a) * Double.valueOf(b);
            case "\u00F7": return Double.valueOf(a) / Double.valueOf(b);
            default: return -1;
        }
    }

    public void equalresult(View v) {
        String input = getinput();

        if(!endsWithOperatore()) {

            if (input.contains("x")) {
                input = input.replaceAll("x", "*");
            }

            if (input.contains("\u00F7")) {
                input = input.replaceAll("\u00F7", "/");
            }
            Expression expression = new ExpressionBuilder(input).build();
            double result = expression.evaluate();

            displaytext.setText(String.valueOf(result));
        }
        else displaytext.setText("");

        System.out.println(result);
    }

    public void clearEvent(View view) {
        edt1.setText("");
        displaytext.setText("");
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
