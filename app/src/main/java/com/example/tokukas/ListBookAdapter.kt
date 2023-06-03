package com.example.tokukas

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tokukas.databinding.BookItemBinding

class ListBookAdapter(private val books: List<Book>) :
    RecyclerView.Adapter<ListBookAdapter.ViewHolder>() {
    class ViewHolder(val binding: BookItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = BookItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val book = books[position]

        Glide.with(holder.itemView.context).load(book.photo)
            .placeholder(R.drawable.default_book_photo).into(holder.binding.imgBookPhoto)
        holder.binding.imgBookPhoto.contentDescription = book.title
        holder.binding.tvBookTitle.text = book.title
        holder.binding.tvBookPrice.text = "Rp" + book.price.toString()
    }

    override fun getItemCount(): Int = books.size
}
