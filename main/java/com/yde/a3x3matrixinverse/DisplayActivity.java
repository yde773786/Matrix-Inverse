package com.yde.a3x3matrixinverse;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;


public class DisplayActivity extends AppCompatActivity {
    private int[][] input = new int[3][3];
    private TextView[][] matrix = new TextView[3][3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        int[] matrixId = new int[]{R.id.one, R.id.two, R.id.three, R.id.four, R.id.five, R.id.six, R.id.seven, R.id.eight, R.id.nine};
        int[] temp = getIntent().getIntArrayExtra("KEY");
        int cnt = 0;
        for(int i = 0 ; i <input.length ; i++){
            for(int j = 0 ; j <input.length ; j++){
                input[i][j] = temp[cnt++];
                matrix[i][j] = findViewById(matrixId[3*i + j]);
            }
        }

        TextView conclude = (TextView) findViewById(R.id.detzero);

        Matrix ob = new Matrix(input);
        if(ob.Determinant()==0){
            for(int i = 0 ; i <3 ; i++){
                for(int j = 0 ; j<3 ; j++){
                    matrix[i][j].setText(R.string.blank);
                }
            }
            conclude.setText(R.string.detzero);
        }
        else {
            String[][] output = ob.Inverse();
            for(int i = 0 ; i <3 ; i++){
                for(int j = 0 ; j<3 ; j++){
                    matrix[i][j].setText(output[i][j]);
                }
            }
        }

    }
}
