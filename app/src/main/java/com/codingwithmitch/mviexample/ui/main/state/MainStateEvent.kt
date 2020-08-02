package com.codingwithmitch.mviexample.ui.main.state

sealed class MainStateEvent {

    class BlogStateEvent :MainStateEvent()

    class UserStateEvent(val userID:String):MainStateEvent()

    class None :MainStateEvent()
}