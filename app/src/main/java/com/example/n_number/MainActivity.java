package com.example.n_number;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    Button button;
    TextView textView;
    int attemptCount = 0;
    final int MAX_ATTEMPTS = 3;
    int secretKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        editText = findViewById(R.id.eText);
        button = findViewById(R.id.btn);
        textView = findViewById(R.id.tResult);

        Random random = new Random();
        secretKey = random.nextInt(20) + 1; // Случайное число от 1 до 20
        Log.i("Result", "Secret key: " + secretKey);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (attemptCount < MAX_ATTEMPTS) {
                    String value = editText.getText().toString();

                    // Проверка, что введено число
                    if (!value.isEmpty()) {
                        int intValue = Integer.parseInt(value);

                        // Проверка диапазона числа
                        if (intValue < 1 || intValue > 20) {
                            Toast.makeText(getApplicationContext(), "Введите число от 1 до 20!", Toast.LENGTH_SHORT).show();
                        } else {
                            attemptCount++;

                            // Проверка правильности ответа
                            if (intValue == secretKey) {
                                textView.setText("Вы выиграли!");
                                Toast.makeText(getApplicationContext(), "Поздравляем!", Toast.LENGTH_SHORT).show();
                            } else {
                                if (attemptCount == MAX_ATTEMPTS) {
                                    textView.setText("Попытки закончились. Вы проиграли!");
                                } else {
                                    if (intValue < secretKey) {
                                        textView.setText("Неверно! Выберите большее число.");
                                    } else {
                                        textView.setText("Неверно! Выберите меньшее число.");
                                    }
                                    Toast.makeText(getApplicationContext(), "Осталось попыток: " + (MAX_ATTEMPTS - attemptCount), Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Введите число!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Вы исчерпали все попытки!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
