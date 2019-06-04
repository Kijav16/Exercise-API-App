package com.example.exercise_api_app;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import static androidx.core.content.ContextCompat.getSystemService;

/**
 * Class for sending push notifications (bothering) to the user.
 */
public class Botherer extends Worker {
    public static final String CHANNEL_ID = "bother";
    public int exercisesToBother = 5;
    public Context context;
    private Stats stats;

    public Botherer(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        this.context = context;
        stats = new Stats(context);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "Botherer", NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription("Used for reminders of old exercises");
            NotificationManager notificationManager = getSystemService(context,NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    /**
     * Sends a push notification to the user.
     */
    public void bother(Context context, int remainingExercices){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setContentTitle("Remaining exercises: "+remainingExercices)
                .setContentText("You still have remaining exercises.")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setPriority(NotificationCompat.PRIORITY_HIGH);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);

        // notificationId is a unique int for each notification that you must define
        int notificationId = 42;
        notificationManager.notify(notificationId, builder.build());
    }

    @NonNull
    @Override
    public Result doWork() {

        if (stats.getUserName() != null && !stats.getUserName().isEmpty()){
            Tracker tracker = new Tracker(stats.getUserName(), context);
            int remainingExercises = getRemainingExercises(tracker);
            if (remainingExercises >= exercisesToBother){
                bother(context, remainingExercises);
            }
        }
        return Result.success();
    }

    private int getRemainingExercises(Tracker tracker) {
        return (int) (tracker.getDeathsExerciseRemaining()+ tracker.getKillsExerciseRemaining() + tracker.getHoursPlayedExerciseRemaining());
    }
}
