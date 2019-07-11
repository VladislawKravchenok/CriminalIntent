package by.vladislaw.kravchenok.criminalintent;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.LayoutRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

/**
 * Created by Vladislaw Kravchenok on 25.06.2019.
 */
public abstract class SingleFragmentActivity extends AppCompatActivity {
    private static final String TAG = SingleFragmentActivity.class.getSimpleName();

    protected abstract Fragment createFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        setContentView(getLayoutResId());
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);
        if (fragment == null) {
            fragment = createFragment();
            FragmentTransaction ft = fm.beginTransaction();
            ft.add(R.id.fragment_container, fragment)
                    .commit();
        }
    }

    @LayoutRes
    protected int getLayoutResId() {
        return R.layout.activity_fragment;
    }

}
