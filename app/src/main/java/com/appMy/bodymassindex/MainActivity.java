package com.appMy.bodymassindex;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText editTextHeight;
    private EditText editTextWeight;
    private Button buttonCalculate;
    private TextView textViewResult;
    private TextView textViewDescription;
    private TextView textViewMoreInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextHeight = findViewById(R.id.editTextHeight);
        editTextWeight = findViewById(R.id.editTextWeight);
        buttonCalculate = findViewById(R.id.buttonCalculate);
        textViewResult = findViewById(R.id.textViewResult);
        textViewDescription = findViewById(R.id.textViewDescription);
        textViewMoreInfo = findViewById(R.id.textViewMoreInfo);

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
                String result = String.format("Индекс массы тела:         %.2f", bmi);
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
            textViewMoreInfo.setText("");
        }
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(buttonCalculate.getWindowToken(), 0);
    }

    @SuppressLint("SetTextI18n")
    private String getBMIDescription(float bmi) {
        if (bmi < 18.5) {
            textViewDescription.setTextColor(0xFF0000FF);
            textViewMoreInfo.setText("");
            return "Недостаточный вес";
        } else if (bmi >= 18.5 && bmi < 24.9) {
            textViewDescription.setTextColor(0xFF00FF00);
            textViewMoreInfo.setText("Поздравляем! Ваш ИМТ указывает на то, что у вас нормальный вес для вашего роста. Поддерживая здоровый вес, вы снижаете риск развития серьезных проблем со здоровьем");
            return "Здоровый вес";
        } else if (bmi >= 25 && bmi < 29.9) {
            textViewDescription.setTextColor(0xFFFFA500);
            textViewMoreInfo.setText("Ваш ИМТ от 25 до 29,9 указывает на то, что у вас небольшой избыточный вес. Вам могут порекомендовать похудеть по состоянию здоровья");
            return "Предварительное ожирение";
        } else if (bmi >= 30 && bmi < 34.9) {
            textViewDescription.setTextColor(0xFFDC143C);
            textViewMoreInfo.setText("Ваш ИМТ более 30 указывает на то, что у вас большой избыточный вес. Ваше здоровье может оказаться под угрозой, если вы не похудеете");
            return "Ожирение I степени";
        } else if (bmi >= 35 && bmi < 39.9) {
            textViewDescription.setTextColor(0xFFFF0000);
            textViewMoreInfo.setText("Ваш ИМТ более 30 указывает на то, что у вас большой избыточный вес. Ваше здоровье может оказаться под угрозой, если вы не похудеете");
            return "Ожирение II степени";
        } else {
            textViewDescription.setTextColor(0xFF800000);
            textViewMoreInfo.setText("Ваш ИМТ более 30 указывает на то, что у вас большой избыточный вес. Ваше здоровье может оказаться под угрозой, если вы не похудеете");
            return "Ожирение III степени";
        }
    }
}