package com.zebronics.remote;

import android.app.Activity;
import android.content.Context;
import android.hardware.ConsumerIrManager;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private ConsumerIrManager irManager;
    private Vibrator vibrator;

    private static final int IR_FREQUENCY = 38000;

    private static final int[] POWER    = {9024,4512,564,564,564,564,564,1692,564,564,564,564,564,564,564,564,564,564,564,1692,564,1692,564,1692,564,1692,564,1692,564,1692,564,564,564,564,564,1692,564,1692,564,564,564,564,564,564,564,564,564,564,564,564,564,1692,564,1692,564,1692,564,1692,564,1692,564,1692,564,564,564,564,564,564,564,44268};
    private static final int[] VOL_UP   = {9024,4512,564,564,564,564,564,1692,564,564,564,564,564,564,564,564,564,564,564,1692,564,1692,564,1692,564,1692,564,1692,564,1692,564,1692,564,564,564,564,564,564,564,1692,564,564,564,564,564,564,564,564,564,1692,564,1692,564,1692,564,1692,564,1692,564,564,564,564,564,564,564,564,564,44268};
    private static final int[] VOL_DOWN = {9024,4512,564,564,564,564,564,1692,564,564,564,564,564,564,564,564,564,564,564,1692,564,1692,564,1692,564,1692,564,1692,564,1692,564,564,564,1692,564,564,564,564,564,1692,564,564,564,564,564,564,564,564,564,1692,564,564,564,1692,564,1692,564,1692,564,1692,564,1692,564,1692,564,564,564,44268};
    private static final int[] MUTE     = {9024,4512,564,564,564,564,564,1692,564,564,564,564,564,564,564,564,564,564,564,1692,564,1692,564,1692,564,1692,564,1692,564,1692,564,1692,564,1692,564,564,564,564,564,564,564,564,564,564,564,564,564,564,564,564,564,1692,564,1692,564,1692,564,1692,564,1692,564,1692,564,1692,564,1692,564,44268};
    private static final int[] UP       = {9024,4512,564,564,564,564,564,1692,564,564,564,564,564,564,564,564,564,564,564,1692,564,1692,564,1692,564,1692,564,1692,564,1692,564,564,564,564,564,564,564,564,564,564,564,1692,564,564,564,564,564,564,564,564,564,1692,564,1692,564,1692,564,1692,564,564,564,1692,564,1692,564,1692,564,44268};
    private static final int[] DOWN     = {9024,4512,564,564,564,564,564,1692,564,564,564,564,564,564,564,564,564,564,564,1692,564,1692,564,1692,564,1692,564,1692,564,1692,564,564,564,1692,564,564,564,564,564,564,564,1692,564,564,564,564,564,564,564,564,564,1692,564,564,564,1692,564,1692,564,1692,564,1692,564,564,564,1692,564,44268};
    private static final int[] LEFT     = {9024,4512,564,564,564,564,564,1692,564,564,564,564,564,564,564,564,564,564,564,1692,564,1692,564,1692,564,1692,564,1692,564,1692,564,564,564,564,564,1692,564,564,564,564,564,1692,564,564,564,564,564,564,564,564,564,1692,564,1692,564,564,564,1692,564,1692,564,1692,564,1692,564,564,564,44268};
    private static final int[] RIGHT    = {9024,4512,564,564,564,564,564,1692,564,564,564,564,564,564,564,564,564,564,564,1692,564,1692,564,1692,564,1692,564,1692,564,1692,564,564,564,1692,564,1692,564,564,564,564,564,1692,564,564,564,564,564,564,564,564,564,1692,564,564,564,564,564,1692,564,1692,564,1692,564,1692,564,564,564,44268};
    private static final int[] OK       = {9024,4512,564,564,564,564,564,1692,564,564,564,564,564,564,564,564,564,564,564,1692,564,1692,564,1692,564,1692,564,1692,564,1692,564,564,564,564,564,1692,564,1692,564,564,564,1692,564,564,564,564,564,564,564,564,564,1692,564,1692,564,564,564,564,564,1692,564,1692,564,1692,564,564,564,44268};
    private static final int[] BACK     = {9024,4512,564,564,564,564,564,1692,564,564,564,564,564,564,564,564,564,564,564,1692,564,1692,564,1692,564,1692,564,1692,564,1692,564,564,564,564,564,564,564,1692,564,1692,564,1692,564,564,564,564,564,564,564,564,564,1692,564,1692,564,1692,564,564,564,564,564,1692,564,1692,564,564,564,44268};
    private static final int[] HOME     = {9024,4512,564,564,564,564,564,1692,564,564,564,564,564,564,564,564,564,564,564,1692,564,1692,564,1692,564,1692,564,1692,564,1692,564,1692,564,564,564,564,564,1692,564,1692,564,1692,564,564,564,564,564,564,564,564,564,564,564,1692,564,1692,564,564,564,564,564,1692,564,1692,564,564,564,44268};
    private static final int[] MENU     = {9024,4512,564,564,564,564,564,1692,564,564,564,564,564,564,564,564,564,564,564,1692,564,1692,564,1692,564,1692,564,1692,564,1692,564,564,564,1692,564,564,564,1692,564,1692,564,1692,564,564,564,564,564,564,564,564,564,1692,564,564,564,1692,564,564,564,564,564,1692,564,1692,564,564,564,44268};
    private static final int[] SOURCE   = {9024,4512,564,564,564,564,564,1692,564,564,564,564,564,564,564,564,564,564,564,1692,564,1692,564,1692,564,1692,564,1692,564,1692,564,1692,564,1692,564,1692,564,564,564,564,564,564,564,564,564,564,564,564,564,564,564,564,564,564,564,564,564,1692,564,1692,564,1692,564,1692,564,1692,564,44268};
    private static final int[] ASPECT   = {9024,4512,564,564,564,564,564,1692,564,564,564,564,564,564,564,564,564,564,564,1692,564,1692,564,1692,564,1692,564,1692,564,1692,564,564,564,564,564,564,564,564,564,564,564,564,564,1692,564,564,564,564,564,564,564,1692,564,1692,564,1692,564,1692,564,1692,564,564,564,1692,564,1692,564,44268};
    private static final int[] NETFLIX  = {9024,4512,564,564,564,564,564,1692,564,564,564,564,564,564,564,564,564,564,564,1692,564,1692,564,1692,564,1692,564,1692,564,1692,564,564,564,564,564,1692,564,564,564,1692,564,564,564,564,564,564,564,1692,564,564,564,1692,564,1692,564,564,564,1692,564,1692,564,1692,564,564,564,1692,564,44268};
    private static final int[] YOUTUBE  = {9024,4512,564,564,564,564,564,1692,564,564,564,564,564,564,564,564,564,564,564,1692,564,1692,564,1692,564,1692,564,1692,564,1692,564,564,564,1692,564,1692,564,564,564,1692,564,564,564,564,564,564,564,1692,564,564,564,1692,564,564,564,564,564,1692,564,1692,564,1692,564,564,564,1692,564,44268};
    private static final int[] PRIME    = {9024,4512,564,564,564,564,564,1692,564,564,564,564,564,564,564,564,564,564,564,1692,564,1692,564,1692,564,1692,564,1692,564,1692,564,1692,564,1692,564,1692,564,1692,564,564,564,564,564,564,564,564,564,564,564,564,564,564,564,564,564,564,1692,564,1692,564,1692,564,1692,564,1692,564,44268};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            setContentView(R.layout.activity_main);

            irManager = (ConsumerIrManager) getSystemService(Context.CONSUMER_IR_SERVICE);
            vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

            TextView statusText = findViewById(R.id.statusText);
            if (statusText != null) {
                if (irManager != null && irManager.hasIrEmitter()) {
                    statusText.setText("IR Blaster Ready - Point top of phone at projector");
                    statusText.setTextColor(0xFF00D4FF);
                } else {
                    statusText.setText("No IR Blaster detected on this device");
                    statusText.setTextColor(0xFFFF4444);
                }
            }

            setBtn(R.id.btnPower,   POWER,    "Power");
            setBtn(R.id.btnMute,    MUTE,     "Mute");
            setBtn(R.id.btnSource,  SOURCE,   "Source");
            setBtn(R.id.btnAspect,  ASPECT,   "Aspect Ratio");
            setBtn(R.id.btnUp,      UP,       "Up");
            setBtn(R.id.btnDown,    DOWN,     "Down");
            setBtn(R.id.btnLeft,    LEFT,     "Left");
            setBtn(R.id.btnRight,   RIGHT,    "Right");
            setBtn(R.id.btnOK,      OK,       "OK");
            setBtn(R.id.btnBack,    BACK,     "Back");
            setBtn(R.id.btnHome,    HOME,     "Home");
            setBtn(R.id.btnMenu,    MENU,     "Menu");
            setBtn(R.id.btnVolUp,   VOL_UP,   "Volume Up");
            setBtn(R.id.btnVolDown, VOL_DOWN, "Volume Down");
            setBtn(R.id.btnNetflix, NETFLIX,  "Netflix");
            setBtn(R.id.btnYoutube, YOUTUBE,  "YouTube");
            setBtn(R.id.btnPrime,   PRIME,    "Prime Video");

        } catch (Exception e) {
            Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void setBtn(int id, final int[] code, final String label) {
        View v = findViewById(id);
        if (v == null) return;
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendIR(code, label);
            }
        });
    }

    private void sendIR(int[] pattern, String label) {
        try {
            if (irManager != null && irManager.hasIrEmitter()) {
                irManager.transmit(IR_FREQUENCY, pattern);
            }
            if (vibrator != null) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    vibrator.vibrate(VibrationEffect.createOneShot(40, VibrationEffect.DEFAULT_AMPLITUDE));
                } else {
                    vibrator.vibrate(40);
                }
            }
            Toast.makeText(this, label, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, "IR Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
