package jp.kshoji.blehid.sample;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.zero.ble_hid.R;

import jp.kshoji.blehid.KeyboardPeripheral;

/**
 * Activity for BLE Keyboard peripheral
 *
 * @author K.Shoji
 */
public class KeyboardActivity extends AbstractBleActivity {

    private KeyboardPeripheral keyboard;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keyboard);

        setTitle(getString(R.string.ble_keyboard));

        findViewById(R.id.sendButton).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(final View view) {
                if (keyboard != null) {
                    keyboard.sendKeys(((TextView) findViewById(R.id.editText)).getText().toString());
                }
            }
        });
    }

    @Override
    void setupBlePeripheralProvider() {
        keyboard = new KeyboardPeripheral(this);
        keyboard.setDeviceName(getString(R.string.ble_keyboard));
        keyboard.startAdvertising();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (keyboard != null) {
            keyboard.stopAdvertising();
        }
    }
}
