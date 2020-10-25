package com.example.a123;


import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextSwitcher;
import android.widget.Toast;

import com.example.a123.service.MusicService;

import java.text.SimpleDateFormat;
import java.util.Date;


public class PlayActivity extends AppCompatActivity implements View.OnClickListener,
        Runnable, ServiceConnection, SeekBar.OnSeekBarChangeListener {
    private ImageView playingPre,playingPlay,playingNext,image;
    private boolean isPlaying = true;//0,1 判断是否处于播放状态
    //声明服务
    private static final String TAG = MainActivity.class.getSimpleName();
    private MusicService.MusicController mMusicController;
    //使用方法：mMusicController.play();播放   mMusicController.pause();暂停
    private boolean running;
    private TextSwitcher mSwitcher;
    private SeekBar mSeekBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play);
        //设置透明栏

        ImageView back=(ImageView)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(PlayActivity.this,MainActivity.class);

                startActivity(intent);

            }
        });

        //滑动条部分
        mSeekBar = (SeekBar) findViewById(R.id.seek_bar);
        mSeekBar.setOnSeekBarChangeListener(this);
        mSwitcher = (TextSwitcher) findViewById(R.id.switcher);
        mSwitcher.setInAnimation(this, android.R.anim.fade_in);
        mSwitcher.setOutAnimation(this, android.R.anim.fade_out);
        Intent intent = new Intent(this, MusicService.class);
        //增加StartService，来增加后台播放功能
        startService(intent);
        bindService(intent, this, BIND_AUTO_CREATE);


        initViews();
    }

    private void initViews() {
        playingPre = (ImageView) findViewById(R.id.pre);
        playingPlay = (ImageView) findViewById(R.id.play);
        playingNext = (ImageView) findViewById(R.id.next);

        playingPre.setOnClickListener(PlayActivity.this);
        playingPlay.setOnClickListener(PlayActivity.this);
        playingNext.setOnClickListener(PlayActivity.this);
    }



    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.pre:
                playing();
                Toast.makeText(this,"pre",Toast.LENGTH_SHORT).show();
                break;
            case R.id.play:
            {  if (isPlaying) {
                   playing();
                } else {
                    mMusicController.pause();
                    playingPlay.setImageResource(R.drawable.play);
                    isPlaying = true;
                    Intent intent = new Intent(this, MusicService.class);
                    stopService(intent);
                }
                Toast.makeText(this,"play",Toast.LENGTH_SHORT).show();
                break;}

        //下一曲
        case R.id.next:
        playing();
        break;
        default:
        break;
    }
    }

    private void playing(){
        playingPlay.setImageResource(R.drawable.pause);
        mMusicController.play();
        isPlaying = false;
    }

    @Override
    protected void onStart() {
        super.onStart();
        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    protected void onStop() {
        running = false;
        super.onStop();
    }

    @Override
    protected void onDestroy() {

        unbindService(this);
        super.onDestroy();
    }


    @Override
    public void run() {
        running = true;
        try {
            while (running) {
                if (mMusicController != null) {
                    long musicDuration = mMusicController.getMusicDuration();
                    final long position = mMusicController.getPosition();
                    final Date dateTotal = new Date(musicDuration);
                    final SimpleDateFormat sb = new SimpleDateFormat("mm:ss");
                    mSeekBar.setMax((int) musicDuration);
                    mSeekBar.setProgress((int) position);
                    mSwitcher.post(
                            new Runnable() {
                                @Override
                                public void run() {
                                    Date date = new Date(position);
                                    String time = sb.format(date) + "/" + sb.format(dateTotal);
                                    mSwitcher.setCurrentText(time);
                                }
                            }
                    );
                }

                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        mMusicController = ((MusicService.MusicController) service);
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        mMusicController = null;
    }

    public void btnStopService(View view) {
        Intent intent = new Intent(this, MusicService.class);
        stopService(intent);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        mMusicController.setPosition(seekBar.getProgress());
    }
}
