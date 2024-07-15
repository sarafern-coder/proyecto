package com.loeches.proyectogrupo3_tienda.vistas;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.loeches.proyectogrupo3_tienda.R;

public class OrderFormActivity extends AppCompatActivity {

    private TextView txtNumPaquetes;
    private TextView txtFechaCompra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_form);

        txtNumPaquetes = findViewById(R.id.Numeropaquetes);
        txtFechaCompra = findViewById(R.id.fecha);

        int numPaquetes = getIntent().getIntExtra("num_paquetes", 0);
        String fechaCompra = getIntent().getStringExtra("fecha_compra");

        txtNumPaquetes.setText("Número de paquetes: " + numPaquetes);
        txtFechaCompra.setText("Fecha de compra: " + fechaCompra);
    }
}

