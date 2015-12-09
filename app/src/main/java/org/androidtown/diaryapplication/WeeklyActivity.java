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
                viewMemo(position, mScheduleListAdapter1);
            }
        });

        mScheduleListView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                viewMemo(position, mScheduleListAdapter2);
            }
        });

        mScheduleListView3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                viewMemo(position, mScheduleListAdapter3);
            }
        });

        mScheduleListView4.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                viewMemo(position, mScheduleListAdapter4);
            }
        });

        mScheduleListView5.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                viewMemo(position, mScheduleListAdapter5);
            }
        });

        mScheduleListView6.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                viewMemo(position, mScheduleListAdapter6);
            }
        });

        mScheduleListView7.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                viewMemo(position, mScheduleListAdapter7);
            }
        });





        // 이전 주로 넘어가는 이벤트 처리
        Button weekPrevious = (Button) findViewById(R.id.weekPrevious);
        weekPrevious.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mCalendar.add(Calendar.DATE,-13);
                final int curDay = mCalendar.get(Calendar.DAY_OF_MONTH);
                Button sunBtn = (Button)findViewById(R.id.sun);
                String curdayStr = String.valueOf(curDay);
                if (curDay < 10) {
                    curdayStr = "0" + curdayStr;
                }
                sunBtn.setText((mCalendar.get(Calendar.MONTH)+1) + "/" + curdayStr);
                loadScheduleListData(mScheduleListAdapter1);
                /////////////////////
                sunBtn.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                        int dayday = curDay;
                        int monmon = mCalendar.get(Calendar.MONTH);
                        int yearyear = mCalendar.get(Calendar.YEAR);

                        intent.putExtra(BasicInfo.KEY_DAY_FROM_WEEK, String.valueOf(dayday));
                        intent.putExtra(BasicInfo.KEY_MONTH_FROM_WEEK, String.valueOf(monmon));
                        intent.putExtra(BasicInfo.KEY_YEAR_FROM_WEEK, String.valueOf(yearyear));

                        //intent.putExtra("111", "heyeheheh");
                        Log.d("WeeklyActivity", "Selected : " + dayday + " " + monmon + " " + yearyear);

                        setResult(RESULT_OK, intent);
                        finish();
                    }
                });
                ////////////////////

                mCalendar.add(Calendar.DATE,1);
                final int curDay2 = mCalendar.get(Calendar.DAY_OF_MONTH);
                Button monBtn = (Button)findViewById(R.id.mon);
                String curdayStr2 = String.valueOf(curDay2);
                if (curDay2 < 10) {
                    curdayStr2 = "0" + curdayStr2;
                }
                monBtn.setText((mCalendar.get(Calendar.MONTH)+1) + "/" + curdayStr2);
                loadScheduleListData(mScheduleListAdapter2);
                /////////////////////
                monBtn.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                        int dayday = curDay2;
                        int monmon = mCalendar.get(Calendar.MONTH);
                        int yearyear = mCalendar.get(Calendar.YEAR);

                        intent.putExtra(BasicInfo.KEY_DAY_FROM_WEEK, String.valueOf(dayday));
                        intent.putExtra(BasicInfo.KEY_MONTH_FROM_WEEK, String.valueOf(monmon));
                        intent.putExtra(BasicInfo.KEY_YEAR_FROM_WEEK, String.valueOf(yearyear));

                        //intent.putExtra("111", "heyeheheh");
                        Log.d("WeeklyActivity", "Selected : " + dayday + " " + monmon + " " + yearyear);

                        setResult(RESULT_OK, intent);
                        finish();
                    }
                });
                ////////////////////

                mCalendar.add(Calendar.DATE,1);
                final int curDay3 = mCalendar.get(Calendar.DAY_OF_MONTH);
                Button tueBtn = (Button)findViewById(R.id.tue);
                String curdayStr3 = String.valueOf(curDay3);
                if (curDay3 < 10) {
                    curdayStr3 = "0" + curdayStr3;
                }
                tueBtn.setText((mCalendar.get(Calendar.MONTH)+1) + "/" + curdayStr3);
                loadScheduleListData(mScheduleListAdapter3);
                /////////////////////
                tueBtn.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                        int dayday = curDay3;
                        int monmon = mCalendar.get(Calendar.MONTH);
                        int yearyear = mCalendar.get(Calendar.YEAR);

                        intent.putExtra(BasicInfo.KEY_DAY_FROM_WEEK, String.valueOf(dayday));
                        intent.putExtra(BasicInfo.KEY_MONTH_FROM_WEEK, String.valueOf(monmon));
                        intent.putExtra(BasicInfo.KEY_YEAR_FROM_WEEK, String.valueOf(yearyear));

                        //intent.putExtra("111", "heyeheheh");
                        Log.d("WeeklyActivity", "Selected : " + dayday + " " + monmon + " " + yearyear);

                        setResult(RESULT_OK, intent);
                        finish();
                    }
                });
                ////////////////////

                mCalendar.add(Calendar.DATE,1);
                final int curDay4 = mCalendar.get(Calendar.DAY_OF_MONTH);
                Button wedBtn = (Button)findViewById(R.id.wed);
                String curdayStr4 = String.valueOf(curDay4);
                if (curDay4 < 10) {
                    curdayStr4 = "0" + curdayStr4;
                }
                wedBtn.setText((mCalendar.get(Calendar.MONTH)+1) + "/" + curdayStr4);
                loadScheduleListData(mScheduleListAdapter4);
                /////////////////////
                wedBtn.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                        int dayday = curDay4;
                        int monmon = mCalendar.get(Calendar.MONTH);
                        int yearyear = mCalendar.get(Calendar.YEAR);

                        intent.putExtra(BasicInfo.KEY_DAY_FROM_WEEK, String.valueOf(dayday));
                        intent.putExtra(BasicInfo.KEY_MONTH_FROM_WEEK, String.valueOf(monmon));
                        intent.putExtra(BasicInfo.KEY_YEAR_FROM_WEEK, String.valueOf(yearyear));

                        //intent.putExtra("111", "heyeheheh");
                        Log.d("WeeklyActivity", "Selected : " + dayday + " " + monmon + " " + yearyear);

                        setResult(RESULT_OK, intent);
                        finish();
                    }
                });
                ////////////////////

                mCalendar.add(Calendar.DATE,1);
                final int curDay5 = mCalendar.get(Calendar.DAY_OF_MONTH);
                Button thuBtn = (Button)findViewById(R.id.thu);
                String curdayStr5 = String.valueOf(curDay5);
                if (curDay5 < 10) {
                    curdayStr5 = "0" + curdayStr5;
                }
                thuBtn.setText((mCalendar.get(Calendar.MONTH)+1) + "/" + curdayStr5);
                loadScheduleListData(mScheduleListAdapter5);
                /////////////////////
                thuBtn.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                        int dayday = curDay5;
                        int monmon = mCalendar.get(Calendar.MONTH);
                        int yearyear = mCalendar.get(Calendar.YEAR);

                        intent.putExtra(BasicInfo.KEY_DAY_FROM_WEEK, String.valueOf(dayday));
                        intent.putExtra(BasicInfo.KEY_MONTH_FROM_WEEK, String.valueOf(monmon));
                        intent.putExtra(BasicInfo.KEY_YEAR_FROM_WEEK, String.valueOf(yearyear));

                        //intent.putExtra("111", "heyeheheh");
                        Log.d("WeeklyActivity", "Selected : " + dayday + " " + monmon + " " + yearyear);

                        setResult(RESULT_OK, intent);
                        finish();
                    }
                });
                ////////////////////

                mCalendar.add(Calendar.DATE,1);
                final int curDay6 = mCalendar.get(Calendar.DAY_OF_MONTH);
                Button friBtn = (Button)findViewById(R.id.fri);
                String curdayStr6 = String.valueOf(curDay6);
                if (curDay6 < 10) {
                    curdayStr6 = "0" + curdayStr6;
                }
                friBtn.setText((mCalendar.get(Calendar.MONTH)+1) + "/" + curdayStr6);
                loadScheduleListData(mScheduleListAdapter6);
                /////////////////////
                friBtn.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                        int dayday = curDay6;
                        int monmon = mCalendar.get(Calendar.MONTH);
                        int yearyear = mCalendar.get(Calendar.YEAR);

                        intent.putExtra(BasicInfo.KEY_DAY_FROM_WEEK, String.valueOf(dayday));
                        intent.putExtra(BasicInfo.KEY_MONTH_FROM_WEEK, String.valueOf(monmon));
                        intent.putExtra(BasicInfo.KEY_YEAR_FROM_WEEK, String.valueOf(yearyear));

                        //intent.putExtra("111", "heyeheheh");
                        Log.d("WeeklyActivity", "Selected : " + dayday + " " + monmon + " " + yearyear);

                        setResult(RESULT_OK, intent);
                        finish();
                    }
                });
                ////////////////////

                mCalendar.add(Calendar.DATE,1);
                final int curDay7 = mCalendar.get(Calendar.DAY_OF_MONTH);
                Button satBtn = (Button)findViewById(R.id.sat);
                String curdayStr7 = String.valueOf(curDay7);
                if (curDay7 < 10) {
                    curdayStr7 = "0" + curdayStr7;
                }
                satBtn.setText((mCalendar.get(Calendar.MONTH)+1) + "/" + curdayStr7);
                loadScheduleListData(mScheduleListAdapter7);
                /////////////////////
                satBtn.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                        int dayday = curDay7;
                        int monmon = mCalendar.get(Calendar.MONTH);
                        int yearyear = mCalendar.get(Calendar.YEAR);

                        intent.putExtra(BasicInfo.KEY_DAY_FROM_WEEK, String.valueOf(dayday));
                        intent.putExtra(BasicInfo.KEY_MONTH_FROM_WEEK, String.valueOf(monmon));
                        intent.putExtra(BasicInfo.KEY_YEAR_FROM_WEEK, String.valueOf(yearyear));

                        //intent.putExtra("111", "heyeheheh");
                        Log.d("WeeklyActivity", "Selected : " + dayday + " " + monmon + " " + yearyear);

                        setResult(RESULT_OK, intent);
                        finish();
                    }
                });
                ////////////////////


            }
        });

        // 다음 주로 넘어가는 이벤트 처리
        Button weekNext = (Button) findViewById(R.id.weekNext);
        weekNext.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mCalendar.add(Calendar.DATE,1);
                final int curDay = mCalendar.get(Calendar.DAY_OF_MONTH);
                Button sunBtn = (Button)findViewById(R.id.sun);
                String curdayStr = String.valueOf(curDay);
                if (curDay < 10) {
                    curdayStr = "0" + curdayStr;
                }
                sunBtn.setText((mCalendar.get(Calendar.MONTH)+1) + "/" + curdayStr);
                loadScheduleListData(mScheduleListAdapter1);
                /////////////////////
                sunBtn.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                        int dayday = curDay;
                        int monmon = mCalendar.get(Calendar.MONTH);
                        int yearyear = mCalendar.get(Calendar.YEAR);

                        intent.putExtra(BasicInfo.KEY_DAY_FROM_WEEK, String.valueOf(dayday));
                        intent.putExtra(BasicInfo.KEY_MONTH_FROM_WEEK, String.valueOf(monmon));
                        intent.putExtra(BasicInfo.KEY_YEAR_FROM_WEEK, String.valueOf(yearyear));

                        //intent.putExtra("111", "heyeheheh");
                        Log.d("WeeklyActivity", "Selected : " + dayday + " " + monmon + " " + yearyear);

                        setResult(RESULT_OK, intent);
                        finish();
                    }
                });
                ////////////////////

                mCalendar.add(Calendar.DATE,1);
                final int curDay2 = mCalendar.get(Calendar.DAY_OF_MONTH);
                Button monBtn = (Button)findViewById(R.id.mon);
                String curdayStr2 = String.valueOf(curDay2);
                if (curDay2 < 10) {
                    curdayStr2 = "0" + curdayStr2;
                }
                monBtn.setText((mCalendar.get(Calendar.MONTH)+1) + "/" + curdayStr2);
                loadScheduleListData(mScheduleListAdapter2);
                /////////////////////
                monBtn.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                        int dayday = curDay2;
                        int monmon = mCalendar.get(Calendar.MONTH);
                        int yearyear = mCalendar.get(Calendar.YEAR);

                        intent.putExtra(BasicInfo.KEY_DAY_FROM_WEEK, String.valueOf(dayday));
                        intent.putExtra(BasicInfo.KEY_MONTH_FROM_WEEK, String.valueOf(monmon));
                        intent.putExtra(BasicInfo.KEY_YEAR_FROM_WEEK, String.valueOf(yearyear));

                        //intent.putExtra("111", "heyeheheh");
                        Log.d("WeeklyActivity", "Selected : " + dayday + " " + monmon + " " + yearyear);

                        setResult(RESULT_OK, intent);
                        finish();
                    }
                });
                ////////////////////

                mCalendar.add(Calendar.DATE,1);
                final int curDay3 = mCalendar.get(Calendar.DAY_OF_MONTH);
                Button tueBtn = (Button)findViewById(R.id.tue);
                String curdayStr3 = String.valueOf(curDay3);
                if (curDay3 < 10) {
                    curdayStr3 = "0" + curdayStr3;
                }
                tueBtn.setText((mCalendar.get(Calendar.MONTH)+1) + "/" + curdayStr3);
                loadScheduleListData(mScheduleListAdapter3);
                /////////////////////
                tueBtn.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                        int dayday = curDay3;
                        int monmon = mCalendar.get(Calendar.MONTH);
                        int yearyear = mCalendar.get(Calendar.YEAR);

                        intent.putExtra(BasicInfo.KEY_DAY_FROM_WEEK, String.valueOf(dayday));
                        intent.putExtra(BasicInfo.KEY_MONTH_FROM_WEEK, String.valueOf(monmon));
                        intent.putExtra(BasicInfo.KEY_YEAR_FROM_WEEK, String.valueOf(yearyear));

                        //intent.putExtra("111", "heyeheheh");
                        Log.d("WeeklyActivity", "Selected : " + dayday + " " + monmon + " " + yearyear);

                        setResult(RESULT_OK, intent);
                        finish();
                    }
                });
                ////////////////////

                mCalendar.add(Calendar.DATE,1);
                final int curDay4 = mCalendar.get(Calendar.DAY_OF_MONTH);
                Button wedBtn = (Button)findViewById(R.id.wed);
                String curdayStr4 = String.valueOf(curDay4);
                if (curDay4 < 10) {
                    curdayStr4 = "0" + curdayStr4;
                }
                wedBtn.setText((mCalendar.get(Calendar.MONTH)+1) + "/" + curdayStr4);
                loadScheduleListData(mScheduleListAdapter4);
                /////////////////////
                wedBtn.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                        int dayday = curDay4;
                        int monmon = mCalendar.get(Calendar.MONTH);
                        int yearyear = mCalendar.get(Calendar.YEAR);

                        intent.putExtra(BasicInfo.KEY_DAY_FROM_WEEK, String.valueOf(dayday));
                        intent.putExtra(BasicInfo.KEY_MONTH_FROM_WEEK, String.valueOf(monmon));
                        intent.putExtra(BasicInfo.KEY_YEAR_FROM_WEEK, String.valueOf(yearyear));

                        //intent.putExtra("111", "heyeheheh");
                        Log.d("WeeklyActivity", "Selected : " + dayday + " " + monmon + " " + yearyear);

                        setResult(RESULT_OK, intent);
                        finish();
                    }
                });
                ////////////////////

                mCalendar.add(Calendar.DATE,1);
                final int curDay5 = mCalendar.get(Calendar.DAY_OF_MONTH);
                Button thuBtn = (Button)findViewById(R.id.thu);
                String curdayStr5 = String.valueOf(curDay5);
                if (curDay5 < 10) {
                    curdayStr5 = "0" + curdayStr5;
                }
                thuBtn.setText((mCalendar.get(Calendar.MONTH)+1) + "/" + curdayStr5);
                loadScheduleListData(mScheduleListAdapter5);
                /////////////////////
                thuBtn.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                        int dayday = curDay5;
                        int monmon = mCalendar.get(Calendar.MONTH);
                        int yearyear = mCalendar.get(Calendar.YEAR);

                        intent.putExtra(BasicInfo.KEY_DAY_FROM_WEEK, String.valueOf(dayday));
                        intent.putExtra(BasicInfo.KEY_MONTH_FROM_WEEK, String.valueOf(monmon));
                        intent.putExtra(BasicInfo.KEY_YEAR_FROM_WEEK, String.valueOf(yearyear));

                        //intent.putExtra("111", "heyeheheh");
                        Log.d("WeeklyActivity", "Selected : " + dayday + " " + monmon + " " + yearyear);

                        setResult(RESULT_OK, intent);
                        finish();
                    }
                });
                ////////////////////

                mCalendar.add(Calendar.DATE,1);
                final int curDay6 = mCalendar.get(Calendar.DAY_OF_MONTH);
                Button friBtn = (Button)findViewById(R.id.fri);
                String curdayStr6 = String.valueOf(curDay6);
                if (curDay6 < 10) {
                    curdayStr6 = "0" + curdayStr6;
                }
                friBtn.setText((mCalendar.get(Calendar.MONTH)+1) + "/" + curdayStr6);
                loadScheduleListData(mScheduleListAdapter6);
                /////////////////////
                friBtn.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                        int dayday = curDay6;
                        int monmon = mCalendar.get(Calendar.MONTH);
                        int yearyear = mCalendar.get(Calendar.YEAR);

                        intent.putExtra(BasicInfo.KEY_DAY_FROM_WEEK, String.valueOf(dayday));
                        intent.putExtra(BasicInfo.KEY_MONTH_FROM_WEEK, String.valueOf(monmon));
                        intent.putExtra(BasicInfo.KEY_YEAR_FROM_WEEK, String.valueOf(yearyear));

                        //intent.putExtra("111", "heyeheheh");
                        Log.d("WeeklyActivity", "Selected : " + dayday + " " + monmon + " " + yearyear);

                        setResult(RESULT_OK, intent);
                        finish();
                    }
                });
                ////////////////////

                mCalendar.add(Calendar.DATE,1);
                final int curDay7 = mCalendar.get(Calendar.DAY_OF_MONTH);
                Button satBtn = (Button)findViewById(R.id.sat);
                String curdayStr7 = String.valueOf(curDay7);
                if (curDay7 < 10) {
                    curdayStr7 = "0" + curdayStr7;
                }
                satBtn.setText((mCalendar.get(Calendar.MONTH)+1) + "/" + curdayStr7);
                loadScheduleListData(mScheduleListAdapter7);
                /////////////////////
                satBtn.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                        int dayday = curDay7;
                        int monmon = mCalendar.get(Calendar.MONTH);
                        int yearyear = mCalendar.get(Calendar.YEAR);

                        intent.putExtra(BasicInfo.KEY_DAY_FROM_WEEK, String.valueOf(dayday));
                        intent.putExtra(BasicInfo.KEY_MONTH_FROM_WEEK, String.valueOf(monmon));
                        intent.putExtra(BasicInfo.KEY_YEAR_FROM_WEEK, String.valueOf(yearyear));

                        //intent.putExtra("111", "heyeheheh");
                        Log.d("WeeklyActivity", "Selected : " + dayday + " " + monmon + " " + yearyear);

                        setResult(RESULT_OK, intent);
                        finish();
                    }
                });
                ////////////////////
            }
        });


    }

    protected void onStart() {

        // 데이터베이스 열기
        openDatabase();

        super.onStart();
    }

    public void openDatabase() {
        /*
        // open database
        if (mDatabase != null && mDatabase.open()) {
            mDatabase.close();
            mDatabase = null;
        }
        */

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

            final int curDay = mCalendar.get(Calendar.DAY_OF_MONTH);
            Button sunBtn = (Button)findViewById(R.id.sun);
            String curdayStr = String.valueOf(curDay);
            if (curDay < 10) {
                curdayStr = "0" + curdayStr;
            }
            sunBtn.setText((mCalendar.get(Calendar.MONTH)+1) + "/" + curdayStr);
            loadScheduleListData(mScheduleListAdapter1);
            /////////////////////
            sunBtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                    int dayday = curDay;
                    int monmon = mCalendar.get(Calendar.MONTH);
                    int yearyear = mCalendar.get(Calendar.YEAR);

                    intent.putExtra(BasicInfo.KEY_DAY_FROM_WEEK, String.valueOf(dayday));
                    intent.putExtra(BasicInfo.KEY_MONTH_FROM_WEEK, String.valueOf(monmon));
                    intent.putExtra(BasicInfo.KEY_YEAR_FROM_WEEK, String.valueOf(yearyear));

                    //intent.putExtra("111", "heyeheheh");
                    Log.d("WeeklyActivity", "Selected : " + dayday + " " + monmon + " " + yearyear);

                    setResult(RESULT_OK, intent);
                    finish();
                }
            });
            ////////////////////

            mCalendar.add(Calendar.DATE,1);
            final int curDay2 = mCalendar.get(Calendar.DAY_OF_MONTH);
            Button monBtn = (Button)findViewById(R.id.mon);
            String curdayStr2 = String.valueOf(curDay2);
            if (curDay2 < 10) {
                curdayStr2 = "0" + curdayStr2;
            }
            monBtn.setText((mCalendar.get(Calendar.MONTH)+1) + "/" + curdayStr2);
            loadScheduleListData(mScheduleListAdapter2);
            /////////////////////
            monBtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                    int dayday = curDay2;
                    int monmon = mCalendar.get(Calendar.MONTH);
                    int yearyear = mCalendar.get(Calendar.YEAR);

                    intent.putExtra(BasicInfo.KEY_DAY_FROM_WEEK, String.valueOf(dayday));
                    intent.putExtra(BasicInfo.KEY_MONTH_FROM_WEEK, String.valueOf(monmon));
                    intent.putExtra(BasicInfo.KEY_YEAR_FROM_WEEK, String.valueOf(yearyear));

                    //intent.putExtra("111", "heyeheheh");
                    Log.d("WeeklyActivity", "Selected : " + dayday + " " + monmon + " " + yearyear);

                    setResult(RESULT_OK, intent);
                    finish();
                }
            });
            ////////////////////

            mCalendar.add(Calendar.DATE,1);
            final int curDay3 = mCalendar.get(Calendar.DAY_OF_MONTH);
            Button tueBtn = (Button)findViewById(R.id.tue);
            String curdayStr3 = String.valueOf(curDay3);
            if (curDay3 < 10) {
                curdayStr3 = "0" + curdayStr3;
            }
            tueBtn.setText((mCalendar.get(Calendar.MONTH)+1) + "/" + curdayStr3);
            loadScheduleListData(mScheduleListAdapter3);
            /////////////////////
            tueBtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                    int dayday = curDay3;
                    int monmon = mCalendar.get(Calendar.MONTH);
                    int yearyear = mCalendar.get(Calendar.YEAR);

                    intent.putExtra(BasicInfo.KEY_DAY_FROM_WEEK, String.valueOf(dayday));
                    intent.putExtra(BasicInfo.KEY_MONTH_FROM_WEEK, String.valueOf(monmon));
                    intent.putExtra(BasicInfo.KEY_YEAR_FROM_WEEK, String.valueOf(yearyear));

                    //intent.putExtra("111", "heyeheheh");
                    Log.d("WeeklyActivity", "Selected : " + dayday + " " + monmon + " " + yearyear);

                    setResult(RESULT_OK, intent);
                    finish();
                }
            });
            ////////////////////

            mCalendar.add(Calendar.DATE,1);
            final int curDay4 = mCalendar.get(Calendar.DAY_OF_MONTH);
            Button wedBtn = (Button)findViewById(R.id.wed);
            String curdayStr4 = String.valueOf(curDay4);
            if (curDay4 < 10) {
                curdayStr4 = "0" + curdayStr4;
            }
            wedBtn.setText((mCalendar.get(Calendar.MONTH)+1) + "/" + curdayStr4);
            loadScheduleListData(mScheduleListAdapter4);
            /////////////////////
            wedBtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                    int dayday = curDay4;
                    int monmon = mCalendar.get(Calendar.MONTH);
                    int yearyear = mCalendar.get(Calendar.YEAR);

                    intent.putExtra(BasicInfo.KEY_DAY_FROM_WEEK, String.valueOf(dayday));
                    intent.putExtra(BasicInfo.KEY_MONTH_FROM_WEEK, String.valueOf(monmon));
                    intent.putExtra(BasicInfo.KEY_YEAR_FROM_WEEK, String.valueOf(yearyear));

                    //intent.putExtra("111", "heyeheheh");
                    Log.d("WeeklyActivity", "Selected : " + dayday + " " + monmon + " " + yearyear);

                    setResult(RESULT_OK, intent);
                    finish();
                }
            });
            ////////////////////

            mCalendar.add(Calendar.DATE,1);
            final int curDay5 = mCalendar.get(Calendar.DAY_OF_MONTH);
            Button thuBtn = (Button)findViewById(R.id.thu);
            String curdayStr5 = String.valueOf(curDay5);
            if (curDay5 < 10) {
                curdayStr5 = "0" + curdayStr5;
            }
            thuBtn.setText((mCalendar.get(Calendar.MONTH)+1) + "/" + curdayStr5);
            loadScheduleListData(mScheduleListAdapter5);
            /////////////////////
            thuBtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                    int dayday = curDay5;
                    int monmon = mCalendar.get(Calendar.MONTH);
                    int yearyear = mCalendar.get(Calendar.YEAR);

                    intent.putExtra(BasicInfo.KEY_DAY_FROM_WEEK, String.valueOf(dayday));
                    intent.putExtra(BasicInfo.KEY_MONTH_FROM_WEEK, String.valueOf(monmon));
                    intent.putExtra(BasicInfo.KEY_YEAR_FROM_WEEK, String.valueOf(yearyear));

                    //intent.putExtra("111", "heyeheheh");
                    Log.d("WeeklyActivity", "Selected : " + dayday + " " + monmon + " " + yearyear);

                    setResult(RESULT_OK, intent);
                    finish();
                }
            });
            ////////////////////

            mCalendar.add(Calendar.DATE,1);
            final int curDay6 = mCalendar.get(Calendar.DAY_OF_MONTH);
            Button friBtn = (Button)findViewById(R.id.fri);
            String curdayStr6 = String.valueOf(curDay6);
            if (curDay6 < 10) {
                curdayStr6 = "0" + curdayStr6;
            }
            friBtn.setText((mCalendar.get(Calendar.MONTH)+1) + "/" + curdayStr6);
            loadScheduleListData(mScheduleListAdapter6);
            /////////////////////
            friBtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                    int dayday = curDay6;
                    int monmon = mCalendar.get(Calendar.MONTH);
                    int yearyear = mCalendar.get(Calendar.YEAR);

                    intent.putExtra(BasicInfo.KEY_DAY_FROM_WEEK, String.valueOf(dayday));
                    intent.putExtra(BasicInfo.KEY_MONTH_FROM_WEEK, String.valueOf(monmon));
                    intent.putExtra(BasicInfo.KEY_YEAR_FROM_WEEK, String.valueOf(yearyear));

                    //intent.putExtra("111", "heyeheheh");
                    Log.d("WeeklyActivity", "Selected : " + dayday + " " + monmon + " " + yearyear);

                    setResult(RESULT_OK, intent);
                    finish();
                }
            });
            ////////////////////

            mCalendar.add(Calendar.DATE,1);
            final int curDay7 = mCalendar.get(Calendar.DAY_OF_MONTH);
            Button satBtn = (Button)findViewById(R.id.sat);
            String curdayStr7 = String.valueOf(curDay7);
            if (curDay7 < 10) {
                curdayStr7 = "0" + curdayStr7;
            }
            satBtn.setText((mCalendar.get(Calendar.MONTH)+1) + "/" + curdayStr7);
            loadScheduleListData(mScheduleListAdapter7);
            /////////////////////
            satBtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                    int dayday = curDay7;
                    int monmon = mCalendar.get(Calendar.MONTH);
                    int yearyear = mCalendar.get(Calendar.YEAR);

                    intent.putExtra(BasicInfo.KEY_DAY_FROM_WEEK, String.valueOf(dayday));
                    intent.putExtra(BasicInfo.KEY_MONTH_FROM_WEEK, String.valueOf(monmon));
                    intent.putExtra(BasicInfo.KEY_YEAR_FROM_WEEK, String.valueOf(yearyear));

                    //intent.putExtra("111", "heyeheheh");
                    Log.d("WeeklyActivity", "Selected : " + dayday + " " + monmon + " " + yearyear);

                    setResult(RESULT_OK, intent);
                    finish();
                }
            });
            ////////////////////
        }

        else if (dayOfWeek==2){

            mCalendar.add(Calendar.DATE,-1);
            final int curDay = mCalendar.get(Calendar.DAY_OF_MONTH);
            Button sunBtn = (Button)findViewById(R.id.sun);
            String curdayStr = String.valueOf(curDay);
            if (curDay < 10) {
                curdayStr = "0" + curdayStr;
            }
            sunBtn.setText((mCalendar.get(Calendar.MONTH)+1) + "/" + curdayStr);
            loadScheduleListData(mScheduleListAdapter1);
            /////////////////////
            sunBtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                    int dayday = curDay;
                    int monmon = mCalendar.get(Calendar.MONTH);
                    int yearyear = mCalendar.get(Calendar.YEAR);

                    intent.putExtra(BasicInfo.KEY_DAY_FROM_WEEK, String.valueOf(dayday));
                    intent.putExtra(BasicInfo.KEY_MONTH_FROM_WEEK, String.valueOf(monmon));
                    intent.putExtra(BasicInfo.KEY_YEAR_FROM_WEEK, String.valueOf(yearyear));

                    //intent.putExtra("111", "heyeheheh");
                    Log.d("WeeklyActivity", "Selected : " + dayday + " " + monmon + " " + yearyear);

                    setResult(RESULT_OK, intent);
                    finish();
                }
            });
            ////////////////////

            mCalendar.add(Calendar.DATE,1);
            final int curDay2 = mCalendar.get(Calendar.DAY_OF_MONTH);
            Button monBtn = (Button)findViewById(R.id.mon);
            String curdayStr2 = String.valueOf(curDay2);
            if (curDay2 < 10) {
                curdayStr2 = "0" + curdayStr2;
            }
            monBtn.setText((mCalendar.get(Calendar.MONTH)+1) + "/" + curdayStr2);
            loadScheduleListData(mScheduleListAdapter2);
            /////////////////////
            monBtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                    int dayday = curDay2;
                    int monmon = mCalendar.get(Calendar.MONTH);
                    int yearyear = mCalendar.get(Calendar.YEAR);

                    intent.putExtra(BasicInfo.KEY_DAY_FROM_WEEK, String.valueOf(dayday));
                    intent.putExtra(BasicInfo.KEY_MONTH_FROM_WEEK, String.valueOf(monmon));
                    intent.putExtra(BasicInfo.KEY_YEAR_FROM_WEEK, String.valueOf(yearyear));

                    //intent.putExtra("111", "heyeheheh");
                    Log.d("WeeklyActivity", "Selected : " + dayday + " " + monmon + " " + yearyear);

                    setResult(RESULT_OK, intent);
                    finish();
                }
            });
            ////////////////////

            mCalendar.add(Calendar.DATE,1);
            final int curDay3 = mCalendar.get(Calendar.DAY_OF_MONTH);
            Button tueBtn = (Button)findViewById(R.id.tue);
            String curdayStr3 = String.valueOf(curDay3);
            if (curDay3 < 10) {
                curdayStr3 = "0" + curdayStr3;
            }
            tueBtn.setText((mCalendar.get(Calendar.MONTH)+1) + "/" + curdayStr3);
            loadScheduleListData(mScheduleListAdapter3);
            /////////////////////
            tueBtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                    int dayday = curDay3;
                    int monmon = mCalendar.get(Calendar.MONTH);
                    int yearyear = mCalendar.get(Calendar.YEAR);

                    intent.putExtra(BasicInfo.KEY_DAY_FROM_WEEK, String.valueOf(dayday));
                    intent.putExtra(BasicInfo.KEY_MONTH_FROM_WEEK, String.valueOf(monmon));
                    intent.putExtra(BasicInfo.KEY_YEAR_FROM_WEEK, String.valueOf(yearyear));

                    //intent.putExtra("111", "heyeheheh");
                    Log.d("WeeklyActivity", "Selected : " + dayday + " " + monmon + " " + yearyear);

                    setResult(RESULT_OK, intent);
                    finish();
                }
            });
            ////////////////////

            mCalendar.add(Calendar.DATE,1);
            final int curDay4 = mCalendar.get(Calendar.DAY_OF_MONTH);
            Button wedBtn = (Button)findViewById(R.id.wed);
            String curdayStr4 = String.valueOf(curDay4);
            if (curDay4 < 10) {
                curdayStr4 = "0" + curdayStr4;
            }
            wedBtn.setText((mCalendar.get(Calendar.MONTH)+1) + "/" + curdayStr4);
            loadScheduleListData(mScheduleListAdapter4);
            /////////////////////
            wedBtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                    int dayday = curDay4;
                    int monmon = mCalendar.get(Calendar.MONTH);
                    int yearyear = mCalendar.get(Calendar.YEAR);

                    intent.putExtra(BasicInfo.KEY_DAY_FROM_WEEK, String.valueOf(dayday));
                    intent.putExtra(BasicInfo.KEY_MONTH_FROM_WEEK, String.valueOf(monmon));
                    intent.putExtra(BasicInfo.KEY_YEAR_FROM_WEEK, String.valueOf(yearyear));

                    //intent.putExtra("111", "heyeheheh");
                    Log.d("WeeklyActivity", "Selected : " + dayday + " " + monmon + " " + yearyear);

                    setResult(RESULT_OK, intent);
                    finish();
                }
            });
            ////////////////////

            mCalendar.add(Calendar.DATE,1);
            final int curDay5 = mCalendar.get(Calendar.DAY_OF_MONTH);
            Button thuBtn = (Button)findViewById(R.id.thu);
            String curdayStr5 = String.valueOf(curDay5);
            if (curDay5 < 10) {
                curdayStr5 = "0" + curdayStr5;
            }
            thuBtn.setText((mCalendar.get(Calendar.MONTH)+1) + "/" + curdayStr5);
            loadScheduleListData(mScheduleListAdapter5);
            /////////////////////
            thuBtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                    int dayday = curDay5;
                    int monmon = mCalendar.get(Calendar.MONTH);
                    int yearyear = mCalendar.get(Calendar.YEAR);

                    intent.putExtra(BasicInfo.KEY_DAY_FROM_WEEK, String.valueOf(dayday));
                    intent.putExtra(BasicInfo.KEY_MONTH_FROM_WEEK, String.valueOf(monmon));
                    intent.putExtra(BasicInfo.KEY_YEAR_FROM_WEEK, String.valueOf(yearyear));

                    //intent.putExtra("111", "heyeheheh");
                    Log.d("WeeklyActivity", "Selected : " + dayday + " " + monmon + " " + yearyear);

                    setResult(RESULT_OK, intent);
                    finish();
                }
            });
            ////////////////////

            mCalendar.add(Calendar.DATE,1);
            final int curDay6 = mCalendar.get(Calendar.DAY_OF_MONTH);
            Button friBtn = (Button)findViewById(R.id.fri);
            String curdayStr6 = String.valueOf(curDay6);
            if (curDay6 < 10) {
                curdayStr6 = "0" + curdayStr6;
            }
            friBtn.setText((mCalendar.get(Calendar.MONTH)+1) + "/" + curdayStr6);
            loadScheduleListData(mScheduleListAdapter6);
            /////////////////////
            friBtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                    int dayday = curDay6;
                    int monmon = mCalendar.get(Calendar.MONTH);
                    int yearyear = mCalendar.get(Calendar.YEAR);

                    intent.putExtra(BasicInfo.KEY_DAY_FROM_WEEK, String.valueOf(dayday));
                    intent.putExtra(BasicInfo.KEY_MONTH_FROM_WEEK, String.valueOf(monmon));
                    intent.putExtra(BasicInfo.KEY_YEAR_FROM_WEEK, String.valueOf(yearyear));

                    //intent.putExtra("111", "heyeheheh");
                    Log.d("WeeklyActivity", "Selected : " + dayday + " " + monmon + " " + yearyear);

                    setResult(RESULT_OK, intent);
                    finish();
                }
            });
            ////////////////////

            mCalendar.add(Calendar.DATE,1);
            final int curDay7 = mCalendar.get(Calendar.DAY_OF_MONTH);
            Button satBtn = (Button)findViewById(R.id.sat);
            String curdayStr7 = String.valueOf(curDay7);
            if (curDay7 < 10) {
                curdayStr7 = "0" + curdayStr7;
            }
            satBtn.setText((mCalendar.get(Calendar.MONTH)+1) + "/" + curdayStr7);
            loadScheduleListData(mScheduleListAdapter7);
            /////////////////////
            satBtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                    int dayday = curDay7;
                    int monmon = mCalendar.get(Calendar.MONTH);
                    int yearyear = mCalendar.get(Calendar.YEAR);

                    intent.putExtra(BasicInfo.KEY_DAY_FROM_WEEK, String.valueOf(dayday));
                    intent.putExtra(BasicInfo.KEY_MONTH_FROM_WEEK, String.valueOf(monmon));
                    intent.putExtra(BasicInfo.KEY_YEAR_FROM_WEEK, String.valueOf(yearyear));

                    //intent.putExtra("111", "heyeheheh");
                    Log.d("WeeklyActivity", "Selected : " + dayday + " " + monmon + " " + yearyear);

                    setResult(RESULT_OK, intent);
                    finish();
                }
            });
            ////////////////////

        }
        else if (dayOfWeek==3){
            mCalendar.add(Calendar.DATE,-2);
            final int curDay = mCalendar.get(Calendar.DAY_OF_MONTH);
            Button sunBtn = (Button)findViewById(R.id.sun);
            String curdayStr = String.valueOf(curDay);
            if (curDay < 10) {
                curdayStr = "0" + curdayStr;
            }
            sunBtn.setText((mCalendar.get(Calendar.MONTH)+1) + "/" + curdayStr);
            loadScheduleListData(mScheduleListAdapter1);
            /////////////////////
            sunBtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                    int dayday = curDay;
                    int monmon = mCalendar.get(Calendar.MONTH);
                    int yearyear = mCalendar.get(Calendar.YEAR);

                    intent.putExtra(BasicInfo.KEY_DAY_FROM_WEEK, String.valueOf(dayday));
                    intent.putExtra(BasicInfo.KEY_MONTH_FROM_WEEK, String.valueOf(monmon));
                    intent.putExtra(BasicInfo.KEY_YEAR_FROM_WEEK, String.valueOf(yearyear));

                    //intent.putExtra("111", "heyeheheh");
                    Log.d("WeeklyActivity", "Selected : " + dayday + " " + monmon + " " + yearyear);

                    setResult(RESULT_OK, intent);
                    finish();
                }
            });
            ////////////////////

            mCalendar.add(Calendar.DATE,1);
            final int curDay2 = mCalendar.get(Calendar.DAY_OF_MONTH);
            Button monBtn = (Button)findViewById(R.id.mon);
            String curdayStr2 = String.valueOf(curDay2);
            if (curDay2 < 10) {
                curdayStr2 = "0" + curdayStr2;
            }
            monBtn.setText((mCalendar.get(Calendar.MONTH)+1) + "/" + curdayStr2);
            loadScheduleListData(mScheduleListAdapter2);
            /////////////////////
            monBtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                    int dayday = curDay2;
                    int monmon = mCalendar.get(Calendar.MONTH);
                    int yearyear = mCalendar.get(Calendar.YEAR);

                    intent.putExtra(BasicInfo.KEY_DAY_FROM_WEEK, String.valueOf(dayday));
                    intent.putExtra(BasicInfo.KEY_MONTH_FROM_WEEK, String.valueOf(monmon));
                    intent.putExtra(BasicInfo.KEY_YEAR_FROM_WEEK, String.valueOf(yearyear));

                    //intent.putExtra("111", "heyeheheh");
                    Log.d("WeeklyActivity", "Selected : " + dayday + " " + monmon + " " + yearyear);

                    setResult(RESULT_OK, intent);
                    finish();
                }
            });
            ////////////////////

            mCalendar.add(Calendar.DATE,1);
            final int curDay3 = mCalendar.get(Calendar.DAY_OF_MONTH);
            Button tueBtn = (Button)findViewById(R.id.tue);
            String curdayStr3 = String.valueOf(curDay3);
            if (curDay3 < 10) {
                curdayStr3 = "0" + curdayStr3;
            }
            tueBtn.setText((mCalendar.get(Calendar.MONTH)+1) + "/" + curdayStr3);
            loadScheduleListData(mScheduleListAdapter3);
            /////////////////////
            tueBtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                    int dayday = curDay3;
                    int monmon = mCalendar.get(Calendar.MONTH);
                    int yearyear = mCalendar.get(Calendar.YEAR);

                    intent.putExtra(BasicInfo.KEY_DAY_FROM_WEEK, String.valueOf(dayday));
                    intent.putExtra(BasicInfo.KEY_MONTH_FROM_WEEK, String.valueOf(monmon));
                    intent.putExtra(BasicInfo.KEY_YEAR_FROM_WEEK, String.valueOf(yearyear));

                    //intent.putExtra("111", "heyeheheh");
                    Log.d("WeeklyActivity", "Selected : " + dayday + " " + monmon + " " + yearyear);

                    setResult(RESULT_OK, intent);
                    finish();
                }
            });
            ////////////////////

            mCalendar.add(Calendar.DATE,1);
            final int curDay4 = mCalendar.get(Calendar.DAY_OF_MONTH);
            Button wedBtn = (Button)findViewById(R.id.wed);
            String curdayStr4 = String.valueOf(curDay4);
            if (curDay4 < 10) {
                curdayStr4 = "0" + curdayStr4;
            }
            wedBtn.setText((mCalendar.get(Calendar.MONTH)+1) + "/" + curdayStr4);
            loadScheduleListData(mScheduleListAdapter4);
            /////////////////////
            wedBtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                    int dayday = curDay4;
                    int monmon = mCalendar.get(Calendar.MONTH);
                    int yearyear = mCalendar.get(Calendar.YEAR);

                    intent.putExtra(BasicInfo.KEY_DAY_FROM_WEEK, String.valueOf(dayday));
                    intent.putExtra(BasicInfo.KEY_MONTH_FROM_WEEK, String.valueOf(monmon));
                    intent.putExtra(BasicInfo.KEY_YEAR_FROM_WEEK, String.valueOf(yearyear));

                    //intent.putExtra("111", "heyeheheh");
                    Log.d("WeeklyActivity", "Selected : " + dayday + " " + monmon + " " + yearyear);

                    setResult(RESULT_OK, intent);
                    finish();
                }
            });
            ////////////////////

            mCalendar.add(Calendar.DATE,1);
            final int curDay5 = mCalendar.get(Calendar.DAY_OF_MONTH);
            Button thuBtn = (Button)findViewById(R.id.thu);
            String curdayStr5 = String.valueOf(curDay5);
            if (curDay5 < 10) {
                curdayStr5 = "0" + curdayStr5;
            }
            thuBtn.setText((mCalendar.get(Calendar.MONTH)+1) + "/" + curdayStr5);
            loadScheduleListData(mScheduleListAdapter5);
            /////////////////////
            thuBtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                    int dayday = curDay5;
                    int monmon = mCalendar.get(Calendar.MONTH);
                    int yearyear = mCalendar.get(Calendar.YEAR);

                    intent.putExtra(BasicInfo.KEY_DAY_FROM_WEEK, String.valueOf(dayday));
                    intent.putExtra(BasicInfo.KEY_MONTH_FROM_WEEK, String.valueOf(monmon));
                    intent.putExtra(BasicInfo.KEY_YEAR_FROM_WEEK, String.valueOf(yearyear));

                    //intent.putExtra("111", "heyeheheh");
                    Log.d("WeeklyActivity", "Selected : " + dayday + " " + monmon + " " + yearyear);

                    setResult(RESULT_OK, intent);
                    finish();
                }
            });
            ////////////////////

            mCalendar.add(Calendar.DATE,1);
            final int curDay6 = mCalendar.get(Calendar.DAY_OF_MONTH);
            Button friBtn = (Button)findViewById(R.id.fri);
            String curdayStr6 = String.valueOf(curDay6);
            if (curDay6 < 10) {
                curdayStr6 = "0" + curdayStr6;
            }
            friBtn.setText((mCalendar.get(Calendar.MONTH)+1) + "/" + curdayStr6);
            loadScheduleListData(mScheduleListAdapter6);
            /////////////////////
            friBtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                    int dayday = curDay6;
                    int monmon = mCalendar.get(Calendar.MONTH);
                    int yearyear = mCalendar.get(Calendar.YEAR);

                    intent.putExtra(BasicInfo.KEY_DAY_FROM_WEEK, String.valueOf(dayday));
                    intent.putExtra(BasicInfo.KEY_MONTH_FROM_WEEK, String.valueOf(monmon));
                    intent.putExtra(BasicInfo.KEY_YEAR_FROM_WEEK, String.valueOf(yearyear));

                    //intent.putExtra("111", "heyeheheh");
                    Log.d("WeeklyActivity", "Selected : " + dayday + " " + monmon + " " + yearyear);

                    setResult(RESULT_OK, intent);
                    finish();
                }
            });
            ////////////////////

            mCalendar.add(Calendar.DATE,1);
            final int curDay7 = mCalendar.get(Calendar.DAY_OF_MONTH);
            Button satBtn = (Button)findViewById(R.id.sat);
            String curdayStr7 = String.valueOf(curDay7);
            if (curDay7 < 10) {
                curdayStr7 = "0" + curdayStr7;
            }
            satBtn.setText((mCalendar.get(Calendar.MONTH)+1) + "/" + curdayStr7);
            loadScheduleListData(mScheduleListAdapter7);
            /////////////////////
            satBtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                    int dayday = curDay7;
                    int monmon = mCalendar.get(Calendar.MONTH);
                    int yearyear = mCalendar.get(Calendar.YEAR);

                    intent.putExtra(BasicInfo.KEY_DAY_FROM_WEEK, String.valueOf(dayday));
                    intent.putExtra(BasicInfo.KEY_MONTH_FROM_WEEK, String.valueOf(monmon));
                    intent.putExtra(BasicInfo.KEY_YEAR_FROM_WEEK, String.valueOf(yearyear));

                    //intent.putExtra("111", "heyeheheh");
                    Log.d("WeeklyActivity", "Selected : " + dayday + " " + monmon + " " + yearyear);

                    setResult(RESULT_OK, intent);
                    finish();
                }
            });
            ////////////////////
        }
        else if (dayOfWeek==4){
            mCalendar.add(Calendar.DATE,-3);
            final int curDay = mCalendar.get(Calendar.DAY_OF_MONTH);
            Button sunBtn = (Button)findViewById(R.id.sun);
            String curdayStr = String.valueOf(curDay);
            if (curDay < 10) {
                curdayStr = "0" + curdayStr;
            }
            sunBtn.setText((mCalendar.get(Calendar.MONTH)+1) + "/" + curdayStr);
            loadScheduleListData(mScheduleListAdapter1);
            /////////////////////
            sunBtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                    int dayday = curDay;
                    int monmon = mCalendar.get(Calendar.MONTH);
                    int yearyear = mCalendar.get(Calendar.YEAR);

                    intent.putExtra(BasicInfo.KEY_DAY_FROM_WEEK, String.valueOf(dayday));
                    intent.putExtra(BasicInfo.KEY_MONTH_FROM_WEEK, String.valueOf(monmon));
                    intent.putExtra(BasicInfo.KEY_YEAR_FROM_WEEK, String.valueOf(yearyear));

                    //intent.putExtra("111", "heyeheheh");
                    Log.d("WeeklyActivity", "Selected : " + dayday + " " + monmon + " " + yearyear);

                    setResult(RESULT_OK, intent);
                    finish();
                }
            });
            ////////////////////

            mCalendar.add(Calendar.DATE,1);
            final int curDay2 = mCalendar.get(Calendar.DAY_OF_MONTH);
            Button monBtn = (Button)findViewById(R.id.mon);
            String curdayStr2 = String.valueOf(curDay2);
            if (curDay2 < 10) {
                curdayStr2 = "0" + curdayStr2;
            }
            monBtn.setText((mCalendar.get(Calendar.MONTH)+1) + "/" + curdayStr2);
            loadScheduleListData(mScheduleListAdapter2);
            /////////////////////
            monBtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                    int dayday = curDay2;
                    int monmon = mCalendar.get(Calendar.MONTH);
                    int yearyear = mCalendar.get(Calendar.YEAR);

                    intent.putExtra(BasicInfo.KEY_DAY_FROM_WEEK, String.valueOf(dayday));
                    intent.putExtra(BasicInfo.KEY_MONTH_FROM_WEEK, String.valueOf(monmon));
                    intent.putExtra(BasicInfo.KEY_YEAR_FROM_WEEK, String.valueOf(yearyear));

                    //intent.putExtra("111", "heyeheheh");
                    Log.d("WeeklyActivity", "Selected : " + dayday + " " + monmon + " " + yearyear);

                    setResult(RESULT_OK, intent);
                    finish();
                }
            });
            ////////////////////

            mCalendar.add(Calendar.DATE,1);
            final int curDay3 = mCalendar.get(Calendar.DAY_OF_MONTH);
            Button tueBtn = (Button)findViewById(R.id.tue);
            String curdayStr3 = String.valueOf(curDay3);
            if (curDay3 < 10) {
                curdayStr3 = "0" + curdayStr3;
            }
            tueBtn.setText((mCalendar.get(Calendar.MONTH)+1) + "/" + curdayStr3);
            loadScheduleListData(mScheduleListAdapter3);
            /////////////////////
            tueBtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                    int dayday = curDay3;
                    int monmon = mCalendar.get(Calendar.MONTH);
                    int yearyear = mCalendar.get(Calendar.YEAR);

                    intent.putExtra(BasicInfo.KEY_DAY_FROM_WEEK, String.valueOf(dayday));
                    intent.putExtra(BasicInfo.KEY_MONTH_FROM_WEEK, String.valueOf(monmon));
                    intent.putExtra(BasicInfo.KEY_YEAR_FROM_WEEK, String.valueOf(yearyear));

                    //intent.putExtra("111", "heyeheheh");
                    Log.d("WeeklyActivity", "Selected : " + dayday + " " + monmon + " " + yearyear);

                    setResult(RESULT_OK, intent);
                    finish();
                }
            });
            ////////////////////

            mCalendar.add(Calendar.DATE,1);
            final int curDay4 = mCalendar.get(Calendar.DAY_OF_MONTH);
            Button wedBtn = (Button)findViewById(R.id.wed);
            String curdayStr4 = String.valueOf(curDay4);
            if (curDay4 < 10) {
                curdayStr4 = "0" + curdayStr4;
            }
            wedBtn.setText((mCalendar.get(Calendar.MONTH)+1) + "/" + curdayStr4);
            loadScheduleListData(mScheduleListAdapter4);
            /////////////////////
            wedBtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                    int dayday = curDay4;
                    int monmon = mCalendar.get(Calendar.MONTH);
                    int yearyear = mCalendar.get(Calendar.YEAR);

                    intent.putExtra(BasicInfo.KEY_DAY_FROM_WEEK, String.valueOf(dayday));
                    intent.putExtra(BasicInfo.KEY_MONTH_FROM_WEEK, String.valueOf(monmon));
                    intent.putExtra(BasicInfo.KEY_YEAR_FROM_WEEK, String.valueOf(yearyear));

                    //intent.putExtra("111", "heyeheheh");
                    Log.d("WeeklyActivity", "Selected : " + dayday + " " + monmon + " " + yearyear);

                    setResult(RESULT_OK, intent);
                    finish();
                }
            });
            ////////////////////

            mCalendar.add(Calendar.DATE,1);
            final int curDay5 = mCalendar.get(Calendar.DAY_OF_MONTH);
            Button thuBtn = (Button)findViewById(R.id.thu);
            String curdayStr5 = String.valueOf(curDay5);
            if (curDay5 < 10) {
                curdayStr5 = "0" + curdayStr5;
            }
            thuBtn.setText((mCalendar.get(Calendar.MONTH)+1) + "/" + curdayStr5);
            loadScheduleListData(mScheduleListAdapter5);
            /////////////////////
            thuBtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                    int dayday = curDay5;
                    int monmon = mCalendar.get(Calendar.MONTH);
                    int yearyear = mCalendar.get(Calendar.YEAR);

                    intent.putExtra(BasicInfo.KEY_DAY_FROM_WEEK, String.valueOf(dayday));
                    intent.putExtra(BasicInfo.KEY_MONTH_FROM_WEEK, String.valueOf(monmon));
                    intent.putExtra(BasicInfo.KEY_YEAR_FROM_WEEK, String.valueOf(yearyear));

                    //intent.putExtra("111", "heyeheheh");
                    Log.d("WeeklyActivity", "Selected : " + dayday + " " + monmon + " " + yearyear);

                    setResult(RESULT_OK, intent);
                    finish();
                }
            });
            ////////////////////

            mCalendar.add(Calendar.DATE,1);
            final int curDay6 = mCalendar.get(Calendar.DAY_OF_MONTH);
            Button friBtn = (Button)findViewById(R.id.fri);
            String curdayStr6 = String.valueOf(curDay6);
            if (curDay6 < 10) {
                curdayStr6 = "0" + curdayStr6;
            }
            friBtn.setText((mCalendar.get(Calendar.MONTH)+1) + "/" + curdayStr6);
            loadScheduleListData(mScheduleListAdapter6);
            /////////////////////
            friBtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                    int dayday = curDay6;
                    int monmon = mCalendar.get(Calendar.MONTH);
                    int yearyear = mCalendar.get(Calendar.YEAR);

                    intent.putExtra(BasicInfo.KEY_DAY_FROM_WEEK, String.valueOf(dayday));
                    intent.putExtra(BasicInfo.KEY_MONTH_FROM_WEEK, String.valueOf(monmon));
                    intent.putExtra(BasicInfo.KEY_YEAR_FROM_WEEK, String.valueOf(yearyear));

                    //intent.putExtra("111", "heyeheheh");
                    Log.d("WeeklyActivity", "Selected : " + dayday + " " + monmon + " " + yearyear);

                    setResult(RESULT_OK, intent);
                    finish();
                }
            });
            ////////////////////

            mCalendar.add(Calendar.DATE,1);
            final int curDay7 = mCalendar.get(Calendar.DAY_OF_MONTH);
            Button satBtn = (Button)findViewById(R.id.sat);
            String curdayStr7 = String.valueOf(curDay7);
            if (curDay7 < 10) {
                curdayStr7 = "0" + curdayStr7;
            }
            satBtn.setText((mCalendar.get(Calendar.MONTH)+1) + "/" + curdayStr7);
            loadScheduleListData(mScheduleListAdapter7);
            /////////////////////
            satBtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                    int dayday = curDay7;
                    int monmon = mCalendar.get(Calendar.MONTH);
                    int yearyear = mCalendar.get(Calendar.YEAR);

                    intent.putExtra(BasicInfo.KEY_DAY_FROM_WEEK, String.valueOf(dayday));
                    intent.putExtra(BasicInfo.KEY_MONTH_FROM_WEEK, String.valueOf(monmon));
                    intent.putExtra(BasicInfo.KEY_YEAR_FROM_WEEK, String.valueOf(yearyear));

                    //intent.putExtra("111", "heyeheheh");
                    Log.d("WeeklyActivity", "Selected : " + dayday + " " + monmon + " " + yearyear);

                    setResult(RESULT_OK, intent);
                    finish();
                }
            });
            ////////////////////

        }
        else if (dayOfWeek==5){
            mCalendar.add(Calendar.DATE,-4);
            final int curDay = mCalendar.get(Calendar.DAY_OF_MONTH);
            Button sunBtn = (Button)findViewById(R.id.sun);
            String curdayStr = String.valueOf(curDay);
            if (curDay < 10) {
                curdayStr = "0" + curdayStr;
            }
            sunBtn.setText((mCalendar.get(Calendar.MONTH)+1) + "/" + curdayStr);
            loadScheduleListData(mScheduleListAdapter1);
            /////////////////////
            sunBtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                    int dayday = curDay;
                    int monmon = mCalendar.get(Calendar.MONTH);
                    int yearyear = mCalendar.get(Calendar.YEAR);

                    intent.putExtra(BasicInfo.KEY_DAY_FROM_WEEK, String.valueOf(dayday));
                    intent.putExtra(BasicInfo.KEY_MONTH_FROM_WEEK, String.valueOf(monmon));
                    intent.putExtra(BasicInfo.KEY_YEAR_FROM_WEEK, String.valueOf(yearyear));

                    //intent.putExtra("111", "heyeheheh");
                    Log.d("WeeklyActivity", "Selected : " + dayday + " " + monmon + " " + yearyear);

                    setResult(RESULT_OK, intent);
                    finish();
                }
            });
            ////////////////////

            mCalendar.add(Calendar.DATE,1);
            final int curDay2 = mCalendar.get(Calendar.DAY_OF_MONTH);
            Button monBtn = (Button)findViewById(R.id.mon);
            String curdayStr2 = String.valueOf(curDay2);
            if (curDay2 < 10) {
                curdayStr2 = "0" + curdayStr2;
            }
            monBtn.setText((mCalendar.get(Calendar.MONTH)+1) + "/" + curdayStr2);
            loadScheduleListData(mScheduleListAdapter2);
            /////////////////////
            monBtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                    int dayday = curDay2;
                    int monmon = mCalendar.get(Calendar.MONTH);
                    int yearyear = mCalendar.get(Calendar.YEAR);

                    intent.putExtra(BasicInfo.KEY_DAY_FROM_WEEK, String.valueOf(dayday));
                    intent.putExtra(BasicInfo.KEY_MONTH_FROM_WEEK, String.valueOf(monmon));
                    intent.putExtra(BasicInfo.KEY_YEAR_FROM_WEEK, String.valueOf(yearyear));

                    //intent.putExtra("111", "heyeheheh");
                    Log.d("WeeklyActivity", "Selected : " + dayday + " " + monmon + " " + yearyear);

                    setResult(RESULT_OK, intent);
                    finish();
                }
            });
            ////////////////////

            mCalendar.add(Calendar.DATE,1);
            final int curDay3 = mCalendar.get(Calendar.DAY_OF_MONTH);
            Button tueBtn = (Button)findViewById(R.id.tue);
            String curdayStr3 = String.valueOf(curDay3);
            if (curDay3 < 10) {
                curdayStr3 = "0" + curdayStr3;
            }
            tueBtn.setText((mCalendar.get(Calendar.MONTH)+1) + "/" + curdayStr3);
            loadScheduleListData(mScheduleListAdapter3);
            /////////////////////
            tueBtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                    int dayday = curDay3;
                    int monmon = mCalendar.get(Calendar.MONTH);
                    int yearyear = mCalendar.get(Calendar.YEAR);

                    intent.putExtra(BasicInfo.KEY_DAY_FROM_WEEK, String.valueOf(dayday));
                    intent.putExtra(BasicInfo.KEY_MONTH_FROM_WEEK, String.valueOf(monmon));
                    intent.putExtra(BasicInfo.KEY_YEAR_FROM_WEEK, String.valueOf(yearyear));

                    //intent.putExtra("111", "heyeheheh");
                    Log.d("WeeklyActivity", "Selected : " + dayday + " " + monmon + " " + yearyear);

                    setResult(RESULT_OK, intent);
                    finish();
                }
            });
            ////////////////////

            mCalendar.add(Calendar.DATE,1);
            final int curDay4 = mCalendar.get(Calendar.DAY_OF_MONTH);
            Button wedBtn = (Button)findViewById(R.id.wed);
            String curdayStr4 = String.valueOf(curDay4);
            if (curDay4 < 10) {
                curdayStr4 = "0" + curdayStr4;
            }
            wedBtn.setText((mCalendar.get(Calendar.MONTH)+1) + "/" + curdayStr4);
            loadScheduleListData(mScheduleListAdapter4);
            /////////////////////
            wedBtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                    int dayday = curDay4;
                    int monmon = mCalendar.get(Calendar.MONTH);
                    int yearyear = mCalendar.get(Calendar.YEAR);

                    intent.putExtra(BasicInfo.KEY_DAY_FROM_WEEK, String.valueOf(dayday));
                    intent.putExtra(BasicInfo.KEY_MONTH_FROM_WEEK, String.valueOf(monmon));
                    intent.putExtra(BasicInfo.KEY_YEAR_FROM_WEEK, String.valueOf(yearyear));

                    //intent.putExtra("111", "heyeheheh");
                    Log.d("WeeklyActivity", "Selected : " + dayday + " " + monmon + " " + yearyear);

                    setResult(RESULT_OK, intent);
                    finish();
                }
            });
            ////////////////////

            mCalendar.add(Calendar.DATE,1);
            final int curDay5 = mCalendar.get(Calendar.DAY_OF_MONTH);
            Button thuBtn = (Button)findViewById(R.id.thu);
            String curdayStr5 = String.valueOf(curDay5);
            if (curDay5 < 10) {
                curdayStr5 = "0" + curdayStr5;
            }
            thuBtn.setText((mCalendar.get(Calendar.MONTH)+1) + "/" + curdayStr5);
            loadScheduleListData(mScheduleListAdapter5);
            /////////////////////
            thuBtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                    int dayday = curDay5;
                    int monmon = mCalendar.get(Calendar.MONTH);
                    int yearyear = mCalendar.get(Calendar.YEAR);

                    intent.putExtra(BasicInfo.KEY_DAY_FROM_WEEK, String.valueOf(dayday));
                    intent.putExtra(BasicInfo.KEY_MONTH_FROM_WEEK, String.valueOf(monmon));
                    intent.putExtra(BasicInfo.KEY_YEAR_FROM_WEEK, String.valueOf(yearyear));

                    //intent.putExtra("111", "heyeheheh");
                    Log.d("WeeklyActivity", "Selected : " + dayday + " " + monmon + " " + yearyear);

                    setResult(RESULT_OK, intent);
                    finish();
                }
            });
            ////////////////////

            mCalendar.add(Calendar.DATE,1);
            final int curDay6 = mCalendar.get(Calendar.DAY_OF_MONTH);
            Button friBtn = (Button)findViewById(R.id.fri);
            String curdayStr6 = String.valueOf(curDay6);
            if (curDay6 < 10) {
                curdayStr6 = "0" + curdayStr6;
            }
            friBtn.setText((mCalendar.get(Calendar.MONTH)+1) + "/" + curdayStr6);
            loadScheduleListData(mScheduleListAdapter6);
            /////////////////////
            friBtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                    int dayday = curDay6;
                    int monmon = mCalendar.get(Calendar.MONTH);
                    int yearyear = mCalendar.get(Calendar.YEAR);

                    intent.putExtra(BasicInfo.KEY_DAY_FROM_WEEK, String.valueOf(dayday));
                    intent.putExtra(BasicInfo.KEY_MONTH_FROM_WEEK, String.valueOf(monmon));
                    intent.putExtra(BasicInfo.KEY_YEAR_FROM_WEEK, String.valueOf(yearyear));

                    //intent.putExtra("111", "heyeheheh");
                    Log.d("WeeklyActivity", "Selected : " + dayday + " " + monmon + " " + yearyear);

                    setResult(RESULT_OK, intent);
                    finish();
                }
            });
            ////////////////////

            mCalendar.add(Calendar.DATE,1);
            final int curDay7 = mCalendar.get(Calendar.DAY_OF_MONTH);
            Button satBtn = (Button)findViewById(R.id.sat);
            String curdayStr7 = String.valueOf(curDay7);
            if (curDay7 < 10) {
                curdayStr7 = "0" + curdayStr7;
            }
            satBtn.setText((mCalendar.get(Calendar.MONTH)+1) + "/" + curdayStr7);
            loadScheduleListData(mScheduleListAdapter7);
            /////////////////////
            satBtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                    int dayday = curDay7;
                    int monmon = mCalendar.get(Calendar.MONTH);
                    int yearyear = mCalendar.get(Calendar.YEAR);

                    intent.putExtra(BasicInfo.KEY_DAY_FROM_WEEK, String.valueOf(dayday));
                    intent.putExtra(BasicInfo.KEY_MONTH_FROM_WEEK, String.valueOf(monmon));
                    intent.putExtra(BasicInfo.KEY_YEAR_FROM_WEEK, String.valueOf(yearyear));

                    //intent.putExtra("111", "heyeheheh");
                    Log.d("WeeklyActivity", "Selected : " + dayday + " " + monmon + " " + yearyear);

                    setResult(RESULT_OK, intent);
                    finish();
                }
            });
            ////////////////////

        }
        else if (dayOfWeek==6){
            mCalendar.add(Calendar.DATE,-5);
            final int curDay = mCalendar.get(Calendar.DAY_OF_MONTH);
            Button sunBtn = (Button)findViewById(R.id.sun);
            String curdayStr = String.valueOf(curDay);
            if (curDay < 10) {
                curdayStr = "0" + curdayStr;
            }
            sunBtn.setText((mCalendar.get(Calendar.MONTH)+1) + "/" + curdayStr);
            loadScheduleListData(mScheduleListAdapter1);
            /////////////////////
            sunBtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                    int dayday = curDay;
                    int monmon = mCalendar.get(Calendar.MONTH);
                    int yearyear = mCalendar.get(Calendar.YEAR);

                    intent.putExtra(BasicInfo.KEY_DAY_FROM_WEEK, String.valueOf(dayday));
                    intent.putExtra(BasicInfo.KEY_MONTH_FROM_WEEK, String.valueOf(monmon));
                    intent.putExtra(BasicInfo.KEY_YEAR_FROM_WEEK, String.valueOf(yearyear));

                    //intent.putExtra("111", "heyeheheh");
                    Log.d("WeeklyActivity", "Selected : " + dayday + " " + monmon + " " + yearyear);

                    setResult(RESULT_OK, intent);
                    finish();
                }
            });
            ////////////////////

            mCalendar.add(Calendar.DATE,1);
            final int curDay2 = mCalendar.get(Calendar.DAY_OF_MONTH);
            Button monBtn = (Button)findViewById(R.id.mon);
            String curdayStr2 = String.valueOf(curDay2);
            if (curDay2 < 10) {
                curdayStr2 = "0" + curdayStr2;
            }
            monBtn.setText((mCalendar.get(Calendar.MONTH)+1) + "/" + curdayStr2);
            loadScheduleListData(mScheduleListAdapter2);
            /////////////////////
            monBtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                    int dayday = curDay2;
                    int monmon = mCalendar.get(Calendar.MONTH);
                    int yearyear = mCalendar.get(Calendar.YEAR);

                    intent.putExtra(BasicInfo.KEY_DAY_FROM_WEEK, String.valueOf(dayday));
                    intent.putExtra(BasicInfo.KEY_MONTH_FROM_WEEK, String.valueOf(monmon));
                    intent.putExtra(BasicInfo.KEY_YEAR_FROM_WEEK, String.valueOf(yearyear));

                    //intent.putExtra("111", "heyeheheh");
                    Log.d("WeeklyActivity", "Selected : " + dayday + " " + monmon + " " + yearyear);

                    setResult(RESULT_OK, intent);
                    finish();
                }
            });
            ////////////////////

            mCalendar.add(Calendar.DATE,1);
            final int curDay3 = mCalendar.get(Calendar.DAY_OF_MONTH);
            Button tueBtn = (Button)findViewById(R.id.tue);
            String curdayStr3 = String.valueOf(curDay3);
            if (curDay3 < 10) {
                curdayStr3 = "0" + curdayStr3;
            }
            tueBtn.setText((mCalendar.get(Calendar.MONTH)+1) + "/" + curdayStr3);
            loadScheduleListData(mScheduleListAdapter3);
            /////////////////////
            tueBtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                    int dayday = curDay3;
                    int monmon = mCalendar.get(Calendar.MONTH);
                    int yearyear = mCalendar.get(Calendar.YEAR);

                    intent.putExtra(BasicInfo.KEY_DAY_FROM_WEEK, String.valueOf(dayday));
                    intent.putExtra(BasicInfo.KEY_MONTH_FROM_WEEK, String.valueOf(monmon));
                    intent.putExtra(BasicInfo.KEY_YEAR_FROM_WEEK, String.valueOf(yearyear));

                    //intent.putExtra("111", "heyeheheh");
                    Log.d("WeeklyActivity", "Selected : " + dayday + " " + monmon + " " + yearyear);

                    setResult(RESULT_OK, intent);
                    finish();
                }
            });
            ////////////////////

            mCalendar.add(Calendar.DATE,1);
            final int curDay4 = mCalendar.get(Calendar.DAY_OF_MONTH);
            Button wedBtn = (Button)findViewById(R.id.wed);
            String curdayStr4 = String.valueOf(curDay4);
            if (curDay4 < 10) {
                curdayStr4 = "0" + curdayStr4;
            }
            wedBtn.setText((mCalendar.get(Calendar.MONTH)+1) + "/" + curdayStr4);
            loadScheduleListData(mScheduleListAdapter4);
            /////////////////////
            wedBtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                    int dayday = curDay4;
                    int monmon = mCalendar.get(Calendar.MONTH);
                    int yearyear = mCalendar.get(Calendar.YEAR);

                    intent.putExtra(BasicInfo.KEY_DAY_FROM_WEEK, String.valueOf(dayday));
                    intent.putExtra(BasicInfo.KEY_MONTH_FROM_WEEK, String.valueOf(monmon));
                    intent.putExtra(BasicInfo.KEY_YEAR_FROM_WEEK, String.valueOf(yearyear));

                    //intent.putExtra("111", "heyeheheh");
                    Log.d("WeeklyActivity", "Selected : " + dayday + " " + monmon + " " + yearyear);

                    setResult(RESULT_OK, intent);
                    finish();
                }
            });
            ////////////////////

            mCalendar.add(Calendar.DATE,1);
            final int curDay5 = mCalendar.get(Calendar.DAY_OF_MONTH);
            Button thuBtn = (Button)findViewById(R.id.thu);
            String curdayStr5 = String.valueOf(curDay5);
            if (curDay5 < 10) {
                curdayStr5 = "0" + curdayStr5;
            }
            thuBtn.setText((mCalendar.get(Calendar.MONTH)+1) + "/" + curdayStr5);
            loadScheduleListData(mScheduleListAdapter5);
            /////////////////////
            thuBtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                    int dayday = curDay5;
                    int monmon = mCalendar.get(Calendar.MONTH);
                    int yearyear = mCalendar.get(Calendar.YEAR);

                    intent.putExtra(BasicInfo.KEY_DAY_FROM_WEEK, String.valueOf(dayday));
                    intent.putExtra(BasicInfo.KEY_MONTH_FROM_WEEK, String.valueOf(monmon));
                    intent.putExtra(BasicInfo.KEY_YEAR_FROM_WEEK, String.valueOf(yearyear));

                    //intent.putExtra("111", "heyeheheh");
                    Log.d("WeeklyActivity", "Selected : " + dayday + " " + monmon + " " + yearyear);

                    setResult(RESULT_OK, intent);
                    finish();
                }
            });
            ////////////////////

            mCalendar.add(Calendar.DATE,1);
            final int curDay6 = mCalendar.get(Calendar.DAY_OF_MONTH);
            Button friBtn = (Button)findViewById(R.id.fri);
            String curdayStr6 = String.valueOf(curDay6);
            if (curDay6 < 10) {
                curdayStr6 = "0" + curdayStr6;
            }
            friBtn.setText((mCalendar.get(Calendar.MONTH)+1) + "/" + curdayStr6);
            loadScheduleListData(mScheduleListAdapter6);
            /////////////////////
            friBtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                    int dayday = curDay6;
                    int monmon = mCalendar.get(Calendar.MONTH);
                    int yearyear = mCalendar.get(Calendar.YEAR);

                    intent.putExtra(BasicInfo.KEY_DAY_FROM_WEEK, String.valueOf(dayday));
                    intent.putExtra(BasicInfo.KEY_MONTH_FROM_WEEK, String.valueOf(monmon));
                    intent.putExtra(BasicInfo.KEY_YEAR_FROM_WEEK, String.valueOf(yearyear));

                    //intent.putExtra("111", "heyeheheh");
                    Log.d("WeeklyActivity", "Selected : " + dayday + " " + monmon + " " + yearyear);

                    setResult(RESULT_OK, intent);
                    finish();
                }
            });
            ////////////////////

            mCalendar.add(Calendar.DATE,1);
            final int curDay7 = mCalendar.get(Calendar.DAY_OF_MONTH);
            Button satBtn = (Button)findViewById(R.id.sat);
            String curdayStr7 = String.valueOf(curDay7);
            if (curDay7 < 10) {
                curdayStr7 = "0" + curdayStr7;
            }
            satBtn.setText((mCalendar.get(Calendar.MONTH)+1) + "/" + curdayStr7);
            loadScheduleListData(mScheduleListAdapter7);
            /////////////////////
            satBtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                    int dayday = curDay7;
                    int monmon = mCalendar.get(Calendar.MONTH);
                    int yearyear = mCalendar.get(Calendar.YEAR);

                    intent.putExtra(BasicInfo.KEY_DAY_FROM_WEEK, String.valueOf(dayday));
                    intent.putExtra(BasicInfo.KEY_MONTH_FROM_WEEK, String.valueOf(monmon));
                    intent.putExtra(BasicInfo.KEY_YEAR_FROM_WEEK, String.valueOf(yearyear));

                    //intent.putExtra("111", "heyeheheh");
                    Log.d("WeeklyActivity", "Selected : " + dayday + " " + monmon + " " + yearyear);

                    setResult(RESULT_OK, intent);
                    finish();
                }
            });
            ////////////////////

        }
        else if (dayOfWeek==7){
            mCalendar.add(Calendar.DATE,-6);
            final int curDay = mCalendar.get(Calendar.DAY_OF_MONTH);
            Button sunBtn = (Button)findViewById(R.id.sun);
            String curdayStr = String.valueOf(curDay);
            if (curDay < 10) {
                curdayStr = "0" + curdayStr;
            }
            sunBtn.setText((mCalendar.get(Calendar.MONTH)+1) + "/" + curdayStr);
            loadScheduleListData(mScheduleListAdapter1);
            /////////////////////
            sunBtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                    int dayday = curDay;
                    int monmon = mCalendar.get(Calendar.MONTH);
                    int yearyear = mCalendar.get(Calendar.YEAR);

                    intent.putExtra(BasicInfo.KEY_DAY_FROM_WEEK, String.valueOf(dayday));
                    intent.putExtra(BasicInfo.KEY_MONTH_FROM_WEEK, String.valueOf(monmon));
                    intent.putExtra(BasicInfo.KEY_YEAR_FROM_WEEK, String.valueOf(yearyear));

                    //intent.putExtra("111", "heyeheheh");
                    Log.d("WeeklyActivity", "Selected : " + dayday + " " + monmon + " " + yearyear);

                    setResult(RESULT_OK, intent);
                    finish();
                }
            });
            ////////////////////

            mCalendar.add(Calendar.DATE,1);
            final int curDay2 = mCalendar.get(Calendar.DAY_OF_MONTH);
            Button monBtn = (Button)findViewById(R.id.mon);
            String curdayStr2 = String.valueOf(curDay2);
            if (curDay2 < 10) {
                curdayStr2 = "0" + curdayStr2;
            }
            monBtn.setText((mCalendar.get(Calendar.MONTH)+1) + "/" + curdayStr2);
            loadScheduleListData(mScheduleListAdapter2);
            /////////////////////
            monBtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                    int dayday = curDay2;
                    int monmon = mCalendar.get(Calendar.MONTH);
                    int yearyear = mCalendar.get(Calendar.YEAR);

                    intent.putExtra(BasicInfo.KEY_DAY_FROM_WEEK, String.valueOf(dayday));
                    intent.putExtra(BasicInfo.KEY_MONTH_FROM_WEEK, String.valueOf(monmon));
                    intent.putExtra(BasicInfo.KEY_YEAR_FROM_WEEK, String.valueOf(yearyear));

                    //intent.putExtra("111", "heyeheheh");
                    Log.d("WeeklyActivity", "Selected : " + dayday + " " + monmon + " " + yearyear);

                    setResult(RESULT_OK, intent);
                    finish();
                }
            });
            ////////////////////

            mCalendar.add(Calendar.DATE,1);
            final int curDay3 = mCalendar.get(Calendar.DAY_OF_MONTH);
            Button tueBtn = (Button)findViewById(R.id.tue);
            String curdayStr3 = String.valueOf(curDay3);
            if (curDay3 < 10) {
                curdayStr3 = "0" + curdayStr3;
            }
            tueBtn.setText((mCalendar.get(Calendar.MONTH)+1) + "/" + curdayStr3);
            loadScheduleListData(mScheduleListAdapter3);
            /////////////////////
            tueBtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                    int dayday = curDay3;
                    int monmon = mCalendar.get(Calendar.MONTH);
                    int yearyear = mCalendar.get(Calendar.YEAR);

                    intent.putExtra(BasicInfo.KEY_DAY_FROM_WEEK, String.valueOf(dayday));
                    intent.putExtra(BasicInfo.KEY_MONTH_FROM_WEEK, String.valueOf(monmon));
                    intent.putExtra(BasicInfo.KEY_YEAR_FROM_WEEK, String.valueOf(yearyear));

                    //intent.putExtra("111", "heyeheheh");
                    Log.d("WeeklyActivity", "Selected : " + dayday + " " + monmon + " " + yearyear);

                    setResult(RESULT_OK, intent);
                    finish();
                }
            });
            ////////////////////

            mCalendar.add(Calendar.DATE,1);
            final int curDay4 = mCalendar.get(Calendar.DAY_OF_MONTH);
            Button wedBtn = (Button)findViewById(R.id.wed);
            String curdayStr4 = String.valueOf(curDay4);
            if (curDay4 < 10) {
                curdayStr4 = "0" + curdayStr4;
            }
            wedBtn.setText((mCalendar.get(Calendar.MONTH)+1) + "/" + curdayStr4);
            loadScheduleListData(mScheduleListAdapter4);
            /////////////////////
            wedBtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                    int dayday = curDay4;
                    int monmon = mCalendar.get(Calendar.MONTH);
                    int yearyear = mCalendar.get(Calendar.YEAR);

                    intent.putExtra(BasicInfo.KEY_DAY_FROM_WEEK, String.valueOf(dayday));
                    intent.putExtra(BasicInfo.KEY_MONTH_FROM_WEEK, String.valueOf(monmon));
                    intent.putExtra(BasicInfo.KEY_YEAR_FROM_WEEK, String.valueOf(yearyear));

                    //intent.putExtra("111", "heyeheheh");
                    Log.d("WeeklyActivity", "Selected : " + dayday + " " + monmon + " " + yearyear);

                    setResult(RESULT_OK, intent);
                    finish();
                }
            });
            ////////////////////

            mCalendar.add(Calendar.DATE,1);
            final int curDay5 = mCalendar.get(Calendar.DAY_OF_MONTH);
            Button thuBtn = (Button)findViewById(R.id.thu);
            String curdayStr5 = String.valueOf(curDay5);
            if (curDay5 < 10) {
                curdayStr5 = "0" + curdayStr5;
            }
            thuBtn.setText((mCalendar.get(Calendar.MONTH)+1) + "/" + curdayStr5);
            loadScheduleListData(mScheduleListAdapter5);
            /////////////////////
            thuBtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                    int dayday = curDay5;
                    int monmon = mCalendar.get(Calendar.MONTH);
                    int yearyear = mCalendar.get(Calendar.YEAR);

                    intent.putExtra(BasicInfo.KEY_DAY_FROM_WEEK, String.valueOf(dayday));
                    intent.putExtra(BasicInfo.KEY_MONTH_FROM_WEEK, String.valueOf(monmon));
                    intent.putExtra(BasicInfo.KEY_YEAR_FROM_WEEK, String.valueOf(yearyear));

                    //intent.putExtra("111", "heyeheheh");
                    Log.d("WeeklyActivity", "Selected : " + dayday + " " + monmon + " " + yearyear);

                    setResult(RESULT_OK, intent);
                    finish();
                }
            });
            ////////////////////

            mCalendar.add(Calendar.DATE,1);
            final int curDay6 = mCalendar.get(Calendar.DAY_OF_MONTH);
            Button friBtn = (Button)findViewById(R.id.fri);
            String curdayStr6 = String.valueOf(curDay6);
            if (curDay6 < 10) {
                curdayStr6 = "0" + curdayStr6;
            }
            friBtn.setText((mCalendar.get(Calendar.MONTH)+1) + "/" + curdayStr6);
            loadScheduleListData(mScheduleListAdapter6);
            /////////////////////
            friBtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                    int dayday = curDay6;
                    int monmon = mCalendar.get(Calendar.MONTH);
                    int yearyear = mCalendar.get(Calendar.YEAR);

                    intent.putExtra(BasicInfo.KEY_DAY_FROM_WEEK, String.valueOf(dayday));
                    intent.putExtra(BasicInfo.KEY_MONTH_FROM_WEEK, String.valueOf(monmon));
                    intent.putExtra(BasicInfo.KEY_YEAR_FROM_WEEK, String.valueOf(yearyear));

                    //intent.putExtra("111", "heyeheheh");
                    Log.d("WeeklyActivity", "Selected : " + dayday + " " + monmon + " " + yearyear);

                    setResult(RESULT_OK, intent);
                    finish();
                }
            });
            ////////////////////

            mCalendar.add(Calendar.DATE,1);
            final int curDay7 = mCalendar.get(Calendar.DAY_OF_MONTH);
            Button satBtn = (Button)findViewById(R.id.sat);
            String curdayStr7 = String.valueOf(curDay7);
            if (curDay7 < 10) {
                curdayStr7 = "0" + curdayStr7;
            }
            satBtn.setText((mCalendar.get(Calendar.MONTH)+1) + "/" + curdayStr7);
            loadScheduleListData(mScheduleListAdapter7);
            /////////////////////
            satBtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                    int dayday = curDay7;
                    int monmon = mCalendar.get(Calendar.MONTH);
                    int yearyear = mCalendar.get(Calendar.YEAR);

                    intent.putExtra(BasicInfo.KEY_DAY_FROM_WEEK, String.valueOf(dayday));
                    intent.putExtra(BasicInfo.KEY_MONTH_FROM_WEEK, String.valueOf(monmon));
                    intent.putExtra(BasicInfo.KEY_YEAR_FROM_WEEK, String.valueOf(yearyear));

                    //intent.putExtra("111", "heyeheheh");
                    Log.d("WeeklyActivity", "Selected : " + dayday + " " + monmon + " " + yearyear);

                    setResult(RESULT_OK, intent);
                    finish();
                }
            });
            ////////////////////

        }
    }


    private void viewMemo(int position,ScheduleListAdapter mScheduleListAdapter) {
        ScheduleListItem item = (ScheduleListItem)mScheduleListAdapter.getItem(position);

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
        curMonth++;
        String curmonthStr = String.valueOf(curMonth);
        if (curMonth < 10) {
            curmonthStr = "0" + curmonthStr;
        }
        int curDay = mCalendar.get(Calendar.DAY_OF_MONTH);
        String curdayStr = String.valueOf(curDay);
        if (curDay < 10) {
            curdayStr = "0" + curdayStr;
        }

        String date = String.valueOf(curYear);
        String date1 = date.concat("-" + curmonthStr);
        String date2 = date1.concat("-" + curdayStr);

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
