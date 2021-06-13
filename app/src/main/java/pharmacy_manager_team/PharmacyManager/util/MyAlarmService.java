package pharmacy_manager_team.PharmacyManager.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class MyAlarmService extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
//        NotificationEventReceiver.setupAlarm(context);
        Log.e("MyAlarmService", "onReceive: Alarm has been received");
        Toast.makeText(context, "onReceive: Alarm has been received", Toast.LENGTH_SHORT).show();

        Intent intent1 = new Intent(context, NotificationIntentService.class);
        intent1.putExtra("reason", intent.getStringExtra("reason"));
        intent1.putExtra("timestamp", intent.getLongExtra("timestamp", 0));
        context.startService(intent1);
    }
}
