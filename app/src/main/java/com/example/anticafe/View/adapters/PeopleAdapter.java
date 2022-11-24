package com.example.anticafe.View.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.anticafe.R;
import com.example.anticafe.View.PeopleFragment;
import com.example.anticafe.View.adapters.viewHolders.PeopleViewHolder;
import com.example.anticafe.databinding.FragmentPeopleBinding;
import com.example.anticafe.databinding.GameElementBinding;

import java.util.List;

public class PeopleAdapter extends RecyclerView.Adapter<PeopleViewHolder> {
    private final List<String> games;
    private final PeopleFragment peopleFragment;
    private final List<String> urlImg;
    FragmentPeopleBinding binding;
    private Context mContext;


    public PeopleAdapter(PeopleFragment peopleFragment, List<String> games, List<String> urlImg){
        this.peopleFragment = peopleFragment;
        this.games = games;
        this.urlImg = urlImg;
        mContext = peopleFragment.getContext();
        //binding = FragmentPeopleBinding.inflate(inflater, container, false);
    }


    @NonNull
    @Override
    public PeopleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        GameElementBinding ceb = GameElementBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);

        return new PeopleViewHolder(ceb);
    }

    @Override
    public void onBindViewHolder(@NonNull PeopleViewHolder holder, int position) {
        ImageView imageView = holder.getImageView();
        holder.getRadioButton().setText(games.get(position));
        //holder.getTextView().setText(urlImg.get(position));

        Glide.with(mContext)
                .load(urlImg.get(position))
                .placeholder(R.drawable.ic_baseline_self_improvement_24)
                .into(imageView);
    }


    @Override
    public int getItemCount() {
        return games.size();
    }
}
