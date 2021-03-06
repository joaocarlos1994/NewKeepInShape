package br.com.newkeepinshape.domain.exercicio;


import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.newkeepinshape.domain.treino.Treino;

/**
 * Created by root on 05/10/16.
 */
@DatabaseTable(tableName = "Exercicio")
public final class Exercicio implements Comparable {
    @DatabaseField(generatedId = true)
    private Integer _id;
    @DatabaseField
    private String nome;
    @DatabaseField
    private double peso;
    @DatabaseField
    private int quantidade;
    @DatabaseField
    private double pontuacao;
    @DatabaseField(foreign = true)
    private Treino treino;

    private Exercicio(final Integer id, final String nome, final double peso, final int quantidade, final double pontuacao){
        this._id = id;
        this.nome = nome;
        this.peso = peso;
        this.quantidade = quantidade;
        this.pontuacao = pontuacao;
    }

    public static Exercicio valueOfExercico(final Integer id, final String nome, final double peso, final int quantidade, final double pontuacao){
        return new Exercicio(id, nome, peso, quantidade, pontuacao);
    }

    public Exercicio() {
    }

    public Integer get_id() {
        return _id;
    }

    public String getNome() {
        return nome;
    }

    public double getPeso() {
        return peso;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double getPontuacao() {
        return pontuacao;
    }

    public void adicionarTreino(final Treino treino){
        this.treino = treino;
    }

    public void setTreino(final Treino treino){
        if (treino != null) {
            this.treino = treino;
        }
    }

    public Treino getTreino() {
        return treino;
    }

    @Override
    public int compareTo(final Object object) {
        final Exercicio otherExercicio = (Exercicio) object;
        return getNome().compareTo(otherExercicio.getNome());
    }
}
