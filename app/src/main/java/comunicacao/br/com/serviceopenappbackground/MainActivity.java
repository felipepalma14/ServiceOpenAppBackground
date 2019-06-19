package comunicacao.br.com.serviceopenappbackground;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import comunicacao.br.com.serviceopenappbackground.services.ScreenOnOffService;

/**
 * Created by Felipe Palma on 18/06/2019.
 */


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ScreenOnOffService mScreenOnOffService = new ScreenOnOffService(getApplicationContext());

        final Window win = getWindow();
        win.addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
        win.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);

        if (!isServiceRunning(mScreenOnOffService.getClass())) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                startForegroundService(new Intent(this, mScreenOnOffService.getClass()));
            } else {
                startService(new Intent(this, mScreenOnOffService.getClass()));
            }
        }
    }


    private boolean isServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                Log.d("isServiceRunning?", true + "");
                return true;
            }
        }
        Log.d("isServiceRunning?", false + "");
        return false;
    }
}
