package susana.faria.eezier4u;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import susana.faria.eezier4u.data.People;
import susana.faria.eezier4u.data.PeopleViewModel;

public class PeopleListAdapter extends RecyclerView.Adapter<PeopleListAdapter.PeopleViewHolder> {

    public static final String EXTRA_REPLY = "EXTRA_REPLY";
    private PeopleViewModel mPeopleViewModel;
    private Context mContext;

    public static class PeopleViewHolder extends RecyclerView.ViewHolder {
        TextView nameTxt;
        ImageView imgView;
        Button edPerson;
        Button delPerson;
        RelativeLayout rl;


        private PeopleViewHolder(View itemView) {
            super(itemView);
            nameTxt = itemView.findViewById(R.id.name);
            imgView = itemView.findViewById(R.id.photo);
            edPerson = itemView.findViewById(R.id.edit);
            delPerson = itemView.findViewById(R.id.delete);
            rl = itemView.findViewById(R.id.item);
        }
    }

    private List<People> mPeople;
    private People person;

    // data is passed into the constructor
    public PeopleListAdapter(Context context) {

        this.mContext = context;
        mPeopleViewModel = ViewModelProviders.of((FragmentActivity) mContext).get(PeopleViewModel.class);
    }


    @NonNull
    @Override
    public PeopleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.main_recycler_view, parent, false);
        return new PeopleViewHolder(view);

    }



    @Override
    public void onBindViewHolder(@NonNull final PeopleViewHolder holder, final int position) {


        People current = mPeople.get(position);
        holder.nameTxt.setText(current.getName());

        String photoUri = current.getPhoto();
        Uri personPhoto = Uri.parse(photoUri);
        holder.imgView.setImageURI(personPhoto);
        holder.rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent cardActivity = new Intent(mContext, MainActivity2.class);
                mContext.startActivity(cardActivity);

            }
        });


        holder.edPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                People current = mPeople.get(position);
                Intent personEdit = new Intent(mContext, EezierEditCard.class);
                personEdit.putExtra(EXTRA_REPLY, current);
                mContext.startActivity(personEdit);
            }
        });

        holder.delPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                People current = mPeople.get(position);


            }
        });

    }

    void setPeople(List<People> people){
        mPeople = people;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
return mPeople.size();
    }


}



