package jr.cheapenergytabs.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import jr.cheapenergytabs.R;
import jr.cheapenergytabs.adapters.IndicatorAdapter;
import jr.cheapenergytabs.dto.IndicatorDTO;

public class SummaryFragment extends Fragment {

    private IndicatorDTO indicatorDTO;

    public SummaryFragment() {
        super();
    }


    public static SummaryFragment newInstance(IndicatorDTO indicatorDTO) {
        SummaryFragment result = new SummaryFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("indicatorDTO", indicatorDTO);
        result.setArguments(bundle);
        return result;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.sumary_fragment, container, false);

//        CardView cardView = (CardView) findViewById(R.id.currentCardView);

//        TextView textView1 = (TextView)getLayoutInflater().inflate(R.layout.textview_template, linearLayout).findViewById(R.id.textview_template);
//        TextView textView2 = (TextView)getLayoutInflater().inflate(R.layout.textview_template, linearLayout).findViewById(R.id.textview_template);
//        TextView textView3 = (TextView)getLayoutInflater().inflate(R.layout.textview_template, linearLayout).findViewById(R.id.textview_template);
//
//        textView1.setText("Take out trash.");
//        textView2.setText("Wash windows.");
//        textView3.setText("Why won't you work?");

        return rootView;
    }

}