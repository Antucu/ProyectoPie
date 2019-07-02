///////////////////Proyecto Pie//////////////////////

//Integrantes
//  Anthony Jhon Apaza Chambi
//  Alvaro Freed Apaza Sosa
//  Alonso Jesus Conde Sanchez
//  Jose David Fuentes Cuadros

//  V1.0

package com.example.anthonyjhon.proyectopie;

import android.media.MediaPlayer;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class CategoryRegionActivity extends AppCompatActivity {

    //Video View
    private VideoView videoView;
    int position =0;

    //Details images of product
    ViewPager idViewPagerProductViewCategoryRegion;
    TabLayout tabLayoutIndicatorCategoryRegion;

    //Datos del reclycler view
    RecyclerView recyViewCategoriRegionMarcas;
    RecyclerCategoriaCardAdapter cardAdapter;
    List<RecyclerCategoriaModel> cardModels;

    //Base de datos
    FirebaseFirestore fireBaseStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_region);

        fireBaseStore= FirebaseFirestore.getInstance();


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final String title=getIntent().getStringExtra("CategoryName");
        getSupportActionBar().setTitle(title);

        //Tab de images of details of product
        idViewPagerProductViewCategoryRegion=(ViewPager) findViewById(R.id.idViewPagerCategoryRegion);
        tabLayoutIndicatorCategoryRegion=(TabLayout) findViewById(R.id.idTabLayoutCategoryRegionLay);

        List<ProductViewPagerModel> listImagesProductDetail=new ArrayList<ProductViewPagerModel>();
        listImagesProductDetail.add(new ProductViewPagerModel(0,R.drawable.imgslide));
        listImagesProductDetail.add(new ProductViewPagerModel(0,R.mipmap.ic_img_forgot));
        listImagesProductDetail.add(new ProductViewPagerModel(0,R.mipmap.ic_mail_password));

        ProductViewPagerAdapter adatpterImagesProductDetail=new ProductViewPagerAdapter(listImagesProductDetail);

        idViewPagerProductViewCategoryRegion.setAdapter(adatpterImagesProductDetail);
        tabLayoutIndicatorCategoryRegion.setupWithViewPager(idViewPagerProductViewCategoryRegion,true);

        ///////////Recycler View
        recyViewCategoriRegionMarcas=(RecyclerView) findViewById(R.id.recyViewCategoryRegionMarcas);
        //LinearLayoutManager layManager= new LinearLayoutManager(this);
        //layManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        //recyViewCategoriRegionMarcas.setLayoutManager(layManager);
        recyViewCategoriRegionMarcas.setLayoutManager(new GridLayoutManager(this,2));
        cardModels=new ArrayList<>();
        cardAdapter=new RecyclerCategoriaCardAdapter(cardModels);

        recyViewCategoriRegionMarcas.setAdapter(cardAdapter);

        ///////Base de datos
        fireBaseStore.collection("CATEGORIES")
                .orderBy("index").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    for (QueryDocumentSnapshot documentSnapshots: task.getResult()){
                        cardModels.add(new RecyclerCategoriaModel(1,documentSnapshots.get("icon").toString()
                                ,documentSnapshots.get("categoryName").toString(),
                                null,
                                documentSnapshots.get("color").toString()));
                    }
                    cardAdapter.notifyDataSetChanged();
                }
                else{
                    String error=task.getException().getMessage();
                    Toast.makeText(getApplicationContext(),error,Toast.LENGTH_LONG).show();
                }
            }
        });
        cardAdapter.notifyDataSetChanged();
        ///////Base de datos
        ///////////Recycler View

        ////Video View
        videoView=findViewById(R.id.videoView);
        final Button b=findViewById(R.id.button);
        final Button b1=findViewById(R.id.button2);
        Uri path=Uri.parse("https://firebasestorage.googleapis.com/v0/b/pruebaspiev001.appspot.com/o/videos%2Fretrieve%20videos%20from%20firebase%20in%20android.mp4?alt=media&token=f1a14137-535c-4971-9595-bac6ed0aab74");
        //MediaController mc=new MediaController(this);
        //v.setMediaController(mc);
        // Se crean los controles multimedia.
        MediaController mediaController = new MediaController(this);

        // Inicializa la VideoView.
        videoView = (VideoView) findViewById(R.id.videoView);
        // Asigna los controles multimedia a la VideoView.
        videoView.setMediaController(mediaController);

        try {
            // Asigna la URI del vídeo que será reproducido a la vista.
            videoView.setVideoURI(path);
            // Se asigna el foco a la VideoView.
            videoView.requestFocus();
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }

        /*
         * Se asigna un listener que nos informa cuando el vídeo
         * está listo para ser reproducido.
         */
        videoView.setOnPreparedListener(videoViewListener);
        //////Video View
    }

    private MediaPlayer.OnPreparedListener videoViewListener =
            new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    /*
                     * Se indica al reproductor multimedia que el vídeo
                     * se reproducirá en un loop (on repeat).
                     */
                    mediaPlayer.setLooping(true);

                    if (position == 0) {
                        /*
                         * Si tenemos una posición en savedInstanceState,
                         * el vídeo debería comenzar desde aquí.
                         */
                        videoView.start();
                    } else {
                        /*
                         * Si venimos de un Activity "resumed",
                         * la reproducción del vídeo será pausada.
                         */
                        videoView.pause();
                    }
                }
            };

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        /* Usamos onSaveInstanceState para guardar la posición de
           reproducción del vídeo en caso de un cambio de orientación. */
        savedInstanceState.putInt("Position",
                videoView.getCurrentPosition());
        videoView.pause();
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        /*
         * Usamos onRestoreInstanceState para reproducir el vídeo
         * desde la posición guardada.
         */
        position = savedInstanceState.getInt("Position");
        videoView.seekTo(position);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search_icon, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.idNavSearch) {
            return true;
        }else if (id == android.R.id.home
                ){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
