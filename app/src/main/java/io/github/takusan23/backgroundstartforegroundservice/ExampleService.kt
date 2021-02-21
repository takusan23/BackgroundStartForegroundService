package io.github.takusan23.backgroundstartforegroundservice

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Icon
import android.os.IBinder
import androidx.core.app.NotificationCompat

class ExampleService : Service() {

    /** 通知チャンネル追加で使う */
    private val notificationManager by lazy { getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        postNotification()
        return START_NOT_STICKY
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    /** フォアグラウンドサービス開始 */
    private fun postNotification() {
        val notificationChannelId = "test_notification"
        if (notificationManager.getNotificationChannel(notificationChannelId) == null) {
            // 通知チャンネル登録
            val channel = NotificationChannel(notificationChannelId, "通知テスト", NotificationManager.IMPORTANCE_LOW)
            notificationManager.createNotificationChannel(channel)
        }
        val notification = Notification.Builder(this, notificationChannelId).apply {
            setContentTitle("サービス起動中")
            setContentText("サービス起動テスト")
            setSmallIcon(R.drawable.ic_outline_textsms_24)
        }.build()
        startForeground(1, notification)
    }

}