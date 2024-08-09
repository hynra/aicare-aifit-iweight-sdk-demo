package aicare.net.cn.iweightdemo.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import java.util.ArrayList;
import java.util.List;

public class BluetoothPermissionHelper {
    private static final int PERMISSION_REQUEST_CODE = 123;
    private static final String TAG = "BluetoothPermHelper";

    public static void requestBluetoothPermissions(Activity activity) {
        List<String> permissionsToRequest = new ArrayList<>();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            // Android 12 and above
            Log.d(TAG, "Checking permissions for Android 12+");
            if (ContextCompat.checkSelfPermission(activity, Manifest.permission.BLUETOOTH_SCAN) != PackageManager.PERMISSION_GRANTED) {
                permissionsToRequest.add(Manifest.permission.BLUETOOTH_SCAN);
                Log.d(TAG, "BLUETOOTH_SCAN permission needed");
            }
            if (ContextCompat.checkSelfPermission(activity, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
                permissionsToRequest.add(Manifest.permission.BLUETOOTH_CONNECT);
                Log.d(TAG, "BLUETOOTH_CONNECT permission needed");
            }
        } else {
            // Android 11 and below
            Log.d(TAG, "Checking permissions for Android 11 and below");
            if (ContextCompat.checkSelfPermission(activity, Manifest.permission.BLUETOOTH) != PackageManager.PERMISSION_GRANTED) {
                permissionsToRequest.add(Manifest.permission.BLUETOOTH);
                Log.d(TAG, "BLUETOOTH permission needed");
            }
            if (ContextCompat.checkSelfPermission(activity, Manifest.permission.BLUETOOTH_ADMIN) != PackageManager.PERMISSION_GRANTED) {
                permissionsToRequest.add(Manifest.permission.BLUETOOTH_ADMIN);
                Log.d(TAG, "BLUETOOTH_ADMIN permission needed");
            }
            if (ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                permissionsToRequest.add(Manifest.permission.ACCESS_FINE_LOCATION);
                Log.d(TAG, "ACCESS_FINE_LOCATION permission needed");
            }
            if (ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                permissionsToRequest.add(Manifest.permission.ACCESS_COARSE_LOCATION);
                Log.d(TAG, "ACCESS_COARSE_LOCATION permission needed");
            }
        }

        if (!permissionsToRequest.isEmpty()) {
            Log.d(TAG, "Requesting permissions: " + permissionsToRequest.toString());
            ActivityCompat.requestPermissions(activity, permissionsToRequest.toArray(new String[0]), PERMISSION_REQUEST_CODE);
        } else {
            Log.d(TAG, "All permissions already granted");
        }
    }

    public static boolean hasBluetoothPermissions(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            boolean hasPermissions = ContextCompat.checkSelfPermission(context, Manifest.permission.BLUETOOTH_SCAN) == PackageManager.PERMISSION_GRANTED
                    && ContextCompat.checkSelfPermission(context, Manifest.permission.BLUETOOTH_CONNECT) == PackageManager.PERMISSION_GRANTED;
            Log.d(TAG, "Android 12+: Has all permissions: " + hasPermissions);
            return hasPermissions;
        } else {
            boolean hasPermissions = ContextCompat.checkSelfPermission(context, Manifest.permission.BLUETOOTH) == PackageManager.PERMISSION_GRANTED
                    && ContextCompat.checkSelfPermission(context, Manifest.permission.BLUETOOTH_ADMIN) == PackageManager.PERMISSION_GRANTED
                    && ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                    && ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED;
            Log.d(TAG, "Android 11 and below: Has all permissions: " + hasPermissions);
            return hasPermissions;
        }
    }
}
