package com.example.tokukas

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
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

        supportActionBar?.title = "Book"

        val book = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(EXTRA_BOOK, Book::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_BOOK)
        }

        if (book != null) {
            bindBook(book)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail_book, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_share -> {
                val text =
                    "Get the book \"${binding.tvBookTitle.text}\" in ${resources.getString(R.string.app_name)} app right now! Only ${binding.tvBookPrice.text}."
                val sendIntent = Intent(Intent.ACTION_SEND).apply {
                    putExtra(Intent.EXTRA_TEXT, text)
                    type = "text/plain"
                }
                val shareIntent = Intent.createChooser(sendIntent, null)
                startActivity(shareIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun bindBook(book: Book) {
        with(binding) {
            Glide.with(this@DetailBookActivity).load(book.photo)
                .placeholder(R.drawable.default_book_photo)
                .into(imgBookPhoto)
            imgBookPhoto.contentDescription = book.title
            tvBookTitle.text = book.title
            tvBookPrice.text = "Rp" + book.price.toString()
            tvBookStock.text = book.stock.toString()
            tvBookPublisher.text = book.publisher
            tvBookYear.text = book.year.toString()
            tvBookDescription.text = book.description
        }
    }
}
