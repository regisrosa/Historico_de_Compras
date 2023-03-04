package com.example.historicodecompras;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

//O RecyclerAdapter não trabalha com array ou lista de objetos, mas sim com array ou lista de string
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    //Context context;
    List<Compra> compras;

    public RecyclerAdapter(List<Compra> compras) {
        //this.context = context;
        this.compras = compras;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Compra compra = compras.get(position);

        holder.tvProduto.setText(compra.getProduto());
        holder.tvData.setText(compra.getDia() + "/" + compra.getMes() + "/" + compra.getAno());
        holder.tvSupermercado.setText(compra.getSupermercado());
        holder.tvPreco.setText(compra.getPreco());

    }

    @Override
    public int getItemCount() {

        return compras.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvProduto;
        TextView tvData;
        TextView tvSupermercado;
        TextView tvPreco;

        public ViewHolder(View itemView) {
            super(itemView);

            tvProduto = itemView.findViewById(R.id.tv_produto);
            tvData = itemView.findViewById(R.id.tv_data);
            tvSupermercado = itemView.findViewById(R.id.tv_supermercado);
            tvPreco = itemView.findViewById(R.id.tv_preco);

        }
    }
}
