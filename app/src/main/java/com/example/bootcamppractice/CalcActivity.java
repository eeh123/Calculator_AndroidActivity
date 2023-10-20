package com.example.bootcamppractice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.text.TextUtils;
import android.widget.Toast;

public class CalcActivity extends AppCompatActivity implements View.OnClickListener {
    // add logs for hasValue2
    // check addition after pressing =

    private Button clear, divide, multiply, subtract, add, equals, blank1, blank2, point;
    private Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0;
    private ImageButton backspace;
    private EditText display, input;
    private int iresult, ival1, ival2;
    private Double result, val1, val2;
    private String symbol;
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

    public void setVal2() {
        Log.e("setVal Function", "setVal2()");
        Log.e(String.valueOf(hasValue1), "val1 state");
        Log.e(String.valueOf(hasValue2), "val2 state");

        if (!hasValue1) {
            display.setText(input.getText());
        }
        else if (!hasValue2) {
            if (input.getText().toString().contains("+")) {
                val2 = Double.parseDouble(TextUtils.substring(input.getText().toString(),
                        input.getText().toString().indexOf("+") + 1,
                        input.getText().toString().length()));
                result = equation();
                display.setText(String.valueOf(result));
                hasValue2 = true;
                Log.e("setVal Function +", "setVal2() addition");
                Log.e(String.valueOf(val2), "setVal2() val2");
            }
            else if (input.getText().toString().contains("−")) {
                if (String.valueOf(val1).contains("−")) {
                    val2 = Double.parseDouble(TextUtils.substring(input.getText().toString(),
                            input.getText().toString().indexOf("−",1) + 1,
                            input.getText().toString().length()));
                    result = equation();
                    display.setText(String.valueOf(result));
                    hasValue2 = true;
                    Log.e("setVal Function −", "setVal2() subtraction if()");
                    Log.e(String.valueOf(val2), "setVal2() val2");
                }
                else {
                    val2 = Double.parseDouble(TextUtils.substring(input.getText().toString(),
                            input.getText().toString().indexOf("−") + 1,
                            input.getText().toString().length()));
                    result = equation();
                    display.setText(String.valueOf(result));
                    hasValue2 = true;
                    Log.e("setVal Function −", "setVal2() subtraction else()");
                    Log.e(String.valueOf(val2), "setVal2() val2");
                }
            }
            else if (input.getText().toString().contains("×")) {
                val2 = Double.parseDouble(TextUtils.substring(input.getText().toString(),
                        input.getText().toString().indexOf("×") + 1,
                        input.getText().toString().length()));
//                hasValue2 = true;
                result = equation();
                display.setText(String.valueOf(result));
                hasValue2 = true;
                Log.e("setVal Function ×", "setVal2() multiplication");
                Log.e(String.valueOf(val2), "setVal2() val2");
            }
            else if (input.getText().toString().contains("÷")) {
                val2 = Double.parseDouble(TextUtils.substring(input.getText().toString(),
                        input.getText().toString().indexOf("÷") + 1,
                        input.getText().toString().length()));
//                hasValue2 = true;
                result = equation();
                display.setText(String.valueOf(result));
                hasValue2 = true;
                Log.e("setVal Function ÷", "setVal2() division");
                Log.e(String.valueOf(val2), "setVal2() val2");
            }
        }
    }

