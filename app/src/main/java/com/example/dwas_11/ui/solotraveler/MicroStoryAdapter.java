package com.example.dwas_11.ui.solotraveler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dwas_11.R;
import com.example.dwas_11.model.MicroStory;

import java.util.List;

public class MicroStoryAdapter extends RecyclerView.Adapter<MicroStoryAdapter.MicroStoryViewHolder> {

    private final Context context;
    private final List<MicroStory> stories;

    public MicroStoryAdapter(Context context, List<MicroStory> stories) {
        this.context = context;
        this.stories = stories;
    }

    @NonNull
    @Override
    public MicroStoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_micro_story, parent, false);
        return new MicroStoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MicroStoryViewHolder holder, int position) {
        MicroStory story = stories.get(position);
        
        holder.storyTitle.setText(story.getTitle());
        holder.storyDistance.setText(story.getFormattedDistance());
        
        // Set icon based on type
        if (story.getType().equals("audio")) {
            holder.storyTypeIcon.setImageResource(R.drawable.ic_audio);
        } else if (story.getType().equals("video")) {
            holder.storyTypeIcon.setImageResource(R.drawable.ic_video);
        }
        
        // Set thumbnail if available
        if (story.getThumbnail() != null) {
            holder.storyImage.setImageBitmap(story.getThumbnail());
        }
        
        holder.itemView.setOnClickListener(v -> {
            Toast.makeText(context, "Playing: " + story.getTitle(), Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return stories.size();
    }

    static class MicroStoryViewHolder extends RecyclerView.ViewHolder {
        ImageView storyImage;
        TextView storyTitle;
        TextView storyDistance;
        ImageView storyTypeIcon;

        MicroStoryViewHolder(@NonNull View itemView) {
            super(itemView);
            storyImage = itemView.findViewById(R.id.story_image);
            storyTitle = itemView.findViewById(R.id.story_title);
            storyDistance = itemView.findViewById(R.id.story_distance);
            storyTypeIcon = itemView.findViewById(R.id.story_type_icon);
        }
    }
} 