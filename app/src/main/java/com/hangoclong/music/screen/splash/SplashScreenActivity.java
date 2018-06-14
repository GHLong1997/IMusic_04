package com.hangoclong.music.screen.splash;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Toast;
import com.hangoclong.music.R;
import com.hangoclong.music.screen.BaseActivity;
import com.hangoclong.music.screen.main.MainActivity;
import com.hangoclong.music.utils.Constant;
import com.hangoclong.music.utils.common.PermissionUtils;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Admin on 3/19/2018.
 */

public class SplashScreenActivity extends BaseActivity {

    private static final int DELAY_2900 = 2000;
    private static final int DURATION_2000 = 2000;
    private static final float TO_0 = 0.0f;
    private static final float TO_1 = 1.0f;
    private static final float TO_610 = -610f;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);
        if (PermissionUtils.requestPermission(this)) {
            initViews();
            handles();
        }
    }

    private void initViews() {
        CircleImageView imageLogo = findViewById(R.id.image_logo);

        AnimationSet animationSetImage = new AnimationSet(true);
        RotateAnimation rotateAnimationImage =
                new RotateAnimation(Constant.FROM_0, Constant.TO_360, Animation.RELATIVE_TO_SELF,
                        Constant.PIVOT_0_5, Animation.RELATIVE_TO_SELF, Constant.PIVOT_0_5);
        rotateAnimationImage.setDuration(DURATION_2000);
        ScaleAnimation scaleAnimationImage =
                new ScaleAnimation(Constant.FROM_0, TO_1, Constant.FROM_0, TO_1);
        scaleAnimationImage.setDuration(Constant.DURATION_1500);
        animationSetImage.addAnimation(rotateAnimationImage);
        animationSetImage.addAnimation(scaleAnimationImage);
        imageLogo.startAnimation(animationSetImage);

        AnimationSet animationSetTextSlogan = new AnimationSet(true);
        TranslateAnimation translateAnimationText =
                new TranslateAnimation(Constant.FROM_0, TO_0, Constant.FROM_0, TO_610);
        translateAnimationText.setDuration(DURATION_2000);
        AlphaAnimation alphaAnimationText = new AlphaAnimation(Constant.FROM_0, TO_1);
        alphaAnimationText.setDuration(DURATION_2000);
        animationSetTextSlogan.addAnimation(translateAnimationText);
        animationSetTextSlogan.addAnimation(alphaAnimationText);
    }

    private void handles() {
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }, DELAY_2900);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
            @NonNull int[] grantResults) {
        switch (requestCode) {
            case Constant.REQUEST_WRITE_EXTERNAL_STORAGE: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, R.string.granted, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, R.string.no_granted, Toast.LENGTH_SHORT).show();
                }
                initViews();
                handles();
                return;
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
