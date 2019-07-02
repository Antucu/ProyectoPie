///////////////////Proyecto Pie//////////////////////

//Integrantes
//  Anthony Jhon Apaza Chambi
//  Alvaro Freed Apaza Sosa
//  Alonso Jesus Conde Sanchez
//  Jose David Fuentes Cuadros

//  V1.0

package com.example.anthonyjhon.proyectopie;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class HomePageAdapter extends RecyclerView.Adapter {

    List<HomePageModel> listModeHome;

    public HomePageAdapter(List<HomePageModel> listModeHome) {
        this.listModeHome = listModeHome;
    }

    @Override
    public int getItemViewType(int position) {
        switch (this.listModeHome.get(position).getType()){
            case 1:
                return HomePageModel.HORIZONTAL_MODEL;
            case 2:
                return HomePageModel.HORIZONTAL_CATECORIES;
            default:
                return -1;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("Esto es horizontal",viewType+"");
        switch (viewType){

            case HomePageModel.HORIZONTAL_MODEL:
                View horizontalLayout= LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontal_scroll_layout,parent,false);
                return new HorizontalLayout(horizontalLayout);
            case HomePageModel.HORIZONTAL_CATECORIES:
                View horizontalCategoriesLayout= LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontal_scroll_categories_layout,parent,false);
                return new HorizontalCategoriesLayout(horizontalCategoriesLayout);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (this.listModeHome.get(position).getType()){
            case HomePageModel.HORIZONTAL_MODEL:
                String titulo=this.listModeHome.get(position).getTile();
                String catego=this.listModeHome.get(position).getTile();
                List<HorizontalScrollProductModel> listauxModelHori=this.listModeHome.get(position).getListModelProduct();
                ((HorizontalLayout) holder).setHorizontalLayout(listauxModelHori,titulo,catego);
                break;
            case HomePageModel.HORIZONTAL_CATECORIES:
                String tituloCat=this.listModeHome.get(position).getTitle();
                String color=this.listModeHome.get(position).getColor();
                List<HorizontalScrollProductModel> listauxModelHoriCate=this.listModeHome.get(position).getListModelCategories();
                ((HorizontalCategoriesLayout) holder).setHorizontalCategoriesLayout(listauxModelHoriCate,tituloCat,color);
                break;
            default:
                return;
        }
    }

    @Override
    public int getItemCount() {
        return this.listModeHome.size();
    }

    public class HorizontalLayout extends RecyclerView.ViewHolder{

        private TextView txtTitleScroll;
        private Button btnAllScrollLay;
        private RecyclerView recyScrollProductItem;

        public HorizontalLayout(final View itemView) {
            super(itemView);
            txtTitleScroll= (TextView) itemView.findViewById(R.id.idTextHorizontalScrollLayouTitle);

            btnAllScrollLay=(Button) itemView.findViewById(R.id.idButtonHoriLayoutTitle);
            recyScrollProductItem=(RecyclerView) itemView.findViewById(R.id.idRecylcerLayotHorizontalScroll);
            btnAllScrollLay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i=new Intent(itemView.getContext(), ViewAllActivity.class);
                    i.putExtra("layout_code",1);
                    itemView.getContext().startActivity(i);
                }
            });
        }
        public void setHorizontalLayout(List<HorizontalScrollProductModel> listHoriProductScroll, String title, String cat){
            txtTitleScroll.setText(title);
            HorizontalScrollProductAdapter adpaterHorLayProduct=new HorizontalScrollProductAdapter(listHoriProductScroll);
            LinearLayoutManager linearLayoutManager= new LinearLayoutManager(itemView.getContext());
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

            recyScrollProductItem.setLayoutManager(linearLayoutManager);
            recyScrollProductItem.setAdapter(adpaterHorLayProduct);
            adpaterHorLayProduct.notifyDataSetChanged();
        }
    }

    public class HorizontalCategoriesLayout extends RecyclerView.ViewHolder{
        private TextView txtTitleCategories;
        private RecyclerView recyScrollCategoriesItem;

        public HorizontalCategoriesLayout(View itemView) {
            super(itemView);

            txtTitleCategories= (TextView) itemView.findViewById(R.id.txtHoriScrollLayoutTitleCate);
            recyScrollCategoriesItem=(RecyclerView) itemView.findViewById(R.id.recyViewHoriLayoutCategories);
        }

        public void setHorizontalCategoriesLayout(List<HorizontalScrollProductModel> listHoriProductScroll, String title, String color){
            txtTitleCategories.setText(title);
            txtTitleCategories.setBackgroundColor(Color.parseColor(color));

            HorizontalScrollProductAdapter adpaterHorLayProduct=new HorizontalScrollProductAdapter(listHoriProductScroll);
            LinearLayoutManager linearLayoutManager= new LinearLayoutManager(itemView.getContext());
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            recyScrollCategoriesItem.setLayoutManager(linearLayoutManager);
            recyScrollCategoriesItem.setAdapter(adpaterHorLayProduct);
            adpaterHorLayProduct.notifyDataSetChanged();
        }
    }
}
