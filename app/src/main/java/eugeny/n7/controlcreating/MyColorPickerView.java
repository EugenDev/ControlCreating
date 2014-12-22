package eugeny.n7.controlcreating;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposeShader;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.RectF;
import android.graphics.Shader;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class MyColorPickerView extends View {

    private Paint paint;
    private Shader luar;
    final float[] color = {1.f, 1.f, 1.f};
    private final String TAG = "ColorPicker";

    public MyColorPickerView(Context context) {
        super(context);
        init();
    }

    public MyColorPickerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyColorPickerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private int[] buildHueColorArray(){

        int[] hue = new int[361];

        int count = 0;
        for(int i = hue.length -1; i >= 0; i--, count++){
            hue[count] = Color.HSVToColor(new float[]{i, 1f, 1f});
        }

        return hue;
    }

    private void init() {

        this.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                int action = MotionEventCompat.getActionMasked(event);

                switch (action) {
                    case (MotionEvent.ACTION_DOWN):
                        Log.i(TAG, "Action was DOWN");
                        break;

                    case (MotionEvent.ACTION_MOVE):
                        Log.i(TAG, "Action was MOVE");
                        break;

                    case (MotionEvent.ACTION_UP):
                        Log.i(TAG, "Action was UP");
                        break;

                }
                return true;
            }
        });

        _paint = new Paint();
        Shader mHueShader = new LinearGradient(100, 100, 100, 100, buildHueColorArray(), null, Shader.TileMode.CLAMP);
        //Shader mHueShader = new LinearGradient(0, 0, 100, 100, 100, 300, Shader.TileMode.CLAMP);
        _paint.setShader(mHueShader);
        _paint.setStrokeWidth(2);
    }

    private Paint _paint;
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        Paint p = new Paint();
//        p.setStrokeWidth(2);
//        p.setColor(Color.RED);
        int colors[] = new int[100];
        float pos[] = new float[100];

        for(int i = 0; i < 100; i++) {
            colors[i] = Color.HSVToColor(new float[] {i, 1f, 1f});
            pos[i] = 5 * i;
        }

        _paint = new Paint();
        Shader mHueShader = new LinearGradient(60, 30, 60, 510, colors, pos, Shader.TileMode.CLAMP);
        //Shader mHueShader = new LinearGradient(0, 0, 100, 100, 100, 300, Shader.TileMode.CLAMP);
        _paint.setShader(mHueShader);
        _paint.setStrokeWidth(2);

        canvas.drawRect(10, 10, 100, 500, _paint);
    }
}