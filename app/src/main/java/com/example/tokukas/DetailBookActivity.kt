package com.example.tokukas

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.tokukas.databinding.ActivityDetailBookBinding

class DetailBookActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBookBinding

    companion object {
        const val EXTRA_BOOK = "extra_book"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBookBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val book = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(EXTRA_BOOK, Book::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_BOOK)
        }

        if (book != null) {
            Glide.with(this).load(book.photo).placeholder(R.drawable.default_book_photo)
                .into(binding.imgBookPhoto)
            binding.imgBookPhoto.contentDescription = book.title
            binding.tvBookTitle.text = book.title
            binding.tvBookPrice.text = "Rp" + book.price.toString()
            binding.tvBookStock.text = book.stock.toString()
            binding.tvBookPublisher.text = book.publisher
            binding.tvBookYear.text = book.year.toString()
            binding.tvBookDescription.text = book.description
        }
    }
}
