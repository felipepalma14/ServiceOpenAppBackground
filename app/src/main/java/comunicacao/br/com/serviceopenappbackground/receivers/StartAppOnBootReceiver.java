package comunicacao.br.com.serviceopenappbackground.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import comunicacao.br.com.serviceopenappbackground.MainActivity;

public class StartAppOnBootReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {

            Log.d("TESTE_FELIPE", "Start Service");
            Intent i = new Intent(context, MainActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startService(new Intent(context, MainActivity.class));
            context.startActivity(i);
        }
    }
}
