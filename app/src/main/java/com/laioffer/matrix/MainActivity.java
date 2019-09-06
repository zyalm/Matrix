package com.laioffer.matrix;

import android.content.Intent;
import android.content.res.Configuration;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.google.firebase.analytics.FirebaseAnalytics;

public class MainActivity extends AppCompatActivity implements EventFragment.OnItemSelectListener, CommentFragment.OnItemSelectListener {

    private EventFragment listFragment;
    private CommentFragment gridFragment;
    private FirebaseAnalytics mFirebaseAnalytics;

//    @Override
//    public void onItemSelected(int position) {
//        gridFragment.onItemSelected(position);
//    }

    @Override
    public void onCommentSelected(int position) {
        listFragment.onItemSelected(position);
    }

    // Add this method to main activity
    @Override
    public void onItemSelected(int position) {
        if (!isTablet()) {
            Fragment fragment = CommentFragment.newInstance(position);
            getSupportFragmentManager().beginTransaction().replace(R.id.event_container, fragment).addToBackStack(null).commit();
//            Intent intent = new Intent(this, EventGridActivity.class);
//            intent.putExtra("position", position);
//            startActivity(intent);
        } else {
            gridFragment.onItemSelected(position);
        }

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        // Show different fragments based on screen size.
//        if (findViewById(R.id.fragment_container) != null) {
//            Fragment fragment = isTablet() ? new  CommentFragment() : new EventFragment();
//            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, fragment).commit();
//        }
//      // Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);


        //add list view
        if (getSupportFragmentManager().findFragmentById(R.id.event_container) == null) {
            listFragment = new EventFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.event_container, listFragment).commit();
        }

        //add Gridview
        if (getSupportFragmentManager().findFragmentById(R.id.comment_container) == null && isTablet()) {
            gridFragment = new CommentFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.comment_container, gridFragment).commit();
        }
    }



    private boolean isTablet() {
        return (getApplicationContext().getResources().getConfiguration().screenLayout &
                Configuration.SCREENLAYOUT_SIZE_MASK) >=
                Configuration.SCREENLAYOUT_SIZE_LARGE;

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("Life cycle test", "We are at onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("Life cycle test", "We are at onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("Life cycle test", "We are at onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("Life cycle test", "We are at onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("Life cycle test", "We are at onDestroy()");
    }
}