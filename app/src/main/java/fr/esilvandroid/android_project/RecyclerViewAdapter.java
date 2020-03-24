package fr.esilvandroid.android_project;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private Context mContext;
    private List<Item> mData;

    public RecyclerViewAdapter(Context mContext, List<Item> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.cardview_item, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.tv_item_title.setText(mData.get(position).getItemTitle());
        holder.img_item_title.setImageResource(mData.get(position).getItemview());


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        private LinearLayout item;
        private TextView tv_item_title;
        private ImageView img_item_title;
        private CardView cardView;

        public MyViewHolder(View itemView){
            super((itemView));

            tv_item_title = (TextView) itemView.findViewById(R.id.item_title_id);
            img_item_title = (ImageView) itemView.findViewById(R.id.item_img_id);
        }
    }
}


