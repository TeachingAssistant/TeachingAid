package com.allen.teachingaid.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.allen.teachingaid.R;
import com.allen.teachingaid.adapter.StuItemAdapter;
import com.allen.teachingaid.config.Urls;
import com.allen.teachingaid.entity.JLogin;
import com.allen.teachingaid.ui.widget.DividerItemDecoration;
import com.allen.teachingaid.util.ToastUtil;
import com.allen.teachingaid.volley.VolleyManager;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RollcallActivity extends BaseActivity {
    public static final String TAG = "RollcallActivity";
    public static String course_id;
    @Bind(R.id.list_stu)
    RecyclerView mRecyclerView;
    StuItemAdapter stuItemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        showDialog();
        initData();

    }

    @Override
    public void initView() {
        setContentView(R.layout.activity_rollcall);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        course_id = bundle.getString("course_id");
        String course_name = bundle.getString("course_name");

        getSupportActionBar().setTitle("点名");
        getSupportActionBar().setSubtitle(course_name);
        //根据course_id获取此课程的所有学生

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        stuItemAdapter = new StuItemAdapter(this);
        mRecyclerView.setAdapter(stuItemAdapter);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(
                this, DividerItemDecoration.VERTICAL_LIST));
    }

    private void showDialog() {
    }

    private void initData() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.activity_rollcall, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_rollcall_commit) {
            ToastUtil.showShort("点名提交");
            //提交点名结果到服务器
            SparseBooleanArray sparseBooleanArray = stuItemAdapter.getStuStates();

            Map<String, Object> map = new HashMap<String, Object>();
            map.put("coures_id", course_id);
            map.put("stu", sparseBooleanArray);

            VolleyManager.newInstance().GsonPostRequest(TAG, map, Urls.LOGIN_URL, JLogin.class,
                    new Response.Listener<JLogin>() {
                        @Override
                        public void onResponse(JLogin jLogin) {
                            Log.d("111111111111111111111", "ok" + jLogin.getData());

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.d("111111111111111111111", "okhhhghhh");

                        }
                    });
            return true;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_left_in, R.anim.slide_right_out);
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onStop() {
        super.onStop();

    }
}
