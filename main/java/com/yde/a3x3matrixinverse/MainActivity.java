package com.yde.a3x3matrixinverse;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private int input[]= new int[9];
    private EditText one;
    private EditText two;
    private EditText three;
    private EditText four;
    private EditText five;
    private EditText six;
    private EditText seven;
    private EditText eight;
    private EditText nine;
    private Button Calculate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        one = (EditText)findViewById(R.id.first);
        two = (EditText)findViewById(R.id.second);
        three = (EditText)findViewById(R.id.third);
        four = (EditText)findViewById(R.id.fourth);
        five = (EditText)findViewById(R.id.fifth);
        six = (EditText)findViewById(R.id.sixth);
        seven = (EditText)findViewById(R.id.seventh);
        eight = (EditText)findViewById(R.id.eighth);
        nine = (EditText)findViewById(R.id.ninth);
        one.requestFocus();

        Calculate = (Button)findViewById(R.id.calc);
        Calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean z = true;

                if(one.getText().toString().equals("")){
                    z=false;
                   one.setError("Enter Value");}
                else
                    input[0] = Integer.valueOf(one.getText().toString());

                if(two.getText().toString().equals("")){
                    z=false;
                    two.setError("Enter Value");}
                 else
                     input[1] = Integer.valueOf(two.getText().toString());

                if(three.getText().toString().equals("")){
                    z=false;
                    three.setError("Enter Value");}
                else
                    input[2] = Integer.valueOf(three.getText().toString());

                if(four.getText().toString().equals("")){
                    z=false;
                    four.setError("Enter Value");}
                else
                    input[3] = Integer.valueOf(four.getText().toString());

                if(five.getText().toString().equals("")){
                    z=false;
                    five.setError("Enter Value");}
                else
                    input[4] = Integer.valueOf(five.getText().toString());

                if(six.getText().toString().equals("")){
                    z=false;
                    six.setError("Enter Value");}
                else
                    input[5] = Integer.valueOf(six.getText().toString());

                if(seven.getText().toString().equals("")){
                    z=false;
                    seven.setError("Enter Value");}
                else
                    input[6] = Integer.valueOf(seven.getText().toString());

                if(eight.getText().toString().equals("")){
                    z=false;
                    eight.setError("Enter Value");}

                else
                    input[7] = Integer.valueOf(eight.getText().toString());

                if(nine.getText().toString().equals("")){
                    z=false;
                    nine.setError("Enter Value");}
                else
                    input[8] = Integer.valueOf(nine.getText().toString());
                if(z) {
                    Intent intent = new Intent(MainActivity.this, DisplayActivity.class);
                    intent.putExtra("KEY", input);
                    startActivity(intent);
                }
            }
        });

    }
}
