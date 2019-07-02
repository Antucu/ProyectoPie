///////////////////Proyecto Pie//////////////////////

//Integrantes
//  Anthony Jhon Apaza Chambi
//  Alvaro Freed Apaza Sosa
//  Alonso Jesus Conde Sanchez
//  Jose David Fuentes Cuadros

//  V1.0

package com.example.anthonyjhon.proyectopie;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class RecyclerCategoriaAdapter extends RecyclerView.Adapter<RecyclerCategoriaAdapter.VistaHolder>{

    private List<RecyclerCategoriaModel> listCat;

    public RecyclerCategoriaAdapter(List<RecyclerCategoriaModel> listCat) {
        this.listCat = listCat;
    }

    @NonNull
    @Override
    public VistaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_categoria_item,parent,false);
        return new VistaHolder(view) {
        };
    }

    @Override
    public void onBindViewHolder(@NonNull VistaHolder holder, int position) {
        String linkIconImg=listCat.get(position).getCatIconLink();
        String nameIcon=listCat.get(position).getCatIconName();
        holder.setTxtIconCat(nameIcon,position);
        holder.setImgIconCat(linkIconImg);
    }

    @Override
    public int getItemCount() {
        return listCat.size();
    }


    public class VistaHolder extends RecyclerView.ViewHolder{

        ImageView imgIconCat;
        TextView txtIconCatName;

        public VistaHolder(View itemView) {
            super(itemView);
            imgIconCat=(ImageView) itemView.findViewById(R.id.imgIconCat);
            txtIconCatName=(TextView) itemView.findViewById(R.id.txtIconCatName);
        }

        public void setImgIconCat(String urlIconCat) {
            if (!urlIconCat.equals("null")){
                Glide.with(itemView.getContext()).load(urlIconCat).apply(new RequestOptions().placeholder(R.mipmap.ic_profile_home)).into(imgIconCat);
            }
        }

        public void setTxtIconCat(final String txtIconCatName, final int pos) {
            this.txtIconCatName.setText(txtIconCatName);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (pos!=0){
                        Intent categoryItent=new Intent(itemView.getContext(),CategoryRegionActivity.class);
                        categoryItent.putExtra("CategoryName",txtIconCatName);
                        itemView.getContext().startActivity(categoryItent);
                    }
                }
            });
        }
    }
}
