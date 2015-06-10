package com.discounty;


import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
    private TextView txt;
    private Button btn, btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_layout);

        txt = (TextView) findViewById(R.id.textView);
        btn = (Button) findViewById(R.id.button);
        btn2 = (Button) findViewById(R.id.button2);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt.setText("Format: " + format + "\nCode: " + contents);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("com.google.zxing.client.android.ENCODE");
                intent.putExtra("ENCODE_FORMAT", format);
                intent.putExtra("ENCODE_DATA", contents);
                Activity a = new Activity();
                a.setTitle("Barcode");
                a.setIntent(intent);
                startActivity(a.getIntent());
            }
        });
    }

    public void OnScan(View v) {
        Intent data = new Intent("com.google.zxing.client.android.SCAN");
        //data.putExtra("SCAN_MODE", "QR_CODE_MODE");
        startActivityForResult(data, 0);
    }


    private String contents, format;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            if (resultCode == RESULT_OK) {
                contents = data.getStringExtra("SCAN_RESULT");
                format = data.getStringExtra("SCAN_RESULT_FORMAT");
            } else if (resultCode == RESULT_CANCELED)
                Log.i("App", "RESULT_CANCELED");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }
}
