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

public class WishListAdapter extends RecyclerView.Adapter<WishListAdapter.ViewHolder> {

    public List<WishListModel> listWishModel;
    public boolean imgSinDataBase;

    public WishListAdapter(List<WishListModel> listWishModel) {
        this.listWishModel = listWishModel;
    }

    public WishListAdapter(List<WishListModel> listWishModel, boolean imgSinDataBase) {
        this.listWishModel = listWishModel;
        this.imgSinDataBase = imgSinDataBase;
    }

    @NonNull
    @Override
    public WishListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.wishlist_item_layout,parent,false);

        return new WishListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d("ClassAdapterWish","entro al bind del adapter");
        if (this.imgSinDataBase){
            int imgResource=this.listWishModel.get(position).getLink();
            String titleImgResource=this.listWishModel.get(position).getTitle();

            holder.setInfoLinkInt(imgResource,titleImgResource);

            Log.d("ClassAdapterWish","entro bravo");
        }else{
            String imgResource=this.listWishModel.get(position).getUrl();
            String titleImgResource=this.listWishModel.get(position).getTitle();
            String precioProductResoirces=this.listWishModel.get(position).getPrecio();
            holder.setInfoResources(imgResource,titleImgResource,precioProductResoirces);
        }
    }

    @Override
    public int getItemCount() {
        return this.listWishModel.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgProduct;
        TextView titleProduct;
        TextView precioProduct;
        ImageView iconOfer;
        TextView freeCopuens;
        TextView rating;
        TextView totalTating;
        TextView cuttedProduct;
        TextView paymentMethod;

        public ViewHolder(final View itemView) {
            super(itemView);

            imgProduct=(ImageView) itemView.findViewById(R.id.imgCartFramentItem);
            titleProduct= (TextView) itemView.findViewById(R.id.txtCartFragmentItemTitle);
            precioProduct=(TextView) itemView.findViewById(R.id.txtCartFragmentItemPrecio);
            rating= (TextView) itemView.findViewById(R.id.idTxtCalificaPrdoctDescrip);
            freeCopuens=(TextView) itemView.findViewById(R.id.txtCartFreeCoupen);
            totalTating=(TextView) itemView.findViewById(R.id.idTxtPrecioScrollHoriLay);
            paymentMethod=(TextView) itemView.findViewById(R.id.txtOferredApliedCartFragment);
            cuttedProduct=(TextView) itemView.findViewById(R.id.txtCuttedPriveCartFragmentItem);
            iconOfer=(ImageView) itemView.findViewById(R.id.imgIconWishListItemOfer);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent produtcDetails= new Intent(itemView.getContext(),ProductDetailsActivity.class);
                    itemView.getContext().startActivity(produtcDetails);
                }
            });

        }

        public void setInfoResources(String imgUrlData, String title, String precio) {
            Glide.with(itemView.getContext()).load(imgUrlData).apply(new RequestOptions().placeholder(R.mipmap.ic_profile_home)).into(imgProduct);
            this.titleProduct.setText(title);
            this.precioProduct.setText(precio);
        }


        public void setInfoLinkInt(int linkFunc,String  titleProduct) {
            this.imgProduct.setImageResource(linkFunc);
            this.titleProduct.setText(titleProduct);
        }

    }
}
