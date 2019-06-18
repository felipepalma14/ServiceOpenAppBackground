package comunicacao.br.com.serviceopenappbackground.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import comunicacao.br.com.serviceopenappbackground.MainActivity;

/**
 * Created by Felipe Palma on 18/06/2019.
 */


public class ScreenOnOffReceiver extends BroadcastReceiver {
    String pkg = "com.whatsapp";

    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
            Log.d("TESTE_FELIPE", "Screen Off");

        } else if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
            Log.d("TESTE_FELIPE", "Screen On");
            restart(context);

        }
    }

    public void restart(Context context) {
        //Intent i = context.getPackageManager().getLaunchIntentForPackage(pkg);
        Intent i = new Intent(context, MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_NEW_TASK);
//        Intent i = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
//        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }

}
