package pharmacy_manager_team.PharmacyManager.util;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Calendar;

public class NotificationUtils {

    public void sendNotification(long time, Activity activity) {
        if (time > 0) {
            AlarmManager alarmManager = (AlarmManager) activity.getSystemService(Activity.ALARM_SERVICE);
            Intent alarmIntent = new Intent(activity.getApplicationContext(), MyAlarmService.class);
            // AlarmReceiver1 = broadcast receiver

            alarmIntent.putExtra("reason", "notification");
            alarmIntent.putExtra("timestamp", time);

            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(time);

            PendingIntent pendingIntent = PendingIntent.getBroadcast(
                    activity,
                    0,
                    alarmIntent,
                    PendingIntent.FLAG_CANCEL_CURRENT
            );
            Log.e("TAG", "sendNotification: >> Notification Sent");
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,System.currentTimeMillis(),time, pendingIntent);
        }
    }
}
