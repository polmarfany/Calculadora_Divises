package com.example.calculadora_divises;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    DecimalFormat formatter = new DecimalFormat("#0.00");

    private TextView textViewConverInput, textViewConverOutput;
    private Button butNum0, butNum1, butNum2, butNum3, butNum4, butNum5, butNum6, butNum7,butNum8, butNum9;
    private Button butCalcComa, butCalcEquals, butCalcClearEntry, butCalcDel;
    private Button butDivLliures, butDivDollar, butDivYen, butDivYuan;

    private double multiplicador = 0;
    private boolean inputSenseComa = true;
    private int maxDecimals = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Calculadora Divises");

        //textView
        textViewConverInput = (TextView) findViewById(R.id.textViewConverInput);
        textViewConverOutput = (TextView) findViewById(R.id.textViewConverOutput);

        //numeros
        butNum0 = (Button) findViewById(R.id.butNum0);
        butNum1 = (Button) findViewById(R.id.butNum1);
        butNum2 = (Button) findViewById(R.id.butNum2);
        butNum3 = (Button) findViewById(R.id.butNum3);
        butNum4 = (Button) findViewById(R.id.butNum4);
        butNum5 = (Button) findViewById(R.id.butNum5);
        butNum6 = (Button) findViewById(R.id.butNum6);
        butNum7 = (Button) findViewById(R.id.butNum7);
        butNum8 = (Button) findViewById(R.id.butNum8);
        butNum9 = (Button) findViewById(R.id.butNum9);

        //calc
        butCalcComa = (Button) findViewById(R.id.butCalcComa);
        butCalcEquals = (Button) findViewById(R.id.butCalcEquals);
        butCalcClearEntry = (Button) findViewById(R.id.butCalcClearEntry);
        butCalcDel = (Button) findViewById(R.id.butCalcDel);

        //divises
        butDivLliures = (Button) findViewById(R.id.butDivLliure);
        butDivDollar = (Button) findViewById(R.id.butDivDollar);
        butDivYen = (Button) findViewById(R.id.butDivYen);
        butDivYuan = (Button) findViewById(R.id.butDivYuan);

        //butons assignats al listener
        butNum0.setOnClickListener(this);
        butNum1.setOnClickListener(this);
        butNum2.setOnClickListener(this);
        butNum3.setOnClickListener(this);
        butNum4.setOnClickListener(this);
        butNum5.setOnClickListener(this);
        butNum6.setOnClickListener(this);
        butNum7.setOnClickListener(this);
        butNum8.setOnClickListener(this);
        butNum9.setOnClickListener(this);

        butCalcDel.setOnClickListener(this);
        butCalcClearEntry.setOnClickListener(this);
        butCalcComa.setOnClickListener(this);
        butCalcEquals.setOnClickListener(this);

        butDivLliures.setOnClickListener(this);
        butDivDollar.setOnClickListener(this);
        butDivYen.setOnClickListener(this);
        butDivYuan.setOnClickListener(this);

        butDivYuan.setBackgroundColor(Color.LTGRAY);
        butDivDollar.setBackgroundColor(Color.LTGRAY);
        butDivYen.setBackgroundColor(Color.LTGRAY);
        butDivLliures.setBackgroundColor(Color.LTGRAY);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.butNum0: addToNumber("0"); break;
            case R.id.butNum1: addToNumber("1"); break;
            case R.id.butNum2: addToNumber("2"); break;
            case R.id.butNum3: addToNumber("3"); break;
            case R.id.butNum4: addToNumber("4"); break;
            case R.id.butNum5: addToNumber("5"); break;
            case R.id.butNum6: addToNumber("6"); break;
            case R.id.butNum7: addToNumber("7"); break;
            case R.id.butNum8: addToNumber("8"); break;
            case R.id.butNum9: addToNumber("9"); break;

            case R.id.butCalcComa: addComaToNumber(); break;
            case R.id.butCalcDel: deleteNumber(); break;
            case R.id.butCalcClearEntry: clearEntry(); break;
            case R.id.butCalcEquals: calcul(); break;

            case R.id.butDivLliure: multiplicador = 0.86; canviColorEstandar(butDivLliures); break;
            case R.id.butDivDollar: multiplicador = 1.10; canviColorEstandar(butDivDollar); break;
            case R.id.butDivYen: multiplicador = 119.61; canviColorEstandar(butDivYen); break;
            case R.id.butDivYuan: multiplicador = 7.73; canviColorEstandar(butDivYuan); break;
        }
    }

    public String returnTextInput(){
        String text = textViewConverInput.getText().toString();
        return text;
    }

    public boolean checkInput() { //comproba que el textView no sigui null, retornant true
        boolean boo = false;
        if ( !returnTextInput().equals("") ) {
            boo = true;
        }
        return boo;
    }

    public boolean checkInputAfterComa() {
        boolean boo = true;
        int indexOfComa = returnTextInput().indexOf('.');

        if (indexOfComa != -1) {
            String afterComa = returnTextInput().substring(indexOfComa);

            if (afterComa.length() == maxDecimals + 1) {
                boo = false;
            }
        }
        return boo;
    }

    public void addToNumber(String numberAdded) {
        if (inputSenseComa ) {

            if (checkInput() ) {
                textViewConverInput.setText(returnTextInput() + numberAdded);
            }
            else {
                textViewConverInput.setText(numberAdded);
            }
        }

        else if (checkInputAfterComa() ) {
            textViewConverInput.setText(returnTextInput() + numberAdded);
        }
    }

    public void addComaToNumber() {

        if (inputSenseComa) {

            if (checkInput() ) {
                textViewConverInput.setText(returnTextInput() + ".");
            }
            else {
                textViewConverInput.setText("0.");
            }
            inputSenseComa = false;
        }

    }

    public void deleteNumber() {

        if ( checkInput() ) {
            int indexCharBorrar = returnTextInput().length() - 1;
            char ultimChar = returnTextInput().charAt(indexCharBorrar);

            textViewConverInput.setText( (returnTextInput().substring(0, indexCharBorrar) ) );

            if (ultimChar == '.') {
                inputSenseComa = true;
            }
        }

    }
    public void clearEntry() {
        textViewConverInput.setText("");
        textViewConverOutput.setText("");
        inputSenseComa = true;
    }

    public void calcul() {

        double factorEuros = 0;

        if ( multiplicador != 0 && checkInput() ) {
            factorEuros = Double.parseDouble( returnTextInput() );
        }

        textViewConverOutput.setText(formatter.format(factorEuros * multiplicador));
    }

    public void canviColorEstandar(Button but) {
       butDivYuan.setBackgroundColor(Color.LTGRAY);
       butDivYuan.setTextColor(Color.BLACK);
       butDivDollar.setBackgroundColor(Color.LTGRAY);
       butDivDollar.setTextColor(Color.BLACK);
       butDivYen.setBackgroundColor(Color.LTGRAY);
       butDivYen.setTextColor(Color.BLACK);
       butDivLliures.setBackgroundColor(Color.LTGRAY);
       butDivLliures.setTextColor(Color.BLACK);

       but.setBackgroundColor(Color.BLACK);
       but.setTextColor(Color.WHITE);
    }
}