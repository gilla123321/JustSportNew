package com.itai.justrun.firebase;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;
import com.itai.justrun.AppUser;
import com.itai.justrun.Task;
import com.itai.justrun.taskData;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;


public class FirebaseHandler {
    public interface TaskCallback {
        void onTaskLoaded(List<Task> tasks);
        void onError(String error);
    }

    public interface SuccessCallbackInterface {
        void onResponse(boolean success);
    }


    public static void navigate(SuccessCallbackInterface callback) {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser != null) {
            AppUser.sherdInstance().setPhone(currentUser.getPhoneNumber());
            callback.onResponse(true);
        } else {
            callback.onResponse(false);
        }
    }

    public static String getPhoneNumber() {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        return currentUser.getPhoneNumber();
    }

    public static void logout(Context context) {
        AppUser.sherdInstance().resetData();

        FirebaseAuth.getInstance().signOut();
    }

    public static void addTask(Task task, final SuccessCallbackInterface callback) {

        CollectionReference usersCollection = FirebaseFirestore.getInstance().collection(AppUser.sherdInstance().getPhone());
        usersCollection.document(task.getId())
                .set(task)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        callback.onResponse(true);
                    }
                })

                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Log.e("Debug", "Error writing document", e);
                        callback.onResponse(false);
                    }
                });
    }

    public static void getTasks(TaskCallback callback) {
        CollectionReference collection = FirebaseFirestore.getInstance().collection(AppUser.sherdInstance().getPhone());
        collection.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                List<Task> tasks = new ArrayList<>();
                for (QueryDocumentSnapshot document : task.getResult()) {
                    Task td = document.toObject(Task.class); // Ensure your taskData class fields match the Firestore document
                    tasks.add(td);
                }
                callback.onTaskLoaded(tasks);
            } else {
                callback.onError(task.getException().getMessage());
            }
        });
    }


}