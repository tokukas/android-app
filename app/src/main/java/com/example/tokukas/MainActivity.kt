package com.example.tokukas

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.tokukas.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var books = ArrayList<Book>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvBooks.setHasFixedSize(true)

        books.addAll(getBooks())
        showRecyclerList()
    }

    private fun getBooks(): ArrayList<Book> {
        val booksData = ArrayList<Book>()
        val titles = resources.getStringArray(R.array.book_titles)
        val publishers = resources.getStringArray(R.array.book_publishers)
        val descriptions = resources.getStringArray(R.array.book_descriptions)
        val years = resources.getIntArray(R.array.book_years)
        val prices = resources.getIntArray(R.array.book_prices)
        val stocks = resources.getIntArray(R.array.book_stocks)
        val photos = resources.getStringArray(R.array.book_photos)

        for (i in titles.indices) {
            booksData.add(
                Book(
                    titles[i],
                    publishers[i],
                    descriptions[i],
                    years[i],
                    prices[i],
                    stocks[i],
                    photos[i],
                )
            )
        }

        return booksData
    }

    private fun showRecyclerList() {
        if (applicationContext.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            binding.rvBooks.layoutManager = GridLayoutManager(this, 3)
        } else {
            binding.rvBooks.layoutManager = GridLayoutManager(this, 2)
        }

        val listBookAdapter = ListBookAdapter(books) { book ->
            goToDetailBookActivity(book)
        }

        binding.rvBooks.adapter = listBookAdapter
    }

    private fun goToDetailBookActivity(book: Book) {
        val detailBookIntent = Intent(this@MainActivity, DetailBookActivity::class.java)
        detailBookIntent.putExtra(DetailBookActivity.EXTRA_BOOK, book)
        startActivity(detailBookIntent)
    }
}
