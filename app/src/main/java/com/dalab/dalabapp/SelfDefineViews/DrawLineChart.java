package com.dalab.dalabapp.SelfDefineViews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import java.text.DecimalFormat;

import static java.lang.Math.min;

public class DrawLineChart extends View {
    /**
     * View宽度
     */
    private int mViewWidth;
    /**
     * View高度
     */
    private int mViewHeight;
    /**
     * 边框线画笔
     */
    private Paint mBorderLinePaint;
    /**
     * 文本画笔
     */
    private Paint mTextPaint;
    /**
     * 要绘制的折线线画笔
     */
    private Paint mBrokenLinePaint;
    /**
     * 折线文本画笔
     */
    private Paint mBrokenLineTextPaint;
    /**
     * 圆画笔
     */
    private Paint mCirclePaint;
    /**
     * 横线画笔
     */
    private Paint mHorizontalLinePaint;
    /**
     * 圆的厚度
     */
    private float mCircleWidth = 2;
    /**
     * 圆的半径
     */
    private float radius = 5;
    /**
     * 边框的左边距
     */
    private float mBrokenLineLeft = 80;
    /**
     * 边框的上边距
     */
    private float mBrokenLineTop = 40;
    /**
     * 边框的下边距
     */
    private float mBrokenLineBottom = 40;
    /**
     * 边框的右边距
     */
    private float mBrokenLinerRight = 20;
    /**
     * 需要绘制的宽度
     */
    private float mNeedDrawWidth;
    /**
     * 需要绘制的高度
     */
    private float mNeedDrawHeight;
    /**
     * 数据值
     */
    private float[] value = new float[]{-5.55f, -6.899f, -4.55f, -0.045f, 21.511f, 22.221f, 22.330f, 21.448f, 21.955f, 23.6612f, 22, 22.18883f, 21.47995f};
    private float[] time=new float[]{0,0,0};
    /**
     * 图表的最大值
     */
    private float maxVlaue = 27.55f;
    /**
     * 图表的最小值
     */
    private float minValue = -19.12f;

    /**
     * 图表的横坐标最大值
     */
    private float x_maxVlaue = 12000.0f;
    /**
     * 图表的横坐标最小值
     */
    private float x_minValue = 0f;
    private float current_maxVlaue = 0f;

    private float min2=5f;
    private float max2=25f;
    /**
     * 要计算的总值
     */
    private float calculateValue;
    /**
     * 框线平均值
     */
    private float averageValue;
    /**
     * 要计算的总值
     */
    private float x_calculateValue;
    /**
     * 框线平均值
     */
    private float x_averageValue;
    /**
     * 横线数量
     */
    private float numberLine = 10;
    /**
     * 横坐标的桩数量
     */
    private float x_numberLine = 10;
    /**
     * 边框线颜色
     */
    private int mBorderLineColor = Color.BLACK;
    /**
     * 边框线的宽度
     */
    private int mBorderWidth = 2;
    /**
     * 边框文本颜色
     */
    private int mBorderTextColor = Color.GRAY;
    /**
     * 边框文本大小
     */
    private float mBorderTextSize = 20;
    /**
     * 边框横线颜色
     */
    private int mBorderTransverseLineColor = Color.GRAY;
    /**
     * 边框横线宽度
     */
    private float mBorderTransverseLineWidth = 2;
    /**
     * 折线颜色
     */
    private int mBrokenLineColor = Color.BLUE;
    /**
     * 折线文本颜色
     */
    private int mBrokenLineTextColor = Color.BLUE;
    /**
     * 折线宽度
     */
    private float mBrokenLineWidth = 2;
    /**
     * 折线文本大小
     */
    private float mBrokenLineTextSize = 15;
    /**
     * 折线圆的颜色
     */
    private int mCircleColor = Color.BLUE;
    /**
     * 计算后的x，y坐标
     */
    private Point[] mPoints;

    //额外的上下限的大小以及画笔
    private float upper = 600;
    private float lower = 100;
    private Paint upperPaint;
    private Paint lowerPaint;


    /**
     * 圆的半径
     */
    public void setRadius(float radius) {
        this.radius = dip2px(radius);
    }

