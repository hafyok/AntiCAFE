package com.example.anticafe.View.adapters.viewHolders;

import android.widget.RadioButton;

import androidx.recyclerview.widget.RecyclerView;

import com.example.anticafe.databinding.RoomsElementBinding;

public class RoomsViewHolder extends RecyclerView.ViewHolder {
    RadioButton radioButton;

    public RoomsViewHolder(RoomsElementBinding ceb) {
        super(ceb.getRoot());
        radioButton = ceb.rButton;
    }

    public RadioButton getRadioButton(){
        return radioButton;
    }
}

