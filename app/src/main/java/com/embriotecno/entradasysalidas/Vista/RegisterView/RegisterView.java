package com.embriotecno.entradasysalidas.Vista.RegisterView;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.embriotecno.entradasysalidas.Presentador.RegisterPresenter.PresentadorRegistro;
import com.embriotecno.entradasysalidas.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterView extends AppCompatActivity implements View.OnClickListener {


    private EditText ETextCorreo, ETextClave, ETextNombreCompleto, ETextUsuario;
    private PresentadorRegistro presentadorRegistro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_view);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();

        presentadorRegistro = new PresentadorRegistro(this,mAuth,mDatabase);
        ETextCorreo = findViewById(R.id.ETextCorreoR);
        ETextClave = findViewById(R.id.ETextClaveR);
        ETextNombreCompleto = findViewById(R.id.ETextNombreCompletoR);
        ETextUsuario = findViewById(R.id.ETextNombreUsuarioR);
        Button button = findViewById(R.id.BtnRegistrar);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.BtnRegistrar:
                String correo = ETextCorreo.getText().toString().trim();
                String clave = ETextClave.getText().toString().trim();
                String ncompleto = ETextNombreCompleto.getText().toString().trim();
                String usuario = ETextUsuario.getText().toString().trim();
                presentadorRegistro.signUpUser(correo,clave,ncompleto,usuario);
                break;
        }

    }

}