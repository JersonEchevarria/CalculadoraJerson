package com.example.calculadora2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView display;
    private double firstValue = 0;
    private String operator = "";
    private boolean isNewOperator = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.display);
        setNumberListeners();
        setOperatorListeners();
    }

    private void setNumberListeners() {
        View.OnClickListener listener = v -> {
            Button button = (Button) v;
            String number = button.getText().toString();
            if (isNewOperator) {
                display.setText(number);
                isNewOperator = false;
            } else {
                display.append(number);
            }
        };

        int[] numberIds = {R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9};
        for (int id : numberIds) {
            findViewById(id).setOnClickListener(listener);
        }
    }

    private void setOperatorListeners() {
        findViewById(R.id.btnAdd).setOnClickListener(v -> setOperator("+"));
        findViewById(R.id.btnSubtract).setOnClickListener(v -> setOperator("-"));
        findViewById(R.id.btnMultiply).setOnClickListener(v -> setOperator("×"));
        findViewById(R.id.btnDivide).setOnClickListener(v -> setOperator("÷"));

        findViewById(R.id.btnEquals).setOnClickListener(v -> calculateResult());
        findViewById(R.id.btnClear).setOnClickListener(v -> clear());
    }

    private void setOperator(String op) {
        firstValue = Double.parseDouble(display.getText().toString());
        operator = op;
        isNewOperator = true;
    }

    private void calculateResult() {
        double secondValue = Double.parseDouble(display.getText().toString());
        double result = 0;

        switch (operator) {
            case "+":
                result = firstValue + secondValue;
                break;
            case "-":
                result = firstValue - secondValue;
                break;
            case "×":
                result = firstValue * secondValue;
                break;
            case "÷":
                result = secondValue != 0 ? firstValue / secondValue : 0;
                break;
        }
        display.setText(secondValue == 0 && operator.equals("÷") ? "Error" : String.valueOf(result));
        isNewOperator = true;
    }

    private void clear() {
        display.setText("0");
        firstValue = 0;
        operator = "";
        isNewOperator = true;
    }
}
