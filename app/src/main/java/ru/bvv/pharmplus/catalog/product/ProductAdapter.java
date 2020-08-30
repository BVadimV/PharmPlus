package ru.bvv.pharmplus.catalog.product;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ru.bvv.pharmplus.R;
import ru.bvv.pharmplus.catalog.ProductItem;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private List<ProductItem> productItems;


    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView titleName;
        TextView price;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);

            titleName = itemView.findViewById(R.id.item_product_name);
            price = itemView.findViewById(R.id.item_price);

//            itemView.setOnClickListener(this);
        }

//        void bind(int position){
//            ProductItem currentItem = productItems.get(position);
//            titleName.setText(currentItem.getTextName());
//            price.setText(currentItem.getTextPrice());
//        }
    }

    public ProductAdapter(List<ProductItem> productItems){
        this.productItems = productItems;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        @SuppressLint("ResourceType") View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_product, parent, false);
        return new ProductViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        ProductItem currentItem = productItems.get(position);

        holder.titleName.setText(currentItem.getTextName());
        holder.price.setText(currentItem.getTextPrice());
    }

    @Override
    public int getItemCount() {
        return productItems.size();
    }
}
