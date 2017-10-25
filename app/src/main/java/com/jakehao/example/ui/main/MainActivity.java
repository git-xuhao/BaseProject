package com.jakehao.example.ui.main;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.jakehao.example.ui.gank.ContentFragment;
import com.jakehao.example.R;
import com.jakehao.example.rxjava.RxSchedulers;
import com.jakehao.example.ui.base.BaseActivity;
import com.jakehao.example.utils.BitmapUtil;
import com.jakehao.example.utils.EventHelper;
import com.jakehao.example.utils.ResourceUtil;
import com.jakehao.example.utils.StatusBarUtil;

import butterknife.BindView;
import rx.Observable;
import rx.functions.Action1;

/**
 *
 * Created by zwl on 16/9/5.
 */
public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener{
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.container)
    FrameLayout mContainer;
    @BindView(R.id.fab)
    FloatingActionButton mFab;
    @BindView(R.id.nav_view)
    NavigationView mNavView;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    public static void startActivity(Context mContext) {
        Intent intent = new Intent(mContext, MainActivity.class);
        mContext.startActivity(intent);
    }

    private ImageView mHeadBgImg, mAvatorImg;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initInjector() {
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void initEventAndData() {
        StatusBarUtil.setColorForDrawerLayout(this, mDrawerLayout, ResourceUtil.getThemeColor(this), 0);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        View headerView = mNavView.inflateHeaderView(R.layout.nav_header_main);
        mHeadBgImg = (ImageView) headerView.findViewById(R.id.head_image);
        mAvatorImg = (ImageView) headerView.findViewById(R.id.imageView);
        EventHelper.click(this, mFab, mAvatorImg);
        EventHelper.setNavigationItemSelected(this, mNavView);

        getBitmap();

        setNewRootFragment(R.id.nav_gank);
    }

    /**
     * 获取侧滑背景并进行模糊 灰白处理
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void getBitmap() {
        Observable.just(R.mipmap.avator)
                .map(integer -> {
                    return BitmapUtil.matrixBitmap(MainActivity.this, integer);
                }).map(bitmap -> {
            return BitmapUtil.blurBitmap(this, bitmap, 15.5f);
        }).compose(RxSchedulers.schedulersTransformer)
             .subscribe(new Action1<Bitmap>() {
                 @Override
                 public void call(Bitmap bitmap) {
                     mHeadBgImg.setImageBitmap(bitmap);
                 }
             });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        setNewRootFragment(item.getItemId());
        return true;
    }

    private void setNewRootFragment(int menuId){
        Fragment fragment = null;
        switch (menuId){
            case R.id.nav_gank:
                fragment = new ContentFragment();
                break;
            case R.id.nav_gallery:
                fragment = new MeituMainFragment();
                break;
//            case R.id.nav_manage:
//                break;
//            case R.id.nav_send:
//                break;
//            case R.id.nav_share:
//                break;
//            case R.id.nav_slideshow:
//                break;
        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        if(null == fragment) return;
        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
    }
}
