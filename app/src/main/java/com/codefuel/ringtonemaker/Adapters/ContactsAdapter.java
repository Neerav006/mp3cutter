package com.codefuel.ringtonemaker.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.codefuel.ringtonemaker.R;
import com.codefuel.ringtonemaker.Activities.ChooseContactActivity;
import com.codefuel.ringtonemaker.Models.ContactsModel;
import com.codefuel.ringtonemaker.RTone.Utils;

import java.util.ArrayList;

/**
 * Created by REYANSH on 4/13/2017.
 */

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ItemHolder> {

    private ChooseContactActivity mChooseContactActivity;
    private ArrayList<ContactsModel> mData;

    public ContactsAdapter(ChooseContactActivity ringdroidSelectActivity, ArrayList<ContactsModel> data) {
        mChooseContactActivity = ringdroidSelectActivity;
        mData = data;
    }


    @Override
    public ContactsAdapter.ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ContactsAdapter.ItemHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contacts, parent, false));
    }

    @Override
    public void onBindViewHolder(ContactsAdapter.ItemHolder holder, int position) {
        holder.mContactName.setText(mData.get(position).mName);

        try {
            String letter = String.valueOf(mData.get(position).mName.charAt(0));
            holder.mOneLetter.setText(letter);
            holder.mOneLetter.setBackgroundColor(Utils.getMatColor(mChooseContactActivity.getApplicationContext()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public void updateData(ArrayList<ContactsModel> data) {
        this.mData = data;
        notifyDataSetChanged();
    }

    public class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mContactName;
        private TextView mOneLetter;

        public ItemHolder(View itemView) {
            super(itemView);
            mContactName = (TextView) itemView.findViewById(R.id.text_view_name);
            mOneLetter = (TextView) itemView.findViewById(R.id.one_letter);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mChooseContactActivity.onItemClicked(getAdapterPosition());
        }
    }
}
