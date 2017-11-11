package com.journey.app.ui.adapter;

import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.journey.app.R;
import com.journey.app.model.CardItem;
import com.journey.app.model.FragmentCardItem;
import com.journey.app.model.Travel;
import com.journey.app.model.TravelCardItem;
import com.journey.app.util.CheatUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CardListAdapter extends RecyclerView.Adapter<CardListAdapter.CardViewHolder> {

    private List<CardItem> items = new ArrayList<>();

    public void setItems(List<CardItem> items) {
        this.items = items;
    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == CardItem.FRAGMENT) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_fragment, parent, false);
            return new FragmentCardViewHolder(view);
        } else if (viewType == CardItem.TRAVEL) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_travel, parent, false);
            return new TravelCardViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(CardViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case CardItem.FRAGMENT:
                com.journey.app.model.Fragment fragment = ((FragmentCardItem) items.get(position)).fragment;
                FragmentCardViewHolder fragmentHolder = (FragmentCardViewHolder) holder;

                fragmentHolder.content.setText(fragment.content);
                fragmentHolder.location.setText(fragment.location);
                if (fragment.imageId > 0) {
                    fragmentHolder.image.setVisibility(View.VISIBLE);
                    int imageResId = CheatUtil.getImageResId(fragment.imageId);
                    fragmentHolder.image.setImageResource(imageResId);
                }

                break;
            case CardItem.TRAVEL:
                final Travel travel = ((TravelCardItem) items.get(position)).travel;
                TravelCardViewHolder travelHolder = (TravelCardViewHolder) holder;

                travelHolder.title.setText(travel.title);
                travelHolder.location.setText(travel.location);
                travelHolder.likeCount.setText(String.valueOf(travel.likeCount));
                travelHolder.image.setImageResource(CheatUtil.getImageResId(travel.imageId));
                travelHolder.setOnTravelCardClickListener(new TravelCardViewHolder.OnTravelCardClickListener() {
                    @Override public void onCLick() {
                        if (onViewTravelListener != null) {
                            onViewTravelListener.onViewTravel(travel.id);
                        }
                    }
                });

                break;
            case CardItem.TOPIC:
                // TODO
                break;
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override public int getItemViewType(int position) {
        return items.get(position).type;
    }

    public interface OnViewTravelListener {
        void onViewTravel(int travelId);
    }

    private OnViewTravelListener onViewTravelListener;

    public void setOnViewTravelListener(OnViewTravelListener listener) {
        onViewTravelListener = listener;
    }

    static class CardViewHolder extends RecyclerView.ViewHolder {
        public CardViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    static class FragmentCardViewHolder extends CardViewHolder {
        @BindView(R.id.card) CardView card;
        @BindView(R.id.image) ImageView image;
        @BindView(R.id.content) TextView content;
        @BindView(R.id.avatar) ImageView avatar;
        @BindView(R.id.nickname) TextView nickname;
        @BindView(R.id.time) TextView time;
        @BindView(R.id.location) TextView location;
        @BindView(R.id.comment) AppCompatImageButton comment;
        @BindView(R.id.like) AppCompatImageButton like;

        public FragmentCardViewHolder(View itemView) {
            super(itemView);
        }
    }

    static class TravelCardViewHolder extends CardViewHolder {
        @BindView(R.id.card) CardView card;
        @BindView(R.id.image) ImageView image;
        @BindView(R.id.avatar) ImageView avatar;
        @BindView(R.id.title) TextView title;
        @BindView(R.id.location) TextView location;
        @BindView(R.id.like_count) TextView likeCount;
        @BindView(R.id.comment_count) TextView commentCount;

        public TravelCardViewHolder(View itemView) {
            super(itemView);

            card.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    if (listener != null) {
                        listener.onCLick();
                    }
                }
            });
        }

        public interface OnTravelCardClickListener {
            void onCLick();
        }

        private OnTravelCardClickListener listener;

        public void setOnTravelCardClickListener(OnTravelCardClickListener listener) {
            this.listener = listener;
        }
    }

}