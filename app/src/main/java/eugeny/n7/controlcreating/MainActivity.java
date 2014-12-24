package eugeny.n7.controlcreating;

import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;


public class MainActivity extends ActionBarActivity implements ColorPickerView.OnColorChangedListener, Button.OnClickListener {

    private final String TAG = "MAIN_ACTIVITY";

    private Button btn;
    private SurfaceView surfaceView;
    private ColorPickerView colorPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        surfaceView = (SurfaceView)findViewById(R.id.surfaceView);
        colorPicker = (ColorPickerView)findViewById(R.id.colorPicker);
        colorPicker.setOnColorChangedListener(this);
        btn = (Button)findViewById(R.id.button);
        btn.setOnClickListener(this);
    }

    @Override
    public void onColorChanged(int color) {
        surfaceView.setBackgroundColor(color);
    }

    @Override
    public void onClick(View v) {
        colorPicker.setColor(Color.BLUE);
    }
}
