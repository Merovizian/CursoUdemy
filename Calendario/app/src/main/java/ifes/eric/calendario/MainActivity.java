package ifes.eric.calendario;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.CalendarView;
import android.widget.Toast;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    //private CalendarView calendario;
    private MaterialCalendarView calendarioNovo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        calendarioNovo =  findViewById(R.id.calendarView);
        calendarioNovo.state().edit()
                .setMinimumDate(CalendarDay.from(2022,1,1))
                .setMaximumDate(CalendarDay.from(2024,1,1))
                .commit();

        CharSequence meses[] = {"Janeiro", "Fevereiro", "Março", "Abril","Maio", "Junho", "Julho", "Eric", "Setembro", "Outubro", "Novembro", "Dezembro"};
        calendarioNovo.setTitleMonths(meses);


        calendarioNovo.setOnMonthChangedListener(new OnMonthChangedListener() {
            @Override
            public void onMonthChanged(MaterialCalendarView widget, CalendarDay date) {
                Log.i("data: ", "valor: " + date.getMonth());
            }
        });


        /*
        calendario = findViewById(R.id.calendarView);

        calendario.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                Toast.makeText(MainActivity.this, i2 + "/" + i1 + "/" + i, Toast.LENGTH_SHORT).show();
            }
        });
         */



    }
}