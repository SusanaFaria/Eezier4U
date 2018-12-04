package susana.faria.eezier4u.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.widget.ImageView;
import android.widget.TextView;

@Entity(tableName = "people_table")
public class People implements Parcelable {

    @PrimaryKey

    private String person_id;

    @ColumnInfo(name = "name")
    private String mName;

    @ColumnInfo(name = "photo")
    private String mPhoto;

    public People(@NonNull String name, String photo) {

        this.mName = name;
        this.mPhoto = photo;}


    protected People(Parcel in) {
        person_id = in.readString();
        mName = in.readString();
        mPhoto = in.readString();
    }

    public static final Creator<People> CREATOR = new Creator<People>() {
        @Override
        public People createFromParcel(Parcel in) {
            return new People(in);
        }

        @Override
        public People[] newArray(int size) {
            return new People[size];
        }
    };

    public String getId () {return this.person_id;}

    public String getName(){
        return this.mName;
    }

    public String getPhoto(){
        return this.mPhoto;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(person_id);
        parcel.writeString(mName);
        parcel.writeString(mPhoto);
    }
}