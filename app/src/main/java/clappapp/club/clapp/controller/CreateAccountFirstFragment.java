package clappapp.club.clapp.controller;


import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import clappapp.club.clapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CreateAccountFirstFragment extends Fragment {
    TextInputEditText email;
    TextInputEditText name;
    TextInputEditText surname;
    ImageView forwardButton;

    public CreateAccountFirstFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_create_account_first, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        email = (TextInputEditText) getActivity().findViewById(R.id.clapper_email_input);
        name = (TextInputEditText) getActivity().findViewById(R.id.clapper_name_input);
        surname = (TextInputEditText) getActivity().findViewById(R.id.clapper_surname_input);
        forwardButton = (ImageView) getActivity().findViewById(R.id.next_page_button);
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
                    ((CreateAccount) getActivity()).setFirstThree(tEmail, tName, tSurname);
                    android.support.v4.app.FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                    Fragment frag = new CreateAccountSecondFragment();
                    ft.replace(R.id.create_account_frame, frag);
                    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                    ft.commit();
                }
            }
        });
    }
}
