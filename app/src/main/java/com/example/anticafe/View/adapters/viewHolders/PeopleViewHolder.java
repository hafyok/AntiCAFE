package com.example.anticafe.View.adapters.viewHolders;

import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.anticafe.databinding.FragmentPeopleBinding;
import com.example.anticafe.databinding.GameElementBinding;
import com.example.anticafe.databinding.RoomsElementBinding;

public class PeopleViewHolder extends RecyclerView.ViewHolder {
    RadioButton radioButton;
    ImageView mImageView;
    TextView textView;

    public PeopleViewHolder(GameElementBinding ceb) {
        super(ceb.getRoot());
        radioButton = ceb.rButton2;
        mImageView = ceb.gameImageView;
        textView = ceb.describeGame;
    }

    public RadioButton getRadioButton(){
        return radioButton;
    }
    public ImageView getImageView(){
        return mImageView;
    }
    public TextView getTextView(){
        return textView;
    }
}
