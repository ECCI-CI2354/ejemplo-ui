package cr.ac.ucr.ecci.ci2354.ejemploui;

import java.lang.ref.WeakReference;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class DialogSampleActivity extends Activity {
    public static final int DIALOG_ID = 2;

    TestAsyncTask currentTask = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_sample);
        TestAsyncTask task = (TestAsyncTask) getLastNonConfigurationInstance();
        if (task != null) {
            task.attach(this);
        }
    }

    public void cambiar(View view) {
        TestAsyncTask task = new TestAsyncTask(this);
        task.execute("Hola");
        currentTask = task;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_dialog_sample, menu);
        return true;
    }

    @Override
    @Deprecated
    protected Dialog onCreateDialog(int id) {
        switch (id) {
        case DIALOG_ID:
            ProgressDialog dialog = new ProgressDialog(this);
            dialog.setMessage("Espere...");
            return dialog;

        default:
            break;
        }
        return null;
    }

    @Override
    @Deprecated
    public Object onRetainNonConfigurationInstance() {
        if (currentTask != null) {
            currentTask.deattach();
            return currentTask;
        } else {
            return super.onRetainNonConfigurationInstance();
        }

    }

    static class TestAsyncTask extends AsyncTask<String, Void, String> {
        WeakReference<Activity> context;

        public void attach(Activity activity) {
            context = new WeakReference<Activity>(activity);
        }

        public void deattach() {
            context = null;
        }

        public TestAsyncTask(Activity activity) {
            attach(activity);
        }

        @Override
        protected void onPostExecute(String result) {
            Activity activity = context.get();
            if (activity != null) {
                activity.dismissDialog(DIALOG_ID);
            }
        }

        @Override
        protected void onPreExecute() {
            Activity activity = context.get();
            activity.showDialog(DIALOG_ID);
        }

        @Override
        protected String doInBackground(String... params) {
            String original = params[0];
            try {
                Thread.sleep(7000);
            } catch (InterruptedException ignore) {
            }

            return original + "_test";
        }

    }
}
