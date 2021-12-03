package com.matiaslugo.obligatorio2021.persistencia;

import android.content.Context;

import androidx.annotation.Nullable;

public class PersistenciaTarea implements IPeristenciaTarea{
    public static PersistenciaTarea instance;

    public static PersistenciaTarea getInstance(Context context){
        if (instance == null){
            instance = new PersistenciaTarea(context);
        }
        return instance;
    }

    private Context context;
    public PersistenciaTarea(@Nullable Context context) {
        this.context = context;
    }
}
