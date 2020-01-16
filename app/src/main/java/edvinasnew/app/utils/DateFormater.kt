package edvinasnew.app.utils

import java.text.SimpleDateFormat
import java.util.*

fun formatDate(date: Date): String {
    return SimpleDateFormat("yyyy-MM-dd", Locale.US).format(date)
}