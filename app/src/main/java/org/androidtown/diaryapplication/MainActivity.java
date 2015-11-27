package org.androidtown.diaryapplication;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.androidtown.diaryapplication.db.ScheduleDatabase;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends Activity {

    public static final String TAG = "MainActivity";

    ListView mScheduleListView;
    ScheduleListAdapter mScheduleListAdapter;

    public static ScheduleDatabase mDatabase = null;

    Calendar mCalendar = Calendar.getInstance();
    TextView dateText;
    int curYear;
    int curMonth;
    int curDay;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Locale curLocale = getResources().getConfiguration().locale;
        BasicInfo.language = curLocale.getLanguage();
        Log.d(TAG, "current language : " + BasicInfo.language);

        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            Toast.makeText(this,"NO SDCARD", Toast.LENGTH_LONG).show();
            return;
        } else {
            String externalPath = Environment.getExternalStorageDirectory().getAbsolutePath();
            if (!BasicInfo.ExternalChecked && externalPath != null) {
                BasicInfo.ExternalPath = externalPath + File.separator;
                Log.d(TAG, "ExternalPath : " + BasicInfo.ExternalPath);

                BasicInfo.DATABASE_NAME = BasicInfo.ExternalPath + BasicInfo.DATABASE_NAME;

                BasicInfo.ExternalChecked = true;
            }
        }

        mScheduleListView = (ListView)findViewById(R.id.scheduleList);
        mScheduleListAdapter = new ScheduleListAdapter(this);
        mScheduleListView.setAdapter(mScheduleListAdapter);
        mScheduleListView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                viewMemo(position);
            }
        });

        Button newScheduleBtn = (Button)findViewById(R.id.add);
        newScheduleBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d(TAG, "newMemoBtn clicked.");
                Intent intent = new Intent(getApplicationContext(), InsertScheduleActivity.class);
                intent.putExtra(BasicInfo.KEY_MEMO_MODE, BasicInfo.MODE_INSERT);
                startActivityForResult(intent, BasicInfo.REQ_INSERT_ACTIVITY);
            }
        });

        init();

        // 이전 일로 넘어가는 이벤트 처리
        Button datePrevious = (Button) findViewById(R.id.preDate);
        datePrevious.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                mCalendar.add(Calendar.DATE, -1);
                setDateText();
                loadScheduleListData();
            }
        });

        // 다음 일로 넘어가는 이벤트 처리
        Button dateNext = (Button) findViewById(R.id.nextDate);
        dateNext.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mCalendar.add(Calendar.DATE,1);
                setDateText();
                loadScheduleListData();
            }
        });

        Button escBtn = (Button)findViewById(R.id.esc);
        escBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

        Button weeklyBtn = (Button)findViewById(R.id.week);
        weeklyBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Log.d(TAG, "weeklyBtn clicked.");
                Intent intent = new Intent(getApplicationContext(), WeeklyActivity.class);
                startActivityForResult(intent, BasicInfo.REQ_WEEKLY_ACTIVITY);
            }
        });
    }
    private void setDateText() {

        dateText = (TextView)findViewById(R.id.dateText);
        curYear = mCalendar.get(Calendar.YEAR);
        curMonth = mCalendar.get(Calendar.MONTH);
        curDay = mCalendar.get(Calendar.DATE);
        dateText.setText(curYear + "-" + (curMonth + 1) + "-" + curDay);

    }
    private void init(){

        dateText = (TextView)findViewById(R.id.dateText);
        Date curDate = new Date();
        mCalendar.setTime(curDate);

        int curYear = mCalendar.get(Calendar.YEAR);
        int curMonth = mCalendar.get(Calendar.MONTH);
        int curDay = mCalendar.get(Calendar.DAY_OF_MONTH);
        dateText.setText(curYear + "-" + (curMonth + 1) + "-" + curDay);



    }


    protected void onStart() {

        // 데이터베이스 열기
        openDatabase();

        // 메모 데이터 로딩
        loadScheduleListData();


        super.onStart();
    }

    /**
     * 데이터베이스 열기 (데이터베이스가 없을 때는 만들기)
     */

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
    /**
     * 메모 리스트 데이터 로딩
     */

    public int loadScheduleListData() {

        String date = dateText.getText().toString();
        String date1 = date.concat(" 00:00:01");
        String date2 = date.concat(" 23:59:59");

        String SQL = "select _id, INPUT_DATE, CONTENT_TEXT from SCHEDULE WHERE INPUT_DATE BETWEEN '"+date1+"' AND '"+date2+"'";

        int recordCount = -1;

        if (MainActivity.mDatabase != null) {
            Cursor outCursor = MainActivity.mDatabase.rawQuery(SQL);

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


    private void viewMemo(int position) {
        ScheduleListItem item = (ScheduleListItem)mScheduleListAdapter.getItem(position);

        Intent intent = new Intent(getApplicationContext(), InsertScheduleActivity.class);
        intent.putExtra(BasicInfo.KEY_MEMO_MODE, BasicInfo.MODE_VIEW);
        intent.putExtra(BasicInfo.KEY_MEMO_ID, item.getId());
        intent.putExtra(BasicInfo.KEY_MEMO_DATE, item.getData(0));
        intent.putExtra(BasicInfo.KEY_MEMO_TEXT, item.getData(1));

        startActivityForResult(intent, BasicInfo.REQ_VIEW_ACTIVITY);
    }


    /**
     * 다른 액티비티의 응답 처리
     */
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch(requestCode) {
            case BasicInfo.REQ_INSERT_ACTIVITY:
                if(resultCode == RESULT_OK) {
                    loadScheduleListData();
                }

                break;

            case BasicInfo.REQ_VIEW_ACTIVITY:
                loadScheduleListData();

                break;

        }
    }
}
