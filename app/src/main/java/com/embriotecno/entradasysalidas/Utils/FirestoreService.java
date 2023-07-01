package com.embriotecno.entradasysalidas.Utils;

import com.embriotecno.entradasysalidas.Modelo.ModelUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class FirestoreService {

    public interface Callback {
        void onSuccess();

        void onError(String errorMessage);
    }

    public void registerUser(ModelUser userModel, Callback callback) {
        // Obtener una referencia a la colección "users" en Firestore
        CollectionReference usersRef = FirebaseFirestore.getInstance().collection("users");

        // Generar un ID único para el nuevo documento de usuario
        String userId = usersRef.document().getId();

        // Establecer los datos del usuario en el documento correspondiente
        usersRef.document(userId)
                .set(userModel)
                .addOnSuccessListener(aVoid -> {
                    // Registro exitoso, llamar a callback.onSuccess()
                    callback.onSuccess();
                })
                .addOnFailureListener(e -> {
                    // Error en el registro, llamar a callback.onError() con el mensaje de error
                    callback.onError(e.getMessage());
                });


    }

    public void updateUser(ModelUser userModel, Callback callback) {
        // Lógica para actualizar los datos de un usuario en Firestore
        // ...
    }

    public void deleteUser(String userId, Callback callback) {
        // Lógica para eliminar un usuario de Firestore
        // ...
    }

    public void getUser(String userId, Callback callback) {
        // Lógica para obtener los datos de un usuario de Firestore
        // ...
    }
}

