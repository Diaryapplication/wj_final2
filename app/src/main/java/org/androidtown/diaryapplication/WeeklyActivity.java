package org.androidtown.diaryapplication;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import org.androidtown.diaryapplication.db.ScheduleDatabase;

import java.util.Calendar;
import java.util.Date;

public class WeeklyActivity extends Activity {

    public static final String TAG = "WeeklyActivity";

    Calendar mCalendar = Calendar.getInstance();

    ListView mScheduleListView1;
    ListView mScheduleListView2;
    ListView mScheduleListView3;
    ListView mScheduleListView4;
    ListView mScheduleListView5;
    ListView mScheduleListView6;
    ListView mScheduleListView7;

    ScheduleListAdapter mScheduleListAdapter1;
    ScheduleListAdapter mScheduleListAdapter2;
    ScheduleListAdapter mScheduleListAdapter3;
    ScheduleListAdapter mScheduleListAdapter4;
    ScheduleListAdapter mScheduleListAdapter5;
    ScheduleListAdapter mScheduleListAdapter6;
    ScheduleListAdapter mScheduleListAdapter7;

    public static ScheduleDatabase mDatabase = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weekly);

        mScheduleListView1 = (ListView)findViewById(R.id.sunList);
        mScheduleListView2 = (ListView)findViewById(R.id.monList);
        mScheduleListView3 = (ListView)findViewById(R.id.tueList);
        mScheduleListView4 = (ListView)findViewById(R.id.wedList);
        mScheduleListView5 = (ListView)findViewById(R.id.thuList);
        mScheduleListView6 = (ListView)findViewById(R.id.friList);
        mScheduleListView7 = (ListView)findViewById(R.id.satList);

        mScheduleListAdapter1 = new ScheduleListAdapter(this);
        mScheduleListAdapter2 = new ScheduleListAdapter(this);
        mScheduleListAdapter3 = new ScheduleListAdapter(this);
        mScheduleListAdapter4 = new ScheduleListAdapter(this);
        mScheduleListAdapter5 = new ScheduleListAdapter(this);
        mScheduleListAdapter6 = new ScheduleListAdapter(this);
        mScheduleListAdapter7 = new ScheduleListAdapter(this);

        mScheduleListView1.setAdapter(mScheduleListAdapter1);
        mScheduleListView2.setAdapter(mScheduleListAdapter2);
        mScheduleListView3.setAdapter(mScheduleListAdapter3);
        mScheduleListView4.setAdapter(mScheduleListAdapter4);
        mScheduleListView5.setAdapter(mScheduleListAdapter5);
        mScheduleListView6.setAdapter(mScheduleListAdapter6);
        mScheduleListView7.setAdapter(mScheduleListAdapter7);

        onStart();
        init();

        mScheduleListView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                viewMemo1(position);
            }
        });

        mScheduleListView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                viewMemo2(position);
            }
        });

        mScheduleListView3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                viewMemo3(position);
            }
        });

        mScheduleListView4.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                viewMemo4(position);
            }
        });

        mScheduleListView5.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                viewMemo5(position);
            }
        });

        mScheduleListView6.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                viewMemo6(position);
            }
        });

        mScheduleListView7.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                viewMemo7(position);
            }
        });





        // 이전 주로 넘어가는 이벤트 처리
        Button weekPrevious = (Button) findViewById(R.id.weekPrevious);
        weekPrevious.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mCalendar.add(Calendar.DATE,-13);
                int curDay = mCalendar.get(Calendar.DAY_OF_MONTH);
                Button sunBtn = (Button)findViewById(R.id.sun);
                String curdayStr = String.valueOf(curDay);
                if (curDay < 10) {
                    curdayStr = "0" + curdayStr;
                }
                sunBtn.setText(curdayStr);
                loadScheduleListData(mScheduleListAdapter1);

                mCalendar.add(Calendar.DATE,1);
                int curDay2 = mCalendar.get(Calendar.DAY_OF_MONTH);
                Button monBtn = (Button)findViewById(R.id.mon);
                String curdayStr2 = String.valueOf(curDay2);
                if (curDay2 < 10) {
                    curdayStr2 = "0" + curdayStr2;
                }
                monBtn.setText(curdayStr2);
                loadScheduleListData(mScheduleListAdapter2);

                mCalendar.add(Calendar.DATE,1);
                int curDay3 = mCalendar.get(Calendar.DAY_OF_MONTH);
                Button tueBtn = (Button)findViewById(R.id.tue);
                String curdayStr3 = String.valueOf(curDay3);
                if (curDay3 < 10) {
                    curdayStr3 = "0" + curdayStr3;
                }
                tueBtn.setText(curdayStr3);
                loadScheduleListData(mScheduleListAdapter3);

                mCalendar.add(Calendar.DATE,1);
                int curDay4 = mCalendar.get(Calendar.DAY_OF_MONTH);
                Button wedBtn = (Button)findViewById(R.id.wed);
                String curdayStr4 = String.valueOf(curDay4);
                if (curDay4 < 10) {
                    curdayStr4 = "0" + curdayStr4;
                }
                wedBtn.setText(curdayStr4);
                loadScheduleListData(mScheduleListAdapter4);

                mCalendar.add(Calendar.DATE,1);
                int curDay5 = mCalendar.get(Calendar.DAY_OF_MONTH);
                Button thuBtn = (Button)findViewById(R.id.thu);
                String curdayStr5 = String.valueOf(curDay5);
                if (curDay5 < 10) {
                    curdayStr5 = "0" + curdayStr5;
                }
                thuBtn.setText(curdayStr5);
                loadScheduleListData(mScheduleListAdapter5);

                mCalendar.add(Calendar.DATE,1);
                int curDay6 = mCalendar.get(Calendar.DAY_OF_MONTH);
                Button friBtn = (Button)findViewById(R.id.fri);
                String curdayStr6 = String.valueOf(curDay6);
                if (curDay6 < 10) {
                    curdayStr6 = "0" + curdayStr6;
                }
                friBtn.setText(curdayStr6);
                loadScheduleListData(mScheduleListAdapter6);

                mCalendar.add(Calendar.DATE,1);
                int curDay7 = mCalendar.get(Calendar.DAY_OF_MONTH);
                Button satBtn = (Button)findViewById(R.id.sat);
                String curdayStr7 = String.valueOf(curDay7);
                if (curDay7 < 10) {
                    curdayStr7 = "0" + curdayStr7;
                }
                satBtn.setText(curdayStr7);
                loadScheduleListData(mScheduleListAdapter7);


            }
        });

        // 다음 주로 넘어가는 이벤트 처리
        Button weekNext = (Button) findViewById(R.id.weekNext);
        weekNext.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mCalendar.add(Calendar.DATE,1);
                int curDay = mCalendar.get(Calendar.DAY_OF_MONTH);
                Button sunBtn = (Button)findViewById(R.id.sun);
                String curdayStr = String.valueOf(curDay);
                if (curDay < 10) {
                    curdayStr = "0" + curdayStr;
                }
                sunBtn.setText(curdayStr);
                loadScheduleListData(mScheduleListAdapter1);

                mCalendar.add(Calendar.DATE,1);
                int curDay2 = mCalendar.get(Calendar.DAY_OF_MONTH);
                Button monBtn = (Button)findViewById(R.id.mon);
                String curdayStr2 = String.valueOf(curDay2);
                if (curDay2 < 10) {
                    curdayStr2 = "0" + curdayStr2;
                }
                monBtn.setText(curdayStr2);
                loadScheduleListData(mScheduleListAdapter2);

                mCalendar.add(Calendar.DATE,1);
                int curDay3 = mCalendar.get(Calendar.DAY_OF_MONTH);
                Button tueBtn = (Button)findViewById(R.id.tue);
                String curdayStr3 = String.valueOf(curDay3);
                if (curDay3 < 10) {
                    curdayStr3 = "0" + curdayStr3;
                }
                tueBtn.setText(curdayStr3);
                loadScheduleListData(mScheduleListAdapter3);

                mCalendar.add(Calendar.DATE,1);
                int curDay4 = mCalendar.get(Calendar.DAY_OF_MONTH);
                Button wedBtn = (Button)findViewById(R.id.wed);
                String curdayStr4 = String.valueOf(curDay4);
                if (curDay4 < 10) {
                    curdayStr4 = "0" + curdayStr4;
                }
                wedBtn.setText(curdayStr4);
                loadScheduleListData(mScheduleListAdapter4);

                mCalendar.add(Calendar.DATE,1);
                int curDay5 = mCalendar.get(Calendar.DAY_OF_MONTH);
                Button thuBtn = (Button)findViewById(R.id.thu);
                String curdayStr5 = String.valueOf(curDay5);
                if (curDay5 < 10) {
                    curdayStr5 = "0" + curdayStr5;
                }
                thuBtn.setText(curdayStr5);
                loadScheduleListData(mScheduleListAdapter5);

                mCalendar.add(Calendar.DATE,1);
                int curDay6 = mCalendar.get(Calendar.DAY_OF_MONTH);
                Button friBtn = (Button)findViewById(R.id.fri);
                String curdayStr6 = String.valueOf(curDay6);
                if (curDay6 < 10) {
                    curdayStr6 = "0" + curdayStr6;
                }
                friBtn.setText(curdayStr6);
                loadScheduleListData(mScheduleListAdapter6);

                mCalendar.add(Calendar.DATE,1);
                int curDay7 = mCalendar.get(Calendar.DAY_OF_MONTH);
                Button satBtn = (Button)findViewById(R.id.sat);
                String curdayStr7 = String.valueOf(curDay7);
                if (curDay7 < 10) {
                    curdayStr7 = "0" + curdayStr7;
                }
                satBtn.setText(curdayStr7);
                loadScheduleListData(mScheduleListAdapter7);
            }
        });


    }

    protected void onStart() {

        // 데이터베이스 열기
        openDatabase();

        super.onStart();
    }

    public void openDatabase() {

        // open database
        if (mDatabase != null) {
            mDatabase.close();
            mDatabase = null;
        }


        mDatabase = ScheduleDatabase.getInstance(this);
        boolean isOpen = mDatabase.open();
        if (isOpen) {
            Log.d(TAG, "Schedule database is open.");
        } else {
            Log.d(TAG, "Schedule database is not open.");
        }
    }

    private void init()
    {
        Date curDate = new Date();
        mCalendar.setTime(curDate);

        int dayOfWeek = mCalendar.get(Calendar.DAY_OF_WEEK);


        if (dayOfWeek == 1){

            int curDay = mCalendar.get(Calendar.DAY_OF_MONTH);
            Button sunBtn = (Button)findViewById(R.id.sun);
            String curdayStr = String.valueOf(curDay);
            if (curDay < 10) {
                curdayStr = "0" + curdayStr;
            }
            sunBtn.setText(curdayStr);
            loadScheduleListData(mScheduleListAdapter1);

            mCalendar.add(Calendar.DATE,1);
            int curDay2 = mCalendar.get(Calendar.DAY_OF_MONTH);
            Button monBtn = (Button)findViewById(R.id.mon);
            String curdayStr2 = String.valueOf(curDay2);
            if (curDay2 < 10) {
                curdayStr2 = "0" + curdayStr2;
            }
            monBtn.setText(curdayStr2);
            loadScheduleListData(mScheduleListAdapter2);

            mCalendar.add(Calendar.DATE,1);
            int curDay3 = mCalendar.get(Calendar.DAY_OF_MONTH);
            Button tueBtn = (Button)findViewById(R.id.tue);
            String curdayStr3 = String.valueOf(curDay3);
            if (curDay3 < 10) {
                curdayStr3 = "0" + curdayStr3;
            }
            tueBtn.setText(curdayStr3);
            loadScheduleListData(mScheduleListAdapter3);

            mCalendar.add(Calendar.DATE,1);
            int curDay4 = mCalendar.get(Calendar.DAY_OF_MONTH);
            Button wedBtn = (Button)findViewById(R.id.wed);
            String curdayStr4 = String.valueOf(curDay4);
            if (curDay4 < 10) {
                curdayStr4 = "0" + curdayStr4;
            }
            wedBtn.setText(curdayStr4);
            loadScheduleListData(mScheduleListAdapter4);

            mCalendar.add(Calendar.DATE,1);
            int curDay5 = mCalendar.get(Calendar.DAY_OF_MONTH);
            Button thuBtn = (Button)findViewById(R.id.thu);
            String curdayStr5 = String.valueOf(curDay5);
            if (curDay5 < 10) {
                curdayStr5 = "0" + curdayStr5;
            }
            thuBtn.setText(curdayStr5);
            loadScheduleListData(mScheduleListAdapter5);

            mCalendar.add(Calendar.DATE,1);
            int curDay6 = mCalendar.get(Calendar.DAY_OF_MONTH);
            Button friBtn = (Button)findViewById(R.id.fri);
            String curdayStr6 = String.valueOf(curDay6);
            if (curDay6 < 10) {
                curdayStr6 = "0" + curdayStr6;
            }
            friBtn.setText(curdayStr6);
            loadScheduleListData(mScheduleListAdapter6);

            mCalendar.add(Calendar.DATE,1);
            int curDay7 = mCalendar.get(Calendar.DAY_OF_MONTH);
            Button satBtn = (Button)findViewById(R.id.sat);
            String curdayStr7 = String.valueOf(curDay7);
            if (curDay7 < 10) {
                curdayStr7 = "0" + curdayStr7;
            }
            satBtn.setText(curdayStr7);
            loadScheduleListData(mScheduleListAdapter7);
        }

        else if (dayOfWeek==2){

            mCalendar.add(Calendar.DATE,-1);
            int curDay = mCalendar.get(Calendar.DAY_OF_MONTH);
            Button sunBtn = (Button)findViewById(R.id.sun);
            String curdayStr = String.valueOf(curDay);
            if (curDay < 10) {
                curdayStr = "0" + curdayStr;
            }
            sunBtn.setText(curdayStr);
            loadScheduleListData(mScheduleListAdapter1);

            mCalendar.add(Calendar.DATE,1);
            int curDay2 = mCalendar.get(Calendar.DAY_OF_MONTH);
            Button monBtn = (Button)findViewById(R.id.mon);
            String curdayStr2 = String.valueOf(curDay2);
            if (curDay2 < 10) {
                curdayStr2 = "0" + curdayStr2;
            }
            monBtn.setText(curdayStr2);
            loadScheduleListData(mScheduleListAdapter2);

            mCalendar.add(Calendar.DATE,1);
            int curDay3 = mCalendar.get(Calendar.DAY_OF_MONTH);
            Button tueBtn = (Button)findViewById(R.id.tue);
            String curdayStr3 = String.valueOf(curDay3);
            if (curDay3 < 10) {
                curdayStr3 = "0" + curdayStr3;
            }
            tueBtn.setText(curdayStr3);
            loadScheduleListData(mScheduleListAdapter3);

            mCalendar.add(Calendar.DATE,1);
            int curDay4 = mCalendar.get(Calendar.DAY_OF_MONTH);
            Button wedBtn = (Button)findViewById(R.id.wed);
            String curdayStr4 = String.valueOf(curDay4);
            if (curDay4 < 10) {
                curdayStr4 = "0" + curdayStr4;
            }
            wedBtn.setText(curdayStr4);
            loadScheduleListData(mScheduleListAdapter4);

            mCalendar.add(Calendar.DATE,1);
            int curDay5 = mCalendar.get(Calendar.DAY_OF_MONTH);
            Button thuBtn = (Button)findViewById(R.id.thu);
            String curdayStr5 = String.valueOf(curDay5);
            if (curDay5 < 10) {
                curdayStr5 = "0" + curdayStr5;
            }
            thuBtn.setText(curdayStr5);
            loadScheduleListData(mScheduleListAdapter5);

            mCalendar.add(Calendar.DATE,1);
            int curDay6 = mCalendar.get(Calendar.DAY_OF_MONTH);
            Button friBtn = (Button)findViewById(R.id.fri);
            String curdayStr6 = String.valueOf(curDay6);
            if (curDay6 < 10) {
                curdayStr6 = "0" + curdayStr6;
            }
            friBtn.setText(curdayStr6);
            loadScheduleListData(mScheduleListAdapter6);

            mCalendar.add(Calendar.DATE,1);
            int curDay7 = mCalendar.get(Calendar.DAY_OF_MONTH);
            Button satBtn = (Button)findViewById(R.id.sat);
            String curdayStr7 = String.valueOf(curDay7);
            if (curDay7 < 10) {
                curdayStr7 = "0" + curdayStr7;
            }
            satBtn.setText(curdayStr7);
            loadScheduleListData(mScheduleListAdapter7);

        }
        else if (dayOfWeek==3){
            mCalendar.add(Calendar.DATE,-2);
            int curDay = mCalendar.get(Calendar.DAY_OF_MONTH);
            Button sunBtn = (Button)findViewById(R.id.sun);
            String curdayStr = String.valueOf(curDay);
            if (curDay < 10) {
                curdayStr = "0" + curdayStr;
            }
            sunBtn.setText(curdayStr);
            loadScheduleListData(mScheduleListAdapter1);

            mCalendar.add(Calendar.DATE,1);
            int curDay2 = mCalendar.get(Calendar.DAY_OF_MONTH);
            Button monBtn = (Button)findViewById(R.id.mon);
            String curdayStr2 = String.valueOf(curDay2);
            if (curDay2 < 10) {
                curdayStr2 = "0" + curdayStr2;
            }
            monBtn.setText(curdayStr2);
            loadScheduleListData(mScheduleListAdapter2);

            mCalendar.add(Calendar.DATE,1);
            int curDay3 = mCalendar.get(Calendar.DAY_OF_MONTH);
            Button tueBtn = (Button)findViewById(R.id.tue);
            String curdayStr3 = String.valueOf(curDay3);
            if (curDay3 < 10) {
                curdayStr3 = "0" + curdayStr3;
            }
            tueBtn.setText(curdayStr3);
            loadScheduleListData(mScheduleListAdapter3);

            mCalendar.add(Calendar.DATE,1);
            int curDay4 = mCalendar.get(Calendar.DAY_OF_MONTH);
            Button wedBtn = (Button)findViewById(R.id.wed);
            String curdayStr4 = String.valueOf(curDay4);
            if (curDay4 < 10) {
                curdayStr4 = "0" + curdayStr4;
            }
            wedBtn.setText(curdayStr4);
            loadScheduleListData(mScheduleListAdapter4);

            mCalendar.add(Calendar.DATE,1);
            int curDay5 = mCalendar.get(Calendar.DAY_OF_MONTH);
            Button thuBtn = (Button)findViewById(R.id.thu);
            String curdayStr5 = String.valueOf(curDay5);
            if (curDay5 < 10) {
                curdayStr5 = "0" + curdayStr5;
            }
            thuBtn.setText(curdayStr5);
            loadScheduleListData(mScheduleListAdapter5);

            mCalendar.add(Calendar.DATE,1);
            int curDay6 = mCalendar.get(Calendar.DAY_OF_MONTH);
            Button friBtn = (Button)findViewById(R.id.fri);
            String curdayStr6 = String.valueOf(curDay6);
            if (curDay6 < 10) {
                curdayStr6 = "0" + curdayStr6;
            }
            friBtn.setText(curdayStr6);
            loadScheduleListData(mScheduleListAdapter6);

            mCalendar.add(Calendar.DATE,1);
            int curDay7 = mCalendar.get(Calendar.DAY_OF_MONTH);
            Button satBtn = (Button)findViewById(R.id.sat);
            String curdayStr7 = String.valueOf(curDay7);
            if (curDay7 < 10) {
                curdayStr7 = "0" + curdayStr7;
            }
            satBtn.setText(curdayStr7);
            loadScheduleListData(mScheduleListAdapter7);
        }
        else if (dayOfWeek==4){
            mCalendar.add(Calendar.DATE,-3);
            int curDay = mCalendar.get(Calendar.DAY_OF_MONTH);
            Button sunBtn = (Button)findViewById(R.id.sun);
            String curdayStr = String.valueOf(curDay);
            if (curDay < 10) {
                curdayStr = "0" + curdayStr;
            }
            sunBtn.setText(curdayStr);
            loadScheduleListData(mScheduleListAdapter1);

            mCalendar.add(Calendar.DATE,1);
            int curDay2 = mCalendar.get(Calendar.DAY_OF_MONTH);
            Button monBtn = (Button)findViewById(R.id.mon);
            String curdayStr2 = String.valueOf(curDay2);
            if (curDay2 < 10) {
                curdayStr2 = "0" + curdayStr2;
            }
            monBtn.setText(curdayStr2);
            loadScheduleListData(mScheduleListAdapter2);

            mCalendar.add(Calendar.DATE,1);
            int curDay3 = mCalendar.get(Calendar.DAY_OF_MONTH);
            Button tueBtn = (Button)findViewById(R.id.tue);
            String curdayStr3 = String.valueOf(curDay3);
            if (curDay3 < 10) {
                curdayStr3 = "0" + curdayStr3;
            }
            tueBtn.setText(curdayStr3);
            loadScheduleListData(mScheduleListAdapter3);

            mCalendar.add(Calendar.DATE,1);
            int curDay4 = mCalendar.get(Calendar.DAY_OF_MONTH);
            Button wedBtn = (Button)findViewById(R.id.wed);
            String curdayStr4 = String.valueOf(curDay4);
            if (curDay4 < 10) {
                curdayStr4 = "0" + curdayStr4;
            }
            wedBtn.setText(curdayStr4);
            loadScheduleListData(mScheduleListAdapter4);

            mCalendar.add(Calendar.DATE,1);
            int curDay5 = mCalendar.get(Calendar.DAY_OF_MONTH);
            Button thuBtn = (Button)findViewById(R.id.thu);
            String curdayStr5 = String.valueOf(curDay5);
            if (curDay5 < 10) {
                curdayStr5 = "0" + curdayStr5;
            }
            thuBtn.setText(curdayStr5);
            loadScheduleListData(mScheduleListAdapter5);

            mCalendar.add(Calendar.DATE,1);
            int curDay6 = mCalendar.get(Calendar.DAY_OF_MONTH);
            Button friBtn = (Button)findViewById(R.id.fri);
            String curdayStr6 = String.valueOf(curDay6);
            if (curDay6 < 10) {
                curdayStr6 = "0" + curdayStr6;
            }
            friBtn.setText(curdayStr6);
            loadScheduleListData(mScheduleListAdapter6);

            mCalendar.add(Calendar.DATE,1);
            int curDay7 = mCalendar.get(Calendar.DAY_OF_MONTH);
            Button satBtn = (Button)findViewById(R.id.sat);
            String curdayStr7 = String.valueOf(curDay7);
            if (curDay7 < 10) {
                curdayStr7 = "0" + curdayStr7;
            }
            satBtn.setText(curdayStr7);
            loadScheduleListData(mScheduleListAdapter7);

        }
        else if (dayOfWeek==5){
            mCalendar.add(Calendar.DATE,-4);
            int curDay = mCalendar.get(Calendar.DAY_OF_MONTH);
            Button sunBtn = (Button)findViewById(R.id.sun);
            String curdayStr = String.valueOf(curDay);
            if (curDay < 10) {
                curdayStr = "0" + curdayStr;
            }
            sunBtn.setText(curdayStr);
            loadScheduleListData(mScheduleListAdapter1);

            mCalendar.add(Calendar.DATE,1);
            int curDay2 = mCalendar.get(Calendar.DAY_OF_MONTH);
            Button monBtn = (Button)findViewById(R.id.mon);
            String curdayStr2 = String.valueOf(curDay2);
            if (curDay2 < 10) {
                curdayStr2 = "0" + curdayStr2;
            }
            monBtn.setText(curdayStr2);
            loadScheduleListData(mScheduleListAdapter2);

            mCalendar.add(Calendar.DATE,1);
            int curDay3 = mCalendar.get(Calendar.DAY_OF_MONTH);
            Button tueBtn = (Button)findViewById(R.id.tue);
            String curdayStr3 = String.valueOf(curDay3);
            if (curDay3 < 10) {
                curdayStr3 = "0" + curdayStr3;
            }
            tueBtn.setText(curdayStr3);
            loadScheduleListData(mScheduleListAdapter3);

            mCalendar.add(Calendar.DATE,1);
            int curDay4 = mCalendar.get(Calendar.DAY_OF_MONTH);
            Button wedBtn = (Button)findViewById(R.id.wed);
            String curdayStr4 = String.valueOf(curDay4);
            if (curDay4 < 10) {
                curdayStr4 = "0" + curdayStr4;
            }
            wedBtn.setText(curdayStr4);
            loadScheduleListData(mScheduleListAdapter4);

            mCalendar.add(Calendar.DATE,1);
            int curDay5 = mCalendar.get(Calendar.DAY_OF_MONTH);
            Button thuBtn = (Button)findViewById(R.id.thu);
            String curdayStr5 = String.valueOf(curDay5);
            if (curDay5 < 10) {
                curdayStr5 = "0" + curdayStr5;
            }
            thuBtn.setText(curdayStr5);
            loadScheduleListData(mScheduleListAdapter5);

            mCalendar.add(Calendar.DATE,1);
            int curDay6 = mCalendar.get(Calendar.DAY_OF_MONTH);
            Button friBtn = (Button)findViewById(R.id.fri);
            String curdayStr6 = String.valueOf(curDay6);
            if (curDay6 < 10) {
                curdayStr6 = "0" + curdayStr6;
            }
            friBtn.setText(curdayStr6);
            loadScheduleListData(mScheduleListAdapter6);

            mCalendar.add(Calendar.DATE,1);
            int curDay7 = mCalendar.get(Calendar.DAY_OF_MONTH);
            Button satBtn = (Button)findViewById(R.id.sat);
            String curdayStr7 = String.valueOf(curDay7);
            if (curDay7 < 10) {
                curdayStr7 = "0" + curdayStr7;
            }
            satBtn.setText(curdayStr7);
            loadScheduleListData(mScheduleListAdapter7);

        }
        else if (dayOfWeek==6){
            mCalendar.add(Calendar.DATE,-5);
            int curDay = mCalendar.get(Calendar.DAY_OF_MONTH);
            Button sunBtn = (Button)findViewById(R.id.sun);
            String curdayStr = String.valueOf(curDay);
            if (curDay < 10) {
                curdayStr = "0" + curdayStr;
            }
            sunBtn.setText(curdayStr);
            loadScheduleListData(mScheduleListAdapter1);

            mCalendar.add(Calendar.DATE,1);
            int curDay2 = mCalendar.get(Calendar.DAY_OF_MONTH);
            Button monBtn = (Button)findViewById(R.id.mon);
            String curdayStr2 = String.valueOf(curDay2);
            if (curDay2 < 10) {
                curdayStr2 = "0" + curdayStr2;
            }
            monBtn.setText(curdayStr2);
            loadScheduleListData(mScheduleListAdapter2);

            mCalendar.add(Calendar.DATE,1);
            int curDay3 = mCalendar.get(Calendar.DAY_OF_MONTH);
            Button tueBtn = (Button)findViewById(R.id.tue);
            String curdayStr3 = String.valueOf(curDay3);
            if (curDay3 < 10) {
                curdayStr3 = "0" + curdayStr3;
            }
            tueBtn.setText(curdayStr3);
            loadScheduleListData(mScheduleListAdapter3);

            mCalendar.add(Calendar.DATE,1);
            int curDay4 = mCalendar.get(Calendar.DAY_OF_MONTH);
            Button wedBtn = (Button)findViewById(R.id.wed);
            String curdayStr4 = String.valueOf(curDay4);
            if (curDay4 < 10) {
                curdayStr4 = "0" + curdayStr4;
            }
            wedBtn.setText(curdayStr4);
            loadScheduleListData(mScheduleListAdapter4);

            mCalendar.add(Calendar.DATE,1);
            int curDay5 = mCalendar.get(Calendar.DAY_OF_MONTH);
            Button thuBtn = (Button)findViewById(R.id.thu);
            String curdayStr5 = String.valueOf(curDay5);
            if (curDay5 < 10) {
                curdayStr5 = "0" + curdayStr5;
            }
            thuBtn.setText(curdayStr5);
            loadScheduleListData(mScheduleListAdapter5);

            mCalendar.add(Calendar.DATE,1);
            int curDay6 = mCalendar.get(Calendar.DAY_OF_MONTH);
            Button friBtn = (Button)findViewById(R.id.fri);
            String curdayStr6 = String.valueOf(curDay6);
            if (curDay6 < 10) {
                curdayStr6 = "0" + curdayStr6;
            }
            friBtn.setText(curdayStr6);
            loadScheduleListData(mScheduleListAdapter6);

            mCalendar.add(Calendar.DATE,1);
            int curDay7 = mCalendar.get(Calendar.DAY_OF_MONTH);
            Button satBtn = (Button)findViewById(R.id.sat);
            String curdayStr7 = String.valueOf(curDay7);
            if (curDay7 < 10) {
                curdayStr7 = "0" + curdayStr7;
            }
            satBtn.setText(curdayStr7);
            loadScheduleListData(mScheduleListAdapter7);

        }
        else if (dayOfWeek==7){
            mCalendar.add(Calendar.DATE,-6);
            int curDay = mCalendar.get(Calendar.DAY_OF_MONTH);
            Button sunBtn = (Button)findViewById(R.id.sun);
            String curdayStr = String.valueOf(curDay);
            if (curDay < 10) {
                curdayStr = "0" + curdayStr;
            }
            sunBtn.setText(curdayStr);
            loadScheduleListData(mScheduleListAdapter1);

            mCalendar.add(Calendar.DATE,1);
            int curDay2 = mCalendar.get(Calendar.DAY_OF_MONTH);
            Button monBtn = (Button)findViewById(R.id.mon);
            String curdayStr2 = String.valueOf(curDay2);
            if (curDay2 < 10) {
                curdayStr2 = "0" + curdayStr2;
            }
            monBtn.setText(curdayStr2);
            loadScheduleListData(mScheduleListAdapter2);

            mCalendar.add(Calendar.DATE,1);
            int curDay3 = mCalendar.get(Calendar.DAY_OF_MONTH);
            Button tueBtn = (Button)findViewById(R.id.tue);
            String curdayStr3 = String.valueOf(curDay3);
            if (curDay3 < 10) {
                curdayStr3 = "0" + curdayStr3;
            }
            tueBtn.setText(curdayStr3);
            loadScheduleListData(mScheduleListAdapter3);

            mCalendar.add(Calendar.DATE,1);
            int curDay4 = mCalendar.get(Calendar.DAY_OF_MONTH);
            Button wedBtn = (Button)findViewById(R.id.wed);
            String curdayStr4 = String.valueOf(curDay4);
            if (curDay4 < 10) {
                curdayStr4 = "0" + curdayStr4;
            }
            wedBtn.setText(curdayStr4);
            loadScheduleListData(mScheduleListAdapter4);

            mCalendar.add(Calendar.DATE,1);
            int curDay5 = mCalendar.get(Calendar.DAY_OF_MONTH);
            Button thuBtn = (Button)findViewById(R.id.thu);
            String curdayStr5 = String.valueOf(curDay5);
            if (curDay5 < 10) {
                curdayStr5 = "0" + curdayStr5;
            }
            thuBtn.setText(curdayStr5);
            loadScheduleListData(mScheduleListAdapter5);

            mCalendar.add(Calendar.DATE,1);
            int curDay6 = mCalendar.get(Calendar.DAY_OF_MONTH);
            Button friBtn = (Button)findViewById(R.id.fri);
            String curdayStr6 = String.valueOf(curDay6);
            if (curDay6 < 10) {
                curdayStr6 = "0" + curdayStr6;
            }
            friBtn.setText(curdayStr6);
            loadScheduleListData(mScheduleListAdapter6);

            mCalendar.add(Calendar.DATE,1);
            int curDay7 = mCalendar.get(Calendar.DAY_OF_MONTH);
            Button satBtn = (Button)findViewById(R.id.sat);
            String curdayStr7 = String.valueOf(curDay7);
            if (curDay7 < 10) {
                curdayStr7 = "0" + curdayStr7;
            }
            satBtn.setText(curdayStr7);
            loadScheduleListData(mScheduleListAdapter7);

        }
    }



    private void viewMemo1(int position) {
        ScheduleListItem item = (ScheduleListItem)mScheduleListAdapter1.getItem(position);

        Intent intent = new Intent(getApplicationContext(), InsertScheduleActivity.class);
        intent.putExtra(BasicInfo.KEY_MEMO_MODE, BasicInfo.MODE_VIEW);
        intent.putExtra(BasicInfo.KEY_MEMO_ID, item.getId());
        intent.putExtra(BasicInfo.KEY_MEMO_DATE, item.getData(0));
        intent.putExtra(BasicInfo.KEY_MEMO_TEXT, item.getData(1));

        startActivityForResult(intent, BasicInfo.REQ_VIEW_ACTIVITY);
    }

    private void viewMemo2(int position) {
        ScheduleListItem item = (ScheduleListItem)mScheduleListAdapter2.getItem(position);

        Intent intent = new Intent(getApplicationContext(), InsertScheduleActivity.class);
        intent.putExtra(BasicInfo.KEY_MEMO_MODE, BasicInfo.MODE_VIEW);
        intent.putExtra(BasicInfo.KEY_MEMO_ID, item.getId());
        intent.putExtra(BasicInfo.KEY_MEMO_DATE, item.getData(0));
        intent.putExtra(BasicInfo.KEY_MEMO_TEXT, item.getData(1));

        startActivityForResult(intent, BasicInfo.REQ_VIEW_ACTIVITY);
    }
    private void viewMemo3(int position) {
        ScheduleListItem item = (ScheduleListItem)mScheduleListAdapter3.getItem(position);

        Intent intent = new Intent(getApplicationContext(), InsertScheduleActivity.class);
        intent.putExtra(BasicInfo.KEY_MEMO_MODE, BasicInfo.MODE_VIEW);
        intent.putExtra(BasicInfo.KEY_MEMO_ID, item.getId());
        intent.putExtra(BasicInfo.KEY_MEMO_DATE, item.getData(0));
        intent.putExtra(BasicInfo.KEY_MEMO_TEXT, item.getData(1));

        startActivityForResult(intent, BasicInfo.REQ_VIEW_ACTIVITY);
    }
    private void viewMemo4(int position) {
        ScheduleListItem item = (ScheduleListItem)mScheduleListAdapter4.getItem(position);

        Intent intent = new Intent(getApplicationContext(), InsertScheduleActivity.class);
        intent.putExtra(BasicInfo.KEY_MEMO_MODE, BasicInfo.MODE_VIEW);
        intent.putExtra(BasicInfo.KEY_MEMO_ID, item.getId());
        intent.putExtra(BasicInfo.KEY_MEMO_DATE, item.getData(0));
        intent.putExtra(BasicInfo.KEY_MEMO_TEXT, item.getData(1));

        startActivityForResult(intent, BasicInfo.REQ_VIEW_ACTIVITY);
    }
    private void viewMemo5(int position) {
        ScheduleListItem item = (ScheduleListItem)mScheduleListAdapter5.getItem(position);

        Intent intent = new Intent(getApplicationContext(), InsertScheduleActivity.class);
        intent.putExtra(BasicInfo.KEY_MEMO_MODE, BasicInfo.MODE_VIEW);
        intent.putExtra(BasicInfo.KEY_MEMO_ID, item.getId());
        intent.putExtra(BasicInfo.KEY_MEMO_DATE, item.getData(0));
        intent.putExtra(BasicInfo.KEY_MEMO_TEXT, item.getData(1));

        startActivityForResult(intent, BasicInfo.REQ_VIEW_ACTIVITY);
    }
    private void viewMemo6(int position) {
        ScheduleListItem item = (ScheduleListItem)mScheduleListAdapter6.getItem(position);

        Intent intent = new Intent(getApplicationContext(), InsertScheduleActivity.class);
        intent.putExtra(BasicInfo.KEY_MEMO_MODE, BasicInfo.MODE_VIEW);
        intent.putExtra(BasicInfo.KEY_MEMO_ID, item.getId());
        intent.putExtra(BasicInfo.KEY_MEMO_DATE, item.getData(0));
        intent.putExtra(BasicInfo.KEY_MEMO_TEXT, item.getData(1));

        startActivityForResult(intent, BasicInfo.REQ_VIEW_ACTIVITY);
    }
    private void viewMemo7(int position) {
        ScheduleListItem item = (ScheduleListItem)mScheduleListAdapter7.getItem(position);

        Intent intent = new Intent(getApplicationContext(), InsertScheduleActivity.class);
        intent.putExtra(BasicInfo.KEY_MEMO_MODE, BasicInfo.MODE_VIEW);
        intent.putExtra(BasicInfo.KEY_MEMO_ID, item.getId());
        intent.putExtra(BasicInfo.KEY_MEMO_DATE, item.getData(0));
        intent.putExtra(BasicInfo.KEY_MEMO_TEXT, item.getData(1));

        startActivityForResult(intent, BasicInfo.REQ_VIEW_ACTIVITY);
    }

    public int loadScheduleListData(ScheduleListAdapter mScheduleListAdapter) {

        int curYear = mCalendar.get(Calendar.YEAR);
        int curMonth = mCalendar.get(Calendar.MONTH);
        int curDay = mCalendar.get(Calendar.DAY_OF_MONTH);

        String date = String.valueOf(curYear);
        String date1 = date.concat("-" + String.valueOf(curMonth+1));
        String date2 = date1.concat("-" + String.valueOf(curDay));

        String date3 = date2.concat(" 00:00:01");
        String date4 = date2.concat(" 23:59:59");

        String SQL = "select _id, INPUT_DATE, CONTENT_TEXT from SCHEDULE WHERE INPUT_DATE BETWEEN '"+date3+"' AND '"+date4+"'";

        int recordCount = -1;

        if (WeeklyActivity.mDatabase != null) {
            Cursor outCursor = WeeklyActivity.mDatabase.rawQuery(SQL);

            recordCount = outCursor.getCount();
            Log.d(TAG, "cursor count : " + recordCount + "\n");

            mScheduleListAdapter.clear();

            for (int i = 0; i < recordCount; i++) {
                outCursor.moveToNext();

                String scheduleId = outCursor.getString(0);

                String dateStr = outCursor.getString(1);
                if (dateStr != null && dateStr.length() > 10) {

                    try {
                        Date inDate = BasicInfo.dateTimeFormat.parse(dateStr);

                        if (BasicInfo.language.equals("ko")) {
                            dateStr = BasicInfo.dateTimeFormat.format(inDate);
                        } else {
                            dateStr = BasicInfo.dateTimeFormat.format(inDate);
                        }
                    } catch(Exception ex) {
                        ex.printStackTrace();
                    }
                } else {
                    dateStr = "";
                }

                String scheduleStr = outCursor.getString(2);


                mScheduleListAdapter.addItem(new ScheduleListItem(scheduleId, dateStr, scheduleStr));
            }

            outCursor.close();

            mScheduleListAdapter.notifyDataSetChanged();

        }

        return recordCount;

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch(requestCode) {
            case BasicInfo.REQ_INSERT_ACTIVITY:
                if(resultCode == RESULT_OK) {
                    init();
                }

                break;

            case BasicInfo.REQ_VIEW_ACTIVITY:
                init();
                break;

        }
    }



}
