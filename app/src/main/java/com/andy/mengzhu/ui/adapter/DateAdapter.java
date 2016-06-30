package com.andy.mengzhu.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.andy.mengzhu.R;
import com.andy.mengzhu.app.util.DateUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by andy on 16-6-30.
 */
public class DateAdapter extends RecyclerView.Adapter<DateAdapter.ViewHolder> {

    /**
     * 保存要显示的数据
     */
    private List<Date> datas = new ArrayList<>();

    /**
     * 现在的日期
     */
    private Date currentDate;

    /**
     * 需要首先显示的位置，即现在的日期
     */
    private int position;

    public DateAdapter() {
        this.datas = DateUtil.findDate();
        currentDate = Calendar.getInstance().getTime();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_record_dateselect, parent, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.date.setText(DateUtil.getDay(datas.get(position)));
        holder.month.setText(DateUtil.getMonth(datas.get(position)));
        holder.week.setText(DateUtil.getWeek(datas.get(position)));
    }

    @Override
    public int getItemCount() {
        return datas.isEmpty() ? 0 : datas.size();
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView date;
        public TextView week;
        public TextView month;

        public ViewHolder(View itemView) {
            super(itemView);
            date = (TextView) itemView.findViewById(R.id.date);
            week = (TextView) itemView.findViewById(R.id.week);
            month = (TextView) itemView.findViewById(R.id.month);
        }
    }
}
