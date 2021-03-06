package com.skyshi.twonavigationdrawer;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {
    private RelativeLayout mDrawerLeft;
    private RelativeLayout mDrawerRight;
    private RelativeLayout mDrawerView;
    private DrawerLayout mDrawerLayout;
    private Toolbar mToolbar;
    private ActionBarDrawerToggle mDrawerToggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        //setContentView(R.layout.two_drawer_activity_layout);
        setContentView(R.layout.two_drawer_activity_layout);
        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        mDrawerLeft = (RelativeLayout)findViewById(R.id.drawerLeft);
        mDrawerRight = (RelativeLayout)findViewById(R.id.drawerRight);
        mDrawerView = (RelativeLayout)findViewById(R.id.mainView);
        mToolbar = (Toolbar)findViewById(R.id.toolBar);
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_menu2);
        setTwoNavDrawer();
        /*TwoDrawerActivity tda = new TwoDrawerActivity(this,mDrawerLayout,mDrawerLeft,mDrawerView,mDrawerRight,mToolbar);
        tda.setTwoNavDrawer();*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        //return true;
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_right_drawer){
            if(mDrawerLayout.isDrawerOpen(mDrawerLeft)){
                mDrawerLayout.closeDrawer(mDrawerLeft);
            }
            if(mDrawerLayout.isDrawerOpen(mDrawerRight)){
                mDrawerLayout.closeDrawer(mDrawerRight);
            }else {
            //if(){
                mDrawerLayout.openDrawer(mDrawerRight);
            }
        }
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            if(mDrawerLayout.isDrawerOpen(mDrawerRight)){
                mDrawerLayout.closeDrawer(mDrawerRight);
            }
            if(mDrawerLayout.isDrawerOpen(mDrawerLeft)) {
                mDrawerLayout.closeDrawer(mDrawerLeft);
            }else{
                mDrawerLayout.openDrawer(mDrawerLeft);
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    public void setTwoNavDrawer(){
        mDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout,R.string.app_name,R.string.app_name){
            @Override
            public void onDrawerClosed(View view) {
                //super.onDrawerClosed(view);
                if(view.equals(mDrawerRight)) {
                    mDrawerLayout.closeDrawer(mDrawerRight);
                }else{
                    mDrawerLayout.closeDrawer(mDrawerLeft);
                }
                //invalidateOptionsMenu();
                supportInvalidateOptionsMenu();
                mDrawerToggle.syncState();
            }

            @Override
            public void onDrawerOpened(View view) {
                //super.onDrawerOpened(view);
                if(view.equals(mDrawerRight)) {
                    mDrawerLayout.openDrawer(mDrawerRight);
                }else{
                    mDrawerLayout.openDrawer(mDrawerLeft);
                }
                supportInvalidateOptionsMenu();
                mDrawerToggle.syncState();
            }

            @Override
            public void onDrawerSlide(View view, float slideOffset) {
                if(view.equals(mDrawerLeft)){
                    if(mDrawerLayout.isDrawerOpen(mDrawerRight)){
                        mDrawerLayout.closeDrawer(mDrawerRight);
                    }
                    super.onDrawerSlide(mDrawerLeft, slideOffset);
                    mDrawerView.setTranslationX(slideOffset * mDrawerLeft.getWidth());
                    mDrawerLayout.bringChildToFront(mDrawerLeft);
     //              mDrawerLayout.setScrimColor(getColor(MainActivity.this, R.color.fadingDrawer));
                    mDrawerLayout.requestLayout();
                }else{
                    if(mDrawerLayout.isDrawerOpen(mDrawerLeft)){
                        mDrawerLayout.closeDrawer(mDrawerLeft);
                    }
                    super.onDrawerSlide(mDrawerLeft, slideOffset);
                    mDrawerView.setTranslationX(slideOffset * -mDrawerRight.getWidth());
                    mDrawerLayout.bringChildToFront(mDrawerRight);
     //               mDrawerLayout.setScrimColor(getColor(MainActivity.this,R.color.fadingDrawer));
                    mDrawerLayout.requestLayout();
                }
            }
        };
        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

}
