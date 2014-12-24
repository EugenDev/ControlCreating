package eugeny.n7.controlcreating;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceView;


public class MainActivity extends ActionBarActivity implements MyColorPickerView.OnColorChangedListener {

    private final String TAG = "MAIN_ACTIVITY";

    private SurfaceView surfaceView;
    private MyColorPickerView colorPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        surfaceView = (SurfaceView)findViewById(R.id.surfaceView);
        colorPicker = (MyColorPickerView)findViewById(R.id.colorPicker);
        colorPicker.setOnColorChangedListener(this);
    }


    @Override
    public void onColorChanged(int color) {
        surfaceView.setBackgroundColor(color);
        Log.d(TAG, "Color = " + color);
    }
}
