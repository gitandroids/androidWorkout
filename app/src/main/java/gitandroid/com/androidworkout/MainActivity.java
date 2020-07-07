package gitandroid.com.androidworkout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
/*      ExampleFragment fragment = new ExampleFragment();
        Bundle args = new Bundle();     // Send arguments to Fragment.. But so much boilerplate and it will be confusing when lots of argument.
        args.putString("argText","example Text");
        args.putInt("argNumber",123);
        fragment.setArguments(args);*/
        ExampleFragment fragment = ExampleFragment.newInstance("exampletextspace", 12345);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();    // We put the fragment instead of the rootlayout(frame layout)
    }
}
