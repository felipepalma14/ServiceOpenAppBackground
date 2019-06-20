package comunicacao.br.com.serviceopenappbackground.preferences;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Felipe Palma on 19/06/2019.
 */

public class SharedPrefManager {

    private static final String APP_PREFER = "meu.app.servico";


    public static SharedPreferences getSharedPref(Context mContext) {
        SharedPreferences pref = mContext.getSharedPreferences(APP_PREFER, Context.MODE_PRIVATE);

        return pref;
    }

    public static void setPrefVal(Context mContext, String key, String value) {
        if(key!=null){
            SharedPreferences.Editor edit = getSharedPref(mContext).edit();
            edit.putString(key, value);
            edit.commit();
        }
    }



    public static void setBooleanPrefVal(Context mContext, String key, boolean value) {
        if(key!=null){
            SharedPreferences.Editor edit = getSharedPref(mContext).edit();
            edit.putBoolean(key, value);
            edit.commit();
        }
    }


    public static String getPrefVal(Context mContext, String key) {
        SharedPreferences pref = getSharedPref(mContext);
        String val = "";
        try {
            if (pref.contains(key))
                val = pref.getString(key, "");
            else
                val = "";
        }catch (Exception e){
            e.printStackTrace();
        }
        return val;
    }

    public static int getIntPrefVal(Context mContext, String key) {
        SharedPreferences pref = getSharedPref(mContext);
        int val = 0;
        try {
            if(pref.contains(key)) val = pref.getInt(key, 0);
        }catch (Exception e){
            e.printStackTrace();
        }
        return val;
    }



    public static boolean getBooleanPrefVal(Context mContext, String key) {
        SharedPreferences pref = getSharedPref(mContext);
        boolean val = false;
        try{
            if(pref.contains(key)) val = pref.getBoolean(key, false);

        }catch (Exception e){
            e.printStackTrace();
        }
        return val;
    }


    public static boolean containkey(Context mContext,String key)
    {
        SharedPreferences pref = getSharedPref(mContext);
        return pref.contains(key);
    }

    public static void saveTestScreens(Context mContext, String key,
                                       String value) {
        SharedPreferences.Editor edit = getSharedPref(mContext).edit();
        edit.putString(key, value);
        edit.commit();
    }


}