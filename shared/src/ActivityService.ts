// ActivityService.ts
// Shared module for managing fitness activities in TypeScript.

interface Activity {
    userId: string;
    type: string;
    duration: number;
    intensity: string;
}

class ActivityService {
    private activities: Activity[] = [];

    async logActivity(activity: Activity): Promise<Activity> {
        this.activities.push(activity);
        return activity;
    }

    async getActivities(): Promise<Activity[]> {
        return this.activities;
    }
}

export default ActivityService;