package com.andy.mengzhu.ui.view;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.andy.mengzhu.ui.adapter.FundsAdapter;
import com.andy.mengzhu.ui.adapter.CategoryAdapter;
import com.andy.mengzhu.ui.adapter.PersonAdapter;
import com.andy.mengzhu.ui.adapter.RecordAdapter;

/**
 * Created by Administrator on 2016/7/11 0011.
 */
public class ItemTouchCallback extends ItemTouchHelper.Callback {
    private RecyclerView.Adapter adapter;

    public ItemTouchCallback(RecyclerView.Adapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        //侧滑方向
        int swipFlag;

        //拖拽方向
        int dragFlag;

        // 当该项是账务记录列表中的日期内容时，不可以移动
        if (viewHolder instanceof RecordAdapter.DateViewHolder) {
            swipFlag = 0;
            dragFlag = 0;
        } else {
            swipFlag = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
            dragFlag = 0;
        }

        return makeMovementFlags(dragFlag, swipFlag);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        if (adapter instanceof RecordAdapter) {
            ((RecordAdapter) adapter).removeRecord(viewHolder.getAdapterPosition());
        } else if (adapter instanceof CategoryAdapter) {
            ((CategoryAdapter) adapter).removeData(viewHolder.getAdapterPosition());
        } else if (adapter instanceof PersonAdapter) {
            ((PersonAdapter) adapter).removeData(viewHolder.getAdapterPosition());
        } else if (adapter instanceof FundsAdapter) {
            ((FundsAdapter) adapter).removeData(viewHolder.getAdapterPosition());
        }
    }
}
