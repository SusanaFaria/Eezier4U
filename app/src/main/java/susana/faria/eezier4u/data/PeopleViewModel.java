package susana.faria.eezier4u.data;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

public class PeopleViewModel extends AndroidViewModel {

    private PeopleRepository mRepository;

    private LiveData<List<People>> mAllPeople;

    public PeopleViewModel (Application application) {
        super(application);
        mRepository = new PeopleRepository(application);
        mAllPeople = mRepository.getAll();
    }

    public LiveData<List<People>> getAll() { return mAllPeople; }

    public void insert(People people) { mRepository.insert(people); }

    public void delete (People people) {mRepository.delete(people);}
}