package com.nicolasdupouy.youyoutube.testFragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;

import com.nicolasdupouy.youyoutube.R;
import com.nicolasdupouy.youyoutube.testFragment.utility.PanelUtility;

public class NewsPresentationActivity extends Activity implements HeadlinesFragment.OnHeadLineSelectedListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_presentation);

        if (isRestoredFromPreviousState(savedInstanceState)) {
            return;
        }

        HeadlinesFragment headlinesFragment = new HeadlinesFragment();
        headlinesFragment.setArguments(getIntent().getExtras());
        if (PanelUtility.isMonoPanel(this)) {

            addFragmentWhenMonoPanel(headlinesFragment);
        } else {
            addheadlinesFragmentWhenDualPanel(headlinesFragment);

            ArticleFragment articleFragment = new ArticleFragment();
            addArticleFragmentWhenDualPanel(articleFragment);
        }
    }

    private boolean isRestoredFromPreviousState(Bundle savedInstanceState) {
        return savedInstanceState != null;
    }

    @Override
    public void onArticleSelected(int position) {

        if (PanelUtility.isDualPanel(this)) {
            ArticleFragment articleFragment = (ArticleFragment) getFragmentManager().findFragmentById(R.id.articleFragment);
            articleFragment.updateArticleView(position);
        } else {
            ArticleFragment articleFragment = new ArticleFragment();
            Bundle arguments = new Bundle();
            arguments.putInt(ArticleFragment.ARGUMENT_POSITION, position);
            articleFragment.setArguments(arguments);

            replaceFragmentWhenMonoPanel(articleFragment);
        }
    }

    private void addFragmentWhenMonoPanel(Fragment fragment) {
        addFragment(R.id.fragment_container, fragment);
    }

    private void addheadlinesFragmentWhenDualPanel(Fragment fragment) {
        addFragment(R.id.headlinesFragment, fragment);
    }

    private void addArticleFragmentWhenDualPanel(Fragment fragment) {
        addFragment(R.id.articleFragment, fragment);
    }

    private void addFragment(int frameLayoutId, Fragment fragment) {
        getFragmentManager()
                .beginTransaction()
                .add(frameLayoutId, fragment)
                .commit();
    }

    private void replaceFragmentWhenMonoPanel(Fragment fragment) {
        replaceFragment(R.id.fragment_container, fragment);
    }

    private void replaceFragment(int frameLayoutId, Fragment fragment) {
        getFragmentManager()
                .beginTransaction()
                .replace(frameLayoutId, fragment)
                .addToBackStack(null)
                .commit();
    }
}
