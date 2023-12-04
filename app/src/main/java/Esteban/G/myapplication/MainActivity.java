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

import java.util.Arrays;
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
       // String ejemplo = "12-10/3+20-(50-20)*3"; // Ejemplo de cadena
        textViewResult.setText(Calculo(operand));
    }

    private String Calculo(String operacion) {
        String[] elementos = operacion.split("(?=[+\\-*/^()])|(?<=[+\\-*/^()])");

        if (elementos.length >= 3){

            for (int i = 0; i < elementos.length; i++) {
                if (Objects.equals(elementos[i], "(") && !Objects.equals(elementos[i+1], "")){
                    String [] parentesis = new String[elementos.length];
                    Arrays.fill(parentesis, "");
                    int ult = i;
                    int j = 0;
                    i++;
                    while (!Objects.equals(elementos[i], ")")) {
                        parentesis[j] = elementos[i];
                        elementos[i] = "";
                        i++;
                        j++;
                    };
                    elementos[ult] = OrdenSigno(parentesis);
                    Ordenar(elementos);
                    i=0;
                }
            }
            return OrdenSigno(elementos);
        }else{return "Formato usado no valido";}
    }

    private String OrdenSigno(String[] elementos) {
        double respuesta = 0;

        for (int i = 0; i < elementos.length; i++) {
            if (Objects.equals(elementos[i], "/") && !Objects.equals(elementos[i+1], "")){

                if ( Double.parseDouble(elementos[i-1]) != 0) {
                    respuesta = Double.parseDouble(elementos[i-1]) / Double.parseDouble(elementos[i+1]);
                    elementos[i-1] = String.valueOf(respuesta);
                    elementos[i] = "";
                    elementos[i+1] = "";
                    Ordenar(elementos);
                    i=0;

                } else {
                    return  "Error: División por cero";
                }
            }
        }

        for (int i = 0; i < elementos.length; i++) {
            if (Objects.equals(elementos[i], "*") && !Objects.equals(elementos[i+1], "")){
                respuesta = Double.parseDouble(elementos[i-1]) * Double.parseDouble(elementos[i+1]);
                elementos[i-1] = String.valueOf(respuesta);
                elementos[i] = "";
                elementos[i+1] = "";
                Ordenar(elementos);
                i=0;

            }
        }

        for (int i = 0; i < elementos.length; i++) {
            if (Objects.equals(elementos[i], "-") && !Objects.equals(elementos[i+1], "")){
                respuesta = Double.parseDouble(elementos[i-1]) - Double.parseDouble(elementos[i+1]);
                elementos[i-1] = String.valueOf(respuesta);
                elementos[i] = "";
                elementos[i+1] = "";
                Ordenar(elementos);
                i=0;

            }
        }

        for (int i = 0; i < elementos.length; i++) {
            if (Objects.equals(elementos[i], "+") && !Objects.equals(elementos[i+1], "")){
                respuesta = Double.parseDouble(elementos[i-1]) + Double.parseDouble(elementos[i+1]);
                elementos[i-1] = String.valueOf(respuesta);
                elementos[i] = "";
                elementos[i+1] = "";
                Ordenar(elementos);
                i=0;

            }
        }
        return String.valueOf(respuesta);
    }

    private String[] Ordenar(String [] elementos) {

        for (int i = 0; i < elementos.length; i++) {
            if (elementos[i].equals("(") || elementos[i].equals(")")){
                elementos[i] = "";
            }
        }

        for (int i = 0; i < elementos.length; i++) {
            if (elementos[i].equals("")){
                for (int j = i; j < elementos.length; j++) {
                    if (!elementos[j].equals("")){
                        elementos[i] = elementos[j];
                        elementos[j] = "";
                        break;
                    }
                }
            }
        }

        return elementos;
    }


}