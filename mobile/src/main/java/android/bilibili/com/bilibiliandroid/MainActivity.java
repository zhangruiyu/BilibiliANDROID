package android.bilibili.com.bilibiliandroid;

import android.bilibili.com.bilibiliandroid.base.BaseActivity;
import android.bilibili.com.bilibiliandroid.modular.homepage.HomepageFragment;
import android.bilibili.com.bilibiliandroid.modular.homepage.HomepagePresenter;
import android.bilibili.com.bilibiliandroid.ui.CircleImageDrawable;
import android.bilibili.com.bilibiliandroid.utils.ActivityUtils;
import android.bilibili.com.bilibiliandroid.utils.BitmapUtils;
import android.bilibili.com.bilibiliandroid.utils.ThemeUtils;
import android.bilibili.com.bilibiliandroid.utils.UIUtils;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private int theme = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //切换主题必须放在onCreate()之前
        if (savedInstanceState == null) {
            theme = ThemeUtils.getAppTheme(MainActivity.this);
        } else {
            theme = savedInstanceState.getInt("theme");
        }
        setTheme(theme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        toolbar.setLogo(new CircleImageDrawable(BitmapUtils.small(bitmap)));
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        HomepageFragment homepageFragment =
                (HomepageFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (homepageFragment == null) {
            homepageFragment = HomepageFragment.newInstance();
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), homepageFragment, R.id.contentFrame);
        }
        HomepagePresenter homepagePresenter = new HomepagePresenter(homepageFragment);
     /* TasksViewModel tasksViewModel =
             new TasksViewModel(getApplicationContext(), mTasksPresenter);
     homepageFragment.setViewModel(tasksViewModel);*/

    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_download) {
            UIUtils.showToast("下载");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_theme) {
            ThemeUtils.switchAppTheme(MainActivity.this);
            Intent intent = getIntent();
            overridePendingTransition(R.anim.activity_select_theme_in, R.anim.activity_select_theme_out);//进入动画
            finish();
            overridePendingTransition(R.anim.activity_select_theme_in, R.anim.activity_select_theme_out);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
