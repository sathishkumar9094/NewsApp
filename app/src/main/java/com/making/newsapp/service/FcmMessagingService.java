package com.making.newsapp.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.making.newsapp.R;
import com.making.newsapp.activity.MainActivity;
import com.making.newsapp.dbmodel.NotificationIntent;
import com.making.newsapp.network.VolleySingleton;

public class FcmMessagingService extends FirebaseMessagingService {

    private static final int REMINDER_NOTIFICATION_ID = 1337;
    private static final int REMINDER_PENDING_INTENT_ID = 9001;
    private static final String REMINDER_NOTIFICATION_CHANNEL_ID = "notification_channel";
    private static final int ACTION_IGNORE_PENDING_INTENT_ID = 69;

    public static final String ACTION_IGNORE_NOTIFICATION = "ignore_notification";
    private static final String TAG=FcmMessagingService.class.getSimpleName();

    @Override
    public void onNewToken(@NonNull  String token) {
        super.onNewToken(token);
        Log.d(TAG, "onNewToken: "+token);
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {

        if (remoteMessage.getData().size() > 0) {

            String title, message, image_url;

            title = remoteMessage.getData().get("title");
            message = remoteMessage.getData().get("message");
            image_url = remoteMessage.getData().get("image_url");

            Intent i = new Intent(this, MainActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, i, PendingIntent.FLAG_ONE_SHOT);
            Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, REMINDER_NOTIFICATION_CHANNEL_ID)
                    .setColor(ContextCompat.getColor(this, R.color.colorPrimary))
                    .setSmallIcon(R.drawable.ic_notification_bitmap_24px)
                    .setLargeIcon(largeIcon(this))
                    .setContentTitle(title)
                    .setContentText(message)
                    .setStyle(new NotificationCompat.BigTextStyle().bigText(this.getString(R.string.notification_body)))
                    .setDefaults(Notification.DEFAULT_VIBRATE)
                    .setContentIntent(contentIntent(this))
                    .addAction(ignoreReminderAction(this))
                    .setAutoCancel(true);

            ImageRequest imageRequest = new ImageRequest(image_url, new Response.Listener<Bitmap>() {
                @Override
                public void onResponse(Bitmap response) {

                    builder.setStyle(new NotificationCompat.BigPictureStyle().bigPicture(response));
                    NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                    notificationManager.notify(0, builder.build());

                }
            }, 0, 0, null, Bitmap.Config.RGB_565, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });

            VolleySingleton.getInstance(this).addToRequestQueue(imageRequest);

        }
    }
    private static PendingIntent contentIntent(Context context) {
        Intent startActivityIntent = new Intent(context, MainActivity.class);
        return PendingIntent.getActivity(
                context,
                REMINDER_PENDING_INTENT_ID,
                startActivityIntent,
                PendingIntent.FLAG_UPDATE_CURRENT
        );
    }
    private static NotificationCompat.Action ignoreReminderAction(Context context) {
        Intent ignoreReminderIntent = new Intent(context, NotificationIntent.class);
        ignoreReminderIntent.setAction(ACTION_IGNORE_NOTIFICATION);

        PendingIntent ignoreReminderPendingIntent = PendingIntent.getService(
                context,
                ACTION_IGNORE_PENDING_INTENT_ID,
                ignoreReminderIntent,
                PendingIntent.FLAG_UPDATE_CURRENT
        );

        NotificationCompat.Action ignoreReminderAction = new NotificationCompat.Action(R.drawable.ic_clearmap, "EXIT",
                ignoreReminderPendingIntent);
        return ignoreReminderAction;
    }
    private static Bitmap largeIcon(Context context) {
        Resources res = context.getResources();
        Bitmap largeIcon = BitmapFactory.decodeResource(res, R.drawable.ic_notification_bitmap_24px);
        return largeIcon;
    }
}