    public double equation() {
        Log.e("Equation Function", "log for equation()");
        Log.e(String.valueOf(symbol), "symbol for equation()");
        Log.e(String.valueOf(val1), "val1 value");
        Log.e(String.valueOf(val2), "val2 value");
        if (symbol == null || symbol.equals("")) {
            return 0;
        }
        else {
            if (symbol.equals("+")) {
                Log.e(String.valueOf(val1+val2), "equation result");
                return val1 + val2;
            }
            else if (symbol.equals("−")) {
                return val1 - val2;
            }
            else if (symbol.equals("×")) {
                return val1 * val2;
            }
            else if (symbol.equals("÷")) {
                return val1 / val2;
            }
            return 0;
        }
    }
    // flag is true if val2 is not null
// if flag is true == trigger equation()
    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btnClear) {
            input.getText().clear();
            display.getText().clear();
            hasValue1 = false;
            hasValue2 = false;
        }
        else if(view.getId() == R.id.btnBackspace) {
            String word = input.getText().toString();
            int length = word.length();
            if (length > 0){
                input.setText(word.substring(0, length - 1));
            }
        }
        else if (view.getId() == R.id.btnDivide) {
            if("".equals(input.getText().toString())) {
            }
            else {
                if (!hasValue1) {
//                    ifVal1IsWholeThenSet(checkV1Whole());
                    val1 = Double.parseDouble(input.getText().toString());
                    hasValue1 = true;
                    input.append("÷");
                    display.setText(String.valueOf(val1));
                }
                else if (hasValue1 && !hasValue2 && equalsFlag) {
                    input.append("÷");
                    equalsFlag = false;
                }
                else if (hasValue1 && !hasValue2) {
                    hasValue2 = false;
                    input.setText(String.valueOf(display.getText()));
                    val1 = Double.parseDouble(input.getText().toString());
                    input.append("÷");
                }
                else if (hasValue1 && hasValue2) {
                    val1 = Double.parseDouble(display.getText().toString());
                    hasValue2 = false;
                    input.setText(String.valueOf(val1));
                    input.append("÷");
                }
                this.symbol = "÷";
            }
        }
        else if (view.getId() == R.id.btnMultiply) {
            if("".equals(input.getText().toString())) {
            }
            else {
                if (!hasValue1) {
                    val1 = Double.parseDouble(input.getText().toString());
                    hasValue1 = true;
                    input.append("×");
                    display.setText(String.valueOf(val1));
                }
                else if (hasValue1 && !hasValue2 && equalsFlag) {
                    input.append("×");
                    equalsFlag = false;
                }
                else if (hasValue1 && !hasValue2) {
                    hasValue2 = false;
                    input.setText(String.valueOf(display.getText()));
                    val1 = Double.parseDouble(input.getText().toString());
                    input.append("×");
                }
                else if (hasValue1 && hasValue2) {
                    val1 = Double.parseDouble(display.getText().toString());
                    hasValue2 = false;
                    input.setText(String.valueOf(val1));
                    input.append("×");
                }
                this.symbol = "×";
            }
        }
        else if (view.getId() == R.id.btnSubtract) {
            if("".equals(input.getText().toString())) {
                input.append("-");
            }
            else {
                if (!hasValue1) {
                    val1 = Double.parseDouble(input.getText().toString());
                    hasValue1 = true;
                    input.append("−");
                    display.setText(String.valueOf(val1));
                    this.symbol = "−";
                }
                else if (hasValue1 && !hasValue2 && equalsFlag) {
                    input.append("−");
                    equalsFlag = false;
                    this.symbol = "−";
                }
                else if (hasValue1 && !hasValue2) {
                    if (input.getText().toString().contains("+")) {
                        input.append("-");
                    }
                    else if (input.getText().toString().contains("×")) {
                        input.append("-");
                    }
                    else if (input.getText().toString().contains("÷")) {
                        input.append("-");
                    }
                    else {
                        hasValue2 = false;
                        input.setText(String.valueOf(display.getText()));
                        val1 = Double.parseDouble(input.getText().toString());
                        input.append("−");
                        this.symbol = "−";
                    }
                }
                else if (hasValue1 && hasValue2) {
                    val1 = Double.parseDouble(display.getText().toString());
                    hasValue2 = false;
                    input.setText(String.valueOf(val1));
                    input.append("−");
                    this.symbol = "−";
                }
            }
        }
        else if (view.getId() == R.id.btnAdd) {
            if("".equals(input.getText().toString())) {
            }
            else {
                if (!hasValue1) {
                    val1 = Double.parseDouble(input.getText().toString());
                    hasValue1 = true;
                    input.append("+");
                    display.setText(String.valueOf(val1));
                }
                else if (hasValue1 && !hasValue2 && equalsFlag) {
                    input.append("+");
                    equalsFlag = false;
                }
                else if (hasValue1 && !hasValue2) {
                    hasValue2 = false;
                    input.setText(String.valueOf(display.getText()));
                    val1 = Double.parseDouble(input.getText().toString());
                    input.append("+");
                }
                else if (hasValue1 && hasValue2) {
                    val1 = Double.parseDouble(display.getText().toString());
                    hasValue2 = false;
                    input.setText(String.valueOf(val1));
                    input.append("+");
                }
                this.symbol = "+";
            }
        }
        else if (view.getId() == R.id.btnEquals) {
            input.setText(display.getText());
            display.getText().clear();
            val1 = Double.parseDouble(input.getText().toString());
            hasValue2 = false;
            equalsFlag = true;
            Log.e(String.valueOf(val1), "val1 after equals");
            Log.e(String.valueOf(val2), "val2 after equals");
            Log.e(String.valueOf(input.getText()), "input value after equals");
            Log.e(String.valueOf(display.getText()), "display value after equals");
        }
        else if (view.getId() == R.id.btnPoint) {
            input.append(".");
            hasValue2 = false;
        }
        else if (view.getId() == R.id.btnBlank1) {
            Toast toast=Toast.makeText(getApplicationContext(),":P",Toast.LENGTH_SHORT);
            toast.show();
        }
        else if (view.getId() == R.id.btnBlank2) {
            Toast toast=Toast.makeText(getApplicationContext(),"P:",Toast.LENGTH_SHORT);
            toast.show();
        }


        else if (view.getId() == R.id.btn0) {
            hasValue2 = false;
            input.append("0");
            setVal2();
        }
        else if (view.getId() == R.id.btn1) {
            hasValue2 = false;
            input.append("1");
            setVal2();
        }
        else if (view.getId() == R.id.btn2) {
            hasValue2 = false;
            input.append("2");
            setVal2();
        }
        else if (view.getId() == R.id.btn3) {
            hasValue2 = false;
            input.append("3");
            setVal2();
        }
        else if (view.getId() == R.id.btn4) {
            hasValue2 = false;
            input.append("4");
            setVal2();
        }
        else if (view.getId() == R.id.btn5) {
            hasValue2 = false;
            input.append("5");
            setVal2();
        }
        else if (view.getId() == R.id.btn6) {
            hasValue2 = false;
            input.append("6");
            setVal2();
        }
        else if (view.getId() == R.id.btn7) {
            hasValue2 = false;
            input.append("7");
            setVal2();
        }
        else if (view.getId() == R.id.btn8) {
            hasValue2 = false;
            input.append("8");
            setVal2();
        }
        else if (view.getId() == R.id.btn9) {
            hasValue2 = false;
            input.append("9");
            setVal2();
        }


    }
}