    /**
     * 设置宽度
     */
    public void setCircleWidth(float circleWidth) {
        this.mCircleWidth = dip2px(circleWidth);
    }

    /**
     * 设置边框左上右下边距
     */
    public void setBrokenLineLTRB(float l, float t, float r, float b) {
        mBrokenLineLeft = dip2px(l);
        mBrokenLineTop = dip2px(t);
        mBrokenLinerRight = dip2px(r);
        mBrokenLineBottom = dip2px(b);
    }

    public void setValid(float time)
    {
        //根据传入的有效时间去调整max2和min2
        if(time>=15 && time<=20)
        {
            max2=25;min2=5;
        }
        else if(20<time && time<=40)
        {
            max2=50;min2=10;
        }
        else if(40<time && time<=60)
        {
            max2=20;min2=100;
        }
    }

    /**
     * 数据data
     */
    public void setValue(float[] value) {
        this.value = value;
    }
    public void setTime(float[] time) {
        this.time = time;
    }

    /**
     * 图表显示最大值
     */
    public void setMaxVlaue(float maxVlaue) {
        this.maxVlaue = maxVlaue;
    }

    /**
     * 图表显示最小值
     */
    public void setMinValue(float minValue) {
        this.minValue = minValue;
    }

    public void setCurrent_maxVlaue(float current_maxVlaue) {
        this.current_maxVlaue = current_maxVlaue;
    }

    /**
     * 图表显示最大值
     */
    public void setX_MaxVlaue(float maxVlaue) {
        this.x_maxVlaue = maxVlaue;
    }

    /**
     * 图表显示最小值
     */
    public void setX_MinValue(float minValue) {
        this.x_minValue = minValue;
    }

    //范围的上下限
    public void setUpper(float upperValue) {
        upper = upperValue;
    }

    public void setLower(float lowerValue) {
        lower = lowerValue;
    }

    /**
     * 图表横线数量
     */
    public void setNumberLine(float numberLine) {
        this.numberLine = numberLine;
    }

    /**
     * 图表纵坐标的桩数量
     */
    public void setX_NumberLine(float numberLine) {
        this.x_numberLine = numberLine;
    }

    /**
     * 边框线颜色
     */
    public void setBorderLineColor(int borderLineColor) {
        mBorderLineColor = borderLineColor;
    }

    /**
     * 边框文本颜色
     */
    public void setBorderTextColor(int borderTextColor) {
        mBorderTextColor = borderTextColor;
    }

    /**
     * 边框文本大小
     */
    public void setBorderTextSize(float borderTextSize) {
        mBorderTextSize = dip2px(borderTextSize);
        Log.i("size", "onCreate: f");
        System.out.println(mBorderTextSize);
//        mBorderTextSize=(int)borderTextSize;
    }

    /**
     * 框线横线 颜色
     */
    public void setBorderTransverseLineColor(int borderTransverseLineColor) {
        mBorderTransverseLineColor = borderTransverseLineColor;
    }

    /**
     * 边框内折线颜色
     */
    public void setBrokenLineColor(int brokenLineColor) {
        mBrokenLineColor = brokenLineColor;
    }

    /**
     * 折线文本颜色
     */
    public void setBrokenLineTextColor(int brokenLineTextColor) {
        mBrokenLineTextColor = brokenLineTextColor;
    }

    /**
     * 折线文本大小
     */
    public void setBrokenLineTextSize(float brokenLineTextSize) {
        mBrokenLineTextSize = dip2px(brokenLineTextSize);
    }

    /**
     * 折线圆颜色
     */
    public void setCircleColor(int circleColor) {
        mCircleColor = dip2px(circleColor);
    }

    /**
     * 边框线宽度
     */
    public void setBorderWidth(float borderWidth) {
        mBorderWidth = dip2px(borderWidth);
    }

    /**
     * 边框横线宽度
     */
    public void setBorderTransverseLineWidth(float borderTransverseLineWidth) {
        mBorderTransverseLineWidth = dip2px(borderTransverseLineWidth);
    }

    /**
     * 折线宽度
     */
    public void setBrokenLineWidth(float brokenLineWidth) {
        mBrokenLineWidth = dip2px(brokenLineWidth);
    }

    public Paint getBorderLinePaint() {
        return mBorderLinePaint;
    }

