package com.example.roomwordsample.viewmodel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roomwordsample.R;
import com.example.roomwordsample.Word;

import java.util.List;

public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder> {

    private final LayoutInflater mInflater;
    private List<Word> mWords; //Cached copy of words

   public WordListAdapter(Context context){

        this.mInflater = LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new WordViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull WordViewHolder holder, int position) {

        if(mWords != null){

            Word current = mWords.get(position);
            holder.wordItemView.setText(current.getWord());
        }else{
            //Covers the case of data not being ready yet.

            holder.wordItemView.setText("No Words");
        }

    }

    @Override
    public int getItemCount() {
        if(mWords != null){
            return mWords.size();
        }else{
            return 0;
        }

    }

    void setWords(List<Word> words){

        mWords = words;
        notifyDataSetChanged();
    }

    class WordViewHolder extends RecyclerView.ViewHolder {
        private final TextView wordItemView;

        private WordViewHolder(View itemView){

            super(itemView);
            wordItemView = itemView.findViewById(R.id.textView);
        }
    }
}
