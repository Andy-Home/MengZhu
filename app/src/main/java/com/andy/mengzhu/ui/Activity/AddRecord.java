package com.andy.mengzhu.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.andy.greendao.Category;
import com.andy.greendao.Funds;
import com.andy.greendao.Person;
import com.andy.greendao.Record;
import com.andy.mengzhu.R;
import com.andy.mengzhu.app.util.DateUtil;
import com.andy.mengzhu.presenter.CategoryPresenter;
import com.andy.mengzhu.presenter.FundsPresenter;
import com.andy.mengzhu.presenter.PersonPresenter;
import com.andy.mengzhu.presenter.RecordPresenter;
import com.andy.mengzhu.presenter.impl.CategoryPresenterImpl;
import com.andy.mengzhu.presenter.impl.FundsPresenterImpl;
import com.andy.mengzhu.presenter.impl.PersonPresenterImpl;
import com.andy.mengzhu.presenter.impl.RecordPresenterImpl;
import com.andy.mengzhu.ui.adapter.DateAdapter;
import com.andy.mengzhu.ui.adapter.SpinnerAdapter;
import com.andy.mengzhu.ui.common.BaseActivity;
import com.andy.mengzhu.ui.component.DateChooser;
import com.andy.mengzhu.ui.view.DataRequestView;
import com.andy.mengzhu.ui.view.DividerItemDecoration;

import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * 记账界面
 *
 * Created by Administrator on 2016/6/28 0028.
 */
public class AddRecord extends BaseActivity implements View.OnClickListener, DataRequestView {
     /**
     * 选择类别的控件
     */
    private Spinner categoryView;

    /**
     * 选择扣款项或者收入项
     */
    private Spinner fundsView;

    /**
     * 转账时才会显示的收入项
     */
    private Spinner fundsView1;

    /**
     * 还/借款时显示该视图
     */
    private Spinner personView;

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
     * 金额的转移方向
     */
    private TextView record_dir;

    /**
     * 金额的转移目标
     */
    private TextView record_more;

    /**
     * 根据借款与收款项判断显示文字是收款人还是借款人
     */
    private TextView person_txt;

    /**
     * 点击该按钮，保存账务信息
     */
    private Button save_record;

    /**
     * 收借款人布局，只有当收借款时才显示
     */
    private RelativeLayout person_layout;

    private RelativeLayout funds_layout;
    /**
     * Presenter 层
     */
    private RecordPresenter mRecordPresenter;
    private FundsPresenter mFundsPresenter;
    private CategoryPresenter mCategoryPresenter;
    private PersonPresenter mPersonPresenter;

    /**
     * 保存需要显示的 Funds 类型的数据
     */
    private List<Funds> fundsList = null;

    /**
     * 保存需要显示的 Category 类型的数据
     */
    private List<Category> categoryList = null;

    /**
     * 保存需要显示的 Person 类型数据
     */
    private List<Person> personList = null;

    private Long fundsID = null;
    private Long categoryID = null;
    /**
     * 保存的账务的类型. 0为支出、1为收入、2为收借款、3为转账
     */
    private int type = 0;

