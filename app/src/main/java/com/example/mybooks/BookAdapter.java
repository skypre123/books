package com.example.mybooks;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mybooks.databinding.BookItemBinding;
import com.example.mybooks.models.BookModel;

import java.util.ArrayList;
import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {
    private final List<BookModel> array;

    public BookAdapter(List<BookModel> array) { this.array = array; }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(BookItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BookModel value = array.get(position);
        holder.binding.textTitle.setText(Html.fromHtml(value.getTitle()));
        holder.binding.textAuthor.setText(Html.fromHtml(value.getAuthor()));
        holder.binding.textDescription.setText(Html.fromHtml(value.getDescription()));

        Glide.with(this)
                .load(value.getImage())
                .into(holder.binding.image);

    }

    @Override
    public int getItemCount() {
        return array.size();
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {
        private final BookItemBinding binding;

        public ViewHolder(BookItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
