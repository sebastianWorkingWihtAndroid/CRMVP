package com.embriotecno.entradasysalidas.Vista.RegisterView;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.embriotecno.entradasysalidas.Presenter.RegisterContractPresenter;
import com.embriotecno.entradasysalidas.Presenter.RegisterPresenter;
import com.embriotecno.entradasysalidas.R;

public class MainActivityRegister extends AppCompatActivity implements MainActivityRegisterContract{

    private EditText etNombres;
    private EditText etUser;
    private EditText etClave;
    private Button btnRegister;
    private RegisterContractPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_view);

        // Inicializar los componentes de la interfaz
        etNombres = findViewById(R.id.ETextNombreCompletoR);
        etUser = findViewById(R.id.ETextCorreoR);
        etClave = findViewById(R.id.ETextClaveR);
        btnRegister = findViewById(R.id.BtnRegistrar);
        // Crear una instancia del Presentador y pasar la vista (this)
        presenter = new RegisterPresenter(this);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener los datos ingresados por el usuario
                String nombres = etNombres.getText().toString();
                String user = etUser.getText().toString();
                String clave = etClave.getText().toString();

                // Llamar al método del Presentador para realizar el registro
                presenter.registerUser(nombres, user, clave);
            }
        });
    }

    @Override
    public void showRegistrationSuccess() {
        // Mostrar mensaje de registro exitoso o realizar alguna acción adicional
        Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showRegistrationError(String errorMessage) {
        // Mostrar mensaje de error en el registro
        Toast.makeText(this, "Error en el registro: " + errorMessage, Toast.LENGTH_SHORT).show();

    }
}