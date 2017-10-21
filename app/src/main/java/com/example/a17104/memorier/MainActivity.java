package com.example.a17104.memorier;

import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<DailyBean> mCostBeanList;
    private DataBaseHelper mDatabaseHelper;
    private CostListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        mDatabaseHelper=new DataBaseHelper(this);
        mCostBeanList=new ArrayList<>();
        final ListView costList = (ListView)findViewById(R.id.lv_main);
        initCostData();
        adapter = new CostListAdapter(this, mCostBeanList);
        costList.setAdapter(adapter);

       FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                LayoutInflater inflate=LayoutInflater.from(MainActivity.this);
                View viewDialog= inflate.inflate(R.layout.new_cost_data,null);
                final EditText account= (EditText) viewDialog.findViewById(R.id.et_cost_title);
                final EditText password= (EditText) viewDialog.findViewById(R.id.et_cost_money);
                final DatePicker date= (DatePicker) viewDialog.findViewById(R.id.dp_cost_date);
                builder.setView(viewDialog);

                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                DailyBean dailyBean = new DailyBean();
                                dailyBean.addAccount = account.getText().toString();
                                dailyBean.addPassword = password.getText().toString();
                                dailyBean.costDate = date.getYear() + "-" + (date.getMonth() + 1) + "-" +
                                        date.getDayOfMonth();
                                mDatabaseHelper.insertCost(dailyBean);
                                mCostBeanList.add(dailyBean);
                                adapter.notifyDataSetChanged();
                            }
                        });
                builder.setNegativeButton("Cancel",null);
                builder.create().show();
            }
        });
    }

    private void initCostData() {
        //mDatabaseHelper.deleteAllData();    //清空数据
        //for (int i=0;i<3;i++) {
          //  CostBean costBean=new CostBean();
         //   costBean.costTitle=i+1+"."+"QQ";
         //   costBean.costDate="2017-10-19";
          //  costBean.costMoney="20";
          //  mDatabaseHelper.insertCost(costBean);
            //mCostBeanList.add(costBean);
       // }
        Cursor cursor= mDatabaseHelper.getAllCostData();
        if(cursor!=null){
            while(cursor.moveToNext()){
                DailyBean dailyBean=new DailyBean();
                dailyBean.addAccount=cursor.getString(cursor.getColumnIndex("cost_title"));
                dailyBean.costDate=cursor.getString(cursor.getColumnIndex("cost_date"));
                dailyBean.addPassword=cursor.getString(cursor.getColumnIndex("cost_money"));
                mCostBeanList.add(dailyBean);
            }
            cursor.close();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_menu) {
            //点击菜单选项弹出图表
           // Intent intent=new Intent(MainActivity.this,ChartActivity.class);
            //intent.putExtra("cost_list", (Serializable) mCostBeanList);
            //startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
