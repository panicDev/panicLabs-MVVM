package id.panicLabs.wancak.postlist.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import id.panicLabs.wancak.postlist.R
import kotlinx.android.synthetic.main.activity_post_list.*

/**
 * @author panicLabs
 * @createdOn 24/11/2017
 */
class PostListActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_list)
        setSupportActionBar(toolbar)
    }
}