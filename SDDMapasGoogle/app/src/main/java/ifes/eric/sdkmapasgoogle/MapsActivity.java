package ifes.eric.sdkmapasgoogle;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.icu.text.Transliterator;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import ifes.eric.sdkmapasgoogle.databinding.ActivityMapsBinding;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        // Cria um objeto Latitude Longitude.
        LatLng ifesLocation = new LatLng(-20.64643394, -40.495842706);

        // Mudar a exibição do mapa
        mMap.setMapType(GoogleMap.MAP_TYPE_NONE);

        // Mover a camera -  ZOOM  2.0  até 21.0
        mMap.moveCamera(   CameraUpdateFactory.newLatLngZoom(ifesLocation, 12) );


        mMap.addCircle(new CircleOptions()
                .center(ifesLocation)
                .radius(500)
                .fillColor(R.color.black)

        );





//   --------------------------------------  Adiciona Marcador e configuração  ----------------------------------------
        mMap.addMarker(
                new MarkerOptions()
                        .position(ifesLocation)
                        .title("IFES")
                        .icon(
                                BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE)  // UTILIZANDO MARCADOR PADRAO
                                //BitmapDescriptorFactory.fromResource(R.drawable.clipart2003120)  -- Utilizando Bitmap
                        )

        );
//   ***********************************    Adiciona Marcador e configuração     ***************************************



//   --------------------------------------  Eventos de Click e de Click Longo  ----------------------------------------
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(@NonNull LatLng latLng) {
                LatLng marcador = (latLng);

                Double latitude = latLng.latitude;
                Double longitude = latLng.longitude;

                Toast.makeText(MapsActivity.this, latitude + "\n" + longitude , Toast.LENGTH_SHORT).show();

                mMap.addMarker(
                        new MarkerOptions()
                                .position(latLng)
                                .title("Local Clicado")
                                .snippet("DESCRICAO BOBA")
                );

            }
        });

        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(@NonNull LatLng latLng) {
                Toast.makeText(MapsActivity.this, "Long: \n" + latLng.latitude +"\n"+latLng.longitude
                        , Toast.LENGTH_SHORT).show();
            }
        });
//   ***********************************   Eventos de Click e de Click Longo   *****************************************

    }





}