package eugeny.n7.controlcreating;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class ColorPickerView extends View {

    private final String TAG = "ColorPicker";

    public ColorPickerView(Context context) {
        super(context);
        init();
    }

    public ColorPickerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ColorPickerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    private Paint _paint;

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
        _paint.setColor(Color.rgb(0, 100, 0));
        _paint.setStyle(Paint.Style.FILL_AND_STROKE);
    }
}