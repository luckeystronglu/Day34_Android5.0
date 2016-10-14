package com.qf.a50view;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TextInputLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private FloatingActionButton fab;
    private Button button;

    private TextInputLayout username, password;

    /*
    * 材料设计(Material Design)
tablayout、FloatingActionButton、
Snackbar、TextInputLayout
    * */

    private TabLayout tabLayout;
    private ViewPager vp;
    private String[] datas = {"热门", "头条", "张靓颖", "三星爆炸门", "直播间", "高洪波离职", "小方不识高洪波"};
    private MyAdapter adapter;


    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

//    private Toolbar tb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(isOpenStatus())

        {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = getWindow();
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(Color.TRANSPARENT);
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            }

            //获得状态栏的高度
            int height = ScreenUtil.getStateBarHeight(this);
            if (height != -1) {
                //设置Padding
                View view = findViewById(R.id.actionbar);
                if (view != null) {
                    view.setPadding(0, height, 0, 0);
                }
            }
        }

        //使用ToolBar
//        tb = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(tb);


        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);

        button = (Button) findViewById(R.id.btn);
        username = (TextInputLayout) findViewById(R.id.et_username);
        password = (TextInputLayout) findViewById(R.id.et_password);

        EditText et = username.getEditText();
        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length() > 10){
                    username.setError("用户名的长度不能超过10");
                    username.setErrorEnabled(true);
                } else {
                    username.setErrorEnabled(false);
                }
            }
        });



        tabLayout = (TabLayout) findViewById(R.id.tab);
        vp = (ViewPager) findViewById(R.id.vp);
        adapter = new MyAdapter(this, datas);
        vp.setAdapter(adapter);
        //关联TabLayout和ViewPager
        tabLayout.setupWithViewPager(vp);


        //侧边菜单点击监听事件
        drawerLayout = (DrawerLayout) findViewById(R.id.dl);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(
                MainActivity.this,
                drawerLayout,
                null,   //tb
                R.string.app_name,
                R.string.app_name
        );
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();//同步状态

        navigationView = (NavigationView) findViewById(R.id.nv);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menu1:
                        Snackbar.make(navigationView, "点击了第一个菜单", Snackbar.LENGTH_SHORT).show();
                        break;
                    case R.id.menu2:
                        break;
                    case R.id.menu3:
                        break;
                    case R.id.menu4:
                        break;
                    case R.id.menu5:
                        break;
                }
                return true;
            }
        });
    }

    @Override
    public void onClick(View v) {
//        Toast.makeText(MainActivity.this, "点击了", Toast.LENGTH_SHORT).show();
        final Snackbar make = Snackbar.make(v, "Hello World!", Snackbar.LENGTH_INDEFINITE);
        make.setAction("退下吧！", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        make.dismiss();
                    }
                })
                .show();
    }

    class MyAdapter extends PagerAdapter{

        private Context context;
        private List<TextView> datas;

        public MyAdapter(Context context, String[] datas){
            this.datas = new ArrayList();
            for (String data : datas) {
                TextView tv = new TextView(context);
                tv.setText(data);
                tv.setTextSize(50);
                tv.setGravity(Gravity.CENTER);
                tv.setTextColor(Color.RED);
                this.datas.add(tv);
            }
        }

        @Override
        public int getCount() {
            return datas.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(datas.get(position));
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(datas.get(position));
            return datas.get(position);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return datas.get(position).getText().toString();
        }
    }


    /**
     * 沉浸式状态栏
     */

        /**
         * 是否打开沉浸式状态栏
         * @return
         */
    public boolean isOpenStatus(){
        return true;
    }
}





