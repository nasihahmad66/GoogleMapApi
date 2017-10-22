package id.sch.smktelkom_mlg.learning.maps.Fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

import id.sch.smktelkom_mlg.learning.maps.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovingMapFragment extends Fragment implements OnMapReadyCallback {

    static final CameraPosition KDR = CameraPosition.builder()
            .target(new LatLng(-7.8474705, 112.0046433))
            .zoom(17)
            .bearing(90)
            .tilt(45)
            .build();
    static final CameraPosition PAPUA = CameraPosition.builder()
            .target(new LatLng(-4.269928, 138.080353))
            .zoom(17)
            .bearing(0)
            .tilt(45)
            .build();
    static final CameraPosition BALI = CameraPosition.builder()
            .target(new LatLng(-8.719735, 115.169073))
            .zoom(17)
            .bearing(0)
            .tilt(45)
            .build();
    static final CameraPosition JKT = CameraPosition.builder()
            .target(new LatLng(-6.175110, 106.865039))
            .zoom(17)
            .bearing(90)
            .tilt(45)
            .build();

    GoogleMap m_map;
    boolean mapReady = false;

    public MovingMapFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_moving_map, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Button btnPapua = (Button) getView().findViewById(R.id.btnPapua);
        btnPapua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mapReady)
                    flyTo(PAPUA);
            }
        });

        Button btnBali = (Button) getView().findViewById(R.id.btnBali);
        btnBali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mapReady)
                    flyTo(BALI);
            }
        });

        Button btnJkt = (Button) getView().findViewById(R.id.btnJkt);
        btnJkt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mapReady)
                    flyTo(JKT);
            }
        });

        SupportMapFragment mapFragment = (SupportMapFragment) this.getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap map) {
        mapReady = true;
        m_map = map;
        m_map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        flyTo(KDR);

    }

    private void flyTo(CameraPosition target) {
        m_map.animateCamera(CameraUpdateFactory.newCameraPosition(target), 10000, null);
    }
}
