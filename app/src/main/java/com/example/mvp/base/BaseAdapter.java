package com.example.mvp.base;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseAdapter<T> extends RecyclerView.Adapter {
    protected Context context;
    List<T> list = new ArrayList<>();

    public BaseAdapter(Context context) {
        this.context = context;
    }
    public void resetList(List<T> dataList){
        this.list.clear();
        this.list.addAll(dataList);
        notifyDataSetChanged();
    }
    public void addList(List<T> dataList){
        this.list.addAll(dataList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(getLayoutId(), parent, false);
        return new BaseViewHolder(inflate);
    }

    protected abstract int getLayoutId();

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        BaseViewHolder viewHolder = (BaseViewHolder) holder;
        T t = list.get(position);
        bindData(viewHolder,t);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    protected abstract void bindData(BaseViewHolder viewHolder, T t);

   public class BaseViewHolder extends RecyclerView.ViewHolder {
        SparseArray<View> viewList;
        public BaseViewHolder(View itemView) {
            super(itemView);
            if (viewList==null){
                viewList = new SparseArray<>();
            }
        }
        public View getViewById(int id){
            View view = viewList.get(id);
            if (view==null){
                view = itemView.findViewById(id);
                viewList.append(id,view);
            }
            return view;
        }
    }
}
