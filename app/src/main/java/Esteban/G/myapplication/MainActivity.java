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

import java.util.Objects;
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
            Toast.makeText(this,"Fomato no válido",Toast.LENGTH_SHORT).show();
        }else if (Objects.equals(operand, ".")){
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
        textViewResult.setText(Calculo(operand));
    }

    private String Calculo(String operacion) {
        String[] numeros = operacion.split("[+\\-*/]");

        if (numeros.length >= 2){
            String [] ordenar = operand.split("");
            double respuesta = 0;

            for (int i = 0; i < ordenar.length; i++) {
                if (Objects.equals(ordenar[i], "/") && !Objects.equals(ordenar[i+1], "")){
                    respuesta = Double.parseDouble(ordenar[i-1]) / Double.parseDouble(ordenar[i+1]);
                    ordenar[i-1] = String.valueOf(respuesta);
                    ordenar[i] = "";
                    ordenar[i+1] = "";
                }
            }

            for (int i = 0; i < ordenar.length; i++) {
                if (Objects.equals(ordenar[i], "*") && !Objects.equals(ordenar[i+1], "")){
                    respuesta = Double.parseDouble(ordenar[i-1]) * Double.parseDouble(ordenar[i+1]);
                    ordenar[i-1] = String.valueOf(respuesta);
                    ordenar[i] = "";
                    ordenar[i+1] = "";
                }
            }

            for (int i = 0; i < (numeros.length-1); i++) {
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