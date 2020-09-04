package com.kencorp.gads2020leaderboard.utils;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;

import com.kencorp.gads2020leaderboard.R;

/**
 * TODO: document your custom view class.
 */
public class CircleImage extends View {

    private final RectF mOval = new RectF();
    private final Rect src = new Rect();
    private float mSweepAngle = 0;
    private int startAngle = 90;
    private int angleGap = 4;
    private Bitmap icon;


    float mEndAngle = 100.0f;

    Paint progressPaint = new Paint();
    TextPaint textPaint = new TextPaint();
    Paint incompletePaint = new Paint();
    Paint percentagePaint = new Paint();

    private float strokeWidth = 50.0f;

    public CircleImage(Context context, AttributeSet attrs) {
        super(context, attrs);


        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.CircleImage,
                0, 0);

        int textColor;
        float textSize;

        int progressColor;
        int incompleteColor;

        try {
            //textColor = a.getColor(R.styleable.ProgressCircle_ProgressCircle_android_textColor, Color.RED);
            textSize = a.getDimension(R.styleable.CircleImage_ProgressCircle_android_textSize, 100);

            strokeWidth = a.getDimension(R.styleable.CircleImage_ProgressCircle_strokeWidth, 30.0f);

            progressColor = a.getColor(R.styleable.CircleImage_ProgressCircle_progressColor, Color.GREEN);
            incompleteColor = a.getColor(R.styleable.CircleImage_ProgressCircle_incompleteProgressColor, Color.DKGRAY);
        } finally {
            a.recycle();
        }

        progressPaint.setColor(progressColor);
        progressPaint.setStrokeWidth(strokeWidth);
        progressPaint.setStyle(Paint.Style.STROKE);
        progressPaint.setFlags(Paint.ANTI_ALIAS_FLAG);

        textPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        // textPaint.setColor(textColor);
        textPaint.setTextSize(textSize);
        Typeface tf = Typeface.create("Roboto Condensed Light", Typeface.BOLD);
        textPaint.setTypeface(tf);

        percentagePaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        // percentagePaint.setColor(textColor);
        percentagePaint.setTextSize(textSize / 3);

        incompletePaint.setColor(incompleteColor);
        incompletePaint.setStrokeWidth(strokeWidth);
        incompletePaint.setStyle(Paint.Style.STROKE);
        incompletePaint.setFlags(Paint.ANTI_ALIAS_FLAG);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float currentAngleGap = mSweepAngle == 1.0f || mSweepAngle == 0 ? 0 : angleGap;
        mOval.set(strokeWidth / 2, strokeWidth / 2, getWidth() - (strokeWidth / 2), getWidth() - (strokeWidth / 2));
        canvas.drawArc(mOval, -startAngle + currentAngleGap, (mSweepAngle * 360) - currentAngleGap, false,
                progressPaint);

        Log.d("onDrawProgress","dimension"+String.valueOf(getWidth())+"X"+String.valueOf(getHeight()));

        mOval.set(strokeWidth / 2, strokeWidth / 2, getWidth() - (strokeWidth / 2), getWidth() - (strokeWidth / 2));
        canvas.drawArc(mOval, mSweepAngle * 360- startAngle + currentAngleGap, 360 - (mSweepAngle * 360) - currentAngleGap, false,
                incompletePaint);

        //drawText(canvas, textPaint, String.valueOf((int) (mSweepAngle * 100)), percentagePaint);


