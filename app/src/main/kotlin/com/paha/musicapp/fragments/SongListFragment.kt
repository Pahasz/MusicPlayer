package com.paha.musicapp.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import com.paha.musicapp.R
import com.paha.musicapp.adapaters.SongListAdapter
import com.paha.musicapp.interfaces.SongDataLoaded
import com.paha.musicapp.objects.SongInfo
import com.paha.musicapp.tasks.LoadSongsTask
import com.paha.musicapp.util.SongsUtil

class SongListFragment(private val songList:List<SongInfo>) : Fragment(), SongDataLoaded {
    init {
        LoadSongsTask.listeners += this
    }

    constructor():this(listOf())

    private lateinit var arrayAdapter: SongListAdapter

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = View.inflate(context, R.layout.song_list_layout, null)
        return view
    }

    override fun onStart() {
        super.onStart()
//        view!!.findViewById<ProgressBar>(R.id.progress_loader).visibility = View.VISIBLE
        onSongDataLoaded(SongsUtil.allSongs, SongsUtil.songsByArtistMap, SongsUtil.songsByAlbumMap)
    }

    override fun onSongDataLoaded(allSongs: List<SongInfo>, songsByArtist: HashMap<String, List<SongInfo>>, songsByAlbum: HashMap<String, List<SongInfo>>) {
        if (allSongs.isEmpty() || view == null)
            return

        arrayAdapter = SongListAdapter(activity, fragmentManager, songList.toMutableList())
        val listView = view!!.findViewById<ListView>(R.id.all_songs_list_view)
        listView.adapter = arrayAdapter

//        view!!.findViewById<ProgressBar>(R.id.progress_loader).visibility = View.GONE
    }
}