///////////////////Proyecto Pie//////////////////////

//Integrantes
//  Anthony Jhon Apaza Chambi
//  Alvaro Freed Apaza Sosa
//  Alonso Jesus Conde Sanchez
//  Jose David Fuentes Cuadros

//  V1.0

package com.example.anthonyjhon.proyectopie;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class SliderAdapterPromocion extends PagerAdapter {

    List<SliderModelPromocion> listSlidePromocion;
    Context context;
    LayoutInflater layInfla;

    int slide_images[]={
            R.drawable.imgslide,
            R.mipmap.ic_cart,
            R.mipmap.ic_close
    };

    public SliderAdapterPromocion(List<SliderModelPromocion> listSlidePromocionClass) {
        this.listSlidePromocion = listSlidePromocionClass;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        View view= LayoutInflater.from(container.getContext()).inflate(R.layout.slider_promociones_layout,container,false);
        ImageView imgBanner=(ImageView) view.findViewById(R.id.idSliderImg);
        Glide.with(container.getContext()).load(listSlidePromocion.get(position).getBanner()).apply(new RequestOptions().placeholder(R.drawable.imgslide)).into(imgBanner);
        //imgBanner.setImageResource(listSlidePromocion.get(position).getBanner());

        container.addView(view,0);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return listSlidePromocion.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }
}
