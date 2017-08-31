package com.artmurka.artmurkaapp.Views.Fragments;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.artmurka.artmurkaapp.Model.Databases.Preferences;
import com.artmurka.artmurkaapp.Presenter.CategorySettingsPresenter;
import com.artmurka.artmurkaapp.Presenter.InterfacesPresenter.ICategorySettingsPresenter;
import com.artmurka.artmurkaapp.R;
import com.artmurka.artmurkaapp.Views.Fragments.Interfaces.ICategorySettings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class CategorySettings extends Fragment implements ICategorySettings {
    private ICategorySettingsPresenter presenter;
    private ImageView ivMicrosoft, ivList;
    private Button btnApply;
    private RadioGroup radioGroup;

    private Spinner spinnerDate, spinnerPrice;
    private Integer currentSelect = 2;

    public CategorySettings() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        presenter = new CategorySettingsPresenter(this);
        View view = inflater.inflate(R.layout.fragment_category_settings, container, false);

        btnApply = (Button) view.findViewById(R.id.btnApply);
        ivList = (ImageView) view.findViewById(R.id.ivList);
        ivMicrosoft = (ImageView) view.findViewById(R.id.ivMicrosoft);
        radioGroup = (RadioGroup) view.findViewById(R.id.radioGroup);
        radioGroup.check(Preferences.getCheckedRadioButton());
        setIvListener();
        changeIconsColor(Preferences.getListSettings());
        btnApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String, String> settings = new HashMap<String, String>();
                Preferences.setCheckedRadioButton(radioGroup.getCheckedRadioButtonId());
                switch (radioGroup.getCheckedRadioButtonId()) {
                    case R.id.rbLowCost:
                        Toast.makeText(view.getContext(), "Дешево!", Toast.LENGTH_SHORT).show();
                        settings.put("sort", "price");
                        settings.put("order", "ask");
                        break;
                    case R.id.rbExpensive:
                        Toast.makeText(view.getContext(), "дорого!", Toast.LENGTH_SHORT).show();
                        settings.put("sort", "price");
                        settings.put("order", "desc");
                        break;
                    case R.id.rbName:
                        Toast.makeText(view.getContext(), "По имени!", Toast.LENGTH_SHORT).show();
                        settings.put("sort", "name");
                        settings.put("order", "ask");
                        break;
                    case R.id.rvNew:
                        Toast.makeText(view.getContext(), "Новые!", Toast.LENGTH_SHORT).show();
                        settings.put("sort", "added_time");
                        settings.put("order", "ask");
                        break;
                    case R.id.rbOld:
                        Toast.makeText(view.getContext(), "Старые!", Toast.LENGTH_SHORT).show();
                        settings.put("sort", "added_time");
                        settings.put("order", "desc");
                        break;
                    default:
                        Toast.makeText(view.getContext(), "По имени!", Toast.LENGTH_SHORT).show();
                        settings.put("sort", "name");
                        settings.put("order", "ask");
                        break;
                }
                presenter.applyChanges(settings, currentSelect);
                Preferences.setListSettings(currentSelect);
                FragmentManager fm = getActivity().getSupportFragmentManager();
                ItemListFragment itemList = new ItemListFragment();
                Bundle bundle = new Bundle();
                bundle.putString("url", Preferences.getListUrl());
                bundle.putString("sort", settings.get("sort"));
                bundle.putString("order", settings.get("order"));
                bundle.putInt("list", currentSelect);
                itemList.setArguments(bundle);
                fm.beginTransaction()
                        .replace(R.id.mainFrame, itemList)
                        .addToBackStack(null)
                        .commit();
                fm.executePendingTransactions();
            }
        });
        return view;
    }

    private void setIvListener() {

        ivList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeIconsColor(1);
            }
        });

        ivMicrosoft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeIconsColor(2);
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        try {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Фільтри");
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }


    private void changeIconsColor(Integer id) {

        this.currentSelect = id;

        if (id == 1) {
            ivList.setImageDrawable(getResources().getDrawable(R.drawable.microsoft_pink));
            ivMicrosoft.setImageDrawable(getResources().getDrawable(R.drawable.view_list));
        } else {
            ivList.setImageDrawable(getResources().getDrawable(R.drawable.microsoft));
            ivMicrosoft.setImageDrawable(getResources().getDrawable(R.drawable.viewlist_pink));
        }
    }
}
