///////////////////Proyecto Pie//////////////////////

//Integrantes
//  Anthony Jhon Apaza Chambi
//  Alvaro Freed Apaza Sosa
//  Alonso Jesus Conde Sanchez
//  Jose David Fuentes Cuadros

//  V1.0

package com.example.anthonyjhon.proyectopie;

import android.content.Intent;
import android.graphics.Color;
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

public class RecyclerCategoriaCardAdapter extends RecyclerView.Adapter<RecyclerCategoriaCardAdapter.VistaHolderRegion>{

    private List<RecyclerCategoriaModel> listCat;

    public RecyclerCategoriaCardAdapter(List<RecyclerCategoriaModel> listCat) {
        this.listCat = listCat;
    }

    @Override
    public VistaHolderRegion onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout_item,parent,false);
        return new VistaHolderRegion(view){
        };
    }

    @Override
    public void onBindViewHolder(VistaHolderRegion holder, int position) {
        int typ=listCat.get(position).getType();
        String linkCadrImg=listCat.get(position).getCatIconLink();
        String nameCard=listCat.get(position).getCatIconName();
        String colorCard=listCat.get(position).getColor();
        String nameEmpresaCard=listCat.get(position).getTitle();
        holder.setTxtIconCat(typ,nameCard,position,nameEmpresaCard,colorCard);
        //holder.setImgIconCat(linkIconImg);
    }

    @Override
    public int getItemCount() {
        return this.listCat.size();
    }

    public class VistaHolderRegion extends RecyclerView.ViewHolder{

        ImageView imgIconCat;
        TextView txtIconCatName;

        public VistaHolderRegion(View itemView) {
            super(itemView);
            txtIconCatName=(TextView) itemView.findViewById(R.id.txtCardItemLayout);
        }

        public void setImgIconCat(String urlIconCat) {
            if (!urlIconCat.equals("null")){
                Glide.with(itemView.getContext()).load(urlIconCat).apply(new RequestOptions().placeholder(R.mipmap.ic_profile_home)).into(imgIconCat);
            }
        }

        public void setTxtIconCat(final int type, final String txtIconCatName, final int pos, final String titleIco,final String color) {

            this.txtIconCatName.setText(txtIconCatName);
            this.txtIconCatName.setBackgroundColor(Color.parseColor(color));

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (pos!=0 || type==2){
                        if (type==1){
                            Intent categoryItent=new Intent(itemView.getContext(),CategoryActivity.class);
                            categoryItent.putExtra("CategoryName",txtIconCatName);
                            categoryItent.putExtra("Color",color);
                            itemView.getContext().startActivity(categoryItent);
                        }
                        if (type==2){

                            Intent categoryItent=new Intent(itemView.getContext(),ProductsEmpresaActivity.class);
                            categoryItent.putExtra("CategoryName",txtIconCatName);
                            categoryItent.putExtra("Title",titleIco);
                            itemView.getContext().startActivity(categoryItent);
                        }
                    }
                }
            });
        }
    }
}
