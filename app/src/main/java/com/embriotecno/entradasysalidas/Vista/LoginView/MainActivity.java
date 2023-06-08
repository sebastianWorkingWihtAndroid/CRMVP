package com.embriotecno.entradasysalidas.Vista.LoginView;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.embriotecno.entradasysalidas.Presentador.LoginPresenter.PresentadorLogin;
import com.embriotecno.entradasysalidas.R;
import com.embriotecno.entradasysalidas.Vista.RegisterView.RegisterView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private EditText ETextUsuario, ETextClave;
    private PresentadorLogin presentadorLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        presentadorLogin = new PresentadorLogin(this, mAuth, mDatabase);
        ETextUsuario = findViewById(R.id.ETextCorreoR);
        ETextClave = findViewById(R.id.ETextClaveR);
        Button BtnIngresar = findViewById(R.id.BtnIngresar);
        BtnIngresar.setOnClickListener(this);
        Button BtnRegistrarA = findViewById(R.id.BtnRegistrarR);
        BtnRegistrarA.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.BtnIngresar:
                String correo = ETextUsuario.getText().toString().trim();
                String pass = ETextClave.getText().toString().trim();
                presentadorLogin.iniciarSesion(correo, pass);
                break;

            case R.id.BtnRegistrarR:
                Intent intent = new Intent(MainActivity.this, RegisterView.class);
                startActivity(intent);
                break;
        }
    }
}