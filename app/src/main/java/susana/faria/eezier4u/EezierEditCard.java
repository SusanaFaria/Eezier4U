package susana.faria.eezier4u;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import susana.faria.eezier4u.data.People;
import susana.faria.eezier4u.data.PeopleViewModel;

public class EezierEditCard extends AppCompatActivity {

    public static final String EXTRA_REPLY = "REPLY";
    private static final int PICK_IMAGE_REQUEST = 0;
    private PeopleViewModel mPeopleViewModel;
    private Button choosePhotoBtn;
    private Button saveBtn;
    private ImageView mPhoto;
    private EditText mName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eezier_edit_card);

        mPeopleViewModel = ViewModelProviders.of(this).get(PeopleViewModel.class);

        Intent personEdit = getIntent();
        People people = personEdit.getParcelableExtra(EXTRA_REPLY);
        if (people == null) {
            setTitle("Add a Kid");
        }
        else {
            setTitle("Edit Kid");
            mPeopleViewModel.insert(people);
        }

        mName = findViewById(R.id.edit_person_name);
        mPhoto = findViewById(R.id.yourPhoto);
        choosePhotoBtn = findViewById(R.id.choose_photo);
        saveBtn = findViewById(R.id.save);

        mPeopleViewModel.insert(people);









    }
}
