package com.example

import android.content.Context
import io.flutter.FlutterInjector
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.embedding.engine.FlutterEngineGroup
import io.flutter.embedding.engine.dart.DartExecutor

object FlutterEngineManager {

    private lateinit var groups: FlutterEngineGroup

    private val cache get() = FlutterEngineCache.getInstance()

    private val mainEntrypoint
        get() = DartExecutor.DartEntrypoint(
            FlutterInjector.instance().flutterLoader().findAppBundlePath(), "main"
        )

    fun init(context: Context) {
        groups = FlutterEngineGroup(context)
    }

    fun create(context: Context, id: String): FlutterEngine {
        var engine = cache.get(id)
        if (engine != null) return engine
        engine = groups.createAndRunEngine(context, mainEntrypoint)
        cache.put(id, engine)
        return engine
    }

    fun destroy(id: String) {
        cache.get(id)?.destroy()
        cache.remove(id)
    }

}