package edvinasnew.app.utils.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface SourceDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(sources: List<SourceEntity>): Completable

    @Query(
        """
        SELECT * FROM SourceEntity ORDER BY id ASC
    """
    )
    fun query(): Single<List<SourceEntity>>

    @Query(
        """
        SELECT * FROM SourceEntity ORDER BY title DESC
    """
    )
    fun queryDESC(): Single<List<SourceEntity>>

    @Query(
        """
            SELECT * FROM SourceEntity WHERE upper(title) LIKE '%' || upper(:searchText) || '%'
        """
    )
    fun getSourcesBySearch(searchText: String): Single<List<SourceEntity>>
}
