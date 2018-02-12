package com.artmurka.artmurkaapp.Other;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.FloatMath;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.artmurka.artmurkaapp.R;


public class ZoomImageView extends android.support.v7.widget.AppCompatImageView implements View.OnTouchListener {

    final static float MIN_DISTANCE_TO_MOVE = 10;
    final static float MIN_SEPARATION_TO_ZOOM = 10;
    final int NONE = 0;
    final int DRAG = 1;
    final int ZOOM = 2;
    final int ADJUSTING = 3;
    final int CENTERING_IMAGE = 4;

    int doubleClickTimeInMillis;

    int mode = NONE;

    float maxZoomLevel, minZoomLevel, doubleClickZoomLevel;
    boolean isMinZoomLevelRelative, isMaxZoomLevelRelative;
    boolean adjustToBounds, doubleClickAdjust;
    boolean allowExcedLimitsWhenMovingImage;
    boolean blockImageInTheMiddle;

    float oldDistance = 1f;
    PointF start = new PointF();
    PointF mid = new PointF();

    boolean areValuesInitialized = false;
    int imageWidth = 0, imageHeight = 0, width, height;

    Matrix matrix = new Matrix();
    Matrix savedMatrix = new Matrix();
    private long lastClickTimeInMillis = 0;
    private float[] initialMatrixValues;

    public ZoomImageView(Context context) {
        super(context);
        setOnTouchListener(this);
    }

