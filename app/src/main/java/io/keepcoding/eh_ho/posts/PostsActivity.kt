package io.keepcoding.eh_ho.posts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import io.keepcoding.eh_ho.R
import kotlinx.android.synthetic.main.activity_posts.*
import java.lang.IllegalArgumentException
import io.keepcoding.eh_ho.*
import io.keepcoding.eh_ho.data.Topic
import io.keepcoding.eh_ho.data.UserRepo
import io.keepcoding.eh_ho.login.LoginActivity
import io.keepcoding.eh_ho.posts.EXTRA_TOPIC_ID
import io.keepcoding.eh_ho.posts.PostsActivity

const val EXTRA_TOPIC_ID = "topic_id"
const val TRANSACTION_CREATE_POST = "create_post"

class PostsActivity : AppCompatActivity(), PostsFragment.PostsInteractionListener, CreatePostFragment.CreatePostInteractionListener {

    var topic_ID: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_posts)

        val topicId = intent.getStringExtra(EXTRA_TOPIC_ID)

        if(topicId != null && topicId.isNotEmpty()) {
            topic_ID = topicId.toInt()
        } else {
            throw IllegalArgumentException("You should provide an id for the topic")
        }


        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainer, PostsFragment(topic_ID))
                .commit()
        }


    }

    //Métodos interfaz
    override fun onGoToCreatePost() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer,CreatePostFragment())
            .addToBackStack(TRANSACTION_CREATE_POST)
            .commit()
    }


    override fun onPostCreated() {
        supportFragmentManager.popBackStack()
    }

    override fun onPostSelected() {

    }
    //-------------

}
