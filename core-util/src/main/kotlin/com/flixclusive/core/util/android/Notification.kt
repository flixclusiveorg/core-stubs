package com.flixclusive.core.util.android

import android.Manifest
import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.app.NotificationManagerCompat.NotificationWithIdAndTag
import androidx.core.content.PermissionChecker
import androidx.core.content.getSystemService

/**
 * Extension property to get the NotificationManager system service.
 *
 * @return The NotificationManager instance.
 */
val Context.notificationManager: NotificationManager
    get() = getSystemService()!!

/**
 * Creates and shows a notification with the given parameters.
 *
 * @param id The unique identifier for this notification.
 * @param channelId The ID of the notification channel.
 * @param channelName The name of the notification channel.
 * @param shouldInitializeChannel Whether to initialize the notification channel if it doesn't exist.
 * @param block An optional lambda to configure the NotificationCompat.Builder.
 */
fun Context.notify(
    id: Int,
    channelId: String,
    channelName: String,
    shouldInitializeChannel: Boolean = false,
    block: (NotificationCompat.Builder.() -> Unit)? = null,
) {
    if(shouldInitializeChannel && Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        notificationManager.createChannel(
            channelId = channelId,
            channelName = channelName
        )
    }

    val notification = notificationBuilder(channelId, block).build()
    this.notify(id, notification)
}

/**
 * Shows a notification with the given ID and Notification object.
 *
 * @param id The unique identifier for this notification.
 * @param notification The Notification object to be displayed.
 */
@SuppressLint("MissingPermission")
fun Context.notify(id: Int, notification: Notification) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU && PermissionChecker.checkSelfPermission(
            this,
            Manifest.permission.POST_NOTIFICATIONS
        ) != PermissionChecker.PERMISSION_GRANTED
    ) {
        return
    }

    NotificationManagerCompat.from(this).notify(id, notification)
}

/**
 * Shows multiple notifications at once.
 *
 * @param notificationWithIdAndTags A list of NotificationWithIdAndTag objects to be displayed.
 */
@SuppressLint("MissingPermission")
fun Context.notify(notificationWithIdAndTags: List<NotificationWithIdAndTag>) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU && PermissionChecker.checkSelfPermission(
            this,
            Manifest.permission.POST_NOTIFICATIONS
        ) != PermissionChecker.PERMISSION_GRANTED
    ) {
        return
    }

    NotificationManagerCompat.from(this).notify(notificationWithIdAndTags)
}

/**
 * Cancels a notification with the given ID.
 *
 * @param id The unique identifier of the notification to be canceled.
 */
fun Context.cancelNotification(id: Int) {
    NotificationManagerCompat.from(this).cancel(id)
}

/**
 * Creates and returns a NotificationCompat.Builder with the given channel ID and optional configuration.
 *
 * @param channelId The ID of the notification channel.
 * @param block An optional lambda to configure the NotificationCompat.Builder.
 * @param color The color to be used for the notification. Defaults to "#B39DDB".
 * @return A configured NotificationCompat.Builder instance.
 */
fun Context.notificationBuilder(
    channelId: String, block: (NotificationCompat.Builder.() -> Unit)? = null,
    color: Int = Color.parseColor("#B39DDB"),
): NotificationCompat.Builder {
    val builder = NotificationCompat.Builder(this, channelId)
        .setColor(color)

    if (block != null) {
        builder.block()
    }
    return builder
}

/**
 * Creates a notification channel with the given ID and name.
 *
 * @param channelId The ID of the notification channel to be created.
 * @param channelName The name of the notification channel to be created.
 */
@RequiresApi(Build.VERSION_CODES.O)
fun NotificationManager.createChannel(
    channelId: String,
    channelName: String
) {
    val channel = NotificationChannel(
        channelId,
        channelName,
        NotificationManager.IMPORTANCE_DEFAULT
    )

    channel.enableLights(true)
    channel.lightColor = Color.valueOf(0xFF191C1E.toInt()).toArgb()

    createNotificationChannel(channel)
}