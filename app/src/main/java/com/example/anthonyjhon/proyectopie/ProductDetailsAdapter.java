///////////////////Proyecto Pie//////////////////////

//Integrantes
//  Anthony Jhon Apaza Chambi
//  Alvaro Freed Apaza Sosa
//  Alonso Jesus Conde Sanchez
//  Jose David Fuentes Cuadros

//  V1.0

package com.example.anthonyjhon.proyectopie;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class ProductDetailsAdapter extends FragmentPagerAdapter {
    private int totalTabs;

    public ProductDetailsAdapter(FragmentManager fm, int tTabs) {
        super(fm);
        this.totalTabs=tTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                ProductDescriptionFragment productDescriptionFragment1=new ProductDescriptionFragment();
                return productDescriptionFragment1;
            case 1:
                ProductEspecificationFragment productEspecificationFragment=new ProductEspecificationFragment();
                return productEspecificationFragment;
            case 2:
                ProductDescriptionFragment productDescriptionFragment2=new ProductDescriptionFragment();
                return productDescriptionFragment2;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return this.totalTabs;
    }
}
