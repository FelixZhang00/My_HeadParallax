package felixzhang.eample.my_headparallax;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import felixzhang.eample.my_headparallax.view.ParallaxListView;


public class MainActivity extends ActionBarActivity {

    private ParallaxListView mListView;
    private View mHeadView;     //listview的头部
    private MyAdapter mAdapter;

    //填充listview的数据
    private static ArrayList<String> lists = new ArrayList<String>();

    static {
        for (int i = 0; i < 25; i++) {
            lists.add("" + i);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = (ParallaxListView) findViewById(R.id.listview);
        mHeadView = View.inflate(this, R.layout.head, null);            //异步解析xml中的布局
        mListView.addHeaderView(mHeadView);

        mListView.setOverScrollMode(View.OVER_SCROLL_NEVER);        //设置滑到顶部和底部的效果
        final ImageView parallaxImageView = (ImageView) mHeadView.findViewById(R.id.imageView);

        //当从xml中加载完成后，才能知道imageview的长高
        mHeadView.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {

            @SuppressLint("NewApi")
            @Override
            public void onGlobalLayout() {
                mListView.setParallaxImageView(parallaxImageView);

                mHeadView.getViewTreeObserver().removeOnGlobalLayoutListener(this);  //取消当前的观察者
            }
        });

        mAdapter = new MyAdapter(this, R.layout.list_item, lists);
        mListView.setAdapter(mAdapter);

    }

    class MyAdapter extends ArrayAdapter<String> {
        Context mContext;
        int mResource;

        public MyAdapter(Context context, int resource, List<String> objects) {
            super(context, resource, objects);
            mContext = context;
            mResource = resource;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = View.inflate(mContext, mResource, null);
            }
            TextView tv = (TextView) convertView.findViewById(R.id.textview);
            tv.setText(getItem(position));

            return convertView;
        }
    }
}
