package br.com.newkeepinshape.activity.exercicio;

import android.app.ListActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.ListAdapter;

import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;


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
                    new String[] {"id", "Nome", "Peso"}, new int [] {R.id.idExercicio, R.id.exercicoNome, R.id.exercicioPeso});

            setListAdapter(adapter);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        final View view = v;
        final TextView textView = (TextView) view.findViewById(R.id.idExercicio);


        Intent i = new Intent(this, ExercicioEspecificacao.class);
        i.putExtra("ID",textView.getText());
        startActivity(i);

    }

    private List<Map<String, String>> getExercicios() {

        final List<Map<String, String>> datas = new ArrayList<>();
        final List<Exercicio> exercicios = new ArrayList<>(exercicioService.listAllExercicio());

        for (Exercicio exercicio : exercicios) {
            final Map<String, String> exercicioData = new WeakHashMap<>();
            exercicioData.put("id", exercicio.get_id().toString());
            exercicioData.put("Nome", exercicio.getNome());
            exercicioData.put("Peso", Double.toString(exercicio.getPeso()));
            datas.add(exercicioData);
        }


        return datas;
    }

}
