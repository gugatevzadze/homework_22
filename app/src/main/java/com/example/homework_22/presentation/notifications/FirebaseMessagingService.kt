package com.example.homework_22.presentation.notifications

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.navigation.NavDeepLinkBuilder
import com.example.homework_22.R
import com.example.homework_22.presentation.MainActivity
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class FirebaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        message.notification?.let {
            val id = message.data["id"]?.toIntOrNull() ?: 0
            val title = it.title ?: "Test Title"
            val desc = it.body ?: "Test Desc"
            sendNotification(id, title, desc)
        }
    }

    private fun sendNotification(id: Int, title: String, desc: String) {
        val args = Bundle().also {
            it.putInt("id", id)
        }

        val pendingIntent = NavDeepLinkBuilder(this)
            .setGraph(R.navigation.nav_graph)
            .setDestination(R.id.detailsFragment)
            .setArguments(args)
            .createPendingIntent()

        val notification = NotificationCompat.Builder(this, "channel_id")
            .setSmallIcon(R.drawable.ic_splash_screen_logo)
            .setContentTitle(title)
            .setContentText(desc)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)

        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        notificationManager.notify(0, notification.build())
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
    }
}