package shekar.com.snakeview.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.content.ContextCompat;

public class PermissionUtil {
  public static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION=100;
  public static boolean shouldAskPermission(Context context, String permission) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
      int permissionResult = ContextCompat.checkSelfPermission(context, permission);
      return permissionResult != PackageManager.PERMISSION_GRANTED;
    }
    return false;
  }
}