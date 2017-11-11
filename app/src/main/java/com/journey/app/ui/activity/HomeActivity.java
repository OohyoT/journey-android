package com.journey.app.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.journey.app.R;
import com.journey.app.api.AuthApi;
import com.journey.app.api.JourneyApi;
import com.journey.app.model.CardItem;
import com.journey.app.model.Fragment;
import com.journey.app.model.FragmentCardItem;
import com.journey.app.model.Travel;
import com.journey.app.model.TravelCardItem;
import com.journey.app.model.User;
import com.journey.app.ui.fragment.CardListFragment;
import com.journey.app.util.Api;
import com.journey.app.util.Auth;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {

    private final static int REQUEST_AUTH = 0;

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.container) ViewPager viewPager;
    @BindView(R.id.tabs) TabLayout tabs;
    @BindView(R.id.fab) FloatingActionButton fab;

    private SectionsPagerAdapter sectionsPagerAdapter;

    private Drawer drawer;
    private AccountHeader accountHeader;
    private ProfileDrawerItem profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ButterKnife.bind(this);

        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (checkAuthStatus()) {
            loadLoggedInUser();
        }
        loadHomeTimeline();
        loadExplore();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_AUTH && resultCode == RESULT_OK) {
            loadLoggedInUser();
        } else {
            finish();
        }
    }

    private void initView() {
        initToolbar();
        initDrawer();
        initTabs();
        initFab();
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
    }

    private void initDrawer() {
        profile = new ProfileDrawerItem()
                .withIdentifier(1L)
                .withName("User")
                .withIcon(R.drawable.avatar)
                .withEmail("12345678912");
        accountHeader = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.color.orange_500)
                .addProfiles(profile)
                .withOnAccountHeaderSelectionViewClickListener(new AccountHeader.OnAccountHeaderSelectionViewClickListener() {
                    @Override public boolean onClick(View view, IProfile profile) {
                        return false;
                    }
                })
                .withSelectionListEnabled(false)
                .build();

        drawer = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .withAccountHeader(accountHeader)
                .build();
    }

    private void initTabs() {
        sectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        sectionsPagerAdapter.setOnViewTravelListener(new SectionsPagerAdapter.OnViewTravelListener() {
            @Override public void onViewTravel(int travelId) {
                Intent viewTravelIntent = new Intent(HomeActivity.this, TravelActivity.class);
                viewTravelIntent.putExtra("TRAVEL_ID", travelId);
                startActivity(viewTravelIntent);
            }
        });

        viewPager.setAdapter(sectionsPagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabs));

        tabs.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0) {
                    fab.show();
                } else {
                    fab.hide();
                }
            }

            @Override public void onTabUnselected(TabLayout.Tab tab) {
                // Ignored
            }

            @Override public void onTabReselected(TabLayout.Tab tab) {
                // Ignored
            }
        });
    }

    private void initFab() {
        // TODO: init fab
    }

    private boolean checkAuthStatus() {
        if (!Auth.hasLoggedIn()) {
            Intent authIntent = new Intent(this, EntryActivity.class);
            startActivityForResult(authIntent, REQUEST_AUTH);
            return false;
        }

        return true;
    }

    private void loadLoggedInUser() {
        int loggedInUserId = Auth.getLoggedInUserId();
        AuthApi api = Api.getAuthApiInstance();
        Call<User> getUser = api.getUser(loggedInUserId);
        getUser.enqueue(new Callback<User>() {
            @Override public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();
                if (user == null) {
                    Auth.logout();
                    checkAuthStatus();
                } else {
                    profile.withName(user.username);
                    accountHeader.updateProfile(profile);
                }
            }

            @Override public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }

    private void loadHomeTimeline() {
        JourneyApi api = Api.getApiInstance();
        Call<ArrayList<com.journey.app.model.Fragment>> getFragments = api.getHomeFragments();
        getFragments.enqueue(new Callback<ArrayList<com.journey.app.model.Fragment>>() {
            @Override public void onResponse(Call<ArrayList<Fragment>> call, Response<ArrayList<com.journey.app.model.Fragment>> response) {
                ArrayList<com.journey.app.model.Fragment> fragments = response.body();
                sectionsPagerAdapter.setHomeTimeline(fragments);
            }

            @Override public void onFailure(Call<ArrayList<Fragment>> call, Throwable t) {
                //
            }
        });
    }

    private void loadExplore() {
        JourneyApi api = Api.getApiInstance();
        Call<ArrayList<Travel>> getTravels = api.getHomeTravels();
        getTravels.enqueue(new Callback<ArrayList<Travel>>() {
            @Override public void onResponse(Call<ArrayList<Travel>> call, Response<ArrayList<Travel>> response) {
                ArrayList<Travel> travels = response.body();
                sectionsPagerAdapter.setExplore(travels);
            }

            @Override public void onFailure(Call<ArrayList<Travel>> call, Throwable t) {
                //
            }
        });
    }

    public static class SectionsPagerAdapter extends FragmentPagerAdapter {

        public interface OnViewTravelListener {
            void onViewTravel(int travelId);
        }

        private OnViewTravelListener onViewTravelListener;

        public void setOnViewTravelListener(OnViewTravelListener listener) {
            onViewTravelListener = listener;
        }

        private CardListFragment homeFragment = new CardListFragment();
        private CardListFragment exploreFragment = new CardListFragment();
        private CardListFragment topicsFragment = new CardListFragment();

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);

            exploreFragment.setOnViewTravelListener(new CardListFragment.OnViewTravelListener() {
                @Override public void onViewTravel(int travelId) {
                    if (onViewTravelListener != null) {
                        onViewTravelListener.onViewTravel(travelId);
                    }
                }
            });
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            switch (position) {
                case 0:
                default:
                    return homeFragment;
                case 1:
                    return exploreFragment;
                case 2:
                    return topicsFragment;
            }
        }

        @Override
        public int getCount() {
            return 3;
        }

        public void setHomeTimeline(List<Fragment> fragments) {
            List<CardItem> items = new ArrayList<>();
            for (Fragment fragment : fragments) {
                FragmentCardItem item = new FragmentCardItem();
                item.fragment = fragment;
                items.add(item);
            }
            homeFragment.setItems(items);
        }

        public void setExplore(List<Travel> travels) {
            List<CardItem> items = new ArrayList<>();
            for (Travel travel : travels) {
                TravelCardItem item = new TravelCardItem(travel);
                items.add(item);
            }
            exploreFragment.setItems(items);
        }

    }
}
