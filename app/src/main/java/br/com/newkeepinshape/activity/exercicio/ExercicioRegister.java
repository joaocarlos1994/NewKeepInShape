package br.com.newkeepinshape.activity.exercicio;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.SQLException;

import br.com.newkeepinshape.R;
import br.com.newkeepinshape.domain.exercicio.ExercicioRepository;
import br.com.newkeepinshape.infrastructure.persist.exercicio.ExercicioDaoIml;
import br.com.newkeepinshape.services.exercicio.ExercicioService;

public class ExercicioRegister extends AppCompatActivity {

    private EditText idEdText;
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

        try {
            exercicioService = ExercicioService.valueOfExercicioService(new ExercicioDaoIml(this));
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void salvarTreino(View view) throws SQLException {

        nomeEdText = (EditText) findViewById(R.id.edt_nome_exercicio);
        pesoEdText = (EditText) findViewById(R.id.edt_peso_exercicio);
        quantidadeEdText = (EditText) findViewById(R.id.edt_quantidade_exercicio);
        pontuacaoEdText = (EditText) findViewById(R.id.edt_pontuacao_exercicio);

        exercicioService.saveExercicio(nomeEdText, pesoEdText, quantidadeEdText, pontuacaoEdText);

        Toast.makeText(this, "Treino salvo", Toast.LENGTH_LONG).show();
    }

}
