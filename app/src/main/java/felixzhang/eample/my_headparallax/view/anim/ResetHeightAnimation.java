package felixzhang.eample.my_headparallax.view.anim;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.OvershootInterpolator;
import android.view.animation.Transformation;

/**
 * Created by felix on 15/4/25.
 */
public class ResetHeightAnimation extends Animation {

    int mOriginalHeight, mTargetHeight;
    int totalValue;
    View mView;

    public ResetHeightAnimation(View view, int targetHeight) {
        super();
        mTargetHeight = targetHeight;
        mView = view;
        mOriginalHeight = mView.getHeight();
        totalValue = mTargetHeight - mOriginalHeight;

        setDuration(400);
        setInterpolator(new OvershootInterpolator()); //设置加速器
    }

    /**
     * @param interpolatedTime 标示动画执行的精度或者百分比
     *                         范围在0~1，对于每一个进度，我都可以在此方法中为制定的view设置属性，
     *                         达到动画额效果
     */
    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        super.applyTransformation(interpolatedTime, t);
        int newHeight = (int) (mOriginalHeight + totalValue * interpolatedTime);
        mView.getLayoutParams().height = newHeight;
        mView.requestLayout();

    }
}
