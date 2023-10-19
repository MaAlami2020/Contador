package com.example.contador;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public int contador;
    TextView textoResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textoResultado=(TextView)findViewById(R.id.contadorTexto);
        contador=0;

        keypadEvent keypad = new keypadEvent();
        EditText n_reseteo = (EditText) findViewById(R.id.n_reseteo);
        n_reseteo.setOnEditorActionListener(keypad);
    }

    class keypadEvent implements TextView.OnEditorActionListener{

        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event){
            if(actionId== EditorInfo.IME_ACTION_DONE){
                reseteaContador(null);
            }
            return false;
        }
    }

    public void incrementaContador(View view){
        contador++;
        textoResultado.setText("" + contador);
    }

    public void decrementaContador(View view){
        contador--;
        if(contador < 0){
            CheckBox negativos = (CheckBox) findViewById(R.id.negativos);
            if(!negativos.isChecked()){
                contador=0;
            }
        }
        textoResultado.setText("" + contador);
    }

    public void reseteaContador(View view){
        EditText numero_reset = (EditText) findViewById(R.id.n_reseteo);
        try {
            contador = Integer.parseInt(numero_reset.getText().toString());
        }catch(Exception e){
            contador=0;
        }
        numero_reset.setText("");
        textoResultado.setText("" + contador);
        InputMethodManager myKeypad = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        myKeypad.hideSoftInputFromWindow(numero_reset.getWindowToken(),0);
    }

    /*public void mostrarResultado(){
        TextView textoResultado=(TextView)findViewById(R.id.contadorTexto);
        textoResultado.setText("" + contador);
    }*/
}