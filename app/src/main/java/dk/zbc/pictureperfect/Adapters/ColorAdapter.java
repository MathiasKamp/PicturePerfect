package dk.zbc.pictureperfect.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import dk.zbc.pictureperfect.Models.Color;
import dk.zbc.pictureperfect.R;

/**
 * this class represents a colorAdapter which is used to show color objects in a listView
 */

public class ColorAdapter extends ArrayAdapter<Color> {
    public ColorAdapter(@NonNull Context context, int resource, @NonNull List<Color> colors) {
        super(context, resource, colors);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View currentItemView =convertView;

        Color color = getItem(position);

        if (convertView == null){

            currentItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_items, parent, false);
        }

        TextView tvColorDominant = (currentItemView).findViewById(R.id.DominantColor);

        tvColorDominant.setText(color.toString());

        return currentItemView;
    }
}
