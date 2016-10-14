package com.qf.recyclerv;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yanzhenjie.recyclerview.swipe.SwipeMenuAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2016/10/13.
 */
public class ReAdapter extends SwipeMenuAdapter<ReAdapter.MyViewHolder> {

    public Context context;
    public List<String> list;

    public ReAdapter(Context context) {
        this.context = context;
        this.list = new ArrayList<>();
    }

    public void setDatas(List<String> list){
        this.list = list;
        this.notifyDataSetChanged();
    }

    //移动item的方法
    public void moveItem(int fromPosition,int toPosition){
        String data = this.list.remove(fromPosition);
        this.list.add(toPosition,data);
        this.notifyItemMoved(fromPosition,toPosition);
    }

    //移除item的方法
    public void remove(int position){
        this.list.remove(position);
        this.notifyItemRemoved(position);
    }

//    @Override
//    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View inflate = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false);
//        return new MyViewHolder(inflate);
//    }

    //SwipeMenuAdapter需要重写的2个方法
    @Override
    public View onCreateContentView(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false);
        return inflate;
    }

    @Override
    public MyViewHolder onCompatCreateViewHolder(View realContentView, int viewType) {
        return new MyViewHolder(realContentView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tv.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tv;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.tv = (TextView) itemView.findViewById(R.id.tv);
        }
    }
}
