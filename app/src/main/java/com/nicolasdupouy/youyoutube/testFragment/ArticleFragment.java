package com.nicolasdupouy.youyoutube.testFragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.nicolasdupouy.youyoutube.R;

public class ArticleFragment extends Fragment {

    public static final String ARGUMENT_POSITION = "argumentPosition";
    private int mCurrentPosition = -1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (savedInstanceState != null) {
            mCurrentPosition = savedInstanceState.getInt(ARGUMENT_POSITION);
        }

        return inflater.inflate(R.layout.fragment_article, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        Bundle args = getArguments();
        if (args != null) {
            updateArticleView(args.getInt(ARGUMENT_POSITION));
        }
        else if (mCurrentPosition != -1) {
            updateArticleView(mCurrentPosition);
        }
    }

    public void updateArticleView(int position) {
        TextView article = (TextView) getView().findViewById(R.id.article);
        if (article != null) {
            article.setText(Ipsum.Articles[position]);
        }
        else {
            Toast.makeText(getActivity(), "TextView article null !", Toast.LENGTH_LONG).show();
        }
        mCurrentPosition = position;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt(ARGUMENT_POSITION, mCurrentPosition);
    }
}
