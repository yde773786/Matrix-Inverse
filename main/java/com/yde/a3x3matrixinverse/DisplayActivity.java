package com.yde.a3x3matrixinverse;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DisplayActivity extends AppCompatActivity {
    private int input[][]= new int[3][3];
    private TextView one;
    private TextView two;
    private TextView three;
    private TextView four;
    private TextView five;
    private TextView six;
    private TextView seven;
    private TextView eight;
    private TextView nine;
    private TextView conclude;
    private Button Calculate;
    private String output[][] = new String[3][3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        int temp[] = getIntent().getIntArrayExtra("KEY");
        int cnt = 0;
        for(int i = 0 ; i <input.length ; i++){
            for(int j = 0 ; j <input.length ; j++){
                input[i][j] = temp[cnt++];
            }
        }
        one = (TextView)findViewById(R.id.one);
        two = (TextView)findViewById(R.id.two);
        three = (TextView)findViewById(R.id.three);
        four = (TextView)findViewById(R.id.four);
        five = (TextView)findViewById(R.id.five);
        six = (TextView)findViewById(R.id.six);
        seven = (TextView)findViewById(R.id.seven);
        eight = (TextView)findViewById(R.id.eight);
        nine = (TextView)findViewById(R.id.nine);
        conclude = (TextView)findViewById(R.id.detzero);

        Matrix ob = new Matrix(input);
        if(ob.Determinant()==0){
            one.setText(R.string.blank);
            two.setText(R.string.blank);
            three.setText(R.string.blank);
            four.setText(R.string.blank);
            five.setText(R.string.blank);
            six.setText(R.string.blank);
            seven.setText(R.string.blank);
            eight.setText(R.string.blank);
            nine.setText(R.string.blank);
            conclude.setText(R.string.detzero);
        }
        else {
            output = ob.Inverse();
            one.setText(output[0][0]);
            two.setText(output[0][1]);
            three.setText(output[0][2]);
            four.setText(output[1][0]);
            five.setText(output[1][1]);
            six.setText(output[1][2]);
            seven.setText(output[2][0]);
            eight.setText(output[2][1]);
            nine.setText(output[2][2]);
        }

    }
}
