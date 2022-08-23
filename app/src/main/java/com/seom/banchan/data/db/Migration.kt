package com.seom.banchan.data.db

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val MIGRATION_1_2 = object : Migration(1,2){ // 1 -> 2로 버전이 바뀔 때 Migration하게함
    override fun migrate(database: SupportSQLiteDatabase) {
        //1 -> 2로 바뀔 때 변경사항 적용
        // ex) database.execSQL("CREATE TABLE `Review` (`id` INTEGER, `name` TEXT)")
    }
}