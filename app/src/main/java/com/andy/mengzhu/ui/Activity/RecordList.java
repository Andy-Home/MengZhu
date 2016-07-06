package com.andy.mengzhu.ui.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.andy.mengzhu.R;

/**
 * 账务记录显示列表页
 * <p/>
 * Created by Administrator on 2016/7/6 0006.
 */
public class RecordList extends Fragment {
    private RecyclerView record_list;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_record_list, container, false);
        findView(view);
        return view;
    }

    private void findView(View view) {
        record_list = (RecyclerView) view.findViewById(R.id.record_list);
    }
}
