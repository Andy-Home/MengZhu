package com.andy.mengzhu.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.andy.greendao.Category;
import com.andy.greendao.Funds;
import com.andy.mengzhu.R;
import com.andy.mengzhu.presenter.CategoryPresenter;
import com.andy.mengzhu.presenter.FundsPresenter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * FundsList 与 CategoryList 中的 RecycleView 的适配器
 * <p/>
 * Created by Administrator on 2016/7/6 0006.
 */
public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> implements View.OnClickListener {
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
     * Presenter 层
     */
    private CategoryPresenter mCategoryPresenter = null;
    private FundsPresenter mFundsPresenter = null;
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


    public ListAdapter(List<Category> categories, CategoryPresenter mCategoryPresenter) {
        this.categories = categories;
        isFunds = false;
        this.mCategoryPresenter = mCategoryPresenter;
    }

    public ListAdapter(List<Funds> funds, FundsPresenter mFundsPresenter) {
        this.funds = funds;
        isFunds = true;
        this.mFundsPresenter = mFundsPresenter;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        view.setOnClickListener(this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (isFunds) {
            holder.name.setText(funds.get(position).getFunds_name());
        } else {
            holder.name.setText(categories.get(position).getCategory_name());
        }
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        if (isFunds) {
            return funds.isEmpty() ? 0 : funds.size();
        } else {
            return categories.isEmpty() ? 0 : categories.size();
        }
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
        if (isFunds) {
            mFundsPresenter.deleteFunds(funds.get(position));
            funds.remove(position);
        } else {
            mCategoryPresenter.deleteCategory(categories.get(position));
            categories.remove(position);
        }
        notifyItemRemoved(position);
    }
}
