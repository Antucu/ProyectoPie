///////////////////Proyecto Pie//////////////////////

//Integrantes
//  Anthony Jhon Apaza Chambi
//  Alvaro Freed Apaza Sosa
//  Alonso Jesus Conde Sanchez
//  Jose David Fuentes Cuadros

//  V1.0

package com.example.anthonyjhon.proyectopie;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class ProductEspecificationAdapter extends RecyclerView.Adapter<ProductEspecificationAdapter.ViewHolder> {
    List<ProductEspecificationModel> productEspecificationModels;

    public ProductEspecificationAdapter(List<ProductEspecificationModel> productEspecificationModels) {
        this.productEspecificationModels = productEspecificationModels;
    }


    @Override
    public int getItemViewType(int position) {
        Log.d("asdasdsa", productEspecificationModels.get(position).getType()+"");
        switch (productEspecificationModels.get(position).getType()){

            case 0:
                return ProductEspecificationModel.SPECIFICATION_TITLE;
            case 1:
                return ProductEspecificationModel.SPECIFICATION_BODY;
            default:
                return -1;
        }
    }

    @NonNull
    @Override
    public ProductEspecificationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("viewType",viewType+"");
        switch (viewType){
            case ProductEspecificationModel.SPECIFICATION_TITLE:
                Log.d("viewTypeCase",viewType+"");
                TextView title=new TextView(parent.getContext());
                title.setTypeface(null, Typeface.BOLD);
                title.setTextColor(Color.parseColor("#000000"));
                LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                layoutParams.setMargins(setUp(16,parent.getContext()),
                        setUp(16,parent.getContext()),
                        setUp(16,parent.getContext()),
                        setUp(8,parent.getContext()));
                title.setLayoutParams(layoutParams);
                return new ViewHolder(title);
            case ProductEspecificationModel.SPECIFICATION_BODY:
                View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.product_especification_item_layout,parent,false);
                return new ViewHolder(view);
            default:
                return null;
        }

    }

    @Override
    public void onBindViewHolder(@NonNull ProductEspecificationAdapter.ViewHolder holder, int position) {
        Log.d("onBindTitleHolder", ProductEspecificationModel.SPECIFICATION_TITLE+"");
        switch (this.productEspecificationModels.get(position).getType()){
            case ProductEspecificationModel.SPECIFICATION_TITLE:
                Log.d("onBindTitleCaseTitle", ProductEspecificationModel.SPECIFICATION_TITLE+"");
                holder.setTitle(productEspecificationModels.get(position).getTitle());
                break;
            case ProductEspecificationModel.SPECIFICATION_BODY:
                String featureTitle=this.productEspecificationModels.get(position).getName();
                String featureDetail=this.productEspecificationModels.get(position).getValue();
                holder.setFeatures(featureTitle,featureDetail);
                break;
            default:
                break;
        }
    }

    @Override
    public int getItemCount() {
        return this.productEspecificationModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txtName;
        private TextView txtValue;
        private TextView title;

        public ViewHolder(View itemView) {
            super(itemView);

        }
        public void setFeatures(String name, String value){
            txtName=(TextView) itemView.findViewById(R.id.txtFeatureNameEspecification);
            txtValue=(TextView) itemView.findViewById(R.id.txtFeatureValueEspecification);
            this.txtName.setText(name);
            this.txtValue.setText(value);
        }
        public void setTitle(String titleText){
            this.title=(TextView) itemView;
            title.setText(titleText);
        }
    }

    private int setUp(int dp, Context context){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dp,context.getResources().getDisplayMetrics());
    }
}
