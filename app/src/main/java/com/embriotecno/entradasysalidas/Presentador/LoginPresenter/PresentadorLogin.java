package com.embriotecno.entradasysalidas.Presentador.LoginPresenter;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;

import com.embriotecno.entradasysalidas.Vista.HomeView.HomeView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

public class PresentadorLogin {

    private Context mContext;

    private String TAG="PresentadorLogin";
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    public PresentadorLogin(Context mContext, FirebaseAuth mAuth, DatabaseReference mDatabase) {
        this.mContext = mContext;
        this.mAuth = mAuth;
        this.mDatabase = mDatabase;
    }

    public void iniciarSesion(String email, String clave){
        mAuth.signInWithEmailAndPassword(email, clave)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Log.w(TAG, "Correctamente");
                            FirebaseUser user=mAuth.getCurrentUser();
                            mDatabase.child("Usuarios").setValue(task.getResult().getUser().getUid());
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
