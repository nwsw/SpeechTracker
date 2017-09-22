package net.nwsw.speechtracker;

import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DisplaySpeechActivity extends AppCompatActivity {
    private Long startTime;
    private CountDownTimer cdt;
    private boolean isDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_speech);
        startTime = System.currentTimeMillis();
        isDone = false;
        cdt = new CountDownTimer(999999, 1000) {
            public void onTick(long millisUntilFinished) {
                TextView txt = (TextView) findViewById(R.id.counter_Clock);
                long d = (System.currentTimeMillis() - startTime)/1000;
                String t = String.format("%02d:%02d",d/60,d%60);
                txt.setText(t);
            }
            public void onFinish() {
            }
        };

        cdt.start();
    }

    protected void onDestroyView() {
        cdt.cancel();
    }

    public void doDoubles(View view) {
        TextView txt = (TextView) findViewById(R.id.counter_Doubles);
        String sv = txt.getText().toString();
        int n = Integer.parseInt(sv) + 1;
        txt.setText(Integer.toString(n));
    }

    public void doUms(View view) {
        TextView txt = (TextView) findViewById(R.id.counter_Uhs);
        String sv = txt.getText().toString();
        int n = Integer.parseInt(sv) + 1;
        txt.setText(Integer.toString(n));
    }

    public void doStop(View view) {
        if (!isDone) {
            cdt.cancel();
            String strDone = "Done";
            Button btn = (Button) findViewById(R.id.btn_Stop);
            btn.setText(strDone);
            btn.setTextColor(Color.BLACK);
            view.setKeepScreenOn(false);
            isDone = true;
        } else {
            finish();
        }
    }
}
