package ru.bonbon.pain_t;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {
    private MyView myView;
    private SeekBar seekBar;
    private ImageButton whiteButton, blackButton, blueButton, redButton, greenButton, yellowButton;
    public TextView penSize;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        myView = findViewById(R.id.myview);
        seekBar = findViewById(R.id.seekbar);
        seekBar.setProgress(MyView.defaultStrokeWidth);
        whiteButton = findViewById(R.id.white_button);
        whiteButton.setOnClickListener(this);
        redButton = findViewById(R.id.red_button);
        redButton.setOnClickListener(this);
        greenButton = findViewById(R.id.green_button);
        greenButton.setOnClickListener(this);
        blueButton = findViewById(R.id.blue_button);
        blueButton.setOnClickListener(this);
        blackButton = findViewById(R.id.black_button);
        blackButton.setOnClickListener(this);
        yellowButton = findViewById(R.id.yellow_button);
        yellowButton.setOnClickListener(this);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        myView.init(displayMetrics);
        penSize = findViewById(R.id.pen_size);
        penSize.setText(String.valueOf(seekBar.getProgress()));
        seekBar.setOnSeekBarChangeListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.white_button:
                MyView.setCurrentColor(ContextCompat.getColor(this, R.color.white));
                Log.d("tag", MyView.alpha+Integer.toHexString(MyView.currentColor));
                break;
            case R.id.black_button:
                MyView.setCurrentColor(ContextCompat.getColor(this, R.color.black));
                Log.d("tag", Integer.toHexString(MyView.currentColor));
                break;
            case R.id.blue_button:
                MyView.setCurrentColor(ContextCompat.getColor(this, R.color.blue));
                Log.d("tag", Integer.toHexString(MyView.currentColor));
                break;
            case R.id.green_button:
                MyView.setCurrentColor(ContextCompat.getColor(this, R.color.green));
                Log.d("tag", Integer.toHexString(MyView.currentColor));
                break;
            case R.id.red_button:
                MyView.setCurrentColor(ContextCompat.getColor(this, R.color.red));
                Log.d("tag", Integer.toHexString(MyView.currentColor));
                break;
            case R.id.yellow_button:
                MyView.setCurrentColor(ContextCompat.getColor(this, R.color.yellow));
                Log.d("tag", Integer.toHexString(MyView.currentColor));
                break;
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        MyView.setStrokeWidth(i);
        penSize.setText(String.valueOf(i));
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}