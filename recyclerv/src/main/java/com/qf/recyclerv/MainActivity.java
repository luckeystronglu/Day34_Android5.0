package com.qf.recyclerv;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.ViewGroup;

import com.yanzhenjie.recyclerview.swipe.Closeable;
import com.yanzhenjie.recyclerview.swipe.OnSwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
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
//        rv.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)); //横向布局,第三个参数false从左往向，true从右往左
//        rv.setLayoutManager(new GridLayoutManager(this,2));
        adapter = new ReAdapter(this);
        rv.setAdapter(adapter);
        rv.setLongPressDragEnabled(true);//开启长按拖拽
        rv.setItemViewSwipeEnabled(false);//开启滑动删除 ,与swipeMenuCreator只能用一种，否则会冲突
        rv.setOnItemMoveListener(this);

        rv.setSwipeMenuCreator(swipeMenuCreator);
        rv.setSwipeMenuItemClickListener(new OnSwipeMenuItemClickListener() {
            @Override
            public void onItemClick(Closeable closeable, int adapterPosition, int menuPosition, int direction) {
                closeable.smoothCloseMenu();//关闭被点击菜单
                if (direction == SwipeMenuRecyclerView.RIGHT_DIRECTION){
                    //右滑
                    if (menuPosition == 0){
//                        Log.d("print", "onItemClick点击了右边第: "+menuPosition +"个菜单");
                        adapter.moveItem(adapterPosition,0);
                        rv.scrollToPosition(0);
                    }else if (menuPosition == 1){
//                        Log.d("print", "onItemClick点击了左边第: "+menuPosition +"个菜单");
                        adapter.remove(adapterPosition);
                    }

                }
//                else if(direction == SwipeMenuRecyclerView.LEFT_DIRECTION){
//                    Log.d("print", "onItemClick点击了左边第: "+menuPosition +"个菜单");
//                    adapter.remove(adapterPosition);
//                }

            }
        });

        datas = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            datas.add("深圳"+i);
        }
        adapter.setDatas(datas);



    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        adapter.moveItem(fromPosition,toPosition);
        return true;
    }

    @Override
    public void onItemDismiss(int position) {
        adapter.remove(position);

    }

    private SwipeMenuCreator swipeMenuCreator = new SwipeMenuCreator() {
        @Override
        public void onCreateMenu(SwipeMenu swipeLeftMenu, SwipeMenu swipeRightMenu, int viewType) {

//            SwipeMenuItem addItem = new SwipeMenuItem(MainActivity.this)
////                    .setBackgroundDrawable(R.mipmap.ic_launcher)// 点击的背景。
//                    .setBackgroundDrawable(R.color.colorPrimary)// 点击的背景。
//                    .setImage(android.R.drawable.ic_delete) // 图标。
//                    .setWidth(200) // 宽度。
//                    .setHeight(ViewGroup.LayoutParams.MATCH_PARENT); // 高度。
//            swipeLeftMenu.addMenuItem(addItem); // 添加一个按钮到左侧菜单。

            SwipeMenuItem deleteItem = new SwipeMenuItem(MainActivity.this)
//                    .setBackgroundDrawable(new ColorDrawable(Color.YELLOW))
                    .setImage(android.R.drawable.ic_dialog_map) // 图标。
                    .setText("置顶") // 文字。
                    .setTextColor(Color.RED) // 文字颜色。
                    .setTextSize(12) // 文字大小。
                    .setWidth(100)
                    .setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
            swipeRightMenu.addMenuItem(deleteItem);// 添加一个按钮到右侧侧菜单。

            SwipeMenuItem addItem2 = new SwipeMenuItem(MainActivity.this)
//                    .setBackgroundDrawable(R.mipmap.ic_launcher)// 点击的背景。
//                    .setBackgroundDrawable(R.color.colorPrimary)// 点击的背景。
                    .setImage(android.R.drawable.ic_dialog_info) // 图标。
                    .setText("删除") // 文字。
                    .setTextColor(Color.BLUE) // 文字颜色。
                    .setTextSize(16) // 文字大小。
                    .setWidth(200) // 宽度。
                    .setHeight(ViewGroup.LayoutParams.MATCH_PARENT); // 高度。
            swipeRightMenu.addMenuItem(addItem2); // 添加一个按钮到左侧菜单。


            // 上面的菜单哪边不要菜单就不要添加。
        }
    };

}