    private static final int GET_FUNDS = 1;
    private static final int GET_CATEGORY = 2;
    private static final int GET_PERSON = 3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record);

        findView();
        initData();
        initializeView();
        setListener();
    }

    private void findView() {
        record_date = (TextView) findViewById(R.id.record_date);
        record_category = (TextView) findViewById(R.id.record_category);
        record_num = (TextView) findViewById(R.id.record_num);
        record_funds = (TextView) findViewById(R.id.record_funds);
        record_desc = (TextView) findViewById(R.id.record_desc);
        record_more = (TextView) findViewById(R.id.record_more);
        record_dir = (TextView) findViewById(R.id.record_dir);
        person_txt = (TextView) findViewById(R.id.finance_person_txt);

        categoryView = (Spinner) findViewById(R.id.finance_category);
        fundsView = (Spinner) findViewById(R.id.finance_funds);
        fundsView1 = (Spinner) findViewById(R.id.finance_funds1);
        personView = (Spinner) findViewById(R.id.finance_person);
        dateView = (DateChooser) findViewById(R.id.finance_date);
        numView = (EditText) findViewById(R.id.finance_num);
        descView = (EditText) findViewById(R.id.finance_desc);
        save_record = (Button) findViewById(R.id.save);

        person_layout = (RelativeLayout) findViewById(R.id.finance_person_layout);
        funds_layout = (RelativeLayout) findViewById(R.id.finance_funds_layout1);
    }

    private void initData() {
        mRecordPresenter = new RecordPresenterImpl(this, this);
        mFundsPresenter = new FundsPresenterImpl(this, this);
        mCategoryPresenter = new CategoryPresenterImpl(this, this);
        mPersonPresenter = new PersonPresenterImpl(this, this);

        mFundsPresenter.getFunds(GET_FUNDS);
        mCategoryPresenter.getCategory(GET_CATEGORY);
        mPersonPresenter.getPerson(GET_PERSON);
    }

    private void setListener() {
        //类别的监听
        categoryView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                record_category.setText(categoryList.get(i).getCategory_name());
                categoryID = categoryList.get(i).getId();
                type = categoryList.get(i).getType();
                if (type == 2) {
                    if (funds_layout.getVisibility() == View.VISIBLE) {
                        funds_layout.setVisibility(View.GONE);
                    }
                    person_layout.setVisibility(View.VISIBLE);
                    String categoryName = categoryList.get(i).getCategory_name();
                    if (categoryName.equals("借出") || categoryName.equals("还款")) {
                        person_txt.setText(R.string.relation_person);
                        record_dir.setText(R.string.right_to_left);
                    } else if (categoryName.equals("借入") || categoryName.equals("收款")) {
                        person_txt.setText(R.string.relation_person);
                        record_dir.setText(R.string.left_to_right);
                    }
                } else if (type == 3) {
                    if (person_layout.getVisibility() == View.VISIBLE) {
                        person_layout.setVisibility(View.GONE);
                    }
                    funds_layout.setVisibility(View.VISIBLE);
                    record_dir.setText(R.string.right_to_left);
                } else {
                    if (person_layout.getVisibility() == View.VISIBLE) {
                        person_layout.setVisibility(View.GONE);
                    }
                    if (funds_layout.getVisibility() == View.VISIBLE) {
                        funds_layout.setVisibility(View.GONE);
                    }
                    record_dir.setText("");
                    record_more.setText("");
                }
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
                record_funds.setText(fundsList.get(i).getFunds_name());
                fundsID = fundsList.get(i).getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                record_funds.setText(fundsList.get(0).getFunds_name());
            }
        });

        //转账的转入项
        fundsView1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                record_more.setText(fundsList.get(i).getFunds_name());
                fundsID = fundsList.get(i).getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                record_more.setText(fundsList.get(0).getFunds_name());
            }
        });

        //收/借款的收/借款人
        personView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                record_more.setText(personList.get(i).getPerson_name());
                fundsID = fundsList.get(i).getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                record_more.setText(personList.get(0).getPerson_name());
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
        SpinnerAdapter adapter = new SpinnerAdapter(this, categoryList);
        categoryView.setAdapter(adapter);

        //配置资金流动项选择器，即收入项与扣款项
        SpinnerAdapter adapter1 = new SpinnerAdapter(this, fundsList);
        fundsView.setAdapter(adapter1);
        fundsView1.setAdapter(adapter1);

        SpinnerAdapter adapter2 = new SpinnerAdapter(this, personList);
        personView.setAdapter(adapter2);

        //配置日期选择器
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        dateView.setLayoutManager(mLayoutManager);
        dateView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL_LIST));
        dateView.setHasFixedSize(true);
        dateAdapter = new DateAdapter();
        dateView.setAdapter(dateAdapter);
        dateView.scrollToPosition(DateUtil.getPosition()-1);
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
        mRecordPresenter.saveRecord(record);

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
            case GET_PERSON:
                personList = (List<Person>) object;
                break;
        }
    }
}
