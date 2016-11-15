package br.com.newkeepinshape.activity.exercicio;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;


import java.sql.SQLException;

import br.com.newkeepinshape.R;
import br.com.newkeepinshape.domain.exercicio.Exercicio;
import br.com.newkeepinshape.infrastructure.persist.exercicio.ExercicioDaoIml;
import br.com.newkeepinshape.services.exercicio.ExercicioService;

public class ExercicioEspecificacao extends AppCompatActivity {

    private ExercicioService exercicioService;
    private TextView idExericioTxtView;
    private TextView nomeTxtView;
    private TextView pesoTxtView;
    private TextView quantidadeTxtView;
    private TextView pontuacaoTxtView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercicio_especificacao);

        idExericioTxtView = (TextView) findViewById(R.id.txtExercicioEspecificacaoId);
        nomeTxtView = (TextView) findViewById(R.id.txtExercicioEspecificacaoNome);
        pesoTxtView = (TextView) findViewById(R.id.txtExercicioEspecificacaoPeso);
        quantidadeTxtView = (TextView) findViewById(R.id.txtExercicioEspecificacaoQuantidade);
        pontuacaoTxtView = (TextView) findViewById(R.id.txtExercicioEspecificacaoPontucao);

        try {

            exercicioService = ExercicioService.valueOfExercicioService(new ExercicioDaoIml(this));
            Intent i = getIntent();
            final Integer id = Integer.parseInt(i.getExtras().getString("ID"));
            mostrarExercicio(id);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void handlerDeletarExercicio(final View view)  {

        final Integer id  = Integer.parseInt(idExericioTxtView.getText().toString());
        exercicioService.deleteExercicio(id);
        startActivity(new Intent(this, ExericioLista.class));
        finish();
    }

    public void handlerEditarExercicio(final View view) {
        Intent i = new Intent(this, ExercicioRegister.class);
        i.putExtra("ID", idExericioTxtView.getText().toString());
        startActivity(i);
    }

    private void mostrarExercicio(final Integer idExercicio) {

        final Exercicio exercicio = exercicioService.returnExercicio(idExercicio);

        idExericioTxtView.setText(exercicio.get_id().toString());
        nomeTxtView.setText("Nome: " + exercicio.getNome());
        pesoTxtView.setText("Peso: " + Double.toString(exercicio.getPeso()));
        quantidadeTxtView.setText("Quantidade: " +  Integer.toString(exercicio.getQuantidade()));
        pontuacaoTxtView.setText("Pontuação: " + Double.toString(exercicio.getPontuacao()));

    }

}
