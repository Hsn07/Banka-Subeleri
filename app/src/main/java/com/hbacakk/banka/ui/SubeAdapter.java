package com.hbacakk.banka.ui;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.hbacakk.banka.R;
import com.hbacakk.banka.data.models.Sube;
import com.hbacakk.banka.databinding.ItemSubeBinding;

import java.util.ArrayList;
import java.util.List;

public class SubeAdapter extends RecyclerView.Adapter<SubeAdapter.SubeViewHolder> {

    ArrayList<Sube> subeArrayList = new ArrayList<>();

    public void setSubeArrayList(List<Sube> subeList) {
        subeArrayList.addAll(subeList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SubeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SubeViewHolder(DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item_sube,
                parent, false
        ));
    }

    @Override
    public void onBindViewHolder(@NonNull SubeViewHolder holder, int position) {
        holder.setData(subeArrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return subeArrayList.size();
    }

    class SubeViewHolder extends RecyclerView.ViewHolder {
        ItemSubeBinding subeBinding;

        public SubeViewHolder(ItemSubeBinding subeBinding) {
            super(subeBinding.getRoot());
            this.subeBinding = subeBinding;
        }

        public void setData(Sube sube) {
            subeBinding.setSube(sube);
            subeBinding.executePendingBindings();
        }
    }

}
