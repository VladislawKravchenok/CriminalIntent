package by.vladislaw.kravchenok.criminalintent;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.io.File;
import java.util.UUID;

import by.vladislaw.kravchenok.criminalintent.model.Crime;
import by.vladislaw.kravchenok.criminalintent.model.CrimeLab;

/**
 * Created by Vladislaw Kravchenok on 08.07.2019.
 */
public class CrimePhotoFragment extends DialogFragment {

    private static final String TAG = CrimePhotoFragment.class.getSimpleName();
    private static final String ARG_CRIME_ID = "crime.id";

    private Crime mCrime;
    private File mImageFile;
    private ImageView mCrimePhoto;

    public static CrimePhotoFragment newInstance(UUID id) {
        CrimePhotoFragment crimePhotoFragment = new CrimePhotoFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(ARG_CRIME_ID, id);
        crimePhotoFragment.setArguments(bundle);
        return crimePhotoFragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID crimeId = (UUID) getArguments().getSerializable(ARG_CRIME_ID);
        mCrime = CrimeLab.get(getActivity()).getCrime(crimeId);
        mImageFile = CrimeLab.get(getActivity()).getPhotoFile(mCrime);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView");
        View view = inflater.inflate(R.layout.dialog_image, container, false);
        mCrimePhoto = view.findViewById(R.id.crime_photo);

        mCrimePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        updatePhotoView();
        return view;
    }

    private void updatePhotoView() {
        if (mImageFile == null || !mImageFile.exists()) {
            mCrimePhoto.setImageDrawable(null);
        } else {
           mCrimePhoto.setImageBitmap(PictureUtils.getScaledBitmap(mImageFile.getPath(), getActivity()));
        }
    }
}
