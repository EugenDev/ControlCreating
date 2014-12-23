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

        generateColorsAndPositions();

        initPaint();
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
    private float[] mPositions;
    private final int HSV_RANGE = 361;

    private void generateColorsAndPositions() {

        mColors = new int[HSV_RANGE];
        mPositions = new float[HSV_RANGE];

        for(int i = 0; i < HSV_RANGE; i++) {
            mColors[i] = Color.HSVToColor(new float[] {i, 1f, 1f});
            mPositions[i] = mWidth * i / HSV_RANGE;
        }
    }

    private void initPaint(){
        mPaint = new Paint();
        Shader mHueShader = new LinearGradient(0, mHeight / 2, mWidth, mHeight / 2, mColors, mPositions, Shader.TileMode.CLAMP);
        mPaint.setStrokeWidth(2);
        mPaint.setShader(mHueShader);
    }

    private Paint mPaint;
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(0, 0, mWidth, mHeight, mPaint);
    }
}