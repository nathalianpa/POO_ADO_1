
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author nathalia
 */
public class GerenciarPib {
    private String pibArquivo;
    private String regioesArquivo;
    private ArrayList<String> estados = new ArrayList<>();
    private ArrayList<Float> pibs = new ArrayList<>();
    private float pibTotal;
    
    public GerenciarPib(String pibArquivo, String regioesArquivo) {
        this.pibArquivo = pibArquivo;
        this.regioesArquivo = regioesArquivo;
    }
    
    public void gerarListas() {
        try {
            File myObj = new File(pibArquivo);
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] textoSeparado = data.split(";");
                estados.add(textoSeparado[0]);
                pibs.add(Float.parseFloat(textoSeparado[1]));
            }

            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    
    public void somarPibs() {
        pibTotal = 0;

        for(float pib : pibs) {
            pibTotal += pib;
        }
    }
    
    public void pibsEstados() {
        for(int i = 0; i < estados.size(); i++) {
            float porcentagem_pib = (pibs.get(i) * 100) / pibTotal;
            System.out.println("Estado: " + estados.get(i) + ", PIB: " + porcentagem_pib + "%");
        }
    }
    
    public void pibsRegioes() {
        try {
            File myObj = new File(regioesArquivo);
            Scanner myReader = new Scanner(myObj);

            FileWriter fw = new FileWriter("./src/saida.txt", false);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            while (myReader.hasNextLine()) {
                String regiao = myReader.nextLine();
                float pibRegiao = 0;
                String line = "";

                while (myReader.hasNextLine() && !(line = myReader.nextLine()).isEmpty()) {
                    String estado = line;

                    for(int i = 0; i < estados.size(); i++) {
                        if(estados.get(i).equals(estado)) {
                            pibRegiao += pibs.get(i);
                            i = estados.size() + 1;
                        }
                    }
                }

                pw.println("Região: " + regiao + ", PIB: " + pibRegiao);
                pw.flush();
            }

            myReader.close();
            pw.close();
            bw.close();
            fw.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
