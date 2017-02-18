package com.example.sahar.rssreader;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link StoryFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link StoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StoryFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    TextView title;
    TextView description;
    TextView pubDate;
    ImageView content;

    // TODO: Rename and change types of parameters
    private RssItem item;


    private OnFragmentInteractionListener mListener;

    public StoryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment StoryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StoryFragment newInstance(RssItem item) {
        StoryFragment fragment = new StoryFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_PARAM1, item);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            item = getArguments().getParcelable(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment title = (TextView)findViewById(R.id.title);
        View rootView = inflater.inflate(R.layout.fragment_story, container, false);
        title = (TextView)rootView.findViewById(R.id.title);
        description = (TextView) rootView.findViewById(R.id.description);
        pubDate = (TextView) rootView.findViewById(R.id.date);
        content = (ImageView) rootView.findViewById(R.id.image);
        LinearLayout rl = (LinearLayout) rootView.findViewById(R.id.parent);
        title.setText(item.getTitle());

        sentMessage(item.getTitle());
        if (item.getContent() == null) {
            content.setVisibility(View.GONE);
            rl.removeView(content);
        } else {
            Picasso.with(getActivity()).load(item.getContent()).into(content);
        }

        description.setText(item.getDescription());
        String[] date = item.getPubDate().split(" ");
        String[] time = date[4].split(":");
        String newDate = date[1] + " " + date[2] + ", " + time[0] + ":" + time[1];
        pubDate.setText(newDate);
        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void sentMessage(String title) {
        if (mListener != null) {
            mListener.onFragmentInteraction(title);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(String title);
    }
}
