package com.loeches.proyectogrupo3_tienda.vistas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.loeches.proyectogrupo3_tienda.R;
import com.loeches.proyectogrupo3_tienda.modelos.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class OrderDetailsActivity extends AppCompatActivity {

    private TextView txtCustomerDetails, txtTotalPrice;
    private LinearLayout linearLayoutItems;
    private Button btnPay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        txtCustomerDetails = findViewById(R.id.txt_customer_details);
        txtTotalPrice = findViewById(R.id.txt_total_price);
        linearLayoutItems = findViewById(R.id.linearLayoutItems);
        btnPay = findViewById(R.id.btn_pay);

        // Obtener los datos del cliente del intent
        String remitente = getIntent().getStringExtra("remitente");
        String direccion = getIntent().getStringExtra("direccion");
        String destinatario = getIntent().getStringExtra("destinatario");
        String telefono = getIntent().getStringExtra("telefono");
        String correoElectronico = getIntent().getStringExtra("correoElectronico");
        ArrayList<Product> cartProducts = (ArrayList<Product>) getIntent().getSerializableExtra("cartProducts");

        // Mostrar los detalles del cliente
        String customerDetails = "Remitente: " + remitente + "\n" +
                "Dirección: " + direccion + "\n" +
                "Destinatario: " + destinatario + "\n" +
                "Teléfono: " + telefono + "\n" +
                "Correo Electrónico: " + correoElectronico;
        txtCustomerDetails.setText(customerDetails);

        // Limpiar la vista de los elementos anteriores
        linearLayoutItems.removeAllViews();

        // Verificar si hay productos en el carrito antes de procesarlos
        if (cartProducts != null && !cartProducts.isEmpty()) {
            double totalPrice = 0;

            // Agrupar productos por nombre y contar cantidades
            Map<String, Integer> productQuantities = new HashMap<>();
            Map<String, Double> productPrices = new HashMap<>();

            for (Product product : cartProducts) {
                String productName = product.getNombre();
                double productPrice = product.getPrecio();

                if (productQuantities.containsKey(productName)) {
                    productQuantities.put(productName, productQuantities.get(productName) + 1);
                } else {
                    productQuantities.put(productName, 1);
                    productPrices.put(productName, productPrice);
                }

                // Calcular el precio total
                totalPrice += productPrice;
            }

            // Mostrar los productos agrupados y sus cantidades utilizando las vistas de elementos de producto
            for (Map.Entry<String, Integer> entry : productQuantities.entrySet()) {
                String productName = entry.getKey();
                int quantity = entry.getValue();
                double productPrice = productPrices.get(productName);

                // Inflar la vista del producto
                View productView = getLayoutInflater().inflate(R.layout.item_product_order_details, null);
                TextView txtProductName = productView.findViewById(R.id.txt_product_name);
                TextView txtProductQuantity = productView.findViewById(R.id.txt_product_quantity);
                TextView txtProductTotalPrice = productView.findViewById(R.id.txt_product_total_price);

                // Configurar los textos de los elementos del producto
                txtProductName.setText(productName);
                txtProductQuantity.setText(String.format("Cantidad: %d", quantity));
                txtProductTotalPrice.setText(String.format("Precio Total: %.2f€", productPrice * quantity));

                // Agregar la vista del producto al contenedor
                linearLayoutItems.addView(productView);
            }

            // Mostrar el precio total
            txtTotalPrice.setText(String.format("Total: %.2f€", totalPrice));
        } else {
            // Si no hay productos en el carrito, mostrar total como 0.00€
            txtTotalPrice.setText("Total: 0.00€");
        }

        // Configurar el botón de Confirmar Compra
        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(OrderDetailsActivity.this, "Compra realizada", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(OrderDetailsActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });
    }
}

