package com.example.bookstore.view.home;

import android.app.Service;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.bookstore.R;
import com.example.bookstore.network.api.APIClient;
import com.example.bookstore.network.services.APIService;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class homeFragment extends Fragment {

    private RecyclerView recyclerViewSlider;
    private APIService  apiService;
    private sliderAdapter sliderAdapter;
    private NavController navController;

    public homeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_home, container, false);
        initUI(view);
        LoadSlider();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
    }

    private void initUI(View view)
    {
        recyclerViewSlider=view.findViewById(R.id.homeRecyclerViewSlider);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        recyclerViewSlider.setLayoutManager(linearLayoutManager);

    }

    private void LoadSlider(){

        apiService= APIClient.getClient().create(APIService.class);
        apiService.getSlider().enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                if (response.isSuccessful()) {
                    setupSlider(response.body());
                }

            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                Toast.makeText(getActivity(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupSlider(List<String> image)
    {
        sliderAdapter=new sliderAdapter(image);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(Objects.requireNonNull(getActivity())
                .getApplicationContext(),
                LinearLayoutManager.HORIZONTAL
                ,false);

        recyclerViewSlider.setLayoutManager(linearLayoutManager);
        recyclerViewSlider.setAdapter(sliderAdapter);

    }
}
