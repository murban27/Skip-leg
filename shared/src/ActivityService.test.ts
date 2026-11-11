// ActivityService.test.ts
// Unit tests for ActivityService using Jest.

import ActivityService from './ActivityService';

describe('ActivityService', () => {
    let activityService: ActivityService;

    beforeEach(() => {
        activityService = new ActivityService();
    });

    it('should log an activity', async () => {
        const activity = {
            userId: 'user1',
            type: 'Running',
            duration: 30,
            intensity: 'High',
        };
        const loggedActivity = await activityService.logActivity(activity);
        expect(loggedActivity).toEqual(activity);
    });

    it('should retrieve all activities', async () => {
        const activity1 = {
            userId: 'user1',
            type: 'Running',
            duration: 30,
            intensity: 'High',
        };
        const activity2 = {
            userId: 'user1',
            type: 'Cycling',
            duration: 45,
            intensity: 'Medium',
        };
        await activityService.logActivity(activity1);
        await activityService.logActivity(activity2);
        const activities = await activityService.getActivities();
        expect(activities).toEqual([activity1, activity2]);
    });
});