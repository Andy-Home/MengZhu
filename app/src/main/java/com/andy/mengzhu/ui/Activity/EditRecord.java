package com.andy.mengzhu.ui.Activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.andy.greendao.Category;
import com.andy.greendao.Funds;
import com.andy.greendao.Record;
import com.andy.mengzhu.R;
import com.andy.mengzhu.app.util.DateUtil;
import com.andy.mengzhu.presenter.CategoryPresenter;
import com.andy.mengzhu.presenter.FundsPresenter;
import com.andy.mengzhu.presenter.RecordPresenter;
import com.andy.mengzhu.presenter.impl.CategoryPresenterImpl;
import com.andy.mengzhu.presenter.impl.FundsPresenterImpl;
import com.andy.mengzhu.presenter.impl.RecordPresenterImpl;
import com.andy.mengzhu.ui.adapter.DateAdapter;
import com.andy.mengzhu.ui.adapter.SpinnerAdapter;
import com.andy.mengzhu.ui.common.BaseActivity;
import com.andy.mengzhu.ui.component.DateChooser;
import com.andy.mengzhu.ui.view.DataRequestView;
import com.andy.mengzhu.ui.view.DividerItemDecoration;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/7/12 0012.
 */
public class EditRecord extends BaseActivity implements View.OnClickListener, DataRequestView {
    /**
     * 选择类别的控件
     */
    private Spinner categoryView;

    /**
     * 选择扣款项或者收入项
     */
    private Spinner fundsView;

    /**
     * 账务金额填写控件
     */
    private EditText numView;

    /**
     * 账务详情的填写控件
     */
    private EditText descView;

    /**
     * 日期选择控件
     */
    private DateChooser dateView;

    /**
     * 日期适配器
     */
    DateAdapter dateAdapter = null;

    /**
     * 显示日期的 View
     */
    private TextView record_date;

    /**
     * 显示类别的 View
     */
    private TextView record_category;

    /**
     * 显示账务金额的 View
     */
    private TextView record_num;

    /**
     * 显示账务扣款项或收入项
     */
    private TextView record_funds;

    /**
     * 显示账务详情
     */
    private TextView record_desc;

    /**
     * 点击该按钮，保存账务信息
     */
    private Button save_record;

    /**
     * Presenter 层
     */
    private RecordPresenter mRecordPresenter;
    private FundsPresenter mFundsPresenter;
    private CategoryPresenter mCategoryPresenter;

    /**
     * 保存需要显示的 Funds 类型的数据
     */
    private List<Funds> fundsList = null;

    /**
     * 保存需要显示的 Category 类型的数据
     */
    private List<Category> categoryList = null;

    private Long fundsID = null;
    private Long categoryID = null;
    /**
     * 保存的账务的类型. 0为支出、1为收入、2为收借款、3为转账
     */
    private int type = 0;

    /**
     * 最初的日期
     */
    private Long initDate;

    /**
     * 编辑的记录的ID
     */
    private Long id;

    /**
     * 上一级页面传过来的值
     */
    private Bundle mBundle;

    private boolean isFirst_Funds = true;
    private boolean isFirst_Category = true;
    private static final int GET_FUNDS = 1;
    private static final int GET_CATEGORY = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record);

        findView();
        initData();
        initView();
        initializeView();
        setListener();
    }

    private void findView() {
        record_date = (TextView) findViewById(R.id.record_date);
        record_category = (TextView) findViewById(R.id.record_category);
        record_num = (TextView) findViewById(R.id.record_num);
        record_funds = (TextView) findViewById(R.id.record_funds);
        record_desc = (TextView) findViewById(R.id.record_desc);

        categoryView = (Spinner) findViewById(R.id.finance_category);
        fundsView = (Spinner) findViewById(R.id.finance_funds);
        dateView = (DateChooser) findViewById(R.id.finance_date);
        numView = (EditText) findViewById(R.id.finance_num);
        descView = (EditText) findViewById(R.id.finance_desc);
        save_record = (Button) findViewById(R.id.save);
    }

    private void initData() {
        mBundle = getIntent().getExtras();
        initDate = mBundle.getLong("date");
        id = mBundle.getLong("id");
        fundsID = mBundle.getLong("fundsId");
        categoryID = mBundle.getLong("categoryId");
        type = mBundle.getInt("type");

        mRecordPresenter = new RecordPresenterImpl(this, this);
        mFundsPresenter = new FundsPresenterImpl(this, this);
        mCategoryPresenter = new CategoryPresenterImpl(this, this);

        mFundsPresenter.getFunds(GET_FUNDS);
        mCategoryPresenter.getCategory(GET_CATEGORY);
    }

    private void initView() {
        record_num.setText("" + mBundle.getDouble("num"));
        record_desc.setText(mBundle.getString("desc"));
        record_funds.setText(mBundle.getString("funds"));
        record_category.setText(mBundle.getString("category"));

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        record_date.setText(sdf.format(new Date(initDate)));
    }

    private void setListener() {
        //类别的监听
        categoryView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (isFirst_Category) {
                    isFirst_Category = false;
                    view.setVisibility(View.INVISIBLE);
                    return;
                }
                record_category.setText(categoryList.get(i).getCategory_name());
                categoryID = categoryList.get(i).getId();
                type = categoryList.get(i).getType();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

                record_category.setText(categoryList.get(0).getCategory_name());

            }
        });

        //扣款项或支出项的监听
        fundsView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (isFirst_Funds) {
                    isFirst_Funds = false;
                    view.setVisibility(View.INVISIBLE);
                    return;
                }
                record_funds.setText(fundsList.get(i).getFunds_name());
                fundsID = fundsList.get(i).getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                record_funds.setText(fundsList.get(0).getFunds_name());
            }
        });

        dateView.setOnItemScrollChangeListener(new DateChooser.OnItemScrollChangeListener() {
            @Override
            public void onChange(View view, int position) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
                record_date.setText(sdf.format(dateAdapter.getDate(position)));
            }
        });

        // 账目金额的监听
        numView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                record_num.setText(numView.getText().toString());
            }
        });

        descView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                record_desc.setText(descView.getText().toString());
            }
        });

        save_record.setOnClickListener(this);
    }

    private void initializeView() {
        //配置类别选择器
        SpinnerAdapter adapter = new SpinnerAdapter(this, categoryList, false);
        categoryView.setAdapter(adapter);

        //配置资金流动项选择器，即收入项与扣款项
        SpinnerAdapter adapter1 = new SpinnerAdapter(this, fundsList);
        fundsView.setAdapter(adapter1);

        //配置日期选择器
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        dateView.setLayoutManager(mLayoutManager);
        dateView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL_LIST));
        dateView.setHasFixedSize(true);
        dateAdapter = new DateAdapter();
        dateView.setAdapter(dateAdapter);
        try {
            dateView.scrollToPosition(DateUtil.getPosition(initDate) - 1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View view) {
        Record record = new Record();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        record.setId(null);
        record.setNum(Double.valueOf(record_num.getText().toString()));
        try {
            record.setDate(sdf.parse(record_date.getText().toString()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        record.setCategory_id(categoryID);
        record.setFunds_id(fundsID);
        record.setDesc(record_desc.getText().toString());
        record.setType(type);
        record.setId(id);
        mRecordPresenter.updateRecord(record);

        finish();
    }

    @Override
    public void showError(int requestCode) {

    }

    @Override
    public void setView(Object object, int requestCode) {
        switch (requestCode) {
            case GET_FUNDS:
                fundsList = (List<Funds>) object;
                break;
            case GET_CATEGORY:
                categoryList = (List<Category>) object;
                break;
        }
    }

}
