//package com.example.randomjokesgenerator.load.data
//
//import com.google.gson.Gson
//import java.io.Serializable
//
//interface CacheDataSource {
//
////    fun save(data: )
//
//    fun read():
//
//    class Base(
//        private val stringCache: StringCache,
//        private val gson: Gson
//    ) : CacheDataSource {
//
//        override fun save(data: List<String>) {
//            val serialisedWrapper = gson.toJson(ListWrapper(data))
//            stringCache.save(serialisedWrapper)
//
//            // val stringFromList = data.joinToString(",")
//            // stringCache.save(stringFromList)
//        }
//
//        override fun read(): List<String> {
//            val serialisedWrapper = stringCache.read()
//            return gson.fromJson(serialisedWrapper, ListWrapper::class.java).list
//
//            // return stringCache.read().split(",")
//        }
//    }
//}
//
//data class ListWrapper(
//    val list: List<String>
//) : Serializable