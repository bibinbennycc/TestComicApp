package com.codetest.comicsapp.feature.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.codetest.comicsapp.R
import com.codetest.comicsapp.common.Status
import com.codetest.comicsapp.feature.model.ComicModel
import com.codetest.comicsapp.feature.viewmodel.ComicViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: ComicViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setObservers()
        getCurrentComic()
    }

    private fun getCurrentComic() {
        progress_bar.visibility = View.VISIBLE
        viewModel.getCurrentComicInfo()
    }

    private fun setObservers() {
        viewModel.currentComic.observe(this, { response ->
            progress_bar.visibility = View.GONE
            when (response.status) {
                Status.SUCCESS -> setComicInfo(response.data)
                Status.ERROR -> handleComicInfoError(response.message)
                else -> handleComicInfoError(getString(R.string.something_went_wrong))
            }
        })
    }

    private fun setComicInfo(comicModel: ComicModel?) {
        if (!comicModel?.imageUrl.isNullOrEmpty()) {
            Glide.with(baseContext).load(comicModel?.imageUrl)
                .into(comic_image)
        } else {
            comic_image.setImageResource(R.drawable.ic_launcher_foreground)
        }
        comic_title.text = if (!comicModel?.title.isNullOrEmpty()) comicModel?.title else "Comic"
        if (!comicModel?.subtitle.isNullOrEmpty()) {
            comic_description.isVisible = true
            comic_description.text = comicModel?.subtitle
        } else {
            comic_description.isVisible = false
        }
    }

    private fun handleComicInfoError(message: String?) {
        setComicInfo(null)
        Toast.makeText(
            this,
            message ?: getString(R.string.something_went_wrong),
            Toast.LENGTH_SHORT
        ).show()
    }
}