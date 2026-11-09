package fitnessapp

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ActivityService {
    private val activities = mutableListOf<Activity>()

    suspend fun logActivity(activity: Activity): Activity {
        activities.add(activity)
        return activity
    }

    suspend fun getActivities(): List<Activity> {
        return activities
    }

    data class Activity(
        val userId: String,
        val type: String,
        val duration: Int,
        val intensity: String
    )
}