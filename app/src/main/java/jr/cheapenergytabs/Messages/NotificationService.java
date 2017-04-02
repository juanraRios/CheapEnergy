package jr.cheapenergytabs.Messages;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by juanra on 20/03/2017.
 */

public class NotificationService extends FirebaseMessagingService {

    public static final String TAG = "NOTIFICACIONES";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        String from = remoteMessage.getFrom();
        Log.d(TAG,"Mensaje recibido de: " + from);

        if (remoteMessage.getNotification() != null){
            Log.d(TAG, "Notificación: " + remoteMessage.getNotification().getBody());
        }
    }
}