    /**
     * 获取边框文本画笔
     */
    public Paint getTextPaint() {
        return mTextPaint;
    }

    /**
     * 获取折线画笔
     */
    public Paint getBrokenLinePaint() {
        return mBrokenLinePaint;
    }

    /**
     * 获取折线文本画笔
     */
    public Paint getBrokenLineTextPaint() {
        return mBrokenLineTextPaint;
    }

    /**
     * 获取圆画笔
     */
    public Paint getCirclePaint() {
        return mCirclePaint;
    }

    /**
     * 获取边框横线画笔
     */
    public Paint getHorizontalLinePaint() {
        return mHorizontalLinePaint;
    }

    /**
     * 将dip或dp值转换为px值，保证尺寸大小不变
     *
     * @param dipValue
     * @return
     */
    private int dip2px(float dipValue) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    public DrawLineChart(Context context) {
        super(context);
        initPaint();
    }

    public DrawLineChart(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mViewHeight = getMeasuredHeight();
        mViewWidth = getMeasuredWidth();

        /**计算总值*/
        calculateValue = maxVlaue - minValue;


        initNeedDrawWidthAndHeight();

        /**计算框线横线间隔的数据平均值*/
        averageValue = calculateValue / (numberLine - 1);

        x_calculateValue = x_maxVlaue - x_minValue;
        /**计算框线横坐标间隔的数据平均值*/
        x_averageValue = x_calculateValue / (x_numberLine - 1);

        initPaint();//什么时候需要init这个东西，才能够让我们的修改有作用...

    }


    /**
     * 初始化绘制折线图的宽高
     */
    private void initNeedDrawWidthAndHeight() {
        mNeedDrawWidth = mViewWidth - mBrokenLineLeft - mBrokenLinerRight;
        mNeedDrawHeight = mViewHeight - mBrokenLineTop - mBrokenLineBottom;
    }

    /**
     * 初始化画笔
     */
    private void initPaint() {

        /**初始化边框文本画笔*/
        if (mTextPaint == null) {
            mTextPaint = new Paint();
            initPaint(mTextPaint);
        }
        mTextPaint.setTextSize(mBorderTextSize);
        mTextPaint.setTextAlign(Paint.Align.RIGHT);
//        mTextPaint.setColor(Color.GRAY);
        mTextPaint.setColor(Color.BLACK);
        mTextPaint.setStyle(Paint.Style.FILL);
        /**初始化边框线画笔*/
        if (mBorderLinePaint == null) {
            mBorderLinePaint = new Paint();
            initPaint(mBorderLinePaint);
        }

        mBorderLinePaint.setTextSize(mBorderTextSize);
        mBorderLinePaint.setStrokeWidth(mBorderWidth);
        mBorderLinePaint.setColor(mBorderLineColor);

        /**初始化折线画笔*/
        if (mBrokenLinePaint == null) {
            mBrokenLinePaint = new Paint();
            initPaint(mBrokenLinePaint);

        }

        mBrokenLinePaint.setStrokeWidth(mBrokenLineWidth);
        mBrokenLinePaint.setColor(mBrokenLineColor);

        if (mCirclePaint == null) {
            mCirclePaint = new Paint();
            initPaint(mCirclePaint);
        }
        mCirclePaint.setStrokeWidth(mCircleWidth);
        mCirclePaint.setColor(mCircleColor);

        /**折线文本画笔*/
        if (mBrokenLineTextPaint == null) {
            mBrokenLineTextPaint = new Paint();
            initPaint(mBrokenLineTextPaint);
        }
        mBrokenLineTextPaint.setTextAlign(Paint.Align.CENTER);
        mBrokenLineTextPaint.setColor(mBrokenLineTextColor);
        mBrokenLineTextPaint.setTextSize(mBrokenLineTextSize);
        mBrokenLineTextPaint.setStyle(Paint.Style.FILL);

        /**横线画笔*/
        if (mHorizontalLinePaint == null) {
            mHorizontalLinePaint = new Paint();
            initPaint(mHorizontalLinePaint);
        }

        mHorizontalLinePaint.setStrokeWidth(mBorderTransverseLineWidth);
        mHorizontalLinePaint.setColor(mBorderTransverseLineColor);
//        mHorizontalLinePaint.setStyle(Paint.Style.FILL);
        /*上下限*/
        if (upperPaint == null) {
            upperPaint = new Paint();
            initPaint(upperPaint);
        }
        upperPaint.setStrokeWidth(mBorderTransverseLineWidth);
        upperPaint.setColor(Color.RED);
//        upperPaint.setStyle(Paint.Style.FILL);
        if (lowerPaint == null) {
            lowerPaint = new Paint();
            initPaint(lowerPaint);
        }
        lowerPaint.setStrokeWidth(mBorderTransverseLineWidth);
        lowerPaint.setColor(Color.rgb(255, 160, 0));
//        lowerPaint.setStyle(Paint.Style.FILL);
    }

