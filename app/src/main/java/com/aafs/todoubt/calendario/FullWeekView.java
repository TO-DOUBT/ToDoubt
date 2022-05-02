package com.aafs.todoubt.calendario;

import android.content.Context;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.WeekView;

import java.util.List;

public class FullWeekView extends WeekView {
    private Paint mRectPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint mSchemeBasicPaint = new Paint();


    public FullWeekView(Context context) {
        super(context);

        mRectPaint.setStyle(Paint.Style.STROKE);
        mRectPaint.setStrokeWidth(dipToPx(context, 0.5f));
        mRectPaint.setColor(0x88efefef);

        mSchemeBasicPaint.setAntiAlias(true);
        mSchemeBasicPaint.setStyle(Paint.Style.FILL);
        mSchemeBasicPaint.setTextAlign(Paint.Align.CENTER);
        mSchemeBasicPaint.setColor(0xffed5353);
        mSchemeBasicPaint.setFakeBoldText(true);

        setLayerType(View.LAYER_TYPE_SOFTWARE, mSchemeBasicPaint);

        mSelectedPaint.setMaskFilter(new BlurMaskFilter(50, BlurMaskFilter.Blur.SOLID));
    }

    @Override
    protected boolean onDrawSelected(Canvas canvas, Calendar calendar, int x, boolean hasScheme) {
        mSelectedPaint.setStyle(Paint.Style.FILL);
        canvas.drawRect(x,0, x + mItemWidth, mItemHeight , mSelectedPaint);
        return true;
    }

    @SuppressWarnings("IntegerDivisionInFloatingPointContext")
    @Override
    protected void onDrawScheme(Canvas canvas, Calendar calendar, int x) {
        mSchemeBasicPaint.setColor(calendar.getSchemeColor());
        List<Calendar.Scheme> schemes = calendar.getSchemes();
        if (schemes == null || schemes.size() == 0) {
            return;
        }
        int space = dipToPx(getContext(), 2);
        int indexY = mItemHeight - 2 * space;
        int sw = dipToPx(getContext(), mItemWidth / 10);
        int sh = dipToPx(getContext(), 4);
        for (Calendar.Scheme scheme : schemes) {

            mSchemePaint.setColor(scheme.getShcemeColor());

            canvas.drawRect(x + mItemWidth - sw -  2 * space,

                    indexY - sh, x + mItemWidth - 2 * space, indexY, mSchemePaint);
            indexY = indexY - space -sh;
        }
    }

    @SuppressWarnings("IntegerDivisionInFloatingPointContext")
    @Override
    protected void onDrawText(Canvas canvas, Calendar calendar, int x, boolean hasScheme, boolean isSelected) {
        canvas.drawRect(x, 0, x + mItemWidth,  mItemHeight, mRectPaint);
        int cx = x + mItemWidth / 2;
        int top = - mItemHeight / 6;

        boolean isInRange = isInRange(calendar);

        if (isSelected) {
            canvas.drawText(String.valueOf(calendar.getDay()), cx, mTextBaseLine + top,
                    mSelectTextPaint);
        } else if (hasScheme) {
            canvas.drawText(String.valueOf(calendar.getDay()), cx, mTextBaseLine + top,
                    calendar.isCurrentMonth() && isInRange ? mSchemeTextPaint : mOtherMonthTextPaint);
        } else {
            canvas.drawText(String.valueOf(calendar.getDay()), cx, mTextBaseLine + top,
                    calendar.isCurrentDay() ? mCurDayTextPaint :
                            calendar.isCurrentMonth() && isInRange ? mCurMonthTextPaint : mOtherMonthTextPaint);
        }
    }

    private static int dipToPx(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
