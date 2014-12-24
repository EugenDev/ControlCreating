package eugeny.n7.controlcreating;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
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

    private int mCurrentColor;

    private int GetCurrentColor(float x){
        float g = x * 360f / gradientRect.width();
        return Color.HSVToColor(new float [] {g, 1f, 1f});
    }

    private void init() {

        this.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                int action = MotionEventCompat.getActionMasked(event);

                switch (action) {

                    case (MotionEvent.ACTION_MOVE):
                    case (MotionEvent.ACTION_DOWN):
                        trackerRect.offsetTo(event.getX(), trackerRect.top);
                        int newColor = GetCurrentColor(event.getX());
                        if(newColor != mCurrentColor) {
                            invalidate();
                            mCurrentColor = newColor;
                            OnColorChanged(mCurrentColor);
                        }
                        break;
                }
                return true;
            }
        });

        generateGradientColors();
        initPaints();
    }

    private int mWidth;
    private int mHeight;

    private Rect gradientRect;
    private RectF trackerRect;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        mWidth = MeasureSpec.getSize(widthMeasureSpec);
        mHeight = mWidth / 10;

        if(gradientRect == null) {
            gradientRect = new Rect(0, 0, mWidth, mHeight);
        }

        if(trackerRect == null) {
            trackerRect = new RectF(0, 0, 20, mHeight);
        }

        setMeasuredDimension(mWidth, mHeight);
    }

    private int[] mColors;
    private void generateGradientColors() {
        int HSV_RANGE = 361;
        mColors = new int[HSV_RANGE];

        for(int i = 0; i < HSV_RANGE; i++) {
            mColors[i] = Color.HSVToColor(new float[] {i, 1f, 1f});
        }
    }

    private Paint mGradientPaint;
    private Shader gradientShader;
    private Paint mTrackerPaint;

    private void initPaints(){
        mGradientPaint = new Paint();

        mTrackerPaint = new Paint();
        mTrackerPaint.setColor(Color.BLACK);
        mTrackerPaint.setStyle(Paint.Style.STROKE);
        mTrackerPaint.setStrokeWidth(3);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //Выполниться только единожды, не будет вызывать проблем с производительностью
        if(gradientShader == null) {
            gradientShader = new LinearGradient(0, 0, mWidth, mHeight, mColors, null, Shader.TileMode.CLAMP);
            mGradientPaint.setShader(gradientShader);
        }

        canvas.drawRect(gradientRect, mGradientPaint);
        canvas.drawRoundRect(trackerRect, 2, 2, mTrackerPaint);
    }

    private OnColorChangedListener mListener;

    private void OnColorChanged(int color){
        if(mListener != null){
            mListener.onColorChanged(color);
        }
    }

    public void setOnColorChangedListener(OnColorChangedListener listener){
        mListener = listener;
    }

    public interface OnColorChangedListener {
        public void onColorChanged(int color);
    }
}