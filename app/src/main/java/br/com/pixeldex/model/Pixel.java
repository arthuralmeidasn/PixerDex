package br.com.pixeldex.model;

public class Pixel implements Comparable<Pixel> {
    
    private int id;
    private String nome;
    private String raridade;
    private int poder;

    public Pixel() {}

    public Pixel(int id, String nome, String raridade, int poder) {
        this.id = id;
        this.nome = nome;
        this.raridade = raridade;
        this.poder = poder;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getRaridade() { return raridade; }
    public void setRaridade(String raridade) { this.raridade = raridade; }

    public int getPoder() { return poder; }
    public void setPoder(int poder) { this.poder = poder; }

    @Override
    public String toString() {
        return String.format("Pixel(%d, %s, %s, %d)", id, nome, raridade, poder);
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pixel pixel = (Pixel) o;
        return id == pixel.id;
    }

    @Override
    public int compareTo(Pixel outro) {
        int comparacaoNome = this.nome.compareToIgnoreCase(outro.nome);
        
        if (comparacaoNome != 0) {
            return comparacaoNome;
        }
        return Integer.compare(this.id, outro.id);
    }
}