package com.andy.mengzhu.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.andy.mengzhu.R;
import com.andy.mengzhu.model.entity.Category;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/29 0029.
 */
public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    /**
     * Category 类型的数据，用来在 ListView 中显示
     */
    private List<Category> datas = new ArrayList<>();

    public CategoryAdapter(List<Category> datas) {
        this.datas = datas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_category, parent, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.categoryName.setText(datas.get(position).getCategoryName());
        holder.categoryPay.setText("" + datas.get(position).getCategoryNum());
    }

    @Override
    public int getItemCount() {
        return datas.isEmpty() ? 0 : datas.size();
    }


    protected static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView categoryName;
        public TextView categoryPay;

        public ViewHolder(View itemView) {
            super(itemView);
            categoryName = (TextView) itemView.findViewById(R.id.category_name);
            categoryPay = (TextView) itemView.findViewById(R.id.category_pay);
        }
    }
}
