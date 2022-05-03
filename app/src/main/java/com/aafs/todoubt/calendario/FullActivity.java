package com.aafs.todoubt.calendario;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.aafs.todoubt.DetallePartido;
import com.aafs.todoubt.R;
import com.aafs.todoubt.calendario.activitycalendario.BaseActivity;
import com.aafs.todoubt.wsdatos.DatosPartido;
import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.CalendarView;

import com.haibin.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FullActivity extends BaseActivity implements
        CalendarView.OnCalendarSelectListener,
        CalendarView.OnYearChangeListener,
        View.OnClickListener {

    private TextView mTextMonthDay;
    private TextView mTextYear;
    private TextView mTextCurrentDay;
    private int mYear;
    private CalendarView mCalendarView;
    private ArrayList<DatosPartido> listaPartidos;
    private ImageButton bck;

    public static void show(Context context) {
        context.startActivity(new Intent(context, FullActivity.class));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_calendario;
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void initView() {
        setStatusBarDarkMode();
        mTextMonthDay = findViewById(R.id.tv_month_day);
        mTextYear = findViewById(R.id.tv_year);
        mTextCurrentDay = findViewById(R.id.tv_current_day);
        mCalendarView = findViewById(R.id.calendarView);
        bck = findViewById(R.id.cal_bck_btn);

        mTextMonthDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCalendarView.showYearSelectLayout(mYear);
                mTextYear.setVisibility(View.GONE);
                mTextMonthDay.setText(String.valueOf(mYear));
            }
        });
        findViewById(R.id.fl_current).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCalendarView.scrollToCurrent();
            }
        });

        mCalendarView.setOnCalendarSelectListener(this);
        mCalendarView.setOnYearChangeListener(this);
        mTextYear.setText(String.valueOf(mCalendarView.getCurYear()));
        mYear = mCalendarView.getCurYear();
        mTextMonthDay.setText(mCalendarView.getCurMonth() + " - " + mCalendarView.getCurDay());
        mTextCurrentDay.setText(String.valueOf(mCalendarView.getCurDay()));
        bck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Recpger Datos Intent
        listaPartidos = (ArrayList<DatosPartido>)( getIntent().getBundleExtra("LIST").getSerializable("key"));
    }

    @Override
    protected void initData() {
        //Eventos
        Map<String, Calendar> map = new HashMap<>();

        for (DatosPartido d :listaPartidos) {
            String año = d.getFecha().substring(d.getFecha().length()-11,d.getFecha().length()-1).split("-")[2];
            String mes = d.getFecha().substring(d.getFecha().length()-11,d.getFecha().length()-1).split("-")[1];
            String dia = d.getFecha().substring(d.getFecha().length()-11,d.getFecha().length()-1).split("-")[0];
            map.put(getSchemeCalendar(Integer.parseInt(año), Integer.parseInt(mes), Integer.parseInt(dia), 0xFF40db25, d.getJornada()).toString(),
                    getSchemeCalendar(Integer.parseInt(año), Integer.parseInt(mes), Integer.parseInt(dia), 0xFF40db25, d.getJornada()));
        }


        mCalendarView.setSchemeDate(map);
    }

    private Calendar getSchemeCalendar(int year, int month, int day, int color, String text) {
        Calendar calendar = new Calendar();
        calendar.setYear(year);
        calendar.setMonth(month);
        calendar.setDay(day);
        calendar.setSchemeColor(color);
        calendar.setScheme(text);
        calendar.addScheme(color, "");
        return calendar;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onCalendarOutOfRange(Calendar calendar) {

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onCalendarSelect(Calendar calendar, boolean isClick) {
        mTextYear.setVisibility(View.VISIBLE);
        mTextMonthDay.setText(calendar.getMonth() + " - " + calendar.getDay());
        mTextYear.setText(String.valueOf(calendar.getYear()));
        mYear = calendar.getYear();

        Log.e("onDateSelected", "  -- " + calendar.getYear() +
                "  --  " + calendar.getMonth() +
                "  -- " + calendar.getDay() +
                "  --  " + isClick + "  --   " + calendar.getScheme());
        for (DatosPartido d : listaPartidos) {
            if (calendar.getScheme() != null && calendar.getScheme().equals(d.getJornada())){
                Intent i = new Intent(FullActivity.this, DetallePartido.class);
                i.putExtra("partido", d);
                startActivity(i);
            }
        }

    }

    @Override
    public void onYearChange(int year) {
        mTextMonthDay.setText(String.valueOf(year));
    }
}