    public ZoomImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOnTouchListener(this);
        parseAttributes(context, attrs);
    }


    public ZoomImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setOnTouchListener(this);
        parseAttributes(context, attrs);
    }

    private void parseAttributes(Context context, AttributeSet attrs) {
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs, R.styleable.ZoomImageView, 0, 0);
        try {
            maxZoomLevel = a.getFloat(R.styleable.ZoomImageView_maximumZoomLevel, 3f);
            minZoomLevel = a.getFloat(R.styleable.ZoomImageView_mininumZoomLevel, 1f);
            doubleClickZoomLevel = a.getFloat(R.styleable.ZoomImageView_doubleClickZoomLevel, 2f);
            isMaxZoomLevelRelative = a.getBoolean(R.styleable.ZoomImageView_isMaximumZoomLevelRelativeToView, true);
            isMinZoomLevelRelative = a.getBoolean(R.styleable.ZoomImageView_isMinimumZoomLevelRelativeToView, true);
            adjustToBounds = a.getBoolean(R.styleable.ZoomImageView_adjustToBounds, true);
            doubleClickAdjust = a.getBoolean(R.styleable.ZoomImageView_doubleClickAdjust, true);
            allowExcedLimitsWhenMovingImage = a.getBoolean(R.styleable.ZoomImageView_allowExcedLimitsWhenMovingImage, false);
            blockImageInTheMiddle = a.getBoolean(R.styleable.ZoomImageView_blockImageInTheMidleWhileIsSmallerThanView, true);
            doubleClickTimeInMillis = a.getInteger(R.styleable.ZoomImageView_doubleClickTimeInMillis, 250);
        } finally {
            a.recycle();
        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        if (!areValuesInitialized){
            initializeValues();
        }
        if (mode == CENTERING_IMAGE){
            return true;
        }

        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                savedMatrix.set(matrix);
                start.set(event.getX(), event.getY());
                mode = DRAG;
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                oldDistance = spacing(event);
                if (oldDistance > 10f) {
                    savedMatrix.set(matrix);
                    midPoint(mid, event);
                    mode = ZOOM;
                }
                break;
            case MotionEvent.ACTION_UP:
                checkClick(event);
                if (mode != CENTERING_IMAGE){
                    postAdjust();
                }
            case MotionEvent.ACTION_POINTER_UP:
                setModeToNoneIfNotAdjustingOrCenteringView();
                break;
            case MotionEvent.ACTION_MOVE:
                if (mode == DRAG) {
                    moveImage(event);
                } else if (mode == ZOOM) {
                    scaleImage(event);
                }
                preAdjust();
                break;
        }

        return true;
    }

    private void initializeValues() {
        initWidthAndHeightValues();
        initMaxAndMinZoomLevel();
        initMatrix();
    }

    private void initWidthAndHeightValues() {
        imageWidth = getDrawable().getIntrinsicWidth();
        imageHeight = getDrawable().getIntrinsicHeight();
        width = getWidth();
        height = getHeight();
    }

    private void initMaxAndMinZoomLevel() {
        Matrix aux = getImageMatrix();
        matrix.set(aux);

        initialMatrixValues = new float[9];
        matrix.getValues(initialMatrixValues);
        if (isMinZoomLevelRelative){
            minZoomLevel = initialMatrixValues[0]* minZoomLevel;
        }
        if (isMaxZoomLevelRelative){
            maxZoomLevel = initialMatrixValues[0]* maxZoomLevel;
        }
    }

    private void initMatrix() {
        setImageMatrix(matrix);
        setScaleType(ScaleType.MATRIX);
        areValuesInitialized = true;
    }

    private void setModeToNoneIfNotAdjustingOrCenteringView() {
        mode = (mode == CENTERING_IMAGE) || (mode == ADJUSTING)?mode:NONE;
    }

    private void checkClick(MotionEvent event) {
        int xDiff = (int) Math.abs(event.getX() - start.x);
        int yDiff = (int) Math.abs(event.getY() - start.y);
        if (xDiff < 8 && yDiff < 8) {
            click();
            checkIfDoubleClick();
        }
    }

    private void checkIfDoubleClick() {
        if ((System.currentTimeMillis() - lastClickTimeInMillis) < doubleClickTimeInMillis){
            doubleClick();
            lastClickTimeInMillis = 0;
        } else {
            lastClickTimeInMillis = System.currentTimeMillis();
        }
    }

    private void doubleClick() {
        if (!doubleClickAdjust)
            return;
        float f[] = new float[9];
        matrix.getValues(f);
        mode = CENTERING_IMAGE;
        if (!areTheSameValues(f, initialMatrixValues)){
            interpolateMatrixToValue(initialMatrixValues);
        } else { // centering to double zoom
            float centeredValuesWithZoom[] = calculateMatrixValuesAfterAdjustingWindowZoom(doubleClickZoomLevel);
            interpolateMatrixToValue(centeredValuesWithZoom);
        }
    }

    private boolean areTheSameValues(float[] f1, float[] f2) {
        return (f1[0] == f2[0] && f1[1] == f2[1] && f1[2] == f2[2] &&
                f1[3] == f2[3] && f1[4] == f2[4] && f1[5] == f2[5] &&
                f1[6] == f2[6] && f1[7] == f2[7] && f1[8] == f2[8]);
    }

    private void click() {
    }

    private void moveImage(MotionEvent event) {
        if (spacing(event.getX(), start.x, event.getY(), start.y)<MIN_DISTANCE_TO_MOVE){
            return;
        }
        matrix.set(savedMatrix);
        matrix.postTranslate(event.getX() - start.x, event.getY() - start.y);
        setImageMatrix(matrix);
    }

    private void scaleImage(MotionEvent event) {
        float newDistance = spacing(event);
        if (newDistance > MIN_SEPARATION_TO_ZOOM) {
            matrix.set(savedMatrix);
            float scale = newDistance / oldDistance;
            matrix.postScale(scale, scale, mid.x, mid.y);
            setImageMatrix(matrix);
        }
    }

    private void preAdjust(){
        if (adjustToBounds && !allowExcedLimitsWhenMovingImage){
            float[] destineMatrix = calculateObjectiveMatrix();
            matrix.setValues(destineMatrix);
            setImageMatrix(matrix);
        }
    }

    private void postAdjust(){
        if (adjustToBounds && allowExcedLimitsWhenMovingImage){
            mode = ADJUSTING;
            float[] destineMatrix = calculateObjectiveMatrix();
            interpolateMatrixToValue(destineMatrix);
        }
    }

    private float[] calculateObjectiveMatrix(){
        float objectiveMatrixValues[] = calculateMatrixValuesAfterAdjustingWindowZoom();
        PointF imagePosition = new PointF(objectiveMatrixValues[2],objectiveMatrixValues[5]);
        float horizontalMargin = calculateWidthMargin(objectiveMatrixValues);
        float verticalMargin = calculateHeightMargin(objectiveMatrixValues);

        if (imageWidthBiggerThanView(objectiveMatrixValues)){
            if (-imagePosition.x > horizontalMargin){
                objectiveMatrixValues[2] = -horizontalMargin;
            } else if (imagePosition.x > 0) {
                objectiveMatrixValues[2] = 0;
            }
        } else { // image width smaller than view
            float marginRight = horizontalMargin;
            float marginLeft = 0;

            if (blockImageInTheMiddle){
                marginLeft = (width - imageWidth*objectiveMatrixValues[0])/2;
                marginRight = -marginLeft;
            }

            if (-imagePosition.x < marginRight){
                objectiveMatrixValues[2] = -marginRight;
            } else if (imagePosition.x < marginLeft) {
                objectiveMatrixValues[2] = marginLeft;
            }
        }
        if (imageHeightBiggerThanView(objectiveMatrixValues)){
            if (-imagePosition.y > verticalMargin){
                objectiveMatrixValues[5] = -verticalMargin;
            } else if (imagePosition.y > 0) {
                objectiveMatrixValues[5] = 0;
            }
        } else { // image height smaller than view
            float marginBottom = verticalMargin;
            float marginTop = 0;

            if (blockImageInTheMiddle){
                marginTop = (height - imageHeight*objectiveMatrixValues[0])/2;
                marginBottom = -marginTop;
            }
            if (-imagePosition.y < marginBottom){
                objectiveMatrixValues[5] = -marginBottom;
            } else if (imagePosition.y < marginTop) {
                objectiveMatrixValues[5] = marginTop;
            }
        }
        return objectiveMatrixValues;
    }

    private float[] calculateMatrixValuesAfterAdjustingWindowZoom() {
        float f[] = new float[9];
        Matrix aux = new Matrix();
        aux.set(matrix);
        aux.getValues(f);

        if (f[0] > maxZoomLevel) {
            aux.postScale(maxZoomLevel / f[0], maxZoomLevel / f[0], mid.x, mid.y);
        }
        if (f[0] < minZoomLevel) {
            aux.postScale(minZoomLevel / f[0], minZoomLevel / f[0], mid.x, mid.y);
        }
        aux.getValues(f);
        return f;
    }

    private float[] calculateMatrixValuesAfterAdjustingWindowZoom(float scale) {
        float f[] = new float[9];
        Matrix aux = new Matrix();
        aux.set(matrix);
        aux.getValues(f);
        aux.postScale(scale, scale, width/2, height/2);
        aux.getValues(f);
        return f;
    }


    private boolean imageWidthBiggerThanView(float[] objectiveMatrixValues) {
        return objectiveMatrixValues[0]*imageWidth>= getWidth();
    }

    private boolean imageHeightBiggerThanView(float[] objectiveMatrixValues) {
        return (objectiveMatrixValues[0]*imageHeight>= height);
    }

    private float calculateWidthMargin(float[] objectiveMatrixValues) {
        return (imageWidth*objectiveMatrixValues[0] - width);
    }

    private float calculateHeightMargin(float[] objectiveMatrixValues) {
        return (imageHeight*objectiveMatrixValues[0] - height);
    }

    private void interpolateMatrixToValue(final float destinyMatrixValues[]){
        final float originMatrixValues[] = new float[9];
        matrix.getValues(originMatrixValues);

        final Interpolator interpolator = new AccelerateDecelerateInterpolator();
        final long startTime = System.currentTimeMillis();
        final long duration = 400;
        post(new Runnable() {
            @Override
            public void run() {
                float tempMatrix[] = new float[9];

                float t = (float) (System.currentTimeMillis() - startTime) / duration;
                t = t > 1.0f ? 1.0f : t;
                float interpolatedRatio = interpolator.getInterpolation(t);

                for (int i = 0; i < 9; i++){
                    tempMatrix[i] =
                            originMatrixValues[i] +
                                    interpolatedRatio * (destinyMatrixValues[i] - originMatrixValues[i]);
                }

                matrix.setValues(tempMatrix);
                setImageMatrix(matrix);
                if ((t < 1f) && ((mode == ADJUSTING) || mode == CENTERING_IMAGE)) {
                    post(this);
                } else {
                    if ((mode == CENTERING_IMAGE) || (mode == ADJUSTING)) {
                        mode = NONE;
                    }
                }
            }
        });

     //   setLayoutParams(new RelativeLayout.LayoutParams(width, height));
    }

    private float spacing(MotionEvent event) {
        float x = event.getX(0) - event.getX(1);
        float y = event.getY(0) - event.getY(1);
        return spacing(event.getX(0), event.getX(1),
                event.getY(0), event.getY(1));
    }
    private float spacing(float x0, float x1, float y0, float y1){
        float a = x0 - x1;
        float b = y0 - y1;
        return (float) Math.sqrt(a * a + b * b);
    }

    private void midPoint(PointF point, MotionEvent event) {
        float x = event.getX(0) + event.getX(1);
        float y = event.getY(0) + event.getY(1);
        point.set(x / 2, y / 2);
    }
}