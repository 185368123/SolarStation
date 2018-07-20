package com.shuorigf.solarstaition.data.linechart;

import java.util.List;

/**
 * auther: clx on 17/10/26.
 */

public class LineInfoData {
    public List<Float> mYVal;
    public int mLineColor;
    public boolean mDrawFilled;
    public boolean mDrawCircleHole;
    public LineInfoData(List<Float> yVal, int lineColor, boolean drawFilled, boolean drawCircleHole) {
        this.mYVal = yVal;
        this.mLineColor = lineColor;
        this.mDrawFilled = drawFilled;
        this.mDrawCircleHole = drawCircleHole;
    }


}
