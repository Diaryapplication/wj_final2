package org.androidtown.diaryapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import org.androidtown.diaryapplication.db.ScheduleDatabase;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

public class InsertScheduleActivity extends Activity {

    public static final String TAG = "InsertScheduleActivity";

    EditText mScheduleEdit;

    String mScheduleMode;
    String mScheduleId;
    String mScheduleDate;

    String mDateStr;
    String mScheduleStr;

    EditText edit_name;

    Calendar mCalendar = Calendar.getInstance();
    Button addDateBtn;
    Button addSaveBtn;
    Button addCancelBtn;
    Button deleteBtn;
    Button addTimeBtn;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_schedule);

        mScheduleEdit = (EditText) findViewById(R.id.edit_name);
        edit_name = (EditText)findViewById(R.id.edit_name);
        deleteBtn = (Button)findViewById(R.id.add_deleteBtn);

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(BasicInfo.CONFIRM_DELETE);
            }
        });

        setBottomButtons();
        setCalendar();

        Intent intent = getIntent();
        mScheduleMode = intent.getStringExtra(BasicInfo.KEY_MEMO_MODE);
        if(mScheduleMode.equals(BasicInfo.MODE_MODIFY) || mScheduleMode.equals(BasicInfo.MODE_VIEW)) {
            processIntent(intent);

            addSaveBtn.setText("Modify");

            deleteBtn.setVisibility(View.VISIBLE);
        } else {
            addSaveBtn.setText("Save");

            deleteBtn.setVisibility(View.GONE);
        }
    }

    public void processIntent(Intent intent) {
        mScheduleId = intent.getStringExtra(BasicInfo.KEY_MEMO_ID);
        mScheduleDate = intent.getStringExtra(BasicInfo.KEY_MEMO_DATE);
        String curScheduleText = intent.getStringExtra(BasicInfo.KEY_MEMO_TEXT);
        mScheduleEdit.setText(curScheduleText);


        setScheduleDate(mScheduleDate);


    }
    public void setBottomButtons(){
        addSaveBtn = (Button)findViewById(R.id.add_saveBtn);
        addCancelBtn = (Button)findViewById(R.id.add_cancelBtn);

        addSaveBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                boolean isParsed = parseValues();
                if (isParsed) {
                    if (mScheduleMode.equals(BasicInfo.MODE_INSERT)) {
                        saveInput();
                    } else if (mScheduleMode.equals(BasicInfo.MODE_MODIFY) || mScheduleMode.equals(BasicInfo.MODE_VIEW)) {
                        modifyInput();
                    }
                }
            }
        });

        addCancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void saveInput(){

        String SQL = null;

        SQL = "insert into " + ScheduleDatabase.TABLE_SCHEDULE +
                "(INPUT_DATE, CONTENT_TEXT) values(" +
                "DATETIME('" + mDateStr + "'), " +
                "'"+ mScheduleStr + "')";

        Log.d(TAG, "SQL : " + SQL);
        if (MainActivity.mDatabase != null) {
            MainActivity.mDatabase.execSQL(SQL);
        }

        Intent intent = getIntent();
        setResult(RESULT_OK, intent);
        finish();
    }

    /**
     * 데이터베이스 레코드 수정
     */
    private void modifyInput() {

        Intent intent = getIntent();


        String SQL = null;
        // update memo info
        SQL = "update " + ScheduleDatabase.TABLE_SCHEDULE + " set " +
                " INPUT_DATE = DATETIME('" + mDateStr + "'), " +
                " CONTENT_TEXT = '" + mScheduleStr + "'" +
                " where _id = '" + mScheduleId + "'";

        Log.d(TAG, "SQL : " + SQL);
        if (MainActivity.mDatabase != null) {
            MainActivity.mDatabase.execSQL(SQL);
        }

        intent.putExtra(BasicInfo.KEY_MEMO_TEXT, mScheduleStr);

        setResult(RESULT_OK, intent);
        finish();
    }

    private void setCalendar(){
        addDateBtn = (Button) findViewById(R.id.insert_dateBtn);
        addDateBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String mDateStr = addDateBtn.getText().toString();
                Calendar calendar = Calendar.getInstance();
                Date date = new Date();
                try {

                    date = BasicInfo.dateDayFormat.parse(mDateStr);

                } catch(Exception ex) {
                    Log.d(TAG, "Exception in parsing date : " + date);
                }

                calendar.setTime(date);

                new DatePickerDialog(
                        InsertScheduleActivity.this,
                        dateSetListener,
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)
                ).show();

            }
        });

        addTimeBtn = (Button) findViewById(R.id.insert_timeBtn);
        addTimeBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String mTimeStr = addTimeBtn.getText().toString();
                Calendar calendar = Calendar.getInstance();
                Date date = new Date();
                try {

                    date = BasicInfo.dateTimeFormat.parse(mTimeStr);

                } catch(Exception ex) {
                    Log.d(TAG, "Exception in parsing date : " + date);
                }

                calendar.setTime(date);

                new TimePickerDialog(
                        InsertScheduleActivity.this,
                        timeSetListener,
                        calendar.get(Calendar.HOUR_OF_DAY),
                        calendar.get(Calendar.MINUTE),
                        true
                ).show();

            }
        });

        Date curDate = new Date();
        mCalendar.setTime(curDate);

        int year = mCalendar.get(Calendar.YEAR);
        int monthOfYear = mCalendar.get(Calendar.MONTH);
        int dayOfMonth = mCalendar.get(Calendar.DAY_OF_MONTH);

        String monthStr = String.valueOf(monthOfYear+1);
        if (monthOfYear < 9) {
            monthStr = "0" + monthStr;
        }

        String dayStr = String.valueOf(dayOfMonth);
        if (dayOfMonth < 10) {
            dayStr = "0" + dayStr;
        }


        addDateBtn.setText(year + "-" + monthStr + "-" + dayStr);


        int hourOfDay = mCalendar.get(Calendar.HOUR_OF_DAY);
        int minute = mCalendar.get(Calendar.MINUTE);

        String hourStr = String.valueOf(hourOfDay);
        if (hourOfDay < 10) {
            hourStr = "0" + hourStr;
        }

        String minuteStr = String.valueOf(minute);
        if (minute < 10) {
            minuteStr = "0" + minuteStr;
        }

        addTimeBtn.setText(hourStr + ":" + minuteStr);


    }

    private void setScheduleDate(String dateStr) {
        Log.d(TAG, "setScheduleDate() called : " + dateStr);

        Date date = new Date();
        try {

            date = BasicInfo.dateNameFormat3.parse(dateStr);

        } catch(Exception ex) {
            Log.d(TAG, "Exception in parsing date : " + dateStr);
        }

        //Calendar calendar = Calendar.getInstance();
        mCalendar.setTime(date);

        int year = mCalendar.get(Calendar.YEAR);
        int monthOfYear = mCalendar.get(Calendar.MONTH);
        int dayOfMonth = mCalendar.get(Calendar.DAY_OF_MONTH);

        String monthStr = String.valueOf(monthOfYear+1);
        if (monthOfYear < 9) {
            monthStr = "0" + monthStr;
        }

        String dayStr = String.valueOf(dayOfMonth);
        if (dayOfMonth < 10) {
            dayStr = "0" + dayStr;
        }


        addDateBtn.setText(year + "-" + monthStr + "-" + dayStr);


        int hourOfDay = mCalendar.get(Calendar.HOUR_OF_DAY);
        int minute = mCalendar.get(Calendar.MINUTE);

        String hourStr = String.valueOf(hourOfDay);
        if (hourOfDay < 10) {
            hourStr = "0" + hourStr;
        }

        String minuteStr = String.valueOf(minute);
        if (minute < 10) {
            minuteStr = "0" + minuteStr;
        }


        addTimeBtn.setText(hourStr + ":" + minuteStr);


    }


    /**
     * 날짜 설정 리스너
     */
    DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            mCalendar.set(year, monthOfYear, dayOfMonth);

            String monthStr = String.valueOf(monthOfYear+1);
            if (monthOfYear < 9) {
                monthStr = "0" + monthStr;
            }

            String dayStr = String.valueOf(dayOfMonth);
            if (dayOfMonth < 10) {
                dayStr = "0" + dayStr;
            }



            addDateBtn.setText(year + "-" + monthStr + "-" + dayStr);

        }
    };

    /**
     * 시간 설정 리스너
     */
    TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
        public void onTimeSet(TimePicker view, int hour_of_day, int minute) {
            mCalendar.set(Calendar.HOUR_OF_DAY, hour_of_day);
            mCalendar.set(Calendar.MINUTE, minute);

            String hourStr = String.valueOf(hour_of_day);
            if (hour_of_day < 10) {
                hourStr = "0" + hourStr;
            }

            String minuteStr = String.valueOf(minute);
            if (minute < 10) {
                minuteStr = "0" + minuteStr;
            }


            addTimeBtn.setText(hourStr + ":" + minuteStr);

        }
    };

    /**
     * 일자와 메모 확인
     */
    private boolean parseValues() {
        String insertDateStr = addDateBtn.getText().toString();
        String insertTimeStr = addTimeBtn.getText().toString();

        String srcDateStr = insertDateStr + " " + insertTimeStr;
        Log.d(TAG, "source date string : " + srcDateStr);

        try {

            Date insertDate = BasicInfo.dateNameFormat3.parse(srcDateStr);
            mDateStr = BasicInfo.dateFormat.format(insertDate);

        } catch(ParseException ex) {
            Log.e(TAG, "Exception in parsing date : " + insertDateStr);
        }


        mScheduleStr = mScheduleEdit.getText().toString();


        if ( (mScheduleMode != null && (mScheduleMode.equals(BasicInfo.MODE_MODIFY)  || mScheduleMode.equals(BasicInfo.MODE_VIEW)))) {

        } else {
            // check text memo
            if (mScheduleStr.trim().length() < 1) {
                showDialog(BasicInfo.CONFIRM_TEXT_INPUT);
                return false;
            }
        }

        return true;
    }

    protected Dialog onCreateDialog(int id) {
        AlertDialog.Builder builder = null;

        switch(id) {
            case BasicInfo.CONFIRM_TEXT_INPUT:
                builder = new AlertDialog.Builder(this);
                builder.setTitle("Schedule");
                builder.setMessage("Input a name");
                builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                    }
                });

                break;


            case BasicInfo.CONFIRM_DELETE:
                builder = new AlertDialog.Builder(this);
                builder.setTitle("Schedule");
                builder.setMessage("정말 지우시겠습니까?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        deleteMemo();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        dismissDialog(BasicInfo.CONFIRM_DELETE);
                    }
                });

                break;

            default:
                break;
        }

        return builder.create();
    }

    /**
     * 메모 삭제
     */
    private void deleteMemo() {


        // delete memo record
        Log.d(TAG, "deleting previous schedule record : " + mScheduleId);
        String SQL = "delete from " + ScheduleDatabase.TABLE_SCHEDULE +
                " where _id = '" + mScheduleId + "'";
        Log.d(TAG, "SQL : " + SQL);
        if (MainActivity.mDatabase != null) {
            MainActivity.mDatabase.execSQL(SQL);
        }

        setResult(RESULT_OK);

        finish();
    }


}
