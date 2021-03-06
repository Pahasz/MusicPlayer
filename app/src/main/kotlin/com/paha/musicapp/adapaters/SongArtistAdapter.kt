package com.paha.musicapp.adapaters

import android.content.Context
import android.support.v4.app.FragmentManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.paha.musicapp.R
import com.paha.musicapp.listeners.AlbumOnClickListener
import com.paha.musicapp.objects.SongInfo

class SongArtistAdapter(private val parentContext:Context, private val fragmentManager: FragmentManager,
                        private val list:List<Pair<String, List<SongInfo>>>) : BaseAdapter() {
    private var lastPosition:Int = 0

    private class ViewHolder {
        internal lateinit var artistName: TextView
        internal lateinit var songAmount: TextView
    }

    var totalCount = 0

    init {
        list.forEach { it.second.forEach { totalCount++ } } //Count each one...
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var convertView = convertView

        // Get the data item for this position
        val dataModel = getItem(position) as Pair<String, List<SongInfo>>
        // Check if an existing view is being reused, otherwise inflate the view
        val viewHolder: ViewHolder // view lookup cache stored in tag

        if (convertView == null) {

            viewHolder = ViewHolder()
            val inflater = LayoutInflater.from(parentContext)
            convertView = inflater.inflate(R.layout.song_artist_layout, parent, false)

            viewHolder.artistName = convertView.findViewById(R.id.artist_name)
            viewHolder.songAmount = convertView.findViewById(R.id.artist_song_amount)
            convertView.tag = viewHolder
        } else {
            viewHolder = convertView.tag as ViewHolder
        }

        lastPosition = position

        viewHolder.artistName.text = dataModel.first
        viewHolder.songAmount.text = dataModel.second.size.toString()

        convertView!!.setOnClickListener(AlbumOnClickListener(fragmentManager, parentContext, dataModel.second))

        // Return the completed view to render on screen
        return convertView
    }

    override fun getItem(position: Int): Any {
        return list[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int = list.size
}