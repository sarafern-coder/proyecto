package com.loeches.proyectogrupo3_tienda.vistas;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.loeches.proyectogrupo3_tienda.R;
import com.loeches.proyectogrupo3_tienda.modelos.Product;

import java.util.ArrayList;

public class OrderDetailsActivity extends AppCompatActivity {

    private TextView txtCustomerDetails, txtTotalPrice;
    private Button btnPay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        txtCustomerDetails = findViewById(R.id.txt_customer_details);
        txtTotalPrice = findViewById(R.id.txt_total_price);
        btnPay = findViewById(R.id.btn_pay);

        // Obtener los datos del cliente del intent
        String name = getIntent().getStringExtra("name");
        String address = getIntent().getStringExtra("address");
        String phone = getIntent().getStringExtra("phone");
        String email = getIntent().getStringExtra("email");

        // Mostrar los datos del cliente
        String customerDetails = "Dirección de Facturación:\n" + address;
        txtCustomerDetails.setText(customerDetails);

        // Obtener los productos del carrito (aquí deberías obtener los datos reales)
        ArrayList<Product> cartProducts = getCartProducts();
        StringBuilder orderDetails = new StringBuilder();
        double totalPrice = 0;

        // Verificar si hay productos en el carrito antes de procesarlos
        if (!cartProducts.isEmpty()) {
            for (Product product : cartProducts) {
                orderDetails.append(product.getNombre()).append(" - ").append(product.getPrecioFormateado()).append("\n");
                totalPrice += product.getPrecio();
            }
        } else {
            orderDetails.append("El carrito está vacío."); // Mensaje si el carrito está vacío
        }

        // No se muestra en el XML, así que lo comentamos
        // txtOrderDetails.setText(orderDetails.toString());
        txtTotalPrice.setText("Total: " + String.format("%.2f€", totalPrice));

        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Simular el pago
                // Aquí puedes agregar la lógica para guardar el pedido en una base de datos o enviar los datos a un servidor
                finish(); // Volver a la página principal
            }
        });
    }

    // Método para obtener los productos del carrito (debes implementar esta lógica)
    private ArrayList<Product> getCartProducts() {
        // Aquí deberías obtener los productos del carrito de compras.
        // Esto es solo un ejemplo con datos estáticos.
        ArrayList<Product> products = new ArrayList<>();

        // Agregar productos al carrito (reemplaza esto con tu lógica real)

        return products;
    }
}