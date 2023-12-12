package br.com.steven.appcalculadoradegorjeta;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private double porcentagem = 0;
    private double valor = 0;
    private double gorjeta = 0;
    private double total = 0;

    private EditText campoValorOriginal;
    private SeekBar campoPercentual;
    private TextView campoPercentualGorjeta;
    private TextView campoValorGorjeta;
    private TextView campoValorFinal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        campoValorOriginal = findViewById(R.id.editTextValorOriginal);
        campoPercentual = findViewById(R.id.seekBarGorjetaPercentual);
        campoPercentualGorjeta = findViewById(R.id.textViewPercentualSelecionado);
        campoValorGorjeta = findViewById(R.id.textViewValorGorjeta);
        campoValorFinal = findViewById(R.id.textViewValorFinal);

        campoPercentual.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                porcentagem = i;
                campoPercentualGorjeta.setText(String.format("%.0f%%", porcentagem));
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

    public void calcularGorjeta() {
        String valorInformado = campoValorOriginal.getText().toString();
        if (valorInformado.isEmpty()) {
            Toast.makeText(this, "Informe um valor para realizar o cálculo.", Toast.LENGTH_LONG).show();
        } else {
            valor = Double.parseDouble(valorInformado);
            gorjeta = valor * (porcentagem / 100);
            total = valor + gorjeta;
            campoValorGorjeta.setText(formatarValor(gorjeta));
            campoValorFinal.setText(formatarValor(total));
        }
    }

    private String formatarValor(double valor) {
        DecimalFormat decimalFormat = new DecimalFormat("$ #,##0.00");
        return decimalFormat.format(valor);
    }

}