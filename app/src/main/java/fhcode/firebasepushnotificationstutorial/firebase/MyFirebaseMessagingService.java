package fhcode.firebasepushnotificationstutorial.firebase;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Random;

import fhcode.firebasepushnotificationstutorial.MainActivity;
import fhcode.firebasepushnotificationstutorial.R;

/**
 * Created by FH code on 23.08.2016..
 */
public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private String recievedID = "0";
    private NotificationManager mNotificationManager;

    private static final String TAG = "MyFirebaseMsgService";

    //Method for recieving push notifications
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        Log.d(TAG, "From: " + remoteMessage.getFrom());
        Log.d(TAG, "Notification Message Body:  " + remoteMessage.getData().get("body"));
        recievedID = remoteMessage.getData().get("id");
        receiveNotification(remoteMessage.getData().get("body"));
    }

    //Method that is used to generate push Notification
    private void receiveNotification(String messageBody) {
        Log.d("Received ID on Recieve", String.valueOf(recievedID));
        //Activity that will open when clicking on notification
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.putExtra("id",recievedID);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        Random generator = new Random();

        mNotificationManager = (NotificationManager)
                getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        // Allowing every notification to open separately
        PendingIntent contentIntent = PendingIntent.getActivity(getApplicationContext(), generator.nextInt(),
                intent, PendingIntent.FLAG_UPDATE_CURRENT);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(getApplicationContext())

                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("FHcode Push notification")
                        .setStyle(new NotificationCompat.BigTextStyle()
                                .bigText(messageBody))
                        .setSound(defaultSoundUri)
                        .setAutoCancel(true)
                        .setContentText(messageBody);


        mBuilder.setContentIntent(contentIntent);
        mNotificationManager.notify(0, mBuilder.build());

    }

    Random random = new Random();
    int m = random.nextInt(9999 - 1000) + 1000;
}
