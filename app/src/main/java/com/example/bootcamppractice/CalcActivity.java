package com.example.bootcamppractice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;
import android.text.TextUtils;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bootcamppractice.adapter.HistoryAdapter;
import com.example.bootcamppractice.objects.History;
import com.example.bootcamppractice.utils.TouchFrameLayout;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class CalcActivity extends AppCompatActivity implements View.OnClickListener {
    public enum Symbol {
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
        val1Val2
    }
    private Checker checker = Checker.empty;
    private MotionLayout motionLayout;
    private TouchFrameLayout container;
    private RecyclerView histContainer;
    private Button clear, backspace, divide, multiply, subtract, add, equals, blank1, blank2, point;
    private Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0;
    private EditText display, input;
    private Double result, val1, val2;
    private boolean val2Flag = false;
    private boolean equalsFlag = false;
    private String operator;
    private ArrayList<History> histories = new ArrayList<History>();
    private ArrayList<String> resultHistories = new ArrayList<String>();
    private HistoryAdapter historyAdapter = new HistoryAdapter(this, histories, resultHistories);
    private ViewTreeObserver vto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc);


        container = findViewById(R.id.container);
        histContainer = findViewById(R.id.histContainer);


//        container.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
//            @Override
//            public void onLayoutChange(
//                    View v, int left, int top, int right, int bottom,
//                    int oldLeft, int oldTop, int oldRight, int oldBottom) {
//
//                vto = container.getViewTreeObserver();
//                vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
//                    @Override
//                    public boolean onPreDraw() {
//                        // Remove the listener to avoid multiple callbacks
//                        container.getViewTreeObserver().removeOnPreDrawListener(this);
//
//                        // Get the height of the FrameLayout
//                        int frameLayoutHeight = container.getHeight();
//
//                        if (container.getHeight() < 750) {
//                            // Set the height of the child view as a percentage of the FrameLayout height
//                            int childViewHeight = (int) (frameLayoutHeight * 0.52); // Adjust the percentage as needed
//                            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) histContainer.getLayoutParams();
//                            layoutParams.height = childViewHeight;
//                            histContainer.setLayoutParams(layoutParams);
//                        } else if (container.getHeight() < 1000) {
//                            int childViewHeight = (int) (frameLayoutHeight * 0.58);
//                            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) histContainer.getLayoutParams();
//                            layoutParams.height = childViewHeight;
//                            histContainer.setLayoutParams(layoutParams);
//                        } else if (container.getHeight() < 1200) {
//                            int childViewHeight = (int) (frameLayoutHeight * 0.62);
//                            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) histContainer.getLayoutParams();
//                            layoutParams.height = childViewHeight;
//                            histContainer.setLayoutParams(layoutParams);
//                        } else if (container.getHeight() < 1500) {
//                            int childViewHeight = (int) (frameLayoutHeight * 0.7);
//                            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) histContainer.getLayoutParams();
//                            layoutParams.height = childViewHeight;
//                            histContainer.setLayoutParams(layoutParams);
//                        } else {
//                            int childViewHeight = (int) (frameLayoutHeight * 0.8);
//                            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) histContainer.getLayoutParams();
//                            layoutParams.height = childViewHeight;
//                            histContainer.setLayoutParams(layoutParams);
//                        }
//                        return true;
//                    }
//                });
//            }
//        });
        histContainer.setLayoutManager(new LinearLayoutManager(this));
        histContainer.setAdapter(historyAdapter);

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
        point.setOnClickListener(this);
        equals.setOnClickListener(this);

        blank1.setOnClickListener(this);
        blank2.setOnClickListener(this);

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

    private String symbolToOperator(Symbol s) {
        switch (s) {
            case plus:
                operator = "+";
                break;

            case minus:
                operator = "-";
                break;

            case multiply:
                operator = "×";
                break;

            case divide:
                operator = "÷";
                break;
        }
        return operator;
    }
    public void addToArrayHistory(double v1, double v2, Symbol s, double res) {
        histories.add(new History(v1,v2,symbolToOperator(s)));
        resultHistories.add(String.valueOf(res));
        historyAdapter.setData(histories, resultHistories);

        if (histories.size() > 1) {
            int lastIndex = histories.size() - 1;
            histContainer.scrollToPosition(lastIndex);
        }
    }
    public void handleNumInput(String stringNum) {
        String i = input.getText().toString();
        boolean condition = i.endsWith("+") || i.endsWith("-") || i.endsWith("×") || i.endsWith("÷");
        if (equalsFlag && !condition) {
            this.equalsFlag = false;
            this.checker = Checker.empty;
            input.getText().clear();
            input.append(stringNum);
        } else {
            input.append(stringNum);
        }
    }
    public void handleBackspace() {
        String i = input.getText().toString();
        int length = i.length();
        if (length > 0) {
            if (i.endsWith("+") || (i.endsWith("-") && symbol == Symbol.minus) || i.endsWith("×") || i.endsWith("÷")) {
                input.setText(i.substring(0, length - 1));
                symbol = Symbol.none;
                this.checker = Checker.noVal1;
                display.setText(input.getText().toString());
            }
            else if (i.endsWith("-") && symbol != Symbol.minus) {
                input.setText(i.substring(0, length - 1));
            }
            else { //when nums are deleted
                input.setText(i.substring(0, length - 1));
                String newI = input.getText().toString();
                boolean endsWithOperator = newI.endsWith("+") || newI.endsWith("-") || newI.endsWith("×") || newI.endsWith("÷");
                if (symbol != Symbol.none && !endsWithOperator) {
                    setVal2();
                }
                else if (symbol != Symbol.none && endsWithOperator) {
                    display.setText(checkIfWhole(val1));
                }
                else if (symbol == Symbol.none && newI.length() > 0) {
                    display.setText(input.getText().toString());
                }
            }
        }
    }
    public void clearIfNaN() {
        if (display.getText().toString().equals("Not a number/Infinity")) {
            input.getText().clear();
            display.getText().clear();
            symbol = Symbol.none;
            this.checker = Checker.empty;
            val1 = 0.0;
        }
    }
    public void advanceCheckerStatus() {
        if (checker == Checker.noVal1 || checker == Checker.empty) {
            this.checker = Checker.val1NoVal2;
        } else if (checker == Checker.val1NoVal2){
            this.checker = Checker.val1Val2;
        } else if (checker == Checker.val1Val2){
            this.checker = Checker.val1NoVal2;
        }
    }
    public void appendIfNotSucceeding(String inputString, String appendingChar) {
        if (!(inputString.endsWith(appendingChar))) {
            input.append(appendingChar);
        }
    }
    public void checker(Symbol symVal, String symbol) {
        if (val2Flag == true) {
            advanceCheckerStatus();
            val2Flag = false;
        }
        switch (checker) {
            case empty:
                break;
            case noVal1:
                val1 = Double.parseDouble(input.getText().toString());
                advanceCheckerStatus();
                appendIfNotSucceeding(input.getText().toString(),symbol);
                display.setText(String.valueOf(val1));
                break;
            case val1NoVal2:
                if (equalsFlag) {
                    appendIfNotSucceeding(input.getText().toString(),symbol);
                    this.equalsFlag = false;
                    break;
                }
                input.setText(String.valueOf(display.getText()));
                val1 = Double.parseDouble(display.getText().toString());
                appendIfNotSucceeding(input.getText().toString(),symbol);
                break;
            case val1Val2:
                input.setText(String.valueOf(display.getText()));
                val1 = Double.parseDouble(input.getText().toString());
                appendIfNotSucceeding(input.getText().toString(),symbol);
                advanceCheckerStatus();
                break;
        }
        this.symbol = symVal;
    }
    public void checkerForSubtract() {
        int length = input.getText().length();
        if (val2Flag == true) {
            advanceCheckerStatus();
            val2Flag = false;
        }
        switch (checker) {
            case empty:
            case noVal1:
                if (input.getText().toString().equals("") || input.getText().toString().equals("-")) {
                    appendIfNotSucceeding(input.getText().toString(),"-");
                } else if (input.getText().toString().equals(".")) {
                    // do nothing
                } else { //if - used as an operator not negative sign
                    val1 = Double.parseDouble(input.getText().toString());
                    display.setText(input.getText().toString());
                    advanceCheckerStatus();
                    appendIfNotSucceeding(input.getText().toString(),"-");
                    this.symbol = Symbol.minus;
                }
                break;
            case val1NoVal2:
                String i = input.getText().toString();
                if (i.endsWith("+") || i.endsWith("×") || i.endsWith("÷")) {
                    appendIfNotSucceeding(input.getText().toString(),"-");
                }
                else { // - used as an operator
                    if (equalsFlag) {
                        appendIfNotSucceeding(input.getText().toString(),"-");
                        this.equalsFlag = false;
                        this.symbol = Symbol.minus;
                        break;
                    }
                    input.setText(String.valueOf(display.getText()));
                    val1 = Double.parseDouble(input.getText().toString());
                    appendIfNotSucceeding(input.getText().toString(),"-");
                    this.symbol = Symbol.minus;
                }
                break;
            case val1Val2:
                String inpt = input.getText().toString();
                if (inpt.endsWith("+") || inpt.endsWith("×") || inpt.endsWith("÷")) {
                    appendIfNotSucceeding(input.getText().toString(),"-");
                }
                else { // - used as an operator
                    input.setText(String.valueOf(display.getText()));
                    val1 = Double.parseDouble(input.getText().toString());
                    appendIfNotSucceeding(input.getText().toString(),"-");
                    advanceCheckerStatus();
                    this.symbol = Symbol.minus;
                }
                break;
        }
    }
    public String checkIfWhole(double result) {
        if (String.valueOf(result).equals("Infinity") || String.valueOf(result).equals("-Infinity")){
            return "Not a number/Infinity";
        }
        else if (result % 1 == 0) {
            if (BigDecimal.valueOf(result).scale() < -3) {
                BigInteger bIntResult = BigDecimal.valueOf(result).toBigInteger();

                DecimalFormat decimalFormat = new DecimalFormat("0.#############E0");
                String formattedResult = decimalFormat.format(bIntResult);

                return formattedResult;
            }
            return String.valueOf(BigDecimal.valueOf(result).toBigInteger()); //returns a whole number
        }
        else {
            if (BigDecimal.valueOf(result).scale() > 5) { //rounds result if its decimal place exceeds 5 decimal places
                double roundResult = Math.round(result * 100000.0) / 100000.0; //rounds to the 5th decimal place
                return String.valueOf(BigDecimal.valueOf(result).setScale(5, RoundingMode.HALF_UP));
            }
            return String.valueOf(BigDecimal.valueOf(result));
        }
    }
    public void setVal2() {
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
                        addToArrayHistory(val1,val2,symbol,result);
                        display.setText(checkIfWhole(result));
                        Log.e(histories.toString(), "Histories");
                        break;

                    case minus:
                        if (String.valueOf(val1).contains("-")) {
                            val2 = Double.parseDouble(TextUtils.substring(input.getText().toString(),
                                    input.getText().toString().indexOf("-",1) + 1,
                                    input.getText().toString().length()));
                            result = equation();
                            addToArrayHistory(val1,val2,symbol,result);
                            display.setText(checkIfWhole(result));
                        }
                        else {
                            val2 = Double.parseDouble(TextUtils.substring(input.getText().toString(),
                                    input.getText().toString().indexOf("-") + 1,
                                    input.getText().toString().length()));
                            result = equation();
                            addToArrayHistory(val1,val2,symbol,result);
                            display.setText(checkIfWhole(result));
                        }
                        break;

                    case multiply:
                        val2 = Double.parseDouble(TextUtils.substring(input.getText().toString(),
                                input.getText().toString().indexOf("×") + 1,
                                input.getText().toString().length()));
                        result = equation();
                        addToArrayHistory(val1,val2,symbol,result);
                        display.setText(checkIfWhole(result));
                        break;

                    case divide:
                        val2 = Double.parseDouble(TextUtils.substring(input.getText().toString(),
                                input.getText().toString().indexOf("÷") + 1,
                                input.getText().toString().length()));
                        result = equation();
                        addToArrayHistory(val1,val2,symbol,result);
                        display.setText(checkIfWhole(result));
                        break;
                }
                val2Flag = true;
                break;
        }
    }
    public double equation() {
        if (symbol == Symbol.none) {
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
                input.getText().clear();
                display.getText().clear();
                this.val2Flag = false;
                this.symbol = Symbol.none;
                this.checker = Checker.empty;
                val1 = 0.0;
                break;

            case R.id.btnBackspace:
                clearIfNaN();
                handleBackspace();
                break;

            case R.id.btnDivide:
                clearIfNaN();
                checker(Symbol.divide,"÷");
                break;

            case R.id.btnMultiply:
                clearIfNaN();
                checker(Symbol.multiply,"×");
                break;

            case R.id.btnSubtract:
                clearIfNaN();
                checkerForSubtract();
                break;

            case R.id.btnAdd:
                clearIfNaN();
                checker(Symbol.plus,"+");
                break;

            case R.id.btnEquals:
                clearIfNaN();

                if (val2Flag) {
                    input.setText(display.getText());
                    display.getText().clear();
                    val1 = Double.parseDouble(input.getText().toString());
                    this.val2Flag = false;
                    this.equalsFlag = true;
                    this.symbol = Symbol.none;
                    this.checker = Checker.val1NoVal2;
                }
                break;

            case R.id.btnPoint:
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
                handleNumInput("0");
                setVal2();
                break;

            case R.id.btn1:
                handleNumInput("1");
                setVal2();
                break;

            case R.id.btn2:
                handleNumInput("2");
                setVal2();
                break;

            case R.id.btn3:
                handleNumInput("3");
                setVal2();
                break;

            case R.id.btn4:
                handleNumInput("4");
                setVal2();
                break;

            case R.id.btn5:
                handleNumInput("5");
                setVal2();
                break;

            case R.id.btn6:
                handleNumInput("6");
                setVal2();
                break;

            case R.id.btn7:
                handleNumInput("7");
                setVal2();
                break;

            case R.id.btn8:
                handleNumInput("8");
                setVal2();
                break;

            case R.id.btn9:
                handleNumInput("9");
                setVal2();
                break;
        }
        input.setSelection(input.getText().length());


        Log.e(checker.toString(), "checker status");
        Log.e(symbol.toString(), "symbol status");
    }
}