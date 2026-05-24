package com.zebronics.remote;

import android.app.Activity;
import android.content.Context;
import android.hardware.ConsumerIrManager;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
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
    private static final int[] NETFLIX  = {9024,4512,564,564,564,564,564,1692,564,564,564,564,564,564,564,564,564,564,564,1692,564,1692,564,1692,564,1692,564,1692,564,1692,564,564,564,564,564,1692,564,564,564,1692,564,564,564,564,564,564,564,1692,564,564,564,1692,564,1692,564,564,564,1692,564,1692,564,1692,564,564,564,1692,564,44268};
    private static final int[] YOUTUBE  = {9024,4512,564,564,564,564,564,1692,564,564,564,564,564,564,564,564,564,564,564,1692,564,1692,564,1692,564,1692,564,1692,564,1692,564,564,564,1692,564,1692,564,564,564,1692,564,564,564,564,564,564,564,1692,564,564,564,1692,564,564,564,564,564,1692,564,1692,564,1692,564,564,564,1692,564,44268};
    private static final int[] PRIME    = {9024,4512,564,564,564,564,564,1692,564,564,564,564,564,564,564,564,564,564,564,1692,564,1692,564,1692,564,1692,564,1692,564,1692,564,1692,564,1692,564,1692,564,1692,564,564,564,564,564,564,564,564,564,564,564,564,564,564,564,564,564,564,1692,564,1692,564,1692,564,1692,564,1692,564,44268};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Init IR
        try { irManager = (ConsumerIrManager) getSystemService(Context.CONSUMER_IR_SERVICE); } catch (Exception e) { irManager = null; }
        try { vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE); } catch (Exception e) { vibrator = null; }

        // Build UI entirely in Java - NO XML used at all
        ScrollView scroll = new ScrollView(this);
        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setBackgroundColor(0xFF0A0A0F);
        root.setPadding(24, 48, 24, 48);
        root.setGravity(Gravity.CENTER_HORIZONTAL);

        // Title
        addText(root, "ZEBRONICS PIXAPLAY", 0xFF00D4FF, 20, true);
        addText(root, "Projector Remote", 0xFF6B6B9A, 13, false);
        space(root, 8);

        // IR Status
        boolean hasIR = irManager != null && irManager.hasIrEmitter();
        addText(root, hasIR ? "✓ IR Blaster Ready" : "✗ No IR Blaster Found",
                hasIR ? 0xFF00FF88 : 0xFFFF4444, 13, false);
        space(root, 24);

        // Power
        root.addView(makeBtn("⏻  POWER", POWER, "Power", 0xFFAA0020, 0xFFFF3A5C));
        space(root, 16);

        // Mute + Source
        addRow(root,
            makeBtn("🔇 MUTE", MUTE, "Mute", 0xFF1C1C2E, 0xFF00D4FF),
            makeBtn("⬛ SOURCE", SOURCE, "Source", 0xFF1C1C2E, 0xFF00D4FF)
        );
        space(root, 12);

        // D-Pad
        addRow(root,
            makeInvisible(),
            makeBtn("▲", UP, "Up", 0xFF1C1C2E, 0xFFE8E8FF),
            makeInvisible()
        );
        space(root, 6);
        addRow(root,
            makeBtn("◀", LEFT, "Left", 0xFF1C1C2E, 0xFFE8E8FF),
            makeBtn("OK", OK, "OK", 0xFF003366, 0xFF00D4FF),
            makeBtn("▶", RIGHT, "Right", 0xFF1C1C2E, 0xFFE8E8FF)
        );
        space(root, 6);
        addRow(root,
            makeInvisible(),
            makeBtn("▼", DOWN, "Down", 0xFF1C1C2E, 0xFFE8E8FF),
            makeInvisible()
        );
        space(root, 12);

        // Back, Home, Menu
        addRow(root,
            makeBtn("↩ BACK", BACK, "Back", 0xFF1C1C2E, 0xFFE8E8FF),
            makeBtn("⌂ HOME", HOME, "Home", 0xFF1C1C2E, 0xFFE8E8FF),
            makeBtn("☰ MENU", MENU, "Menu", 0xFF1C1C2E, 0xFFE8E8FF)
        );
        space(root, 12);

        // Volume
        addRow(root,
            makeBtn("VOL −", VOL_DOWN, "Volume Down", 0xFF1C1C2E, 0xFFE8E8FF),
            makeBtn("VOL +", VOL_UP, "Volume Up", 0xFF1C1C2E, 0xFFE8E8FF)
        );
        space(root, 24);

        // Streaming
        root.addView(makeBtn("NETFLIX", NETFLIX, "Netflix", 0xFFB20710, 0xFFFFFFFF));
        space(root, 8);
        root.addView(makeBtn("▶  YouTube", YOUTUBE, "YouTube", 0xFFCC0000, 0xFFFFFFFF));
        space(root, 8);
        root.addView(makeBtn("prime video", PRIME, "Prime Video", 0xFF007AA5, 0xFFFFFFFF));
        space(root, 24);

        addText(root, "Point TOP of phone at projector", 0xFF6B6B9A, 11, false);

        scroll.addView(root);
        setContentView(scroll);
    }

    private void addText(LinearLayout parent, String text, int color, float size, boolean bold) {
        TextView tv = new TextView(this);
        tv.setText(text);
        tv.setTextColor(color);
        tv.setTextSize(size);
        tv.setGravity(Gravity.CENTER);
        if (bold) tv.setTypeface(null, android.graphics.Typeface.BOLD);
        tv.setPadding(0, 4, 0, 4);
        parent.addView(tv);
    }

    private void space(LinearLayout parent, int dp) {
        View v = new View(this);
        v.setLayoutParams(new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT, dp));
        parent.addView(v);
    }

    private Button makeBtn(String label, final int[] code, final String toast, int bg, int fg) {
        Button btn = new Button(this);
        btn.setText(label);
        btn.setTextColor(fg);
        btn.setBackgroundColor(bg);
        btn.setPadding(16, 16, 16, 16);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(4, 4, 4, 4);
        btn.setLayoutParams(lp);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendIR(code, toast);
            }
        });
        return btn;
    }

    private View makeInvisible() {
        View v = new View(this);
        v.setVisibility(View.INVISIBLE);
        return v;
    }

    private void addRow(LinearLayout parent, View... views) {
        LinearLayout row = new LinearLayout(this);
        row.setOrientation(LinearLayout.HORIZONTAL);
        row.setLayoutParams(new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT));
        for (View v : views) {
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f);
            lp.setMargins(4, 4, 4, 4);
            v.setLayoutParams(lp);
            row.addView(v);
        }
        parent.addView(row);
    }

    private void sendIR(int[] pattern, String label) {
        try {
            if (irManager != null && irManager.hasIrEmitter()) {
                irManager.transmit(IR_FREQUENCY, pattern);
            }
        } catch (Exception ignored) {}
        try {
            if (vibrator != null) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    vibrator.vibrate(VibrationEffect.createOneShot(40, VibrationEffect.DEFAULT_AMPLITUDE));
                } else {
                    vibrator.vibrate(40);
                }
            }
        } catch (Exception ignored) {}
        Toast.makeText(this, label, Toast.LENGTH_SHORT).show();
    }
}