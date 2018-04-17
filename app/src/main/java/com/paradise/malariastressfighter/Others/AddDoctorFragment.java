package com.paradise.malariastressfighter.Others;


import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.StorageReference;
import com.paradise.malariastressfighter.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddDoctorFragment extends Fragment {

    ImageView imageView;
    EditText email, name, desc;

    private StorageReference storageReference;
    private DatabaseReference databaseReference;

    public static final String STORAGE_PATH ="doctorsImages/";
    public static final String DATABASE_PATH ="doctors";
    private Uri imageUri;

    public AddDoctorFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_add_doctor, container, false);
//        imageView =(ImageView) container.findViewById(R.id.insertImages);
//        email=(EditText) container.findViewById(R.id.insertEmail);
//        name=(EditText) container.findViewById(R.id.insertName);
//        desc=(EditText) container.findViewById(R.id.insertDesc);
    }

}
