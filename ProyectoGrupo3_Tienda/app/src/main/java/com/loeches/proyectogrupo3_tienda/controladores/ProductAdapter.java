package com.loeches.proyectogrupo3_tienda.controladores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.loeches.proyectogrupo3_tienda.R;
import com.loeches.proyectogrupo3_tienda.modelos.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private List<Product> productList;
    private OnAddToCartListener onAddToCartListener;
    private List<Integer> quantities;

    public interface OnAddToCartListener {
        void onAddToCart(Product product);
    }

    public ProductAdapter(List<Product> productList, OnAddToCartListener onAddToCartListener) {
        this.productList = productList != null ? productList : new ArrayList<>(); // Handle null list case
        this.onAddToCartListener = onAddToCartListener;
        this.quantities = new ArrayList<>(productList.size());
        for (int i = 0; i < productList.size(); i++) {
            quantities.add(0);
        }
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        if (position >= 0 && position < productList.size()) {
            Product product = productList.get(position);

            holder.txtProductName.setText(product.getNombre());
            holder.txtProductPrice.setText(product.getPrecioFormateado());
            holder.txtProductDefinition.setText(product.getDefinicion());
            Glide.with(holder.itemView.getContext())
                    .load(product.getImagen())
                    .into(holder.imgProduct);

            holder.txtQuantity.setText(String.valueOf(quantities.get(position)));

            holder.btnAddToCart.setOnClickListener(v -> {
                if (onAddToCartListener != null) {
                    onAddToCartListener.onAddToCart(product);
                    quantities.set(position, quantities.get(position) + 1);
                    notifyItemChanged(position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    static class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView txtProductName, txtProductPrice, txtProductDefinition, txtQuantity;
        ImageView imgProduct;
        Button btnAddToCart;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            txtProductName = itemView.findViewById(R.id.Nombre);
            txtProductPrice = itemView.findViewById(R.id.Precio);
            txtProductDefinition = itemView.findViewById(R.id.Definicion);
            imgProduct = itemView.findViewById(R.id.Imagen);
            btnAddToCart = itemView.findViewById(R.id.Anadido);
            txtQuantity = itemView.findViewById(R.id.txt_quantity);
        }
    }

    public void updateProductList(List<Product> newProductList) {
        productList = newProductList != null ? newProductList : new ArrayList<>();
        quantities = new ArrayList<>(productList.size());
        for (int i = 0; i < productList.size(); i++) {
            quantities.add(0);
        }
        notifyDataSetChanged();
    }
}
