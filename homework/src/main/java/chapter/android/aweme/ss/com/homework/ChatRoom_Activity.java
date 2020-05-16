package chapter.android.aweme.ss.com.homework;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class ChatRoom_Activity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.test);

        TextView textview = findViewById(R.id.textView);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Bundle bundle = extras.getBundle("user");
            if (bundle != null) {
                textview.setText("我是第" + bundle.getString("title") + "个item");
            }
        }

    }
}
