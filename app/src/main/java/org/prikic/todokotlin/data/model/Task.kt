package org.prikic.todokotlin.data.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "task")
data class Task(@ColumnInfo(name = "week_day") val weekDay: String,
                @ColumnInfo(name = "task_desc") val taskText: String) {
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true) var id: Long = 0
}

