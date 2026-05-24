package com.zebronics.remote;

import android.app.Activity;
import android.content.Context;
import android.hardware.ConsumerIrManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private ConsumerIrManager irManager;
    private Vibrator vibrator;

    // Zebronics Pixaplay IR Frequency
    private static final int IR_FREQUENCY = 38000;

    // IR Codes for Zebronics Pixaplay Projector (NEC protocol, 38kHz)
    private static final int[] POWER     = {9024,4512,564,564,564,564,564,1692,564,564,564,564,564,564,564,564,564,564,564,1692,564,1692,564,1692,564,1692,564,1692,564,1692,564,564,564,564,564,1692,564,1692,564,564,564,564,564,564,564,564,564,564,564,564,564,1692,564,1692,564,1692,564,1692,564,1692,564,1692,564,564,564,564,564,564,564,44268};
    private static final int[] VOL_UP    = {9024,4512,564,564,564,564,564,1692,564,564,564,564,564,564,564,564,564,564,564,1692,564,1692,564,1692,564,1692,564,1692,564,1692,564,1692,564,564,564,564,564,564,564,1692,564,564,564,564,564,564,564,564,564,1692,564,1692,564,1692,564,1692,564,1692,564,564,564,564,564,564,564,564,564,44268};
    private static final int[] VOL_DOWN  = {9024,4512,564,564,564,564,564,1692,564,564,564,564,564,564,564,564,564,564,564,1692,564,1692,564,1692,564,1692,564,1692,564,1692,564,564,564,1692,564,564,564,564,564,1692,564,564,564,564,564,564,564,564,564,1692,564,564,564,1692,564,1692,564,1692,564,1692,564,1692,564,1692,564,564,564,44268};
    private static final int[] MUTE      = {9024,4512,564,564,564,564,564,1692,564,564,564,564,564,564,564,564,564,564,564,1692,564,1692,564,1692,564,1692,564,1692,564,1692,564,1692,564,1692,564,564,564,564,564,564,564,564,564,564,564,564,564,564,564,564,564,1692,564,1692,564,1692,564,1692,564,1692,564,1692,564,1692,564,1692,564,44268};
    private static final int[] UP        = {9024,4512,564,564,564,564,564,1692,564,564,564,564,564,564,564,564,564,564,564,1692,564,1692,564,1692,564,1692,564,1692,564,1692,564,564,564,564,564,564,564,564,564,564,564,1692,564,564,564,564,564,564,564,564,564,1692,564,1692,564,1692,564,1692,564,564,564,1692,564,1692,564,1692,564,44268};
    private static final int[] DOWN      = {9024,4512,564,564,564,564,564,1692,564,564,564,564,564,564,564,564,564,564,564,1692,564,1692,564,1692,564,1692,564,1692,564,1692,564,564,564,1692,564,564,564,564,564,564,564,1692,564,564,564,564,564,564,564,564,564,1692,564,564,564,1692,564,1692,564,1692,564,1692,564,564,564,1692,564,44268};
    private static final int[] LEFT      = {9024,4512,564,564,564,564,564,1692,564,564,564,564,564,564,564,564,564,564,564,1692,564,1692,564,1692,564,1692,564,1692,564,1692,564,564,564,564,564,1692,564,564,564,564,564,1692,564,564,564,564,564,564,564,564,564,1692,564,1692,564,564,564,1692,564,1692,564,1692,564,1692,564,564,564,44268};
    private static final int[] RIGHT     = {9024,4512,564,564,564,564,564,1692,564,564,564,564,564,564,564,564,564,564,564,1692,564,1692,564,1692,564,1692,564,1692,564,1692,564,564,564,1692,564,1692,564,564,564,564,564,1692,564,564,564,564,564,564,564,564,564,1692,564,564,564,564,564,1692,564,1692,564,1692,564,1692,564,564,564,44268};
    private static final int[] OK        = {9024,4512,564,564,564,564,564,1692,564,564,564,564,564,564,564,564,564,564,564,1692,564,1692,564,1692,564,1692,564,1692,564,1692,564,564,564,564,564,1692,564,1692,564,564,564,1692,564,564,564,564,564,564,564,564,564,1692,564,1692,564,564,564,564,564,1692,564,1692,564,1692,564,564,564,44268};
    private static final int[] BACK      = {9024,4512,564,564,564,564,564,1692,564,564,564,564,564,564,564,564,564,564,564,1692,564,1692,564,1692,564,1692,564,1692,564,1692,564,564,564,564,564,564,564,1692,564,1692,564,1692,564,564,564,564,564,564,564,564,564,1692,564,1692,564,1692,564,564,564,564,564,1692,564,1692,564,564,564,44268};
    private static final int[] HOME      = {9024,4512,564,564,564,564,564,1692,564,564,564,564,564,564,564,564,564,564,564,1692,564,1692,564,1692,564,1692,564,1692,564,1692,564,1692,564,564,564,564,564,1692,564,1692,564,1692,564,564,564,564,564,564,564,564,564,564,564,1692,564,1692,564,564,564,564,564,1692,564,1692,564,564,564,44268};
    private static final int[] MENU      = {9024,4512,564,564,564,564,564,1692,564,564,564,564,564,564,564,564,564,564,564,1692,564,1692,564,1692,564,1692,564,1692,564,1692,564,564,564,1692,564,564,564,1692,564,1692,564,1692,564,564,564,564,564,564,564,564,564,1692,564,564,564,1692,564,564,564,564,564,1692,564,1692,564,564,564,44268};
    private static final int[] SOURCE    = {9024,4512,564,564,564,564,564,1692,564,564,564,564,564,564,564,564,564,564,564,1692,564,1692,564,1692,564,1692,564,1692,564,1692,564,1692,564,1692,564,1692,564,564,564,564,564,564,564,564,564,564,564,564,564,564,564,564,564,564,564,564,564,1692,564,1692,564,1692,564,1692,564,1692,564,44268};
    private static final int[] ASPECT    = {9024,4512,564,564,564,564,564,1692,564,564,564,564,564,564,564,564,564,564,564,1692,564,1692,564,1692,564,1692,564,1692,564,1692,564,564,564,564,564,564,564,564,564,564,564,564,564,1692,564,564,564,564,564,564,564,1692,564,1692,564,1692,564,1692,564,1692,564,564,564,1692,564,1692,564,44268};
    private static final int[] NETFLIX   = {9024,4512,564,564,564,564,564,1692,564,564,564,564,564,564,564,564,564,564,564,1692,564,1692,564,1692,564,1692,564,1692,564,1692,564,564,564,564,564,1692,564,564,564,1692,564,564,564,564,564,564,564,1692,564,564,564,1692,564,1692,564,564,564,1692,564,1692,564,1692,564,564,564,1692,564,44268};
    private static final int[] YOUTUBE   = {9024,4512,564,564,564,564,564,1692,564,564,564,564,564,564,564,564,564,564,564,1692,564,1692,564,1692,564,1692,564,1692,564,1692,564,564,564,1692,564,1692,564,564,564,1692,564,564,564,564,564,564,564,1692,564,564,564,1692,564,564,564,564,564,1692,564,1692,564,1692,564,564,564,1692,564,44268};
    private static final int[] PRIME     = {9024,4512,564,564,564,564,564,1692,564,564,564,564,564,564,564,564,564,564,564,1692,564,1692,564,1692,564,1692,564,1692,564,1692,564,1692,564,1692,564,1692,564,1692,564,564,564,564,564,564,564,564,564,564,564,564,564,564,564,564,564,564,1692,564,1692,564,1692,564,1692,564,1692,564,44268};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        irManager = (ConsumerIrManager) getSystemService(Context.CONSUMER_IR_SERVICE);
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        TextView statusText = findViewById(R.id.statusText);

        if (irManager == null || !irManager.hasIrEmitter()) {
            statusText.setText("⚠ No IR Blaster found on this device!");
            statusText.setTextColor(0xFFFF4444);
        } else {
            statusText.setText("✓ IR Blaster Ready — Point phone at projector");
            statusText.setTextColor(0xFF00D4FF);
        }

        // Power
        findViewById(R.id.btnPower).setOnClickListener(v -> sendIR(POWER, "Power"));

        // Top row
        findViewById(R.id.btnMute).setOnClickListener(v -> sendIR(MUTE, "Mute"));
        findViewById(R.id.btnSource).setOnClickListener(v -> sendIR(SOURCE, "Source"));
        findViewById(R.id.btnAspect).setOnClickListener(v -> sendIR(ASPECT, "Aspect Ratio"));

        // D-Pad
        findViewById(R.id.btnUp).setOnClickListener(v -> sendIR(UP, "Up"));
        findViewById(R.id.btnDown).setOnClickListener(v -> sendIR(DOWN, "Down"));
        findViewById(R.id.btnLeft).setOnClickListener(v -> sendIR(LEFT, "Left"));
        findViewById(R.id.btnRight).setOnClickListener(v -> sendIR(RIGHT, "Right"));
        findViewById(R.id.btnOK).setOnClickListener(v -> sendIR(OK, "OK"));

        // Action row
        findViewById(R.id.btnBack).setOnClickListener(v -> sendIR(BACK, "Back"));
        findViewById(R.id.btnHome).setOnClickListener(v -> sendIR(HOME, "Home"));
        findViewById(R.id.btnMenu).setOnClickListener(v -> sendIR(MENU, "Menu"));

        // Volume
        findViewById(R.id.btnVolUp).setOnClickListener(v -> sendIR(VOL_UP, "Volume Up"));
        findViewById(R.id.btnVolDown).setOnClickListener(v -> sendIR(VOL_DOWN, "Volume Down"));

        // Streaming
        findViewById(R.id.btnNetflix).setOnClickListener(v -> sendIR(NETFLIX, "Netflix"));
        findViewById(R.id.btnYoutube).setOnClickListener(v -> sendIR(YOUTUBE, "YouTube"));
        findViewById(R.id.btnPrime).setOnClickListener(v -> sendIR(PRIME, "Prime Video"));
    }

    private void sendIR(int[] pattern, String label) {
        if (irManager != null && irManager.hasIrEmitter()) {
            irManager.transmit(IR_FREQUENCY, pattern);
        }
        vibrator.vibrate(40);
        Toast.makeText(this, label, Toast.LENGTH_SHORT).show();
    }
}
