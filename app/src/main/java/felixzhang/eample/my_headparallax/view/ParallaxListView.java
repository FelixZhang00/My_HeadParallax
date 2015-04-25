package felixzhang.eample.my_headparallax.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.ListView;

import felixzhang.eample.my_headparallax.view.anim.ResetHeightAnimation;

/**
 * Created by felix on 15/4/25.
 */
public class ParallaxListView extends ListView {

    public ParallaxListView(Context context) {
        super(context);
    }

    public ParallaxListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ParallaxListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private ImageView parallaxImageView;
    private int maxHeight;
    private int originalHeight;

    public void setParallaxImageView(ImageView parallaxImageView) {
        this.parallaxImageView = parallaxImageView;
        originalHeight = parallaxImageView.getHeight();

        //获得imagview上图片的原始高度，即为imageview的最高度
        maxHeight = parallaxImageView.getDrawable().getIntrinsicHeight();

    }


    /**
     * 当ListView被滑动到顶部和底部时会调用此方法
     *
     * @param deltaY         y方向滑动的距离。 正：底部到头;负：顶部到头
     * @param maxOverScrollY 到头后，最大可滚动的范围
     * @param isTouchEvent   是否是触摸滑动。true:手指拖动到头;false:惯性滑动到头。
     * @return
     */
    @Override
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX, int scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {
        if (deltaY < 0 && isTouchEvent) {
            int newHeight = parallaxImageView.getHeight() - deltaY / 3;     //新的高度增加与手指拖动的距离不成正比，
            //达到拖动吃力的效果
            if (newHeight > maxHeight) newHeight = maxHeight;
            if (parallaxImageView != null) {
                parallaxImageView.getLayoutParams().height = newHeight;
                parallaxImageView.requestLayout();                              //当完成高度设置后，需要调用重新布局方法
            }
        }

        return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX, scrollRangeY, maxOverScrollX, maxOverScrollY, isTouchEvent);

    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_UP:
                ResetHeightAnimation animation = new ResetHeightAnimation(parallaxImageView, originalHeight);
                startAnimation(animation);
                break;
        }
        return super.onTouchEvent(ev);
    }
}
