package com.sample.exoplayer

import android.os.Bundle
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.ui.StyledPlayerView

/**
 * @author Dusya Bagdasaryan on 30.01.2023
 */
class PlayerActivity : AppCompatActivity() {
    private lateinit var progressBar: ProgressBar
    private lateinit var playerView: StyledPlayerView
    private lateinit var player: ExoPlayer
    private val mediaItemProvider by lazy(LazyThreadSafetyMode.NONE) {
        MediaItemProvider()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)
        progressBar = findViewById(R.id.progress)
        playerView = findViewById(R.id.playerView)
        initExoPlayer()
    }

    private fun initExoPlayer() {
        player = ExoPlayer.Builder(this).build()
        player.repeatMode = Player.REPEAT_MODE_ALL
        player.playWhenReady = true
        playerView.player = player
        player.addListener(object : Player.Listener {
            override fun onPlaybackStateChanged(playbackState: Int) {
                super.onPlaybackStateChanged(playbackState)
                progressBar.isVisible = playbackState == Player.STATE_BUFFERING
            }
        })
        addRemoteMediaItems()
//         addLocalMediaItems()
        player.prepare()
    }

    override fun onResume() {
        super.onResume()
        player.play()
    }

    override fun onPause() {
        super.onPause()
        player.pause()
    }

    override fun onDestroy() {
        super.onDestroy()
        player.release()
    }

    /**
     * Adds to player videos links (remote videos)
     */
    private fun addRemoteMediaItems() {
        player.clearMediaItems()
        mediaItemProvider.provideRemoteMediaItems().forEach { mediaItem ->
            player.addMediaItem(mediaItem)
        }
    }

    /**
     * Adds to player videos from the device (local videos)
     */
    private fun addLocalMediaItems() {
        player.clearMediaItems()
        mediaItemProvider.provideLocalMediaItemsSource(this).forEach { mediaSource ->
            player.addMediaSource(mediaSource)
        }
    }
}
