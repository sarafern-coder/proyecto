package com.loeches.proyectogrupo3_tienda.controladores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.loeches.proyectogrupo3_tienda.R;
import com.loeches.proyectogrupo3_tienda.modelos.Product;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private List<Product> listaProductos;
    private final OnAddToCartClickListener onAddToCartClickListener;

    public ProductAdapter(List<Product> listaProductos, OnAddToCartClickListener onAddToCartClickListener) {
        this.listaProductos = listaProductos;
        this.onAddToCartClickListener = onAddToCartClickListener;
    }

    public void actualizarListaProductos(List<Product> listaProductos) {
        this.listaProductos = listaProductos;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product producto = listaProductos.get(position);
        holder.imageView.setImageResource(producto.getImageResId());
        holder.txtNombre.setText(producto.getNombre());
        holder.txtPrecio.setText(String.format("%.2f€", producto.getPrecio()));
        holder.txtDefinicion.setText(producto.getDefinicion());
        holder.txtCantidad.setText(String.valueOf(producto.getCantidad()));

        holder.btnAnadir.setOnClickListener(v -> {

            producto.incrementarCantidad();

            holder.txtCantidad.setText(String.valueOf(producto.getCantidad()));

            onAddToCartClickListener.onAddToCart(producto);
        });
    }


    @Override
    public int getItemCount() {
        return listaProductos.size();
    }

    public interface OnAddToCartClickListener {
        void onAddToCart(Product product);
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView txtNombre, txtPrecio, txtDefinicion, txtCantidad;
        Button btnAnadir;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.Imagen);
            txtNombre = itemView.findViewById(R.id.Nombre);
            txtPrecio = itemView.findViewById(R.id.Precio);
            txtDefinicion = itemView.findViewById(R.id.Definicion);
            txtCantidad = itemView.findViewById(R.id.txt_quantity);
            btnAnadir = itemView.findViewById(R.id.Anadido);
        }
    }
}
