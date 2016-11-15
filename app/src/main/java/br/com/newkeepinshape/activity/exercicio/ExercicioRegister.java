package br.com.newkeepinshape.activity.exercicio;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLException;

import br.com.newkeepinshape.R;
import br.com.newkeepinshape.domain.exercicio.Exercicio;

import br.com.newkeepinshape.infrastructure.persist.exercicio.ExercicioDaoIml;
import br.com.newkeepinshape.services.exercicio.ExercicioService;

public class ExercicioRegister extends AppCompatActivity {

    private TextView idTxtView;
    private EditText nomeEdText;
    private EditText pesoEdText;
    private EditText quantidadeEdText;
    private EditText pontuacaoEdText;
    private ExercicioService exercicioService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercicio_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        idTxtView = (TextView) findViewById(R.id.txtIdRegisterExercicio);
        nomeEdText = (EditText) findViewById(R.id.edt_nome_exercicio);
        pesoEdText = (EditText) findViewById(R.id.edt_peso_exercicio);
        quantidadeEdText = (EditText) findViewById(R.id.edt_quantidade_exercicio);
        pontuacaoEdText = (EditText) findViewById(R.id.edt_pontuacao_exercicio);

        try {
            exercicioService = ExercicioService.valueOfExercicioService(new ExercicioDaoIml(this));
        } catch (SQLException e) {
            e.printStackTrace();
        }


        if(getIntent() != null) {

            Intent i = getIntent();
            final Integer id = Integer.parseInt(i.getExtras().getString("ID"));

            idTxtView.setText(id.toString());

            carregarExercicio(id);

        } else {

        }

    }

    public void salvarTreino(View view) {

        exercicioService.salvarOuAtualizarExercicio(idTxtView, nomeEdText, pesoEdText, quantidadeEdText, pontuacaoEdText);

        Toast.makeText(this, "Treino salvo", Toast.LENGTH_LONG).show();
    }

    private void carregarExercicio(final Integer id) {

        final Exercicio exercicio = exercicioService.returnExercicio(id);

        nomeEdText.setText(exercicio.getNome());
        pesoEdText.setText(Double.toString(exercicio.getPeso()));
        quantidadeEdText.setText(Integer.toString(exercicio.getQuantidade()));
        pontuacaoEdText.setText(Double.toString(exercicio.getPontuacao()));

    }

}
