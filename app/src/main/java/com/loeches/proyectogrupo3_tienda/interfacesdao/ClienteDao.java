package com.loeches.proyectogrupo3_tienda.interfacesdao;

import com.loeches.proyectogrupo3_tienda.modelos.Cliente;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ClienteDao {
    @GET("api.php")
    Call<List<Cliente>> getClientes();

    @POST("api.php")
    Call<Cliente> addCliente(@Body Cliente cliente);

    @PUT("api.php")
    Call<Void> updateCliente(@Body Cliente cliente);

    @DELETE("api.php/{idCliente}")
    Call<Void> deleteCliente(@Path("idCliente") int idCliente);
}

