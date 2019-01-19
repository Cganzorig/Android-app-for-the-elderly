package academy.learnprogramming.simplerecyclerlistexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MyRecyclerViewAdapter.ItemClickListener {

    MyRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // data to populate the RecyclerView with
        ArrayList<Animal> animals = new ArrayList<>();
        animals.add(new Animal("Horse", 400, "Horses have four legs"));
        animals.add(new Animal("Camel", 400));
        animals.add(new Animal("Elephant", 400));
        animals.add(new Animal("Tiger", 400));
        animals.add(new Animal("Frog", 400));
        animals.add(new Animal("Snail", 400));
        animals.add(new Animal("Shark", 400));
        animals.add(new Animal("Salmon", 400));
        animals.add(new Animal("Sea horse", 400));
        animals.add(new Animal("Dog", 400));
        animals.add(new Animal("Cat", 400));
        animals.add(new Animal("Bee", 400));


        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.rvAnimals);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyRecyclerViewAdapter(this, animals);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(this,adapter.getItem(position), Toast.LENGTH_SHORT).show();
    }
}
