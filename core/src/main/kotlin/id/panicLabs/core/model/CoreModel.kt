package id.panicLabs.core.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * @author panicLabs
 * @createdOn 24/11/2017
 */
@Entity
data class CoreModel(
        @PrimaryKey
        var id: Int
)