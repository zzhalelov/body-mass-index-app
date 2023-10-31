package com.example.bodymassindex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText editTextHeight;
    private EditText editTextWeight;
    private Button buttonCalculate;
    private TextView textViewResult;
    private TextView textViewDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextHeight = findViewById(R.id.editTextHeight);
        editTextWeight = findViewById(R.id.editTextWeight);
        buttonCalculate = findViewById(R.id.buttonCalculate);
        textViewResult = findViewById(R.id.textViewResult);
        textViewDescription = findViewById(R.id.textViewDescription);
        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateBMI();
            }
        });
    }

    private void calculateBMI() {
        String heightStr = editTextHeight.getText().toString();
        String weightStr = editTextWeight.getText().toString();
        if (!heightStr.isEmpty() && !weightStr.isEmpty()) {
            try {
                float height = Float.parseFloat(heightStr) / 100;
                float weight = Float.parseFloat(weightStr);
                float bmi = weight / (height * height);
                String result = String.format("Индекс массы тела:            %.2f", bmi);
                textViewResult.setText(result);
                String description = getBMIDescription(bmi);
                textViewDescription.setText(description);
                textViewDescription.setVisibility(View.VISIBLE);
            } catch (NumberFormatException e) {
                textViewResult.setText("Ошибка: Введите корректные числовые значения.");
                textViewDescription.setVisibility(View.GONE);
            }
        } else {
            textViewResult.setText("Ошибка: Введите рост и вес.");
            textViewDescription.setVisibility(View.GONE);
        }
    }

    private String getBMIDescription(float bmi) {
        if (bmi < 18.5) {
            textViewDescription.setTextColor(0xFF0000FF);
            return "Недостаточный вес";
        } else if (bmi >= 18.5 && bmi < 24.9) {
            textViewDescription.setTextColor(0xFF00FF00);
            return "Здоровый вес";
        } else if (bmi >= 25 && bmi < 29.9) {
            textViewDescription.setTextColor(0xFFFFA500);
            return "Предварительное ожирение";
        } else if (bmi >= 30 && bmi < 34.9) {
            textViewDescription.setTextColor(0xFFDC143C);
            return "Ожирение I степени";
        } else if (bmi >= 35 && bmi < 39.9) {
            textViewDescription.setTextColor(0xFFFF0000);
            return "Ожирение II степени";
        } else {
            textViewDescription.setTextColor(0xFF800000);
            return "Ожирение III степени";
        }
    }
}