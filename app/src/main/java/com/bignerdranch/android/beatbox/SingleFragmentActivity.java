package com.bignerdranch.android.beatbox;


import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.SeekBar;
import android.widget.Toast;

public abstract class SingleFragmentActivity extends AppCompatActivity {
    protected abstract Fragment createFragment();
    private SeekBar mseekbar;
    private BeatBox mbeatbox;
    protected int getLayoutResId() {
        return R.layout.activity_fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        FragmentManager manager = getSupportFragmentManager();
        Fragment fragment = manager.findFragmentById(R.id.fragment_container);

        if (fragment == null) {
            fragment = createFragment();
            manager.beginTransaction()
                .add(R.id.fragment_container, fragment)
                .commit();
        }

        MediaPlayer player = MediaPlayer.create(this, Settings.System.DEFAULT_RINGTONE_URI);
        player.setLooping(true);
        player.start();

        mseekbar = findViewById(R.id.seekbar);
        mseekbar.setMax(4);
        mseekbar.setProgress(2);
        mseekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (progress == 0){
                Toast.makeText(getBaseContext(),"PlayBack Speed: Very Slow",Toast.LENGTH_SHORT).show();
                    mbeatbox.setRATE(0);
                } else if (progress == 1){
                    Toast.makeText(getBaseContext(),"PlayBack Speed: Slow",Toast.LENGTH_SHORT).show();
                    mbeatbox.setRATE(1);
                }else if (progress == 2){
                    Toast.makeText(getBaseContext(),"PlayBack Speed: Normal",Toast.LENGTH_SHORT).show();
                    mbeatbox.setRATE(2);
                }else if (progress == 3){
                    Toast.makeText(getBaseContext(),"PlayBack Speed: Fast",Toast.LENGTH_SHORT).show();
                    mbeatbox.setRATE(3);
                }else if (progress == 4){
                    Toast.makeText(getBaseContext(),"PlayBack Speed: Very Fast",Toast.LENGTH_SHORT).show();
                    mbeatbox.setRATE(4);
                }
            }


            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
