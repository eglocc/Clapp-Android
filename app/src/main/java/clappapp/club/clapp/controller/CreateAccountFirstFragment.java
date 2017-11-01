package clappapp.club.clapp.controller;


import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import clappapp.club.clapp.R;
import clappapp.club.clapp.databinding.FragmentCreateAccountFirstBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class CreateAccountFirstFragment extends Fragment {

    interface onArrowClickListener {
        void next(String email, String name, String surname);
    }

    TextInputEditText email;
    TextInputEditText name;
    TextInputEditText surname;
    ImageView forwardButton;

    private FragmentCreateAccountFirstBinding mBinding;
    private onArrowClickListener mCallback;


    public CreateAccountFirstFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallback = (onArrowClickListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement onArrowClickListener.");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_create_account_first, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        email = mBinding.clapperEmailInput;
        name = mBinding.clapperNameInput;
        surname = mBinding.clapperSurnameInput;
        forwardButton = mBinding.nextPageButton;
        forwardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean error = false;

                String tEmail = email.getText().toString();
                String tName = name.getText().toString();
                String tSurname = surname.getText().toString();

                if (tEmail.equals("")) {
                    email.setError(getResources().getString(R.string.no_email_error));
                    error = true;
                } else if (!tEmail.contains("@ku.edu.tr")) {
                    email.setError(getResources().getString(R.string.email_not_valid_error));
                    error = true;
                }

                if (name.getText().toString().equals("")) {
                    name.setError(getResources().getString(R.string.no_name_error));
                    error = true;
                } else if (name.length() < 2) {
                    name.setError(getResources().getString(R.string.name_too_short_error));
                    error = true;
                }

                if (surname.getText().toString().equals("")) {
                    surname.setError(getResources().getString(R.string.no_surname_error));
                    error = true;
                } else if (surname.length() < 2) {
                    surname.setError(getResources().getString(R.string.surname_too_short_error));
                    error = true;
                }

                if (!error) {
                    mCallback.next(tEmail, tName, tSurname);

                }
            }
        });
    }
}
