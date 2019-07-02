///////////////////Proyecto Pie//////////////////////

//Integrantes
//  Anthony Jhon Apaza Chambi
//  Alvaro Freed Apaza Sosa
//  Alonso Jesus Conde Sanchez
//  Jose David Fuentes Cuadros

//  V1.0

package com.example.anthonyjhon.proyectopie;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
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

public class ViewAllActivity extends AppCompatActivity {

    ///////////DataBase
    FirebaseFirestore fireBaseStore;
    ///////////DataBase

    RecyclerView recyViewAllView;
    WishListAdapter recyWishAdapterViewAlllView;
    GridProductLayoutAdapter gridAdapterAllViewWishList;
    List<WishListModel> listProductAllView;
    List<HorizontalScrollProductModel> lisntGridLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("All days discount");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        int layout_code=getIntent().getIntExtra("layout_code",-1);

        recyViewAllView=(RecyclerView) findViewById(R.id.recyclerViewAllView);
        fireBaseStore= FirebaseFirestore.getInstance();

        if (layout_code==0){
            lisntGridLayout=new ArrayList<HorizontalScrollProductModel>();

            gridAdapterAllViewWishList=new GridProductLayoutAdapter(lisntGridLayout,false);

            ////////Base de datos Grid Product
            fireBaseStore.collection("CATEGORIES").document("HOME").collection("NOVEDADES")
                    .orderBy("index").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()){
                        for (QueryDocumentSnapshot documentSnapshots: task.getResult()){
                            float precio=Float.parseFloat(documentSnapshots.get("precio")+"");
                            lisntGridLayout.add(new HorizontalScrollProductModel(documentSnapshots.get("link").toString(),
                                    documentSnapshots.get("title").toString(),
                                    documentSnapshots.get("descripcion").toString(),
                                    String.valueOf(precio)));
                        }
                        gridAdapterAllViewWishList.notifyDataSetChanged();
                    }
                    else{
                        String error=task.getException().getMessage();
                        Toast.makeText(getApplication(),error,Toast.LENGTH_LONG).show();
                    }
                }
            });
            ////////Base de datos Grid Proudct

            recyViewAllView.setAdapter(gridAdapterAllViewWishList);

            recyViewAllView.setLayoutManager(new GridLayoutManager(this,2));
        }
        else if (layout_code==1){
            listProductAllView=new ArrayList<WishListModel>();

            recyWishAdapterViewAlllView=new WishListAdapter(listProductAllView,false);

            ////////Base de datos Grid Product
            fireBaseStore.collection("CATEGORIES").document("HOME").collection("NOVEDADES")
                    .orderBy("index").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()){
                        for (QueryDocumentSnapshot documentSnapshots: task.getResult()){
                            float precio=Float.parseFloat(documentSnapshots.get("precio")+"");
                            listProductAllView.add(new WishListModel(documentSnapshots.get("link").toString(),
                                    documentSnapshots.get("title").toString(),
                                    String.valueOf(precio)));
                        }
                        recyWishAdapterViewAlllView.notifyDataSetChanged();
                    }
                    else{
                        String error=task.getException().getMessage();
                        Toast.makeText(getApplication(),error,Toast.LENGTH_LONG).show();
                    }
                }
            });
            ////////Base de datos Grid Proudct

            LinearLayoutManager linearLayoutManager= new LinearLayoutManager(this);
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyViewAllView.setLayoutManager(linearLayoutManager);

            recyViewAllView.setAdapter(recyWishAdapterViewAlllView);
        }else{

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.pantalla_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
