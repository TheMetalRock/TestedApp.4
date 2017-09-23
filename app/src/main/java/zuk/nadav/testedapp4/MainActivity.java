package zuk.nadav.testedapp4;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import nadav.tasher.lightool.Light;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout ll=new LinearLayout(this);
        ll.setOrientation(LinearLayout.VERTICAL);
        ll.setGravity(Gravity.START);
        HorizontalScrollView hsv=new HorizontalScrollView(this);
        LinearLayout hsvll=new LinearLayout(this);
        hsvll.setGravity(Gravity.START);
        hsvll.setOrientation(LinearLayout.HORIZONTAL);
        hsv.addView(hsvll);
        ll.addView(hsv);
        Toast.makeText(this,"Screen: "+ Light.Device.screenX(getApplicationContext()) + "x"+ Light.Device.screenY(this),Toast.LENGTH_SHORT).show();
        //getting a list of apps{
        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        List<ResolveInfo> pkgAppsList = getPackageManager().queryIntentActivities( mainIntent, 0);
        //getting a list of apps}
        for(int i=0;i<pkgAppsList.size();i++){
//            LinearLayout currentApp=getApp((String)pkgAppsList.get(i).loadLabel(getPackageManager()),pkgAppsList.get(i).loadIcon(getPackageManager()));
            //TODO add app view (currentApp) to Layout
            ImageButton bt=new ImageButton(this);
            bt.setImageDrawable(pkgAppsList.get(i).loadIcon(getPackageManager()));
            bt.setBackgroundColor(Color.TRANSPARENT);
            bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
            int size= Light.Device.screenX(this)/7;
            bt.setLayoutParams(new LinearLayout.LayoutParams(size,size));
            hsvll.addView(bt);
        }
        setContentView(ll);
    }
    LinearLayout getApp(String name,Drawable icon){
        LinearLayout app=new LinearLayout(getApplicationContext());
        app.setOrientation(LinearLayout.VERTICAL);
        app.setGravity(Gravity.CENTER);
        app.setLayoutParams(new LinearLayout.LayoutParams(Light.Device.screenX(getApplicationContext())/3, Light.Device.screenY(getApplicationContext())/4));
        ImageView iv=new ImageView(getApplicationContext());
        TextView tv=new TextView(getApplicationContext());
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
    void popupInfo(){

    }
}
