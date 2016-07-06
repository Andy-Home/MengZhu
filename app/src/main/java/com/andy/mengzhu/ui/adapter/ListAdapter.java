package com.andy.mengzhu.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.andy.greendao.Category;
import com.andy.greendao.Funds;
import com.andy.mengzhu.R;

import java.util.ArrayList;
import java.util.List;

/**
 * FundsList 与 CategoryList 中的 RecycleView 的适配器
 * <p/>
 * Created by Administrator on 2016/7/6 0006.
 */
public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    /**
     * 保存资金项的数据
     */
    private List<Funds> funds = new ArrayList<>();

    /**
     * 保存类别项的数据
     */
    private List<Category> categories = new ArrayList<>();

    /**
     * 区分是资金项还是类别项，如果是资金项则为true，否为为false
     */
    private boolean isFunds = true;

    /**
     * ListAdapter 构造函数
     *
     * @param data
     * @param isFunds isFunds为true时，则将传入的数据作为 List<Funds> 格式的数据，
     *                否则为 List<Category>
     */
    public ListAdapter(List data, boolean isFunds) {
        this.isFunds = isFunds;
        if (isFunds) {
            this.funds = (List<Funds>) data;
        } else {
            this.categories = (List<Category>) data;
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (isFunds) {
            holder.name.setText(funds.get(position).getFunds_name());
        } else {
            holder.name.setText(categories.get(position).getCategory_name());
        }
    }

    @Override
    public int getItemCount() {
        if (isFunds) {
            return funds.isEmpty() ? 0 : funds.size();
        } else {
            return categories.isEmpty() ? 0 : categories.size();
        }
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name;

        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
        }
    }
}
