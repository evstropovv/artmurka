package com.artmurka.artmurkaapp.presenter.adapters;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.artmurka.artmurkaapp.R;

public class SearchSuggestionAdapter extends android.support.v4.widget.CursorAdapter {

    private static final String TAG = SearchSuggestionAdapter.class.getSimpleName();

    private LayoutInflater mLayoutInflater;

    public SearchSuggestionAdapter(Context context, Cursor cursor) {
        super(context, cursor, false);

        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {

        View view = mLayoutInflater.inflate(R.layout.search_suggestion_item, parent, false);

        TextView text = (TextView) view.findViewById(R.id.search_suggestion_item_text);

        view.setTag(text);

        return view;
    }


    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        if (cursor.moveToPosition(cursor.getPosition())) {
            String name = cursor.getString(1);
            ((TextView) view.getTag()).setText(name);
            Log.d(TAG, "bindView text is: " + name);
        }
    }
}