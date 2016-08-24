package com.andy.mengzhu.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.andy.greendao.Person;
import com.andy.mengzhu.R;
import com.andy.mengzhu.presenter.PersonPresenter;

import java.util.List;

/**
 * Created by Administrator on 2016/8/23 0023.
 */
public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.ViewHolder> implements View.OnClickListener {
    /**
     * Person 类数据
     */
    private List<Person> persons;

    /**
     * Presenter层，用来处理数据
     */
    private PersonPresenter mPersonPresenter = null;

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

    public PersonAdapter(List<Person> persons, PersonPresenter mPersonPresenter) {
        this.persons = persons;
        this.mPersonPresenter = mPersonPresenter;
    }

    @Override
    public PersonAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_person, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        view.setOnClickListener(this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PersonAdapter.ViewHolder holder, int position) {
        holder.name.setText(persons.get(position).getPerson_name());
        holder.num.setText(persons.get(position).getNum().toString());
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return persons.isEmpty() ? 0 : persons.size();
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
        mPersonPresenter.deletePerson(persons.get(position));
        persons.remove(position);
        notifyItemRemoved(position);
    }
}
