package com.fanava.rally.Utils;

import android.app.NotificationChannel;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.widget.RemoteViews;

import com.fanava.rally.R;

public class Notification {
//    public void CreateNotification() {
//        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.notification_layout);
//        remoteViews.setTextViewText(R.id.notification_txt, title);
//        remoteViews.setOnClickPendingIntent(R.id.notification_txt, launchAppPendingIntent);
//        if (notificationTemp == 1) {
//
//            final NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
//                    .setPriority(NotificationCompat.PRIORITY_HIGH)
//                    .setOngoing(true)
//                    .setWhen(0)
//                    .setContentIntent(launchAppPendingIntent)
//                    .setContentTitle(utils.shape(title))
//                    .setContent(remoteViews)
//
//                    .setSmallIcon(R.drawable.icon);
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                NotificationChannel mchannel = new NotificationChannel("1001", "بالقران", NotificationManager.IMPORTANCE_LOW);
//                notify(context, mchannel, builder.build());
//            } else
//                notify(context, builder.build());
//        }
//
//    }
}
