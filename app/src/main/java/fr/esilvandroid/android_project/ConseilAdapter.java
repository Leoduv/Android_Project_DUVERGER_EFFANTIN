package fr.esilvandroid.android_project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ConseilAdapter extends RecyclerView.Adapter<ConseilAdapter.ConseilsAdapterVh> implements Filterable {

    private List<ConseilsModel> conseilsModelList;
    private List<ConseilsModel> getConseilsModelListFiltered;
    private Context context;
    private SelectedConseil selectedConseil;

    public ConseilAdapter(List<ConseilsModel> conseilsModelList, SelectedConseil selectedConseil){
        this.conseilsModelList = conseilsModelList;
        this.getConseilsModelListFiltered = conseilsModelList;
        this.selectedConseil = selectedConseil;
    }

    @NonNull
    @Override
    public ConseilAdapter.ConseilsAdapterVh onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        context = parent.getContext();

        return new ConseilsAdapterVh(LayoutInflater.from(context).inflate(R.layout.row_conseils, null));
    }

    @Override
    public void onBindViewHolder(@NonNull ConseilsAdapterVh holder, int position) {

        ConseilsModel conseilsModel = conseilsModelList.get(position);

        String title = conseilsModel.getTitle();
        String desc = conseilsModel.getDesc();
        String prefix = conseilsModel.getTitle().substring(0,1);

        holder.tvTitle.setText(title);
        holder.tvDesc.setText(desc);
        holder.tvPrefix.setText(prefix);
    }

    @Override
    public int getItemCount() {

        return conseilsModelList.size();
    }

    @Override
    public Filter getFilter() {

        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                FilterResults filterResults = new FilterResults();

                if (charSequence == null | charSequence.length() == 0){
                    filterResults.count = getConseilsModelListFiltered.size();
                    filterResults.values = getConseilsModelListFiltered;
                }else {
                    String searchChr = charSequence.toString().toLowerCase();

                    List<ConseilsModel> resultData = new ArrayList<>();

                    for (ConseilsModel conseilsModel: getConseilsModelListFiltered){
                        if (conseilsModel.getTitle().toLowerCase().contains(searchChr)){
                            resultData.add(conseilsModel);
                        }
                    }
                    filterResults.count = resultData.size();
                    filterResults.values = resultData;
                }

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults filterResults) {

                conseilsModelList = (List<ConseilsModel>) filterResults.values;
                notifyDataSetChanged();
            }
        };

        return filter;
    }

    public interface SelectedConseil{

        void selectedConseil(ConseilsModel conseilsModel);
    }

    public class ConseilsAdapterVh extends RecyclerView.ViewHolder{
        TextView tvPrefix;
        TextView tvTitle;
        TextView tvDesc;
        ImageView imIcon;

        public ConseilsAdapterVh(@NonNull View itemView) {
            super(itemView);
            tvPrefix = itemView.findViewById(R.id.prefix);
            tvTitle = itemView.findViewById(R.id.title);
            tvDesc = itemView.findViewById(R.id.desc);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    selectedConseil.selectedConseil(conseilsModelList.get(getAdapterPosition()));
                }
            });
        }
    }

}