    /**
     * 初始化画笔默认属性
     */
    private void initPaint(Paint paint) {
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        //文字可以不用stroke
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //如果把width修改了呢？固定成最大值
//        float actual_width = current_maxVlaue / x_maxVlaue * mNeedDrawWidth;//这里的1200就是训练总时间
//        mPoints = getPoints(value,mNeedDrawHeight,mNeedDrawWidth,calculateValue,minValue,mBrokenLineLeft,mBrokenLineTop);
        mPoints = updatePoint(value,time, mNeedDrawHeight, mNeedDrawWidth, calculateValue, minValue, mBrokenLineLeft, mBrokenLineTop);
        x_calculateValue = x_maxVlaue - x_minValue;
        /**计算框线横坐标间隔的数据平均值*/
        x_averageValue = x_calculateValue / (x_numberLine - 1);
        /**绘制边框线和边框文本*/
        DrawBorderLineAndText(canvas);

        /**根据数据绘制线*/
        DrawBrokenLine(canvas);

        /**绘制圆*/
        //如果画曲线就不用绘制圆，因为如果太多的化会很密集，不好。
//        DrawLineCircle(canvas);

        //绘制额外的bound上下限
        DrawBounds(canvas);

    }

    /**
     * 绘制线上的圆
     */
    private void DrawLineCircle(Canvas canvas) {
        for (int i = 0; i < mPoints.length; i++) {
            Point point = mPoints[i];
            mCirclePaint.setColor(Color.WHITE);
            mCirclePaint.setStyle(Paint.Style.FILL);
            canvas.drawCircle(point.x, point.y, radius, mCirclePaint);

            mCirclePaint.setColor(mCircleColor);
            mCirclePaint.setStyle(Paint.Style.STROKE);
            /**
             * drawCircle(float cx, float cy, float radius, Paint paint)
             * cx 中间x坐标
             * xy 中间y坐标
             * radius 圆的半径
             * paint 绘制圆的画笔
             * */
            canvas.drawCircle(point.x, point.y, radius, mCirclePaint);
        }
    }

    /**
     * 根据值绘制折线
     */
    private void DrawBrokenLine(Canvas canvas) {
        Path mPath = new Path();
        //画折线
//        for (int i = 0; i < mPoints.length; i++) {
//            Point point=mPoints[i];
//            if(i==0){
//                mPath.moveTo(point.x,point.y);
//            }else {
//                mPath.lineTo(point.x,point.y);
//            }
//            //其实折线文字可以不要,肯定不要，因为太密集了
//            //canvas.drawText(value[i]+"",point.x,point.y-radius,mBrokenLineTextPaint);
//        }
        //其实也可以画曲线...
        for (int j = 0; j < mPoints.length; j++) {
            Point startp = mPoints[j];
            Point endp;
            if (j != mPoints.length - 1) {
                endp = mPoints[j + 1];
                int wt = (startp.x + endp.x) / 2;
                Point p3 = new Point();
                Point p4 = new Point();
                p3.y = startp.y;
                p3.x = wt;
                p4.y = endp.y;
                p4.x = wt;
                if (j == 0) {
                    mPath.moveTo(startp.x, startp.y);
                }
                /*
                 *添加一个立方贝塞尔曲线的最后一点,接近控制点
                 *(x1,y1)和(x2,y2),到最后(x3,y3)。如果没有移至()调用
                 *为这个轮廓,第一点是自动设置为(0,0)。
                 *
                 * @param x1的坐标1立方曲线控制点
                 * @param y1第一控制点的坐标一立方曲线
                 * @param x2上第二个控制点的坐标一立方曲线
                 * @param y2第二控制点的坐标一立方曲线
                 * @param x3的坐标三次曲线的终点
                 * @param y3终点坐标的三次曲线
                 *
                 */
                mPath.cubicTo(p3.x, p3.y, p4.x, p4.y, endp.x, endp.y);
            }

        }
        canvas.drawPath(mPath, mBrokenLinePaint);
    }

