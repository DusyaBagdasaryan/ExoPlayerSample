package com.sample.exoplayer

import android.content.Context
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.RawResourceDataSource

/**
 * @author Dusya Bagdasaryan on 30.01.2023
 */
class MediaItemProvider {
    fun provideRemoteMediaItems(): List<MediaItem> {
        return buildList {
            add(MediaItem.fromUri("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"))
            add(MediaItem.fromUri("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4"))
            add(MediaItem.fromUri("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerBlazes.mp4"))
            add(MediaItem.fromUri("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerEscapes.mp4"))
            add(MediaItem.fromUri("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerFun.mp4"))
            add(MediaItem.fromUri("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerJoyrides.mp4"))
            add(MediaItem.fromUri("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerMeltdowns.mp4"))
            add(MediaItem.fromUri("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/Sintel.mp4"))
            add(MediaItem.fromUri("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/SubaruOutbackOnStreetAndDirt.mp4"))
            add(MediaItem.fromUri("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/TearsOfSteel.mp4"))
            add(MediaItem.fromUri("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/VolkswagenGTIReview.mp4"))
            add(MediaItem.fromUri("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/WeAreGoingOnBullrun.mp4"))
            add(MediaItem.fromUri("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/WhatCarCanYouGetForAGrand.mp4"))
        }
    }

    fun provideLocalMediaItemsSource(context: Context): List<MediaSource> {
        val rawResourceDataStore = RawResourceDataSource(context)
        val mediaItemOcean = MediaItem.Builder()
            .setMediaId("ocean")
            .setUri(RawResourceDataSource.buildRawResourceUri(R.raw.ocean))
            .build()
        val mediaItemKoala = MediaItem.Builder()
            .setMediaId("koala")
            .setUri(RawResourceDataSource.buildRawResourceUri(R.raw.koala))
            .build()
        val progressiveMediaSource = ProgressiveMediaSource.Factory { rawResourceDataStore }
        return buildList {
            add(progressiveMediaSource.createMediaSource(mediaItemOcean))
            add(progressiveMediaSource.createMediaSource(mediaItemKoala))
        }
    }
}
