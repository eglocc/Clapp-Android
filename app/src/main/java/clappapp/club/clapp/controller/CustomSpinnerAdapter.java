package clappapp.club.clapp.controller;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;

import clappapp.club.clapp.R;
import clappapp.club.clapp.databinding.SpinnerClosedLayoutBinding;
import clappapp.club.clapp.databinding.SpinnerItemOnDropdownLayoutBinding;

public class CustomSpinnerAdapter extends ArrayAdapter<String> implements SpinnerAdapter {

    private int[] mIconResourceIds;

    public CustomSpinnerAdapter(@NonNull Context context, String[] data, int[] symbols) {
        super(context, 0, data);
        mIconResourceIds = symbols;
    }

    @Override
    public int getCount() {
        return super.getCount() - 1;
    }

    private View getCustomView(int position, View convertView, ViewGroup parent, int layoutResourceID) {

        View customView = convertView;

        if (customView == null) {
            customView = LayoutInflater.from(getContext()).inflate(layoutResourceID, parent, false);
        }

        ViewDataBinding binding = DataBindingUtil.bind(customView);
        String currentType = getItem(position);

        switch (layoutResourceID) {
            case R.layout.spinner_item_on_dropdown_layout:
                ((SpinnerItemOnDropdownLayoutBinding) binding).meaning.setText(currentType);
                ((SpinnerItemOnDropdownLayoutBinding) binding).symbol.setImageResource(mIconResourceIds[position]);
                break;
            case R.layout.spinner_closed_layout:
                if (position == getCount()) {
                    ((SpinnerClosedLayoutBinding) binding).meaning.setText(getItem(getCount()));
                    ((SpinnerClosedLayoutBinding) binding).meaning.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
                    ((SpinnerClosedLayoutBinding) binding).symbol.setVisibility(View.GONE);
                } else {
                    ((SpinnerClosedLayoutBinding) binding).symbol.setImageResource(mIconResourceIds[position]);
                    ((SpinnerClosedLayoutBinding) binding).meaning.setText(currentType);
                }
                break;
        }

        return customView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getCustomView(position, convertView, parent, R.layout.spinner_item_on_dropdown_layout);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getCustomView(position, convertView, parent, R.layout.spinner_closed_layout);
    }
}
