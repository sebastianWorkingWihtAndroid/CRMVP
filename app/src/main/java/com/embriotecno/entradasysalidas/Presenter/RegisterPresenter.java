package com.embriotecno.entradasysalidas.Presenter;

import com.embriotecno.entradasysalidas.Modelo.ModelUser;
import com.embriotecno.entradasysalidas.Utils.FirestoreService;
import com.embriotecno.entradasysalidas.Vista.RegisterView.MainActivityRegisterContract;

public class RegisterPresenter implements RegisterContractPresenter {

    private MainActivityRegisterContract view;
    private FirestoreService firestoreService;

    public RegisterPresenter(MainActivityRegisterContract view) {
        this.view = view;
        this.firestoreService = new FirestoreService();
    }

    @Override
    public void registerUser(String nombres, String user, String clave) {
        // Aquí puedes realizar la lógica de validación de los datos si es necesario

        // Crear un nuevo objeto ModelUser con los datos ingresados
        ModelUser userModel = new ModelUser();
        userModel.setNombres(nombres);
        userModel.setUser(user);
        userModel.setClave(clave);

        // Llamar al servicio de Firestore para realizar el registro
        firestoreService.registerUser(userModel, new FirestoreService.Callback() {
            @Override
            public void onSuccess() {
                view.showRegistrationSuccess();
            }

            @Override
            public void onError(String errorMessage) {
                view.showRegistrationError(errorMessage);
            }
        });

    }


}
