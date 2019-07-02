///////////////////Proyecto Pie//////////////////////

//Integrantes
//  Anthony Jhon Apaza Chambi
//  Alvaro Freed Apaza Sosa
//  Alonso Jesus Conde Sanchez
//  Jose David Fuentes Cuadros

//  V1.0

package com.example.anthonyjhon.proyectopie;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.FrameLayout;

public class RegisterActivity extends AppCompatActivity {

    FrameLayout register_layout;
    public static boolean onResetPasswordFragment=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        register_layout=(FrameLayout) findViewById(R.id.register_framelayout);
        setDefaulltFragment(new SingInFragment());
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK){
            if (onResetPasswordFragment){
                onResetPasswordFragment=false;
                setFragment(new SingInFragment());
                return false;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    void setDefaulltFragment(Fragment fragment){
        FragmentTransaction fragTransanccion=getSupportFragmentManager().beginTransaction();
        fragTransanccion.replace(register_layout.getId(),fragment);
        fragTransanccion.commit();
    }
    public void setFragment(Fragment fragment){
        FragmentTransaction fragTransanccion=getSupportFragmentManager().beginTransaction();
        fragTransanccion.setCustomAnimations(R.anim.slide_from_left,R.anim.slideout_from_rigth);
        fragTransanccion.replace(register_layout.getId(),fragment);
        fragTransanccion.commit();
    }
}
