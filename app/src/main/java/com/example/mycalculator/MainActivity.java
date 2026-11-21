package com.example.mycalculator;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    TextView Result;
    double EnterNumber1 = 0;
    double EnterNumber2 = 0;
    double ResultNumber = 0;
    String Operator = "";
    boolean IsOperatorClicked = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);

            Result = findViewById(R.id.Result);
            return insets;
        });
    }
    public void AddNumber(View V)
    {
       Button button = (Button) V;
       Result.append(button.getText().toString());
    }
    public void ClearText(View V)
    {
        Result.setText("");
        EnterNumber1 = 0;
        EnterNumber2 = 0;
        ResultNumber = 0;
        Operator = "";
        IsOperatorClicked = false;
    }
    public void BackSpace(View V)
    {
        String text = Result.getText().toString();
        if (!text.isEmpty())
        {
            String NweText = text.substring(0,text.length()-1);
            Result.setText(NweText);
        }
    }
    public void GetOperator(View V)
    {
        if(!Result.getText().toString().isEmpty())
        {
            if (!IsOperatorClicked)
            {
                Button button = (Button) V;
                Operator = button.getText().toString();
                EnterNumber1 = Double.parseDouble(Result.getText().toString());
                Result.setText("");
                IsOperatorClicked = true;
            }
        }
    }
    @SuppressLint("SetTextI18n")
    public void Equal(View V)
    {
        if(!Result.getText().toString().isEmpty())
        {
            EnterNumber2 = Double.parseDouble(Result.getText().toString());
            if (Operator.isEmpty())
            {
                Result.setText("Invalid Operator");
                IsOperatorClicked = false;
                return;
            }
            switch (Operator)
            {
                case "+":
                    ResultNumber = EnterNumber1 + EnterNumber2;
                    break;
                case "-":
                    ResultNumber = EnterNumber1 - EnterNumber2;
                    break;
                case "X":
                    ResultNumber = EnterNumber1 * EnterNumber2;
                    break;
                case "÷":
                    if (EnterNumber2 == 0) {
                        Result.setText("can't divide by zero");
                        IsOperatorClicked = false;
                        return;
                    }
                    ResultNumber = EnterNumber1 / EnterNumber2;
                    break;
            }
            Result.setText(String.valueOf(ResultNumber));
            IsOperatorClicked = false;
        }
    }
   public void Point(View V)
   {
       if(!Result.getText().toString().isEmpty())
       {
           if(!Result.getText().toString().contains("."))
           {
               Result.append(".");
           }
       }
       if(Result.getText().toString().isEmpty())
       {
           Result.append("0.");
       }
   }
}