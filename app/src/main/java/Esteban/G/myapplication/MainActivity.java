package Esteban.G.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
EditText editTextExprecion;
Button deleteButton ;
private  String operand="",result="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextExprecion = findViewById(R.id.etInput);
        deleteButton = findViewById(R.id.btnDelete);
        deleteButton.setEnabled(false);
    }

    public void onClickButtons(View view) {
        Button button = (Button) view;
        operand += button.getText().toString();
        editTextExprecion.setText(operand);
        deleteButton.setEnabled(true);

    }

    public void onClickDelete(View view) {

        operand = operand.substring(0,operand.length()-1);
        editTextExprecion.setText(operand);

        if (operand.isEmpty()){
            deleteButton.setEnabled(false);
        }
    }

}