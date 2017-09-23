package zuk.nadav.testedapp4;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import nadav.tasher.lightool.Light;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startApp();
    }

    private void startApp() {
        LinearLayout ll = new LinearLayout(this);
        ll.setOrientation(LinearLayout.VERTICAL);
        ll.setGravity(Gravity.START);
        HorizontalScrollView hsv = new HorizontalScrollView(this);
        LinearLayout hsvll = new LinearLayout(this);
        hsvll.setGravity(Gravity.START);
        hsvll.setOrientation(LinearLayout.HORIZONTAL);
        hsv.addView(hsvll);
        ll.addView(hsv);
        hsvll.setPadding(10, 10, 10, 10);
        //INFOVIEW
        LinearLayout superinfo = new LinearLayout(this);
        superinfo.setGravity(Gravity.CENTER);
        superinfo.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout info = new LinearLayout(this);
        info.setOrientation(LinearLayout.VERTICAL);
        info.setGravity(Gravity.CENTER);
        final TextView main = new TextView(this);
        final TextView ver = new TextView(this);
        final TextView packagen = new TextView(this);
        final ImageView icon = new ImageView(this);
        main.setTextSize((float) 30);
        final Button uninstall = new Button(this);
        uninstall.setText(R.string.uninstall);
        superinfo.addView(icon);
        info.addView(main);
        info.addView(packagen);
        info.addView(ver);
        info.addView(uninstall);
        superinfo.addView(info);
        superinfo.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        int s2 = Light.Device.screenX(this) / 3;
        icon.setLayoutParams(new LinearLayout.LayoutParams(s2, s2));
        ll.addView(superinfo);
        final List<PackageInfo> apps = getPackageManager().getInstalledPackages(PackageManager.GET_ACTIVITIES);
        for (int i = 0; i < apps.size(); i++) {
            if (isUserApp(apps.get(i).applicationInfo)) {
                ImageButton bt = new ImageButton(this);
                bt.setImageDrawable(getPackageManager().getApplicationIcon(apps.get(i).applicationInfo));
                bt.setBackgroundColor(Color.TRANSPARENT);
                final int finalI = i;
                bt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        main.setText(apps.get(finalI).applicationInfo.loadLabel(getPackageManager()));
                        ver.setText(String.valueOf(apps.get(finalI).versionName) + " (" + String.valueOf(apps.get(finalI).versionCode) + ")");
                        packagen.setText(apps.get(finalI).packageName);
                        icon.setImageDrawable(apps.get(finalI).applicationInfo.loadIcon(getPackageManager()));
                        uninstall.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(Intent.ACTION_DELETE);
                                intent.setData(Uri.parse("package:" + apps.get(finalI).packageName));
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                                final BroadcastReceiver uninstallrec = new BroadcastReceiver() {
                                    @Override
                                    public void onReceive(Context context, Intent intent) {
                                        if (!Light.Device.isInstalled(getApplicationContext(), apps.get(finalI).packageName)) {
                                            context.unregisterReceiver(this);
                                            startApp();
                                        }
                                    }
                                };
                                IntentFilter intentFilter = new IntentFilter(Intent.ACTION_PACKAGE_REMOVED);
                                intentFilter.addDataScheme("package");
                                registerReceiver(uninstallrec, intentFilter);
                            }
                        });
                    }
                });
                int size = Light.Device.screenX(this) / 7;
                bt.setLayoutParams(new LinearLayout.LayoutParams(size, size));
                hsvll.addView(bt);
                if (apps.get(i).packageName.equals(getPackageName())) {
                    bt.callOnClick();
                }
            }
        }
        setContentView(ll);
    }

    boolean isUserApp(ApplicationInfo ai) {
        int mask = ApplicationInfo.FLAG_SYSTEM | ApplicationInfo.FLAG_UPDATED_SYSTEM_APP;
        return (ai.flags & mask) == 0;
    }
}
