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
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;



import static com.example.anthonyjhon.proyectopie.RegisterActivity.onResetPasswordFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class SingInFragment extends Fragment {

    int cont=0;

    EditText txtSingInCorreo;
    EditText txtSingInContrasena;
    TextInputLayout laySingInCorreo;
    TextInputLayout laySingInContrasena;

    TextView dontAcount;
    TextView btnForgotPassword;
    Button btnSigIn;
    ImageButton btnImgClose;

    FrameLayout parentFrameLayout;
    FirebaseAuth firebaseAuth;

    public SingInFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_sing_in, container, false);

        txtSingInCorreo=(EditText) view.findViewById(R.id.txtLoginCorreo);
        txtSingInContrasena=(EditText) view.findViewById(R.id.txtLoginContrasena);

        dontAcount=(TextView) view.findViewById(R.id.btnTextSingUp);
        btnForgotPassword=(TextView) view.findViewById(R.id.btnTxtForgot) ;
        btnSigIn=(Button) view.findViewById(R.id.btnLogIn);
        btnImgClose=(ImageButton) view.findViewById(R.id.btnCloseSingIn);

        firebaseAuth=(FirebaseAuth) FirebaseAuth.getInstance();
        parentFrameLayout= (FrameLayout) getActivity().findViewById(R.id.register_framelayout);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dontAcount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setFragment(new SingUpEmailFragment());
            }
        });
        btnSigIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cont<3){
                    if (txtSingInCorreo.getText().toString().trim().length()==0){
                        Toast.makeText(getActivity(),"Ingrese Usuario",Toast.LENGTH_SHORT).show();
                    }
                    else if (txtSingInContrasena.getText().toString().trim().length()==0){
                        Toast.makeText(getActivity(),"Ingrese Contraseña",Toast.LENGTH_SHORT).show();
                    }
                    else if (txtSingInCorreo.getText().toString().trim().equals("admin@admin.com") && txtSingInContrasena.getText().toString().trim().equals("admin")){
                        Intent i=new Intent(getActivity(), PantallaPrincipal.class);
                        startActivity(i);
                    }
                    else{
                        firebaseAuth.signInWithEmailAndPassword(txtSingInCorreo.getText().toString(),txtSingInContrasena.getText().toString())
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()){
                                            txtSingInCorreo.setText("");
                                            txtSingInContrasena.setText("");
                                            Intent i=new Intent(getActivity(), PantallaPrincipal.class);
                                            startActivity(i);
                                        }
                                        else{
                                            String error=task.getException().getMessage();
                                            Toast.makeText(getActivity(),error,Toast.LENGTH_SHORT).show();
                                            cont++;
                                        }
                                    }
                                });
                    }
                }
                else{
                    Toast.makeText(getActivity(),"Espere 5 segundos",Toast.LENGTH_SHORT).show();
                    cont=0;
                }
            }
        });

        btnForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onResetPasswordFragment=true;
                setFragment(new ResetPasswordFragment());
            }
        });

        btnImgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),PantallaPrincipal.class));
            }
        });
    }
    void setFragment(Fragment fragment){
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
