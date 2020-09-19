package com.yde.a3x3matrixinverse;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.TextView;

import static com.yde.a3x3matrixinverse.MainActivity.DpToPx;


public class DisplayActivity extends AppCompatActivity {
    Context mContext = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        int[] temp = getIntent().getIntArrayExtra("KEY");
        int selectedDimension = getIntent().getIntExtra("NKEY" , 0);
        int[][] input = new int[selectedDimension][selectedDimension];
        int cnt = 0;
        for(int i = 0 ; i < selectedDimension ; i++){
            for(int j = 0 ; j < selectedDimension ; j++){
                input[i][j] = temp[cnt++];
            }
        }

        TextView[][] matrix = new TextView[selectedDimension][selectedDimension];
        GridLayout outputGrid = findViewById(R.id.gridLayout);

        TextView conclude = (TextView) findViewById(R.id.determinant);
        outputGrid.setColumnCount(selectedDimension);
        outputGrid.setRowCount(selectedDimension);

        for (int i = 0; i < selectedDimension; i++) {
            for (int j = 0; j < selectedDimension; j++) {
                GridLayout.LayoutParams param = new GridLayout.LayoutParams();
                matrix[i][j] = new EditText(mContext);
                param.width = DpToPx(mContext, 90);
                param.height = DpToPx(mContext, 90);
                param.rightMargin = 0;
                param.topMargin = 0;
                param.leftMargin = 0;
                param.bottomMargin = 0;
                param.columnSpec = GridLayout.spec(j);
                param.rowSpec = GridLayout.spec(i);
                matrix[i][j].setGravity(Gravity.CENTER);
                matrix[i][j].setLayoutParams(param);
                matrix[i][j].setBackgroundResource(R.drawable.my_border);
                outputGrid.addView(matrix[i][j], selectedDimension * i + j);
            }
        }

        if(Matrix.determinant(selectedDimension , input)==0){
            for(int i = 0 ; i <selectedDimension ; i++){
                for(int j = 0 ; j<selectedDimension ; j++){
                    matrix[i][j].setText(R.string.blank);
                }
            }
            conclude.setText(R.string.detzero);
        }
        else {
            String[][] output = Matrix.Inverse(input);
            for(int i = 0 ; i <selectedDimension ; i++){
                for(int j = 0 ; j<selectedDimension ; j++){
                    matrix[i][j].setText(output[i][j]);
                }
            }
            conclude.setText(String.format("Determinant is : %s", Matrix.determinant(selectedDimension, input)));
        }

    }
}
