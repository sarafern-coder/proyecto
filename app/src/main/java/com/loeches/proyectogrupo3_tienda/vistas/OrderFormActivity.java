package com.loeches.proyectogrupo3_tienda.vistas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.loeches.proyectogrupo3_tienda.R;
import com.loeches.proyectogrupo3_tienda.modelos.Product;

import java.util.ArrayList;

public class OrderFormActivity extends AppCompatActivity {

    private EditText etRemitente, etDireccion, etDestinatario, etTelefono, etCorreoElectronico;
    private TextView txtNumPaquetes, txtFechaCompra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_form);

        etRemitente = findViewById(R.id.Datosremitente);
        etDireccion = findViewById(R.id.direccion);
        etDestinatario = findViewById(R.id.Destinatario);
        etTelefono = findViewById(R.id.telefono);
        etCorreoElectronico = findViewById(R.id.correoElectronico);

        txtNumPaquetes = findViewById(R.id.Numeropaquetes);
        txtFechaCompra = findViewById(R.id.fecha);

        int numPaquetes = getIntent().getIntExtra("num_paquetes", 0);
        String fechaCompra = getIntent().getStringExtra("fecha_compra");
        ArrayList<Product> cartProducts = (ArrayList<Product>) getIntent().getSerializableExtra("cartProducts");

        txtNumPaquetes.setText("Número de paquetes: " + numPaquetes);
        txtFechaCompra.setText("Fecha de compra: " + fechaCompra);

        Button btnConfirmarEnvio = findViewById(R.id.confirmarOrden);
        btnConfirmarEnvio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String remitente = etRemitente.getText().toString();
                String direccion = etDireccion.getText().toString();
                String destinatario = etDestinatario.getText().toString();
                String telefono = etTelefono.getText().toString();
                String correoElectronico = etCorreoElectronico.getText().toString();

                Intent intent = new Intent(OrderFormActivity.this, OrderDetailsActivity.class);
                intent.putExtra("remitente", remitente);
                intent.putExtra("direccion", direccion);
                intent.putExtra("destinatario", destinatario);
                intent.putExtra("telefono", telefono);
                intent.putExtra("correoElectronico", correoElectronico);
                intent.putExtra("cartProducts", cartProducts);  // Asegúrate de pasar los productos aquí
                startActivity(intent);
            }
        });
    }
}