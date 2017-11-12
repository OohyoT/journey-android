package com.journey.app.ui.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.journey.app.R;
import com.journey.app.model.CardItem;
import com.journey.app.ui.adapter.CardListAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CardListFragment extends Fragment {

    public interface OnViewTravelListener {
        void onViewTravel(int travelId);
    }

    private OnViewTravelListener onViewTravelListener;

    public void setOnViewTravelListener(OnViewTravelListener listener) {
        onViewTravelListener = listener;
    }

    @BindView(R.id.srl) SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.recycler) RecyclerView recyclerView;

    private CardListAdapter adapter;

    @SuppressLint("ValidFragment")
    public CardListFragment(Context context) {
        adapter = new CardListAdapter(context);
        adapter.setOnViewTravelListener(new CardListAdapter.OnViewTravelListener() {
            @Override public void onViewTravel(int travelId) {
                if (onViewTravelListener != null) {
                    onViewTravelListener.onViewTravel(travelId);
                }
            }
        });
    }

    public void startRefreshing() {
        if (swipeRefreshLayout != null) {
            swipeRefreshLayout.setRefreshing(true);
        }
    }

    public void stopRefreshing() {
        new CountDownTimer(1000, 1000) {

            @Override public void onTick(long millisUntilFinished) {

            }

            @Override public void onFinish() {
                if (swipeRefreshLayout != null) {
                    swipeRefreshLayout.setRefreshing(false);
                }
            }

        }.start();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_card_list, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        initSrl();
        initRecycler();
    }

    public interface OnRefreshListener {
        void onRefresh();
    }

    private OnRefreshListener onRefreshListener;

    public void setOnRefreshListener(OnRefreshListener listener) {
        this.onRefreshListener = listener;
    }

    private void initSrl() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override public void onRefresh() {
                if (onRefreshListener != null) {
                    onRefreshListener.onRefresh();
                }
            }
        });
    }

    private void initRecycler() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
    }

    public void setItems(List<CardItem> items) {
        adapter.setItems(items);
        adapter.notifyItemRangeChanged(0, items.size());
    }

}
