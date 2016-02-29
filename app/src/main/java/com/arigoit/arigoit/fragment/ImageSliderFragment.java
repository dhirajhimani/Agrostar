package com.arigoit.arigoit.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arigoit.arigoit.R;
import com.arigoit.arigoit.adapter.ImagePagerAdapter;
import com.viewpagerindicator.CirclePageIndicator;

/**
 * Created by dhiraj on 27-02-2016.
 */
public class ImageSliderFragment extends Fragment {

    private ViewPager viewPager;
    private CirclePageIndicator circleIndicator;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_imageslider, container, false);
        viewPager = (ViewPager) view.findViewById(R.id.view_pager);
        circleIndicator = (CirclePageIndicator) view.findViewById(R.id.indicator);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ImagePagerAdapter adapter = new ImagePagerAdapter(getActivity());
        viewPager.setAdapter(adapter);
        circleIndicator.setViewPager(viewPager);

    }
}
