package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvResult;
    private String firstNum = "";
    private String operator = "";
    private String secondNum = "";
    private String result = "";
    private String showText = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvResult = findViewById(R.id.tv_result);
        findViewById(R.id.btn_cancel).setOnClickListener(this);
        findViewById(R.id.btn_divide).setOnClickListener(this);
        findViewById(R.id.btn_multiply).setOnClickListener(this);
        findViewById(R.id.btn_clear).setOnClickListener(this);
        findViewById(R.id.btn_seven).setOnClickListener(this);
        findViewById(R.id.btn_eight).setOnClickListener(this);
        findViewById(R.id.btn_nine).setOnClickListener(this);
        findViewById(R.id.btn_plus).setOnClickListener(this);
        findViewById(R.id.btn_four).setOnClickListener(this);
        findViewById(R.id.btn_five).setOnClickListener(this);
        findViewById(R.id.btn_six).setOnClickListener(this);
        findViewById(R.id.btn_minus).setOnClickListener(this);
        findViewById(R.id.btn_one).setOnClickListener(this);
        findViewById(R.id.btn_two).setOnClickListener(this);
        findViewById(R.id.btn_three).setOnClickListener(this);
        findViewById(R.id.btn_sqrt).setOnClickListener(this);
        findViewById(R.id.btn_reciprocal).setOnClickListener(this);
        findViewById(R.id.btn_zero).setOnClickListener(this);
        findViewById(R.id.btn_dot).setOnClickListener(this);
        findViewById(R.id.btn_equal).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String inputText;
        if (view.getId() == R.id.btn_sqrt) {
            inputText = "√";
        } else {
            inputText = ((Button) view).getText().toString();
        }

        switch (view.getId()){
            case R.id.btn_clear:
                clear();
                break;
            case R.id.btn_cancel:
                if (showText.length() > 0 && !showText.equals("0")) {
                    showText = showText.substring(0, showText.length() - 1);
                    refreshText(showText);
                }
                if (showText.length() == 0) {
                    showText = "0";
                    refreshText(showText);
                }
                break;
            case R.id.btn_plus:
            case R.id.btn_minus:
            case R.id.btn_multiply:
            case R.id.btn_divide:
                operator = inputText;
                refreshText(showText + operator);
                break;
            case R.id.btn_equal:
                double calculateResult = calculateFour();
                refreshOperate(String.valueOf(calculateResult));
                refreshText(showText + "=" + result);
                break;
            case R.id.btn_sqrt:
                double sqrt = Math.sqrt(Double.parseDouble(firstNum));
                refreshOperate(String.valueOf(sqrt));
                refreshText(showText + "√=" + result);
                break;
            case R.id.btn_reciprocal:
                double reciprocal = 1 / Double.parseDouble(firstNum);
                refreshOperate(String.valueOf(reciprocal));
                refreshText(showText + "/=" + result);
                break;
            default:
                if (result.length() > 0 && operator.equals("")) {
                    clear();
                }
                if (operator.equals("")) {
                    firstNum += inputText;
                } else {
                    secondNum += inputText;
                }
                if (showText.equals("0") && !inputText.equals(".")) {
                    refreshText(inputText);
                } else {
                    refreshText(showText + inputText);
                }
                break;
        }
    }

    private void refreshText(String text) {
        showText = text;
        tvResult.setText(showText);
    }

    private void clear() {
        refreshText("");
        refreshOperate("");
    }

    private void refreshOperate(String newResult) {
        result = newResult;
        firstNum = result;
        secondNum = "";
        operator = "";
    }

    private double calculateFour() {
        switch (operator) {
            case "+":
                return Double.parseDouble(firstNum) + Double.parseDouble(secondNum);
            case "-":
                return Double.parseDouble(firstNum) - Double.parseDouble(secondNum);
            case "x":
                return Double.parseDouble(firstNum) * Double.parseDouble(secondNum);
            default:
                return Double.parseDouble(firstNum) / Double.parseDouble(secondNum);
        }
    }
}