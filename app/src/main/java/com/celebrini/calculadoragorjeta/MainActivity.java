package com.celebrini.calculadoragorjeta;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText textValorPedido;
    private SeekBar seekGorjeta;
    private TextView textGorjetaPorcentagem, textGorjetaResultado, textTotalResultado, textFeedback;
    private double porcentagem;

    private String valorPedidoString;
    private double valorPedidoDouble, valorGorjeta, valorTotal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textValorPedido = findViewById(R.id.textValorPedido);
        seekGorjeta = findViewById(R.id.seekGorjeta);
        textGorjetaPorcentagem = findViewById(R.id.textGorjetaPorcentagem);
        textGorjetaResultado = findViewById(R.id.textGorjetaResultado);
        textTotalResultado = findViewById(R.id.textTotalResultado);
        textFeedback = findViewById(R.id.textFeedback);



        seekGorjeta.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progresso, boolean b) {

                porcentagem = progresso;
                textGorjetaPorcentagem.setText(progresso + "%");
                calcularGorjeta();

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }


    public void calcularGorjeta(){

        valorPedidoString = textValorPedido.getText().toString();
        //converter para double
        valorPedidoDouble = Double.parseDouble(valorPedidoString);
        valorGorjeta = valorPedidoDouble * (porcentagem / 100);
        valorTotal = valorPedidoDouble + valorGorjeta;

        if (valorPedidoString == null || valorPedidoString.equals("")){
            Toast.makeText(
                    getApplicationContext(),
                    "Escreva o valor do pedido primeiro!",
                    Toast.LENGTH_SHORT
            ).show();
        } else {

            textGorjetaResultado.setText("R$" + valorGorjeta);
            textTotalResultado.setText("R$" + valorTotal);
        }
    }

    public void exibirFeedback(View view){

        textFeedback.setText("A transação foi efetuada, o valor pago foi de R$" + valorTotal);

    }
}