    /**
     * 绘制边框线和边框文本
     */
    private void DrawBorderLineAndText(Canvas canvas) {
        /*对应的属性
         * drawLine(float startX, float startY, float stopX, float stopY, Paint paint);
         * startX   开始的x坐标
         * startY   开始的y坐标
         * stopX    结束的x坐标
         * stopY    结束的y坐标
         * paint    绘制该线的画笔
         * */

        /**绘制边框竖线*/
        canvas.drawLine(mBrokenLineLeft, mBrokenLineTop - dip2px(5), mBrokenLineLeft, mViewHeight - mBrokenLineBottom, mBorderLinePaint);
        /**绘制边框横线*/
        canvas.drawLine(mBrokenLineLeft, mViewHeight - mBrokenLineBottom, mViewWidth, mViewHeight - mBrokenLineBottom, mBorderLinePaint);


        /**绘制边框分段横线与分段文本*/
        float averageHeight = mNeedDrawHeight / (numberLine - 1);

        for (int i = 0; i < numberLine; i++) {
            float nowadayHeight = averageHeight * i;
            float v = averageValue * (numberLine - 1 - i) + minValue;

            /**最后横线无需绘制，否则会将边框横线覆盖*/
            if (i != numberLine - 1) {
//                canvas.drawLine(mBrokenLineLeft, nowadayHeight + mBrokenLineTop, mViewWidth, nowadayHeight + mBrokenLineTop, mHorizontalLinePaint);
                canvas.drawLine(mBrokenLineLeft, nowadayHeight + mBrokenLineTop, mBrokenLineLeft+dip2px(10), nowadayHeight + mBrokenLineTop, mHorizontalLinePaint);

            }
//            canvas.drawText(floatKeepTwoDecimalPlaces(v) + "", mBrokenLineLeft - dip2px(2), nowadayHeight + mBrokenLineTop, mTextPaint);
            canvas.drawText((int)(v) + "", mBrokenLineLeft - dip2px(2), nowadayHeight + mBrokenLineTop, mTextPaint);

        }

        /**绘制边框分段横坐标与分段文本*/
//        float averageWeight = mNeedDrawWidth / (x_numberLine - 1);
        float averageWeight = mNeedDrawWidth * 3/5 / 3;//第一部分，从0~3的weight,把3/5再分成三份
        float nowadayWeight=0;
        float v=0;//1,2,3
        for (int i = 1; i <=3; i++) {
            nowadayWeight = averageWeight * i;
            v = i;
                canvas.drawLine(mBrokenLineLeft + nowadayWeight , mViewHeight - mBrokenLineBottom, mBrokenLineLeft + nowadayWeight , mViewHeight - mBrokenLineBottom - dip2px(12), mBorderLinePaint);
                canvas.drawText((int)v + "", mBrokenLineLeft + nowadayWeight+dip2px(5), mViewHeight - mBrokenLineBottom + dip2px(13), mTextPaint);
        }
        //画3之后的那个,可以认为是参数min2，
        float distance=dip2px(15);
        float NewStart=mBrokenLineLeft + nowadayWeight+distance;
        canvas.drawLine(NewStart, mViewHeight - mBrokenLineBottom, NewStart , mViewHeight - mBrokenLineBottom - dip2px(12), mBorderLinePaint);
        canvas.drawText((int)min2 + "", mBrokenLineLeft + nowadayWeight+distance+dip2px(5), mViewHeight - mBrokenLineBottom + dip2px(13), mTextPaint);

        x_averageValue=(max2-min2)/4;
        averageWeight = (mNeedDrawWidth *2 /5 - distance) / 4;//第三部分，从min2到max2的weight,把剩下的再分成份
        for (int i = 1; i <=4; i++) {//四份
            nowadayWeight = averageWeight * i;
            v = x_averageValue * i + min2;

            /**最后横线无需绘制，否则会将边框横线覆盖*/
                canvas.drawLine(NewStart + nowadayWeight, mViewHeight - mBrokenLineBottom, NewStart + nowadayWeight , mViewHeight - mBrokenLineBottom - dip2px(12), mBorderLinePaint);
                canvas.drawText((int)(v) + "", NewStart + nowadayWeight+ dip2px(7), mViewHeight - mBrokenLineBottom + dip2px(13), mTextPaint);

        }

        /**竖线*/
        for (int i = 1; i < mPoints.length; i++) {
            //canvas.drawLine(mPoints[i].x,mBrok enLineTop,mPoints[i].x,mBrokenLineTop+mNeedDrawHeight,mBorderLinePaint);
        }

    }

