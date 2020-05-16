package chapter.android.aweme.ss.com.homework;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;


/**
 * 大作业:实现一个抖音消息页面,
 * 1、所需的data数据放在assets下面的data.xml这里，使用PullParser这个工具类进行xml解析即可
 * <p>如何读取assets目录下的资源，可以参考如下代码</p>
 * <pre class="prettyprint">
 *
 *         @Override
 *     protected void onCreate(@Nullable Bundle savedInstanceState) {
 *         super.onCreate(savedInstanceState);
 *         setContentView(R.layout.activity_xml);
 *         //load data from assets/data.xml
 *         try {
 *             InputStream assetInput = getAssets().open("data.xml");
 *             List<Message> messages = PullParser.pull2xml(assetInput);
 *             for (Message message : messages) {
 *
 *             }
 *         } catch (Exception exception) {
 *             exception.printStackTrace();
 *         }
 *     }
 * </pre>
 * 2、所需UI资源已放在res/drawable-xxhdpi下面
 *
 * 3、作业中的会用到圆形的ImageView,可以参考 widget/CircleImageView.java
 */
public class Exercises3 extends AppCompatActivity implements GreenAdapter.ListItemClickListener {

    private static final String TAG = "YangRui";
    private static final int NUM_LIST_ITEMS = 10;

    private GreenAdapter mAdapter;
    private RecyclerView mNumbersListView;

    private Toast mToast;


/*
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_tips);


    }
*/

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips);

        mNumbersListView = findViewById(R.id.rv_list);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        mNumbersListView.setLayoutManager(layoutManager);
        /*
         * Use this setting to improve performance if you know that changes in content do not
         * change the child layout size in the RecyclerView
         */
        mNumbersListView.setHasFixedSize(true);

        /*
         * The GreenAdapter is responsible for displaying each item in the list.
         */
        mAdapter = new GreenAdapter(NUM_LIST_ITEMS, this);

        mNumbersListView.setAdapter(mAdapter);
        mNumbersListView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            // 最后一个完全可见项的位置
            private int lastCompletelyVisibleItemPosition;

            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                int visibleItemCount = layoutManager.getChildCount();
                int totalItemCount = layoutManager.getItemCount();
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    if (visibleItemCount > 0 && lastCompletelyVisibleItemPosition >= totalItemCount - 1) {
                     //   Toast.makeText(RecycleViewActivity.this, "已滑动到底部!,触发loadMore", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                if (layoutManager instanceof LinearLayoutManager) {
                    lastCompletelyVisibleItemPosition = ((LinearLayoutManager) layoutManager).findLastCompletelyVisibleItemPosition();
                }
                Log.d(TAG, "onScrolled: lastVisiblePosition=" + lastCompletelyVisibleItemPosition);
            }
        });
    }

    @Override
    public void onListItemClick(String titleindex) {
        Log.d(TAG, "onListItemClick: " + titleindex);

        //携带数据，跳转到新的界面
        Intent intent = new Intent(this, ChatRoom_Activity.class);
        Bundle bundle = new Bundle();
        bundle.putString("title", titleindex);
        intent.putExtra("user", bundle);
        startActivity(intent);
//        if (mToast != null) {
//            mToast.cancel();
//        }
//        String toastMessage = "Item #" + clickedItemIndex + " clicked.";
//        mToast = Toast.makeText(this, toastMessage, Toast.LENGTH_LONG);
//
//        mToast.show();
//
//
//        //实现recycleView点击item进行页面跳转
//        //测试时发现，跳转页面后显示空白
//   //     Intent intent = new Intent(Exercises3.this, ChatRoom_Activity.class);
//    //        startActivity(new Intent(this, ChatRoom_Activity.class));
//   //     startActivity(intent);


    }



}
