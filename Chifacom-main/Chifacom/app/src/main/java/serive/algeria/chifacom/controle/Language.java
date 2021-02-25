package serive.algeria.chifacom.controle;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Build;
import android.preference.PreferenceManager;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

import serive.algeria.chifacom.R;

public class Language extends AppCompatActivity {

    Context mcontext;

    public Language(Context mcontext) {
        this.mcontext = mcontext;
    }

    //region Language
    /*
    public void showChangeLanguageDialoge() {
        final String[] listLanguage =  {"English","عربي","Français"};
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(mcontext);
        mBuilder.setTitle(mcontext.getResources().getString(R.string.choose_language));

        mBuilder.setSingleChoiceItems(listLanguage, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(which == 0){
                    ((Activity)mcontext).finish();
                    mcontext.startActivity(new Intent(mcontext,mcontext.getClass()));
                    setLocate("en",mcontext);
                    ((Activity)mcontext).recreate();
                }else if(which == 1){
                    ((Activity)mcontext).finish();
                    mcontext.startActivity(new Intent(mcontext,mcontext.getClass()));
                    setLocate("ar",mcontext);
                    ((Activity)mcontext).recreate();
                }else if(which == 2){
                    ((Activity)mcontext).finish();
                    mcontext.startActivity(new Intent(mcontext,mcontext.getClass()));
                    setLocate("fr",mcontext);
                    ((Activity)mcontext).recreate();
                }
                dialog.dismiss();
            }
        });
        AlertDialog mDialog = mBuilder.create();
        mDialog.show();
    }
    */

    public void setLocate(String lang, final Context context) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        context.getResources().updateConfiguration(config,context.getResources().getDisplayMetrics());

        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(mcontext).edit();

        editor.putString("Lang",lang);
        editor.apply();
    }

    public void loadLocale(){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(mcontext);
        String language = prefs.getString("Lang","");
        setLocate(language,mcontext);

        if(getLocale().equals("ar")){
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1){
                ((Activity)mcontext).getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            }
        }
    }

    public String getLocale(){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(mcontext);
        String language = prefs.getString("Lang","");
        return language;
    }

//endregion
}
