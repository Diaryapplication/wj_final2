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
import android.widget.TextView;

import org.androidtown.diaryapplication.db.ScheduleDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends Activity {

    public static final String TAG = "MainActivity";

    Calendar mCalendar = Calendar.getInstance();
    TextView dateBtn;
    public static SimpleDateFormat dateDayNameFormat = new SimpleDateFormat("yyyy년 MM월 dd일");

    ListView mschedulelistView;
    ScheduleListAdapter mscheduleListAdapter;

    /**
     * 데이터베이스 인스턴스
     */
    public static ScheduleDatabase mDatabase = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setCalendar();
        mschedulelistView = (ListView)findViewById(R.id.scheduleList);
        mscheduleListAdapter = new ScheduleListAdapter(this);
        mschedulelistView.setAdapter(mscheduleListAdapter);
        mschedulelistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
    }

    protected void onStart() {

        // 데이터베이스 열기
        openDatabase();

        // 메모 데이터 로딩
        loadMemoListData();


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
            Log.d(TAG, "Memo database is open.");
        } else {
            Log.d(TAG, "Memo database is not open.");
        }
    }
    /**
     * 메모 리스트 데이터 로딩
     */
    public int loadMemoListData() {

        String SQL = "select _id, INPUT_DATE, INPUT_TIME, CONTENT_TEXT, from SCHEDULE ";

        int recordCount = -1;
        if (MainActivity.mDatabase != null) {
            Cursor outCursor = MainActivity.mDatabase.rawQuery(SQL);

            recordCount = outCursor.getCount();
            Log.d(TAG, "cursor count : " + recordCount + "\n");

            mscheduleListAdapter.clear();

            for (int i = 0; i < recordCount; i++) {
                outCursor.moveToNext();

                String scheduleId = outCursor.getString(0);

                String dateStr = outCursor.getString(1);
                if (dateStr != null && dateStr.length() > 10) {
                    //dateStr = dateStr.substring(0, 10);
                    try {
                        Date inDate = BasicInfo.dateFormat.parse(dateStr);

                        if (BasicInfo.language.equals("ko")) {
                            dateStr = BasicInfo.dateNameFormat2.format(inDate);
                        } else {
                            dateStr = BasicInfo.dateNameFormat3.format(inDate);
                        }
                    } catch(Exception ex) {
                        ex.printStackTrace();
                    }
                } else {
                    dateStr = "";
                }

                String scheduleStr = outCursor.getString(2);


                mscheduleListAdapter.addItem(new ScheduleListItem(scheduleId, dateStr, scheduleStr));
            }

            outCursor.close();

            mscheduleListAdapter.notifyDataSetChanged();

        }

        return recordCount;

    }


    private void viewMemo(int position) {

        /*schedulelistitem item = (schedulelistitem)mscheduleListAdapter.getItem(position);

        Intent intent = new Intent(getApplicationContext(), AddActivity.class);
        intent.putExtra(BasicInfo.KEY_MEMO_MODE, BasicInfo.MODE_VIEW);
        intent.putExtra(BasicInfo.KEY_MEMO_ID, item.getId());
        intent.putExtra(BasicInfo.KEY_MEMO_DATE, item.getData(0));
        intent.putExtra(BasicInfo.KEY_MEMO_TEXT, item.getData(1));

        startActivityForResult(intent, BasicInfo.REQ_VIEW_ACTIVITY);*/
    }




    private void setCalendar(){

        dateBtn = (TextView)findViewById(R.id.dateText);
        String mDateStr = dateBtn.getText().toString();
        Calendar calendar = Calendar.getInstance();
        Date date = new Date();
        try {
            date = dateDayNameFormat.parse(mDateStr);
        } catch (Exception ex) {
            // Log.d(TAG, "Exception in parsing date : " + date);
        }

        calendar.setTime(date);

        Date curDate = new Date();
        mCalendar.setTime(curDate);

        int year = mCalendar.get(Calendar.YEAR);
        int monthOfYear = mCalendar.get(Calendar.MONTH);
        int dayOfMonth = mCalendar.get(Calendar.DAY_OF_MONTH);

        dateBtn.setText(year + "년 " + (monthOfYear + 1) + "월 " + dayOfMonth + "일");

    }

    /**
     * 다른 액티비티의 응답 처리
     */
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch(requestCode) {
            case BasicInfo.REQ_INSERT_ACTIVITY:
                if(resultCode == RESULT_OK) {
                    loadMemoListData();
                }

                break;

            case BasicInfo.REQ_VIEW_ACTIVITY:
                loadMemoListData();

                break;

        }
    }
}
