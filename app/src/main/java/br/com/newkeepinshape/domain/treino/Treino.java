package br.com.newkeepinshape.domain.treino;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import br.com.newkeepinshape.domain.exercicio.Exercicio;
import br.com.newkeepinshape.util.DiaSemana;

/**
 * Created by root on 05/10/16.
 */
@DatabaseTable(tableName = "Treino")
public class Treino {

    @DatabaseField(generatedId = true)
    private Integer _id;
    @DatabaseField
    private String nome;
    @DatabaseField
    private DiaSemana dia;
    @ForeignCollectionField
    private Collection<Exercicio> exercicios;

    private Treino (final Integer id, final String nome, final DiaSemana dia){
        this._id = id;
        this.nome = nome;
        this.dia = dia;
        this.exercicios = new ArrayList<Exercicio>();
    }

    public static Treino valeOfTreino(final Integer id, final String nome, final DiaSemana dia) {
        return new Treino(id, nome, dia);
    }

    public Treino() {
    }

    public void adicionarExercicio(final Exercicio exercicio){
        this.exercicios.add(exercicio);
    }

    public void removeExercicio(final Exercicio exercicio){
        this.exercicios.remove(exercicio);
    }

    private double atualizaPontucaoMaxima(){
        double pontos = 0;

        for (Exercicio exercicio: this.exercicios) {
            pontos += exercicio.getPontuacao();
        }
        return pontos;
    }

    public Integer get_id() {
        return _id;
    }

    public String getNome() {
        return nome;
    }

    public DiaSemana getDia() {
        return dia;
    }

    public Collection<Exercicio> getExercicios() {
        return Collections.unmodifiableCollection(exercicios);
    }

    public double getPontuacaoMaxima() {
        return atualizaPontucaoMaxima();
    }

}
