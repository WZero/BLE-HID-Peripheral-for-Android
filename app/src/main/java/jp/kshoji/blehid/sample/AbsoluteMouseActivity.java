package jp.kshoji.blehid.sample;

import static android.view.MotionEvent.ACTION_DOWN;
import static android.view.MotionEvent.ACTION_MOVE;
import static android.view.MotionEvent.ACTION_POINTER_DOWN;
import static android.view.MotionEvent.ACTION_POINTER_UP;
import static android.view.MotionEvent.ACTION_UP;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

import com.zero.ble_hid.R;

import jp.kshoji.blehid.AbsoluteMousePeripheral;

/**
 * Activity for BLE Mouse peripheral
 *
 * @author K.Shoji
 */
public class AbsoluteMouseActivity extends AbstractBleActivity {

    private AbsoluteMousePeripheral mouse;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_absolute_mouse);

        setTitle(getString(R.string.ble_mouse));

        final DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        findViewById(R.id.activity_mouse).setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(final View view, final MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case ACTION_DOWN:
                    case ACTION_POINTER_DOWN:
                        if (mouse != null) {
                            mouse.movePointer((int) (32767 * motionEvent.getX() / metrics.widthPixels), (int) (32767 * motionEvent.getY() / metrics.heightPixels), 0, true, false, false);
                        }
                        return true;

                    case ACTION_MOVE:
                        if (mouse != null) {
                            mouse.movePointer((int) (32767 * motionEvent.getX() / metrics.widthPixels), (int) (32767 * motionEvent.getY() / metrics.heightPixels), 0, true, false, false);
                        }
                        return true;

                    case ACTION_UP:
                    case ACTION_POINTER_UP:
                        mouse.movePointer((int) (32767 * motionEvent.getX() / metrics.widthPixels), (int) (32767 * motionEvent.getY() / metrics.heightPixels), 0, false, false, false);
                        return true;
                }
                return false;
            }
        });
    }

    @Override
    void setupBlePeripheralProvider() {
        mouse = new AbsoluteMousePeripheral(this);
        mouse.setDeviceName(getString(R.string.ble_mouse));
        mouse.startAdvertising();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mouse != null) {
            mouse.stopAdvertising();
        }
    }
}
