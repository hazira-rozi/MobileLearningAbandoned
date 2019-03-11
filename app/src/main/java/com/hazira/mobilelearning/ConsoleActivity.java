package com.hazira.mobilelearning;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ConsoleActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_console);

        final Button button = findViewById(R.id.runButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                run();
            }
        });
    }

    private void run() {
        EditText includes = findViewById(R.id.includeHello);
        EditText texts = findViewById(R.id.hello);
        EditText returnValues = findViewById(R.id.returnHello);
        TextView result = findViewById(R.id.resultHello);

        String include = includes.getText().toString();
        String text = texts.getText().toString();
        String returnValue = returnValues.getText().toString();
        int index = text.length();

        if (include.equals("<iostream>") && !text.equals("") && returnValue.equals("0")) {
            result.setText(text.replace("\"", ""));
        } else {
            result.setText(R.string.error);
        }
    }

}
