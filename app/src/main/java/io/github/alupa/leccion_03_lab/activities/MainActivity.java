package io.github.alupa.leccion_03_lab.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import io.github.alupa.leccion_03_lab.R;
import io.github.alupa.leccion_03_lab.adapters.FruitAdapter;
import io.github.alupa.leccion_03_lab.models.Fruit;

public class MainActivity extends AppCompatActivity {
    private List<Fruit> fruits;

    private RecyclerView recyclerView;
    private FruitAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.fruits = getAllFruits();

        this.recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        this.layoutManager = new LinearLayoutManager(this);
        this.adapter = new FruitAdapter(fruits, R.layout.recycler_view_fruit_item, this, new FruitAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Fruit fruit, int position) {
                //Toast.makeText(MainActivity.this, "Elegiste " + fruit.getName(), Toast.LENGTH_SHORT).show();
                fruit.addQuantity(1);
                adapter.notifyItemChanged(position);
            }
        });

        //recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_fruit:
                this.addFruit(0);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void addFruit(int position) {
        this.fruits.add(position, new Fruit("New fruit" + (++counter), "Fruit add by the user", R.drawable.plum_bg, R.drawable.plum_ic, 1));
        this.adapter.notifyItemInserted(position);
        this.layoutManager.scrollToPosition(position);
    }

    public List<Fruit> getAllFruits() {
        return new ArrayList<Fruit>(){{
            add(new Fruit("Manzana", "Rica manzana del sur de Chile", R.drawable.apple_bg, R.drawable.apple_ic, 1));
            add(new Fruit("Plátano", "Sabroso plátano ecuatoriano", R.drawable.banana_bg, R.drawable.banana_ic, 1));
            add(new Fruit("Cereza", "Exquisitas cerezas tiernas", R.drawable.cherry_bg, R.drawable.cherry_ic, 1));
            add(new Fruit("Naranja", "Jugosas naranjas sureñas sin pepa", R.drawable.orange_bg, R.drawable.orange_ic, 1));
            add(new Fruit("Pera", "Maravillosas peras grandes", R.drawable.pear_bg, R.drawable.pear_ic, 1));
            add(new Fruit("Ciruela", "Grandiosas ciruelas norteñas", R.drawable.plum_bg, R.drawable.plum_ic, 1));
            add(new Fruit("Frambuesa", "Dulces frambuesas listas para servir", R.drawable.raspberry_bg, R.drawable.raspberry_ic, 1));
            add(new Fruit("Frutilla", "Frutillas de intenso color rojo y sabor dulce", R.drawable.strawberry_bg, R.drawable.strawberry_ic, 1));
        }};
    }
}
