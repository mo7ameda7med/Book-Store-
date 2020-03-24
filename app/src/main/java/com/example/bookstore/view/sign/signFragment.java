package com.example.bookstore.view.sign;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.constraintlayout.widget.Group;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookstore.R;
import com.example.bookstore.network.api.APIClient;
import com.example.bookstore.network.models.SignForm;
import com.example.bookstore.network.models.Token;
import com.example.bookstore.network.models.User;
import com.example.bookstore.network.services.APIService;
import com.example.bookstore.uitl.PrefManager;
import com.example.bookstore.view.splash.splashActivity;
import com.squareup.picasso.Picasso;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class signFragment extends Fragment implements View.OnClickListener {
    private View viewSign;
    private View viewProfile;
    private Context context;

    //Sign
    private Group groupSign;
    private Button BtnSignIn;
    private Button BtnSignUp;
    private EditText edtName;
    private EditText edtEmail;
    private EditText edtPhone;
    private EditText edtAddress;
    private EditText edtPassword;
    private Button btnSign;

    //profile
    private ImageView imageProfile;
    private TextView tvName;
    private TextView tvEmail;
    private TextView tvPhone;
    private Button btnLogOut;


    public signFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign, container, false);
        context= Objects.requireNonNull(getActivity()).getApplicationContext();
        initUI(view);
        // Inflate the layout for this fragment
        if (PrefManager.retrieveAccessToken(Objects.requireNonNull(getActivity())) != null) {
            viewProfile.setVisibility(View.VISIBLE);
            viewSign.setVisibility(View.GONE);
            initProfileUi(view);
            getProfile(PrefManager.retrieveAccessToken(getActivity()));
        } else {
            viewProfile.setVisibility(View.GONE);
            viewSign.setVisibility(View.VISIBLE);

            initSignUi(view);
        }

        return view;
    }


    private void initSignUi(View view) {
        groupSign = view.findViewById(R.id.group);
        BtnSignIn = view.findViewById(R.id.signIn_BT);
        BtnSignUp = view.findViewById(R.id.signUp_BT);
        edtName = view.findViewById(R.id.userName_ET);
        edtEmail = view.findViewById(R.id.email_ET);
        edtAddress = view.findViewById(R.id.address_ET);
        edtPassword = view.findViewById(R.id.password_ET);
        edtPhone = view.findViewById(R.id.mobile_ET);
        btnSign = view.findViewById(R.id.signUp_In_BT);

        BtnSignUp.setOnClickListener(this);
        BtnSignIn.setOnClickListener(this);
        btnSign.setOnClickListener(this);
    }

    private void initProfileUi(View view) {
       imageProfile=view.findViewById(R.id.profileImageView);
        tvName=view.findViewById(R.id.profileName_txt);
        tvEmail=view.findViewById(R.id.emailProfile_txt);
        tvPhone=view.findViewById(R.id.mobileProfile_txt);
        btnLogOut=view.findViewById(R.id.logout_Bt);

        btnLogOut.setOnClickListener(this);
    }

    private void initUI(View view) {
        viewSign = view.findViewById(R.id.layout_sign);
        viewProfile = view.findViewById(R.id.layout_profile);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.signUp_BT:
                groupSign.setVisibility(View.VISIBLE);

                BtnSignUp.setTextAppearance(context,R.style.ButtonClicked);
                BtnSignIn.setTextAppearance(context,R.style.ButtonDisable);

                btnSign.setText("Sign Up");
                break;
            case R.id.signIn_BT:
                groupSign.setVisibility(View.GONE);

                BtnSignUp.setTextAppearance(context,R.style.ButtonDisable);
                BtnSignIn.setTextAppearance(context,R.style.ButtonClicked);

                btnSign.setText("Sign In");
                break;
            case R.id.signUp_In_BT:

                if(groupSign.getVisibility()==View.VISIBLE)
                {
                    String email = edtEmail.getText().toString();
                    String password = edtPassword.getText().toString();
                    String phone = edtPhone.getText().toString();
                    String address = edtAddress.getText().toString();
                    String name = edtName.getText().toString();
                    signUp(new SignForm(name,phone,email,address,password));

                }
                else
                {
                    String email = edtEmail.getText().toString();
                    String password = edtPassword.getText().toString();
                    signIn(new SignForm(email,password));
                }
                break;

            case R.id.logout_Bt:

                PrefManager.storeAccessToken(Objects.requireNonNull(getActivity()),null);
                resetApp();
                break;
        }
    }

    private void signIn(SignForm signForm)
    {
        APIService apiService= APIClient.getClient().create(APIService.class);
        apiService.login(signForm).enqueue(new Callback<Token>() {
            @Override
            public void onResponse(Call<Token> call, Response<Token> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(context, "WelCome back!", Toast.LENGTH_SHORT).show();
                    assert response.body()!=null;
                    storeToken(response.body());
                    resetApp();
                }else
                {
                    Toast.makeText(context, "Wrong email or password!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Token> call, Throwable t) {
                Toast.makeText(context, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
    private void getProfile(String accessToken) {
        APIService apiService = APIClient.getClient().create(APIService.class);
        apiService.getProfile(accessToken).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful())
                {
                    User user= response.body();
                    assert user != null;
                    Picasso.get().load(user.getImage()).into(imageProfile);
                    tvName.setText(user.getName());
                    tvEmail.setText(user.getEmail());
                    tvPhone.setText(user.getPhone());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }

    private void signUp(SignForm signForm)
    {
        APIService apiService= APIClient.getClient().create(APIService.class);
        apiService.register(signForm).enqueue(new Callback<Token>() {
            @Override
            public void onResponse(Call<Token> call, Response<Token> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(context, "Hi!", Toast.LENGTH_SHORT).show();
                }else
                {
                    Toast.makeText(context, "Wrong email or password!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Token> call, Throwable t) {
                Toast.makeText(context, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
    private void storeToken(Token token)
    {
        PrefManager.storeAccessToken(Objects.requireNonNull(getActivity()),token.getAccessToken());
    }
    private void resetApp()
    {
        Objects.requireNonNull(getActivity()).startActivity(new Intent(getActivity(), splashActivity.class ));
        getActivity().finish();
    }
}

