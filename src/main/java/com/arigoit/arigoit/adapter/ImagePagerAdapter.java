package com.arigoit.arigoit.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.arigoit.arigoit.R;

/**
 * Created by dhiraj on 27-02-2016.
 */
public class ImagePagerAdapter extends PagerAdapter {


    private final int[] mImages = new int[]{
            R.drawable.imageslide1,
            R.drawable.imageslide2,
    };
    private Context context;

    public ImagePagerAdapter(Context context) {
        this.context = context;
    }


    @Override
    public void destroyItem(final ViewGroup container, final int position, final Object view) {
        container.removeView((View) view);
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {
        LayoutInflater inflater = LayoutInflater.from(context);
        ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.slideimageitem, container, false);
        if (position % 2 == 0) {
            ((ImageView) layout.findViewById(R.id.slideImage)).setImageResource(this.mImages[0]);
        } else {
            ((ImageView) layout.findViewById(R.id.slideImage)).setImageResource(this.mImages[1]);
        }
        container.addView(layout);
        return layout;
    }

    @Override
    public boolean isViewFromObject(final View view, final Object object) {
        return view == object;
    }
}
