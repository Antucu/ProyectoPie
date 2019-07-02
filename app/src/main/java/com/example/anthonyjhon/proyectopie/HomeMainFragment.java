///////////////////Proyecto Pie//////////////////////

//Integrantes
//  Anthony Jhon Apaza Chambi
//  Alvaro Freed Apaza Sosa
//  Alonso Jesus Conde Sanchez
//  Jose David Fuentes Cuadros

//  V1.0

package com.example.anthonyjhon.proyectopie;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeMainFragment extends Fragment {

    ///////////DataBase
    FirebaseFirestore fireBaseStore;
    ///////////DataBase

    ///////////Recycler View Categorias
    RecyclerView categoriaRecyclerViewHomeMain;
    HomePageAdapter adapterHome;

    ///////////Recycler View Categorias

    ///////////Category Lay
    RecyclerView categoriaRecyclerView;
    RecyclerCategoriaAdapter categoriaAdapter;
    List<RecyclerCategoriaModel> listCatModelRecycler;
    ///////////Category Lay

    ///////////Banner
    ViewPager viewBannerPromocion;
    SliderAdapterPromocion sliderBannerAdapter;
    List<SliderModelPromocion> listSlideModelPromo;
    private int current=2;

    private Timer timer;
    final private long DELTA_TIME=3000;
    final private long PERIODO_TIME=3000;
    ///////////Banner

    ////////////////Scroll Product
    private TextView txtTitleScroll;
    private Button btnAllScrollLay;
    private RecyclerView recyScrollProductItem;
    private HorizontalScrollProductAdapter adpaterHorLayProduct;
    List<HorizontalScrollProductModel> listHoriProductScroll;
    ////////////////Scroll Product

    ////////////////Grid Lay Product
    private TextView txtTitleScrollGrid;
    private Button btnAllScrollLayGrid;
    private RecyclerView gridViewProduct;
    private GridProductLayoutAdapter adapterHorLayProductGrid;
    List<HorizontalScrollProductModel> listGridProductScroll;
    ////////////////Grid Lay Product

    public HomeMainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home_main, container, false);

        ////////Categoria View

        categoriaRecyclerView= view.findViewById(R.id.idCategoria_recyclerView);
        LinearLayoutManager layManager= new LinearLayoutManager(getActivity());
        layManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        categoriaRecyclerView.setLayoutManager(layManager);

        listCatModelRecycler= new ArrayList<RecyclerCategoriaModel>();

        categoriaAdapter=new RecyclerCategoriaAdapter(listCatModelRecycler);
        categoriaRecyclerView.setAdapter(categoriaAdapter);

        ////////Base de datos categoria
        fireBaseStore= FirebaseFirestore.getInstance();
        fireBaseStore.collection("REGIONES").orderBy("index").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            for (QueryDocumentSnapshot documentSnapshots: task.getResult()){
                                listCatModelRecycler.add(new RecyclerCategoriaModel(1,documentSnapshots.get("icon").toString()
                                        ,documentSnapshots.get("name").toString()));
                            }
                            categoriaAdapter.notifyDataSetChanged();
                        }
                        else{
                            String error=task.getException().getMessage();
                            Toast.makeText(getContext(),error,Toast.LENGTH_LONG).show();
                        }
                    }
                });

        ////////Base de datos categoria
        ///////Categoria View

        //////////Banner
        viewBannerPromocion=(ViewPager) view.findViewById(R.id.idBannerSlide_viewPager);

        listSlideModelPromo=new ArrayList<SliderModelPromocion>();



        ViewPager.OnPageChangeListener onpageChager= new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                current=position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (state==ViewPager.SCROLL_STATE_IDLE){
                    pageLooper();
                }
            }
        };

        viewBannerPromocion.addOnPageChangeListener(onpageChager);

        starBannerShow();

        viewBannerPromocion.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                pageLooper();
                stopBannerShow();
                if (motionEvent.getAction()==MotionEvent.ACTION_UP){
                    starBannerShow();
                }
                return false;
            }
        });
        sliderBannerAdapter=new SliderAdapterPromocion(listSlideModelPromo);
        ////////Base de datos Banner
        fireBaseStore.collection("CATEGORIES").document("HOME").collection("TOP_DEALS")
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    for (QueryDocumentSnapshot documentSnapshots: task.getResult()){
                        long no_of_banners=(long)documentSnapshots.get("no_of_banners");
                        for (long i=1; i<=no_of_banners;i++){
                            listSlideModelPromo.add(new SliderModelPromocion(documentSnapshots.get("banner_"+i).toString()));
                        }
                    }
                    sliderBannerAdapter.notifyDataSetChanged();
                }
                else{
                    String error=task.getException().getMessage();
                    Toast.makeText(getContext(),error,Toast.LENGTH_LONG).show();
                }
            }
        });
        ////////Base de datos banner



        viewBannerPromocion.setAdapter(sliderBannerAdapter);
        viewBannerPromocion.setClipToPadding(false);
        viewBannerPromocion.setPageMargin(20);
        viewBannerPromocion.setCurrentItem(current);

        /////////////////Baner

        ////////////////Scroll Product

        txtTitleScroll= (TextView) view.findViewById(R.id.idTextTitleProductHorizontalScrollLayout);
        btnAllScrollLay=(Button) view.findViewById(R.id.idButtonHoriLayoutTitle);
        btnAllScrollLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getActivity(), ViewAllActivity.class);
                i.putExtra("layout_code",1);
                startActivity(i);
            }
        });
        recyScrollProductItem=(RecyclerView) view.findViewById(R.id.idRecylcerLayotHorizontalScroll);
        listHoriProductScroll= new ArrayList<HorizontalScrollProductModel>();
        listGridProductScroll= new ArrayList<HorizontalScrollProductModel>();

        adpaterHorLayProduct=new HorizontalScrollProductAdapter(listHoriProductScroll);
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        recyScrollProductItem.setLayoutManager(linearLayoutManager);
        recyScrollProductItem.setAdapter(adpaterHorLayProduct);

        ////////Base de datos Scroll Horizontal
        fireBaseStore.collection("CATEGORIES").document("HOME").collection("NOVEDADES")
                .orderBy("index").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    for (QueryDocumentSnapshot documentSnapshots: task.getResult()){
                        float precio=Float.parseFloat(documentSnapshots.get("precio")+"");
                        listHoriProductScroll.add(new HorizontalScrollProductModel(documentSnapshots.get("link").toString(),
                                documentSnapshots.get("title").toString(),
                                documentSnapshots.get("descripcion").toString(),
                                String.valueOf(precio)));
                    }
                    adpaterHorLayProduct.notifyDataSetChanged();
                }
                else{
                    String error=task.getException().getMessage();
                    Toast.makeText(getContext(),error,Toast.LENGTH_LONG).show();
                }
            }
        });
        ////////Base de datos Scroll Horizontal

        ////////////////Scroll Product

        ////////////GridLayProduct
        txtTitleScrollGrid= (TextView) view.findViewById(R.id.txtTitleGridLayoutProduct);
        btnAllScrollLayGrid=(Button) view.findViewById(R.id.btnAllGridProductLayoutProduct);
        btnAllScrollLayGrid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getActivity(), ViewAllActivity.class);
                i.putExtra("layout_code",0);
                startActivity(i);
            }
        });
        gridViewProduct=(RecyclerView) view.findViewById(R.id.idRecyViewGridLayout);

        adapterHorLayProductGrid=new GridProductLayoutAdapter(listGridProductScroll);
        gridViewProduct.setAdapter(adapterHorLayProductGrid);
        gridViewProduct.setLayoutManager(new GridLayoutManager(view.getContext(),2));

        ////////Base de datos Grid Product
        fireBaseStore.collection("CATEGORIES").document("HOME").collection("NOVEDADES")
                .orderBy("index").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    for (QueryDocumentSnapshot documentSnapshots: task.getResult()){
                        float precio=Float.parseFloat(documentSnapshots.get("precio")+"");
                        listGridProductScroll.add(new HorizontalScrollProductModel(documentSnapshots.get("link").toString(),
                                documentSnapshots.get("title").toString(),
                                documentSnapshots.get("descripcion").toString(),
                                String.valueOf(precio)));
                    }
                    adapterHorLayProductGrid.notifyDataSetChanged();
                }
                else{
                    String error=task.getException().getMessage();
                    Toast.makeText(getContext(),error,Toast.LENGTH_LONG).show();
                }
            }
        });
        ////////Base de datos Grid Proudct
        ////////////GridLayProduct

        ////////////Recicler view de Categorias

        categoriaRecyclerViewHomeMain=(RecyclerView) view.findViewById(R.id.recyViewMainHomeFragCategorias);

        /*LinearLayoutManager lin= new LinearLayoutManager(getContext());
        lin.setOrientation(LinearLayoutManager.VERTICAL);
        categoriaRecyclerViewHomeMain.setLayoutManager(lin);

        final List<HomePageModel> listPageModelCategorias=new ArrayList<>();
        final HomePageAdapter adapterHomeCategorias=new HomePageAdapter(listPageModelCategorias);

        categoriaRecyclerViewHomeMain.setAdapter(adapterHomeCategorias);

        for (int i=0;i<3;i++){;
            final List<RecyclerCategoriaModel> auxList=new ArrayList<>();
            listPageModelCategorias.add(new HomePageModel(2,"Regiones",auxList,null));
            ///////Base de datos
            fireBaseStore.collection("CATEGORIES").orderBy("index").get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()){
                                for (QueryDocumentSnapshot documentSnapshots: task.getResult()){
                                    auxList.add(new RecyclerCategoriaModel(documentSnapshots.get("icon").toString()
                                            ,documentSnapshots.get("categoryName").toString()));
                                }
                                adapterHomeCategorias.notifyDataSetChanged();
                            }
                            else{
                                String error=task.getException().getMessage();
                                Toast.makeText(getContext(),error,Toast.LENGTH_LONG).show();
                            }
                        }
                    });
            ///////Base de datos
        }
        adapterHomeCategorias.notifyDataSetChanged();*/

        //listProductCategoria=new ArrayList<HorizontalScrollProductModel>();

        LinearLayoutManager lin= new LinearLayoutManager(getContext());
        lin.setOrientation(LinearLayoutManager.VERTICAL);
        categoriaRecyclerViewHomeMain.setLayoutManager(lin);

        final List<HomePageModel> listPageModel=new ArrayList<HomePageModel>();
        adapterHome=new HomePageAdapter(listPageModel);

        for (int i=0;i<2;i++){
            final List<HorizontalScrollProductModel> listProductCategoria=new ArrayList<HorizontalScrollProductModel>();
            listPageModel.add(new HomePageModel(2,"Dulces","#751375",listProductCategoria,null));

            fireBaseStore.collection("CATEGORIES").document("HOME").collection("NOVEDADES")
                    .orderBy("index").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()){
                        for (QueryDocumentSnapshot documentSnapshots: task.getResult()){
                            float precio=Float.parseFloat(documentSnapshots.get("precio")+"");
                            listProductCategoria.add(new HorizontalScrollProductModel(documentSnapshots.get("link").toString(),
                                    documentSnapshots.get("title").toString(),
                                    documentSnapshots.get("descripcion").toString(),
                                    String.valueOf(precio)));
                        }
                        adapterHome.notifyDataSetChanged();
                    }
                    else{
                        String error=task.getException().getMessage();
                        Toast.makeText(getContext(),error,Toast.LENGTH_LONG).show();
                    }
                }
            });
        }

        categoriaRecyclerViewHomeMain.setAdapter(adapterHome);
        ////////Base de datos Scroll Horizontal

        adapterHome.notifyDataSetChanged();
        ////////////Recicler view de Categorias

        return view;
    }

    private void pageLooper(){
        if (current==listSlideModelPromo.size()){
            current=2;
            viewBannerPromocion.setCurrentItem(current,false);
        }
        if (current==1){
            current=listSlideModelPromo.size()-3;
            viewBannerPromocion.setCurrentItem(current,false);
        }
    }

    private void starBannerShow(){
        final Handler hand= new Handler();
        final Runnable update= new Runnable() {
            @Override
            public void run() {
                if (current>=listSlideModelPromo.size()){
                    current=1;
                }
                viewBannerPromocion.setCurrentItem(current++,true);
            }
        };
        timer= new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                hand.post(update);
            }
        },DELTA_TIME,PERIODO_TIME);
    }

    private void stopBannerShow(){
        timer.cancel();
    }


}
