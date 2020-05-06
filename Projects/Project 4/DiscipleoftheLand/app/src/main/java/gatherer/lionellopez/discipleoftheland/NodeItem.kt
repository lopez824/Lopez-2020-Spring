package gatherer.lionellopez.discipleoftheland

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import java.io.Serializable
/*
Data that represents relevant item information for this app.
 */
@Entity
data class NodeItem (
    @Id var id:Long = 0,
    val itemName: String,
    val itemTime: Int,
    val itemArea: String,
    val itemClass: String,
    val itemRegion: String,
    var isFavorite: Boolean?
):Serializable