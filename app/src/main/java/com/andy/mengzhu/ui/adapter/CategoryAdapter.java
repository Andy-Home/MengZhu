package com.andy.mengzhu.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.andy.greendao.Category;
import com.andy.mengzhu.R;
import com.andy.mengzhu.presenter.CategoryPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * 类目列表的适配器
 * <p/>
 * Created by Administrator on 2016/7/6 0006.
 */
public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> implements View.OnClickListener {
    /**
     * 保存类别项的数据
     */
    private List<Category> categories = new ArrayList<>();

    /**
     * Presenter 层
     */
    private CategoryPresenter mCategoryPresenter = null;

    /**
     * 使用
     */
    private OnRecyclerViewItemClickListener onRecyclerViewItemClickListener;

    /**
     * 点击事件监听器
     */
    public interface OnRecyclerViewItemClickListener {
        /**
         * 点击事件的回调接口
         *
         * @param view
         * @param position
         */
        void onItemClick(View view, int position);
    }


    public CategoryAdapter(List<Category> categories, CategoryPresenter mCategoryPresenter) {
        this.categories = categories;
        this.mCategoryPresenter = mCategoryPresenter;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        view.setOnClickListener(this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.name.setText(categories.get(position).getCategory_name());
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return categories.isEmpty() ? 0 : categories.size();
    }

    @Override
    public void onClick(View view) {
        if (onRecyclerViewItemClickListener != null) {
            onRecyclerViewItemClickListener.onItemClick(view, (int) view.getTag());
        }
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name;

        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
        }
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.onRecyclerViewItemClickListener = listener;
    }

    public void removeData(int position) {
        mCategoryPresenter.deleteCategory(categories.get(position));
        categories.remove(position);
        notifyItemRemoved(position);
    }
}
