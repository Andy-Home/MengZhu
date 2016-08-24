package com.andy.mengzhu.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.andy.greendao.Funds;
import com.andy.mengzhu.R;
import com.andy.mengzhu.presenter.FundsPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * 资金列表的适配器
 * <p/>
 * Created by Administrator on 2016/8/24 0024.
 */
public class FundsAdapter extends RecyclerView.Adapter<FundsAdapter.ViewHolder> implements View.OnClickListener {
    /**
     * 保存资金项的数据
     */
    private List<Funds> funds = new ArrayList<>();

    /**
     * Presenter 层
     */
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


    public FundsAdapter(List<Funds> funds, FundsPresenter mFundsPresenter) {
        this.funds = funds;
        this.mFundsPresenter = mFundsPresenter;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_funds, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        view.setOnClickListener(this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.name.setText(funds.get(position).getFunds_name());
        holder.num.setText(funds.get(position).getNum().toString());
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return funds.isEmpty() ? 0 : funds.size();
    }

    @Override
    public void onClick(View view) {
        if (onRecyclerViewItemClickListener != null) {
            onRecyclerViewItemClickListener.onItemClick(view, (int) view.getTag());
        }
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView num;

        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            num = (TextView) itemView.findViewById(R.id.num);
        }
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.onRecyclerViewItemClickListener = listener;
    }

    public void removeData(int position) {
        mFundsPresenter.deleteFunds(funds.get(position));
        funds.remove(position);
        notifyItemRemoved(position);
    }
}
