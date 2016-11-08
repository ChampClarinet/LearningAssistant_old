package com.example.clarinetmaster.learningassistant;

import android.app.Activity;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.clarinetmaster.learningassistant.DB.dbHelper;
import com.example.clarinetmaster.learningassistant.Info.Time;
import com.example.clarinetmaster.learningassistant.Info.uncompleteFieldAlert;
import com.example.clarinetmaster.learningassistant.Info.weekday;

import java.util.ArrayList;

import static com.example.clarinetmaster.learningassistant.Info.Time.HOUR;
import static com.example.clarinetmaster.learningassistant.Info.Time.MINUTE;

public class AddCourseActivity extends AppCompatActivity {

    private static final String TAG = "ADD_COURSE_ACTIVITY";
    private EditText newCourseName;
    private Spinner learnDaySpinner;
    private Spinner testDaySpinner;
    private Spinner startLHourSpinner;
    private Spinner startLMinSpinner;
    private Spinner finishLHourSpinner;
    private Spinner finishLMinSpinner;
    private Spinner startTHourSpinner;
    private Spinner startTMinSpinner;
    private Spinner finishTHourSpinner;
    private Spinner finishTMinSpinner;
    private EditText courseDesc;

    private String courseDay;
    private String testDay;
    private String courseStartH;
    private String courseStartM;
    private String courseFinishH;
    private String courseFinishM;
    private String testStartH;
    private String testStartM;
    private String testFinishH;
    private String testFinishM;

    private dbHelper mHelper;
    private SQLiteDatabase db;

