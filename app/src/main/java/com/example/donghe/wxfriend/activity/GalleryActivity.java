package com.example.donghe.wxfriend.activity;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.donghe.wxfriend.PhotoViewPager;
import com.example.donghe.wxfriend.R;

import java.util.List;

import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * 图片查看
 * <p/>
 * Created by hedong on 2016/6/30.
 */
public class GalleryActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    // ViewPager对象
    private PhotoViewPager mViewPager;
    // 原图url路径List
    private List<String> imagePath;
    // 当前显示的位置
    private int position;

    private TextView tvCurrent, tvAllPage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_gallery);

        // 获取参数
        position = getIntent().getIntExtra("position", 0);
        this.imagePath = getIntent().getStringArrayListExtra("imagePath");
        mViewPager = (PhotoViewPager) findViewById(R.id.images_view);
        tvCurrent = (TextView) findViewById(R.id.tv_current);
        tvAllPage = (TextView) findViewById(R.id.tv_all_img);
        tvAllPage.setText("/" + imagePath.size());
        tvCurrent.setText("" + (position + 1));
        // 设置左右两列缓存的数目
        mViewPager.setOffscreenPageLimit(2);
        // 添加Adapter
        mViewPager.setAdapter(new SamplePagerAdapter());
        mViewPager.setCurrentItem(0);
        mViewPager.setOnPageChangeListener(this);


    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        tvCurrent.setText((position + 1) + "");
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    class SamplePagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return imagePath.size();
        }

        @Override
        public View instantiateItem(ViewGroup container, int position) {
            final PhotoView photoView = new PhotoView(container.getContext());
            Glide.with(container.getContext()).load(imagePath.get(position)).into(photoView);
            //给view做标记
            photoView.setId(position);

            //单击退出
            photoView.setOnViewTapListener(new PhotoViewAttacher.OnViewTapListener() {
                @Override
                public void onViewTap(View view, float x, float y) {
                    GalleryActivity.this.finish();
                }
            });

            container.addView(photoView, ViewPager.LayoutParams.MATCH_PARENT,
                    ViewPager.LayoutParams.MATCH_PARENT);

            return photoView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        if (imagePath != null) {
            imagePath.clear();
        }
        if (mViewPager != null) {
            mViewPager = null;
        }
    }
}
