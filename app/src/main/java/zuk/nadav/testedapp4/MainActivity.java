package zuk.nadav.testedapp4;

import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.List;

import nadav.tasher.lightool.Light;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        LinearLayout ll=findViewById(R.id.linearLayout1);
        Toast.makeText(this,"Screen: "+ Light.Device.screenX(getApplicationContext()) + "x"+ Light.Device.screenY(this),Toast.LENGTH_SHORT).show();
        //getting a list of apps{
        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        List<ResolveInfo> pkgAppsList = getPackageManager().queryIntentActivities( mainIntent, 0);
        //getting a list of apps}
        for(int i=0;i<pkgAppsList.size();i++){
            LinearLayout currentApp=getApp((String)pkgAppsList.get(i).loadLabel(getPackageManager()),pkgAppsList.get(i).loadIcon(getPackageManager()));
            //TODO add app view (currentApp) to Layout

        }
        setContentView(R.layout.activity_main);
    }
    LinearLayout getApp(String name,Drawable icon){
    return null;
    }
    void popupInfo(){

    }
}
