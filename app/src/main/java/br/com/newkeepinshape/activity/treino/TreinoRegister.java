package br.com.newkeepinshape.activity.treino;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import br.com.newkeepinshape.R;
import br.com.newkeepinshape.util.DiaSemana;

public class TreinoRegister extends AppCompatActivity {

    private Spinner diasSemanaSpinner;
    private Spinner exercicioSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_treino_register);

        diasSemanaSpinner = (Spinner) findViewById(R.id.idSpinnerTreinoDiaSemana);
        exercicioSpinner = (Spinner) findViewById(R.id.idSpinnerTreinoExercicio);

        carregarDiaSemana();

    }



    private void carregarDiaSemana() {
        final ArrayAdapter diasSemana = new ArrayAdapter<DiaSemana>(this, android.R.layout.simple_spinner_item, diasSemana());
        diasSemanaSpinner.setAdapter(diasSemana);
    }

    private List<DiaSemana> diasSemana() {

        final List<DiaSemana> dias = new ArrayList<>();
        dias.add(DiaSemana.SEGUNDA);
        dias.add(DiaSemana.TERÇA);
        dias.add(DiaSemana.QUARTA);
        dias.add(DiaSemana.QUINTA);
        dias.add(DiaSemana.SEXTA);
        dias.add(DiaSemana.SÁBADO);
        dias.add(DiaSemana.DOMINGO);

        return dias;

    }

}
