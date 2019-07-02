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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class HorizontalScrollProductAdapter extends RecyclerView.Adapter<HorizontalScrollProductAdapter.ViewHolder> {

    private List<HorizontalScrollProductModel> listModelScrollProcudt;

    public List<HorizontalScrollProductModel> getListModelScrollProcudt() {
        return listModelScrollProcudt;
    }

    public void setListModelScrollProcudt(List<HorizontalScrollProductModel> listModelScrollProcudt) {
        this.listModelScrollProcudt = listModelScrollProcudt;
    }

    public HorizontalScrollProductAdapter(List<HorizontalScrollProductModel> listModelScrollProcudt) {
        this.listModelScrollProcudt = listModelScrollProcudt;
    }

    @NonNull
    @Override
    public HorizontalScrollProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontal_scroll_layout_item,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HorizontalScrollProductAdapter.ViewHolder holder, int position) {
        String imgResource=this.listModelScrollProcudt.get(position).getImgHorizontalProduct();
        String titleImgResource=this.listModelScrollProcudt.get(position).getTitleImg();
        String descripImgResource=this.listModelScrollProcudt.get(position).getDescripcionImg();
        String precioImgResource=this.listModelScrollProcudt.get(position).getPrecioImg();
        String catego=this.listModelScrollProcudt.get(position).getCategoria();

        holder.setImgResource(imgResource,catego, this.listModelScrollProcudt.get(position).getEmpresa(), this.listModelScrollProcudt.get(position).getIndex());
        holder.setTitleImgScrollText(titleImgResource);
        holder.setDescripcionImgScrollText(descripImgResource);
        holder.setPrecioImgScrollText(precioImgResource);
    }

    @Override
    public int getItemCount() {
        return listModelScrollProcudt.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imgScrolHorizontal;
        TextView titleImgScroll;
        TextView descripcionImgScroll;
        TextView precioImgScroll;

        public ViewHolder(final View itemView) {
            super(itemView);

            imgScrolHorizontal=(ImageView) itemView.findViewById(R.id.idImageProductScrollHorizontalLayout);
            titleImgScroll= (TextView) itemView.findViewById(R.id.idTextTitleProductHorizontalScrollLayout);
            descripcionImgScroll=(TextView) itemView.findViewById(R.id.idTextDescriptionScrollHorizontalLay);
            precioImgScroll=(TextView) itemView.findViewById(R.id.idTxtPrecioScrollHoriLay);


        }

        public void setImgResource(String imgLinkScrolHorizontal, final String cat,final String emp, final int idx) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent produtcDetails= new Intent(itemView.getContext(),ProductDetailsActivity.class);
                    produtcDetails.putExtra("Empresa",emp);
                    produtcDetails.putExtra("Categoria",cat);
                    produtcDetails.putExtra("Index",idx);
                    itemView.getContext().startActivity(produtcDetails);
                }
            });
            Glide.with(itemView.getContext()).load(imgLinkScrolHorizontal).apply(new RequestOptions().placeholder(R.mipmap.ic_profile_home)).into(imgScrolHorizontal);
        }

        public void setTitleImgScrollText(String titleImgScroll) {
            this.titleImgScroll.setText(titleImgScroll);
        }

        public void setDescripcionImgScrollText(String descripcionImgScroll) {
            this.descripcionImgScroll.setText(descripcionImgScroll);
        }

        public void setPrecioImgScrollText(String precioImgScroll) {
            this.precioImgScroll.setText(precioImgScroll);
        }
    }
}
