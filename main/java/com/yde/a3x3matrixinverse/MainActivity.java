package com.yde.a3x3matrixinverse;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private int[] input = new int[9];
    private EditText[][] matrix = new EditText[3][3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int[] matrixId = new int[]{R.id.first, R.id.second, R.id.third, R.id.fourth, R.id.fifth, R.id.sixth, R.id.seventh, R.id.eighth, R.id.ninth};

        for(int i = 0 ; i<3 ; i++){
            for(int j = 0 ; j<3 ; j++){
                matrix[i][j] = findViewById(matrixId[3*i + j]);
            }
        }

        matrix[0][0].requestFocus();

        Button calculate = (Button) findViewById(R.id.calc);
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getValue()) {
                    Intent intent = new Intent(MainActivity.this, DisplayActivity.class);
                    intent.putExtra("KEY", input);
                    startActivity(intent);
                }
            }
        });

    }

    private boolean getValue(){
        boolean z=true;
        for(int i = 0 ; i < 3 ; i++){
            for(int j = 0 ; j<3 ; j++){
                if(matrix[i][j].getText().toString().equals("")){
                    z=false;
                    matrix[i][j].setError("Enter Value");}
                else
                    input[3*i + j] = Integer.parseInt(matrix[i][j].getText().toString());
            }
        }
        return z;
    }
}
