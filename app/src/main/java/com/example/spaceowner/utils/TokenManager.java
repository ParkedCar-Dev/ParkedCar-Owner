package com.example.spaceowner.utils;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.spaceowner.OwnerApplication;

public class TokenManager {
    private SharedPreferences sp;
    private static TokenManager tokenManager;
    public static TokenManager getInstance(){
        if(tokenManager == null) tokenManager = new TokenManager();
        return tokenManager;
    }
    private TokenManager() {
        sp = OwnerApplication.getContext().getSharedPreferences("token", Context.MODE_PRIVATE);
    }
    public void setToken(String token){
        Log.d("TOKEN_MANAGER: ", "setToken: " + token);
        sp.edit().putString("token", token).apply();
    }

    public String getToken(){
        Log.d("TOKEN_MANAGER: ", "getToken: " + sp.getString("token", null));
        return sp.getString("token", null);
    }

    public void clearToken() {
        sp.edit().clear().apply();
    }
}
