package com.andy.mengzhu.ui.view;

/**
 * Created by Administrator on 2016/6/29 0029.
 */
public interface InitializeView {

    /**
     * 当请求数据错误时调用
     * @param requestCode  数据请求的标志，用于后面区分多个请求
     */
    void showError(int requestCode);

    /**
     * 当请求数据成功后设置 View
     *
     * @param object 反馈的数据
     * @param requestCode 数据请求的标志，用于后面区分多个请求
     */
    void setView(Object object, int requestCode);
}
