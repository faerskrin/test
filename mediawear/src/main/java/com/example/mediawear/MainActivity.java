package com.example.mediawear;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.NotificationManagerCompat;
import androidx.media.app.NotificationCompat;

public class MainActivity extends WearableActivity {

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = (TextView) findViewById(R.id.text);

//        Intent intent = new Intent(this,MainActivity.class);
//        PendingIntent pedding = new PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//        Notification.Action action = new Notification.Action.Builder(R.drawable.action_item_background,"TITLE",pedding).build();
//
//        Notification notification = new Notification.Builder(this)
//                .setContentText("Content text")
//                .setContentTitle("Content title")
//                .extend(new Notification.WearableExtender().addAction(action))
//                .build();
//        // Enables Always-on
//
//        NotificationManagerCompat not = NotificationManagerCompat.from(this);
//        not.notify(001, notification);


        setAmbientEnabled();
    }

    public void click_play(View view) {

        Toast.makeText(this,"PLAY",Toast.LENGTH_SHORT).show();

    }
}
