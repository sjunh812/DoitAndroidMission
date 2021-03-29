package org.techtown.mission12;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class MessageService extends Service {

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(intent != null) {
            String message = intent.getStringExtra("message");
            returnToBroadCast(message);
        }

        return super.onStartCommand(intent, flags, startId);
    }

    private void returnToBroadCast(String message) {
        Intent intent = new Intent();
        intent.setAction("org.techtown.broadcast.MESSAGE");
        intent.putExtra("message", message);

        sendBroadcast(intent);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}