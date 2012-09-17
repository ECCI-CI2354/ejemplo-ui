package cr.ac.ucr.ecci.ci2354.ejemploui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    public void launchActivity(View view) {
        String tag = (String) view.getTag();
        if ("DRAWABLE".equals(tag)) {
            startActivity(new Intent(this, EjemploDrawableActivity.class));
        } else if ("ANIMATION".equals(tag)) {
            startActivity(new Intent(this, EjemploAnimationActivity.class));
        } else if ("DIALOG".equals(tag)) {
            startActivity(new Intent(this, DialogSampleActivity.class));
        }
    }
}
