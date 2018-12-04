package susana.faria.eezier4u.data;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;
@Dao
public interface PeopleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(People people);


    @Query("SELECT * FROM people_table WHERE person_id = :personId")
    LiveData<People> getPeopleById(String personId);


    @Query("SELECT * from people_table")
    LiveData<List<People>> getAll();

    @Query("DELETE FROM people_table")
    void deleteAll();

    @Update
    void updatePeople(People people);
}
