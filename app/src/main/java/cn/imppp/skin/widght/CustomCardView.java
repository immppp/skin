package cn.imppp.skin.widght;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

/**
 * @ClassName: CustomCardView
 * @Author: gaoQl
 * @Description: 绘制一个带回弹效果的控件
 * @Date: 2020/12/23 9:20 AM
 * @Version: 1.0
 */

public class CustomCardView extends CardView {
    public CustomCardView(@NonNull Context context) {
        super(context);
    }

    public CustomCardView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomCardView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        Log.i("cardView", motionEvent.getAction() + "  ok");
        /*按下操作*/
        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
            ScaleAnimation animation = new ScaleAnimation(1.0f, 0.9f, 1.0f, 0.9f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            animation.setDuration(300);
            animation.setFillAfter(true);
            this.startAnimation(animation);
        }
        /*抬起操作*/
        if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
            ScaleAnimation animation = new ScaleAnimation(0.9f, 1.0f, 0.9f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            animation.setDuration(300);
            animation.setFillAfter(true);
            this.startAnimation(animation);
        }
        return true;
    }

}
