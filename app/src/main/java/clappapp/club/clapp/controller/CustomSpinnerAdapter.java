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

    private static final String TAG = CustomSpinnerAdapter.class.getSimpleName();

    private ViewDataBinding mBinding;
    private int[] mIconResourceIds;

    public CustomSpinnerAdapter(@NonNull Context context, String[] data, int[] symbols) {
        super(context, 0, data);
        mIconResourceIds = symbols;
    }

    public void setError(View v, CharSequence errorText) {
        ((SpinnerClosedLayoutBinding) mBinding).meaning.setError(errorText);
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

        mBinding = DataBindingUtil.bind(customView);
        String currentType = getItem(position);

        switch (layoutResourceID) {
            case R.layout.spinner_item_on_dropdown_layout:
                ((SpinnerItemOnDropdownLayoutBinding) mBinding).meaning.setText(currentType);
                ((SpinnerItemOnDropdownLayoutBinding) mBinding).symbol.setImageResource(mIconResourceIds[position]);
                break;
            case R.layout.spinner_closed_layout:
                if (position == getCount()) {
                    ((SpinnerClosedLayoutBinding) mBinding).meaning.setText(getItem(getCount()));
                    ((SpinnerClosedLayoutBinding) mBinding).meaning.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
                    ((SpinnerClosedLayoutBinding) mBinding).symbol.setVisibility(View.GONE);
                } else {
                    ((SpinnerClosedLayoutBinding) mBinding).symbol.setImageResource(mIconResourceIds[position]);
                    ((SpinnerClosedLayoutBinding) mBinding).meaning.setText(currentType);
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
