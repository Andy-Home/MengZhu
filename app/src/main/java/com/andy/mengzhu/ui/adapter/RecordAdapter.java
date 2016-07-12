package com.andy.mengzhu.ui.adapter;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.andy.greendao.Record;
import com.andy.mengzhu.R;
import com.andy.mengzhu.presenter.RecordPresenter;
import com.andy.mengzhu.presenter.impl.RecordPresenterImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/7/7 0007.
 */
public class RecordAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
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

    /**
     * Presenter层
     */
    private RecordPresenter mRecordPresenter = null;

    private OnItemClickListener itemClickListener;

    public interface OnItemClickListener {
        void OnItemClick(Record record);
    }

    public RecordAdapter(List<Record> recordList, RecordPresenter mRecordPresenter) {
        this.recordList = initData(recordList);
        this.mRecordPresenter = mRecordPresenter;
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == RECORD_TYPE) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_record_type1, parent, false);
            view.setOnClickListener(this);
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
            ((RecordViewHolder) holder).funds.setText(recordList.get(position).getFunds_name());
            ((RecordViewHolder) holder).num.setText(recordList.get(position).getNum() + "");
            //账务类型 0为支出、1为收入、2为收借款、3为转账
            int recordType = recordList.get(position).getType();

            if (recordType == 0) {
                ((RecordViewHolder) holder).into_or_out.setText("<——");
            } else if (recordType == 1) {
                ((RecordViewHolder) holder).into_or_out.setText("——>");
            }
            holder.itemView.setTag(recordList.get(position));
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

    @Override
    public void onClick(View view) {
        itemClickListener.OnItemClick((Record) view.getTag());
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

    public static class DateViewHolder extends RecyclerView.ViewHolder {
        public TextView date;

        public DateViewHolder(View itemView) {
            super(itemView);
            date = (TextView) itemView.findViewById(R.id.date);
        }
    }

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    //根据传入的Record集合, 将数据转化为方便显示的数据.
    //如果某一日期是第一次出现，那么就在Record类型中保存相应的日期，并将金额项变为-1.0，方便后面的判断
    //统计每一个日期下的记录数，并保存在Record类型中的ID项中当该记录数为0时，记录删除
    private List<Record> initData(List<Record> data) {
        List<Record> ans = new ArrayList<>();

        String dstr = "1995-4-15";
        Date date = null;
        try {
            date = sdf.parse(dstr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        int datePosition = 0;
        for (Record record : data) {
            if (!date.equals(record.getDate())) {
                date = record.getDate();
                Record record1 = new Record();
                record1.setDate(date);
                record1.setNum(-1.0);
                record1.setId(Long.valueOf(0));
                ans.add(record1);
                datePosition = ans.size() - 1;
            }
            Long recordNum = ans.get(datePosition).getId();
            ans.get(datePosition).setId(recordNum + 1);
            record.setDatePosition(datePosition);
            ans.add(record);
        }
        return ans;
    }

    public void removeRecord(int position) {
        int datePosition = recordList.get(position).getDatePosition();

        Long recordNum = recordList.get(datePosition).getId();
        recordList.get(datePosition).setId(recordNum - 1);
        //删除数据库中的数据
        mRecordPresenter.deleteRecord(recordList.get(position));

        recordList.remove(position);
        notifyItemRemoved(position);
        if (recordList.get(datePosition).getId().equals(Long.valueOf(0))) {
            recordList.remove(datePosition);
            notifyItemRemoved(datePosition);
        }
    }
}
