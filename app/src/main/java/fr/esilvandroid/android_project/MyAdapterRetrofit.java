package fr.esilvandroid.android_project;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapterRetrofit extends RecyclerView.Adapter<MyAdapterRetrofit.CustomViewHolder> {

    private List<RetroTodos> dataList;

    public MyAdapterRetrofit(List<RetroTodos> dataList) {
        this.dataList = dataList;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

//Get a reference to the Views in our layout//

        public final View myView;

        TextView textTodo;

        CustomViewHolder(View itemView) {
            super(itemView);
            myView = itemView;
            textTodo = myView.findViewById(R.id.user);
        }
    }

    @Override

//Construct a RecyclerView.ViewHolder//
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_retrofit, parent, false);
        return new CustomViewHolder(view);
    }

    @Override

//Set the data//
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        holder.textTodo.setText(dataList.get(position).getTodo());
    }

//Calculate the item count for the RecylerView//
    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
