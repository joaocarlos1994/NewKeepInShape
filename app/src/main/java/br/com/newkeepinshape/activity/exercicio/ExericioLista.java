package br.com.newkeepinshape.activity.exercicio;

import android.app.ListActivity;

import android.os.Bundle;

import android.widget.ListAdapter;

import android.widget.SimpleAdapter;


import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

import br.com.newkeepinshape.R;
import br.com.newkeepinshape.domain.exercicio.Exercicio;
import br.com.newkeepinshape.infrastructure.persist.exercicio.ExercicioDaoIml;
import br.com.newkeepinshape.services.exercicio.ExercicioService;

public class ExericioLista extends ListActivity {

    private ExercicioService exercicioService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exericio_lista);

        try {

            exercicioService = ExercicioService.valueOfExercicioService(new ExercicioDaoIml(this));
            final ListAdapter adapter = new SimpleAdapter(this, getExercicios(), R.layout.row_exercicio_layout,
                    new String[] {"Nome", "Peso"}, new int [] {R.id.exercicoNome, R.id.exercicioPeso});

            setListAdapter(adapter);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private List<Map<String, String>> getExercicios() throws SQLException {

        final List<Map<String, String>> datas = new ArrayList<>();
        final List<Exercicio> exercicios = new ArrayList<>(exercicioService.listAllExercicio());

        for (Exercicio exercicio : exercicios) {
            final Map<String, String> exercicioData = new WeakHashMap<>();
            exercicioData.put("Nome", exercicio.getNome());
            exercicioData.put("Peso", Double.toString(exercicio.getPeso()));
            datas.add(exercicioData);
        }


        return datas;
    }

}
