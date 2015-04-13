package witype.systembartintdemo;

import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.readystatesoftware.systembartint.SystemBarTintManager;


public class MainActivity extends ActionBarActivity {

    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        linearLayout = (LinearLayout) findViewById(R.id.linear);
        initSystenBar();
        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.color.primary));
    }

    //判断android 版本然后设置Systembar颜色
    public void initSystenBar(){
        // 只支持android >= KITKAT的版本
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.KITKAT){
            setTranslucentStatus(true);
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);//开启SystembarTint
            tintManager.setStatusBarTintResource(R.color.primary_dark);//设置Systembar颜色
            //因为使用此种方式会导致整个activity的位置向上移动了Systembar的高度，因此需要设置你activity中             //控件的padinntTop避免这个问题
            SystemBarTintManager.SystemBarConfig config = tintManager.getConfig();
            linearLayout.setPadding(0, config.getPixelInsetTop(true), 0, config.getPixelInsetBottom());
        }
    }

    //设置systembar透明
    private void setTranslucentStatus(boolean status){
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if(status)
            winParams.flags |= bits;
        else
            winParams.flags &= ~bits;
        win.setAttributes(winParams);
    }
}
