package com.example.ecogo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class Router {
    private static final String TAG = "Router";
    private static final String PACKAGE_NAME = "com.example.ecogo";

    /**
     * Navigates to a screen based on a label or feature name.
     * Rules: "Settings" -> SettingsActivity, "My Reports" -> MyReportsActivity, etc.
     */
    public static void navigate(Context context, String label) {
        if (label == null || label.isEmpty()) return;

        // Handle Back navigation
        if (label.equalsIgnoreCase("Back") || label.equalsIgnoreCase("Cancel") || label.equalsIgnoreCase("Close")) {
            if (context instanceof Activity) {
                ((Activity) context).onBackPressed();
            }
            return;
        }

        // Normalize label to Activity class name
        String className = label.replace(" ", "").replace("&", "And").replace("@", "");
        
        // Common mappings
        if (label.equalsIgnoreCase("Home")) className = "Main";
        if (label.equalsIgnoreCase("Log Out")) className = "Login";
        if (label.contains("Reports")) className = "MyReports";
        if (label.equalsIgnoreCase("Trending") || label.equalsIgnoreCase("Nearby") || label.equalsIgnoreCase("Following")) className = "Community";

        // Try navigating
        if (!tryNavigate(context, className + "Activity")) {
            if (!tryNavigate(context, className)) {
                Log.e(TAG, "No activity found for label: " + label + " (tried " + className + ")");
            }
        }
    }

    private static boolean tryNavigate(Context context, String className) {
        try {
            Class<?> activityClass = Class.forName(PACKAGE_NAME + "." + className);
            Intent intent = new Intent(context, activityClass);
            if (!(context instanceof Activity)) {
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            }
            context.startActivity(intent);
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }
}
