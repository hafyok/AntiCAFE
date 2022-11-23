package com.example.anticafe.View.adapters.viewHolders;

import android.widget.RadioButton;

import androidx.recyclerview.widget.RecyclerView;

import com.example.anticafe.databinding.FragmentPeopleBinding;
import com.example.anticafe.databinding.GameElementBinding;
import com.example.anticafe.databinding.RoomsElementBinding;

public class PeopleViewHolder extends RecyclerView.ViewHolder {
    RadioButton radioButton;

    public PeopleViewHolder(GameElementBinding ceb) {
        super(ceb.getRoot());
        radioButton = ceb.rButton2;
    }

    public RadioButton getRadioButton(){
        return radioButton;
    }
}
