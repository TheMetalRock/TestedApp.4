package zuk.nadav.testedapp4;

import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
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
        final TextView vercode = new TextView(this);
        final TextView vername = new TextView(this);
        ImageView icon = new ImageView(this);
        Button uninstall = new Button(this);
        uninstall.setText(R.string.uninstall);
        superinfo.addView(icon);
        info.addView(main);
        info.addView(vercode);
        info.addView(vername);
        info.addView(uninstall);
        superinfo.addView(info);
        ll.addView(superinfo);
        //        Toast.makeText(this,"Screen: "+ Light.Device.screenX(getApplicationContext()) + "x"+ Light.Device.screenY(this),Toast.LENGTH_SHORT).show();
        //getting a list of apps{
        final List<PackageInfo> apps = getPackageManager().getInstalledPackages(PackageManager.GET_ACTIVITIES);
        //getting a list of apps}
        for (int i = 0; i < apps.size(); i++) {
            //TODO add app view (currentApp) to Layout
            ImageButton bt = new ImageButton(this);
            bt.setImageDrawable(getPackageManager().getApplicationIcon(apps.get(i).applicationInfo));
            bt.setBackgroundColor(Color.TRANSPARENT);
            final int finalI = i;
            bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    main.setText(apps.get(finalI).applicationInfo.loadLabel(getPackageManager()));
                    vercode.setText(String.valueOf(apps.get(finalI).versionCode));
                    vername.setText(String.valueOf(apps.get(finalI).versionName));
                }
            });
            int size = Light.Device.screenX(this) / 7;
            bt.setLayoutParams(new LinearLayout.LayoutParams(size, size));
            hsvll.addView(bt);
        }
        setContentView(ll);
    }

    LinearLayout getApp(String name, Drawable icon) {
        LinearLayout app = new LinearLayout(getApplicationContext());
        app.setOrientation(LinearLayout.VERTICAL);
        app.setGravity(Gravity.CENTER);
        app.setLayoutParams(new LinearLayout.LayoutParams(Light.Device.screenX(getApplicationContext()) / 3, Light.Device.screenY(getApplicationContext()) / 4));
        ImageView iv = new ImageView(getApplicationContext());
        TextView tv = new TextView(getApplicationContext());
        app.addView(iv);
        app.addView(tv);
        iv.setImageDrawable(icon);
        tv.setText(name);
        app.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO prompt uninstall
            }
        });
        app.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                //TODO show details
                return true;
            }
        });
        return app;
    }

    void popupInfo() {

    }
}