    private ContentValues cv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);

        mHelper = new dbHelper(this);
        db = mHelper.getWritableDatabase();

        findViewByIDs();
        setAdapter();
        setListener();

    }

    private void findViewByIDs(){
        newCourseName = (EditText) findViewById(R.id.newCourseName);
        learnDaySpinner = (Spinner) findViewById(R.id.learnDaySpinner);
        testDaySpinner = (Spinner) findViewById(R.id.testDaySpinner);

        startLHourSpinner = (Spinner) findViewById(R.id.startLHour);
        startLMinSpinner = (Spinner) findViewById(R.id.startLMin);
        finishLHourSpinner = (Spinner) findViewById(R.id.finishLHour);
        finishLMinSpinner = (Spinner) findViewById(R.id.finishLMin);

        startTHourSpinner = (Spinner) findViewById(R.id.startTHour);
        startTMinSpinner = (Spinner) findViewById(R.id.startTMin);
        finishTHourSpinner = (Spinner) findViewById(R.id.finishTHour);
        finishTMinSpinner = (Spinner) findViewById(R.id.finishTMin);

        courseDesc = (EditText) findViewById(R.id.courseDesc);

    }

    private void setListener() {

        findViewById(R.id.activity_add_course).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                return true;
            }
        });

        newCourseName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        });

        courseDesc.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    InputMethodManager inputMethodManager = (InputMethodManager) getSystemService((Activity.INPUT_METHOD_SERVICE));
                    inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        });

        learnDaySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                courseDay = learnDaySpinner.getSelectedItem().toString();
                Log.i("courseDay", courseDay);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        testDaySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                testDay = testDaySpinner.getSelectedItem().toString();
                Log.i("testDay", testDay);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        startLHourSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                courseStartH = startLHourSpinner.getSelectedItem().toString();
                Log.i("courseStartH", courseStartH);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        startLMinSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                courseStartM = startLMinSpinner.getSelectedItem().toString();
                Log.i("courseStartM", courseStartM);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        finishLHourSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                courseFinishH = finishLHourSpinner.getSelectedItem().toString();
                Log.i("courseFinishH", courseFinishH);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        finishLMinSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                courseFinishM = finishLMinSpinner.getSelectedItem().toString();
                Log.i("courseFinishM", courseFinishM);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        startTHourSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                testStartH = startTHourSpinner.getSelectedItem().toString();
                Log.i("testStartH", testStartH);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        startTMinSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                testStartM = startTMinSpinner.getSelectedItem().toString();
                Log.i("testStartM", testStartM);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        finishTHourSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                testFinishH = finishTHourSpinner.getSelectedItem().toString();
                Log.i("testFinishH", testFinishH);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        finishTMinSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                testFinishM = finishTMinSpinner.getSelectedItem().toString();
                Log.i("testFinishM", testFinishM);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void setAdapter(){
        ArrayAdapter<String> adapterD = new ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, day());
        ArrayAdapter<String> adapterH = new ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, HOUR());
        ArrayAdapter<String> adapterM = new ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, MINUTE());

        learnDaySpinner.setAdapter(adapterD);
        learnDaySpinner.setSelection(0);

        testDaySpinner.setAdapter(adapterD);
        testDaySpinner.setSelection(0);

        startLHourSpinner.setAdapter(adapterH);
        startLHourSpinner.setSelection(0);

        startLMinSpinner.setAdapter(adapterM);
        startLMinSpinner.setSelection(0);

        finishLHourSpinner.setAdapter(adapterH);
        finishLHourSpinner.setSelection(0);

        finishLMinSpinner.setAdapter(adapterM);
        finishLMinSpinner.setSelection(0);

        startTHourSpinner.setAdapter(adapterH);
        startTHourSpinner.setSelection(0);

        startTMinSpinner.setAdapter(adapterM);
        startTMinSpinner.setSelection(0);

        finishTHourSpinner.setAdapter(adapterH);
        finishTHourSpinner.setSelection(0);

        finishTMinSpinner.setAdapter(adapterM);
        finishTMinSpinner.setSelection(0);

    }

    private void addToDB(){
        Log.i(TAG, "inserting");
        db.insert(dbHelper.TBLCOURSE, null, cv);
        finish();
    }

    private void encapData() {
        String courseName = newCourseName.getText().toString();
        if(courseName.length() == 0) new uncompleteFieldAlert(this).alert();
        String courseDescS = courseDesc.getText().toString();
        if(courseDescS.length() == 0) courseDescS = null;
        String learnS = Time.timeJoiner(courseStartH, courseStartM);
        String learnF = Time.timeJoiner(courseFinishH, courseFinishM);
        String testS = Time.timeJoiner(testStartH, testStartM);
        String testF = Time.timeJoiner(testFinishH, testFinishM);

        Log.i(TAG, courseName+"\n"+
                courseDay+"\n"+
                learnS+" "+learnF+"\n"+
                testDay+"\n"+
                testS+" "+testF+"\n"+
                courseDescS
        );

        cv = new ContentValues();
        cv.put(dbHelper.COLCOURSENAME, courseName);
        cv.put(dbHelper.COLCOURSEDAY, courseDay);
        cv.put(dbHelper.COLCOURSESTART, learnS);
        cv.put(dbHelper.COLCOURSEEND, learnF);
        cv.put(dbHelper.COLTESTDAY, testDay);
        cv.put(dbHelper.COLTESTSTART, testS);
        cv.put(dbHelper.COLTESTFINISH, testF);
        cv.put(dbHelper.COLCOURSEDESC, courseDescS);

        Log.i(dbHelper.COLCOURSENAME, courseName);
        Log.i(dbHelper.COLCOURSEDAY, courseDay);
        Log.i(dbHelper.COLCOURSESTART, learnS);
        Log.i(dbHelper.COLCOURSEEND, learnF);
        Log.i(dbHelper.COLTESTDAY, testDay);
        Log.i(dbHelper.COLTESTSTART, testS);
        Log.i(dbHelper.COLTESTFINISH, testF);
        Log.i(dbHelper.COLCOURSEDESC, courseDescS);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.course_add_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_add_course) {
            Log.i(TAG, "save menu triggered, adding to DB");
            encapData();
            confirmation();
            //addToDB(); //in case no confirmation
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void confirmation() {
        Log.i(TAG, "confirmMethod");
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle(R.string.confirm);
        dialog.setMessage(getResources().getString(R.string.conMessage)+"\n"+printData());
        dialog.setPositiveButton(R.string.yesButton, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.i(TAG, "PositiveDialogButton triggered");
                addToDB();
                dialog.dismiss();
            }
        });
        dialog.setNegativeButton(R.string.backButton, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.i(TAG, "NegativeDialogButton triggered");
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private String printData() {
        String s = "Adding course "+cv.getAsString(dbHelper.COLCOURSENAME)+"\n"+
                "learn on "+cv.getAsString(dbHelper.COLCOURSEDAY)+" "+cv.getAsString(dbHelper.COLCOURSESTART)+" - "+cv.getAsString(dbHelper.COLCOURSEEND)+"\n"+
                "test on "+cv.getAsString(dbHelper.COLTESTDAY)+" "+cv.getAsString(dbHelper.COLTESTSTART)+" - "+cv.getAsString(dbHelper.COLTESTFINISH)+"\n"+
                "Description : "+cv.getAsString(dbHelper.COLCOURSEDESC);
        return s;
    }

    private ArrayList<String> day(){
        ArrayList<String> s = new ArrayList<>();
        for(int x: weekday.getArrayWeekDay()) s.add(getResources().getString(x));
        return s;
    }

}
