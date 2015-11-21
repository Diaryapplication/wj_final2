package org.androidtown.diaryapplication;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by SOYOUNG on 2015-11-15.
 */
public class ScheduleListItemView extends LinearLayout {

    Calendar mCalendar = Calendar.getInstance();

    public static final String TAG = "ScheduleListItemView";

    private TextView timeText;

    private TextView itemText;


    public ScheduleListItemView(Context context) {
        super(context);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.activity_schedule_list_item, this, true);

        timeText = (TextView) findViewById(R.id.timeText);
        itemText = (TextView) findViewById(R.id.itemText);

    }

    public void setContents(int index, String data){

        if(index == 0) {

            Log.d(TAG, "setMemoDate() called : " + data);

            Date date = new Date();
            try {
                    date = BasicInfo.dateNameFormat3.parse(data);
            } catch(Exception ex) {
                Log.d(TAG, "Exception in parsing date : " + data);
            }

            mCalendar.setTime(date);

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

            timeText.setText(hourStr + ":" + minuteStr);

        }
        else if(index==1){
            itemText.setText(data);
        }
        else{
            throw new IllegalArgumentException();
        }
    }


}



