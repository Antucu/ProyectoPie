///////////////////Proyecto Pie//////////////////////

//Integrantes
//  Anthony Jhon Apaza Chambi
//  Alvaro Freed Apaza Sosa
//  Alonso Jesus Conde Sanchez
//  Jose David Fuentes Cuadros

//  V1.0

package com.example.anthonyjhon.proyectopie;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProductEspecificationFragment extends Fragment {

    RecyclerView recyViewProductEspecification;
    public ProductEspecificationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_product_especification,container,false);
        recyViewProductEspecification=(RecyclerView) view.findViewById(R.id.reclyerViewEspecificationProduct);

        LinearLayoutManager lnLayout= new LinearLayoutManager(view.getContext());
        lnLayout.setOrientation(LinearLayoutManager.VERTICAL);

        recyViewProductEspecification.setLayoutManager(lnLayout);

        List<ProductEspecificationModel> listProductoEspecificationModels=new ArrayList<ProductEspecificationModel>();
        listProductoEspecificationModels.add(new ProductEspecificationModel(0,"General"));
        listProductoEspecificationModels.add(new ProductEspecificationModel(1,"Caracteristica 1","Home"));
        listProductoEspecificationModels.add(new ProductEspecificationModel(1,"Caracteristica 1","Home"));
        listProductoEspecificationModels.add(new ProductEspecificationModel(1,"Caracteristica 1","Home"));
        listProductoEspecificationModels.add(new ProductEspecificationModel(1,"Caracteristica 1","Home"));
        listProductoEspecificationModels.add(new ProductEspecificationModel(1,"Caracteristica 1","Home"));
        listProductoEspecificationModels.add(new ProductEspecificationModel(1,"Caracteristica 1","Home"));
        listProductoEspecificationModels.add(new ProductEspecificationModel(0,"Display"));
        listProductoEspecificationModels.add(new ProductEspecificationModel(1,"Caracteristica 1","Home"));
        listProductoEspecificationModels.add(new ProductEspecificationModel(1,"Caracteristica 1","Home"));
        listProductoEspecificationModels.add(new ProductEspecificationModel(1,"Caracteristica 1","Home"));
        listProductoEspecificationModels.add(new ProductEspecificationModel(1,"Caracteristica 1","Home"));
        listProductoEspecificationModels.add(new ProductEspecificationModel(1,"Caracteristica 1","Home"));
        listProductoEspecificationModels.add(new ProductEspecificationModel(1,"Caracteristica 1","Home"));

        ProductEspecificationAdapter productEspecificationAdapter=new ProductEspecificationAdapter(listProductoEspecificationModels);
        productEspecificationAdapter.notifyDataSetChanged();

        recyViewProductEspecification.setAdapter(productEspecificationAdapter);
        return view;
    }

}
