package comunicacao.br.com.serviceopenappbackground.services;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import comunicacao.br.com.serviceopenappbackground.receivers.ScreenOnOffReceiver;

/**
 * Created by Felipe Palma on 18/06/2019.
 */


public class ScreenOnOffService extends Service {

    private ScreenOnOffReceiver mScreenReceiver;
    private Context mContext;

    public ScreenOnOffService() {
    }

    public ScreenOnOffService(Context mContext) {
        super();
        this.mContext = mContext;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public void onCreate() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            String CHANNEL_ID = "your_channel_id";
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID,
                    "Notification Channel Title",
                    NotificationManager.IMPORTANCE_DEFAULT);

            ((NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE)).createNotificationChannel(channel);

            Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                    .setContentTitle("")
                    .setContentText("").build();

            startForeground(1, notification);
        }
        registerScreenStatusReceiver();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        Log.d("SERVICE_", "Im running");
        return START_STICKY;
    }


    @Override
    public void onDestroy() {

        Log.d("SERVICE_", "Im dead");

        keepServiceAlive();
        unregisterScreenStatusReceiver();
    }

    private void registerScreenStatusReceiver() {
        mScreenReceiver = new ScreenOnOffReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        filter.addAction(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_BOOT_COMPLETED);
        registerReceiver(mScreenReceiver, filter);
    }

    private void unregisterScreenStatusReceiver() {
        try {
            if (mScreenReceiver != null) {
                unregisterReceiver(mScreenReceiver);
            }
        } catch (IllegalArgumentException e) {
        }
    }


    private void keepServiceAlive() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            startForegroundService(new Intent(ScreenOnOffService.this, ScreenOnOffService.class));
        } else {
            startService(new Intent(ScreenOnOffService.this, ScreenOnOffService.class));
        }
    }

}
