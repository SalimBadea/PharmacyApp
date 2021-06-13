package pharmacy_manager_team.PharmacyManager.util;

import android.annotation.SuppressLint;
import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.legacy.content.WakefulBroadcastReceiver;

import java.util.Calendar;

import pharmacy_manager_team.PharmacyManager.R;
import pharmacy_manager_team.PharmacyManager.Ui.MedicinesEntriesActivity;

public class NotificationIntentService extends IntentService {

    private static final int NOTIFICATION_ID = 1000;
    private static final String ACTION_START = "ACTION_START";
    private static final String ACTION_DELETE = "ACTION_DELETE";
    Notification mNotification;

    public NotificationIntentService() {
        super(NotificationIntentService.class.getSimpleName());
    }


    public static Intent createIntentStartNotificationService(Context context) {
        Intent intent = new Intent(context, NotificationIntentService.class);
        intent.setAction(ACTION_START);
        return intent;
    }

    public static Intent createIntentDeleteNotification(Context context) {
        Intent intent = new Intent(context, NotificationIntentService.class);
        intent.setAction(ACTION_DELETE);
        return intent;
    }

//    @Override
//    protected void onHandleIntent(Intent intent) {
//        Log.d(getClass().getSimpleName(), "onHandleIntent, started handling a notification event");
//        try {
//            String action = intent.getAction();
//            if (ACTION_START.equals(action)) {
//                processStartNotification();
//            }
//            if (ACTION_DELETE.equals(action)) {
//                processDeleteNotification(intent);
//            }
//        } finally {
//            WakefulBroadcastReceiver.completeWakefulIntent(intent);
//        }
//    }

//    private void processDeleteNotification(Intent intent) {
//        // Log something?
//    }

//    private void processStartNotification() {
//        // Do something. For example, fetch fresh data from backend to create a rich notification?
//        Log.d(getClass().getSimpleName(), "This notification has been triggered by Notification Service");
//        final NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
//        builder.setContentTitle("Scheduled Notification")
//                .setAutoCancel(true)
//                .setColor(getResources().getColor(R.color.teal_200))
//                .setContentText("This notification has been triggered by Notification Service")
//                .setSmallIcon(R.drawable.logo);
//
//        PendingIntent pendingIntent = PendingIntent.getActivity(this,
//                NOTIFICATION_ID,
//                new Intent(this, MedicinesEntriesActivity.class),
//                PendingIntent.FLAG_UPDATE_CURRENT);
//        builder.setContentIntent(pendingIntent);
//        builder.setDeleteIntent(NotificationEventReceiver.getDeleteIntent(this));
//
//        final NotificationManager manager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
//        manager.notify(NOTIFICATION_ID, builder.build());
//    }

    @SuppressLint("NewApi")
    private void createChannel() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            // Create the NotificationChannel, but only on API 26+ because
            // the NotificationChannel class is new and not in the support library

            Context context = this.getApplicationContext();
            NotificationManager notificationManager =
                    (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

            NotificationChannel notificationChannel = new NotificationChannel(
                    getString(R.string.notification_channel_id),
                    getString(R.string.notification_channel_name), NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.enableVibration(true);
            notificationChannel.setShowBadge(true);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.GREEN);
            notificationChannel.setDescription(getString(R.string.task_notification_channel_description));
            notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
            notificationManager.createNotificationChannel(notificationChannel);
        }

    }

    @Override
    protected void onHandleIntent(Intent intent) {
        createChannel();
        Log.d(getClass().getSimpleName(), "onHandleIntent, started handling a notification event");
//        final NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
//        builder.setContentTitle("Scheduled Notification")
//                .setAutoCancel(true)
//                .setColor(getResources().getColor(R.color.teal_200))
//                .setContentText("This notification has been triggered by Notification Service")
//                .setSmallIcon(R.drawable.logo);

//        PendingIntent pendingIntent = PendingIntent.getActivity(this,
//                NOTIFICATION_ID,
//                new Intent(this, MedicinesEntriesActivity.class),
//                PendingIntent.FLAG_UPDATE_CURRENT);
//        builder.setContentIntent(pendingIntent);
//        builder.setDeleteIntent(NotificationEventReceiver.getDeleteIntent(this));
//
//        final NotificationManager manager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
//        manager.notify(NOTIFICATION_ID, builder.build());

        long timestamp = 0;
        if (intent != null && intent.getExtras() != null) {
            timestamp = intent.getExtras().getLong("timestamp");
            Log.e("TAG", "onHandleIntent: time >> " + timestamp);
        }

        if (timestamp > 0) {

            Context context = this.getApplicationContext();
            NotificationManager notificationManager = (NotificationManager)
                    context.getSystemService(Context.NOTIFICATION_SERVICE);
            Intent notifyIntent = new Intent(this, MedicinesEntriesActivity.class);

            String title = "Medicine Reminder";
            String message = "There is a Medicine Reminder , Check it Now";

            notifyIntent.putExtra("title", title);
            notifyIntent.putExtra("message", message);
            notifyIntent.putExtra("notification", true);

            notifyIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(timestamp);

            PendingIntent pendingIntent = PendingIntent.getActivity(
                    context,
                    0,
                    notifyIntent,
                    PendingIntent.FLAG_UPDATE_CURRENT
            );

            Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

                mNotification =
                        new Notification.Builder(this, getString(R.string.notification_channel_id))
                                // Set the intent that will fire when the user taps the notification
                                .setContentIntent(pendingIntent)
                                .setAutoCancel(true)
                                .setPriority(Notification.PRIORITY_HIGH)
                                .setContentTitle(title)
                                .setStyle(new Notification.BigTextStyle().bigText(message))
                                .setSound(uri)
                                .setContentText(message)
                                .setColor(getResources().getColor(R.color.teal_200))
                                .setSmallIcon(R.drawable.logo).build();
            } else {

                mNotification = new Notification.Builder(this)
                        // Set the intent that will fire when the user taps the notification
                        .setContentIntent(pendingIntent)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setLargeIcon(BitmapFactory.decodeResource(this.getResources(), R.mipmap.ic_launcher))
                        .setAutoCancel(false)
                        .setPriority(Notification.PRIORITY_HIGH)
                        .setContentTitle(title)
                        .setStyle(new Notification.BigTextStyle().bigText(message))
                        .setSound(uri)
                        .setContentText(message).build();

            }

            notificationManager =
                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            // mNotificationId is a unique int for each notification that you must define
            notificationManager.notify(NOTIFICATION_ID, mNotification);
        }
    }
//
//    private void processDeleteNotification(Intent intent) {
//        // Log something?
//    }
//
//    private void processStartNotification() {
//        // Do something. For example, fetch fresh data from backend to create a rich notification?
//
//        Log.e("TAG", "processStartNotification: Notification Started");
//        final NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
//        builder.setContentTitle("Scheduled Notification")
//                .setAutoCancel(true)
//                .setColor(getResources().getColor(R.color.teal_200))
//                .setContentText("This notification has been triggered by Notification Service")
//                .setSmallIcon(R.drawable.logo);
//
//        PendingIntent pendingIntent = PendingIntent.getActivity(this,
//                NOTIFICATION_ID,
//                new Intent(this, MedicinesEntriesActivity.class),
//                PendingIntent.FLAG_UPDATE_CURRENT);
//        builder.setContentIntent(pendingIntent);
//        builder.setDeleteIntent(NotificationEventReceiver.getDeleteIntent(this));
//
//        final NotificationManager manager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
//        manager.notify(NOTIFICATION_ID, builder.build());
//    }
}
