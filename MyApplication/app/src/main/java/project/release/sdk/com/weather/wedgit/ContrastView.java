package project.release.sdk.com.weather.wedgit;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;

import java.util.List;

import project.release.sdk.com.weather.bean.WeatherBeans;
import project.release.sdk.com.weather.utils.DensityUtils;

/**
 * @author 左成城
 * @desc
 * @date 2017/1/3 17:20
 */
public class ContrastView extends View {
    private List<WeatherBeans> mListData;
    private Paint mPaint;
    private int mStartX, mStartY;
    private int mEndX, mEndY;
    private int mWidth,mHeight;
    private int mDefaultCircle = 0;
    private float releaseValue = 0;

    public void init(Context context) {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.BLACK);
        mPaint.setTextSize(DensityUtils.px2dip(context,16));
        mDefaultCircle = 4;
    }


    public List<WeatherBeans> getListData() {
        return mListData;
    }

    public void setListData(List<WeatherBeans> listData) {
        mListData = listData;
//        mMax = Collections.max(mListData);
//        mMin = Collections.min(mListData);
        postInvalidate();
    }

    public ContrastView(Context context) {
        super(context);
        init(context);


    }

    public ContrastView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        Log.e("!!!","---");
        mPaint.setColor(Color.BLACK);
        canvas.drawLine(mStartX, mEndY, mEndX, mEndY, mPaint);
        //竖线
        canvas.drawLine(mStartX, mStartY, mStartX, mEndY, mPaint);
        //画竖线刻度
        if (mListData == null){
            return;
        }
        if (mListData.size() <= 0){
            return;
        }
        float d = mHeight / 50;
        float xd = mWidth /mListData.size();

        Rect rects = new Rect();
        mPaint.getTextBounds("最高温度", 0, "最高温度".toString().length(), rects);
        int strHighW = rects.width();
        int strHighH = rects.height();

        Log.e("!!!",strHighH+"----"+strHighW+"---");
        int width = 30;
        mPaint.setColor(Color.RED);
        canvas.drawLine(mWidth-strHighW-width,mStartY+strHighH/2,mWidth-strHighW,mStartY+strHighH/2,mPaint);
        canvas.drawText("最高温度",mWidth-strHighW+5,mStartY+strHighH,mPaint);

        mPaint.setColor(Color.BLUE);
        canvas.drawLine(mWidth-strHighW-width,mStartY+strHighH/2*3,mWidth-strHighW,mStartY+strHighH/2*3,mPaint);

        canvas.drawText("最低温度",mWidth-strHighW+5,mStartY+strHighH*2,mPaint);

        Path path = new Path();
        path.moveTo(30, mHeight-(mListData.get(0).getHigh()*d));
        for (int j = 0;j<mListData.size();j++){

            Rect rect = new Rect();
            mPaint.getTextBounds(mListData.get(j).getHigh()+"", 0, (mListData.get(j).getHigh()+"").toString().length(), rect);
            int strW = rect.width();
            int strH = rect.height();
            mPaint.setColor(Color.RED);
            Log.e("-----",mListData.get(j).getHigh()+"");
            canvas.drawText(mListData.get(j).getHigh()+"",xd*j-strW/2+30,mHeight-(mListData.get(j).getHigh()*d)-strH,mPaint);


            Rect rectDay = new Rect();
            mPaint.getTextBounds(mListData.get(j).getDay(), 0, mListData.get(j).getDay().length(), rect);
            int strDayW = rectDay.width();
            int strDayH = rectDay.height();
            mPaint.setColor(Color.BLACK);

            canvas.drawText(mListData.get(j).getDay(),xd*j-strDayW/2+30,mHeight+60+strDayH,mPaint);


            mPaint.setStyle(Paint.Style.FILL);
            mPaint.setColor(Color.YELLOW);
            canvas.drawCircle(xd*j+30,mHeight-(mListData.get(j).getHigh()*d),mDefaultCircle,mPaint);

            path.lineTo(xd*j+30,  mHeight-(mListData.get(j).getHigh()*d));
            mPaint.setColor(Color.RED);
            mPaint.setAntiAlias(true);
            mPaint.setStyle(Paint.Style.STROKE);

            canvas.drawPath(path, mPaint);
        }

        Path path1 = new Path();
        path1.moveTo(30, mHeight-(mListData.get(0).getLow()*d));
        for (int j = 0;j<mListData.size();j++){
            Rect rect = new Rect();
            mPaint.getTextBounds(mListData.get(j).getLow()+"", 0, (mListData.get(j).getLow()+"").toString().length(), rect);
            int strW = rect.width();
            int strH = rect.height();
            mPaint.setColor(Color.BLUE);
            canvas.drawText(mListData.get(j).getLow()+"",xd*j-strW/2+30,mHeight-(mListData.get(j).getLow()*d)+strH*2,mPaint);
            mPaint.setStyle(Paint.Style.FILL);
            mPaint.setColor(Color.YELLOW);
            canvas.drawCircle(xd*j+30,mHeight-(mListData.get(j).getLow()*d),mDefaultCircle,mPaint);
            path1.lineTo(xd*j+30,  mHeight-(mListData.get(j).getLow()*d));
            mPaint.setColor(Color.BLUE);
            mPaint.setAntiAlias(true);
            mPaint.setStyle(Paint.Style.STROKE);
            canvas.drawPath(path1, mPaint);
        }

        mPaint.reset();
        super.onDraw(canvas);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mStartX = 30;
        mStartY = 30;
        mEndX = w-30;
        mEndY = h-30;
        mWidth = mEndX-mStartX;
        mHeight = mEndY - mStartY;
    }

    private void startAnimation(){
        final ValueAnimator animator = ValueAnimator.ofFloat(0.0f, 1.0f);
        animator.setInterpolator(new LinearInterpolator());
        animator.setDuration(500);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {

                releaseValue = (float) animation.getAnimatedValue();
                Log.e("---",releaseValue+"==");
                postInvalidate();
            }
        });
        animator.setRepeatMode(ValueAnimator.RESTART);
        animator.setInterpolator(new OvershootInterpolator());
        animator.start();
    }
}

