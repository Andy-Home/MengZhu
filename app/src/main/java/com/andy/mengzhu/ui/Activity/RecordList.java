package com.andy.mengzhu.ui.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.andy.greendao.Record;
import com.andy.mengzhu.R;
import com.andy.mengzhu.presenter.OnDataRequestListener;
import com.andy.mengzhu.presenter.RecordPresenter;
import com.andy.mengzhu.presenter.impl.RecordPresenterImpl;
import com.andy.mengzhu.ui.adapter.ListAdapter;
import com.andy.mengzhu.ui.adapter.RecordAdapter;
import com.andy.mengzhu.ui.view.DataRequestView;
import com.andy.mengzhu.ui.view.DividerItemDecoration;

import java.util.List;

/**
 * 账务记录显示列表页
 * <p/>
 * Created by Administrator on 2016/7/6 0006.
 */
public class RecordList extends Fragment implements DataRequestView {
    private RecyclerView record_list;
    private RecordAdapter mRecordAdapter = null;

    private RecordPresenter mRecordPresenter = null;
    private List<Record> recordList = null;

    private static final int GET_RECORD = 1;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_record_list, container, false);
        findView(view);
        initData();
        initializeView();
        return view;
    }

    private void findView(View view) {
        record_list = (RecyclerView) view.findViewById(R.id.record_list);
    }

    private void initData() {
        mRecordPresenter = new RecordPresenterImpl(getActivity(), this);

        mRecordPresenter.getRecord(GET_RECORD);
    }

    private void initializeView() {
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        record_list.setLayoutManager(mLayoutManager);
        record_list.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
        record_list.setHasFixedSize(true);
        mRecordAdapter = new RecordAdapter(recordList);
        record_list.setAdapter(mRecordAdapter);
    }

    @Override
    public void setView(Object object, int requestCode) {
        switch (requestCode) {
            case GET_RECORD:
                recordList = (List<Record>) object;
                break;
        }
    }

    @Override
    public void showError(int requestCode) {

    }
}
