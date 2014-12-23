package eugeny.n7.controlcreating;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class MyColorPickerView extends View {

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

        generateColors();
        initPaints();
    }

    private int mWidth;
    private int mHeight;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        mWidth = MeasureSpec.getSize(widthMeasureSpec);
        mHeight = mWidth / 10;

        setMeasuredDimension(mWidth, mHeight);
    }

    private int[] mColors;
    private void generateColors() {
        int HSV_RANGE = 361;
        mColors = new int[HSV_RANGE];

        for(int i = 0; i < HSV_RANGE; i++) {
            mColors[i] = Color.HSVToColor(new float[] {i, 1f, 1f});
        }
    }

    private Paint mainPaint;
    private Paint mTrackerPaint;
    private Shader mHueShader;

    private void initPaints(){
        mainPaint = new Paint();
        mTrackerPaint = new Paint();
        mTrackerPaint.setColor(Color.BLACK);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mHueShader = new LinearGradient(0, 0, mWidth, mHeight, mColors, null, Shader.TileMode.CLAMP);
        mainPaint.setShader(mHueShader);
        canvas.drawRect(0, 0, mWidth, mHeight, mainPaint);

        //TODO: draw tracker, please
    }
}