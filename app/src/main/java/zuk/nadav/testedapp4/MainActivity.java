package zuk.nadav.testedapp4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

import nadav.tasher.lightool.Light;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        LinearLayout ll=findViewById(R.id.linearLayout1);
        Toast.makeText(this,"Screen: "+ Light.Device.screenX(getApplicationContext()) + "x"+ Light.Device.screenY(this),Toast.LENGTH_SHORT).show();






        setContentView(R.layout.activity_main);
    }
    LinearLayout getApp(){
    return null;
    }
    void popupInfo(){

    }
}
