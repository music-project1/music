package com.example.a123;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.a123.adapter.MusicAdapter;
import com.example.a123.listener.ItemClickListener;
import com.example.a123.listener.ItemInnerClickListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDraweLayout;
    private SearchView mSearchView;
    private CircleImageView profile_image;
    private View musicItem;
    private View headView;
    private Music[] music = {
            new Music("test1", R.drawable.bk1),
            new Music("test2", R.drawable.bk1),
            new Music("test3", R.drawable.back),
            new Music("test4", R.drawable.msg),
            new Music("test5", R.drawable.bk),
            new Music("test6", R.drawable.bk),
            new Music("test7", R.drawable.bk),
            new Music("test8", R.drawable.bk),
            new Music("test9", R.drawable.bk),
            new Music("test10", R.drawable.bk)};
    private List<Music> MusicList = new ArrayList<>();
    private MusicAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.firstsheet);
        Toolbar toolbar=(Toolbar)findViewById(R.id.appbar);
        setSupportActionBar(toolbar);

      mDraweLayout=(DrawerLayout)findViewById(R.id.drawer_layout);

        NavigationView navView=(NavigationView)findViewById(R.id.nav_view);
        ActionBar actionBar=getSupportActionBar();

     if(actionBar!=null)
           {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.settings);
        }
     /*未登录时点击头像登录*/
    headView = navView.getHeaderView(0);
    profile_image = headView.findViewById(R.id.header_icon);
    profile_image.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent=new Intent(MainActivity.this,LoginActivity.class);
            startActivity(intent);
        }
    });
    /*点击nav_menu各item的响应*/
     navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch(menuItem.getItemId())
                { case R.id.nav_massage:
                    Intent intent=new Intent(MainActivity.this,PlayActivity.class);
                    startActivity(intent);
                }
                return true;
            }
        });
    initMusic();
    RecyclerView recyclerView=(RecyclerView)findViewById(R.id.recycler_view);
    GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
    recyclerView.setLayoutManager(layoutManager);
    adapter = new MusicAdapter(MusicList);
    recyclerView.setAdapter(adapter);
    /*recyclerview的item点击事件*/
    adapter.setOnItemClickListener(new ItemClickListener() {
        public void itemClick(View view, int postion) {
            Toast.makeText(MainActivity.this,"click",Toast.LENGTH_SHORT).show();

        }
    }
    );
        adapter.setOnItemInnerClickListener(new ItemInnerClickListener() {
            @Override
            public void itemClick(int postion) {
                Toast.makeText(MainActivity.this,"thumb",Toast.LENGTH_SHORT).show();
            }
        }
        );
    }


  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return super.onCreateOptionsMenu(menu);
       }

    private void initMusic() {
    MusicList.clear();
    for (int i = 0; i < 50; i++) {
        Random random = new Random();
        int index = random.nextInt(music.length);
       MusicList.add(music[index]); }

}


  @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId())
        { case R.id.search:
            break;
            case android.R.id.home:
             mDraweLayout.openDrawer(GravityCompat.START);
        }
        return true;
    }
}
