package io.github.alupa.leccion_03_lab.adapters;

import android.app.Activity;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import io.github.alupa.leccion_03_lab.R;
import io.github.alupa.leccion_03_lab.models.Fruit;

/**
 * Created by Alvaro on 28-09-2017.
 */

public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder> {
    private List<Fruit> fruits;
    private int layout;
    private OnItemClickListener itemClickListener;
    private Activity activity;

    public FruitAdapter(List<Fruit> fruits, int layout, Activity activity, OnItemClickListener itemClickListener) {
        this.fruits = fruits;
        this.layout = layout;
        this.itemClickListener = itemClickListener;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(activity).inflate(layout, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(fruits.get(position), itemClickListener);
    }

    @Override
    public int getItemCount() {
        return fruits.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener {
        private ImageView imageViewBG;
        private TextView textViewName;
        private TextView textViewDescription;
        private TextView textViewQuantity;

        public ViewHolder(View itemView) {
            super(itemView);
            this.imageViewBG = (ImageView) itemView.findViewById(R.id.imageViewBG);
            this.textViewName = (TextView) itemView.findViewById(R.id.textViewName);
            this.textViewDescription = (TextView) itemView.findViewById(R.id.textViewDescription);
            this.textViewQuantity = (TextView) itemView.findViewById(R.id.textViewQuantity);
            // Añadimos al view el listener para el context menu, en vez de hacerlo en
            // el activity mediante el método registerForContextMenu
            itemView.setOnCreateContextMenuListener(this);
        }

        public void bind(final Fruit fruit, final OnItemClickListener listener){
            // Procesamos los datos a renderizar
            Picasso.with(activity).load(fruit.getBg()).fit().into(imageViewBG);
            textViewName.setText(fruit.getName());
            textViewDescription.setText(fruit.getDescription());
            textViewQuantity.setText("Stock: " + fruit.getQuantity());
            if (fruit.getQuantity() == fruit.LIMIT_QUANTITY){
                textViewQuantity.setTextColor(ContextCompat.getColor(activity, R.color.colorAlert));
                textViewQuantity.setTypeface(null, Typeface.BOLD);
            } else {
                textViewQuantity.setTextColor(ContextCompat.getColor(activity, R.color.defaultTextColor));
                textViewQuantity.setTypeface(null, Typeface.NORMAL);
            }
            imageViewBG.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(fruit, getAdapterPosition());
                }
            });
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            // Recogemos la posicion con el metodo getAdapterPosition
            Fruit fruitSelected = fruits.get(this.getAdapterPosition());
            // Establecemos titulo e icono para cada elemento
            menu.setHeaderTitle(fruitSelected.getName());
            menu.setHeaderIcon(fruitSelected.getIc());
            // Inflamos menu
            MenuInflater inflater = activity.getMenuInflater();
            inflater.inflate(R.menu.context_menu_fruit, menu);
            // Añadimos a cada elemento del context menu el listener onMenuItemClick para
            // controlar sus acciones
            for (int i = 0 ; i <  menu.size() ; i++)
                menu.getItem(i).setOnMenuItemClickListener(this);
        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            // La posicion de la fruta la podemos rescatar desde getAdapterPosition
            // como estamos dentro del adaptador, podemos acceder a los metodos propios
            // de él como notifyItemRemoved o notifyItemChanged
            switch (item.getItemId()){
                case R.id.delete_fruit:
                    fruits.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());
                    return true;
                case R.id.reset_quantity_fruit:
                    fruits.get(getAdapterPosition()).resetQuantity();
                    notifyItemChanged(getAdapterPosition());
                    return true;
                default:
                    return false;
            }
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Fruit fruit, int position);
    }
}
