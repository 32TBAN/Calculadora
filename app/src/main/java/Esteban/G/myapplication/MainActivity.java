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
import android.widget.TextView;
import android.widget.Toast;

import java.util.Stack;

public class MainActivity extends AppCompatActivity {
EditText editTextExprecion;
TextView textViewResult;
Button deleteButton ;
private  String operand="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextExprecion = findViewById(R.id.etInput);
        textViewResult = findViewById(R.id.textViewResult);
        deleteButton = findViewById(R.id.btnDelete);
        deleteButton.setEnabled(false);
    }

    public void onClickButtons(View view) {
        Button button = (Button) view;
        operand += button.getText().toString();
        if(operand.matches("[+\\-*/]")){
            Toast.makeText(this,"Fomato no válido",Toast.LENGTH_SHORT);
        }else if (operand == "."){
            operand = "0.";
            editTextExprecion.setText(operand);
            deleteButton.setEnabled(true);
        }else{
            editTextExprecion.setText(operand);
            deleteButton.setEnabled(true);
        }

    }

    public void onClickDelete(View view) {

        operand = operand.substring(0,operand.length()-1);
        editTextExprecion.setText(operand);

        if (operand.isEmpty()){
            deleteButton.setEnabled(false);
        }
    }

    public void onClickCalculate(View view) {
        String ejemplo = "12-10/3+20-(50-20)*3"; // Ejemplo de cadena
        //operand = ordenar(operand);
        textViewResult.setText(Calculo(operand));
    }

    private String ordenar(String operand) {
        String result ="";

        while (operand.contains("(") && operand.contains(")")){
            int startIndex = operand.lastIndexOf('(');
            int endIndex = operand.indexOf(')', startIndex);
            String innerExpression = operand.substring(startIndex + 1, endIndex);

            // Resuelve las operaciones dentro de los paréntesis
            String innerResult = Calculo(innerExpression);

            // Reemplaza la expresión dentro de los paréntesis con el resultado
            operand = operand.substring(0, startIndex) + innerResult + operand.substring(endIndex + 1);
        }

        return result;
    }

    private String Calculo(String operacion) {
        String[] numeros = operacion.split("[+\\-*/]");

        if (numeros.length >= 2){
            double respuesta = 0;
            for (int i = 0; i <= (numeros.length-1); i++) {
                double operando1 = Double.parseDouble(numeros[i]);
                double operando2 = Double.parseDouble(numeros[i+1]);

                if (operacion.contains("+")) {
                     respuesta = operando1 + operando2;
                } else if (operacion.contains("-")) {
                    respuesta =operando1 - operando2;
                } else if (operacion.contains("/")) {
                    if (operando2 != 0) {
                        respuesta =operando1 / operando2;
                    } else {
                        return  "Error: División por cero";
                    }
                } else if (operacion.contains("*")) {
                    respuesta =operando1 * operando2;
                }
                numeros[i+1] = String.valueOf(respuesta);
            }

            return String.valueOf(respuesta);

        }else{return "Formato usado no valido";}
    }


}