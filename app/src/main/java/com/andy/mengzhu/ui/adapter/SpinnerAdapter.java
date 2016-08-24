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
import com.andy.greendao.Person;
import com.andy.mengzhu.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/7 0007.
 */
public class SpinnerAdapter extends BaseAdapter {
    /**
     * 保存需要显示的数据
     */
    private List<Object> objectList = null;

    private Context context;

    /**
     * 构造函数
     *
     * @param context
     * @param object
     */
    public SpinnerAdapter(Context context, Object object) {
        this.context = context;
        objectList = (List<Object>) object;
    }


    @Override
    public int getCount() {
        return objectList.isEmpty() ? 0 : objectList.size();
    }

    @Override
    public Object getItem(int i) {
        return objectList.get(i);
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
        if (objectList.get(0) instanceof Category) {
            viewHolder.name.setText(((Category) objectList.get(i)).getCategory_name());
        } else if (objectList.get(0) instanceof Funds) {
            viewHolder.name.setText(((Funds) objectList.get(i)).getFunds_name());
        } else if (objectList.get(0) instanceof Person) {
            viewHolder.name.setText(((Person) objectList.get(i)).getPerson_name());
        }
        return view;
    }

    protected static class ViewHolder {
        public TextView name;
    }
}
