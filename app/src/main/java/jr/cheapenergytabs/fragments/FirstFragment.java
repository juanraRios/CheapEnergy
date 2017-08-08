package jr.cheapenergytabs.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import jr.cheapenergytabs.R;
import jr.cheapenergytabs.adapters.IndicatorAdapter;
import jr.cheapenergytabs.dto.IndicatorDTO;

public class FirstFragment extends Fragment {

    private IndicatorDTO indicatorDTO;

    public FirstFragment() {
        super();
    }


    public static FirstFragment newInstance(IndicatorDTO indicatorDTO) {
        FirstFragment result = new FirstFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("indicatorDTO", indicatorDTO);
        result.setArguments(bundle);
        return result;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        indicatorDTO = (IndicatorDTO) getArguments().getSerializable("indicatorDTO");
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.first_fragment, container, false);
        RecyclerView.Adapter adapter;
        RecyclerView.LayoutManager layoutManager;

        if (!indicatorDTO.getValues().isEmpty()){
            layoutManager = new LinearLayoutManager(getContext());
            RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(layoutManager);
            adapter = new IndicatorAdapter(getContext(), indicatorDTO);
            recyclerView.setAdapter(adapter);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
        }
        else {
            TextView emptyView = (TextView) rootView.findViewById(R.id.empty_recycler_view);
            RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
            recyclerView.setVisibility(View.GONE);
            emptyView.setVisibility(View.VISIBLE);


        }


        return rootView;
    }
}