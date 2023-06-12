package net.hwyz.iov.data.user.local.database

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey
    var userId: String = "",
    var username: String? = null,
    @Embedded
    var token: Token? = null

) {
    data class Token(
        var token: String? = null
    )
}