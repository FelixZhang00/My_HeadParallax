package felixzhang.eample.my_headparallax;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    private ListView mListView;
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
        mListView = (ListView) findViewById(R.id.listview);
        mHeadView=View.inflate(this,R.layout.head,null);            //异步解析xml中的布局
        mListView.addHeaderView(mHeadView);


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
