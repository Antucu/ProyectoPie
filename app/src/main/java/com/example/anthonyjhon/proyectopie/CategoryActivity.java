///////////////////Proyecto Pie//////////////////////

//Integrantes
//  Anthony Jhon Apaza Chambi
//  Alvaro Freed Apaza Sosa
//  Alonso Jesus Conde Sanchez
//  Jose David Fuentes Cuadros

//  V1.0

package com.example.anthonyjhon.proyectopie;

import android.graphics.Color;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends AppCompatActivity {

    private VideoView v;

    //Primera opcion
    private RecyclerView catRecycle;
    private TextView txtTitleScroll;
    private Button btnAllScrollLay;
    private List<HorizontalScrollProductModel> listProductCategoriaPrueba;

    //Segunda opcion adecuando a los requerimientos de Jpse
    TextView txtTitleCateGoriaMarcas;
    RecyclerCategoriaCardAdapter cardAdapter;
    List<RecyclerCategoriaModel> cardModels;
    RecyclerView recyViewCategoriaMarcas;

    //Base de datos
    FirebaseFirestore fireBaseStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        fireBaseStore= FirebaseFirestore.getInstance();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final String title=getIntent().getStringExtra("CategoryName");
        final String color=getIntent().getStringExtra("Color");
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ////////////////Scroll Product

        catRecycle=(RecyclerView) findViewById(R.id.recyclerViewCategoria);

        listProductCategoriaPrueba=new ArrayList<HorizontalScrollProductModel>();

        LinearLayoutManager lin= new LinearLayoutManager(this);
        lin.setOrientation(LinearLayoutManager.VERTICAL);
        catRecycle.setLayoutManager(lin);

        final List<HomePageModel> listPageModel=new ArrayList<>();
        final HomePageAdapter adapterHome=new HomePageAdapter(listPageModel);

        catRecycle.setAdapter(adapterHome);

        ////////Base de datos Scroll Horizontal
        fireBaseStore.collection("EMPRESAS").whereEqualTo("categoria",title)
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                List<String> nameempr=new ArrayList<>();
                for (QueryDocumentSnapshot documentSnapshot:task.getResult()){
                    nameempr.add(documentSnapshot.get("name").toString());
                }
                for (String na:nameempr){
                    final List<HorizontalScrollProductModel> auxList=new ArrayList<>();
                    listPageModel.add(new HomePageModel(1,na,auxList));

                    fireBaseStore.collection("CATEGORIES").document(title).collection(na)
                            .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            for(QueryDocumentSnapshot queryDocumentSnapshot1:task.getResult()){
                                float precio=Float.parseFloat(queryDocumentSnapshot1.get("precio")+"");
                                int index=Integer.parseInt(queryDocumentSnapshot1.get("index").toString());
                                auxList.add(new HorizontalScrollProductModel(queryDocumentSnapshot1.get("url").toString(),
                                        queryDocumentSnapshot1.get("title").toString(),
                                        queryDocumentSnapshot1.get("description").toString(),
                                        String.valueOf(precio),
                                        title,
                                        queryDocumentSnapshot1.get("empresa").toString(),
                                        index));
                            }
                            adapterHome.notifyDataSetChanged();
                        }
                    });
                }
            }
        });

        adapterHome.notifyDataSetChanged();

        //Adecuando a la interfaz grafica dado por Jose
        txtTitleCateGoriaMarcas=(TextView) findViewById(R.id.txtTitleCategoryActivityMarcas);
        recyViewCategoriaMarcas=(RecyclerView) findViewById(R.id.recyViewCatogryActivityMarcas);

        txtTitleCateGoriaMarcas.setBackgroundColor(Color.parseColor(color));
        txtTitleCateGoriaMarcas.setText(title);

        recyViewCategoriaMarcas.setLayoutManager(new GridLayoutManager(this,2));
        cardModels=new ArrayList<>();
        cardAdapter=new RecyclerCategoriaCardAdapter(cardModels);

        recyViewCategoriaMarcas.setAdapter(cardAdapter);
        //Base de datos
        fireBaseStore.collection("EMPRESAS")
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    for (QueryDocumentSnapshot documentSnapshots: task.getResult()){
                        cardModels.add(new RecyclerCategoriaModel(2,null
                                ,documentSnapshots.get("name").toString(),
                                title,
                                color));
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
        //Base de datos
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
