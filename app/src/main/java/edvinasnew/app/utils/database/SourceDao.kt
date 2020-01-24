package edvinasnew.app.utils.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface SourceDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(sources: List<SourceEntity>)

    @Query(
        """
        SELECT * FROM SourceEntity ORDER BY id ASC
    """
    )
    fun query(): List<SourceEntity>
}
