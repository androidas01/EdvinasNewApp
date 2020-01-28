package edvinasnew.app.utils.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(articles: List<ArticleEntity>)

    @Delete
    fun delete(articles: List<ArticleEntity>)

    @Query(
        value = """
                SELECT * FROM ArticleEntity WHERE sourceId = :sourceId AND chipId = :chipId order by publishedAt
            """
    )
    fun query(sourceId: String, chipId: Int): List<ArticleEntity>

    @Query(
        """
            UPDATE ArticleEntity SET favorite = NOT favorite WHERE url = :url
        """
    )
    fun changeFavoriteStatus(url: String)

    @Query(
        """
            DELETE FROM ArticleEntity WHERE chipId = :chipId AND sourceId = :sourceId AND NOT favorite
        """
    )
    fun deleteAll(sourceId: String, chipId: Int)

    @Query(
        value = """
                SELECT * FROM ArticleEntity WHERE favorite
            """
    )
    fun getFavorite(): List<ArticleEntity>
}