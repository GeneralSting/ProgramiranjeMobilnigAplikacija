package com.example.pma_1.Classes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.pma_1.R;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.ArrayList;

public class CustomSpinnerAdapter extends ArrayAdapter {

    public CustomSpinnerAdapter(@NonNull Context context, ArrayList<CustomSpinnerItem> customSpinnerList) {
        super(context, 0, customSpinnerList);   //super -> parent constructor
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return customView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return customView(position, convertView, parent);
    }

    public View customView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {   //set custom spinner XML file into View "convertView" and return it
        if(convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_spinner_layout, parent, false); //xml file, parent, don't add it yet
        CustomSpinnerItem items = (CustomSpinnerItem)getItem(position);
        ImageView spinnerItemImage = convertView.findViewById(R.id.imgCustomSpinner);
        TextView spinnerItemText = convertView.findViewById(R.id.tvCustomSpinner);
        if(items != null) {
            spinnerItemImage.setImageResource(items.getSpinnerImage());
            spinnerItemText.setText((items.getSpinnerText()));
        }
        return convertView;
     }
}



