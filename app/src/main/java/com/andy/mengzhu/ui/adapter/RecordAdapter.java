package com.andy.mengzhu.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.andy.greendao.Record;
import com.andy.mengzhu.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/7/7 0007.
 */
public class RecordAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    /**
     * 需要显示的数据
     */
    private List<Record> recordList = null;

    /**
     * 日期类型
     */
    private static final int DATE_TYPE = 1;

    /**
     * 完整的账务记录类型
     */
    private static final int RECORD_TYPE = 2;

    /**
     * Date 数据的格式化
     */
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");


    public RecordAdapter(List<Record> recordList) {
        this.recordList = initData(recordList);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == RECORD_TYPE) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_record_type1, parent, false);
            return new RecordViewHolder(view);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_record_type2, parent, false);
            return new DateViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof RecordViewHolder) {
            ((RecordViewHolder) holder).desc.setText(recordList.get(position).getDesc());
            //((RecordViewHolder) holder).funds.setText(recordList.get(position).getFunds());
            ((RecordViewHolder) holder).num.setText(recordList.get(position).getNum() + "");
        } else if (holder instanceof DateViewHolder) {
            ((DateViewHolder) holder).date.setText(sdf.format(recordList.get(position).getDate()));
        }
    }


    @Override
    public int getItemViewType(int position) {
        if (recordList.get(position).getNum() == -1.0) {
            return DATE_TYPE;
        } else {
            return RECORD_TYPE;
        }
    }

    @Override
    public int getItemCount() {
        return recordList.isEmpty() ? 0 : recordList.size();
    }

    protected static class RecordViewHolder extends RecyclerView.ViewHolder {
        //账务记录中的金额
        private TextView num;
        //账务记录中的描述详情
        private TextView desc;
        //该账务是支出还是收入
        private TextView into_or_out;
        //账务记录的资金项
        private TextView funds;

        public RecordViewHolder(View itemView) {
            super(itemView);
            num = (TextView) itemView.findViewById(R.id.num);
            desc = (TextView) itemView.findViewById(R.id.desc);
            into_or_out = (TextView) itemView.findViewById(R.id.into_or_out);
            funds = (TextView) itemView.findViewById(R.id.funds);
        }
    }

    protected static class DateViewHolder extends RecyclerView.ViewHolder {
        public TextView date;

        public DateViewHolder(View itemView) {
            super(itemView);
            date = (TextView) itemView.findViewById(R.id.date);
        }
    }

    //根据传入的Record集合, 将数据转化为方便显示的数据.
    //如果某一日期是第一次出现，那么就在Record类型中保存相应的日期，并将金额变为-1.0，方便后面的判断
    private List<Record> initData(List<Record> data) {
        List<Record> ans = new ArrayList<>();

        String dstr = "1995-4-15";
        Date date = null;
        try {
            date = sdf.parse(dstr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        for (Record record : data) {
            if (!date.equals(record.getDate())) {
                date = record.getDate();
                Record record1 = new Record();
                record1.setDate(date);
                record1.setNum(-1.0);
                ans.add(record1);
            }
            ans.add(record);
        }
        return ans;
    }
}
