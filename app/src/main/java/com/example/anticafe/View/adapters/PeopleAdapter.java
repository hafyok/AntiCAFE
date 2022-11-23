package com.example.anticafe.View.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.anticafe.View.PeopleFragment;
import com.example.anticafe.View.RoomsFragment;
import com.example.anticafe.View.adapters.viewHolders.PeopleViewHolder;
import com.example.anticafe.View.adapters.viewHolders.RoomsViewHolder;
import com.example.anticafe.databinding.GameElementBinding;
import com.example.anticafe.databinding.RoomsElementBinding;

import java.util.List;

public class PeopleAdapter extends RecyclerView.Adapter<PeopleViewHolder> {
    private final List<String> games;
    private final PeopleFragment peopleFragment;

    public PeopleAdapter(PeopleFragment peopleFragment, List<String> games){
        this.peopleFragment = peopleFragment;
        this.games = games;
    }


    @NonNull
    @Override
    public PeopleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        GameElementBinding ceb = GameElementBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new PeopleViewHolder(ceb);
    }

    @Override
    public void onBindViewHolder(@NonNull PeopleViewHolder holder, int position) {
        holder.getRadioButton().setText(games.get(position));
    }

    /*@Override
    public void onBindViewHolder(@NonNull RoomsViewHolder holder, int position) {
        holder.getRadioButton().setText(games.get(position));
        holder.getRadioButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }*/

    @Override
    public int getItemCount() {
        return games.size();
    }
}
