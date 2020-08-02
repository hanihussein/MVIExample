package com.codingwithmitch.mviexample.ui.main.state

import com.codingwithmitch.mviexample.model.BlogPost
import com.codingwithmitch.mviexample.model.User

data class MainViewState(
    var user: User? = null,
    var blogPosts: List<BlogPost>? = null
)