package com.andy.mengzhu.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.andy.greendao.Funds;
import com.andy.greendao.Person;
import com.andy.mengzhu.R;
import com.andy.mengzhu.presenter.PersonPresenter;
import com.andy.mengzhu.presenter.impl.PersonPresenterImpl;
import com.andy.mengzhu.ui.adapter.PersonAdapter;
import com.andy.mengzhu.ui.view.DataRequestView;
import com.andy.mengzhu.ui.view.DividerItemDecoration;
import com.andy.mengzhu.ui.view.ItemTouchCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/23 0023.
 */
public class PersonList extends AppCompatActivity implements DataRequestView, Toolbar.OnMenuItemClickListener, View.OnClickListener {
    /**
     * 导航栏
     */
    private Toolbar mToolbar;

    /**
     * 点击添加之后弹出该对话框，供用户填写新的资金项
     */
    private AlertDialog mAlertDialog;

    /**
     * 新建的资金项的名字
     */
    private EditText personName;

    /**
     * 对话框中的取消按钮
     */
    private Button cancel;

    /**
     * 对话框中的确定按钮
     */
    private Button determine;

    /**
     * 收/还款人列表
     */
    private RecyclerView person_list;

    /**
     * person_list 的适配器
     */
    private PersonAdapter mListAdapter = null;

    /**
     * 需要显示的 Person 类型的数据
     */
    private List<Person> person = new ArrayList<>();

    /**
     * Presenter 层
     */
    private PersonPresenter mPersonPresenter = null;

    /**
     * 判断是否是添加模式
     */
    private boolean isAdd = false;

    /**
     * 用户在列表中的点击位置
     */
    private int position = 0;

    /**
     * 获取Person类型数据请求的标志
     */
    private static final int GET_PERSON = 1;
    /**
     * 保存Person类型数据请求的标志
     */
    private static final int SAVE_PERSON = 2;

    /**
     * 更新Person类型数据请求的标志
     */
    private static final int UPDATE_PERSON = 3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        findView();
        setToolBar();
        initData();
        setListener();
    }

    private void setListener() {
        mToolbar.setOnMenuItemClickListener(this);
        mListAdapter.setOnItemClickListener(new PersonAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                modifyPersonDialog(position);
            }
        });
    }

    private void findView() {
        person_list = (RecyclerView) findViewById(R.id.list);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
    }

    private void setToolBar() {
        mToolbar.setTitle(R.string.menu_person);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initData() {
        mPersonPresenter = new PersonPresenterImpl(this, this);
        mPersonPresenter.getPerson(GET_PERSON);
    }

    private void initializeView() {
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        person_list.setLayoutManager(mLayoutManager);
        person_list.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        person_list.setHasFixedSize(true);
        mListAdapter = new PersonAdapter(person, mPersonPresenter);
        person_list.setAdapter(mListAdapter);

        ItemTouchHelper.Callback callback = new ItemTouchCallback(mListAdapter);
        ItemTouchHelper mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(person_list);
    }

    @Override
    public void showError(int requestCode) {

    }

    @Override
    public void setView(Object object, int requestCode) {
        switch (requestCode) {
            case GET_PERSON:
                person.addAll((List<Person>) object);
                initializeView();
                break;

            case SAVE_PERSON:
                person.clear();
                person.addAll((List<Person>) object);
                mListAdapter.notifyDataSetChanged();
                break;

            case UPDATE_PERSON:
                person.clear();
                person.addAll((List<Person>) object);
                mListAdapter.notifyDataSetChanged();
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_person, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_person:
                setPersonDialog();
                break;
        }
        return true;
    }

    /**
     * 用户修改类别项
     */
    private void modifyPersonDialog(int position) {
        isAdd = false;
        this.position = position;
        mAlertDialog = new AlertDialog.Builder(this).create();
        mAlertDialog.show();
        Window window = mAlertDialog.getWindow();
        window.setContentView(R.layout.dialog_add);
        window.setGravity(Gravity.CENTER);
        window.clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        TextView tv_title = (TextView) window.findViewById(R.id.title);
        tv_title.setText(R.string.modify_person_title);
        personName = (EditText) window.findViewById(R.id.name);
        cancel = (Button) window.findViewById(R.id.cancel);
        determine = (Button) window.findViewById(R.id.determine);
        cancel.setOnClickListener(this);
        determine.setOnClickListener(this);
    }

    /**
     * 自定对话框. 用户添加新的资金项，显示自定义的对话框
     */
    private void setPersonDialog() {
        isAdd = true;
        mAlertDialog = new AlertDialog.Builder(this).create();
        mAlertDialog.show();
        Window window = mAlertDialog.getWindow();
        window.setContentView(R.layout.dialog_add);
        window.setGravity(Gravity.CENTER);
        window.clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        TextView tv_title = (TextView) window.findViewById(R.id.title);
        tv_title.setText(R.string.add_person_title);
        personName = (EditText) window.findViewById(R.id.name);
        cancel = (Button) window.findViewById(R.id.cancel);
        determine = (Button) window.findViewById(R.id.determine);
        cancel.setOnClickListener(this);
        determine.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cancel:
                mAlertDialog.cancel();
                break;

            case R.id.determine:
                Person mPerson = new Person();
                if (isAdd) {
                    mPerson.setPerson_name(personName.getText().toString());
                    mPerson.setNum(0.0);
                    mPersonPresenter.savaPerson(mPerson, SAVE_PERSON);
                } else {
                    mPerson = person.get(position);
                    mPerson.setPerson_name(personName.getText().toString());
                    mPersonPresenter.updatePerson(mPerson, UPDATE_PERSON);
                }
                mAlertDialog.cancel();
                break;
        }
    }
}
