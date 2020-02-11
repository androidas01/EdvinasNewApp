package edvinasnew.app

import android.app.*
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.analytics.FirebaseAnalytics
import edvinasnew.app.main.MainActivity
import edvinasnew.app.tutorial.TutorialActivity
import io.reactivex.Observable
import java.util.concurrent.TimeUnit

class NewsApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Log.d("NewsApplication", "onCreate")


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (NotificationManagerCompat.from(this).getNotificationChannel("myChannel") == null) {
                NotificationManagerCompat.from(this).createNotificationChannel(
                    NotificationChannel(
                        "myChannel",
                        getString(R.string.common_notification_channel_general),
                        NotificationManager.IMPORTANCE_MIN
                    )
                )
            }

            if (NotificationManagerCompat.from(this).getNotificationChannel("myChannel1") == null) {
                NotificationManagerCompat.from(this).createNotificationChannel(
                    NotificationChannel(
                        "myChannel1",
                        getString(R.string.common_notification_channel_general),
                        NotificationManager.IMPORTANCE_HIGH
                    )
                )
            }
        }
        val disposable = Observable.timer(2, TimeUnit.SECONDS).subscribe {
            val notification = NotificationCompat.Builder(this, "myChannel").apply {
                setContentTitle("It's a notification")
                setContentText("text!!!")
                setSmallIcon(R.drawable.ic_launcher_foreground)
                addAction(
                    R.drawable.ic_launcher_foreground, "SHOW", PendingIntent.getActivity(
                        this@NewsApplication,
                        4,
                        MainActivity.createIntent(this@NewsApplication),
                        0
                    )
                )
                setContentIntent(
                    PendingIntent.getActivity(
                        this@NewsApplication,
                        4,
                        TutorialActivity.createIntent(this@NewsApplication),
                        0
                    )
                )
            }.build()

            val notification1 = NotificationCompat.Builder(this, "myChannel1").apply {
                setContentTitle("It's a notification high")
                setContentText("text!!! high")
                setSmallIcon(R.drawable.ic_launcher_background)
                addAction(
                    R.drawable.ic_launcher_background, "SHOW high", PendingIntent.getActivity(
                        this@NewsApplication,
                        5,
                        MainActivity.createFavorites(this@NewsApplication),
                        0
                    )
                )
                setContentIntent(
                    PendingIntent.getActivity(
                        this@NewsApplication,
                        5,
                        MainActivity.createFavorites(this@NewsApplication),
                        0
                    )
                )
            }.build()

            NotificationManagerCompat.from(this).notify(1, notification)

            NotificationManagerCompat.from(this).notify(2, notification1)
        }
    }
}