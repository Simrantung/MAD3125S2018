package com.example.macstudent.danveer;


import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;


/**
 * A simple {@link Fragment} subclass.
 */
public class VideoFragment extends Fragment {


    public VideoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_video, container, false);


        VideoView vplayer = view.findViewById(R.id.videoPlayer);

        vplayer.setVideoURI(Uri.parse("android.resource://" +
                getActivity().getPackageName()+"/"+R.raw.toronto));

        MediaController mediaController = new MediaController(getActivity());
        mediaController.show(300);

        vplayer.setMediaController(mediaController);
        vplayer.pause();

        return view;
    }

}
