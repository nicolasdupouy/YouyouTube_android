package com.nicolasdupouy.youyoutube.testFragment;

import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.nicolasdupouy.youyoutube.testFragment.utility.PanelUtility;

public class HeadlinesFragment extends ListFragment {

    private OnHeadLineSelectedListener mCallBack;

    public interface OnHeadLineSelectedListener {
        public void onArticleSelected(int position);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (activity instanceof OnHeadLineSelectedListener) {
            mCallBack = (OnHeadLineSelectedListener) activity;
        } else {
            throw new ClassCastException(activity.toString() + "must implement OnHeadLineSelectedListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallBack = null;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setListAdapter(
                new ArrayAdapter<String>(
                        getActivity(),
                        android.R.layout.simple_list_item_1,
                        Ipsum.Headlines
                ));
    }

    @Override
    public void onStart() {
        super.onStart();

        if (PanelUtility.isDualPanel(getActivity())) {
            getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        mCallBack.onArticleSelected(position);

        getListView().setItemChecked(position, true);
    }
}
