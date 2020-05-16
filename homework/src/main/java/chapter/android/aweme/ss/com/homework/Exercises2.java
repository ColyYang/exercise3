package chapter.android.aweme.ss.com.homework;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * 作业2：一个抖音笔试题：统计页面所有view的个数
 * Tips：ViewGroup有两个API
 * {@link android.view.ViewGroup #getChildAt(int) #getChildCount()}
 * 用一个TextView展示出来
 */
public class Exercises2 extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercise2_relativelayout);

        TextView centerTextView = findViewById(R.id.tv_center);
        ViewGroup allViews = findViewById(R.id.exercise_view);
        centerTextView.setText(String.valueOf(getAllChildViewCount(allViews)));

    }

    public int getAllChildViewCount(View view) {
        //todo 补全你的代码

        int viewCount = 0;

        if (null == view) {
            return 0;
        }

        if (view instanceof ViewGroup) {
            viewCount++;
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View sonView = ((ViewGroup) view).getChildAt(i);
                if (sonView instanceof ViewGroup) {
                    viewCount += getAllChildViewCount(sonView);
                } else {
                    viewCount++;
                }
            }
        }

        return viewCount;

        //return 0;
    }
}
