///////////////////Proyecto Pie//////////////////////

//Integrantes
//  Anthony Jhon Apaza Chambi
//  Alvaro Freed Apaza Sosa
//  Alonso Jesus Conde Sanchez
//  Jose David Fuentes Cuadros

//  V1.0

package com.example.anthonyjhon.proyectopie;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class ProductDetailsActivity extends AppCompatActivity {

    FloatingActionButton btnFloatFavorite;
    //Details images of product
    ViewPager idViewPagerProductViewDetailsLay;
    TabLayout tabLayoutIndicatorProductViewDetailsLay;

    //Details description of product
    ViewPager idViewPagerTabDescriptionProduct;
    TabLayout tabLayoutDescriptionProduct;

    //Rating Layout
    LinearLayout linRateSatrRating;

    //Info de la empresa
    TextView nameEmpresa;
    TextView txtMensaje;
    TextView txtMapa;
    TextView txtWeb;
    TextView txtLlamar;
    EditText textoMensaje;

    //Base de datos
    FirebaseFirestore fireBaseStore;

    String categoria;
    String title;
    int index;
    String mapa;
    String telefono;
    String web;
    int auxSendMsg=0;

    private static boolean ALREADY_ADDED_TO_WISHLIST=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        //Permisos
        if(ActivityCompat.checkSelfPermission(
                this, Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED&& ActivityCompat.checkSelfPermission(
                this,Manifest
                        .permission.SEND_SMS)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]
                    { Manifest.permission.SEND_SMS,},1000);
        }else{
        };

        PackageManager pm = this.getPackageManager();

        if (!pm.hasSystemFeature(PackageManager.FEATURE_TELEPHONY) &&
                !pm.hasSystemFeature(PackageManager.FEATURE_TELEPHONY_CDMA)) {
            Toast.makeText(this, "Lo sentimos, tu dispositivo probablemente no pueda enviar SMS...", Toast.LENGTH_SHORT).show();
        }
        //Permisos

        fireBaseStore= FirebaseFirestore.getInstance();

        categoria=getIntent().getStringExtra("Categoria");
        title=getIntent().getStringExtra("Empresa");
        index=getIntent().getIntExtra("Index",0);

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setTitle(title+index);

        //Tab de images of details of product
        idViewPagerProductViewDetailsLay=(ViewPager) findViewById(R.id.idViewPagerProductDetailsViewLay);
        tabLayoutIndicatorProductViewDetailsLay=(TabLayout) findViewById(R.id.idTabLayoutProductViewLay);
        btnFloatFavorite=(FloatingActionButton) findViewById(R.id.idbtnFloatingFavorite);

        List<ProductViewPagerModel> listImagesProductDetail=new ArrayList<ProductViewPagerModel>();
        listImagesProductDetail.add(new ProductViewPagerModel(0,R.drawable.imgslide));
        listImagesProductDetail.add(new ProductViewPagerModel(0,R.mipmap.ic_img_forgot));
        listImagesProductDetail.add(new ProductViewPagerModel(0,R.mipmap.ic_mail_password));
        listImagesProductDetail.add(new ProductViewPagerModel(1,"https://firebasestorage.googleapis.com/v0/b/pruebaspiev001.appspot.com/o/videos%2Fretrieve%20videos%20from%20firebase%20in%20android.mp4?alt=media&token=f1a14137-535c-4971-9595-bac6ed0aab74"));

        ProductViewPagerAdapter adatpterImagesProductDetail=new ProductViewPagerAdapter(listImagesProductDetail);

        idViewPagerProductViewDetailsLay.setAdapter(adatpterImagesProductDetail);
        tabLayoutIndicatorProductViewDetailsLay.setupWithViewPager(idViewPagerProductViewDetailsLay,true);

        btnFloatFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ALREADY_ADDED_TO_WISHLIST){
                    ALREADY_ADDED_TO_WISHLIST=false;
                    btnFloatFavorite.setImageTintList(ColorStateList.valueOf(Color.parseColor("#9e9e9e")));
                }else{
                    ALREADY_ADDED_TO_WISHLIST=true;
                    btnFloatFavorite.setImageTintList(getResources().getColorStateList(R.color.colorPrimary));
                }
            }
        });

        //TabLayout descrition of details of product
        idViewPagerTabDescriptionProduct=(ViewPager) findViewById(R.id.viewPagerDescriptionProduct);
        tabLayoutDescriptionProduct=(TabLayout) findViewById(R.id.tabLayoutDescriptionProduct);

        idViewPagerTabDescriptionProduct.setAdapter(new ProductDetailsAdapter(getSupportFragmentManager(),tabLayoutDescriptionProduct.getTabCount()));
        idViewPagerTabDescriptionProduct.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayoutDescriptionProduct));

        tabLayoutDescriptionProduct.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                idViewPagerTabDescriptionProduct.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        //Coger las estrellas
        linRateSatrRating=(LinearLayout) findViewById(R.id.liinearLayoutRowStarRating);
        for (int i=0;i<linRateSatrRating.getChildCount();i++){
            final int strPosition=i;
            linRateSatrRating.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    setRating (strPosition);
                }
            });
        }

        //Mostrar los datos del producto
        if (title!=null){
            ////////Base de datos Scroll Horizontal
            fireBaseStore.collection("CATEGORIES").document(categoria).collection(title)
                    .orderBy("index").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()){
                        for (QueryDocumentSnapshot documentSnapshots: task.getResult()){
                            float precio=Float.parseFloat(documentSnapshots.get("precio")+"");
                            int indexPro=Integer.parseInt(documentSnapshots.get("index").toString());
                            if (index==indexPro){
                                mapa=documentSnapshots.get("mapa").toString();
                                web=documentSnapshots.get("web").toString();
                                telefono=documentSnapshots.get("telefono").toString();
                            }
                        }
                    }
                    else{
                        String error=task.getException().getMessage();
                    }
                }
            });
            ////////Base de datos Scroll Horizontal
        }

        //Mostrar datos de la empresa
        nameEmpresa=findViewById(R.id.txtNombreEmpresaLayInfo);
        txtMensaje=findViewById(R.id.btnTxtMensaje);
        txtMapa=findViewById(R.id.btnTxtMapa);
        txtLlamar=findViewById(R.id.btnTxtLlamar);
        txtWeb=findViewById(R.id.btnTxtPaginaWeb);
        textoMensaje=findViewById(R.id.editTextSMSLayInfo);

        nameEmpresa.setText(title);

        txtMensaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (auxSendMsg==0){
                    textoMensaje.setVisibility(View.VISIBLE);

                    Toast.makeText(ProductDetailsActivity.this, "Escriba su mensaje", Toast.LENGTH_SHORT).show();
                    auxSendMsg=1;
                }
                if (auxSendMsg==1){
                    try{
                        SmsManager sms = SmsManager.getDefault();

                        sms.sendTextMessage(telefono, null,
                                textoMensaje.getText().toString(), null, null);

                        Toast.makeText(getApplicationContext(), "Mensaje enviado", Toast.LENGTH_SHORT).show();
                        textoMensaje.setVisibility(View.GONE);

                    }catch (Exception e){
                        Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        txtMapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ProductDetailsActivity.this, mapa, Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(mapa)));
            }
        });
        txtLlamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ProductDetailsActivity.this, telefono, Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+telefono)));
            }
        });
        txtWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ProductDetailsActivity.this, web, Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(web)));
            }
        });


    }

    public void setRating(int pos){
        for (int i=0;i<linRateSatrRating.getChildCount();i++){
            ImageView imgStar=(ImageView) linRateSatrRating.getChildAt(i);
            imgStar.setImageTintList(ColorStateList.valueOf(Color.parseColor("#bebebe")));
            if (i<=pos){
                imgStar.setImageTintList(ColorStateList.valueOf(Color.parseColor("#ffbb00")));
            }
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
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.idNavSearch) {
            return true;
        }
        else if (id == android.R.id.home){
            finish();
            return true;
        }
        else if (id == R.id.idNavCart){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
