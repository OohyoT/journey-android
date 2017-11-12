package com.journey.app.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.journey.app.R;
import com.journey.app.api.JourneyApi;
import com.journey.app.model.CardItem;
import com.journey.app.model.Fragment;
import com.journey.app.model.FragmentCardItem;
import com.journey.app.ui.adapter.CardListAdapter;
import com.journey.app.util.Api;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyFragmentsActivity extends AppCompatActivity {

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.recycler) RecyclerView recyclerView;

    private CardListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_fragments);

        ButterKnife.bind(this);
        initView();

        loadFragments();
    }

    private void initView() {
        initToolbar();
        initRecycler();
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void initRecycler() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CardListAdapter(this);
        recyclerView.setAdapter(adapter);
    }

    private void loadFragments() {
        int userId = getIntent().getIntExtra("USER_ID", 0);
        if (userId <= 0) {
            finish();
            return;
        }

        JourneyApi api = Api.getApiInstance();
        Call<ArrayList<Fragment>> getUserFragments = api.getUserFragments(userId);
        getUserFragments.enqueue(new Callback<ArrayList<Fragment>>() {
            @Override public void onResponse(Call<ArrayList<Fragment>> call, Response<ArrayList<Fragment>> response) {
                List<Fragment> fragments = response.body();
                List<CardItem> items = new ArrayList<>();
                for (Fragment fragment : fragments) {
                    FragmentCardItem item = new FragmentCardItem();
                    item.fragment = fragment;

                    int insertPosition = 0;
                    while (insertPosition < items.size()) {
                        FragmentCardItem i = (FragmentCardItem) items.get(insertPosition);
                        if (i.fragment.id <= fragment.id) {
                            insertPosition += 1;
                        }
                    }
                    items.add(insertPosition, item);
                }
                adapter.setItems(items);
                adapter.notifyDataSetChanged();
            }

            @Override public void onFailure(Call<ArrayList<Fragment>> call, Throwable t) {

            }
        });
    }

}
