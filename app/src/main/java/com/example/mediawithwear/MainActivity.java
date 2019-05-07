package com.example.mediawithwear;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSeekBar;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements MusicAdapter.SetOnClickItem {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.seek)
    AppCompatSeekBar seekBar;
    @BindView(R.id.recyc)
    RecyclerView recyc;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.play_music)
    ImageButton play;
    @BindView(R.id.pause_music)
    ImageButton pause;
    @BindView(R.id.time)
    TextView time;
    private MusicAdapter adapter;
    List<UrlModel> urlModels;
    int i = 0;
    MediaPlayer mediaPlayer;
    SearchView searchView;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search,menu);
        MenuItem sr = menu.findItem(R.id.search);
        searchView = (SearchView) sr.getActionView();
        searchView.setQueryHint("Поиск");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                newText = newText.toLowerCase();
                List<UrlModel> uri = new ArrayList<>();

                for (UrlModel urlModel : App.dm.getUrl())
                {
                    String name = urlModel.getName().toLowerCase();
                    if (name.contains(newText))
                    {
                        uri.add(urlModel);
                    }
                }

                adapter.setSearch(uri);

                return false;
            }
        });

        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
       // toolbar.setTitle("");
        setSupportActionBar(toolbar);
        urlModels = App.dm.getUrl();
        handleSeek();
       // mediaPlayer = MediaPlayer.create(this, Uri.parse(urlModels.get(i).getUrl()));
       // mediaPlayer.start();
        title.setText(urlModels.get(i).getName());
       // seekBarDur();
        recyc.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MusicAdapter(urlModels);
        adapter.notifyItemChanged(i);
        adapter.setSelectpos(i);
        adapter.notifyDataSetChanged();
        adapter.setSetOnClickItem(this);
        recyc.setAdapter(adapter);
        pause.setVisibility(View.VISIBLE);
        play.setVisibility(View.INVISIBLE);
    }

    @OnClick(R.id.next_butt)
    void doNextAct(){
        Intent intent = new Intent(this,Activitynext.class);
        startActivity(intent);
    }

    private void handleSeek() {
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (mediaPlayer!=null && b)
                {
                    mediaPlayer.seekTo(i*1000);
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
    @OnClick (R.id.play_music)
    void doPlay(){
        if (mediaPlayer.isPlaying())
        {
            mediaPlayer.stop();
            mediaPlayer.reset();
        }
        else {
            title.setText(urlModels.get(i).getName());
            mediaPlayer.start();
            seekBarDur();
        }
        pause.setVisibility(View.VISIBLE);
        play.setVisibility(View.INVISIBLE);

    }
    private void seekBarDur() {
        Handler thread = new Handler();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                seekBar.setMax(mediaPlayer.getDuration()/1000);
                seekBar.setProgress(mediaPlayer.getCurrentPosition()/1000);
                time.setText(ConvertD((long) mediaPlayer.getCurrentPosition()));
                thread.postDelayed(this,1000);
                if (seekBar.getProgress()==mediaPlayer.getDuration()/1000)
                {
                    doNext();
                }
            }
        });
    }
    @OnClick (R.id.next_music)
    void doNext(){
        i++;
        if (i>=urlModels.size()) {i=0;}
        mediaPlayer.pause();
        seekBar.setProgress(0);
        mediaPlayer = MediaPlayer.create(this, Uri.parse(urlModels.get(i).getUrl()));
        title.setText(urlModels.get(i).getName());

        adapter.notifyItemChanged(adapter.getSelectpos());
        adapter.setSelectpos(i);
        adapter.notifyDataSetChanged();
        seekBarDur();
        mediaPlayer.start();
        pause.setVisibility(View.VISIBLE);
        play.setVisibility(View.INVISIBLE);
    }
    @OnClick(R.id.back_music)
    void doBack(){
        i--;
        if (i==-1) {i=urlModels.size()-1;}
        mediaPlayer.pause();
        seekBar.setProgress(0);
        mediaPlayer = MediaPlayer.create(this, Uri.parse(urlModels.get(i).getUrl()));
        title.setText(urlModels.get(i).getName());

        adapter.notifyItemChanged(adapter.getSelectpos());
        adapter.setSelectpos(i);
        adapter.notifyDataSetChanged();
        seekBarDur();
        mediaPlayer.start();
        pause.setVisibility(View.VISIBLE);
        play.setVisibility(View.INVISIBLE);
    }
    @OnClick(R.id.pause_music)
    void doPause(){
        mediaPlayer.pause();
        pause.setVisibility(View.INVISIBLE);
        play.setVisibility(View.VISIBLE);
    }
    private String ConvertD(long currentPosition) {
        long min =(currentPosition/1000)/60;
        long sec = (currentPosition/1000)%60;
        String converted = String.format("%d:%02d",min,sec) ;
                return converted;
    }
    @Override
    public void startClick(UrlModel urlModel,int pos) {
        mediaPlayer.pause();
        seekBar.setProgress(0);
        i=pos;
        title.setText(urlModel.getName());
        mediaPlayer =MediaPlayer.create(this,Uri.parse(urlModel.getUrl()));
        adapter.notifyItemChanged(adapter.getSelectpos());
        adapter.setSelectpos(i);
        adapter.notifyDataSetChanged();
        seekBarDur();
        mediaPlayer.start();
        pause.setVisibility(View.VISIBLE);
        play.setVisibility(View.INVISIBLE);

    }
}
