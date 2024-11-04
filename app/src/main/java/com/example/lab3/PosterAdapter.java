package com.example.lab3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * This adapter class for managing a list of Poster objects in a Recyclerview
 * It helps the functionality for displaying each poster with details and handling selection states
 */
public class PosterAdapter extends RecyclerView.Adapter<PosterAdapter.PosterViewHolder> {
    private List<Poster> posterList;
    private PostersListener postersListener;

    /**
     * Retrieves the list of selected poster
     * @return A list of posters that are currently selected
     */
    public List<Poster> getSelectedPoster() {
        List<Poster> selectedPosters = new ArrayList<>();
        for (Poster poster: this.posterList) {
            if(poster.isSelected) {
                selectedPosters.add(poster);
            }
        }
        return selectedPosters;
    }

    /**
     * Constructor for posterAdapter
     * @param posterList                list of Poster objects to be displayed in the RecyclerView
     * @param postersListener           Listener for handling poster selection actions
     */
    public PosterAdapter(List<Poster> posterList, PostersListener postersListener) {
        this.posterList = posterList;
        this.postersListener = postersListener;
    }

    /**
     * The item layout for each poster and returns a ViewHolder
     * @param parent   The ViewGroup into which the new View will be added after it is bound to
     *                 an adapter position.
     * @param viewType The view type of the new View.
     * @return a new PosterViewHolder instance with the inflated layout
     */
    @NonNull
    @Override
    public PosterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PosterViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_container_poster, parent, false));
    }

    /**
     * Binds the data to each ViewHolder for a specific position
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull PosterViewHolder holder, int position) {
        holder.bindPosters(posterList.get(position));
    }

    /**
     * Returns the total number of items in the data set held by the adapter
     * @return the size of the posterlist
     */
    @Override
    public int getItemCount() {
        return posterList.size();
    }

    /**
     * ViewHolder class for holding vies related to a single poster item in the RecyclerView
     * It binds the data to the views and manages the slection state of each poster
     */
    class PosterViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout layoutPosters;
        View viewBackground;
        RoundedImageView imagePoster;
        TextView textName, textCreatedBy, textStory;
        RatingBar ratingBar;
        ImageView imageSelected;

        /**
         * Constructor for PosterViewHolser
         * @param itemView The view of the item container
         */
        public PosterViewHolder(@NonNull View itemView) {
            super(itemView);
            layoutPosters = itemView.findViewById(R.id.layoutPosters);
            viewBackground = itemView.findViewById(R.id.viewBackground);
            imagePoster = itemView.findViewById(R.id.imagePoster);
            textName = itemView.findViewById(R.id.TextName);
            textCreatedBy = itemView.findViewById(R.id.TextCreateBy);
            textStory = itemView.findViewById(R.id.TextStory);
            ratingBar = itemView.findViewById(R.id.ratingBar);
            imageSelected = itemView.findViewById(R.id.imageSelected);
        }

        /**
         * Binds the data from a Poster object to the views in the ViewHolder
         * Also manages the selection state by changing the background
         * @param poster the Poster object containing data for binding
         */
        void bindPosters(final Poster poster)  {
            imagePoster.setImageResource(poster.image);
            textName.setText(poster.name);
            textCreatedBy.setText(poster.createdBy);
            textStory.setText(poster.story);
            ratingBar.setRating(poster.rating);
            if(poster.isSelected) {
                viewBackground.setBackgroundResource(R.drawable.poster_selected_background);
                imageSelected.setVisibility(View.VISIBLE);
            }else {
                viewBackground.setBackgroundResource(R.drawable.poster_background);
                imageSelected.setVisibility(View.GONE);
            }
            layoutPosters.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(poster.isSelected) {
                        viewBackground.setBackgroundResource(R.drawable.poster_background);
                        imageSelected.setVisibility(View.GONE);
                        poster.isSelected = false;
                        if(getSelectedPoster().isEmpty()) {
                            postersListener.onPosterAction(false);
                        }

                    }else {
                        viewBackground.setBackgroundResource(R.drawable.poster_selected_background);
                        imageSelected.setVisibility(View.VISIBLE);
                        poster.isSelected = true;
                        postersListener.onPosterAction(true);

                    }
                }
            });
        }



    }
}
