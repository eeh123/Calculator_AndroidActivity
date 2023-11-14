package com.example.bootcamppractice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.text.TextUtils;
import android.widget.Switch;
import android.widget.Toast;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class CalcActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String inputPattern = "^[-]?\\d+(\\.)?(\\d+)?[+\\-*/]?(\\d+)?(\\.)?(\\d+)?$";
//    private static final String inputPattern = "^[-]?\\d+(\\.\\d+)?$";
    private enum Symbol {
        plus,
        minus,
        multiply,
        divide,
        none
    }
    private Symbol symbol = Symbol.none;
    private enum Checker {
        empty,
        noVal1,
        val1NoVal2,
        val1NoVal2Equals,
        val1Val2
    }
    private Checker checker = Checker.empty;
    private Button clear, backspace, divide, multiply, subtract, add, equals, blank1, blank2, point;
    private Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0;
//    private ImageButton backspace;
    private EditText display, input;
    private int iresult, ival1, ival2;
    private Double result, val1, val2;
    private boolean isWhole = false;
    private boolean equalsFlag = false;
    private boolean hasValue1 = false;
    private boolean hasValue2 = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc);

        clear = findViewById(R.id.btnClear);
        divide = findViewById(R.id.btnDivide);
        multiply = findViewById(R.id.btnMultiply);
        subtract = findViewById(R.id.btnSubtract);
        add = findViewById(R.id.btnAdd);
        equals = findViewById(R.id.btnEquals);
        blank1 = findViewById(R.id.btnBlank1);
        blank2 = findViewById(R.id.btnBlank2);
        point = findViewById(R.id.btnPoint);
        btn0 = findViewById(R.id.btn0);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);
        backspace = findViewById(R.id.btnBackspace);
        display = findViewById(R.id.tv_display);
        input = findViewById(R.id.tv_input);

        display.setEnabled(false);

        clear.setOnClickListener(this);
        backspace.setOnClickListener(this);
        divide.setOnClickListener(this);
        multiply.setOnClickListener(this);
        subtract.setOnClickListener(this);
        add.setOnClickListener(this);
        equals.setOnClickListener(this);
        blank1.setOnClickListener(this);
        blank2.setOnClickListener(this);
        point.setOnClickListener(this);

        btn0.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);

    }
    public void clearIfNaN() {
        if (display.getText().toString().equals("Not a number/Infinity")) {
            input.getText().clear();
            display.getText().clear();
            symbol = Symbol.none;
            checker = Checker.empty;
            val1 = 0.0;
        }
    }
    public void advanceCheckerStatus() {
        if (checker == Checker.val1NoVal2) {
            checker = Checker.val1Val2;
        } else if (checker == Checker.noVal1) {
            checker = Checker.val1NoVal2;
        }
    }
    public void appendIfNotSucceeding(String inputString, String appendingChar) {
        if (!(inputString.endsWith(appendingChar))) {
            input.append(appendingChar);
        }
    }
    public void checker(Symbol symVal, String symbol) {
        Log.e("Inside checker()", "checker() function");
        Log.e(String.valueOf(checker), "curr checker val");
        advanceCheckerStatus();
        switch (checker) {
            case empty:
                break;
            case noVal1:
                val1 = Double.parseDouble(input.getText().toString());
                checker = Checker.val1NoVal2;
                appendIfNotSucceeding(input.getText().toString(),symbol);
//                input.append(symbol);
                display.setText(String.valueOf(val1));
                break;
            case val1NoVal2Equals:
                appendIfNotSucceeding(input.getText().toString(),symbol);
//                input.append(symbol);
                checker = Checker.val1NoVal2;
                break;
            case val1NoVal2:
                input.setText(String.valueOf(display.getText()));
                val1 = Double.parseDouble(input.getText().toString());
                appendIfNotSucceeding(input.getText().toString(),symbol);
//                input.append(symbol);
                checker = Checker.val1NoVal2;
                break;
            case val1Val2:
                val1 = Double.parseDouble(display.getText().toString());
                checker = Checker.val1NoVal2;
                input.setText(String.valueOf(val1));
                appendIfNotSucceeding(input.getText().toString(),symbol);
//                input.append(symbol);
        }
        this.symbol = symVal;
    }
    public void checkerForSubtract() {
        int length = input.getText().length();
        Log.e(String.valueOf(length), "length of input");
        if (checker == Checker.val1NoVal2) {
            checker = Checker.val1Val2;
        }
        switch (checker) {
            case empty:
                if (input.getText().toString().equals("")) {
                    input.append("-");
                }
                break;
            case noVal1:
                if (input.getText().toString().equals("")) {
                    input.append("-");
                }
                else {
                    val1 = Double.parseDouble(input.getText().toString());
                    checker = Checker.val1NoVal2;
                    appendIfNotSucceeding(input.getText().toString(),"-");
//                    input.append("-");
                    display.setText(String.valueOf(val1));
                    this.symbol = Symbol.minus;
                }
                break;
            case val1NoVal2Equals:
                input.append("-");
                checker = Checker.val1NoVal2;
                this.symbol = Symbol.minus;
                break;
            case val1NoVal2:
                if (input.getText().toString().contains("+")) {
                    appendIfNotSucceeding(input.getText().toString(),"-");
//                    input.append("-");
                }
                else if (input.getText().toString().contains("×")) {
                    appendIfNotSucceeding(input.getText().toString(),"-");
//                    input.append("-");
                }
                else if (input.getText().toString().contains("÷")) {
                    appendIfNotSucceeding(input.getText().toString(),"-");
//                    input.append("-");
                }
                else {
                    input.setText(String.valueOf(display.getText()));
                    val1 = Double.parseDouble(input.getText().toString());
                    appendIfNotSucceeding(input.getText().toString(),"-");
//                    input.append("−");
                    this.symbol = Symbol.minus;
                }
                break;
            case val1Val2:
                val1 = Double.parseDouble(display.getText().toString());
                checker = Checker.val1NoVal2;
                input.setText(String.valueOf(val1));
                appendIfNotSucceeding(input.getText().toString(),"-");
//                input.append("−");
                this.symbol = Symbol.minus;
                break;
        }
    }
    public boolean checkV1Whole() {
        if (Integer.parseInt(input.getText().toString()) == (int)Integer.parseInt(input.getText().toString())){
            //Integer.parseInt(input.getText().toString()) % 1 == 0
            isWhole = true;
            return true;
        }
        else {
            isWhole = false;
            return false;
        }
    }
    public boolean checkV2Whole(String symbol) {
        if (input.getText().toString().contains("+")) {
            if (Integer.parseInt(TextUtils.substring(input.getText().toString(),
                    input.getText().toString().indexOf("+") + 1,
                    input.getText().toString().length())) == (int)Integer.parseInt(TextUtils.substring(input.getText().toString(),
                    input.getText().toString().indexOf("+") + 1,
                    input.getText().toString().length()))){
                //Integer.parseInt(input.getText().toString()) % 1 == 0
                isWhole = true;
                return true;
            }
            else {
                isWhole = false;
                return false;
            }
        }
        return false;
    }
    public void ifVal1IsWholeThenSet(boolean isTrue) {
        if (isTrue) {
            ival1 = Integer.parseInt(input.getText().toString());
            hasValue1 = true;
            display.setText(String.valueOf(ival1));
        }
        else {
            val1 = Double.parseDouble(input.getText().toString());
            hasValue1 = true;
            display.setText(String.valueOf(val1));
        }
    }
    public void ifVal2IsWholeThenSet(boolean isTrue) {
        if (isTrue) {
            ival2 = Integer.parseInt(input.getText().toString());
            hasValue2 = true;
        }
        else {
            val2 = Double.parseDouble(input.getText().toString());
            hasValue2 = true;
        }
    }
    public String checkIfWhole(double result) {
        Log.e("result: ", String.valueOf(result));
//        if (Double.isNaN(result)){
        if (String.valueOf(result).equals("Infinity") || String.valueOf(result).equals("-Infinity")){
            return "Not a number/Infinity";
        }
        else if (result % 1 == 0) {
            if (BigDecimal.valueOf(result).scale() < -3) {
                BigInteger bIntResult = BigDecimal.valueOf(result).toBigInteger();

                DecimalFormat decimalFormat = new DecimalFormat("0.#############E0"); // Adjust the format as needed
                String formattedResult = decimalFormat.format(bIntResult);

                return formattedResult;
//                return String.valueOf(BigDecimal.valueOf(result).toBigInteger());
            }
            return String.valueOf(BigDecimal.valueOf(result).toBigInteger()); //returns a whole number
        }
        else {
            if (BigDecimal.valueOf(result).scale() > 5) { //rounds result if its decimal place exceeds 5 decimal places
                double roundResult = Math.round(result * 100000.0) / 100000.0; //rounds to the 5th decimal place
//                return String.valueOf(BigDecimal.valueOf(roundResult));
                return String.valueOf(BigDecimal.valueOf(result).setScale(5, RoundingMode.HALF_UP));
            }
            return String.valueOf(BigDecimal.valueOf(result));
        }
    }
    public void setVal2() {
        Log.e("setVal Function", "setVal2()");
        Log.e(String.valueOf(checker), "checker state");
        Log.e(String.valueOf(symbol), "curr symbol");

        switch (checker) {
            case empty:
            case noVal1:
                display.setText(input.getText());
                checker = Checker.noVal1;
                break;

            case val1NoVal2:
                switch (symbol) {
                    case plus:
                        val2 = Double.parseDouble(TextUtils.substring(input.getText().toString(),
                                input.getText().toString().indexOf("+") + 1,
                                input.getText().toString().length()));
                        result = equation();
                        display.setText(checkIfWhole(result));
                        Log.e("setVal Function +", "setVal2() addition");
                        Log.e(String.valueOf(val2), "setVal2() val2");
                        break;

                    case minus:
                        Log.e("Inside case -", "case -");
                        Log.e("val1 value", val1.toString());
                        if (String.valueOf(val1).contains("-")) {
                            Log.e("If block", "if");
                            val2 = Double.parseDouble(TextUtils.substring(input.getText().toString(),
                                    input.getText().toString().indexOf("-",1) + 1,
                                    input.getText().toString().length()));
                            result = equation();
                            display.setText(checkIfWhole(result));
//                            display.setText(String.valueOf(result));
                            Log.e(String.valueOf(result), "setVal2() val2");
                        }
                        else {
                            Log.e("Else block", "else");
                            val2 = Double.parseDouble(TextUtils.substring(input.getText().toString(),
                                    input.getText().toString().indexOf("-") + 1,
                                    input.getText().toString().length()));
                            result = equation();
                            display.setText(checkIfWhole(result));
//                            display.setText(String.valueOf(result));
                            Log.e("setVal Function −", "setVal2() subtraction else()");
                            Log.e(String.valueOf(val2), "setVal2() val2");
                        }
                        break;

                    case multiply:
                        val2 = Double.parseDouble(TextUtils.substring(input.getText().toString(),
                                input.getText().toString().indexOf("×") + 1,
                                input.getText().toString().length()));
                        result = equation();
                        display.setText(checkIfWhole(result));
//                        display.setText(String.valueOf(result));
                        Log.e("setVal Function ×", "setVal2() multiplication");
                        Log.e(String.valueOf(val2), "setVal2() val2");
                        break;

                    case divide:
                        val2 = Double.parseDouble(TextUtils.substring(input.getText().toString(),
                                input.getText().toString().indexOf("÷") + 1,
                                input.getText().toString().length()));
                        result = equation();
                        display.setText(checkIfWhole(result));
//                        display.setText(String.valueOf(result));
                        Log.e("setVal Function ÷", "setVal2() division");
                        Log.e(String.valueOf(val2), "setVal2() val2");
                        break;
                }
//                checker = Checker.val1Val2;
                break;
        }
    }
    public double equation() {
        Log.e("Equation Function", "log for equation()");
        Log.e(String.valueOf(symbol), "symbol for equation()");
        Log.e(String.valueOf(val1), "val1 value");
        Log.e(String.valueOf(val2), "val2 value");
        if (symbol == null || symbol == Symbol.none) {
            return 0;
        }
        else {
            if (symbol == Symbol.plus) {
                return val1 + val2;
            }
            else if (symbol == Symbol.minus) {
                return val1 - val2;
            }
            else if (symbol == Symbol.multiply) {
                return val1 * val2;
            }
            else if (symbol == Symbol.divide) {
                return val1 / val2;
            }
            return 0;
        }
    }
    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btnClear:
                clearIfNaN();
                Log.e("Clicked clear", "clear");
                input.getText().clear();
                display.getText().clear();
                symbol = Symbol.none;
                checker = Checker.empty;
                val1 = 0.0;
                break;

            case R.id.btnBackspace:
                clearIfNaN();
                Log.e("Clicked backspace", "backspace");
                //check if an operator is deleted then set symbol.none
                String word = input.getText().toString();
                int length = word.length();
                if (length > 0){
                    input.setText(word.substring(0, length - 1));
                }
                break;

            case R.id.btnDivide:
                clearIfNaN();
                Log.e("Clicked ÷", "÷");
                checker(Symbol.divide,"÷");
