///////////////////Proyecto Pie//////////////////////

//Integrantes
//  Anthony Jhon Apaza Chambi
//  Alvaro Freed Apaza Sosa
//  Alonso Jesus Conde Sanchez
//  Jose David Fuentes Cuadros

//  V1.0

package com.example.anthonyjhon.proyectopie;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.transition.TransitionManager;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;


/**
 * A simple {@link Fragment} subclass.
 */
public class ResetPasswordFragment extends Fragment {

    TextView textDetForgot;
    TextView textConfimrCorreo;
    TextView textBtnRegresar;
    EditText txtResetCorreo;
    ImageView imgMail;

    Button btnResetContrasena;
    ProgressBar progressbar;

    ViewGroup viewGroup;
    FrameLayout parentFrameLayout;
    FirebaseAuth firebaseauth;

    public ResetPasswordFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_reset_password, container, false);

        textDetForgot=(TextView) view.findViewById(R.id.txtForgotDetalleEmail);
        textConfimrCorreo=(TextView) view.findViewById(R.id.txtConfirmViewCorreo);
        txtResetCorreo=(EditText) view.findViewById(R.id.txtResetCorreoForgot);
        imgMail=(ImageView) view.findViewById(R.id.imgEmailForgot);
        textBtnRegresar=(TextView) view.findViewById(R.id.txtBtnGoBackForgot);

        btnResetContrasena=(Button) view.findViewById(R.id.btnForgotSend);
        progressbar=(ProgressBar) view.findViewById(R.id.idProgressbar);

        viewGroup=view.findViewById(R.id.password_forgot_container) ;
        parentFrameLayout=(FrameLayout) getActivity().findViewById(R.id.register_framelayout);
        firebaseauth=(FirebaseAuth) FirebaseAuth.getInstance();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnResetContrasena.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                TransitionManager.beginDelayedTransition(viewGroup);
                textDetForgot.setVisibility(View.GONE);

                TransitionManager.beginDelayedTransition(viewGroup);
                imgMail.setVisibility(View.VISIBLE);
                progressbar.setVisibility(View.VISIBLE);
                if (Patterns.EMAIL_ADDRESS.matcher(txtResetCorreo.getText().toString()).matches()==false){
                    TransitionManager.beginDelayedTransition(viewGroup);
                    imgMail.setVisibility(View.GONE);
                    progressbar.setVisibility(View.GONE);
                    Toast.makeText(getActivity().getApplicationContext(),"Ingrese nuevamente el correo",Toast.LENGTH_SHORT).show();
                }else{
                    TransitionManager.beginDelayedTransition(viewGroup);
                    textDetForgot.setVisibility(View.GONE);

                    TransitionManager.beginDelayedTransition(viewGroup);
                    imgMail.setVisibility(View.VISIBLE);
                    progressbar.setVisibility(View.VISIBLE);

                    firebaseauth.sendPasswordResetEmail(txtResetCorreo.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                        ScaleAnimation scaleAnimation=new ScaleAnimation(1,0,1,0);
                                        scaleAnimation.setDuration(100);
                                        scaleAnimation.setInterpolator(new AccelerateInterpolator());
                                        scaleAnimation.setRepeatMode(Animation.REVERSE);
                                        scaleAnimation.setRepeatCount(1);

                                        textDetForgot.setText("Email sent succeful");
                                        textDetForgot.setTextColor(getResources().getColor(R.color.succesGreen));
                                        TransitionManager.beginDelayedTransition(viewGroup);
                                        textDetForgot.setVisibility(View.VISIBLE);
                                        Toast.makeText(getActivity(),"Email sent succesful",Toast.LENGTH_SHORT).show();
                                    }
                                    else{
                                        String error=task.getException().getMessage();
                                        textDetForgot.setText(error);
                                        textDetForgot.setTextColor(getResources().getColor(R.color.succesGreen));
                                        TransitionManager.beginDelayedTransition(viewGroup);
                                        textDetForgot.setVisibility(View.VISIBLE);
                                    }
                                    progressbar.setVisibility(View.GONE);
                                }
                            });
                }
            }
        });

        textBtnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setFragment(new SingInFragment());
            }
        });
    }

    public void setFragment(Fragment fragment){
        FragmentTransaction fragTransanccion=getActivity().getSupportFragmentManager().beginTransaction();
        fragTransanccion.setCustomAnimations(R.anim.slide_from_rigth,R.anim.slideout_from_left);
        fragTransanccion.replace(parentFrameLayout.getId(),fragment);
        fragTransanccion.commit();
    }

}
