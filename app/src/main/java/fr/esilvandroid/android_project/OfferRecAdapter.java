package fr.esilvandroid.android_project;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class OfferRecAdapter extends RecyclerView.Adapter<OfferRecAdapter.OfferRecViewHolder> {

    private List<OffersModel> list;

    public OfferRecAdapter(List<OffersModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public OfferRecViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_offer, parent, false);
        return new OfferRecViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OfferRecViewHolder holder, final int position) {
        final OffersModel offer = list.get(position);

        holder.bind(offer);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the current state of the item
                boolean expanded = offer.isExpanded();
                // Change the state
                offer.setExpanded(!expanded);
                // Notify the adapter that item has changed
                notifyItemChanged(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class OfferRecViewHolder extends RecyclerView.ViewHolder {

        private TextView company;
        private TextView jobtitle;
        private TextView summary;
        private TextView skills;
        private TextView link;
        private TextView status;
        private View subItem;

        public OfferRecViewHolder(@NonNull View itemView) {
            super(itemView);

            company = itemView.findViewById(R.id.item_company);
            jobtitle = itemView.findViewById(R.id.sub_item_jobtitle);
            summary = itemView.findViewById(R.id.sub_item_summary);
            skills = itemView.findViewById(R.id.sub_item_skills);
            link = itemView.findViewById(R.id.sub_item_link);
            status = itemView.findViewById(R.id.sub_item_status);
            subItem = itemView.findViewById(R.id.sub_item);
        }

        private void bind(OffersModel offer) {
            boolean expanded = offer.isExpanded();
            Offer the_offer = offer.getOffer();

            subItem.setVisibility(expanded ? View.VISIBLE : View.GONE);

            //FAIRE TRUC PLUS JOLI ?
            company.setText(the_offer.company);
            jobtitle.setText("Job : " + the_offer.jobtitle);
            summary.setText(the_offer.summary);
            skills.setText("Skills : " + the_offer.skills);
            link.setText(the_offer.link);
            status.setText("Status : " + the_offer.status);
        }
    }

}