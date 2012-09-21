package cr.ac.ucr.ecci.ci2354.ejemploui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class EjemploAnimationActivity extends Activity {
    ImageView mFYeah;

    Animation fadeIn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        mFYeah = (ImageView) findViewById(R.id.animation_fyeah);
        fadeIn = AnimationUtils.loadAnimation(this, R.anim.hyperspace_jump);
        fadeIn.setAnimationListener( new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation animation) {
				mFYeah.setVisibility(View.INVISIBLE);
			}
			
			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationEnd(Animation animation) {
				mFYeah.setVisibility(View.VISIBLE);
			}
		});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_animation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case android.R.id.home:
            NavUtils.navigateUpFromSameTask(this);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void showImage(View view) {
        mFYeah.startAnimation(fadeIn);
    }
}
