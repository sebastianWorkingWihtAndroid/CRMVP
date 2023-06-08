package com.embriotecno.entradasysalidas.Presentador.RegisterPresenter;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.embriotecno.entradasysalidas.Vista.HomeView.HomeView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

import java.util.HashMap;
import java.util.Map;

public class PresentadorRegistro {

    private Context mContext;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private String TAG="PresentadorRegistro";

    public PresentadorRegistro(Context mContext, FirebaseAuth mAuth, DatabaseReference mDatabase) {
        this.mContext = mContext;
        this.mAuth = mAuth;
        this.mDatabase = mDatabase;
    }

    public void signUpUser(String email, String clave,final String nombrecompleto, final String username){

        ProgressDialog dialog = new ProgressDialog(mContext);
        dialog.setMessage("Registrando...");
        dialog.setCancelable(false);
        dialog.show();

        mAuth.signInWithEmailAndPassword(email, clave)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Log.w(TAG, "Correctamente");
                            FirebaseUser user=mAuth.getCurrentUser();
                            Map<String,Object> crearUsuario = new HashMap<>();
                            crearUsuario.put("nombre",nombrecompleto);
                            crearUsuario.put("username",username);
                            crearUsuario.put("email",email);
                            mDatabase.child("Usuarios").child(task.getResult().getUser()
                                    .getUid()).updateChildren(crearUsuario);

                            Intent intent = new Intent(mContext, HomeView.class);
                            mContext.startActivity(intent);

                        }else {
                            Log.w(TAG,"Falló");
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(mContext, "Error", Toast.LENGTH_SHORT).show();
                    }
                });

    }
    



}
