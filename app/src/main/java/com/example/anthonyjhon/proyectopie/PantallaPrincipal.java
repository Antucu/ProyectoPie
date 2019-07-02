///////////////////Proyecto Pie//////////////////////

//Integrantes
//  Anthony Jhon Apaza Chambi
//  Alvaro Freed Apaza Sosa
//  Alonso Jesus Conde Sanchez
//  Jose David Fuentes Cuadros

//  V1.0

package com.example.anthonyjhon.proyectopie;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

public class PantallaPrincipal extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final int HOME_FRAGMENT=0;
    private static final int CART_FRAGMENT=1;

    FrameLayout main_frameLayout;
    private static int currentFragment;

    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_principal);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().getItem(0).setChecked(true);

        main_frameLayout=(FrameLayout) findViewById(R.id.main_frameLayout);
        setDefaulltFragment(new HomeMainFragment(),HOME_FRAGMENT);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        /*if (currentFragment==HOME_FRAGMENT){
            getMenuInflater().inflate(R.menu.pantalla_principal, menu);
        }*/
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
        else if (id == R.id.idNavNotificaciones){
            NotificationCompat.Builder notifiBuilder=new NotificationCompat.Builder(this).setSmallIcon(R.mipmap.ic_icon_logo)
                    .setContentTitle("Pie").setContentText("Llego su pedido");

            NotificationManager notificationManager=(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(1,notifiBuilder.build());
            return true;
        }
        else if (id == R.id.idNavCart){
            //myCart();
            startActivity(new Intent(this,ViewAllActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_idHome) {
            // Handle the camera action
            //setDefaulltFragment(new HomeMainFragment(),HOME_FRAGMENT);
        } else if (id == R.id.nav_idCuenta) {

        } else if (id == R.id.nav_idCanasta) {

        } else if (id == R.id.nav_idFavorite) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    void setDefaulltFragment(Fragment fragment, int fragmentNo){
        //currentFragment=fragmentNo;
        FragmentTransaction fragTransanccion=getSupportFragmentManager().beginTransaction();
        fragTransanccion.replace(main_frameLayout.getId(),fragment);
        fragTransanccion.commit();
    }
    private void myCart(){
        //invalidateOptionsMenu();
        //setDefaulltFragment(new MyCartFragment(),CART_FRAGMENT);
        //navigationView.getMenu().getItem(3).setChecked(true);
    }
}
