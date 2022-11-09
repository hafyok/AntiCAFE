package com.example.anticafe.View.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.anticafe.View.RoomsFragment;
import com.example.anticafe.databinding.RoomsElementBinding;

import java.util.List;

import com.example.anticafe.View.adapters.viewHolders.RoomsViewHolder;

public class RoomsAdapter extends RecyclerView.Adapter<RoomsViewHolder> {
    private final List<String> rooms;
    private final RoomsFragment roomsFragment;

    public RoomsAdapter(RoomsFragment roomsFragment, List<String> rooms){
        this.roomsFragment = roomsFragment;
        this.rooms = rooms;
    }


    @NonNull
    @Override
    public RoomsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RoomsElementBinding ceb = RoomsElementBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new RoomsViewHolder(ceb);
    }

    @Override
    public void onBindViewHolder(@NonNull RoomsViewHolder holder, int position) {
        holder.getRadioButton().setText(rooms.get(position));
        holder.getRadioButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    @Override
    public int getItemCount() {
        return rooms.size();
    }
}
