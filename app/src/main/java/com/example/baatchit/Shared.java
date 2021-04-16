package com.example.baatchit;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

public class Shared {
    SharedPreferences sharedPreferences; //to create SP file
    SharedPreferences.Editor editor;     //to edit SP file
    Context context;                        // context pass reference to another class
    int mode=0;                     //mode should be private in PS files
    String Filename="sdfiles";    //PS file name
    String Data = "b";              //to store boolean value with respect to key id

    //create constructor to pass memory at runtime to Shared file


    public Shared(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(Filename,mode);
        editor = sharedPreferences.edit();

    }


    //for second time user
    public void secondtime() {
         editor.putBoolean(Data,true);
         editor.commit();
    }
    public void firsttime() {
        if(!this.login())
        {
        //if b false then jump to dashboard activity
            Intent intent= new Intent(context,LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            context.startActivity(intent);
        }
    }
        //to get default value as false
    private boolean login() {
        return sharedPreferences.getBoolean(Data,false);
    }
}