        if(icon != null){

            // src.set((int)(strokeWidth/4 ), (int)(strokeWidth/4 ), (int)(icon.getWidth() - (strokeWidth/4)), (int)(icon.getWidth() - (strokeWidth/2 )));

            //src.set(0,0, icon.getWidth()/2 , icon.getHeight() );
            Log.d("onDrawProgress","dimension Bitmap icon :"+String.valueOf(icon.getWidth())+"X"+String.valueOf(icon.getHeight()));

            //canvas.drawCircle(icon.getWidth()/2+0.7f,icon.getHeight()/2+0.7f,icon.getWidth()/2+0.1f,progressPaint);
            // canvas.drawBitmap(icon,src,src,null);
            //canvas.drawBitmap(icon,src,mOval,progressPaint);
            //  canvas.drawBitmap(icon,strokeWidth/4,strokeWidth/4,null);
            // canvas.drawBitmap(icon,(int)strokeWidth*2,(int)strokeWidth*2,null);
            //canvas.drawBitmap(icon,(int)(strokeWidth),(int)strokeWidth,null);
            //canvas.drawBitmap(icon,(int)(strokeWidth+2),(int)(strokeWidth-2),null);

            canvas.drawBitmap(icon, canvas.getWidth() / 2 - icon.getWidth() / 2, (canvas.getHeight() / 2)-icon.getHeight()/2, null);
            //final Paint paint = new Paint();
            //final Rect rect = new Rect(0, 0, icon.getWidth(),
            //      icon.getHeight());


            //paint.setAntiAlias(true);
            //paint.setFilterBitmap(true);
            //paint.setDither(true);
            //canvas.drawARGB(0, 0, 0, 0);
            //paint.setColor(Color.parseColor("#BAB399"));
            //canvas.drawCircle(icon.getWidth() /2, icon.getHeight() /2, icon.getWidth() / 2, progressPaint);
            //paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
            //canvas.drawBitmap(icon, rect, rect, progressPaint);
            // canvas.drawBitmap(icon,(int)(strokeWidth+2),(int)(strokeWidth-2),progressPaint);


        }
        // canvas.drawBitmap(icon, canvas.getWidth() / 2 - icon.getWidth() / 2, strokeWidth + (canvas.getHeight() / 15), null);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }
    private void drawText(Canvas canvas, Paint paint, String text, Paint percentagePaint) {
        Rect bounds = new Rect();
        paint.getTextBounds(text, 0, text.length(), bounds);
        Rect percentageBounds = new Rect();
        percentagePaint.getTextBounds("%", 0, 1, percentageBounds);
        int x = (canvas.getWidth() / 2) - (bounds.width() / 2) - (percentageBounds.width() / 2);
        int y = (canvas.getHeight() / 2) + (bounds.height() / 2);
        canvas.drawText(text, x, y, paint);

        canvas.drawText("%", x + bounds.width() + (percentageBounds.width() / 2), y - bounds.height() + percentageBounds.height(), percentagePaint);
    }





    public void setProgress(float progress) {
        if (progress > 1.0f || progress < 0) {
            throw new RuntimeException("Value must be between 0 and 1: " + progress);
        }

        mEndAngle = progress;

        this.invalidate();
    }

    public void setIcon(Bitmap icon) {

        int w = icon.getWidth(), h = icon.getHeight();

        this.icon = getRoundedCroppedBitmap(icon, w);
        //this.icon = Bitmap.createBitmap(icon.getWidth(),
        //        icon.getHeight(), Bitmap.Config.ARGB_8888);
        // this.icon = icon;

    }

    public void startAnimation() {
        ValueAnimator anim = ValueAnimator.ofFloat(mSweepAngle, mEndAngle);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                CircleImage.this.mSweepAngle = (Float) valueAnimator.getAnimatedValue();
                CircleImage.this.invalidate();
            }

        });
        anim.setDuration(500);
        anim.setInterpolator(new AccelerateDecelerateInterpolator());
        anim.start();

    }

    public static Bitmap getRoundedCroppedBitmap(Bitmap bitmap, int radius) {
        Bitmap finalBitmap;
        if (bitmap.getWidth() != radius || bitmap.getHeight() != radius)
            finalBitmap = Bitmap.createScaledBitmap(bitmap, radius, radius,
                    false);
        else
            finalBitmap = bitmap;
        Bitmap output = Bitmap.createBitmap(finalBitmap.getWidth(),
                finalBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, finalBitmap.getWidth(),
                finalBitmap.getHeight());

        Log.d("onDrawProgress","dimension Bitmap image :"+String.valueOf(finalBitmap.getWidth())+"X"+String.valueOf(finalBitmap.getHeight()));

        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setDither(true);
        paint.setColor(Color.RED);
        // paint.setStrokeWidth(10);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(Color.parseColor("#BAB399"));
        canvas.drawCircle(finalBitmap.getWidth() /2, finalBitmap.getHeight() /2, finalBitmap.getWidth() / 2, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(finalBitmap, rect, rect, paint);

        return output;
    }



}
