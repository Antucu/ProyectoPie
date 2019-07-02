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

public class GridProductLayoutAdapter extends RecyclerView.Adapter<GridProductLayoutAdapter.ViewHolder> {

    private List<HorizontalScrollProductModel> listGridProductModel;
    public boolean imgSinDataBase;

    public GridProductLayoutAdapter(List<HorizontalScrollProductModel> listGridProductModel) {
        this.listGridProductModel = listGridProductModel;
    }

    public GridProductLayoutAdapter(List<HorizontalScrollProductModel> listGridProductModel, boolean database) {
        this.listGridProductModel = listGridProductModel;
        this.imgSinDataBase=database;
    }


    @NonNull
    @Override
    public GridProductLayoutAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontal_scroll_layout_item,parent,false);

        return new GridProductLayoutAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GridProductLayoutAdapter.ViewHolder holder, int position) {
        if (this.imgSinDataBase){
            int imgResource=this.listGridProductModel.get(position).icon;
            String titleImgResource=this.listGridProductModel.get(position).getTitleImg();
            String descripImgResource=this.listGridProductModel.get(position).getDescripcionImg();
            String precioImgResource=this.listGridProductModel.get(position).getPrecioImg();

            holder.setImgResource(imgResource+"");
            holder.setTitleImgScrollText(titleImgResource);
            holder.setDescripcionImgScrollText(descripImgResource);
            holder.setPrecioImgScrollText(precioImgResource);
        }else{
            String imgResource=this.listGridProductModel.get(position).getImgHorizontalProduct();
            String titleImgResource=this.listGridProductModel.get(position).getTitleImg();
            String descripImgResource=this.listGridProductModel.get(position).getDescripcionImg();
            String precioImgResource=this.listGridProductModel.get(position).getPrecioImg();

            holder.setImgResource(imgResource);
            holder.setTitleImgScrollText(titleImgResource);
            holder.setDescripcionImgScrollText(descripImgResource);
            holder.setPrecioImgScrollText(precioImgResource);
        }
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public int getItemCount() {
        return this.listGridProductModel.size();
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

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent produtcDetails= new Intent(itemView.getContext(),ProductDetailsActivity.class);
                    itemView.getContext().startActivity(produtcDetails);
                }
            });
        }

        public void setImgResource(String imgLinkScrolHorizontal) {
            if (imgSinDataBase){
                int link=Integer.parseInt(imgLinkScrolHorizontal);
                this.imgScrolHorizontal.setImageResource(link);
            }
            else{
                Glide.with(itemView.getContext()).load(imgLinkScrolHorizontal).apply(new RequestOptions().placeholder(R.mipmap.ic_profile_home)).into(imgScrolHorizontal);
            }
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
