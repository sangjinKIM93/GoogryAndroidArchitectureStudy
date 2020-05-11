package com.sangjin.newproject

import android.app.Application
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.sangjin.newproject.data.model.Movie
import com.sangjin.newproject.adapter.MovieListAdapter
import com.sangjin.newproject.data.model.ResponseData
import com.sangjin.newproject.data.repository.NaverMovieRepository
import com.sangjin.newproject.data.repository.NaverMovieRepositoryImpl
import com.sangjin.newproject.data.source.local.LocalDataSourceImpl
import com.sangjin.newproject.data.source.local.RoomDB
import com.sangjin.newproject.data.source.remote.RemoteDataSourceImpl
import kotlinx.android.synthetic.main.activity_movie_list.*

class MovieListActivity : AppCompatActivity() {

    private val naverMovieRepositoryImpl : NaverMovieRepositoryImpl by lazy{
        NaverMovieRepositoryImpl(
            RemoteDataSourceImpl(),
            LocalDataSourceImpl(),
            RoomDB.getInstance(this).movieDao
        )
    }
    private var movieList = ArrayList<Movie>()
    private lateinit var movieListAdapter: MovieListAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)

        setRecyclerView()

        movieNameET.setOnEditorActionListener { v, actionId, event ->
            if(actionId == EditorInfo.IME_ACTION_SEARCH){

                onClick(v)

                true
            }else{
                false
            }
        }

        showKeyPad()

    }

    /**
     * 키패드 보여주기
     */
    private fun showKeyPad(){
        movieNameET.requestFocus()
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE)
    }


    /**
     * 키패드 숨기기
     */
    private fun hideKeyPad(v: View){
        val imm : InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(v.windowToken, 0)
    }


    /**
     * 검색 이벤트
     */
    fun onClick(view: View) {

        val keyWord = movieNameET.text.toString().trim()

        if(TextUtils.isEmpty(keyWord)){
            Toast.makeText(this, R.string.no_keyword, Toast.LENGTH_LONG).show()
        }else{
            getMovieList(keyWord)
            hideKeyPad(view)
        }

    }

    /**
     * 검색 결과 받아서 출력
     */
    private fun getMovieList(keyWord: String){

        naverMovieRepositoryImpl.getNaverMovie(keyWord, object : NaverMovieRepository.LoadMoviesCallback{

            override fun onSuccess(movies: List<Movie>) {

                if(movies.isNullOrEmpty()){
                    movieList.clear()
                    movieListAdapter.addList(movieList)
                    Toast.makeText(this@MovieListActivity, R.string.no_movie_list, Toast.LENGTH_SHORT).show()
                } else {
                    movieList.clear()
                    movieList.addAll(movies)
                    movieListAdapter.addList(movieList)
                }
            }

            override fun onFailure(t: Throwable) {
                Toast.makeText(this@MovieListActivity, t.toString(), Toast.LENGTH_SHORT).show()
            }

        })

    }


    /**
     * 리사이클러뷰 셋팅
     */
    private fun setRecyclerView(){

        //각 항목 클릭시 이벤트 처리
        val onItemClickListener: ((Int) -> Unit) = {position ->
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(movieList.get(position).link))
            startActivity(intent)
        }

        movieListAdapter = MovieListAdapter(onItemClickListener)
        movieListView.adapter = movieListAdapter


    }


}
