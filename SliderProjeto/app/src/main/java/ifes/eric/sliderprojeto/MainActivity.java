package ifes.eric.sliderprojeto;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.heinrichreimersoftware.materialintro.app.IntroActivity;
import com.heinrichreimersoftware.materialintro.slide.FragmentSlide;
import com.heinrichreimersoftware.materialintro.slide.SimpleSlide;
import com.heinrichreimersoftware.materialintro.slide.Slide;

public class MainActivity extends IntroActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

//   ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Configurações iniciais ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        setButtonBackVisible(false);
        setButtonNextVisible(false);
//  *********************************  Configurações iniciais ***************************************


//   ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Slide Fragment  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        addSlide(new FragmentSlide.Builder()
                .background(R.color.purple_700)
                .fragment(R.layout.intro_um)
                .build()
        );
        addSlide(new FragmentSlide.Builder()
                .background(R.color.purple_500)
                .fragment(R.layout.intro_dois)
                .build()
        );
        addSlide(new FragmentSlide.Builder()
                .background(R.color.teal_700)
                .fragment(R.layout.intro_tres)
                .build()
        );
//  *********************************  Slide Fragment  *********************************************




//   ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Slide Image  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
/*

        addSlide(new SimpleSlide.Builder()
                .title("Titulo")
                .description("Descrição")
                .image(R.drawable.um)
                .background(android.R.color.holo_orange_light)
                .build()
        );

        addSlide(new SimpleSlide.Builder()
                .title("Titulo2")
                .description("Descrição2")
                .image(R.drawable.dois)
                .background(com.heinrichreimersoftware.materialintro.R.color.mi_icon_color_dark)
                .build()
        );

        addSlide(new SimpleSlide.Builder()
                .title("Titulo3")
                .description("Descrição3")
                .image(R.drawable.tres)
                .background(com.heinrichreimersoftware.materialintro.R.color.mi_text_color_secondary_light)
                .build()
        );

 */
//  *********************************  Slide Image *************************************************
    }
}


