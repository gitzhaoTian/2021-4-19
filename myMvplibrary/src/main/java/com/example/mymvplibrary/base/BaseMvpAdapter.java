package com.example.mymvplibrary.base;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseMvpAdapter<T,G> extends RecyclerView.Adapter {
    protected Context context;
    List<T> list = new ArrayList<>();
    List<G> ban = new ArrayList<>();

    public BaseMvpAdapter(Context context) {
        this.context = context;
    }
    public void resetList(List<T> dataList,List<G> ban){
        this.ban.clear();
        this.ban.addAll(ban);
        this.list.clear();
        this.list.addAll(dataList);
        notifyDataSetChanged();
    }
    public void addList(List<T> dataList,List<G> ban){
        if (this.ban!=null){
            this.ban.clear();
        }
        this.ban.addAll(ban);
        this.list.addAll(dataList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType==0){
            View view = LayoutInflater.from(context).inflate(getLayoutBan(), parent, false);
            return new BaseBanViewHolder(view);
        }else {
            View inflate = LayoutInflater.from(context).inflate(getLayoutId(), parent, false);
            return new BaseViewHolder(inflate);
        }
    }

    protected abstract int getLayoutBan();

    protected abstract int getLayoutId();

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int itemViewType = getItemViewType(position);
        if (itemViewType==0){
            BaseBanViewHolder baseBanViewHolder = (BaseBanViewHolder) holder;
            bindBanner(baseBanViewHolder,ban);
        }
        if (itemViewType==1){
            BaseViewHolder viewHolder = (BaseViewHolder) holder;
            int pos = position;
            if (ban.size()>0){
                pos = position-1;
            }
            T t = list.get(pos);
            bindData(viewHolder,t);
        }
    }

    protected abstract void bindBanner(BaseBanViewHolder baseBanViewHolder, List<G> ban);


    @Override
    public int getItemCount() {
        if (ban.size()>0){
            return list.size()+1;
        }else {
            return list.size();
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (ban.size()>0&&position==0){
            return 0;
        }else {
            return 1;
        }
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

    public class BaseBanViewHolder extends RecyclerView.ViewHolder {
       SparseArray<View> viewList;
        public BaseBanViewHolder(View itemView) {
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
