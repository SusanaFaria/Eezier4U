package susana.faria.eezier4u;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import susana.faria.eezier4u.data.People;
import susana.faria.eezier4u.data.PeopleViewModel;

public class MainActivity extends AppCompatActivity {

    public static final int NEW_PERSON_REQUEST_CODE = 1;
    private PeopleViewModel mPeopleViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.gridrv);
        final PeopleListAdapter adapter = new PeopleListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        // Get a new or existing ViewModel from the ViewModelProvider.
        mPeopleViewModel = ViewModelProviders.of(this).get(PeopleViewModel.class);

        // Add an observer on the LiveData returned by getAll.
        // The onChanged() method fires when the observed data changes and the activity is
        // in the foreground.
        mPeopleViewModel.getAll().observe(this, new Observer<List<People>>() {
            @Override
            public void onChanged(@Nullable final List<People> people) {
                // Update the cached copy of the words in the adapter.
                adapter.setPeople(people);
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, EezierEditCard.class);
                startActivity(intent);
            }
        });
    }



    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_PERSON_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            Word word = new Word(data.getStringExtra(MainActivity2.EXTRA_REPLY));
            mWordViewModel.insert(word);
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        }
    }
}
