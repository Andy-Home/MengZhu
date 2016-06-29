package com.andy.mengzhu.presenter;

/**
 * 在Presenter层实现，给Model层回调，更改View层的状态，确保Model层不直接操作View层
 *
 * Created by Administrator on 2016/6/29 0029.
 */
public interface OnDataRequestListener {

    /**
     * 成功时回调
     *
     * @param object 成功时存入的值
     * @param requestCode
     */
    void onSuccess(Object object, int requestCode);

    /**
     * 失败时的回调
     *
     * @param requestCode
     */
    void onError(int requestCode);
}
