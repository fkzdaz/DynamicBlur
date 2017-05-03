package com.example.fkz2.dynamicblur;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.qiushui.blurredview.BlurBitmap;

public class MainActivity extends AppCompatActivity {

    //原始图片控件
    private ImageView mOriginImg;
    //模糊后的图片控件
    private ImageView mBluredImage;
    //进度条SeekBar
    private SeekBar mSeekBar;
    //显示进度文字
    private TextView mProgressTv;
    //透明度
    private int mAlpha;
    //原始图片
    private Bitmap mTemBitmap;
    //模糊后的图片
    private Bitmap mFinalBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始视图
        initView();

        //获取图片
        mTemBitmap= BitmapFactory.decodeResource(getResources(),R.mipmap.meinv);
        mFinalBitmap= BlurBitmap.blur(this, mTemBitmap);

        //填充模糊后的图像和原图
        mBluredImage.setImageBitmap(mFinalBitmap);
        mOriginImg.setImageBitmap(mTemBitmap);

        //处理seekbar滑动事件

        setSeekBar();
    }

    private void setSeekBar() {
        mSeekBar.setMax(100);
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                mAlpha=i;
                mOriginImg.setAlpha((int) (255-mAlpha*2.55));
                mProgressTv.setText(String.valueOf(mAlpha));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void initView() {

        mBluredImage= (ImageView) findViewById(R.id.img);
        mOriginImg= (ImageView) findViewById(R.id.img2);
        mSeekBar= (SeekBar) findViewById(R.id.seekbar);
        mProgressTv= (TextView) findViewById(R.id.tv);

    }
}
