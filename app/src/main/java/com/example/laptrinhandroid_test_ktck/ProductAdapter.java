package com.example.laptrinhandroid_test_ktck;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    private Context context;
    private List<Product> products;

    public ProductAdapter(Context context, List<Product> products) {
        this.context = context;
        this.products = products;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.line_item, parent, false);
        return new ViewHolder(view,this);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = products.get(position);
        holder.txt_li_id.setText("Id : "+product.getId());
        holder.txt_li_name.setText("Full Name : "+ product.getFullName());
        holder.txt_li_job.setText("Job : "+product.getJob());
        holder.itemView.setOnClickListener(v -> {
            SendData sendData = (SendData) context;
            sendData.SendingData(product);
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ProductAdapter adapter;
        TextView txt_li_id, txt_li_name, txt_li_job;
        public ViewHolder(@NonNull View itemView, ProductAdapter adapter) {
            super(itemView);
            this.adapter = adapter;
            txt_li_id = itemView.findViewById(R.id.txt_li_id);
            txt_li_name = itemView.findViewById(R.id.txt_li_name);
            txt_li_job = itemView.findViewById(R.id.txt_li_job);
        }
    }
}
