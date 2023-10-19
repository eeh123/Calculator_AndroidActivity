package com.example.bootcamppractice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;

public class CalcActivity extends AppCompatActivity implements View.OnClickListener {
    private Button clear, divide, multiply, subtract, add, equals, percent, point;
    private Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0;
    private ImageButton backspace;
    private EditText display, input;
    private boolean hasValue = false;
    private Double val1, val2;
    private String holder, symbol;



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
        percent = findViewById(R.id.btnPercent);
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
        percent.setOnClickListener(this);
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

    public void trigger(){
        if (val2 == null) {
            hasValue = false;
        }
        else {
            hasValue = true;
        }
    }

    public double equation() {
        if (symbol == null || symbol.equals("")) {
            return 0;
        }
        else {
            Log.e(symbol, "equation() else block");
            if (symbol.equals("÷")) {
                if (input.getText().toString().equals("") || input.getText().toString().equals("÷")){
                    Log.e(symbol, "if in else block");
                    return val1 / 1;
                }
            } else {
                return val1 / val2;
            }
        }
        return 0;
    }
// flag is true if val2 is not null
// if flag is true == trigger equation()
    @Override
    public void onClick(View view) {

        if (hasValue) {
            equation();
            display.setText((String.valueOf(val2)));
        }
//        val2 = equation();
        Log.e(symbol, "symbol value");
        if(view.getId() == R.id.btnClear) {
            input.getText().clear();
        }
        else if(view.getId() == R.id.btnBackspace) {
            String word = input.getText().toString();
            int length = word.length();
            if (length > 0){
                input.setText(word.substring(0, length - 1));
            }
        }
        else if (view.getId() == R.id.btnDivide) {
            if(input.getText().toString() != null) {
                val1 = Integer.valueOf(String.valueOf(input.getText()));
                input.getText().clear();
                input.append("÷");
                this.symbol = "÷";
                holder = String.valueOf(input.getText());
//                display.setText(String.valueOf(val1) + " " + holder);
            }
            Log.e(String.valueOf(val1), " value 1");
        }
        else if (view.getId() == R.id.btnMultiply) {
            input.append("×");
            holder = String.valueOf(input.getText());
            Log.e(holder, " holder value");
        }
        else if (view.getId() == R.id.btnSubtract) {
            input.append("−");
            holder = String.valueOf(input.getText());
            Log.e(holder, " holder value");
        }
        else if (view.getId() == R.id.btnAdd) {
            input.append("+");
            holder = String.valueOf(input.getText());
            Log.e(holder, " holder value");
        }
        else if (view.getId() == R.id.btnEquals) {
            input.append("=");
            holder = String.valueOf(input.getText());
            Log.e(holder, " holder value");
        }
        else if (view.getId() == R.id.btnPercent) {
            input.append("%");
            holder = String.valueOf(input.getText());
            Log.e(holder, " holder value");
        }
        else if (view.getId() == R.id.btnPoint) {
            input.append(".");
            holder = String.valueOf(input.getText());
            Log.e(holder, " holder value");
        }


        else if (view.getId() == R.id.btn0) {
            if (input.getText().toString().equals("÷")) {
                input.getText().clear();
                input.append("0");
                holder = String.valueOf(input.getText());
            }
            else {
                input.append("0");
                holder = String.valueOf(input.getText());
                Log.e(holder, " holder value");
            }
        }
        else if (view.getId() == R.id.btn1) {
            if (input.getText().toString().equals("÷")) {
                input.getText().clear();
                input.append("1");
                holder = String.valueOf(input.getText());
            }
            else {
                input.append("1");
                holder = String.valueOf(input.getText());
                Log.e(holder, " holder value");
            }
        }
        else if (view.getId() == R.id.btn2) {
            if (input.getText().toString().equals("÷")) {
                input.getText().clear();
                input.append("2");
                holder = String.valueOf(input.getText());
            }
            else {
                input.append("2");
                holder = String.valueOf(input.getText());
                Log.e(holder, " holder value");
            }
        }
        else if (view.getId() == R.id.btn3) {
            if (input.getText().toString().equals("÷")) {
                input.getText().clear();
                input.append("3");
                holder = String.valueOf(input.getText());
            }
            else {
                input.append("3");
                holder = String.valueOf(input.getText());
                Log.e(holder, " holder value");
            }
        }
        else if (view.getId() == R.id.btn4) {
            input.append("4");
            holder = String.valueOf(input.getText());
            Log.e(holder, " holder value");
        }
        else if (view.getId() == R.id.btn5) {
            input.append("5");
            holder = String.valueOf(input.getText());
            Log.e(holder, " holder value");
        }
        else if (view.getId() == R.id.btn6) {
            input.append("6");
            holder = String.valueOf(input.getText());
            Log.e(holder, " holder value");
        }
        else if (view.getId() == R.id.btn7) {
            input.append("7");
            holder = String.valueOf(input.getText());
            Log.e(holder, " holder value");
        }
        else if (view.getId() == R.id.btn8) {
            input.append("8");
            holder = String.valueOf(input.getText());
            Log.e(holder, " holder value");
        }
        else if (view.getId() == R.id.btn9) {
            input.append("9");
            holder = String.valueOf(input.getText());
            Log.e(holder, " holder value");
        }


    }
}