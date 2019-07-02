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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;


/**
 * A simple {@link Fragment} subclass.
 */
public class SingUpContrasenaFragment extends Fragment {


    private String correo="";

    private EditText txtContrasena;
    private EditText txtConfirmContrasena;
    private TextInputLayout layLogContrasena;

    private Button btnRegistro;
    private ImageButton btnClose;

    private FirebaseAuth fireBaseAuth;
    private FirebaseFirestore fireBaseFireStore;

    public SingUpContrasenaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_sing_up_contrasena, container, false);
        txtContrasena=(EditText) view.findViewById(R.id.txtLogContrasena);

        btnRegistro= (Button) view.findViewById(R.id.btnSingUp);
        btnClose=(ImageButton)view.findViewById(R.id.btnCloseSingIpContrasena);

        fireBaseAuth=(FirebaseAuth) FirebaseAuth.getInstance();
        fireBaseFireStore=(FirebaseFirestore) FirebaseFirestore.getInstance();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        final EditText auxCorreo=(EditText) view.findViewById(R.id.txtLogCorreo);
        super.onViewCreated(view, savedInstanceState);
        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txtContrasena.getText().toString().equals(txtConfirmContrasena.getText().toString())){
                    fireBaseAuth.createUserWithEmailAndPassword(correo,txtContrasena.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()){
                                        Toast.makeText(getActivity(),"Succes",Toast.LENGTH_SHORT).show();
                                        Intent i = new Intent(getActivity(), PantallaPrincipal.class);
                                        startActivity(i);
                                        getActivity().finish();
                                    }
                                    else {
                                        String error=task.getException().getMessage();
                                        Toast.makeText(getActivity(),error,Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
                else{
                    Toast.makeText(getActivity().getApplicationContext(),"Las contraseñas no son las mismas",Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainRegister();
            }
        });
    }

    public void setCorreo(String correoLogIn){
        this.correo=correoLogIn;
    }

    private void MainRegister(){
        Intent i= new Intent(getActivity(),RegisterActivity.class);
        startActivity(i);
        getActivity().finish();
    }

}
