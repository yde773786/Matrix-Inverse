package com.yde.a3x3matrixinverse;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

   static private int[] input;
   private EditText[][] matrix;
   static private int selectedDimension;
   private Context mContext = this;
   private GridLayout inputGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputGrid = findViewById(R.id.gridLayout);

        final Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.dimension_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        selectedDimension = 2;

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int x, long l) {
                selectedDimension = spinner.getSelectedItemPosition() + 2;
                input = new int[selectedDimension * selectedDimension];
                inputGrid.removeAllViews();
                matrix = new EditText[selectedDimension][selectedDimension];
                inputGrid.setColumnCount(selectedDimension);
                inputGrid.setRowCount(selectedDimension);
                dimensionChanger(mContext,matrix,inputGrid);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                selectedDimension = 2;
            }
        });

        input = new int[selectedDimension * selectedDimension];
        matrix = new EditText[selectedDimension][selectedDimension];
        dimensionChanger(mContext,matrix,inputGrid);

        matrix[0][0].requestFocus();

        Button calculate = (Button) findViewById(R.id.determinant);
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getValue()) {
                    Intent intent = new Intent(MainActivity.this, DisplayActivity.class);
                    intent.putExtra("KEY", input);
                    intent.putExtra("NKEY", selectedDimension);
                    startActivity(intent);
                }
            }
        });

    }

    private boolean getValue() {
        boolean z = true;
        for (int i = 0; i < selectedDimension; i++) {
            for (int j = 0; j < selectedDimension; j++) {
                if (matrix[i][j].getText().toString().equals("")) {
                    z = false;
                    matrix[i][j].setError("Enter Value");
                } else
                    input[selectedDimension * i + j] = Integer.parseInt(matrix[i][j].getText().toString());
            }
        }
        return z;
    }

    public static int DpToPx(Context context, float dp) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, metrics);
    }

    public static void dimensionChanger(Context mContext , EditText[][] matrix , GridLayout inputGrid ) {
        inputGrid.setColumnCount(selectedDimension);
        inputGrid.setRowCount(selectedDimension);

        for (int i = 0; i < selectedDimension; i++) {
            for (int j = 0; j < selectedDimension; j++) {
                GridLayout.LayoutParams param = new GridLayout.LayoutParams();
                matrix[i][j] = new EditText(mContext);
                param.width = DpToPx(mContext, 50);
                param.height = DpToPx(mContext, 50);
                param.rightMargin = 25;
                param.topMargin = 25;
                param.leftMargin = 25;
                param.bottomMargin = 25;
                param.columnSpec = GridLayout.spec(j);
                param.rowSpec = GridLayout.spec(i);
                matrix[i][j].setGravity(Gravity.CENTER_HORIZONTAL);
                matrix[i][j].setLayoutParams(param);
                matrix[i][j].setInputType(InputType.TYPE_NUMBER_FLAG_SIGNED | InputType.TYPE_CLASS_NUMBER);
                matrix[i][j].setBackgroundResource(R.color.colorGrey);
                inputGrid.addView(matrix[i][j], selectedDimension * i + j);
            }
        }
    }
}
