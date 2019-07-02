///////////////////Proyecto Pie//////////////////////

//Integrantes
//  Anthony Jhon Apaza Chambi
//  Alvaro Freed Apaza Sosa
//  Alonso Jesus Conde Sanchez
//  Jose David Fuentes Cuadros

//  V1.0

package com.example.anthonyjhon.proyectopie;

import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

import java.util.List;

public class ProductViewPagerAdapter extends PagerAdapter {

    private List<ProductViewPagerModel> listRecursos;

    public ProductViewPagerAdapter(List<ProductViewPagerModel> listProductImage) {
        this.listRecursos = listProductImage;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        if (listRecursos.get(position).getType()==0){
            ImageView imgProduct=new ImageView(container.getContext());
            imgProduct.setImageResource(listRecursos.get(position).getRecurso());
            container.addView(imgProduct,0);
            return imgProduct;
        }else{
            VideoView videoView=new VideoView(container.getContext());
            Log.d("URL",listRecursos.get(position).getRecursoFB());
            Uri path= Uri.parse(listRecursos.get(position).getRecursoFB());
            MediaController mc=new MediaController(container.getContext());
            videoView.setMediaController(mc);
            videoView.setVideoURI(path);
            videoView.requestFocus();
            //videoView.start();
            return videoView;
        }
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        if (listRecursos.get(position).getType()==0)
            container.removeView((ImageView) object);
        else

            container.removeView((VideoView) object);
    }

    @Override
    public int getCount() {
        return listRecursos.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    public List<ProductViewPagerModel> getListProductImage() {
        return listRecursos;
    }

    public void setListProductImage(List<ProductViewPagerModel> listProductImage) {
        this.listRecursos = listProductImage;
    }
}