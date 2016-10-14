package com.qf.recyclerv;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;
import com.yanzhenjie.recyclerview.swipe.touch.OnItemMoveListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnItemMoveListener {
    private SwipeMenuRecyclerView rv;
    private ReAdapter adapter;
    private List<String> datas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv = (SwipeMenuRecyclerView) findViewById(R.id.recyclerv);
        rv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ReAdapter(this);
        rv.setAdapter(adapter);
        rv.setLongPressDragEnabled(true);
        rv.setItemViewSwipeEnabled(true);
        rv.setOnItemMoveListener(this);

        datas = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            datas.add("深圳"+i);
        }
        adapter.setDatas(datas);



    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        return false;
    }

    @Override
    public void onItemDismiss(int position) {

    }

//    private SwipeMenuCreator swipeMenuCreator = new SwipeMenuCreator() {
//        @Override
//        public void onCreateMenu(SwipeMenu swipeLeftMenu, SwipeMenu swipeRightMenu, int viewType) {
//
//            SwipeMenuItem addItem = new SwipeMenuItem(MainActivity.this)
//                    .setBackgroundDrawable(R.drawable.selector_green)// 点击的背景。
//                    .setImage(R.mipmap.ic_action_add) // 图标。
//                    .setWidth(size) // 宽度。
//                    .setHeight(size); // 高度。
//            swipeLeftMenu.addMenuItem(addItem); // 添加一个按钮到左侧菜单。
//
//            SwipeMenuItem deleteItem = new SwipeMenuItem(mContext)
//                    .setBackgroundDrawable(R.drawable.selector_red)
//                    .setImage(R.mipmap.ic_action_delete) // 图标。
//                    .setText("删除") // 文字。
//                    .setTextColor(Color.WHITE) // 文字颜色。
//                    .setTextSize(16) // 文字大小。
//                    .setWidth(size)
//                    .setHeight(size);
//            swipeRightMenu.addMenuItem(deleteItem);// 添加一个按钮到右侧侧菜单。.
//
//            // 上面的菜单哪边不要菜单就不要添加。
//        }
//    };
}
