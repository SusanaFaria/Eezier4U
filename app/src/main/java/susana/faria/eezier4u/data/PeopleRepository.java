package susana.faria.eezier4u.data;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.RoomDatabase;
import android.os.AsyncTask;

import java.util.List;

public class PeopleRepository {
    private PeopleDao mPeopleDao;
    private LiveData<List<People>> mAllPeople;

    PeopleRepository(Application application) {
        PeopleRoomDatabase db = PeopleRoomDatabase.getDatabase(application);
        mPeopleDao = db.peopleDao();
        mAllPeople = mPeopleDao.getAll();
    }


    LiveData<People> getPeopleId(String peopleId) {
        return mPeopleDao.getPeopleById(peopleId);
    }
    LiveData<List<People>> getAll() {
        return mAllPeople;
    }


    public void insert (People people) {
        new insertAsyncTask(mPeopleDao).execute(people);
    }

    void delete(People people) {
        mPeopleDao.deleteAll();
    }

    private static class insertAsyncTask extends AsyncTask<People, Void, Void> {

        private PeopleDao mAsyncTaskDao;

        insertAsyncTask(PeopleDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final People... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}

