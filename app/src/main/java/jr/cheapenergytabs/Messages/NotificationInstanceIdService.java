package jr.cheapenergytabs.Messages;

import com.google.firebase.FirebaseApp;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.google.firebase.iid.zzd;
import com.google.firebase.messaging.FirebaseMessaging;

import android.util.Log;

/**
 * Created by juanra on 20/03/2017.
 */

public class NotificationInstanceIdService extends FirebaseInstanceIdService {

    public static final String TAG = "NOTIFICACIONES";

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        FirebaseMessaging.getInstance().subscribeToTopic("news");
        String token = FirebaseInstanceId.getInstance().getToken();

        Log.d(TAG, "Token: " + token);
    }
}
