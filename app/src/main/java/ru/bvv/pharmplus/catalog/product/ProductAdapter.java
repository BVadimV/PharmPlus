package ru.bvv.pharmplus.catalog.product;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ru.bvv.pharmplus.R;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private List<ProductItem> productItems;
    private OnItemClickListener listener;
    private static int id;

    public ProductAdapter(List<ProductItem> productItems, OnItemClickListener listener){
        this.productItems = productItems;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        @SuppressLint("ResourceType") View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_product, parent, false);
        return new ProductViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ProductViewHolder holder,final int position) {
        ProductItem currentItem = productItems.get(position);

        holder.titleName.setText(currentItem.getTextName());
        holder.price.setText(currentItem.getTextPrice());
    }

    public static int getId(){
        return id;
    }

    @Override
    public int getItemCount() {return productItems.size();}

    public class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView titleName;
        TextView price;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);

            titleName = itemView.findViewById(R.id.item_product_name);
            price = itemView.findViewById(R.id.item_price);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            ProductItem currentItem = productItems.get(position);
            id = currentItem.getId();
            if (position != RecyclerView.NO_POSITION){
                listener.onItemClick(position);
            }
        }
    }

    // интерфейс Обработка нажатия
    public interface OnItemClickListener{
        void onItemClick(int position);
    }
}