package jp.kshoji.blehid.sample;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import androidx.appcompat.app.AppCompatActivity;

import com.zero.ble_hid.R;


/**
 * Main Activity
 *
 * @author K.Shoji
 */
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle(getString(R.string.ble_hid));

        findViewById(R.id.mouseButton).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MouseActivity.class));
            }
        });
        findViewById(R.id.absoluteMouseButton).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), AbsoluteMouseActivity.class));
            }
        });
        findViewById(R.id.keyboardButton).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), KeyboardActivity.class));
            }
        });
        findViewById(R.id.joystickButton).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), JoystickActivity.class));
            }
        });
    }
}
