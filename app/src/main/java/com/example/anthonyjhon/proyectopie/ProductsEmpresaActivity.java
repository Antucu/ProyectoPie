///////////////////Proyecto Pie//////////////////////

//Integrantes
//  Anthony Jhon Apaza Chambi
//  Alvaro Freed Apaza Sosa
//  Alonso Jesus Conde Sanchez
//  Jose David Fuentes Cuadros

//  V1.0

package com.example.anthonyjhon.proyectopie;

import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ProductsEmpresaActivity extends AppCompatActivity {

    //Details images of product
    ViewPager idViewPagerProductViewEmpresaAll;
    TabLayout tabLayoutIndicatorEmpresaAll;

    //Datos del reclycler view
    RecyclerView recyViewProductsEmpresaAll;
    private GridProductLayoutAdapter gridProductAdapter;
    List<HorizontalScrollProductModel> modelProduct;

    //Base de datos
    FirebaseFirestore fireBaseStore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_empresa);

        fireBaseStore= FirebaseFirestore.getInstance();


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final String titleIconName=getIntent().getStringExtra("CategoryName");
        final String titleIcon=getIntent().getStringExtra("Title");
        getSupportActionBar().setTitle(titleIconName);

        //Tab de images of details of product
        idViewPagerProductViewEmpresaAll=(ViewPager) findViewById(R.id.idViewPagerProductEmpresaNovedades);
        tabLayoutIndicatorEmpresaAll=(TabLayout) findViewById(R.id.idTabLayoutProductEmpresaLay);

        List<ProductViewPagerModel> listImagesProductDetail=new ArrayList<ProductViewPagerModel>();
        listImagesProductDetail.add(new ProductViewPagerModel(0,R.drawable.imgslide));
        listImagesProductDetail.add(new ProductViewPagerModel(0,R.mipmap.ic_img_forgot));
        listImagesProductDetail.add(new ProductViewPagerModel(0,R.mipmap.ic_mail_password));

        ProductViewPagerAdapter adatpterImagesProductDetail=new ProductViewPagerAdapter(listImagesProductDetail);

        idViewPagerProductViewEmpresaAll.setAdapter(adatpterImagesProductDetail);
        tabLayoutIndicatorEmpresaAll.setupWithViewPager(idViewPagerProductViewEmpresaAll,true);
        //Banner Slide

        ////////////GridLayProduct
        recyViewProductsEmpresaAll=(RecyclerView) findViewById(R.id.recyViewProductsEmpresaAll);

        modelProduct=new ArrayList<>();
        gridProductAdapter=new GridProductLayoutAdapter(modelProduct);
        recyViewProductsEmpresaAll.setAdapter(gridProductAdapter);

        recyViewProductsEmpresaAll.setLayoutManager(new GridLayoutManager(this,4));

        ////////Base de datos Grid Product
        /*fireBaseStore.collection("CATEGORIES").document("HOME").collection("NOVEDADES")
                .orderBy("index").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    for (QueryDocumentSnapshot documentSnapshots: task.getResult()){
                        float precio=Float.parseFloat(documentSnapshots.get("precio")+"");
                        modelProduct.add(new HorizontalScrollProductModel(documentSnapshots.get("link").toString(),
                                documentSnapshots.get("title").toString(),
                                documentSnapshots.get("descripcion").toString(),
                                String.valueOf(precio)));
                    }
                    gridProductAdapter.notifyDataSetChanged();
                }
                else{
                    String error=task.getException().getMessage();
                    Toast.makeText(getApplicationContext(),error,Toast.LENGTH_LONG).show();
                }
            }
        });*/
        ////////Base de datos Grid Proudct

        ////////Base de datos Scroll Horizontal
        fireBaseStore.collection("CATEGORIES").document(titleIcon).collection(titleIconName)
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    for(QueryDocumentSnapshot queryDocumentSnapshot1:task.getResult()){
                        float precio=Float.parseFloat(queryDocumentSnapshot1.get("precio")+"");
                        int index=Integer.parseInt(queryDocumentSnapshot1.get("index").toString());
                        modelProduct.add(new HorizontalScrollProductModel(queryDocumentSnapshot1.get("url").toString(),
                                queryDocumentSnapshot1.get("title").toString(),
                                queryDocumentSnapshot1.get("description").toString(),
                                String.valueOf(precio),
                                titleIconName,
                                queryDocumentSnapshot1.get("empresa").toString(),
                                index));
                    }
                    gridProductAdapter.notifyDataSetChanged();
                }
                else{
                    String error=task.getException().getMessage();
                    Toast.makeText(getApplicationContext(),error,Toast.LENGTH_LONG).show();
                }
            }
        });

        gridProductAdapter.notifyDataSetChanged();
        ////////////GridLayProduct
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
