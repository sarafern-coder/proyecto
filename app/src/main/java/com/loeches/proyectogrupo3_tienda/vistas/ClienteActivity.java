package com.loeches.proyectogrupo3_tienda.vistas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import com.loeches.proyectogrupo3_tienda.R;
import com.loeches.proyectogrupo3_tienda.modelos.Cliente;
import com.loeches.proyectogrupo3_tienda.interfacesDAO.ClienteDao;
import com.loeches.proyectogrupo3_tienda.ApiClient;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClienteActivity extends AppCompatActivity {

    private ClienteDao apiService;
    private int idCliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente);

        EditText txtNombre = findViewById(R.id.txtNombre);
        EditText txtTelefono = findViewById(R.id.txtTelefono);
        EditText txtNombreUsuario = findViewById(R.id.txtNombreUsuario);
        EditText txtContrasena = findViewById(R.id.txtContrasena);
        EditText txtId = findViewById(R.id.txtId);
        Button btnGuardar = findViewById(R.id.btnGuardar);
        Button btnEliminar = findViewById(R.id.btnEliminar);

        apiService = ApiClient.getClient().create(ClienteDao.class);

        Intent intent = getIntent();
        idCliente = intent.getIntExtra("idCliente", -1);

        if (idCliente > 0) {
            fetchCliente(idCliente, txtId, txtNombre, txtTelefono, txtNombreUsuario, txtContrasena);
        }

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = txtNombre.getText().toString();
                String telefono = txtTelefono.getText().toString();
                String nombreUsuario = txtNombreUsuario.getText().toString();
                String contrasena = txtContrasena.getText().toString();

                Cliente cliente = new Cliente(nombre, telefono, nombreUsuario, contrasena);
                if (idCliente > 0) {
                    cliente.setIdCliente(idCliente);
                    updateCliente(cliente);
                } else {
                    addCliente(cliente);
                }
            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (idCliente > 0) {
                    deleteCliente(idCliente);
                }
            }
        });
    }

    private void fetchCliente(int idCliente, EditText txtId, EditText txtNombre, EditText txtTelefono, EditText txtNombreUsuario, EditText txtContrasena) {
        Call<Cliente> call = apiService.getCliente(idCliente);
        call.enqueue(new Callback<Cliente>() {
            @Override
            public void onResponse(Call<Cliente> call, Response<Cliente> response) {
                if (response.isSuccessful()) {
                    Cliente cliente = response.body();
                    txtId.setText(String.valueOf(cliente.getIdCliente()));
                    txtNombre.setText(cliente.getNombre());
                    txtTelefono.setText(cliente.getTelefono());
                    txtNombreUsuario.setText(cliente.getNombreUsuario());
                    txtContrasena.setText(cliente.getContrasena());
                }
            }

            @Override
            public void onFailure(Call<Cliente> call, Throwable t) {
                // Manejar el error aquí
            }
        });
    }

    private void addCliente(Cliente cliente) {
        Call<Cliente> call = apiService.addCliente(cliente);
        call.enqueue(new Callback<Cliente>() {
            @Override
            public void onResponse(Call<Cliente> call, Response<Cliente> response) {
                if (response.isSuccessful()) {
                    finish();
                }
            }

            @Override
            public void onFailure(Call<Cliente> call, Throwable t) {
                // Manejar el error aquí
            }
        });
    }

    private void updateCliente(Cliente cliente) {
        Call<Void> call = apiService.updateCliente(cliente);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    finish();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                // Manejar el error aquí
            }
        });
    }

    private void deleteCliente(int idCliente) {
        Call<Void> call = apiService.deleteCliente(idCliente);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    finish();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                // Manejar el error aquí
            }
        });
    }
}
