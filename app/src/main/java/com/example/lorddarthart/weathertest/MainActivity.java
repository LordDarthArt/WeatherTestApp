package com.example.lorddarthart.weathertest;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    SQLiteDatabase mSqLiteDatabase;
    DatabaseHelper mDatabaseHelper;
    HttpServiceHelper httpServiceHelper;
    ArrayList<Weather> weather = new ArrayList<>();
    private Cursor cursor;
    private ProgressDialog dialog;
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager layoutManager;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor ed;
    int opening=0, opening2=0;
    String[] cities;
    ConstraintLayout consLayText;
    ImageView constraintLayout;
    FloatingActionButton fab;
    EditText editText;
    Animation consLayOpen, consLayClose, rotateForward, rotateBackward, tvOpen, tvClose;
    boolean isOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        ed = sharedPreferences.edit();
        constraintLayout = findViewById(R.id.consLayHide);
        consLayText = findViewById(R.id.consLayText);
        editText = findViewById(R.id.editText);
        fab = findViewById(R.id.floatingActionButton);
        constraintLayout.setVisibility(View.VISIBLE);
        mDatabaseHelper = new DatabaseHelper(MainActivity.this, DatabaseHelper.DATABASE_NAME, null, DatabaseHelper.DATABASE_VERSION);
        mSqLiteDatabase = mDatabaseHelper.getWritableDatabase();
        String query = "SELECT " + DatabaseHelper.WEATHER_FILTERNAME + ", " + DatabaseHelper.WEATHER_DATE + ", " + DatabaseHelper.WEATHER_CITY + ", " + DatabaseHelper.WEATHER_NOW + ", "
                + DatabaseHelper.WEATHER_HIGH + " , " + DatabaseHelper.WEATHER_LOW + ", " + DatabaseHelper.WEATHER_TEXT + ", " + DatabaseHelper.WEATHER_DESCRIPTION + ", "
                + DatabaseHelper.WEATHER_HUMIDITY + ", " + DatabaseHelper.WEATHER_PRESSURE + ", " + DatabaseHelper.WEATHER_RISING + ", " + DatabaseHelper.WEATHER_VISIBILITY + ", "
                + DatabaseHelper.WEATHER_SUNRISE + ", " + DatabaseHelper.WEATHER_SUNSET + ", " + DatabaseHelper.WEATHER_D1_DAY + ", " + DatabaseHelper.WEATHER_D1_HIGH + ", "
                + DatabaseHelper.WEATHER_D1_LOW + ", " + DatabaseHelper.WEATHER_D1_TEXT + ", " + DatabaseHelper.WEATHER_D2_DAY + ", " + DatabaseHelper.WEATHER_D2_HIGH + ", " + DatabaseHelper.WEATHER_D2_LOW + ", "
                + DatabaseHelper.WEATHER_D2_TEXT + ", " + DatabaseHelper.WEATHER_D3_DAY + ", " + DatabaseHelper.WEATHER_D3_HIGH + ", " + DatabaseHelper.WEATHER_D3_LOW + ", "
                + DatabaseHelper.WEATHER_D3_TEXT + ", " + DatabaseHelper.WEATHER_D4_DAY + ", " + DatabaseHelper.WEATHER_D4_HIGH + ", " + DatabaseHelper.WEATHER_D4_LOW + ", "
                + DatabaseHelper.WEATHER_D4_TEXT + ", " + DatabaseHelper.WEATHER_D5_DAY + ", " + DatabaseHelper.WEATHER_D5_HIGH + ", " + DatabaseHelper.WEATHER_D5_LOW + ", "
                + DatabaseHelper.WEATHER_D5_TEXT + ", " + DatabaseHelper.WEATHER_D6_DAY + ", " + DatabaseHelper.WEATHER_D6_HIGH + ", " + DatabaseHelper.WEATHER_D6_LOW + ", "
                + DatabaseHelper.WEATHER_D6_TEXT + ", " + DatabaseHelper.WEATHER_D7_DAY + ", " + DatabaseHelper.WEATHER_D7_HIGH + ", " + DatabaseHelper.WEATHER_D7_LOW + ", "
                + DatabaseHelper.WEATHER_D7_TEXT + " FROM " + DatabaseHelper.DATABASE_WEATHER;
        cursor = mSqLiteDatabase.rawQuery(query, new String[0]);
        mRecyclerView = findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateFab();
            }
        });
        rotateForward = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_forward);
        rotateBackward = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_backward);
        consLayOpen = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.conslay_open);
        consLayClose = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.conslay_close);
        tvOpen = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.tv_open);
        tvClose = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.tv_close);
        if (!sharedPreferences.contains("cities")) {
            ed.putString("cities", "Saint-Petersburg,Russia,,Moscow,Russia,,");
            ed.commit();
        }
        cities = sharedPreferences.getString("cities", "").split(",,");
        httpServiceHelper = new HttpServiceHelper();
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (editText.getText().length()>0&&fab.getRotation()!=-45) {
                    fab.setImageResource(android.R.drawable.ic_menu_send);
                    fab.setRotation(-45);
                    fab.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            fab.setRotation(0);
                            fab.setImageResource(R.drawable.ic_baseline_plus_24px);
                            animateFab();
                            fab.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    animateFab();
                                }
                            });
                            ed.putString("cities", sharedPreferences.getString("cities", "")+editText.getText().toString()+",,");
                            ed.commit();
                            editText.setText("");
                            hideSoftKeyboard();
                            cities = sharedPreferences.getString("cities", "").split(",,");
                            opening=0;
                            opening2=0;
                            mRecyclerView.setVisibility(View.INVISIBLE);
                            try {
                                for (int i=0; i<cities.length; i++) {
                                    new UpdateDatabaseTask().execute(cities[i]);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }else if (editText.getText().length()==0) {
                    fab.setImageResource(R.drawable.ic_baseline_plus_24px);
                    fab.setRotation(0);
                    fab.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            animateFab();
                        }
                    });
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        try {
            for (int i=0; i<cities.length; i++) {
                new UpdateDatabaseTask().execute(cities[i]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void animateFab() {
        if (isOpen) {
            fab.startAnimation(rotateForward);
            consLayText.startAnimation(tvClose);
            consLayText.setClickable(false);
            consLayText.setVisibility(View.GONE);
            constraintLayout.startAnimation(consLayClose);
            constraintLayout.setClickable(false);
            constraintLayout.setVisibility(View.GONE);
            isOpen=false;
        }
        else
        {
            fab.startAnimation(rotateBackward);
            consLayText.startAnimation(tvOpen);
            consLayText.setClickable(true);
            consLayText.setVisibility(View.VISIBLE);
            constraintLayout.setClickable(true);
            constraintLayout.setColorFilter(Color.argb(150, 155, 155, 155),  PorterDuff.Mode.DARKEN);
            constraintLayout.startAnimation(consLayOpen);
            isOpen=true;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_setcity, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        LinearLayout container = new LinearLayout(getApplicationContext());
        container.setOrientation(LinearLayout.VERTICAL);
        for (int i=0; i<cities.length; i++) {
            View holder = getLayoutInflater().inflate(R.layout.settings_city, null, false);
            final TextView textViewCity = (TextView) holder.findViewById(R.id.tvCity);
            textViewCity.setText(cities[i]);
            final ImageView img = holder.findViewById(R.id.ivDelCity);
            img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try{
                        String cities = sharedPreferences.getString("cities", "");
                        String fixed = cities.replace(textViewCity.getText().toString()+",,", "");
                        ed.putString("cities", fixed);
                        ed.commit();
                        String query = "DELETE from " + mDatabaseHelper.DATABASE_WEATHER + " WHERE "+ mDatabaseHelper.WEATHER_FILTERNAME + " = \"" + textViewCity.getText().toString()+"\"";
                        mSqLiteDatabase.execSQL(query);
                        textViewCity.setVisibility(View.GONE);
                        img.setVisibility(View.GONE);
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG);
                    }
                }
            });
            container.addView(holder);
        }
        final AlertDialog builder = new AlertDialog.Builder( this)
                        .setTitle("Введите необходимые данные")
                        .setPositiveButton(android.R.string.ok, null)
                        .setCancelable(false)
                        .setView(container)
                        .create();
        switch (item.getItemId()) {
            case R.id.action_setcity:{
                builder.setOnShowListener(new DialogInterface.OnShowListener() {

                    @Override
                    public void onShow(DialogInterface dialogInterface) {

                        Button button = ((AlertDialog) builder).getButton(AlertDialog.BUTTON_POSITIVE);
                        button.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                try {
                                    builder.dismiss();
                                    MainActivity.this.recreate();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                });

                builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                    }
                });
            }
        }
        builder.show();
        return super.onOptionsItemSelected(item);
    }

    public void getTodayEvents() {
        cursor.moveToFirst();
        cursor.moveToPrevious();
        weather.clear();
        while (cursor.moveToNext()) {
            Weather weathers = new Weather();
            weathers.setWeather_city(cursor.getString(cursor.getColumnIndex(mDatabaseHelper.WEATHER_CITY)));
            weathers.setWeather_now(cursor.getDouble(cursor.getColumnIndex(mDatabaseHelper.WEATHER_NOW)));
            weathers.setWeather_date(cursor.getLong(cursor.getColumnIndex(mDatabaseHelper.WEATHER_DATE)));
            weathers.setWeather_high(cursor.getDouble(cursor.getColumnIndex(mDatabaseHelper.WEATHER_HIGH)));
            weathers.setWeather_low(cursor.getDouble(cursor.getColumnIndex(mDatabaseHelper.WEATHER_LOW)));
            weathers.setWeather_sunrise(cursor.getString(cursor.getColumnIndex(mDatabaseHelper.WEATHER_SUNRISE)));
            weathers.setWeather_sunset(cursor.getString(cursor.getColumnIndex(mDatabaseHelper.WEATHER_SUNSET)));
            weathers.setWeather_text(cursor.getString(cursor.getColumnIndex(mDatabaseHelper.WEATHER_TEXT)));
            weathers.setWeather_description(cursor.getString(cursor.getColumnIndex(mDatabaseHelper.WEATHER_DESCRIPTION)));
            weathers.setWeather_humidity(cursor.getDouble(cursor.getColumnIndex(mDatabaseHelper.WEATHER_HUMIDITY)));
            weathers.setWeather_pressure(cursor.getDouble(cursor.getColumnIndex(mDatabaseHelper.WEATHER_PRESSURE)));
            weathers.setWeather_rising(cursor.getLong(cursor.getColumnIndex(mDatabaseHelper.WEATHER_RISING)));
            weathers.setWeather_visibility(cursor.getDouble(cursor.getColumnIndex(mDatabaseHelper.WEATHER_VISIBILITY)));
            weathers.setWeather_d1_day(cursor.getString(cursor.getColumnIndex(mDatabaseHelper.WEATHER_D1_DAY)));
            weathers.setWeather_d1_high(cursor.getDouble(cursor.getColumnIndex(mDatabaseHelper.WEATHER_D1_HIGH)));
            weathers.setWeather_d1_low(cursor.getDouble(cursor.getColumnIndex(mDatabaseHelper.WEATHER_D1_LOW)));
            weathers.setWeather_d1_text(cursor.getString(cursor.getColumnIndex(mDatabaseHelper.WEATHER_D1_TEXT)));
            weathers.setWeather_d2_day(cursor.getString(cursor.getColumnIndex(mDatabaseHelper.WEATHER_D2_DAY)));
            weathers.setWeather_d2_high(cursor.getDouble(cursor.getColumnIndex(mDatabaseHelper.WEATHER_D2_HIGH)));
            weathers.setWeather_d2_low(cursor.getDouble(cursor.getColumnIndex(mDatabaseHelper.WEATHER_D2_LOW)));
            weathers.setWeather_d2_text(cursor.getString(cursor.getColumnIndex(mDatabaseHelper.WEATHER_D2_TEXT)));
            weathers.setWeather_d3_day(cursor.getString(cursor.getColumnIndex(mDatabaseHelper.WEATHER_D3_DAY)));
            weathers.setWeather_d3_high(cursor.getDouble(cursor.getColumnIndex(mDatabaseHelper.WEATHER_D3_HIGH)));
            weathers.setWeather_d3_low(cursor.getDouble(cursor.getColumnIndex(mDatabaseHelper.WEATHER_D3_LOW)));
            weathers.setWeather_d3_text(cursor.getString(cursor.getColumnIndex(mDatabaseHelper.WEATHER_D3_TEXT)));
            weathers.setWeather_d4_day(cursor.getString(cursor.getColumnIndex(mDatabaseHelper.WEATHER_D4_DAY)));
            weathers.setWeather_d4_high(cursor.getDouble(cursor.getColumnIndex(mDatabaseHelper.WEATHER_D4_HIGH)));
            weathers.setWeather_d4_low(cursor.getDouble(cursor.getColumnIndex(mDatabaseHelper.WEATHER_D4_LOW)));
            weathers.setWeather_d4_text(cursor.getString(cursor.getColumnIndex(mDatabaseHelper.WEATHER_D4_TEXT)));
            weathers.setWeather_d5_day(cursor.getString(cursor.getColumnIndex(mDatabaseHelper.WEATHER_D5_DAY)));
            weathers.setWeather_d5_high(cursor.getDouble(cursor.getColumnIndex(mDatabaseHelper.WEATHER_D5_HIGH)));
            weathers.setWeather_d5_low(cursor.getDouble(cursor.getColumnIndex(mDatabaseHelper.WEATHER_D5_LOW)));
            weathers.setWeather_d5_text(cursor.getString(cursor.getColumnIndex(mDatabaseHelper.WEATHER_D5_TEXT)));
            weathers.setWeather_d6_day(cursor.getString(cursor.getColumnIndex(mDatabaseHelper.WEATHER_D6_DAY)));
            weathers.setWeather_d6_high(cursor.getDouble(cursor.getColumnIndex(mDatabaseHelper.WEATHER_D6_HIGH)));
            weathers.setWeather_d6_low(cursor.getDouble(cursor.getColumnIndex(mDatabaseHelper.WEATHER_D6_LOW)));
            weathers.setWeather_d6_text(cursor.getString(cursor.getColumnIndex(mDatabaseHelper.WEATHER_D6_TEXT)));
            weathers.setWeather_d7_day(cursor.getString(cursor.getColumnIndex(mDatabaseHelper.WEATHER_D7_DAY)));
            weathers.setWeather_d7_high(cursor.getDouble(cursor.getColumnIndex(mDatabaseHelper.WEATHER_D7_HIGH)));
            weathers.setWeather_d7_low(cursor.getDouble(cursor.getColumnIndex(mDatabaseHelper.WEATHER_D7_LOW)));
            weathers.setWeather_d7_text(cursor.getString(cursor.getColumnIndex(mDatabaseHelper.WEATHER_D7_TEXT)));
            weather.add(weathers);
        }
        initializeAdapter();
    }

    private void initializeAdapter() {
        OnItemTouchListener itemTouchListener = new OnItemTouchListener() {
            @Override
            public void onCardViewTap(View view, int position) {
                Intent startTaskInfoActivity = new Intent(MainActivity.this, PodrobnoActivity.class);
                Weather weathers = weather.get(position);
                startTaskInfoActivity.putExtra("weatherCity", weathers.getWeather_city());
                startTaskInfoActivity.putExtra("weatherNow", weathers.getWeather_now());
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
                Date date = new Date(weathers.getWeather_date());
                String dateText = sdf.format(date);
                startTaskInfoActivity.putExtra("weatherDate", dateText);
                startTaskInfoActivity.putExtra("weatherHigh", weathers.getWeather_high());
                startTaskInfoActivity.putExtra("weatherLow", weathers.getWeather_low());
                startTaskInfoActivity.putExtra("weatherSunrise", weathers.getWeather_sunrise());
                startTaskInfoActivity.putExtra("weatherSunset", weathers.getWeather_sunset());
                startTaskInfoActivity.putExtra("weatherDescription", weathers.getWeather_description());
                startTaskInfoActivity.putExtra("weatherText", weathers.getWeather_text());
                startTaskInfoActivity.putExtra("weatherHumidity", weathers.getWeather_humidity());
                startTaskInfoActivity.putExtra("weatherPressure", weathers.getWeather_pressure());
                startTaskInfoActivity.putExtra("weatherRising", weathers.getWeather_rising());
                startTaskInfoActivity.putExtra("weatherVisibility", weathers.getWeather_visibility());
                startTaskInfoActivity.putExtra("weatherD1day", weathers.getWeather_d1_day());
                startTaskInfoActivity.putExtra("weatherD1high", weathers.getWeather_d1_high());
                startTaskInfoActivity.putExtra("weatherD1low", weathers.getWeather_d1_low());
                startTaskInfoActivity.putExtra("weatherD1text", weathers.getWeather_d1_text());
                startTaskInfoActivity.putExtra("weatherD2day", weathers.getWeather_d2_day());
                startTaskInfoActivity.putExtra("weatherD2high", weathers.getWeather_d2_high());
                startTaskInfoActivity.putExtra("weatherD2low", weathers.getWeather_d2_low());
                startTaskInfoActivity.putExtra("weatherD2text", weathers.getWeather_d2_text());
                startTaskInfoActivity.putExtra("weatherD3day", weathers.getWeather_d3_day());
                startTaskInfoActivity.putExtra("weatherD3high", weathers.getWeather_d3_high());
                startTaskInfoActivity.putExtra("weatherD3low", weathers.getWeather_d3_low());
                startTaskInfoActivity.putExtra("weatherD3text", weathers.getWeather_d3_text());
                startTaskInfoActivity.putExtra("weatherD4day", weathers.getWeather_d4_day());
                startTaskInfoActivity.putExtra("weatherD4high", weathers.getWeather_d4_high());
                startTaskInfoActivity.putExtra("weatherD4low", weathers.getWeather_d4_low());
                startTaskInfoActivity.putExtra("weatherD4text", weathers.getWeather_d4_text());
                startTaskInfoActivity.putExtra("weatherD5day", weathers.getWeather_d5_day());
                startTaskInfoActivity.putExtra("weatherD5high", weathers.getWeather_d5_high());
                startTaskInfoActivity.putExtra("weatherD5low", weathers.getWeather_d5_low());
                startTaskInfoActivity.putExtra("weatherD5text", weathers.getWeather_d5_text());
                startTaskInfoActivity.putExtra("weatherD6day", weathers.getWeather_d6_day());
                startTaskInfoActivity.putExtra("weatherD6high", weathers.getWeather_d6_high());
                startTaskInfoActivity.putExtra("weatherD6low", weathers.getWeather_d6_low());
                startTaskInfoActivity.putExtra("weatherD6text", weathers.getWeather_d6_text());
                startTaskInfoActivity.putExtra("weatherD7day", weathers.getWeather_d7_day());
                startTaskInfoActivity.putExtra("weatherD7high", weathers.getWeather_d7_high());
                startTaskInfoActivity.putExtra("weatherD7low", weathers.getWeather_d7_low());
                startTaskInfoActivity.putExtra("weatherD7text", weathers.getWeather_d7_text());
                startActivity(startTaskInfoActivity);
            }

            @Override
            public void onButtonCvMenuClick(View view, int position) {

            }
        };
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(getApplicationContext(), weather, itemTouchListener);
        mRecyclerView.setAdapter(recyclerViewAdapter);
    }

    public boolean tableExists(SQLiteDatabase db, String tableName)
    {
        if (tableName == null || db == null || !db.isOpen())
        {
            return false;
        }
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM sqlite_master WHERE type = ? AND name = ?", new String[] {"table", tableName});
        if (!cursor.moveToFirst())
        {
            cursor.close();
            return false;
        }
        int count = cursor.getInt(0);
        cursor.close();
        return count > 0;
    }

    class UpdateDatabaseTask extends AsyncTask<String, Void, Void> {
        int responseCode;

        @Override
        protected void onPreExecute() {
            if (opening==0) {
                dialog = new ProgressDialog(MainActivity.this);
                dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                dialog.setMessage("Синхронизация…");
                dialog.setCancelable(false);
                dialog.show();
                opening++;
            }
        }

        @Override
        protected Void doInBackground(String...strings) {
            try {
                responseCode = httpServiceHelper.getTasks(mSqLiteDatabase, strings[0]);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            opening2++;
            if (opening2==cities.length) {
                String query = "SELECT " + DatabaseHelper.WEATHER_FILTERNAME + ", " + DatabaseHelper.WEATHER_DATE + ", " + DatabaseHelper.WEATHER_CITY + ", " + DatabaseHelper.WEATHER_NOW + ", "
                        + DatabaseHelper.WEATHER_HIGH + " , " + DatabaseHelper.WEATHER_LOW + ", " + DatabaseHelper.WEATHER_TEXT + ", " + DatabaseHelper.WEATHER_DESCRIPTION + ", "
                        + DatabaseHelper.WEATHER_HUMIDITY + ", " + DatabaseHelper.WEATHER_PRESSURE + ", " + DatabaseHelper.WEATHER_RISING + ", " + DatabaseHelper.WEATHER_VISIBILITY + ", "
                        + DatabaseHelper.WEATHER_SUNRISE + ", " + DatabaseHelper.WEATHER_SUNSET + ", " + DatabaseHelper.WEATHER_D1_DAY + ", " + DatabaseHelper.WEATHER_D1_HIGH + ", "
                        + DatabaseHelper.WEATHER_D1_LOW + ", " + DatabaseHelper.WEATHER_D1_TEXT + ", " + DatabaseHelper.WEATHER_D2_DAY + ", " + DatabaseHelper.WEATHER_D2_HIGH + ", "
                        + DatabaseHelper.WEATHER_D2_LOW + ", " + DatabaseHelper.WEATHER_D2_TEXT + ", " + DatabaseHelper.WEATHER_D3_DAY + ", " + DatabaseHelper.WEATHER_D3_HIGH + ", "
                        + DatabaseHelper.WEATHER_D3_LOW + ", " + DatabaseHelper.WEATHER_D3_TEXT + ", " + DatabaseHelper.WEATHER_D4_DAY + ", " + DatabaseHelper.WEATHER_D4_HIGH + ", "
                        + DatabaseHelper.WEATHER_D4_LOW + ", " + DatabaseHelper.WEATHER_D4_TEXT + ", " + DatabaseHelper.WEATHER_D5_DAY + ", " + DatabaseHelper.WEATHER_D5_HIGH + ", "
                        + DatabaseHelper.WEATHER_D5_LOW + ", " + DatabaseHelper.WEATHER_D5_TEXT + ", " + DatabaseHelper.WEATHER_D6_DAY + ", " + DatabaseHelper.WEATHER_D6_HIGH + ", "
                        + DatabaseHelper.WEATHER_D6_LOW + ", " + DatabaseHelper.WEATHER_D6_TEXT + ", " + DatabaseHelper.WEATHER_D7_DAY + ", " + DatabaseHelper.WEATHER_D7_HIGH + ", "
                        + DatabaseHelper.WEATHER_D7_LOW + ", " + DatabaseHelper.WEATHER_D7_TEXT + " FROM " + DatabaseHelper.DATABASE_WEATHER;
                cursor = mSqLiteDatabase.rawQuery(query, new String[0]);
                getTodayEvents();
                dialog.dismiss();
                mRecyclerView.setVisibility(View.VISIBLE);
                constraintLayout.setVisibility(View.GONE);
            }
        }
    }

    public void showSoftKeyboard() {
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.toggleSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.SHOW_FORCED, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void hideSoftKeyboard() {
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