//                checker = Checker.val1NoVal2;
                break;

            case R.id.btnMultiply:
                clearIfNaN();
                Log.e("Clicked ×", "×");
                checker(Symbol.multiply,"×");
//                checker = Checker.val1NoVal2;
                break;

            case R.id.btnSubtract:
                clearIfNaN();
                Log.e("Clicked -", "-");
                checkerForSubtract();
                break;

            case R.id.btnAdd:
                clearIfNaN();
                Log.e("Clicked +", "+");
                checker(Symbol.plus,"+");
//                checker = Checker.val1NoVal2;
                break;

            case R.id.btnEquals:
                clearIfNaN();
                input.setText(display.getText());
                display.getText().clear();
                val1 = Double.parseDouble(input.getText().toString());
                symbol = Symbol.none;
                checker = Checker.val1NoVal2Equals;
                Log.e(String.valueOf(val1), "val1 after equals");
                Log.e(String.valueOf(val2), "val2 after equals");
                Log.e(String.valueOf(input.getText()), "input value after equals");
                Log.e(String.valueOf(display.getText()), "display value after equals");
                break;

            case R.id.btnPoint:
//                input.append(".");
//                checker = Checker.val1NoVal2;
//                revertCheckerStatus();
                appendIfNotSucceeding(input.getText().toString(), ".");
                break;

            case R.id.btnBlank1:
                Toast toast=Toast.makeText(getApplicationContext(),":P",Toast.LENGTH_SHORT);
                toast.show();
                break;

            case R.id.btnBlank2:
                Toast toast2=Toast.makeText(getApplicationContext(),"P:",Toast.LENGTH_SHORT);
                toast2.show();
                break;

            case R.id.btn0:
                input.append("0");
//                checkInput("0");
                setVal2();
                break;

            case R.id.btn1:
                input.append("1");
//                checkInput("1");
                setVal2();
                break;

            case R.id.btn2:
                input.append("2");
//                checkInput("2");
                setVal2();
                break;

            case R.id.btn3:
                input.append("3");
//                checkInput("3");
                setVal2();
                break;

            case R.id.btn4:
                input.append("4");
//                checkInput("4");
                setVal2();
                break;

            case R.id.btn5:
                input.append("5");
//                checkInput("5");
                setVal2();
                break;

            case R.id.btn6:
                input.append("6");
//                checkInput("6");
                setVal2();
                break;

            case R.id.btn7:
                input.append("7");
//                checkInput("7");
                setVal2();
                break;

            case R.id.btn8:
                input.append("8");
//                checkInput("8");
                setVal2();
                break;

            case R.id.btn9:
                input.append("9");
//                checkInput("9");
                setVal2();
                break;
        }

        input.setSelection(input.getText().length());
        Log.e("Onlickstats checker(): ", checker.toString());
        Log.e("Onlickstats symbol(): ", symbol.toString());
        if (val1 != null){
            Log.e("val1 value: ", val1.toString());
        }

    }
}