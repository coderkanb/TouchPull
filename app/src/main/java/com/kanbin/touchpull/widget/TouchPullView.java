package com.kanbin.touchpull.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by SONY on 2017/8/23.
 */

public class TouchPullView extends View {
    // 圆的画笔
    private Paint mCirclePaint;
    // 圆的半径
    private int mCircleRadius = 150;

    public TouchPullView(Context context) {
        super(context);
        init();
    }

    public TouchPullView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TouchPullView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public TouchPullView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    /**
     * 初始化方法
     */
    private void init() {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        // 设置抗锯齿
        paint.setAntiAlias(true);
        // 设置防抖动
        paint.setDither(true);
        // 设置填充方式
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(0xFF000000);
        mCirclePaint = paint;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float x = getWidth() >> 1;
        float y = getHeight() >> 1;
        canvas.drawCircle(x, y, mCircleRadius, mCirclePaint);
    }
}
