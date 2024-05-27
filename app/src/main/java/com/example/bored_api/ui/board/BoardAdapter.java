package com.example.bored_api.ui.board;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageSwitcher;

import androidx.annotation.NonNull;
import androidx.appcompat.app.WindowDecorActionBar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bored_api.Prefs;
import com.example.bored_api.R;
import com.example.bored_api.databinding.ItemBoardBinding;

public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.ViewHolder> {

    ItemBoardBinding binding;
    NavController navController;

    private int [] images = new int[] {R.drawable.ic_launcher_background,
                                        R.drawable.ic_launcher_background,
                                        R.drawable.ic_launcher_background};

    private String [] title = new String[]{
            "Willst du etwas für die Menschen und die Erde tun?",
            "Wollen Sie etwas für die Menschen tun?",
            "Willst du etwas für dich selbst tun?"
    };

    @NonNull
    @Override
    public BoardAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemBoardBinding
                .inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BoardAdapter.ViewHolder holder, int position) {
        holder.bind(position);
    }


    @Override
    public int getItemCount() {
        return images.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(ItemBoardBinding itemView) {
            super(itemView.getRoot());
        }

        public void bind(int position) {

            binding.textBoard.setText(title[position]);
            binding.imageBoard.setImageResource(images[position]);


            if(position == images.length-1){
                binding.btnStart.setVisibility(View.VISIBLE);
            }else {
                binding.btnStart.setVisibility(View.INVISIBLE);
            }

            binding.btnStart.setOnClickListener(v->{

                new Prefs((Activity)itemView.getContext()).saveBoardState();

                navController = Navigation.findNavController((Activity) itemView.getContext(),
                        R.id.nav_host);
                navController.navigate(R.id.action_navigation_board_to_navigation_home);
            });
        }
    }





}
