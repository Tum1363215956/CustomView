package com.tum.custumview;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.tum.custumview.D1SlidingView.D1SlidmenuActivity;
import com.tum.custumview.D2StatusMenu.D2StatusActivity;
import com.tum.custumview.D3ViewAnimation.D3AnimationActivity;

public class MainActivity extends ListActivity {

    private String[] mTitles = new String[]{
            D1SlidmenuActivity.class.getSimpleName(),
            D2StatusActivity.class.getSimpleName(),
            D3AnimationActivity.class.getSimpleName()
    };

    private Class[] mActivities = new Class[]{
            D1SlidmenuActivity.class,
            D2StatusActivity.class,
            D3AnimationActivity.class
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,mTitles));
        Logger.addLogAdapter(new AndroidLogAdapter());
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        startActivity(new Intent(this,mActivities[position]));
    }
}
