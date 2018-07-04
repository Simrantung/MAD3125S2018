package com.example.macstudent.danveer;


import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;


/**
 * A simple {@link Fragment} subclass.
 */
public class AudioFragment extends Fragment {

    MediaPlayer mediaPlayer;
    int startTime = 0;
    int finalTime = 0;
    Handler myHandler = new Handler();
    int seekTime = 5000;
    SeekBar seekBarProgress;
    TextView txtDuration;
    ImageButton imgPlay, imgPause, imgForward, imgRewind;

    public AudioFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_audio, container, false);

    seekBarProgress = (SeekBar)view.findViewById(R.id.seekBarProgress);
    imgPause = view.findViewById(R.id.imgPause);
    imgPlay = view.findViewById(R.id.imgPlay);
    imgForward = view.findViewById(R.id.imgForward);
    imgRewind = view.findViewById(R.id.imgRewind);

    //Create MediaPlayer
    mediaPlayer = MediaPlayer.create(getActivity(),R.raw.dichotomy);
    finalTime = mediaPlayer.getDuration();
    startTime = mediaPlayer.getCurrentPosition();
        seekBarProgress.setMax((int) finalTime);


    txtDuration = (TextView)view.findViewById(R.id.txtDuration);

        imgPlay.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mediaPlayer.start();
            seekBarProgress.setProgress((int)startTime);
            myHandler.postDelayed(UpdateSongTime,100);
        }
    });

        imgPause.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mediaPlayer.pause();
        }
    });

        imgForward.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int currentPosition = mediaPlayer.getCurrentPosition();
            if(currentPosition + seekTime <= mediaPlayer.getDuration()){
                mediaPlayer.seekTo(currentPosition + seekTime);
            }else{
                mediaPlayer.seekTo(mediaPlayer.getDuration());
            }
        }
    });

        imgRewind.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int currentPosition = mediaPlayer.getCurrentPosition();
            if(currentPosition - seekTime >= 0){
                mediaPlayer.seekTo(currentPosition - seekTime);
            }else{
                mediaPlayer.seekTo(0);
            }
        }
    });

        return view;
}
    private Runnable UpdateSongTime = new Runnable() {
        public void run() {
            startTime = mediaPlayer.getCurrentPosition();
            txtDuration.setText(String.format("%d min, %d sec",
                    TimeUnit.MILLISECONDS.toMinutes((long) startTime),
                    TimeUnit.MILLISECONDS.toSeconds((long) startTime) -
                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.
                                    toMinutes((long) startTime)))
            );
            seekBarProgress.setProgress((int)startTime);
            myHandler.postDelayed(this, 100);
        }
    };

}






