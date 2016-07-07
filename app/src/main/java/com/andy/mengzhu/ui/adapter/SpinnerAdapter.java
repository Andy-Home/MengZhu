package com.andy.mengzhu.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.andy.greendao.Category;
import com.andy.greendao.CategoryDao;
import com.andy.greendao.Funds;
import com.andy.mengzhu.R;

import java.util.List;

/**
 * Created by Administrator on 2016/7/7 0007.
 */
public class SpinnerAdapter extends BaseAdapter {
    /**
     * 数据是否是Funds类型
     */
    private boolean isFunds = true;

    /**
     * 需要显示的 Category 类型数据
     */
    private List<Category> categoryList = null;

    /**
     * 需要显示的 Funds 类型数据
     */
    private List<Funds> fundsList = null;

    private Context context;

    /**
     * 构造函数
     *
     * @param context
     * @param categoryList
     * @param isFunds      没有作用，可以为null
     */
    public SpinnerAdapter(Context context, List<Category> categoryList, boolean isFunds) {
        this.context = context;
        this.categoryList = categoryList;
        this.isFunds = false;
    }

    public SpinnerAdapter(Context context, List<Funds> fundsList) {
        this.context = context;
        this.fundsList = fundsList;
        this.isFunds = true;
    }

    @Override
    public int getCount() {
        if (isFunds) {
            return fundsList.isEmpty() ? 0 : fundsList.size();
        } else {
            return categoryList.isEmpty() ? 0 : categoryList.size();
        }
    }

    @Override
    public Object getItem(int i) {
        if (isFunds) {
            return fundsList.get(i);
        } else {
            return categoryList.get(i);
        }
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            viewHolder = new ViewHolder();
            view = LayoutInflater.from(context).inflate(R.layout.item_spinner, viewGroup, false);
            viewHolder.name = (TextView) view.findViewById(R.id.name);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        if (isFunds) {
            viewHolder.name.setText(fundsList.get(i).getFunds_name());
        } else {
            viewHolder.name.setText(categoryList.get(i).getCategory_name());
        }
        return view;
    }

    protected static class ViewHolder {
        public TextView name;
    }
}