    /**
     * 保留2位小数
     */
    private String floatKeepTwoDecimalPlaces(float f) {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");//构造方法的字符格式这里如果小数不足2位,会以0补足.
        String p = decimalFormat.format(f);
        return p;
    }

    private void DrawBounds(Canvas canvas) {
        float averageHeight = mNeedDrawHeight / (numberLine - 1);
        float upperHeight = (maxVlaue - upper) * averageHeight / averageValue;
        canvas.drawLine(mBrokenLineLeft, upperHeight + mBrokenLineTop, mViewWidth, upperHeight + mBrokenLineTop, upperPaint);
        canvas.drawText((int)(upper) + "", mBrokenLineLeft - dip2px(2), upperHeight + mBrokenLineTop, mTextPaint);

        float lowerHeight = (maxVlaue - lower) * averageHeight / averageValue;
        canvas.drawLine(mBrokenLineLeft, lowerHeight + mBrokenLineTop, mViewWidth, lowerHeight + mBrokenLineTop, lowerPaint);
        canvas.drawText((int)(lower) + "", mBrokenLineLeft - dip2px(2), lowerHeight + mBrokenLineTop, mTextPaint);

    }

    /**
     * 根据值计算在该值的 x，y坐标
     */
    public Point[] getPoints(float[] values, float height, float width, float max, float min, float left, float top) {

        float leftPading = width / (values.length - 1);//绘制边距
        Point[] points = new Point[values.length];
        for (int i = 0; i < values.length; i++) {
            double value = values[i] - min;
            //计算每点高度所以对应的值
            double mean = (double) max / height;
            //获取要绘制的高度
            float drawHeight = (float) (value / mean);
            int pointY = (int) (height + top - drawHeight);
            int pointX = (int) (leftPading * i + left);
            Point point = new Point(pointX, pointY);
            points[i] = point;
        }
        return points;
    }
    public Point[] updatePoint(float[] values,float[] times, float height, float width, float max, float min, float left, float top)
    {
        //需要知道每个点对应的时间才行...
        int length=min(values.length,times.length);
        Point[] points = new Point[length];

        for (int i = 0; i < values.length && i < times.length; i++) {
            float minute=times[i]/60;
            double value = values[i] - min;
            //计算每点高度所以对应的值
            double mean = (double) max / height;
            //获取要绘制的高度
            float drawHeight = (float) (value / mean);
            int pointY = (int) (height + top - drawHeight);
            //问题只发生在X坐标
            //分成三段处理
            if(minute<=3)
            {
                float leftPading = minute/3*3/5*width;//绘制边距
                int pointX = (int) (leftPading + left);
                Point point = new Point(pointX, pointY);
                points[i] = point;
            }
            else if(minute>3 && minute<=min2)
            {
                float leftPading = 3*width/5+ (minute-3)/(min2-3)*dip2px(15);//dip2px(15)即distance
                int pointX = (int) (leftPading + left);
                Point point = new Point(pointX, pointY);
                points[i] = point;
            }
            else if(minute<max2 && minute>min2){
                float leftPading = 3*width/5+dip2px(15)+ (minute-min2)/(max2-min2)*(2*width/5-dip2px(15));//dip2px(15)即distance
                int pointX = (int) (leftPading + left);
                Point point = new Point(pointX, pointY);
                points[i] = point;
            }
        }
        return points;
    }
}

