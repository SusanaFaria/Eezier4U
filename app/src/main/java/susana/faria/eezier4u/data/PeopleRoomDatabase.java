package susana.faria.eezier4u.data;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

@Database(entities = {People.class}, version = 1)
public abstract class PeopleRoomDatabase extends RoomDatabase {

    public abstract PeopleDao peopleDao();

    private static volatile PeopleRoomDatabase INSTANCE;

    static PeopleRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (PeopleRoomDatabase.class) {
                if (INSTANCE == null) {
                    // Create database here
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            PeopleRoomDatabase.class, "people_database")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }
    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback(){

                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final PeopleDao mDao;

        PopulateDbAsync(PeopleRoomDatabase db) {
            mDao = db.peopleDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            mDao.deleteAll();

            return null;
        }
    }
}
