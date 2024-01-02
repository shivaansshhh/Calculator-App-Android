package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private String currentInput = "";
    private double result = 0;
    private String operator = "";
    Button one, two, three, four, five, six, seven, eight, nine, zero;
    Button dot, cancel, percentage, bracket, divide, multiply, subtract, add, equals;
    EditText showValues, showResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        // Set up click listeners for number buttons
        setupButtonClickListener(one, "1");
        setupButtonClickListener(two, "2");
        setupButtonClickListener(three, "3");
        setupButtonClickListener(four, "4");
        setupButtonClickListener(five, "5");
        setupButtonClickListener(six, "6");
        setupButtonClickListener(seven, "7");
        setupButtonClickListener(eight, "8");
        setupButtonClickListener(nine, "9");
        setupButtonClickListener(zero, "0");
        setupButtonClickListener(dot, ".");

        // Set up click listeners for operator buttons
        setupOperatorButtonClickListener(add, "+");
        setupOperatorButtonClickListener(subtract, "-");
        setupOperatorButtonClickListener(multiply, "X");
        setupOperatorButtonClickListener(divide, "/");


        // Set up click listener for the equals button
        equals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performCalculation();  // Calculate and display the result
                operator = "";  // Reset the operator
            }
        });

        // Set up click listener to reset values (cancel button)
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearCalculation();  // Clear all input and results
            }
        });
    }

    // Helper method to set up click listeners for number buttons
    private void setupButtonClickListener(Button button, final String digit){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentInput += digit;  // Append the clicked digit to the input
                updateCalculatorDisplay();  // Update the display with the current input
            }
        });
    }

    // Helper method to set up click listeners for operator buttons
    private void setupOperatorButtonClickListener(Button button, final String operatorSymbol){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performCalculation();  // Calculate and display the result
                operator = operatorSymbol;  // Set the current operator
            }
        });
    }

    // Helper method to update the display with the current input
    private void updateCalculatorDisplay(){
        showValues.setText(currentInput);
        showResult.setText("");
    }

    // Helper method to perform calculations based on the current operator
    private void performCalculation(){
        if (!currentInput.isEmpty()) {
            double inputNumber = Double.parseDouble(currentInput);
            switch (operator) {
                case "+":
                    result += inputNumber;
                    break;
                case "-":
                    result -= inputNumber;
                    break;
                case "X":
                    result *= inputNumber;
                    break;
                case "/":
                    if (inputNumber != 0) {
                        result /= inputNumber;
                    } else {
                        showResult.setText(getString(R.string.error_message));  // Display error for division by zero
                    }
                    break;
                default:
                    result = inputNumber;
                    break;
            }
            currentInput = String.valueOf(result);  // Update the current input with the result
            updateCalculatorDisplay();  // Update the display with the new input
        }
    }


    private void init(){
        one = findViewById(R.id.one);
        two = findViewById(R.id.two);
        three = findViewById(R.id.three);
        four = findViewById(R.id.four);
        five = findViewById(R.id.five);
        six = findViewById(R.id.six);
        seven = findViewById(R.id.seven);
        eight = findViewById(R.id.eight);
        nine = findViewById(R.id.nine);
        zero = findViewById(R.id.zero);
        dot = findViewById(R.id.dot);
        cancel = findViewById(R.id.cancel);
        percentage = findViewById(R.id.percentage);
        bracket = findViewById(R.id.bracket);
        divide = findViewById(R.id.divide);
        multiply = findViewById(R.id.multiply);
        subtract = findViewById(R.id.subtract);
        add = findViewById(R.id.add);
        equals = findViewById(R.id.equals);
        showResult= findViewById(R.id.showResult);
        showValues = findViewById(R.id.showValues);
    }

    private void clearCalculation(){
        currentInput = "";
        result = 0;
        operator = "";
        showValues.setText(currentInput);
        showResult.setText("");
    }
}