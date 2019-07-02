///////////////////Proyecto Pie//////////////////////

//Integrantes
//  Anthony Jhon Apaza Chambi
//  Alvaro Freed Apaza Sosa
//  Alonso Jesus Conde Sanchez
//  Jose David Fuentes Cuadros

//  V1.0

package com.example.anthonyjhon.proyectopie;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

/**
 * A simple {@link Fragment} subclass.
 */

public class SingUpEmailFragment extends Fragment {
    FrameLayout parentFrameLayout;
    Button btnSiguiente;
    ImageButton imgBtnClose;
    EditText textLogCorreo;
    TextInputLayout layLogCorreo;
    FirebaseAuth fireBaseAuth;

    public SingUpEmailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_sing_up_email, container, false);
        parentFrameLayout=(FrameLayout) getActivity().findViewById(R.id.register_framelayout);
        textLogCorreo=(EditText) view.findViewById(R.id.txtLogCorreo);
        btnSiguiente=(Button) view.findViewById(R.id.btnLogSiguiente);
        imgBtnClose=(ImageButton) view.findViewById(R.id.btnCloseSingUpCorreo);

        fireBaseAuth=(FirebaseAuth) FirebaseAuth.getInstance();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Patterns.EMAIL_ADDRESS.matcher(textLogCorreo.getText().toString()).matches()==false){
                    layLogCorreo.setError("Correo Invalido");
                    Toast.makeText(getActivity().getApplicationContext(),"Ingrese nuevamente el correo",Toast.LENGTH_SHORT).show();
                }else{
                    layLogCorreo.setError(null);
                    SingUpContrasenaFragment aux= new SingUpContrasenaFragment();
                    aux.setCorreo(textLogCorreo.getText().toString());
                    setFragment(aux);
                }
            }
        });

        imgBtnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainRegister();
            }
        });
    }

    public void setFragment(Fragment fragment){
        FragmentTransaction fragTransanccion=getActivity().getSupportFragmentManager().beginTransaction();
        fragTransanccion.setCustomAnimations(R.anim.slide_from_rigth,R.anim.slideout_from_left);
        fragTransanccion.replace(parentFrameLayout.getId(),fragment);
        fragTransanccion.commit();
    }
    private void MainRegister(){
        Intent i= new Intent(getActivity(),RegisterActivity.class);
        startActivity(i);
        getActivity().finish();
    }
}
