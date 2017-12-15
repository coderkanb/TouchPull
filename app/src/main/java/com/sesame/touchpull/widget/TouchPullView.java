package com.sesame.touchpull.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by SONY on 2017/8/23.
 */

public class TouchPullView extends View {
    private static final String TAG = TouchPullView.class.getSimpleName();

    // 圆的画笔
    private Paint mCirclePaint;
    // 圆的半径
    private int mCircleRadius = 150;
    private int mCirclePointX, mCirclePointY;

    // 可拖动的高度
    private int mDragHeight = 800;

    // 进度值
    private float mProgress;

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

        canvas.drawCircle(mCirclePointX,
                mCirclePointY,
                mCircleRadius,
                mCirclePaint);
    }

    /**
     * 当进行测量时触发
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        // 宽度的意图，宽度的类型
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        int iHeight = (int) ((mDragHeight * mProgress + 0.5f) + getPaddingTop() + getPaddingBottom());
        int iWidth = 2 * mCircleRadius + getPaddingLeft() + getPaddingRight();

        int measureWidth, measureHeight;

        if (widthMode == MeasureSpec.EXACTLY) {
            // 确切的
            measureWidth = width;
        } else if (widthMode == MeasureSpec.AT_MOST) {
            // 最多的
            measureWidth = Math.min(iWidth, width);
        }else {
            measureWidth = iWidth;
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            // 确切的
            measureHeight = height;
        } else if (heightMode == MeasureSpec.AT_MOST) {
            // 最多的
            measureHeight = Math.min(iHeight, height);
        }else {
            measureHeight = iHeight;
        }

        // 设置测量的高度宽度
        setMeasuredDimension(measureWidth, measureHeight);
    }

    /**
     * 当大小改变时触发
     *
     * @param w
     * @param h
     * @param oldw
     * @param oldh
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mCirclePointX = getWidth() >> 1;
        mCirclePointY = getHeight() >> 1;

    }

    /**
     * 设置进度
     *
     * @param progress
     */
    public void setProgress(float progress) {
        Log.d(TAG, "setProgress: " + progress);
        mProgress = progress;
        // 请求重新进行测量
        requestLayout();
    }
}
