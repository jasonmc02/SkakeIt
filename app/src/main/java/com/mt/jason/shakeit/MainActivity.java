package com.mt.jason.shakeit;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private ShakeDetector mShakeDetector;

    private EditText edtOne;
    private EditText edtTwo;
    private Button btnOne;
    private Button btnTwo;
    private Button btnThr;
    private TextView txtOne;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager
                .getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mShakeDetector = new ShakeDetector();
        mShakeDetector.setOnShakeListener(new ShakeDetector.OnShakeListener() {

            @Override
            public void onShake(int count) {
                /*
                 * The following method, "handleShakeEvent(count):" is a stub //
				 * method you would use to setup whatever you want done once the
				 * device has been shook.
				 */
//                handleShakeEvent(count);
                Toast.makeText(getApplicationContext(), "Hola Jennifer <3", Toast.LENGTH_LONG).show();
            }
        });


        this.edtOne = (EditText)findViewById(R.id.editText);
        this.edtTwo = (EditText)findViewById(R.id.editText2);
        this.txtOne = (TextView)findViewById(R.id.textView);

        this.btnOne = (Button) findViewById(R.id.button);
        this.btnOne.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int result = Integer.parseInt(edtOne.getText().toString()) +  Integer.parseInt(edtTwo.getText().toString());
                txtOne.setText(String.valueOf(result));
            }
        });
        this.btnTwo = (Button)findViewById(R.id.button2);
        this.btnTwo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int result = Integer.parseInt(edtOne.getText().toString()) -  Integer.parseInt(edtTwo.getText().toString());
                txtOne.setText(String.valueOf(result));
            }
        });
        this.btnThr = (Button)findViewById(R.id.button3);
        this.btnThr.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int result = Integer.parseInt(edtOne.getText().toString()) /  Integer.parseInt(edtTwo.getText().toString());
                txtOne.setText(String.valueOf(result));
            }
        });
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume() {
        super.onResume();
        // Add the following line to register the Session Manager Listener onResume
        mSensorManager.registerListener(mShakeDetector, mAccelerometer, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    public void onPause() {
        // Add the following line to unregister the Sensor Manager onPause
        mSensorManager.unregisterListener(mShakeDetector);
        super.onPause();
    }
}
